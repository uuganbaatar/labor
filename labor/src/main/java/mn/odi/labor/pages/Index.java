package mn.odi.labor.pages;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.AccessLog;
import mn.odi.labor.util.Constants;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Response;

/*@Import(library = { "context:/js/indexcharts.js" })*/
public class Index {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private Request request;

	@Inject
	private RequestGlobals requestGlobals;

	@Inject
	private Response response;

	@Inject
	private Context context;

	@Inject
	private SccDAO dao;

	@Persist
	@Property
	private List<AccessLog> aList;

	@Persist
	@Property
	private AccessLog aRow;

	private int rowIndex;

	private int year, month;

	@Property
	private boolean jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec;
	
	@Inject
	private AlertManager alertManager;
	
	@Inject
	private Messages messages;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("hyanah");
		loginState.setPageTitle(message.get("dashboard"));

		
	
			AccessLog accessLog = new AccessLog();
			accessLog.setAccessDate(dao.getCurrentDate());
			accessLog.setUser(loginState.getUser());
			accessLog.setIpAddress(requestGlobals.getHTTPServletRequest()
					.getRemoteAddr());
			dao.saveOrUpdateObject(accessLog);
		
			
			aList = dao.getAccessLogs();

		rowIndex = 1;

		year = Calendar.getInstance().get(Calendar.YEAR);
		month = Calendar.getInstance().get(Calendar.MONTH)+1;
		if (month == 1) {
			jan = true;
		} else if (month == 2) {
			jan = true;
			feb = true;
		} else if (month == 3) {
			jan = true;
			feb = true;
			mar = true;
		} else if (month == 4) {
			jan = true;
			feb = true;
			mar = true;
			apr = true;
		} else if (month == 5) {
			jan = true;
			feb = true;
			mar = true;
			apr = true;
			may = true;
		} else if (month == 6) {
			jan = true;
			feb = true;
			mar = true;
			apr = true;
			may = true;
			jun = true;
		} else if (month == 7) {
			jan = true;
			feb = true;
			mar = true;
			apr = true;
			may = true;
			jun = true;
			jul = true;
		} else if (month == 8) {
			jan = true;
			feb = true;
			mar = true;
			apr = true;
			may = true;
			jun = true;
			jul = true;
			aug = true;
		} else if (month == 9) {
			jan = true;
			feb = true;
			mar = true;
			apr = true;
			may = true;
			jun = true;
			jul = true;
			aug = true;
			sep = true;
		} else if (month == 10) {
			jan = true;
			feb = true;
			mar = true;
			apr = true;
			may = true;
			jun = true;
			jul = true;
			aug = true;
			sep = true;
			oct = true;

		} else if (month == 11) {
			jan = true;
			feb = true;
			mar = true;
			apr = true;
			may = true;
			jun = true;
			jul = true;
			aug = true;
			sep = true;
			oct = true;
			nov = true;
		} else {
			jan = true;
			feb = true;
			mar = true;
			apr = true;
			may = true;
			jun = true;
			jul = true;
			aug = true;
			sep = true;
			oct = true;
			nov = true;
			dec = true;
		}
	}

	public int getRowIndex() {
		return rowIndex++;
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	public Integer getAllJobCount() {
		Integer l = dao.getAllJobs();
		if (l != null && l > 0) {
			return l;
		} else {
			return 0;
		}
	}

	public Integer getNewJobCount() {
		Integer l = dao.getNewJobs();
		if (l != null && l > 0) {
			return l;
		} else {
			return 0;
		}
	}

	public Integer getHasagdsanJobs() {
		Integer l = dao.getHasagdsanJobs();
		if (l != null && l > 0) {
			return l;
		} else {
			return 0;
		}
	}

	public Integer getAllEmployees() {
		Integer l = dao.getAllEmployees();
		if (l != null && l > 0) {
			return l;
		} else {
			return 0;
		}
	}
	
	public String getTest(){
		return "[1,2,3,4,5,6,7]";
	}
	

	public Object getAreaData() {
		JSONArray bar = new JSONArray();
		
		JSONArray counts = new JSONArray();
		bar.put(counts);
		List<BigDecimal> list = getInfoAddedByTypeNew();

		for (BigDecimal _values : list) {
			counts.put(_values);
		/*	String[] values = (String[]) _values;
			if (values[0] != null) {

				System.err.println(counts.put(values[0]));
				counts.put(values[0]);
			}*/
		}
		System.err.println(bar);
		
		
		String s = bar.toString();
		
		System.err.println("substr" + s.substring(1, 7));
		

Object b = s.substring(1, 9);

System.err.println("obj=" + b);

		return bar;
	}

	public String getUsername() {
		String name = "-";
		if (loginState != null && loginState.getUser() != null) {
			if (loginState.getUser().getLastname() != null) {
				name = loginState.getUser().getLastname();
			}
			if (loginState.getUser().getFirstname() != null) {
				name = name + "-" + loginState.getUser().getFirstname();
			}
		}
		return name;

	}

	public List<BigDecimal> getInfoAddedByTypeNew() {
		// if (loginState.getRole().getUserType() == UserTypeEnum.DOTOODAUDIT) {
		return dao.getInfoBar();
		/*
		 * } else { if (securityService.hasRole(StringUtil
		 * .getName(UserTypeEnum.ORGMANAGER))) { return
		 * dao.getInfoAddedByTypeNew(loginState.getUser().getOrg() .getId(),
		 * null); } else if (securityService.hasRole(StringUtil
		 * .getName(UserTypeEnum.USER))) { return
		 * dao.getInfoAddedByTypeNew(null, loginState.getUser() .getId()); }
		 * return dao.getInfoAddedByTypeNew(null, null); }
		 */

	}

	public Integer getTotalJan() {
		Integer i = dao.getTotalJan(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleJan() {
		Integer i = dao.getTotalFemaleJan(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFeb() {
		Integer i = dao.getTotalFeb(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleFeb() {
		Integer i = dao.getTotalFemaleFeb(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalMar() {
		Integer i = dao.getTotalMar(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleMar() {
		Integer i = dao.getTotalFemaleMar(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalApr() {
		Integer i = dao.getTotalApr(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleApr() {
		Integer i = dao.getTotalFemaleApr(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalMay() {
		Integer i = dao.getTotalMay(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleMay() {
		Integer i = dao.getTotalFemaleMay(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalJun() {
		Integer i = dao.getTotalJun(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleJun() {
		Integer i = dao.getTotalFemaleJun(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalJul() {
		Integer i = dao.getTotalJul(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleJul() {
		Integer i = dao.getTotalFemaleJul(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalAug() {
		Integer i = dao.getTotalAug(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleAug() {
		Integer i = dao.getTotalFemaleAug(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalSep() {
		Integer i = dao.getTotalSep(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleSep() {
		Integer i = dao.getTotalFemaleSep(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalNov() {
		Integer i = dao.getTotalNov(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleNov() {
		Integer i = dao.getTotalFemaleNov(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalDec() {
		Integer i = dao.getTotalDec(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleDec() {
		Integer i = dao.getTotalFemaleDec(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalOct() {
		Integer i = dao.getTotalJan(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleOct() {
		Integer i = dao.getTotalFemaleJan(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	
	public Integer getTotalImpJan() {
		Integer i = dao.getTotalImpJan(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	
	public Integer getTotalImpFeb() {
		Integer i = dao.getTotalImpFeb(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	
	public Integer getTotalImpMar() {
		Integer i = dao.getTotalImpMar(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	
	public Integer getTotalImpApr() {
		Integer i = dao.getTotalImpApr(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	public Integer getTotalImpMay() {
		Integer i = dao.getTotalImpMay(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	public Integer getTotalImpJun() {
		Integer i = dao.getTotalImpJun(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	
	public Integer getTotalImpJul() {
		Integer i = dao.getTotalImpJul(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	public Integer getTotalImpAug() {
		Integer i = dao.getTotalImpAug(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	
	public Integer getTotalImpSep() {
		Integer i = dao.getTotalImpSep(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	
	public Integer getTotalImpOct() {
		Integer i = dao.getTotalImpOct(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	public Integer getTotalImpNov() {
		Integer i = dao.getTotalImpNov(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	
	public Integer getTotalImpDec() {
		Integer i = dao.getTotalImpDec(year);
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}
	
	public Format getTimeFormat() {
		return new SimpleDateFormat(Constants.TIME_FORMAT);
	}

}
