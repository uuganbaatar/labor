/* 
 * Package Name: mn.odi.scc.pages
 * File Name: ExceptionPage.java
 * Description: Ð¯Ð¼Ð°Ñ€ Ñ‡ Ñ…ÑƒÑƒÐ´Ð°Ñ� Ð´Ñ�Ñ�Ñ€ Ð°Ð»Ð´Ð°Ð° Ð³Ð°Ñ€Ð²Ð°Ð» Ñ�Ð½Ñ� Ñ…ÑƒÑƒÐ´Ð°Ñ� Ð´ÑƒÑƒÐ´Ð°Ð³Ð´Ð°Ð¶ Ñ…Ð°Ñ€Ð°Ð³Ð´Ð°Ð½Ð°. 
 * 				Ð�Ð½Ñ…Ð°Ð°Ñ€: Ñ…ÑƒÑƒÐ´Ð°Ñ�Ð½Ñ‹ Ð½Ñ�Ñ€Ð¸Ð¹Ð³ ExceptionReport-Ð¾Ð¾Ñ� Ó©Ó©Ñ€ Ð½Ñ�Ñ€ Ó©Ð³Ñ‡ Ð±Ð¾Ð»Ð¾Ñ…Ð³Ò¯Ð¹!!!
 * 
 * Created By: Tserentogtokh.D
 * Created Date: 2014/04/14
 * 
 * History
 * ------------------------------------------------------------------------------
 * Date							Programmer						Description
 * ------------------------------------------------------------------------------
 * 2014/04/14 1.0.0 			Tserentogtokh.D				    Ð¨Ð¸Ð½Ñ�Ñ�Ñ€ Ò¯Ò¯Ñ�Ð³Ñ�Ð².
 * ------------------------------------------------------------------------------
 * 
 * ALL RIGHTS RESERVED COPYRIGHT (C) 2014 Od Innovation CO.,LTD SOFTWARE DIVSION
*/
package mn.odi.labor.pages;

import org.apache.tapestry5.services.ExceptionReporter;
import org.slf4j.Logger;

public class ExceptionReportXXX implements ExceptionReporter {

	private Throwable exception;

	private Logger log;

	private void writeInfoLog() {
		log.info("Exception Report:");
		log.info(exception.toString());
	}

	public void reportException(Throwable exception) {
		this.exception = exception;

		writeInfoLog();
	}

	public String getExceptionMessage() {
		return exception.getMessage();
	}
}