/* 
 * Package Name: mn.itzone.project.util
 * File Name: Constants.java
 * 
 * Created By: Boldbaatar.B
 * Created Date: 2015.06.29
 * 
 * History 
 * ------------------------------------------------------------------------------
 * Date							Programmer						Description
 * ------------------------------------------------------------------------------
 * 2015/06/29 	1.0.0 			Boldbaatar.B					Created.
 * ------------------------------------------------------------------------------
 * 
 * ALL RIGHTS RESERVED COPYRIGHT (C) 2015 ITZONE LLC, SOFTWARE DEPARTMENT
 */
package mn.odi.labor.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Constants {

	/**
	 * Authentication Constants
	 * 
	 **/

	public static final String BUNDLE_KEY = "ApplicationResources";

	public static final String FILE_SEP = System.getProperty("file.separator");

	public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

	public static final String CONFIG = "appConfig";

	public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

	public static final String USER_KEY = "userForm";

	public static final String USER_LIST = "userList";

	public static final String REGISTERED = "registered";

	public static final Integer TWELFTH = Integer.valueOf(12);

	public static final Integer FOUR = Integer.valueOf(4);

	public static final Integer TWO = Integer.valueOf(2);

	/**
	 * Role Constants
	 * 
	 **/

	public static final String ADMIN_ROLE = "ROLE_ADMIN";

	public static final String USER_ROLE = "ROLE_USER";

	public static final String USER_ROLES = "userRoles";

	public static final String AVAILABLE_ROLES = "availableRoles";

	public static final String ENC_ALGORITHM = "SHA";

	public static final String TASK_ASSOC = "taskOrgAssoc";

	/**
	 * Format Constants
	 * 
	 **/

	public static final String NUMBER_FORMAT = "#,##0";

	public static final String PERCENT_FORMAT = "0.00%";

	public static final String CURRENCY_FORMAT = "[$$-409]#,##0;[RED]-[$$-409]#,##0";

	public static final String FILE_PATH_DOCUMENT = "/documents/";

	public static final String TMP_IMAGE_PATH = "/documents/";

	public static final int PASSWORD_RANDOM_LENGTH_MIN = 7;

	public static final int PASSWORD_RANDOM_LENGTH_MAX = 9;

	/**
	 * Date Format Constants
	 * 
	 **/

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String DATE_FORMAT_CODE = "yyyyMMdd";

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd H:mm:ss";

	public static final String DATE_TIME_FULL_FORMAT = "yyyy-MM-dd H:mm:ss.SSS";

	public static final String DATE_TIME_MIN_FORMAT = "yyyy-MM-dd H:mm";

	public static final String ORACLE_DATE_FORMAT = "yyyy-MM-dd";

	public static final String DEFAULT_DATE_FORMAT = "EEE MMM d HH:mm:ss zzz yyyy";

	public static final String YEAR_FORMAT = "yyyy";

	public static final String MONTH_FORMAT = "MM";

	public static final String DAY_FORMAT = "dd";

	public static final String GFMIS = "gfmis_transaction_info";

	public static final String GFMISMV = "GFMIS_MVIEW";

	public static final String GFMISTEMP4 = "GFMIS_4_MVIEW";

	public static final String GFMISTEMP2 = "GFMIS_2_MVIEW";

	public static final String GFMISTEMP20 = "GFMIS_20_MVIEW";

	public static final String GFMISTEMP20NEW = "GFMIS_20_MVIEW_NEW";

	public static final Integer Year = 2015;

	public static final String numberformat = "###,###.###";

	public static DateFormat formatter() {
		return new SimpleDateFormat(ORACLE_DATE_FORMAT);
	}

	public static Calendar now = Calendar.getInstance();

	public static int CURRENT_YEAR = now.get(Calendar.YEAR);
	public static int CURRENT_MONTH = now.get(Calendar.MONTH) + 1;
	public static int CURRENT_DAY = now.get(Calendar.DAY_OF_MONTH);
	public static int CURRENT_HOUR = now.get(Calendar.HOUR_OF_DAY);
	public static int CURRENT_MINUTE = now.get(Calendar.MINUTE);
	public static int CURRENT_SECOND = now.get(Calendar.SECOND);
	public static int CURRENT_MILLIS = now.get(Calendar.MILLISECOND);
	public static int HALF_YEAR_MONTH = Integer.valueOf(6);
	public static int YEAR_MONTH = Integer.valueOf(12);
	
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * FilType Constants
	 * 
	 **/
	public static String[] FILE_TYPE = { "jpg", "png", "tif" };

	public static String IMAGETYPE = "jpg";

	/**
	 * Grid Constants
	 */
	public static final int ROWS_PER_PAGE = 30;
	public static final String PAGER_POSITION = "bottom";
	public static final String TABLE_CSS_CLASS = "display dTable";

	public static final String WHITESPACE = " ";
	public static final String SLASH = "/";

	/**
	 * LIBRARY CONSTANTS
	 */
	public static final String[] FORM_LIBRARIES = { "context:js/plugins/handsontable/handsontable.js",
			"context:js/plugins/handsontable/dist/lib/lodash/lodash.js",
			"context:js/plugins/handsontable/dist/lib/underscore.string/underscore.string.js",
			"context:js/plugins/handsontable/dist/lib/moment/moment.js",
			"context:js/plugins/handsontable/dist/lib/numeral/numeral.js",
			"context:js/plugins/handsontable/dist/lib/numericjs/numeric.js",
			"context:js/plugins/handsontable/dist/lib/js-md5/md5.js",
			"context:js/plugins/handsontable/dist/lib/jstat/jstat.js",
			"context:js/plugins/handsontable/dist/lib/formulajs/formula.js",
			"context:js/plugins/handsontable/dist/js/parser.js", "context:js/plugins/handsontable/dist/js/ruleJS.js",
			"context:js/plugins/handsontable/handsontable.formula.js" };

	public static final String[] FORM_LAST_LIBRARIES = {
			"context:js/plugins/handsontable-master/dist/handontable.full.min.js" };

	public static final String[] FORM_STYLESHEETS = { "context:js/plugins/handsontable/handsontable.full.min.css",
			"context:js/plugins/handsontable/handsontable.formula.css" };

	public static final String[] FORM_LAST_STYLESHEETS = {
			"context:js/plugins/handsontable-master/dist/handsontable.full.min.css" };

}
