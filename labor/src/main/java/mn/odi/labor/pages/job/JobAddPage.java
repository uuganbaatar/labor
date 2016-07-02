package mn.odi.labor.pages.job;

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
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.util.EnumSelectModel;

public class JobAddPage {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Inject
	private ComponentResources resources;

	@Persist
	@Property
	private boolean newCheck;

	@Persist
	@Property
	private Job jobs;

	@Persist
	@Property
	private boolean isActive;

	void onActivate(Job job) {
		if (job != null) {
			jobs = job;
		}

	}

	@CommitAfter
	void beginRender() {
		System.out.println("onBeginRender Success");

		loginState.setActiveMenu("job");
		loginState.setPageTitle(message.get("job"));

		loginState.setActiveMenu("news");

		if (jobs == null)
			jobs = new Job();

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

	public Object onActionFromJobCancel() {
		return JobPage.class;
	}

	@CommitAfter
	Object onSuccessFromJobAddForm() {

		if (newCheck) {
			jobs.setIsNew(true);
		} else {
			jobs.setIsNew(false);
		}

		if (isActive == true) {
			jobs.setIsActive(false);
		} else {
			jobs.setIsActive(true);
		}

		if (dao.isJobExists(jobs)) {
			return this;
		}

		dao.saveOrUpdateObject(jobs);
		return JobPage.class;
	}
}