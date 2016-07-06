package mn.odi.labor.pages.labor;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.Organization;
import mn.odi.labor.entities.labor.Job;
import mn.odi.labor.enums.RoleEnum;
import mn.odi.labor.models.CommonSM;
import mn.odi.labor.pages.organization.LaborReportOrgList;

public class OrgListPage {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@InjectComponent
	private Zone orgGridZone;

	@InjectComponent
	private Grid grid;

	@Property
	@Persist
	private List<Organization> orgList;

	@Property
	@Persist
	private Organization orgRow;

	@Property
	@Persist
	private Organization org;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Inject
	private ComponentResources resources;

	@InjectPage
	private LaborReportOrgList page;

	private int number;

	@Persist
	@Property
	private Date d1;

	@Persist
	@Property
	private Date d2;

	@Persist
	@Property
	private Boolean active;

	@Persist
	@Property
	private String gname;

	void beginRender() {

		loginState.setActiveMenu("org");
		loginState.setPageTitle(message.get("org-label"));

		if (active == null) {
			active = true;
		}
		if(loginState.getUser().getCurrentrole()==RoleEnum.ADMIN){
		orgList = dao.getOrgListSearch(gname, d1, d2, active);
		}else{
			orgList = dao.getOrgListSearchWithSum(gname, d1, d2, active,loginState.getUser().getOrg().getSumId());
		}

		if (org == null) {
			org = new Organization();
		}
	}

	public SelectModel getJobModel() {
		return new CommonSM<Job>(Job.class, dao.getJobList(), "getJobName");
	}

	public SelectModel getOrgModel() {
		return new CommonSM<Organization>(Organization.class, dao.getOrgList(),
				"getName");
	}

	public Object onActionFromReportAction(Organization o) {
		page.onActivate(o);
		return page;
	}

	public int getCount() {
		if (orgList != null && orgList.size() > 0) {
			return orgList.size();
		} else
			return 0;
	}

	public int getNumber() {
		return (grid.getCurrentPage() - 1) * grid.getRowsPerPage() + ++number;
	}

	@CommitAfter
	Object onSuccessFromSearch() {
		return null;
	}

	@OnEvent(value = "cancel")
	void reset() {
		gname = null;
		d1 = null;
		d2 = null;
		active = null;
	}

}