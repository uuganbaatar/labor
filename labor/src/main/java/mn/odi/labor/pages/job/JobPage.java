package mn.odi.labor.pages.job;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.util.EnumSelectModel;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.admin.AjiliinBairHurungu;
import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.entities.labor.Job;
import mn.odi.labor.enums.JobTypeEnum;
import mn.odi.labor.models.CommonSM;

public class JobPage {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@InjectComponent
	private Zone jobFormZone, jobGridZone;

	@InjectComponent
	private Form jobAddForm;

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
	@Persist(PersistenceConstants.FLASH)
	@Validate("required")
	private JobTypeEnum jobType;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Inject
	private ComponentResources resources;

	@Property
	private boolean newCheck;
	
	private int number;

	void onActivate() {
		if (job == null) {
			job = new Job();
		}

		loginState.setActiveMenu("job");
		loginState.setPageTitle(message.get("job"));
		jobList = dao.getJobList();
	}

	public SelectModel getGeneralTypeModel() {
		return new CommonSM<GeneralType>(GeneralType.class, dao.getGeneralTypeList(), "getName");
	}

	public SelectModel getFundingSourceModel() {
		return new CommonSM<AjiliinBairHurungu>(AjiliinBairHurungu.class, dao.getFundingSourceList(), "getName");
	}

	public SelectModel getJobTypeModel() {
		return new EnumSelectModel(JobTypeEnum.class, resources.getMessages());
	}

	void onActionFromJobEdit(Job job) {
		this.job = job;
		ajaxResponseRenderer.addRender(jobFormZone);
	}

	void onActionFromJobCancel() {
		job = new Job();
		ajaxResponseRenderer.addRender(jobFormZone);
	}
	
	public int getCount() {
		return jobList.size();
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

	@CommitAfter
	Object onSuccessFromJobAddForm() {

		if (newCheck) {
			job.setIsNew(true);
		} else {
			job.setIsNew(false);
		}

		dao.saveOrUpdateObject(job);
		job = new Job();
		jobList = dao.getJobList();
		
		if (request.isXHR()) {
			return jobGridZone.getBody();
		} else {
			return this;
		}
	}
}