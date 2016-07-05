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

			System.err.println("get user=" + loginState.getUser());

			roleId = loginState.getUser().getCurrentrole().getVal();

			if (loginState.getUser().getOrg() != null) {
				orgId = loginState.getUser().getOrg().getId();
			}

			if (loginState.getUser().getOrg() != null
					&& loginState.getUser().getOrg().getSumId() != null) {
				sumId = loginState.getUser().getOrg().getSumId().getId();
			}

			user.setLastAccessDate(Calendar.getInstance().getTime());

			this.dao.saveOrUpdateObject(user);

			loginState.setRoleNames(user.getRoleNames());
		}
		loginState.setActiveMenu("hyanah");
		loginState.setPageTitle(message.get("dashboard"));

		System.err.println(loginState.getUser());

		AccessLog accessLog = new AccessLog();
		accessLog.setAccessDate(dao.getCurrentDate());
		accessLog.setUser(loginState.getUser());
		accessLog.setIpAddress(requestGlobals.getHTTPServletRequest()
				.getRemoteAddr());
		dao.saveOrUpdateObject(accessLog);

		aList = dao.getAccessLogs();

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
		Integer i = dao.getTotalFemaleJan(year);
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
		Integer i = dao.getTotalFemaleFeb(year);
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
		Integer i = dao.getTotalFemaleMar(year);
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
		Integer i = dao.getTotalFemaleApr(year);
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
		Integer i = dao.getTotalFemaleMay(year);
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
		Integer i = dao.getTotalFemaleJun(year);
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
		Integer i = dao.getTotalFemaleJul(year);
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
		Integer i = dao.getTotalFemaleAug(year);
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
		Integer i = dao.getTotalFemaleSep(year);
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
		Integer i = dao.getTotalFemaleNov(year);
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
		Integer i = dao.getTotalFemaleDec(year);
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
