package mn.odi.labor.pages.organization;

import java.util.List;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.util.EnumSelectModel;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.entities.labor.ReportDetail;
import mn.odi.labor.entities.labor.ReportStatus;
import mn.odi.labor.enums.JobTypeEnum;
import mn.odi.labor.enums.ReportDetailType;
import mn.odi.labor.models.CommonSM;

public class LaborReportInsert {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Property
	@Persist
	private ReportDetail reportDetail;

	@Property
	@Persist
	private List<GeneralType> list;

	@Property
	@Persist
	private GeneralType row;

	@Property
	@Persist
	private ReportStatus reportStatus;

	@Property
	private String name;

	@InjectComponent
	private Zone listZone;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	public void onActivate(ReportStatus rt) {
		reportStatus = rt;
	}

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("report");
		loginState.setPageTitle(message.get("report"));
		reportDetail = new ReportDetail();
		list = dao.getGeneralTypeList();
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	public SelectModel getGeneralTypeSM() {
		CommonSM<GeneralType> sm = new CommonSM<GeneralType>(GeneralType.class, dao.getGeneralTypeList(), "getName");
		return sm;
	}

	public SelectModel getReportTypeEnum() {
		EnumSelectModel sm = new EnumSelectModel(ReportDetailType.class, message);
		return sm;
	}

	public SelectModel getJobTypeEnum() {
		EnumSelectModel sm = new EnumSelectModel(JobTypeEnum.class, message);
		return sm;
	}

	@CommitAfter
	public Object onSuccess() {
		dao.saveOrUpdateObject(reportStatus);
		ReportDetail rt = dao.getReportDetailListWithParameter(reportDetail.getGeneralType(),
				reportDetail.getDetailType(), reportDetail.getJobType(), reportStatus.getYear(),
				reportStatus.getMonth());
		if (rt != null) {
			rt.setValue(reportDetail.getValue());
			reportDetail = rt;
		}
		reportDetail.setReportStatusId(reportStatus);
		dao.saveOrUpdateObject(reportDetail);
		list = dao.getGeneralTypeList();
		return request.isXHR() ? listZone.getBody() : null;
	}

	public Integer getAddedPer() {
		ReportDetail rt = dao.getReportDetailListWithParameter(row, ReportDetailType.NEMEGDSEN, JobTypeEnum.PERMANENT,
				reportStatus.getYear(), reportStatus.getMonth());
		if (rt != null && rt.getValue() != null)
			return rt.getValue();
		else
			return 0;
	}

	public Integer getAddedQua() {
		ReportDetail rt = dao.getReportDetailListWithParameter(row, ReportDetailType.NEMEGDSEN, JobTypeEnum.QUARTERLY,
				reportStatus.getYear(), reportStatus.getMonth());
		if (rt != null && rt.getValue() != null)
			return rt.getValue();
		else
			return 0;
	}

	public Integer getAddedTemp() {
		ReportDetail rt = dao.getReportDetailListWithParameter(row, ReportDetailType.NEMEGDSEN, JobTypeEnum.TEMPORARAY,
				reportStatus.getYear(), reportStatus.getMonth());
		if (rt != null && rt.getValue() != null)
			return rt.getValue();
		else
			return 0;
	}

	public Integer getAddedTotal() {
		Long rt = dao.getReportDetailAddRemove(row, ReportDetailType.NEMEGDSEN, reportStatus.getYear(),
				reportStatus.getMonth());
		if (rt != null)
			return rt.intValue();
		else
			return 0;
	}

	public Integer getRemovedPer() {
		ReportDetail rt = dao.getReportDetailListWithParameter(row, ReportDetailType.HASAGDSAN, JobTypeEnum.PERMANENT,
				reportStatus.getYear(), reportStatus.getMonth());
		if (rt != null && rt.getValue() != null)
			return rt.getValue();
		else
			return 0;
	}

	public Integer getRemovedQua() {
		ReportDetail rt = dao.getReportDetailListWithParameter(row, ReportDetailType.HASAGDSAN, JobTypeEnum.QUARTERLY,
				reportStatus.getYear(), reportStatus.getMonth());
		if (rt != null && rt.getValue() != null)
			return rt.getValue();
		else
			return 0;
	}

	public Integer getRemovedTemp() {
		ReportDetail rt = dao.getReportDetailListWithParameter(row, ReportDetailType.HASAGDSAN, JobTypeEnum.TEMPORARAY,
				reportStatus.getYear(), reportStatus.getMonth());
		if (rt != null && rt.getValue() != null)
			return rt.getValue();
		else
			return 0;
	}

	public Integer getRemovedTotal() {
		Long rt = dao.getReportDetailAddRemove(row, ReportDetailType.HASAGDSAN, reportStatus.getYear(),
				reportStatus.getMonth());
		if (rt != null)
			return rt.intValue();
		else
			return 0;
	}
}