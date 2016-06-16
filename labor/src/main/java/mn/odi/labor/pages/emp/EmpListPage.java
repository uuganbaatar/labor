package mn.odi.labor.pages.emp;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
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
import mn.odi.labor.entities.labor.Employee;
import mn.odi.labor.entities.labor.Job;
import mn.odi.labor.models.CommonSM;

public class EmpListPage {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@InjectComponent
	private Zone empFilterZone, empGridZone;
	
	@InjectComponent
	private Form empFilterForm;
	
	@InjectComponent
	private Grid grid;

	@Property
	@Persist
	private List<Employee> empList;

	@Property
	@Persist
	private Employee empRow;

	@Property
	@Persist
	private Employee emp;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Inject
	private ComponentResources resources;
	
	private int number;
	
	void beginRender() {
		
		loginState.setActiveMenu("emp");
		loginState.setPageTitle(message.get("employer"));
		empList = dao.getEmpFilter(emp);
		
		if (emp == null) {
			emp = new Employee();
		}
	}

	public SelectModel getJobModel() {
		return new CommonSM<Job>(Job.class, dao.getJobList(), "getJobName");
	}

	public SelectModel getOrgModel() {
		return new CommonSM<Organization>(Organization.class, dao.getOrgList(), "getName");
	}

	void onActionFromClearBtn() {
		emp = new Employee();
		ajaxResponseRenderer.addRender(empFilterZone);
	}
	
	public int getCount() {
		return empList.size();
	}
	
	public int getNumber() {
		return (grid.getCurrentPage() - 1) * grid.getRowsPerPage() + ++number;
	}

	@CommitAfter
	Object onActionFromEmpDelete(Employee emp) {
		dao.deleteObject(emp);
		emp = new Employee();
		empList = dao.getEmpList();
		
		if (request.isXHR()) {
			return empGridZone.getBody();
		} else {
			return this;
		}
	}

	@CommitAfter
	Object onSuccessFromEmpFilterForm() {
		emp = new Employee();
		
		if (request.isXHR()) {
			return empGridZone.getBody();
		} else {
			return this;
		}
	}
}