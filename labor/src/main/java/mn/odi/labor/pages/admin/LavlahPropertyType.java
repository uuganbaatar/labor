package mn.odi.labor.pages.admin;

import java.util.Date;
import java.util.List;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.admin.PropertyType;
import mn.odi.labor.enums.PropertyTypeEnum;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.util.EnumSelectModel;

public class LavlahPropertyType {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Property
	@Persist
	private List<PropertyType> typeList;

	@Property
	private PropertyType typeRow;

	@Property
	private PropertyType type;

	@Property
	private String name;

	@InjectComponent
	private Zone listZone;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Inject
	private ComponentResources resources;

	@Inject
	private AlertManager alertManager;

	@InjectComponent
	private Grid grid;

	private int number;

	@Persist
	@Property
	private Date d1;

	@Persist
	@Property
	private Date d2;

	@Persist
	@Property
	private Boolean active;

	@Persist
	@Property
	private String gname;

	@Property
	@Persist(PersistenceConstants.FLASH)
	@Validate("required")
	private PropertyTypeEnum propertyTypeEnum;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("lavlah");
		loginState.setActiveDedMenu("lavlahproperty");
		loginState.setPageTitle(message.get("lavlah"));
		typeList = dao.getPropertyTypeListSearch(gname, d1, d2, active);
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	public SelectModel getPropertyTypeModel() {
		return new EnumSelectModel(PropertyTypeEnum.class,
				resources.getMessages());
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

		if (LavlahPropertyType.containsWhiteSpace(name)) {
			alertManager.alert(Duration.TRANSIENT, Severity.ERROR,
					message.get("hoosonzai"));
		} else {
			if (dao.getPropertyTypeByName(name) != null) {
				alertManager.alert(Duration.TRANSIENT, Severity.ERROR,
						message.get("burtgeltei"));
			} else {
				type = new PropertyType();
				type.setName(name);
				type.setPropertyTypeEnum(propertyTypeEnum);
				dao.saveOrUpdateObject(type);
			}
		}

		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone);
		}
		typeList = dao.getPropertyTypeList();
	}

	public Object onActionFromDeleteObject(PropertyType obj) {
		try {
			dao.deleteObject(obj);
		} catch (Exception e) {
			System.out.println("[ERROR DELETE:]" + e);
			alertManager.alert(Duration.TRANSIENT, Severity.ERROR,
					message.get("deleteerror"));
		}

		return LavlahPropertyType.class;
	}

	public int getNumber() {
		return (grid.getCurrentPage() - 1) * grid.getRowsPerPage() + ++number;
	}

	@CommitAfter
	public Object onActionFromEnable(PropertyType type) {
		if (type.getIsActive() == true) {
			type.setIsActive(false);
		} else {
			type.setIsActive(true);
		}
		dao.saveOrUpdateObject(type);
		typeList = dao.getPropertyTypeListSearch(gname, d1, d2, active);
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone);
		}
		return LavlahPropertyType.class;
	}

	@CommitAfter
	Object onSuccessFromSearch() {
		return null;
	}

	@OnEvent(value = "cancel")
	void reset() {
		gname = null;
		d1 = null;
		d2 = null;
		active = null;
	}
}
