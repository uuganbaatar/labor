package mn.odi.labor.pages.admin;

import java.util.List;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
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
import org.apache.tapestry5.util.EnumSelectModel;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.entities.common.SumDuureg;
import mn.odi.labor.enums.AimagNiislelEnum;

public class LavlahSumDuureg {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Property
	@Persist
	private List<SumDuureg> list;

	@Property
	private SumDuureg row;

	@Property
	private String name;

	@Property
	private AimagNiislelEnum aimag;

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

	@Persist
	@Property
	private Boolean active;

	@Persist
	@Property
	private String gname;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("lavlah");
		loginState.setActiveDedMenu("lavlahsum");
		loginState.setPageTitle(message.get("lavlah"));
		if (active == null) {
			active = true;
		}
		if (list == null)
			list = dao.getSumDuureg();
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	public static boolean containsWhiteSpace(final String testCode) {
		if (testCode != null) {
			for (int i = 0; i < 2; i++) {
				if (Character.isWhitespace(testCode.charAt(i))) {
					return true;
				}
			}
		}
		return false;
	}

	@CommitAfter
	public void onSuccessFromSave() {
		if (LavlahSumDuureg.containsWhiteSpace(name)) {
			alertManager.alert(Duration.TRANSIENT, Severity.ERROR,
					message.get("hoosonzai"));
		} else {
			if (dao.getSumDuuregByName(name, aimag) != null) {
				alertManager.alert(Duration.TRANSIENT, Severity.ERROR,
						message.get("burtgeltei"));
			} else {
				SumDuureg obj = new SumDuureg();
				obj.setName(name);
				obj.setIsActive(true);
				obj.setAimagId(aimag);
				dao.saveOrUpdateObject(obj);
			}
		}
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone);
		}
		list = dao.getSumDuureg();
	}

	public Object onActionFromDeleteObject(GeneralType obj) {
		try {
			dao.deleteObject(obj);
		} catch (Exception e) {
			System.out.println("[ERROR DELETE:]" + e);
			alertManager.alert(Duration.TRANSIENT, Severity.ERROR,
					message.get("deleteerror"));
		}

		return LavlahSumDuureg.class;
	}

	public int getNumber() {
		return (grid.getCurrentPage() - 1) * grid.getRowsPerPage() + ++number;
	}

	public SelectModel getAimagSelectModel() {
		EnumSelectModel em = new EnumSelectModel(AimagNiislelEnum.class,
				message);
		return em;
	}

	@CommitAfter
	public Object onActionFromEnable(SumDuureg type) {
		if (type.getIsActive() == true) {
			type.setIsActive(false);
		} else {
			type.setIsActive(true);
		}
		dao.saveOrUpdateObject(type);
		list = dao.getSumDuureg();
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone);
		}
		return LavlahSumDuureg.class;
	}

	Object onSuccessFromSearch() {
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone);
		}
		list = dao.getSumDuuregSearch(gname, null);
		return null;
	}

	@OnEvent(value = "cancel")
	void reset() {
	}
}