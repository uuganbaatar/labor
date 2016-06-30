package mn.odi.labor.pages.organization;

import java.util.Calendar;
import java.util.List;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.Organization;
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
	@Persist
	private Integer s_year;

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

	@Property
	private boolean isaction = false;

	@InjectPage
	private LaborReportInsert insertPage;

	@Property
	@Persist
	private Organization org;

	public void onActivate(Organization obj) {
		if (obj != null)
			org = obj;
		else {
			if (loginState.getUser() != null
					&& loginState.getUser().getOrg() != null)
				org = loginState.getUser().getOrg();
			else
				org = null;
		}

	}

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("report");
		loginState.setPageTitle(message.get("report"));
		list = dao.getReportList();

		if (month == null) {
			month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		}

		if (s_year == null) {
			year = Calendar.getInstance().get(Calendar.YEAR);
		} else {
			year = s_year;
		}

		System.err.println("jil:" + year);
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	public String getStatus(Integer m) {
		if (year <= Calendar.getInstance().get(Calendar.YEAR) && month >= m) {
			ReportStatus rt = dao.getReportStatusList(row, year, m, org);
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
			isaction = true;
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

	public Object onActionFromJanAction(Report r) {
		if (dao.getReportStatusList(r, year, 1, org) != null)
			insertPage.onActivate(dao.getReportStatusList(r, year, 1, org));
		else {
			ReportStatus t = new ReportStatus();
			t.setYear(year);
			t.setMonth(1);
			insertPage.onActivate(t);
		}
		return insertPage;
	}

	public Object onActionFromFebAction(Report r) {
		if (dao.getReportStatusList(r, year, 2, org) != null)
			insertPage.onActivate(dao.getReportStatusList(r, year, 2, org));
		else {
			ReportStatus t = new ReportStatus();
			t.setYear(year);
			t.setMonth(2);
			insertPage.onActivate(t);
		}
		return insertPage;
	}

	public Object onActionFromMarAction(Report r) {
		if (dao.getReportStatusList(r, year, 3, org) != null)
			insertPage.onActivate(dao.getReportStatusList(r, year, 3, org));
		else {
			ReportStatus t = new ReportStatus();
			t.setYear(year);
			t.setMonth(3);
			insertPage.onActivate(t);
		}
		return insertPage;
	}

	public Object onActionFromAprAction(Report r) {
		if (dao.getReportStatusList(r, year, 4, org) != null)
			insertPage.onActivate(dao.getReportStatusList(r, year, 4, org));
		else {
			ReportStatus t = new ReportStatus();
			t.setYear(year);
			t.setMonth(4);
			insertPage.onActivate(t);
		}
		return insertPage;
	}

	public Object onActionFromMayAction(Report r) {
		if (dao.getReportStatusList(r, year, 5, org) != null)
			insertPage.onActivate(dao.getReportStatusList(r, year, 5, org));
		else {
			ReportStatus t = new ReportStatus();
			t.setYear(year);
			t.setMonth(5);
			insertPage.onActivate(t);
		}
		return insertPage;
	}

	public Object onActionFromJunAction(Report r) {
		if (dao.getReportStatusList(r, year, 6, org) != null)
			insertPage.onActivate(dao.getReportStatusList(r, year, 6, org));
		else {
			ReportStatus t = new ReportStatus();
			t.setYear(year);
			t.setMonth(6);
			insertPage.onActivate(t);
		}
		return insertPage;
	}

	public Object onActionFromJulAction(Report r) {
		if (dao.getReportStatusList(r, year, 7, org) != null)
			insertPage.onActivate(dao.getReportStatusList(r, year, 7, org));
		else {
			ReportStatus t = new ReportStatus();
			t.setYear(year);
			t.setMonth(7);
			insertPage.onActivate(t);
		}
		return insertPage;
	}

	public Object onActionFromAugAction(Report r) {
		if (dao.getReportStatusList(r, year, 8, org) != null)
			insertPage.onActivate(dao.getReportStatusList(r, year, 8, org));
		else {
			ReportStatus t = new ReportStatus();
			t.setYear(year);
			t.setMonth(8);
			insertPage.onActivate(t);
		}
		return insertPage;
	}

	public Object onActionFromSepAction(Report r) {
		if (dao.getReportStatusList(r, year, 9, org) != null)
			insertPage.onActivate(dao.getReportStatusList(r, year, 9, org));
		else {
			ReportStatus t = new ReportStatus();
			t.setYear(year);
			t.setMonth(9);
			t.setOrgId(org);
			insertPage.onActivate(t);
		}
		return insertPage;
	}

	public Object onActionFromOctAction(Report r) {
		if (dao.getReportStatusList(r, year, 10, org) != null)
			insertPage.onActivate(dao.getReportStatusList(r, year, 10, org));
		else {
			ReportStatus t = new ReportStatus();
			t.setYear(year);
			t.setMonth(10);
			insertPage.onActivate(t);
		}
		return insertPage;
	}

	public Object onActionFromNovAction(Report r) {
		if (dao.getReportStatusList(r, year, 11, org) != null)
			insertPage.onActivate(dao.getReportStatusList(r, year, 11, org));
		else {
			ReportStatus t = new ReportStatus();
			t.setYear(year);
			t.setMonth(11);
			insertPage.onActivate(t);
		}
		return insertPage;
	}

	public Object onActionFromDecAction(Report r) {
		if (dao.getReportStatusList(r, year, 12, org) != null)
			insertPage.onActivate(dao.getReportStatusList(r, year, 12, org));
		else {
			ReportStatus t = new ReportStatus();
			t.setYear(year);
			t.setMonth(12);
			insertPage.onActivate(t);
		}
		return insertPage;
	}

	@CommitAfter
	Object onSuccessFromSearch() {
		return null;
	}

}