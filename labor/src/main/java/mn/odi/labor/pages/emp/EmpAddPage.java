package mn.odi.labor.pages.emp;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.DiscardAfter;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.util.EnumSelectModel;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.admin.LavlahGarsan;
import mn.odi.labor.entities.common.Organization;
import mn.odi.labor.entities.labor.Employee;
import mn.odi.labor.entities.labor.Job;
import mn.odi.labor.enums.EduLevelEnum;
import mn.odi.labor.enums.EmpMovementEnum;
import mn.odi.labor.enums.EmploymentEnum;
import mn.odi.labor.enums.GenderEnum;
import mn.odi.labor.enums.YesNoEnum;
import mn.odi.labor.models.CommonSM;
import mn.odi.labor.models.JobSM;

public class EmpAddPage {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Property
	@PageActivationContext
	private Employee emp;

	@InjectComponent
	private Form empForm;

	@Inject
	private ComponentResources resources;

	@Property
	private boolean disabled;

	@Property
	private Organization organization;

	@Property
	private Job job;

	@InjectComponent
	private Zone orgZone;

	@Property
	private Organization orgSel;

	void onActivate(Employee emp) {

		if (emp != null) {
			this.emp = emp;
			job = emp.getJob();
		}

		disabled = false;
	}

	Employee onPassivate() {
		return emp;
	}

	void onPrepare() {
		if (emp == null) {
			emp = new Employee();
			job = emp.getJob();
		}
		disabled = false;
	}

	public SelectModel getGenderModel() {
		return new EnumSelectModel(GenderEnum.class, resources.getMessages());
	}

	public SelectModel getEduLevelModel() {
		return new EnumSelectModel(EduLevelEnum.class, resources.getMessages());
	}

	public SelectModel getEmploymentModel() {
		return new EnumSelectModel(EmploymentEnum.class, resources.getMessages());
	}

	public SelectModel getJobModel(Organization org) {
		return new CommonSM<Job>(Job.class, dao.getJobList(), "getJobName");
	}

	public SelectModel getOrgModel() {
		return new CommonSM<Organization>(Organization.class, dao.getOrgList(), "getName");
	}

	public SelectModel getJobModel() {
		JobSM sm = new JobSM(dao, orgSel);
		return sm;
	}

	public Object onValueChangedFromOrg(Organization org) {
		if (org != null) {
			orgSel = org;
			this.getJobModel();
		}
		return orgZone.getBody();
	}

	// public SelectModel getOrgModel() {
	//
	// OrgSM sm = new OrgSM(dao);
	//
	// return sm;
	// }

	public SelectModel getIsNewModel() {
		return new EnumSelectModel(YesNoEnum.class, resources.getMessages());
	}

	public SelectModel getMovementModel() {
		return new EnumSelectModel(EmpMovementEnum.class, resources.getMessages());
	}

	public SelectModel getFiredReasonModel() {
		return new CommonSM<LavlahGarsan>(LavlahGarsan.class, dao.getLavlahEmpGarsanList(), "getName");
	}

	@CommitAfter
	Object onSubmit() {
		
		emp.setJob(job);
		dao.saveOrUpdateObject(emp);
		return EmpListPage.class;
	}

	@DiscardAfter
	Object onCancel() {
		return EmpListPage.class;
	}

//	public Object onValueChangedFromOrg(Organization org) {
//		return orgZone.getBody();
//	}

}