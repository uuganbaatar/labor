package mn.odi.labor.pages.job;

import java.util.List;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.entities.labor.FundingSource;
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
	private Form ajaxForm;

	@Property
	private List<Job> jobList;

	@Property
	private Job jobRow;

	@Property
	@Persist
	private Job job;

	@Property
	@Persist
	private JobTypeEnum jobType;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@CommitAfter
	void beginRender() {
		if (job == null)
			job = new Job();

		loginState.setActiveMenu("job");
		loginState.setPageTitle(message.get("job"));
		jobList = dao.getJobList();
	}
	
	public SelectModel getGeneralTypeModel() {
		return new CommonSM<GeneralType>(GeneralType.class, dao.getGeneralTypeList(),
				"getName");
	}
	
	public SelectModel getFundingSourceModel() {
		return new CommonSM<FundingSource>(FundingSource.class, dao.getFundingSourceList(),
				"getName");
	}
	
	public SelectModel getJobTypeModel() {
		return new CommonSM<GeneralType>(GeneralType.class, dao.getGeneralTypeList(),
				"getName");
	}

	void onActionFromJobEdit(Job job) {
		this.job = job;
		ajaxResponseRenderer.addRender(jobFormZone);
	}
	
	void onActionFromJobCancel() {
		job = new Job();
		ajaxResponseRenderer.addRender(jobFormZone);
	}

	Object onSubmit() {
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