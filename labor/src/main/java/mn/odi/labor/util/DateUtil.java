/* 
 * Package Name: mn.itzone.project.util
 * File Name: DateUtil.java
 * 
 * Created By: yondonsuren.n
 * Created Date: Jul 30, 2015
 * 
 * History 
 * ------------------------------------------------------------------------------
 * Date							Programmer						Description
 * ------------------------------------------------------------------------------
 * Jul 30, 2015 	1.0.0 			yondonsuren.n					Created.
 * ------------------------------------------------------------------------------
 * 
 * ALL RIGHTS RESERVED COPYRIGHT (C) 2015 ITZONE LLC, SOFTWARE DEPARTMENT
 */
package mn.odi.labor.util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			Constants.DATE_FORMAT);

	private static final Logger log = Logger.getLogger("shilendans");

	/**
	 * Tsag, minute, second ni 0 utgatai baih Calendar-iin shine instance-iig
	 * butsaana
	 * 
	 * @return
	 */
	public static Calendar getCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	/**
	 * Ugugdsun ognoonii hamgiin baga utgiig butsaana butsaana
	 * 
	 * @param date
	 *            Ognoo
	 * @return
	 */
	public static Date getMinDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Ugugdsun ognoonii hamgiin ih utgiig butsaana butsaana
	 * 
	 * @param date
	 *            Ognoo
	 * @return
	 */
	public static Date getMaxDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 99);
		return cal.getTime();
	}

	/**
	 * Ugugdsun ognoog default format-aar formatalna
	 * 
	 * @param date
	 *            Ognoo
	 * @return
	 */
	public static String format(Date date) {
		return sdf.format(date);
	}

	/**
	 * Ognoog ugugdsun format-iin daguu formatalna
	 * 
	 * @param date
	 *            Ognoo
	 * @param format
	 *            Ognoonii format
	 * @return
	 */
	public static String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * String-iig ugugdsun format-iin daguu date ruu hurvuulne
	 * 
	 * @param date
	 *            Ognoonii string
	 * @param format
	 *            format
	 * @return
	 */
	public static Date parse(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			log.error("context", e);
		}
		return null;
	}
	private static String[] arrWeekdays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
	"Saturday" };
public static final String DATA_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
public static final String DATA_FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
public static final String DATA_FORMAT_AMERICAN = "MMM dd, yyyy hh:mm:ss a";

public static int getDatePart(Date dateValue, String interval) {
Calendar c = Calendar.getInstance();
c.setTime(dateValue);

if (interval.equalsIgnoreCase("y"))
	return c.get(1);
if (interval.equals("M"))
	return c.get(2);
if (interval.equalsIgnoreCase("d"))
	return c.get(5);
if (interval.equalsIgnoreCase("dm"))
	return c.get(5);
if (interval.equalsIgnoreCase("dy"))
	return c.get(6);
if (interval.equals("h"))
	return c.get(10);
if (interval.equals("H"))
	return c.get(11);
if (interval.equals("m"))
	return c.get(12);
if (interval.equals("s"))
	return c.get(13);
if (interval.equals("S"))
	return c.get(14);
if (interval.equalsIgnoreCase("w"))
	return c.get(7);
if (interval.equalsIgnoreCase("wim"))
	return c.get(8);
if (interval.equalsIgnoreCase("wm"))
	return c.get(4);
if (interval.equalsIgnoreCase("wy")) {
	return c.get(3);
}
return -1;
}

public static int getDatePart(String strDateValue, String pattern, String interval) throws ParseException {
SimpleDateFormat sdfFormat = new SimpleDateFormat(pattern);
Date dateValue = sdfFormat.parse(strDateValue);
return getDatePart(dateValue, interval);
}

public static Date getDateAdd(Date dateValue, int intDiff, String interval) {
Calendar c = Calendar.getInstance();
c.setTime(dateValue);

if (interval.equalsIgnoreCase("y"))
	c.add(1, intDiff);
if (interval.equals("M"))
	c.add(2, intDiff);
if (interval.equalsIgnoreCase("d"))
	c.add(5, intDiff);
if (interval.equalsIgnoreCase("h"))
	c.add(10, intDiff);
if (interval.equals("m"))
	c.add(12, intDiff);
if (interval.equals("s"))
	c.add(13, intDiff);
if (interval.equals("S")) {
	c.add(14, intDiff);
}
return c.getTime();
}

public static String getLastMonth() {
return getLastMonth(new Date());
}

public static String getLastMonth(Date dateValue) {
Calendar c = Calendar.getInstance();

c.setTime(dateValue);
c.add(2, 0);

return new SimpleDateFormat("yyyyMM").format(c.getTime());
}

public static String getCurrentDate(String pattern) {
return getFormatDateTime(new Date(), pattern);
}

public static String getCurrentDate() {
return getCurrentDate("yyyy-MM-dd");
}

public static String getCurrentDateTime() {
return getCurrentDate("yyyy-MM-dd HH:mm:ss");
}

public static String getSpecifyDateTime(long value) {
return getFormatDateTime(new Date(value), "yyyy-MM-dd HH:mm:ss");
}

public static Date getDateTime(long value) {
Calendar c = Calendar.getInstance();
c.setTimeInMillis(value);
return c.getTime();
}

public static String getSpecifyDate(long value) {
return getFormatDateTime(new Date(value), "yyyy-MM-dd");
}

public static String getCurrentWeekDay() {
return arrWeekdays[(getDatePart(new Date(), "w") - 1)];
}

public static String getWeekDay(Date dateValue) {
return arrWeekdays[(getDatePart(dateValue, "w") - 1)];
}

public static String getWeekDay(String dateValue, String pattern) throws ParseException {
return arrWeekdays[(getDatePart(dateValue, pattern, "w") - 1)];
}

public static String getFormatDateTime(Date dateValue, String strFormat) {
return new SimpleDateFormat(strFormat).format(dateValue);
}

public static String getFormatDateTime(String strDateValue, String strFormat1, String strFormat2)
	throws ParseException {
return getFormatDateTime(new SimpleDateFormat(strFormat1).parse(strDateValue), strFormat2);
}

public static Date changeStringToDate(String strDate) throws ParseException {
if ((strDate == null) || (strDate.equals(""))) {
	return null;
}

return DateFormat.getDateInstance(2).parse(strDate);
}

public static int getDateDiff(Date date1, Date date2) {
Calendar c1 = Calendar.getInstance();
c1.setTime(date1);
Calendar c2 = Calendar.getInstance();
c2.setTime(date2);
Long lngDiff = Long.valueOf((c1.getTime().getTime() - c2.getTime().getTime()) / 86400000L);
return lngDiff.intValue();
}

public static int getDateDiff(Date date1, String date2, String pattern) throws ParseException {
SimpleDateFormat sdfFormat = new SimpleDateFormat(pattern);
return getDateDiff(date1, sdfFormat.parse(date2));
}

public static int getDateDiff(String date1, String date2, String pattern) throws ParseException {
SimpleDateFormat sdfFormat = new SimpleDateFormat(pattern);
return getDateDiff(sdfFormat.parse(date1), sdfFormat.parse(date2));
}

public static int getDateDiff(String date1, String pattern1, String date2, String pattern2) throws ParseException {
SimpleDateFormat sdfFormat1 = new SimpleDateFormat(pattern1);
SimpleDateFormat sdfFormat2 = new SimpleDateFormat(pattern2);
return getDateDiff(sdfFormat1.parse(date1), sdfFormat2.parse(date2));
}

public static String getDateAfter(long inter, String format) {
Date d1 = new Date();
d1.setTime(d1.getTime() + inter * 24L * 3600L * 1000L);
return getFormatDateTime(d1, format);
}

public static String getDateAfter(String date1, String date1format, long inter, String format)
	throws ParseException {
SimpleDateFormat sdfFormat = new SimpleDateFormat(date1format);
Date dateValue = sdfFormat.parse(date1);
dateValue.setTime(dateValue.getTime() + inter * 24L * 3600L * 1000L);
return getFormatDateTime(dateValue, format);
}

public static String getDateNoSpace(String data) {
if ((data == null) || (data.length() < 10))
	return "";
data = data.substring(0, 4) + data.substring(5, 7) + data.substring(8, 10);
return data;
}

public static Date getSpecifyHourOfDayByDate(int hour) {
Calendar c = Calendar.getInstance();
c.clear(13);
c.clear(12);
c.clear(14);
c.clear(11);
c.set(11, hour);
return c.getTime();
}

public static long changeDateStringToLong(String s_date) {
DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

long l = System.currentTimeMillis();
try {
	Date date = format.parse(s_date);
	Calendar c = Calendar.getInstance();
	c.setTime(date);
	l = c.getTimeInMillis();
} catch (Exception e) {
	e.printStackTrace();
}
return l;
}

public static Date getDateOfFormat(long l_date, String s_format) {
DateFormat format = new SimpleDateFormat(s_format);
Calendar c = Calendar.getInstance();
c.setTimeInMillis(l_date);
String dt = format.format(c.getTime());
Date date = null;
try {
	date = format.parse(dt);
} catch (Exception e) {
	e.printStackTrace();
}
return date;
}

public static Date getDateOfFormat(String s_date, String s_format) {
DateFormat format = new SimpleDateFormat(s_format);

Date date = null;
try {
	date = format.parse(s_date);
} catch (Exception e) {
	e.printStackTrace();
}
return date;
}

public static Date getDateOfFormat(Date d_date, String s_format) {
DateFormat format = new SimpleDateFormat(s_format);

String dt = format.format(d_date);
Date date = null;
try {
	date = format.parse(dt);
} catch (Exception e) {
	e.printStackTrace();
}
return date;
}

public static long getSpecifyHourOfDayByLong(int hour) {
Calendar c = Calendar.getInstance();
c.clear(13);
c.clear(12);
c.clear(14);
c.clear(11);
c.set(11, hour);
return c.getTimeInMillis();
}

public static int compareDatetime(String dt1, String dt2, String format) {
int result = -100;
DateFormat df = new SimpleDateFormat(format);
try {
	Date d1 = df.parse(dt1);
	Date d2 = df.parse(dt2);
	if (d1.equals(d2))
		result = 0;
	if (d1.after(d2))
		result = 1;
	if (d1.before(d2))
		result = -1;
} catch (ParseException localParseException) {
}
return result;
}

public static void main(String[] args) {
System.out.println(getDateOfFormat(System.currentTimeMillis(), "MMM dd,yyyy HH:mm:ss aaa"));
}

public static String GetCurrentDatetimeString() {
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
Date now = new Date(System.currentTimeMillis());
return formatter.format(now);
}

public static String convertCalendarToString(Calendar cal) {
if (cal != null) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	return formatter.format(cal.getTime());
}
return "";
}

public static String convertCalendarToString(Calendar cal, String dataFormat) {
if (cal != null) {
	SimpleDateFormat formatter = new SimpleDateFormat(dataFormat);
	return formatter.format(cal.getTime());
}
return "";
}

public static Calendar convertStringToCalendar(String dateString) {
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
Date date = null;
try {
	date = dateFormat.parse(dateString);
} catch (ParseException e) {
	return null;
}
Calendar cal = Calendar.getInstance();
cal.setTime(date);
return cal;
}

public static Calendar stringToCalendar(String dateString) {
Calendar cal = Calendar.getInstance();
try {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	Date d = df.parse(dateString);

	cal.setTime(d);
} catch (ParseException e) {
	e.printStackTrace();
}
return cal;
}

public static String convertStringEncoding(String str) {
try {
	return new String(str.getBytes("iso8859-1"), "UTF-8");
} catch (UnsupportedEncodingException e) {
	e.printStackTrace();
}
return "Error";
}
}
