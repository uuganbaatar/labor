package mn.odi.labor.pages.emp;

import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.labor.Employee;
import mn.odi.labor.enums.JobTypeEnum;

public class EmpListPage {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Property
	@Persist
	private List<Employee> empList;

	@Property
	private Employee empRow;

	@Property
	@Persist
	private JobTypeEnum jobType;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("emp");
		loginState.setPageTitle(message.get("employer"));
		empList = dao.getEmpList();
	}
}