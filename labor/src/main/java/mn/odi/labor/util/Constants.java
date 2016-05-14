/* 
 * Package Name: mn.odi.scc.util
 * File Name: Constants.java
 * 
 * Created By: Tserentogtokh.D
 * Created Date: 2014/04/14
 * 
 * History
 * ------------------------------------------------------------------------------
 * Date							Programmer						Description
 * ------------------------------------------------------------------------------
 * 2014/04/14 1.0.0 			Tserentogtokh.D				    Шинээр үүсгэв.
 * ------------------------------------------------------------------------------
 * 
 * ALL RIGHTS RESERVED COPYRIGHT (C) 2014 Od Innovation CO.,LTD SOFTWARE DIVSION
 */
package mn.odi.labor.util;

/**
 * Constant values used throughout the application.
 */
public class Constants {
	// ~ Static fields/initializers
	// =============================================

	/**
	 * The name of the ResourceBundle used in this application
	 */
	public static final String BUNDLE_KEY = "ApplicationResources";

	/**
	 * File separator from System properties
	 */
	public static final String FILE_SEP = System.getProperty("file.separator");

	/**
	 * The name of the configuration hashmap stored in application scope.
	 */
	public static final String CONFIG = "appConfig";

	/**
	 * Session scope attribute that holds the locale set by the user. By setting
	 * this key to the same one that Struts uses, we get synchronization in
	 * Struts w/o having to do extra work or have two session-level variables.
	 */
	public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

	/**
	 * The request scope attribute under which an editable user form is stored
	 */
	public static final String USER_KEY = "userForm";

	/**
	 * The request scope attribute for indicating a newly-registered user
	 */
	public static final String REGISTERED = "registered";

	/**
	 * The name of the Administrator role, as specified in web.xml
	 */
	public static final String ADMIN_ROLE = "ROLE_ADMIN";

	/**
	 * The name of the User role, as specified in web.xml
	 */
	public static final String USER_ROLE = "ROLE_USER";

	/**
	 * The name of the user's role list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String USER_ROLES = "userRoles";

	/**
	 * The name of the available roles list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String AVAILABLE_ROLES = "availableRoles";

	/**
	 * The name of the Module.
	 */

	public static final String MODULE_USER_PROFILE = "userProfile";

	/**
	 * The name of the CSS Theme setting.
	 */
	public static final String CSS_THEME = "csstheme";

	/** The encryption algorithm key to be used for passwords */
	public static final String ENC_ALGORITHM = "SHA1";

	public static final Integer DEFAULT_FOUSER_VERSION = 1;

	public static final int FOUSER_PASSWORD_LENTH = 8;

	public static final String NUMBER_FORMAT = "##0";
	public static final String CURRENCY_FORMAT = "#,##0";
	public static final String PERCENT_FORMAT = "0.00%";
	public static final String FILE_PATH_DOCUMENT = "/documents/";
	public static final String DEFAULT_FULL_DAY_FORMAT = "yyyy-MM-dd";
	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd hh:mm:ss";

	public static final String IMAGETYPE = "png";
	public static final String TMP_IMAGE_PATH = "/images/tmp/";

	public static final Integer THOUSAND = 100;
	public static final Double D1 = 1.2;
	public static final Double D2 = 1.093;
	public static final Double D3 = 1.12;
	public static final Double D4 = 2.0;
	public static final Double D5 = 1.5;
	public static final Double D6 = 12.0;
	public static final Double D7 = 8.9;
	public static final Double D8 = 9.3;
	public static final Double D9 = 13.0;
	public static final Double D10 = 1.1;
	public static final Double D11 = 237.0;
	public static final Double D12 = 3.0;
	public static final Double D13 = 10.0;
	public static final Double D14 = 0.0058;
	public static final Double D15 = 50000.0;
	public static final Double D16 = 0.004;
	public static final Double D17 = 0.093;
	public static final Double D19 = 11.0;
	public static final Double D22 = 0.11;
	public static final Double D23 = 0.12;
	

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final Integer TEN = 10;
	public static final Integer HUNDRED = 100;
	public static final Integer SEVENTH = 70000;
	public static final Integer COST = 1920000;
	public static final Integer SHI = 192000;
	public static final Integer TwoTHOUSEND = 2000;
	public static final Integer TWO = 2;
	public static final Integer sal_food = 112000;
	public static final Integer inv_food = 137106;
	public static final Integer hour = 168;
	public static final Integer time = 160;
	public static final Integer D20 = 11;
	public static final Integer gli=18850;
	
}