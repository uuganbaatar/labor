package mn.odi.labor.pages.labor;

import java.util.ArrayList;
import java.util.Arrays;
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
import mn.odi.labor.enums.AimagNiislelEnum;

public class HudulmurReportPage {

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

	private int number;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("heltes");
		loginState.setPageTitle(message.get("heltes"));
		if (list == null) {
			list = new ArrayList<AimagNiislelEnum>(Arrays.asList(AimagNiislelEnum.values()));
		}
	}

	public int getAllJobs() {
		int i = 0;
		i = dao.getAllJobsSum(row);
		return i;
	}

}