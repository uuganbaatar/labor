package mn.odi.labor.pages.job;

import java.util.Date;
import java.util.List;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.admin.AjiliinBairHurungu;
import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.entities.common.Organization;
import mn.odi.labor.entities.labor.Job;
import mn.odi.labor.enums.JobTypeEnum;
import mn.odi.labor.models.CommonSM;

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
import org.apache.tapestry5.util.EnumSelectModel;

public class JobPage {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@InjectComponent
	private Zone jobGridZone;

	@Property
	private List<Job> jobList;

	@Property
	@Persist
	private Job jobRow;

	@Property
	@Persist
	private Job job;

	@InjectComponent
	private Grid grid;

	@Property
	@Persist
	private JobTypeEnum jobType;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Inject
	private ComponentResources resources;

	@Persist
	@Property
	private boolean newCheck;

	@InjectPage
	private JobAddPage createPage;

	private int number;

	@Persist
	@Property
	private GeneralType generalType;

	@Persist
	@Property
	private String jobName;

	@Persist
	@Property
	private Date startDate, endDate;

	@Persist
	@Property
	private AjiliinBairHurungu fundingSource;

	@Persist
	@Property
	private Organization org;

	@CommitAfter
	void beginRender() {
		System.err.println(generalType);
		System.err.println(newCheck);
		System.err.println(startDate);

		loginState.setActiveMenu("job");
		loginState.setPageTitle(message.get("job"));
		jobList = dao.getJobSearch(generalType, jobName, newCheck, startDate,
				endDate, fundingSource, jobType, org);
	}

	public SelectModel getGeneralTypeModel() {
		return new CommonSM<GeneralType>(GeneralType.class,
				dao.getGeneralTypeList(), "getName");
	}

	public SelectModel getFundingSourceModel() {
		return new CommonSM<AjiliinBairHurungu>(AjiliinBairHurungu.class,
				dao.getFundingSourceList(), "getName");
	}

	public SelectModel getOrgModel() {
		return new CommonSM<Organization>(Organization.class, dao.getOrgList(),
				"getName");
	}

	public SelectModel getJobTypeModel() {
		return new EnumSelectModel(JobTypeEnum.class, resources.getMessages());
	}

	/*
	 * void onActionFromJobEdit(Job job) { this.job = job;
	 * ajaxResponseRenderer.addRender(jobFormZone); }
	 */

	public Object onActionFromJobEdit(Job j) {
		createPage.onActivate(j);
		return createPage;
	}

	@CommitAfter
	Object onSuccessFromSearch() {
		return null;
	}

	@OnEvent(value = "cancel")
	void reset() {
		
		startDate = null;
	}

	public int getCount() {
		if (jobList != null && jobList.size() > 0) {
			return jobList.size();
		} else {
			return 0;
		}
	}

	public int getNumber() {
		return (grid.getCurrentPage() - 1) * grid.getRowsPerPage() + ++number;
	}

	@CommitAfter
	Object onActionFromJobDelete(Job job) {
		dao.deleteObject(job);
		job = new Job();
		jobList = dao.getJobList();

		if (request.isXHR()) {
			return jobGridZone.getBody();
		} else {
			return this;
		}
	}

	/*
	 * @CommitAfter Object onSuccessFromJobAddForm() {
	 * 
	 * if (newCheck) { job.setIsNew(true); } else { job.setIsNew(false); }
	 * 
	 * if (dao.isJobExists(job)) { return this; }
	 * 
	 * dao.saveOrUpdateObject(job); job = new Job(); jobList = dao.getJobList();
	 * 
	 * if (request.isXHR()) { return jobGridZone.getBody(); } else { return
	 * this; } }
	 */

	public Object onActionFromJobCreate() {
		createPage.onActivate(null);
		return createPage;
	}
}