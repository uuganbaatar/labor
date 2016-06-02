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
import mn.odi.labor.enums.ReportStatusEnum;
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

	private Integer number;

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
		if (number == null)
			number = 1;
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
		reportStatus.setReportStatus(ReportStatusEnum.DRAFT);
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
		for (int i = 0; i < 3; i++) {
			JobTypeEnum en = null;
			switch (i) {
			case 0:
				en = JobTypeEnum.PERMANENT;
				break;
			case 1:
				en = JobTypeEnum.QUARTERLY;
				break;
			case 2:
				en = JobTypeEnum.TEMPORARAY;
				break;
			default:
				en = null;
				break;
			}
			ReportDetail rtd = dao.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.DUN,
					en, reportStatus.getYear(), reportStatus.getMonth());
			if (rtd == null) {
				rtd = new ReportDetail();
			}
			rtd.setGeneralType(reportDetail.getGeneralType());
			rtd.setDetailType(ReportDetailType.DUN);
			rtd.setJobType(en);
			rtd.setReportStatusId(reportStatus);
			int pre = 0;
			int added = 0;
			int removed = 0;
			switch (en) {
			case PERMANENT:
				if (dao.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.NEMEGDSEN,
						JobTypeEnum.PERMANENT, reportStatus.getYear(), reportStatus.getMonth()) != null)
					added = dao
							.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.NEMEGDSEN,
									JobTypeEnum.PERMANENT, reportStatus.getYear(), reportStatus.getMonth())
							.getValue();
				if (dao.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.HASAGDSAN,
						JobTypeEnum.PERMANENT, reportStatus.getYear(), reportStatus.getMonth()) != null)
					removed = dao
							.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.HASAGDSAN,
									JobTypeEnum.PERMANENT, reportStatus.getYear(), reportStatus.getMonth())
							.getValue();
				if (dao.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.DUN,
						JobTypeEnum.PERMANENT, reportStatus.getYear(), reportStatus.getMonth() - 1) != null)
					removed = dao
							.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.DUN,
									JobTypeEnum.PERMANENT, reportStatus.getYear(), reportStatus.getMonth() - 1)
							.getValue();
				rtd.setValue(pre + added - removed);
				break;
			case QUARTERLY:
				if (dao.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.NEMEGDSEN,
						JobTypeEnum.QUARTERLY, reportStatus.getYear(), reportStatus.getMonth()) != null)
					added = dao
							.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.NEMEGDSEN,
									JobTypeEnum.QUARTERLY, reportStatus.getYear(), reportStatus.getMonth())
							.getValue();
				if (dao.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.HASAGDSAN,
						JobTypeEnum.QUARTERLY, reportStatus.getYear(), reportStatus.getMonth()) != null)
					removed = dao
							.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.HASAGDSAN,
									JobTypeEnum.QUARTERLY, reportStatus.getYear(), reportStatus.getMonth())
							.getValue();
				if (dao.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.DUN,
						JobTypeEnum.QUARTERLY, reportStatus.getYear(), reportStatus.getMonth() - 1) != null)
					removed = dao
							.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.DUN,
									JobTypeEnum.QUARTERLY, reportStatus.getYear(), reportStatus.getMonth() - 1)
							.getValue();
				rtd.setValue(pre + added - removed);
				break;
			case TEMPORARAY:
				if (dao.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.NEMEGDSEN,
						JobTypeEnum.TEMPORARAY, reportStatus.getYear(), reportStatus.getMonth()) != null)
					added = dao
							.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.NEMEGDSEN,
									JobTypeEnum.TEMPORARAY, reportStatus.getYear(), reportStatus.getMonth())
							.getValue();
				if (dao.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.HASAGDSAN,
						JobTypeEnum.TEMPORARAY, reportStatus.getYear(), reportStatus.getMonth()) != null)
					removed = dao
							.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.HASAGDSAN,
									JobTypeEnum.TEMPORARAY, reportStatus.getYear(), reportStatus.getMonth())
							.getValue();
				if (dao.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.DUN,
						JobTypeEnum.TEMPORARAY, reportStatus.getYear(), reportStatus.getMonth() - 1) != null)
					removed = dao
							.getReportDetailListWithParameter(reportDetail.getGeneralType(), ReportDetailType.DUN,
									JobTypeEnum.TEMPORARAY, reportStatus.getYear(), reportStatus.getMonth() - 1)
							.getValue();
				rtd.setValue(pre + added - removed);
				break;
			default:
				rtd.setValue(0);
				break;
			}
			dao.saveOrUpdateObject(rtd);
		}
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

	public Integer getPrePer() {
		ReportDetail rt = dao.getReportDetailListWithParameter(row, ReportDetailType.DUN, JobTypeEnum.PERMANENT,
				reportStatus.getYear(), reportStatus.getMonth() - 1);
		if (rt != null && rt.getValue() != null)
			return rt.getValue();
		else
			return 0;
	}

	public Integer getPreQua() {
		ReportDetail rt = dao.getReportDetailListWithParameter(row, ReportDetailType.DUN, JobTypeEnum.QUARTERLY,
				reportStatus.getYear(), reportStatus.getMonth() - 1);
		if (rt != null && rt.getValue() != null)
			return rt.getValue();
		else
			return 0;
	}

	public Integer getPreTemp() {
		ReportDetail rt = dao.getReportDetailListWithParameter(row, ReportDetailType.DUN, JobTypeEnum.TEMPORARAY,
				reportStatus.getYear(), reportStatus.getMonth() - 1);
		if (rt != null && rt.getValue() != null)
			return rt.getValue();
		else
			return 0;
	}

	public Integer getPreTotal() {
		Long rt = dao.getReportDetailAddRemove(row, ReportDetailType.DUN, reportStatus.getYear(),
				reportStatus.getMonth() - 1);
		if (rt != null)
			return rt.intValue();
		else
			return 0;
	}

	public Integer getPer() {
		ReportDetail rt = dao.getReportDetailListWithParameter(row, ReportDetailType.DUN, JobTypeEnum.PERMANENT,
				reportStatus.getYear(), reportStatus.getMonth());
		if (rt != null && rt.getValue() != null)
			return rt.getValue();
		else
			return 0;
	}

	public Integer getQua() {
		ReportDetail rt = dao.getReportDetailListWithParameter(row, ReportDetailType.DUN, JobTypeEnum.QUARTERLY,
				reportStatus.getYear(), reportStatus.getMonth());
		if (rt != null && rt.getValue() != null)
			return rt.getValue();
		else
			return 0;
	}

	public Integer getTemp() {
		ReportDetail rt = dao.getReportDetailListWithParameter(row, ReportDetailType.DUN, JobTypeEnum.TEMPORARAY,
				reportStatus.getYear(), reportStatus.getMonth());
		if (rt != null && rt.getValue() != null)
			return rt.getValue();
		else
			return 0;
	}

	public Integer getTotal() {
		Long rt = dao.getReportDetailAddRemove(row, ReportDetailType.DUN, reportStatus.getYear(),
				reportStatus.getMonth());
		if (rt != null)
			return rt.intValue();
		else
			return 0;
	}

	public Integer getNumber() {
		return number++;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@CommitAfter
	public Object onActionFromReportSend() {
		reportStatus.setReportStatus(ReportStatusEnum.SENT);
		dao.saveOrUpdateObject(reportStatus);
		return null;
	}

	public boolean getReportSent() {
		switch (reportStatus.getReportStatus()) {
		case DRAFT:
			return false;
		case SENT:
			return true;
		default:
			return false;
		}
	}

}