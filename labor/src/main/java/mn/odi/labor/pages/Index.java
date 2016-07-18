package mn.odi.labor.pages;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.AccessLog;
import mn.odi.labor.entities.common.User;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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

	private int roleId;

	private Long orgId, sumId;

	@Inject
	private Messages messages;
	
	 @Inject
	 private Request _request;
	

	@CommitAfter
	void beginRender() {
		
		if (loginState.getUser() == null) {

			SecurityContext ctx = SecurityContextHolder.getContext();

			Authentication auth = ctx.getAuthentication();

			UserDetails userDetails = (UserDetails) (auth.getPrincipal());

			User user = (User) this.dao.getUserByUsername(userDetails
					.getUsername());

			if (user == null) {
				throw new UsernameNotFoundException(

				"User not found in database");
			}

			loginState.setUser(user);

			user.setLastAccessDate(Calendar.getInstance().getTime());

			this.dao.saveOrUpdate(user, false);

			loginState.setRoleNames(user.getRoleNames());
		}
		loginState.setActiveMenu("hyanah");
		loginState.setPageTitle(message.get("dashboard"));

		AccessLog accessLog = new AccessLog();
		accessLog.setAccessDate(dao.getCurrentDate());
		accessLog.setUser(loginState.getUser());
		
		accessLog.setIpAddress(requestGlobals.getHTTPServletRequest()
				.getRemoteAddr());
		
		System.err.println("remote host:" + request.getRemoteHost());

		dao.saveOrUpdate(accessLog, false);

		aList = dao.getAccessLogs();

		roleId = loginState.getUser().getCurrentrole().getVal();

		if (loginState.getUser().getOrg() != null) {
			orgId = loginState.getUser().getOrg().getId();
		}

		if (loginState.getUser().getOrg() != null
				&& loginState.getUser().getOrg().getSumId() != null) {
			sumId = loginState.getUser().getOrg().getSumId().getId();
		}

		rowIndex = 1;

		year = Calendar.getInstance().get(Calendar.YEAR);
		month = Calendar.getInstance().get(Calendar.MONTH) + 1;
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
		Integer l = 0;
		if (roleId == 0) {
			l = dao.getAllJobs(null, null);
		} else if (roleId == 1) {
			l = dao.getAllJobs(orgId, null);
		} else {
			l = dao.getAllJobs(null, sumId);
		}

		if (l != null && l > 0) {
			return l;
		} else {
			return 0;
		}
	}

	public Integer getNewJobCount() {
		Integer l = 0;
		if (roleId == 0) {
			l = dao.getNewJobs(null, null);
		} else if (roleId == 1) {
			l = dao.getNewJobs(orgId, null);
		} else {
			l = dao.getNewJobs(null, sumId);
		}
		if (l != null && l > 0) {
			return l;
		} else {
			return 0;
		}
	}

	public Integer getHasagdsanJobs() {
		Integer l = 0;
		if (roleId == 0) {
			l = dao.getHasagdsanJobs(null, null);
		} else if (roleId == 1) {
			l = dao.getHasagdsanJobs(orgId, null);
		} else {
			l = dao.getHasagdsanJobs(null, sumId);
		}
		if (l != null && l > 0) {
			return l;
		} else {
			return 0;
		}
	}

	public Integer getAllEmployees() {
		Integer l = 0;
		if (roleId == 0) {
			l = dao.getAllEmployees(null, null);
		} else if (roleId == 1) {
			l = dao.getAllEmployees(orgId, null);
		} else {
			l = dao.getAllEmployees(null, sumId);
		}
		if (l != null && l > 0) {
			return l;
		} else {
			return 0;
		}
	}

	public String getTest() {
		return "[1,2,3,4,5,6,7]";
	}

	public Object getAreaData() {
		JSONArray bar = new JSONArray();

		JSONArray counts = new JSONArray();
		bar.put(counts);
		List<BigDecimal> list = getInfoAddedByTypeNew();

		for (BigDecimal _values : list) {
			counts.put(_values);
			/*
			 * String[] values = (String[]) _values; if (values[0] != null) {
			 * 
			 * System.err.println(counts.put(values[0])); counts.put(values[0]);
			 * }
			 */
		}

		String s = bar.toString();

		Object b = s.substring(1, 9);

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
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalJan(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalJan(year, orgId, null);
		} else {
			i = dao.getTotalJan(year, null, sumId);
		}

		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleJan() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFemaleJan(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFemaleJan(year, orgId, null);
		} else {
			i = dao.getTotalFemaleJan(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFeb() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFeb(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFeb(year, orgId, null);
		} else {
			i = dao.getTotalFeb(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleFeb() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFemaleFeb(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFemaleFeb(year, orgId, null);
		} else {
			i = dao.getTotalFemaleFeb(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalMar() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalMar(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalMar(year, orgId, null);
		} else {
			i = dao.getTotalMar(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleMar() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFemaleMar(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFemaleMar(year, orgId, null);
		} else {
			i = dao.getTotalFemaleMar(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalApr() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalApr(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalApr(year, orgId, null);
		} else {
			i = dao.getTotalApr(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleApr() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFemaleApr(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFemaleApr(year, orgId, null);
		} else {
			i = dao.getTotalFemaleApr(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalMay() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalMay(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalMay(year, orgId, null);
		} else {
			i = dao.getTotalMay(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleMay() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFemaleMay(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFemaleMay(year, orgId, null);
		} else {
			i = dao.getTotalFemaleMay(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalJun() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalJun(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalJun(year, orgId, null);
		} else {
			i = dao.getTotalJun(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleJun() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFemaleJun(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFemaleJun(year, orgId, null);
		} else {
			i = dao.getTotalFemaleJun(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalJul() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalJul(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalJul(year, orgId, null);
		} else {
			i = dao.getTotalJul(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleJul() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFemaleJul(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFemaleJul(year, orgId, null);
		} else {
			i = dao.getTotalFemaleJul(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalAug() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalAug(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalAug(year, orgId, null);
		} else {
			i = dao.getTotalAug(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleAug() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFemaleAug(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFemaleAug(year, orgId, null);
		} else {
			i = dao.getTotalFemaleAug(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalSep() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalSep(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalSep(year, orgId, null);
		} else {
			i = dao.getTotalSep(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleSep() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFemaleSep(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFemaleSep(year, orgId, null);
		} else {
			i = dao.getTotalFemaleSep(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalNov() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalNov(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalNov(year, orgId, null);
		} else {
			i = dao.getTotalNov(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleNov() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFemaleNov(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFemaleNov(year, orgId, null);
		} else {
			i = dao.getTotalFemaleNov(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalDec() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalDec(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalDec(year, orgId, null);
		} else {
			i = dao.getTotalDec(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleDec() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFemaleDec(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFemaleDec(year, orgId, null);
		} else {
			i = dao.getTotalFemaleDec(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalOct() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalOct(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalOct(year, orgId, null);
		} else {
			i = dao.getTotalOct(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalFemaleOct() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalFemaleJan(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalFemaleJan(year, orgId, null);
		} else {
			i = dao.getTotalFemaleJan(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalImpJan() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalImpJan(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalImpJan(year, orgId, null);
		} else {
			i = dao.getTotalImpJan(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalImpFeb() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalImpFeb(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalImpFeb(year, orgId, null);
		} else {
			i = dao.getTotalImpFeb(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalImpMar() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalImpMar(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalImpMar(year, orgId, null);
		} else {
			i = dao.getTotalImpMar(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalImpApr() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalImpApr(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalImpApr(year, orgId, null);
		} else {
			i = dao.getTotalImpApr(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalImpMay() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalImpMay(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalImpMay(year, orgId, null);
		} else {
			i = dao.getTotalImpMay(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalImpJun() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalImpJun(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalImpJun(year, orgId, null);
		} else {
			i = dao.getTotalImpJun(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalImpJul() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalImpJul(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalImpJul(year, orgId, null);
		} else {
			i = dao.getTotalImpJul(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalImpAug() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalImpAug(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalImpAug(year, orgId, null);
		} else {
			i = dao.getTotalImpAug(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalImpSep() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalImpSep(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalImpSep(year, orgId, null);
		} else {
			i = dao.getTotalImpSep(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalImpOct() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalImpOct(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalImpOct(year, orgId, null);
		} else {
			i = dao.getTotalImpOct(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalImpNov() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalImpNov(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalImpNov(year, orgId, null);
		} else {
			i = dao.getTotalImpNov(year, null, sumId);
		}
		if (i != null && i > 0) {
			return i;
		} else {
			return 0;
		}
	}

	public Integer getTotalImpDec() {
		Integer i = 0;
		if (roleId == 0) {
			i = dao.getTotalImpDec(year, null, null);
		} else if (roleId == 1) {
			i = dao.getTotalImpDec(year, orgId, null);
		} else {
			i = dao.getTotalImpDec(year, null, sumId);
		}
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
