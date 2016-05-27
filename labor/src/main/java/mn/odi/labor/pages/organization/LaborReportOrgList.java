package mn.odi.labor.pages.organization;

import java.util.Calendar;
import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.labor.Report;
import mn.odi.labor.entities.labor.ReportStatus;

public class LaborReportOrgList {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Property
	@Persist
	private List<Report> list;

	@Property
	private Report row;

	@Property
	@Persist
	private Integer year;

	@Property
	private Integer month;

	@Property
	private String btnClass;

	private final String check = "fa fa-check";

	private final String draft = "fa fa-save";

	private final String cross = "fa fa-times";

	private final String lock = "fa fa-lock";

	private final String checkButton = "btn btn-icon waves-effect waves-light btn-success report-button";

	private final String draftButton = "btn btn-icon waves-effect waves-light btn-primary report-button";

	private final String crossButton = "btn btn-icon waves-effect waves-light btn-danger report-button";

	private final String lockButton = "btn btn-icon waves-effect waves-light btn-inverse report-button disabled";

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("report");
		loginState.setPageTitle(message.get("report"));
		list = dao.getReportList();

		if (month == null) {
			month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		}

		if (year == null)
			year = Calendar.getInstance().get(Calendar.YEAR);
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	public String getStatus(Integer m) {
		if (year <= Calendar.getInstance().get(Calendar.YEAR) && month >= m) {
			ReportStatus rt = dao.getReportStatusList(row, year, m);
			if (rt != null) {
				switch (rt.getReportStatus()) {
				case DRAFT:
					btnClass = draft;
					return draftButton;
				case SENT:
					btnClass = check;
					return checkButton;
				default:
					btnClass = cross;
					return crossButton;
				}
			} else {
				btnClass = cross;
				return crossButton;
			}
		} else {
			btnClass = lock;
			return lockButton;
		}
	}

	public String getJanuary() {
		return this.getStatus(1);
	}

	public String getFebruary() {
		return this.getStatus(2);
	}

	public String getMarch() {
		return this.getStatus(3);
	}

	public String getApril() {
		return this.getStatus(4);
	}

	public String getMay() {
		return this.getStatus(5);
	}

	public String getJune() {
		return this.getStatus(6);
	}

	public String getJuly() {
		return this.getStatus(7);
	}

	public String getAugust() {
		return this.getStatus(8);
	}

	public String getSeptember() {
		return this.getStatus(9);
	}

	public String getOctober() {
		return this.getStatus(10);
	}

	public String getNovember() {
		return this.getStatus(11);
	}

	public String getDecember() {
		return this.getStatus(12);
	}

}