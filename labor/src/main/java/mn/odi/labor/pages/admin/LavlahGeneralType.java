package mn.odi.labor.pages.admin;

import java.util.List;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.InjectComponent;
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
import mn.odi.labor.entities.admin.GeneralType;

public class LavlahGeneralType {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Property
	@Persist
	private List<GeneralType> typeList;

	@Property
	private GeneralType typeRow;

	@Property
	private GeneralType type;

	@Property
	private String name;

	@InjectComponent
	private Zone listZone;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Inject
	private AlertManager alertManager;
	
	@InjectComponent
	private Grid grid;

	private int number;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("lavlah");
		loginState.setActiveDedMenu("lavlahgeneral");
		loginState.setPageTitle(message.get("lavlah"));
		typeList = dao.getGeneralTypeList();
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	@CommitAfter
	public void onSuccess() {
		type = new GeneralType();
		type.setName(name);
		dao.saveOrUpdateObject(type);
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone);
		}
		typeList = dao.getGeneralTypeList();
	}

	public Object onActionFromDeleteObject(GeneralType obj) {
		try {
			dao.deleteObject(obj);
		} catch (Exception e) {
			System.out.println("[ERROR DELETE:]" + e);
			alertManager.alert(Duration.TRANSIENT, Severity.ERROR, message.get("deleteerror"));
		}

		return LavlahGeneralType.class;
	}
	
	public int getNumber() {
		return (grid.getCurrentPage() - 1) * grid.getRowsPerPage() + ++number;
	}
}