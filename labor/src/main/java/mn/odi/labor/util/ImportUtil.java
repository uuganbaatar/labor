/* 
 * File Name: 		ImportUtil.java
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.ioc.Messages;

public class ImportUtil {

	private List<String> errMsgs;

	public ImportUtil() {
		errMsgs = new ArrayList<String>();
	}

	public List<String> getErrMsgs() {
		return errMsgs;
	}

	public void regErrMsg(String error, int row, int col) {
		errMsgs.add("ERROR: " + error + ", мөр: " + row + ",багана : " + col);
	}

	public void regErrMsg(String error, String obj, int row, int col) {
		errMsgs.add("ERROR: " + error + ", мөр: " + row + ",багана : " + col);
	}

	public void regErrMsg(String error, int row, int col, String obj, String dep) {
		errMsgs.add("ERROR: " + error + ", at row: " + row + ",at col : " + col
				+ ",Org:" + obj + ",Department:" + dep);
	}

	public Date getDateFromString(String str) {
		SimpleDateFormat sdf;

		if (str.contains("-")) {
			sdf = new SimpleDateFormat(Constants.DEFAULT_FULL_DAY_FORMAT);
		} else if (str.contains("/") && str.length() == 10) {
			sdf = new SimpleDateFormat("dd/MM/yyyy");
		} else if (str.contains("/")) {
			sdf = new SimpleDateFormat("d/M/yyyy");
		} else if (str.contains(".")) {
			sdf = new SimpleDateFormat("yyyy.MM.dd");
		} else if (str.contains("*")) {
			sdf = new SimpleDateFormat("yyyy*MM*dd");
		} else {
			sdf = new SimpleDateFormat("yyyyMMdd");
		}

		try {
			System.err.println(sdf.parse(str));
			return sdf.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	public Integer getInteger(Object obj) {
		double d = (Double) obj;
		int i = (int) d;
		return i;
	}

	public Double getDouble(Object obj) {
		double d = (Double) obj;
		return d;
	}

	public Long getLong(Object obj) {
		double d = (Double) obj;
		long i = (long) d;
		return i;
	}

	/**
	 * @param obj
	 * @param messages
	 * @return
	 */
	public boolean getBoolean(String obj, Messages messages) {
		if (obj == null)
			return false;

		if (obj.equals(messages.get("Yes")))
			return true;

		return false;
	}
}
