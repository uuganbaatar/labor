package mn.odi.labor.pages.job;

import java.util.List;

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
import mn.odi.labor.entities.admin.AjiliinBairHurungu;
import mn.odi.labor.entities.labor.Job;
import mn.odi.labor.entities.labor.ProfessionType;
import mn.odi.labor.enums.JobTypeEnum;

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
	@Persist
	private List<Job> jobList;

	@Property
	private Job jobRow;

	@Property
	@Persist
	private JobTypeEnum jobType;

	@Property
	@Persist
	private ProfessionType profType;

	@Property
	@Persist
	private AjiliinBairHurungu fundSrc;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("job");
		loginState.setPageTitle(message.get("job"));
		jobList = dao.getJobList();
	}

	void onActionFromJobEdit(Job job) {
		System.out.println("looool " + job.getJobName());
	}

	Object onSubmit() {
		dao.saveOrUpdateObject(jobRow);

		if (request.isXHR()) {
			return jobGridZone.getBody();
		} else {
			return this;
		}
	}
}