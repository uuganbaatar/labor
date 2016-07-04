package mn.odi.labor.pages.labor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.enums.AimagNiislelEnum;
import mn.odi.labor.models.FormYearSM;
import mn.odi.labor.util.CalendarUtil;

public class HudulmurReportPage2 {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Inject
	private Request request;

	@Property
	@Persist
	private List<AimagNiislelEnum> list;

	@Property
	private AimagNiislelEnum row;

	@Property
	@Persist
	private List<GeneralType> headerEz;

	@Property
	private GeneralType valueEZ;

	private int number = 0;

	@Property
	@Persist
	private Integer year;

	@Property
	@Persist
	private Integer month;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("heltes");
		loginState.setPageTitle(message.get("heltes"));
		if (list == null) {
			list = new ArrayList<AimagNiislelEnum>(Arrays.asList(AimagNiislelEnum.values()));
		}

		if (headerEz == null)
			headerEz = dao.getGeneralTypeList();

		if (year == null) {
			year = Calendar.getInstance().get(Calendar.YEAR);
		}

		if (month == null) {
			month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		}

		System.out.println("FIRST DAY:" + CalendarUtil.getFirstDate(year, month));
		System.out.println("LAST DAY:" + CalendarUtil.getLastDate(year, month));
	}

	public int getAllJobs() {
		int i = 0;
		i = dao.getAllJobsSum(row, CalendarUtil.getFirstDate(year, month), CalendarUtil.getLastDate(year, month));
		return i;
	}

	public Integer getEzval() {
		int i = 0;
		i = dao.getRestJobsSum(row, valueEZ, CalendarUtil.getFirstDate(year, month),
				CalendarUtil.getLastDate(year, month));
		return i;
	}

	public Integer getHeadersize() {
		int i = 1;
		if (headerEz != null && !headerEz.isEmpty())
			i = headerEz.size();
		return i;
	}

	public int getNumber() {
		number = number + 1;
		return number;
	}

	public FormYearSM getFormDateModel() {
		Date d = dao.getCurrentDate();
		List<Integer> formYears = new ArrayList<Integer>();
		for (int i = d.getYear() + 1900; i >= 2015; i--) {
			formYears.add(i);

		}
		if (year == null) {
			year = formYears.get(0);
		}

		return new FormYearSM(formYears);
	}

}