/* 
 * Package Name: mn.odi.scc.util
 * File Name: CalendarUtil.java
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

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
	// ~ Static fields/initializers
	// =============================================

	// private final static Log log = LogFactory.getLog(CalendarUtil.class);

	// ~ Methods
	// ================================================================

	public static Date getDateAdded(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		cal.add(field, amount);

		return cal.getTime();
	}

	public static Date getDateSetAmount(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		cal.set(field, amount);

		return cal.getTime();
	}

	/**
	 * Хоёр огнооны хоорондох хугацааг ? жил ? сар гэж харуулахад ашиглана Жил
	 * хэсгийг бодно
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	private static int getYearDiff(Date dateStart, Date dateEnd) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(dateStart);
		Calendar cal2 = Calendar.getInstance();

		int year = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);

		cal1.add(Calendar.YEAR, year);

		if (cal1.after(cal2)) {
			return year - 1;
		} else {
			return year;
		}
	}

	/**
	 * Хоёр огнооны хоорондох хугацааг ? жил ? сар гэж харуулахад ашиглана
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @return [жил, сар]
	 */
	public static int[] getYearMonthDiff(Date dateStart, Date dateEnd) {
		assert dateEnd.after(dateStart);

		int[] yearMonth = new int[2];

		yearMonth[0] = CalendarUtil.getYearDiff(dateStart, dateEnd);

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(dateStart);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(dateEnd);

		cal1.add(Calendar.YEAR, yearMonth[1]);

		// int month;
		if (cal1.get(Calendar.MONTH) < cal2.get(Calendar.MONTH)) {
			yearMonth[1] = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
		} else if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
			yearMonth[1] = 0;
		} else {
			yearMonth[1] = (12 - cal1.get(Calendar.MONTH) + cal2.get(Calendar.MONTH));
		}

		cal1.add(Calendar.MONTH, yearMonth[1]);

		if (cal1.after(cal2)) {
			yearMonth[1]--;
		}

		return yearMonth;
	}

	/**
	 * dateStart dateEnd өдрүүдийг харьцуулна.Цагийг нь тооцохгүй
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @returnthe value 0 if the time represented by the argument is equal to
	 *            the time represented by this Calendar; a value less than 0 if
	 *            the time of this Calendar is before the time represented by
	 *            the argument; and a value greater than 0 if the time of this
	 *            Calendar is after the time represented by the argument.
	 */
	public static int compare(Date dateStart, Date dateEnd) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(dateStart);

		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dateEnd);

		calStart.set(Calendar.HOUR_OF_DAY, 0);
		calStart.set(Calendar.MINUTE, 0);
		calStart.set(Calendar.SECOND, 0);
		calStart.set(Calendar.MILLISECOND, 0);

		calEnd.set(Calendar.HOUR_OF_DAY, 0);
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		calEnd.set(Calendar.MILLISECOND, 0);

		return calStart.compareTo(calEnd);
	}

	public static Date setZeroOffset(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static Date getNextDateOffset(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return cal.getTime();
	}

	public static Date getStartDateOfEpic() {
		Calendar cal = Calendar.getInstance();

		cal.setTimeInMillis(0l);

		return cal.getTime();
	}

	public static Date getFirstDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		month = month - 1;
		cal.set(year, month, 1);
		return cal.getTime();
	}

	public static Date getLastDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal1.set(year, month - 1, 1);
		cal.set(year, month - 1, cal1.getActualMaximum(cal1.DAY_OF_MONTH));
		return cal.getTime();
	}

}
