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
import mn.odi.labor.entities.admin.AjiliinBairHurungu;
import mn.odi.labor.entities.admin.GeneralType;

public class LavlahHurungu {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Property
	@Persist
	private List<AjiliinBairHurungu> list;

	@Property
	private AjiliinBairHurungu row;

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
		loginState.setActiveDedMenu("lavlahhurungu");
		loginState.setPageTitle(message.get("lavlah"));
		list = dao.getLavlahHurunguList();
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	@CommitAfter
	public void onSuccess() {
		AjiliinBairHurungu obj = new AjiliinBairHurungu();
		obj.setName(name);
		dao.saveOrUpdateObject(obj);
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone);
		}
		list = dao.getLavlahHurunguList();
	}

	public Object onActionFromDeleteObject(GeneralType obj) {
		try {
			dao.deleteObject(obj);
		} catch (Exception e) {
			System.out.println("[ERROR DELETE:]" + e);
			alertManager.alert(Duration.TRANSIENT, Severity.ERROR, message.get("deleteerror"));
		}

		return LavlahEmpGarsan.class;
	}

	public int getNumber() {
		return (grid.getCurrentPage() - 1) * grid.getRowsPerPage() + ++number;
	}
}