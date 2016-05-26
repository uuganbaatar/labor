package mn.odi.labor.pages.emp;

import org.apache.tapestry5.annotations.DiscardAfter;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.labor.Employee;

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
	
	void onActivate(Employee emp) {
		
		if(emp != null){
			this.emp = emp;
		}
	}
	
	Employee onPassivate(){
		return emp;
	}

	void onPrepare() {
		if (emp == null) {
			emp = new Employee();
		}
	}

	Object onSubmit() {

		dao.saveOrUpdateObject(emp);
		return EmpListPage.class;
	}


	@DiscardAfter
	Object onCancel() {
		return EmpListPage.class;
	}
}