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
import mn.odi.labor.entities.common.Organization;
import mn.odi.labor.entities.common.SumDuureg;
import mn.odi.labor.enums.AimagNiislelEnum;
import mn.odi.labor.enums.OrgTypeEnum;
import mn.odi.labor.models.CommonSM;

public class LavlahHudulmurHeltes {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Property
	@Persist
	private List<Organization> list;

	@Property
	private Organization row;

	@Property
	private String name;

	@Property
	private String register;

	@Property
	private String telephone;

	@Property
	@Persist
	private AimagNiislelEnum aimag;

	@InjectComponent
	private Zone listZone, sumZone;

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

	@Persist
	@Property
	private SumDuureg sumDuureg;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("lavlah");
		loginState.setActiveDedMenu("lavlahorg");
		loginState.setPageTitle(message.get("lavlah"));
		if (active == null) {
			active = true;
		}
		if (list == null)
			list = dao.getOrgList();
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	@CommitAfter
	public void onSuccessFromSave() {
		Organization obj = null;
		if (dao.getOrgByNameDuureg(name, sumDuureg) != null) {
			obj = dao.getOrgByNameDuureg(name, sumDuureg);
		} else {
			obj = new Organization();
		}
		obj.setName(name);
		obj.setIsActive(true);
		obj.setSumId(sumDuureg);
		obj.setOrgType(OrgTypeEnum.HELTES);
		obj.setMobilePhone(telephone);
		obj.setRegNum(register);
		dao.saveOrUpdateObject(obj);
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone);
		}
		list = dao.getOrgList();
	}

	public Object onActionFromDeleteObject(GeneralType obj) {
		try {
			dao.deleteObject(obj);
		} catch (Exception e) {
			System.out.println("[ERROR DELETE:]" + e);
			alertManager.alert(Duration.TRANSIENT, Severity.ERROR,
					message.get("deleteerror"));
		}

		return LavlahHudulmurHeltes.class;
	}

	public int getNumber() {
		return (grid.getCurrentPage() - 1) * grid.getRowsPerPage() + ++number;
	}

	public SelectModel getAimagSelectModel() {
		EnumSelectModel em = new EnumSelectModel(AimagNiislelEnum.class,
				message);
		return em;
	}

	public SelectModel getSumSelectModel() {
		System.out.println("Aimag:" + aimag);
		CommonSM<SumDuureg> sm = new CommonSM<SumDuureg>(SumDuureg.class,
				dao.getSumDuuregSearch(null, aimag), "getName");
		return sm;
	}

	public void onValueChangedFromAimag() {
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(sumZone);
		}
	}

	@CommitAfter
	public Object onActionFromEnable(Organization type) {
		if (type.getIsActive() == true) {
			type.setIsActive(false);
		} else {
			type.setIsActive(true);
		}
		dao.saveOrUpdateObject(type);
		list = dao.getOrgList();
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone);
		}
		return LavlahHudulmurHeltes.class;
	}

	Object onSuccessFromSearch() {
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone);
		}
		list = dao.getOrgList();
		return null;
	}

	@OnEvent(value = "cancel")
	void reset() {
	}
}