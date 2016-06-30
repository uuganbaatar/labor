package mn.odi.labor.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.internal.OperationException;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.admin.AjiliinBairHurungu;
import mn.odi.labor.entities.admin.CompanyHelber;
import mn.odi.labor.entities.admin.CompanyStatus;
import mn.odi.labor.entities.admin.CompanyTrend;
import mn.odi.labor.entities.admin.EconomicCategory;
import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.entities.admin.LavlahGarsan;
import mn.odi.labor.entities.admin.PropertyType;
import mn.odi.labor.entities.common.AccessLog;
import mn.odi.labor.entities.common.BaseObject;
import mn.odi.labor.entities.common.Organization;
import mn.odi.labor.entities.common.SumDuureg;
import mn.odi.labor.entities.common.User;
import mn.odi.labor.entities.labor.Employee;
import mn.odi.labor.entities.labor.Job;
import mn.odi.labor.entities.labor.Report;
import mn.odi.labor.entities.labor.ReportDetail;
import mn.odi.labor.entities.labor.ReportStatus;
import mn.odi.labor.enums.AimagNiislelEnum;
import mn.odi.labor.enums.JobTypeEnum;
import mn.odi.labor.enums.OrgTypeEnum;
import mn.odi.labor.enums.ReportDetailType;

public class SccDAOHibernate implements SccDAO {

	private Session session;

	@Inject
	private AlertManager alertManager;

	@Inject
	private Messages messages;

	@SessionState
	private LoginState loginState;

	public SccDAOHibernate(Session session) {
		this.session = session;
	}

	public Date getCurrentDate() {
		return new Date();
	}

	private void updateDateAndActor(BaseObject obj) {
		if (obj != null) {
			if (obj.getCreatedDate() == null) {
				obj.setCreatedDate(getCurrentDate());
			}

			obj.setModifiedDate(getCurrentDate());

			if (obj.getCreatedBy() == null && loginState != null && loginState.getUser() != null) {
				obj.setCreatedBy(loginState.getUser());
			}

			if (loginState != null && loginState.getUser() != null) {
				obj.setModifiedBy(loginState.getUser());
			}
		}
	}

	@CommitAfter
	public void saveObject(BaseObject obj) {

		this.updateDateAndActor(obj);

		this.saveObject((Object) obj);
	}

	@CommitAfter
	public void updateObject(BaseObject obj) {
		this.updateDateAndActor(obj);

		this.updateObject((Object) obj);
	}

	@CommitAfter
	public void saveOrUpdateObject(BaseObject obj) {
		this.updateDateAndActor(obj);

		this.saveOrUpdateObject((Object) obj);
	}

	@CommitAfter
	public void saveObject(Object obj) {

		session.save(obj);
		alertManager.alert(Duration.TRANSIENT, Severity.SUCCESS, messages.get("success"));
	}

	@CommitAfter
	public void updateObject(Object obj) {
		if (obj != null) {
			session.saveOrUpdate(obj);
			alertManager.alert(Duration.TRANSIENT, Severity.SUCCESS, messages.get("success"));
		}
	}

	@CommitAfter
	public void saveOrUpdateObject(Object obj) {
		try {
			session.saveOrUpdate(obj);
			alertManager.alert(Duration.TRANSIENT, Severity.SUCCESS, messages.get("success"));
		} catch (Exception e) {
			session.merge(obj);
		}
	}

	public void mergeObject(Object obj) {
		session.merge(obj);
	}

	public void deleteObject(Object obj) {
		try {
			session.delete(obj);
		} catch (OperationException e) {
			System.out.println("[ERROR DELETE:]" + e);
		}
	}

	public void deleteBaseObject(BaseObject obj) {
		if (loginState != null && loginState.getUser() != null) {
			obj.setDeletedBy(loginState.getUser());
		}
		obj.setDeletedDate(getCurrentDate());

		session.saveOrUpdate(obj);
	}

	public Object getObject(Class<?> clss, Long id) {
		try {
			if (id == null || id < 1)
				return null;
			return session.get(clss, id);
		} catch (HibernateException he) {
			return null;
		}
	}

	public Object getObject(Class<?> clss, Long id, String fetchProperty) {
		Criteria criteria = session.createCriteria(clss);

		criteria.add(Restrictions.eq("id", id));

		criteria.setFetchMode(fetchProperty, FetchMode.JOIN);

		return criteria.uniqueResult();
	}

	public Object getObject(Class<?> clss, Long id, List<String> fetchProperties) {
		Criteria criteria = session.createCriteria(clss);

		criteria.add(Restrictions.eq("id", id));

		for (String fetchProperty : fetchProperties) {
			criteria.setFetchMode(fetchProperty, FetchMode.JOIN);
		}

		return criteria.uniqueResult();
	}

	/**
	 * @param username
	 *            - Hereglegchin nevtreh ner
	 * @return User
	 */
	public User getUserByUsername(String username) {
		try {
			Criteria criteria = session.createCriteria(User.class);

			if (username != null) {
				// Case insensitive search
				criteria.add(Restrictions.eq("username", username));
			}

			User user = (User) criteria.uniqueResult();

			return user;
		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	/**
	 * @param -
	 *            Systemiin hereglegchiin jagsaalt
	 * @return List<User>
	 */
	public List<User> getUserList() {
		try {
			Criteria crit = session.createCriteria(User.class);

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	/**
	 * @param -
	 *            Ajliin bairnii jagsaalt
	 * @return List<Job>
	 */
	public List<Job> getJobList() {
		try {
			Criteria crit = session.createCriteria(Job.class);
			crit.addOrder(Order.desc("jobName"));
			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	/**
	 * @param -
	 *            General type jagsaalt
	 * @return List<GeneralType>
	 */
	public List<GeneralType> getGeneralTypeList() {
		try {
			Criteria crit = session.createCriteria(GeneralType.class);

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	/**
	 * @param -
	 *            Property type jagsaalt
	 * @return List<GeneralType>
	 */
	public List<PropertyType> getPropertyTypeList() {
		try {
			Criteria crit = session.createCriteria(PropertyType.class);

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	/**
	 * @param -
	 *            EconomicCategory jagsaalt
	 * @return List<GeneralType>
	 */
	public List<EconomicCategory> getEconomicCategoryList() {
		try {
			Criteria crit = session.createCriteria(EconomicCategory.class);

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<CompanyTrend> getCompanyTrendList() {
		try {
			Criteria crit = session.createCriteria(CompanyTrend.class);

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<AjiliinBairHurungu> getLavlahHurunguList() {
		try {
			Criteria crit = session.createCriteria(AjiliinBairHurungu.class);
			crit.addOrder(Order.desc("name"));
			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<CompanyHelber> getLavlahHelberList() {
		try {
			Criteria crit = session.createCriteria(CompanyHelber.class);
			crit.addOrder(Order.desc("name"));
			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<CompanyStatus> getLavlahStatusList() {
		try {
			Criteria crit = session.createCriteria(CompanyStatus.class);
			crit.addOrder(Order.desc("name"));
			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<LavlahGarsan> getLavlahEmpGarsanList() {
		try {
			Criteria crit = session.createCriteria(LavlahGarsan.class);
			crit.addOrder(Order.desc("name"));
			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	/**
	 * @param -
	 *            Ajiltnii jagsaalt
	 * @return List<Employee>
	 */
	public List<Employee> getEmpList() {
		try {
			Criteria crit = session.createCriteria(Employee.class);
			crit.addOrder(Order.desc("empName"));

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public List<AjiliinBairHurungu> getFundingSourceList() {
		try {
			Criteria crit = session.createCriteria(AjiliinBairHurungu.class);

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public List<Organization> getOrgList() {
		try {
			Criteria crit = session.createCriteria(Organization.class);

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public List<Employee> getEmpFilter(Employee emp) {

		try {
			String sql = "SELECT employee.* FROM employee WHERE employee.deleted_by_id IS NULL";

			if (emp != null) {

				if (emp.getJob() != null) {
					sql += " AND employee.job_id = " + emp.getJob().getId();
				}

				if (emp.getCreatedDate() != null) {
					sql += " AND employee.created_date = " + emp.getCreatedDate();
				}

				if (emp.getPhone() != null) {
					sql += " AND employee.phone = " + emp.getPhone();
				}

				if (emp.getSurName() != null) {
					sql += " AND employee.surname LIKE " + emp.getSurName();
				}

				if (emp.getEmpName() != null) {
					sql += " AND employee.empname LIKE " + emp.getEmpName();
				}
			}

			sql += " ORDER BY employee.empname";

			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Employee.class);
			List<Employee> list = query.list();
			return list;

		} catch (HibernateException e) {
			return null;
		}
	}

	public List<Report> getReportList() {
		try {
			Criteria crit = session.createCriteria(Report.class);

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public ReportStatus getReportStatusList(Report report, Integer year, Integer month, Organization orgId) {
		try {
			Criteria crit = session.createCriteria(ReportStatus.class);

			if (report != null)
				crit.add(Restrictions.eq("reportId", report));

			if (year != null)
				crit.add(Restrictions.eq("year", year));

			if (month != null)
				crit.add(Restrictions.eq("month", month));

			if (orgId != null)
				crit.add(Restrictions.eq("orgId", orgId));

			if (crit.list() != null && !crit.list().isEmpty())
				return (ReportStatus) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public List<ReportDetail> getReportDetailList() {
		try {
			Criteria crit = session.createCriteria(ReportDetail.class);

			return crit.list();

		} catch (HibernateException e) {
			return null;
		}
	}

	public ReportDetail getReportDetailListWithParameter(GeneralType generalType, ReportDetailType detailType,
			JobTypeEnum jobType, Integer year, Integer month) {
		try {
			Criteria crit = session.createCriteria(ReportDetail.class);
			crit.createAlias("reportStatusId", "reportStatusId");

			if (generalType != null)
				crit.add(Restrictions.eq("generalType", generalType));

			if (detailType != null)
				crit.add(Restrictions.eq("detailType", detailType));

			if (jobType != null)
				crit.add(Restrictions.eq("jobType", jobType));

			if (year != null)
				crit.add(Restrictions.eq("reportStatusId.year", year));

			if (month != null)
				crit.add(Restrictions.eq("reportStatusId.month", month));

			if (crit.list() != null && !crit.list().isEmpty())
				return (ReportDetail) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public Long getReportDetailAddRemove(GeneralType generalType, ReportDetailType detailType, Integer year,
			Integer month) {
		try {
			Criteria crit = session.createCriteria(ReportDetail.class);
			crit.createAlias("reportStatusId", "reportStatusId");

			if (generalType != null)
				crit.add(Restrictions.eq("generalType", generalType));

			if (detailType != null)
				crit.add(Restrictions.eq("detailType", detailType));

			if (year != null)
				crit.add(Restrictions.eq("reportStatusId.year", year));

			if (month != null)
				crit.add(Restrictions.eq("reportStatusId.month", month));

			crit.setProjection(Projections.sum("value"));

			if (crit.list() != null && !crit.list().isEmpty())
				return (Long) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public Integer getAllJobs() {

		String sql = "SELECT COUNT(id) countJob FROM job";

		Query query = session.createSQLQuery(sql).addScalar("countJob", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getNewJobs() {

		String sql = "SELECT COUNT(id) countJob FROM job where isnew=1";

		Query query = session.createSQLQuery(sql).addScalar("countJob", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getAllEmployees() {

		String sql = "SELECT COUNT(id) countEmp FROM employee";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public boolean isJobExists(Job job) {

		Criteria crit = session.createCriteria(Job.class);

		crit.add(Restrictions.eq("jobName", job.getJobName()));
		crit.add(Restrictions.eq("generalType", job.getGeneralType()));

		if (job.getId() != null) {
			crit.add(Restrictions.ne("id", job.getId()));
		}

		if (crit.list() != null && crit.list().size() > 0) {
			alertManager.alert(Duration.SINGLE, Severity.WARN, messages.get("jobExist"));
			return true;
		}

		return false;
	}

	public List<AccessLog> getAccessLogs() {
		try {
			Criteria crit = session.createCriteria(AccessLog.class);

			crit.addOrder(Order.desc("createdDate")).setMaxResults(5);

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<User> getUserListSearch(String ln, String fn, String mail, Date d1, Date d2, Boolean b) {
		try {
			Criteria crit = session.createCriteria(User.class);

			if (ln != null)
				crit.add(Restrictions.ilike("lastname", "%" + ln + "%"));

			if (fn != null)
				crit.add(Restrictions.ilike("firstname", "%" + fn + "%"));

			if (mail != null)
				crit.add(Restrictions.ilike("email", "%" + mail + "%"));

			if (d1 != null && d2 != null) {
				crit.add(Restrictions.between("createdDate", d1, d2));
			}

			if (b != null)
				crit.add(Restrictions.eq("isActive", b));

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<Employee> getEmpListSearch(Organization org, Job job, String emp, String sur, String phone) {
		try {
			Criteria crit = session.createCriteria(Employee.class);
			crit.addOrder(Order.desc("empName"));

			if (emp != null)
				crit.add(Restrictions.ilike("empName", '%' + emp + '%'));

			if (sur != null)
				crit.add(Restrictions.ilike("surName", '%' + sur + '%'));

			if (phone != null)
				crit.add(Restrictions.ilike("phone", '%' + phone + '%'));

			if (org != null)
				crit.add(Restrictions.eq("org", org));

			if (job != null)
				crit.add(Restrictions.eq("job", job));

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public List<GeneralType> getGeneralTypeListSearch(String name, Date d1, Date d2, Boolean b) {
		try {
			Criteria crit = session.createCriteria(GeneralType.class);

			if (name != null)
				crit.add(Restrictions.ilike("name", "%" + name + "%"));

			if (d1 != null && d2 != null) {
				crit.add(Restrictions.between("createdDate", d1, d2));
			}

			if (b != null)
				crit.add(Restrictions.eq("isActive", b));

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<PropertyType> getPropertyTypeListSearch(String name, Date d1, Date d2, Boolean b) {
		try {
			Criteria crit = session.createCriteria(PropertyType.class);

			if (name != null)
				crit.add(Restrictions.ilike("name", "%" + name + "%"));

			if (d1 != null && d2 != null) {
				crit.add(Restrictions.between("createdDate", d1, d2));
			}

			if (b != null)
				crit.add(Restrictions.eq("isActive", b));

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<EconomicCategory> getEconomicCategoryListSearch(String name, Date d1, Date d2, Boolean b) {
		try {
			Criteria crit = session.createCriteria(EconomicCategory.class);

			if (name != null)
				crit.add(Restrictions.ilike("name", "%" + name + "%"));

			if (d1 != null && d2 != null) {
				crit.add(Restrictions.between("createdDate", d1, d2));
			}

			if (b != null)
				crit.add(Restrictions.eq("isActive", b));

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<CompanyTrend> getCompanyTrendListSearch(String name, Date d1, Date d2, Boolean b) {
		try {
			Criteria crit = session.createCriteria(CompanyTrend.class);

			if (name != null) {
				crit.add(Restrictions.ilike("name", "%" + name + "%"));
			}

			if (d1 != null && d2 != null) {
				crit.add(Restrictions.between("createdDate", d1, d2));
			}

			if (b != null)
				crit.add(Restrictions.eq("isActive", b));

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<AjiliinBairHurungu> getLavlahHurunguListSearch(String name, Date d1, Date d2, Boolean b) {
		try {

			Criteria crit = session.createCriteria(AjiliinBairHurungu.class);

			if (name != null)
				crit.add(Restrictions.ilike("name", "%" + name + "%"));

			if (d1 != null && d2 != null) {
				crit.add(Restrictions.between("createdDate", d1, d2));
			}

			if (b != null)
				crit.add(Restrictions.eq("isActive", b));

			crit.addOrder(Order.desc("name"));

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<AccessLog> getAccessLogsSearch(String lname, String fname, Date d1, Date d2) {
		try {
			Criteria crit = session.createCriteria(AccessLog.class);

			crit.addOrder(Order.desc("createdDate"));

			if (lname != null) {
				System.err.println("1");
				List<User> ulist = this.getUserListByLastName(lname);
				if (ulist != null && ulist.size() > 0) {
					System.err.println("2");
					crit.add(Restrictions.in("user", ulist));
				} else {
					System.err.println("3");
					crit.add(Restrictions.eq("id", Long.valueOf("0")));
				}
			}

			if (fname != null) {
				List<User> ulist = this.getUserListByFirstName(fname);
				if (ulist != null && ulist.size() > 0) {
					crit.add(Restrictions.in("user", ulist));
				} else {
					crit.add(Restrictions.eq("id", Long.valueOf("0")));
				}
			}

			if (d1 != null && d2 != null) {
				crit.add(Restrictions.between("createdDate", d1, d2));
			}

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<User> getUserListByLastName(String name) {
		try {
			Criteria crit = session.createCriteria(User.class);

			if (name != null) {
				crit.add(Restrictions.ilike("lastname", "%" + name + "%"));
			}

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<User> getUserListByFirstName(String name) {
		try {
			Criteria crit = session.createCriteria(User.class);

			if (name != null) {
				crit.add(Restrictions.ilike("firstname", "%" + name + "%"));
			}

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<CompanyHelber> getLavlahHelberListSearch(String name, Date d1, Date d2, Boolean b) {
		try {
			Criteria crit = session.createCriteria(CompanyHelber.class);

			if (name != null)
				crit.add(Restrictions.ilike("name", "%" + name + "%"));

			if (d1 != null && d2 != null) {
				crit.add(Restrictions.between("createdDate", d1, d2));
			}

			if (b != null)
				crit.add(Restrictions.eq("isActive", b));

			crit.addOrder(Order.desc("name"));

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<CompanyStatus> getLavlahStatusListSearch(String name, Date d1, Date d2, Boolean b) {
		try {
			Criteria crit = session.createCriteria(CompanyStatus.class);
			if (name != null)
				crit.add(Restrictions.ilike("name", "%" + name + "%"));

			if (d1 != null && d2 != null) {
				crit.add(Restrictions.between("createdDate", d1, d2));
			}

			if (b != null)
				crit.add(Restrictions.eq("isActive", b));

			crit.addOrder(Order.desc("name"));

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<LavlahGarsan> getLavlahEmpGarsanListSearch(String name, Date d1, Date d2, Boolean b) {
		try {
			Criteria crit = session.createCriteria(LavlahGarsan.class);

			if (name != null)
				crit.add(Restrictions.ilike("name", "%" + name + "%"));

			if (d1 != null && d2 != null) {
				crit.add(Restrictions.between("createdDate", d1, d2));
			}

			if (b != null)
				crit.add(Restrictions.eq("isActive", b));

			crit.addOrder(Order.desc("name"));

			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<Organization> getOrgListSearch(String name, Date d1, Date d2, Boolean b) {
		try {
			Criteria crit = session.createCriteria(Organization.class);

			if (name != null)
				crit.add(Restrictions.ilike("name", "%" + name + "%"));

			if (d1 != null && d2 != null) {
				crit.add(Restrictions.between("createdDate", d1, d2));
			}

			if (b != null)
				crit.add(Restrictions.eq("isActive", b));
			if (crit.list().size() > 0)
				return crit.list();
			else
				return null;

		} catch (HibernateException e) {
			// Critical errors : database unreachable, etc.
			return null;
		}
	}

	public List<Object> getInfoBar() {
		// int year = Calendar.getInstance().get(Calendar.YEAR);
		try {
			String sql = "select gt.name,count(job.id) from job left join general_type gt on gt.id=job.generaltype_id group by gt.name";
			// sql += " group by fg.name";
			SQLQuery query = session.createSQLQuery(sql);
			// query.setParameter("tYear", year);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return (new ArrayList<Object>());
		}
	}

	public GeneralType getGeneralTypeByName(String name) {
		try {
			Criteria crit = session.createCriteria(GeneralType.class);

			if (name != null)
				crit.add(Restrictions.eq("name", name));

			if (crit.list() != null && !crit.list().isEmpty())
				return (GeneralType) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public PropertyType getPropertyTypeByName(String name) {
		try {
			Criteria crit = session.createCriteria(PropertyType.class);

			if (name != null)
				crit.add(Restrictions.eq("name", name));

			if (crit.list() != null && !crit.list().isEmpty())
				return (PropertyType) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public EconomicCategory getEconomicCategoryByName(String name) {
		try {
			Criteria crit = session.createCriteria(EconomicCategory.class);

			if (name != null)
				crit.add(Restrictions.eq("name", name));

			if (crit.list() != null && !crit.list().isEmpty())
				return (EconomicCategory) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public CompanyTrend getCompanyTrendByName(String name) {
		try {
			Criteria crit = session.createCriteria(CompanyTrend.class);

			if (name != null)
				crit.add(Restrictions.eq("name", name));

			if (crit.list() != null && !crit.list().isEmpty())
				return (CompanyTrend) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public AjiliinBairHurungu getHurunguByName(String name) {
		try {
			Criteria crit = session.createCriteria(AjiliinBairHurungu.class);

			if (name != null)
				crit.add(Restrictions.eq("name", name));

			if (crit.list() != null && !crit.list().isEmpty())
				return (AjiliinBairHurungu) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public CompanyHelber getHelberByName(String name) {
		try {
			Criteria crit = session.createCriteria(CompanyHelber.class);

			if (name != null)
				crit.add(Restrictions.eq("name", name));

			if (crit.list() != null && !crit.list().isEmpty())
				return (CompanyHelber) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public CompanyStatus getStatusByName(String name) {
		try {
			Criteria crit = session.createCriteria(CompanyStatus.class);

			if (name != null)
				crit.add(Restrictions.eq("name", name));

			if (crit.list() != null && !crit.list().isEmpty())
				return (CompanyStatus) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public LavlahGarsan getGarsanByName(String name) {
		try {
			Criteria crit = session.createCriteria(LavlahGarsan.class);

			if (name != null)
				crit.add(Restrictions.eq("name", name));

			crit.addOrder(Order.desc("name"));

			if (crit.list() != null && !crit.list().isEmpty())
				return (LavlahGarsan) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public Integer getTotalJan(int year) {

		String s = String.valueOf(year);

		String date1 = "01-JAN-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-FEB-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where created_date BETWEEN '" + date1 + "' and '" + date2
				+ "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalFemaleJan(int year) {

		String s = String.valueOf(year);

		String date1 = "01-JAN-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-FEB-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where gender='1' and created_date BETWEEN '" + date1
				+ "' and '" + date2 + "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public List<SumDuureg> getSumDuureg() {
		try {
			Criteria crit = session.createCriteria(SumDuureg.class);

			return crit.list();

		} catch (HibernateException e) {
			return null;
		}
	}

	public SumDuureg getSumDuuregByName(String name, AimagNiislelEnum aimagId) {
		try {
			Criteria crit = session.createCriteria(SumDuureg.class);

			if (name != null)
				crit.add(Restrictions.eq("name", name));

			if (aimagId != null)
				crit.add(Restrictions.eq("aimagId", aimagId));

			if (crit.list() != null && !crit.list().isEmpty())
				return (SumDuureg) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public Integer getTotalFeb(int year) {

		String s = String.valueOf(year);

		String date1 = "01-FEB-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-MAR-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where created_date BETWEEN '" + date1 + "' and '" + date2
				+ "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalFemaleFeb(int year) {

		String s = String.valueOf(year);

		String date1 = "01-FEB-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-MAR-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where gender='1' and created_date BETWEEN '" + date1
				+ "' and '" + date2 + "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalMar(int year) {

		String s = String.valueOf(year);

		String date1 = "01-MAR-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-APR-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where created_date BETWEEN '" + date1 + "' and '" + date2
				+ "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalFemaleMar(int year) {

		String s = String.valueOf(year);

		String date1 = "01-MAR-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-APR-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where gender='1' and created_date BETWEEN '" + date1
				+ "' and '" + date2 + "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalApr(int year) {

		String s = String.valueOf(year);

		String date1 = "01-APR-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-MAY-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where created_date BETWEEN '" + date1 + "' and '" + date2
				+ "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalFemaleApr(int year) {

		String s = String.valueOf(year);

		String date1 = "01-APR-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-MAY-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where gender='1' and created_date BETWEEN '" + date1
				+ "' and '" + date2 + "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalMay(int year) {

		String s = String.valueOf(year);

		String date1 = "01-MAY-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-JUN-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where created_date BETWEEN '" + date1 + "' and '" + date2
				+ "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalFemaleMay(int year) {

		String s = String.valueOf(year);

		String date1 = "01-MAY-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-JUN-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where gender='1' and created_date BETWEEN '" + date1
				+ "' and '" + date2 + "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalJun(int year) {

		String s = String.valueOf(year);

		String date1 = "01-JUN-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-JUL-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where created_date BETWEEN '" + date1 + "' and '" + date2
				+ "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalFemaleJun(int year) {

		String s = String.valueOf(year);

		String date1 = "01-JUN-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-JUL-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where gender='1' and created_date BETWEEN '" + date1
				+ "' and '" + date2 + "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalJul(int year) {

		String s = String.valueOf(year);

		String date1 = "01-JUL-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-AUG-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where created_date BETWEEN '" + date1 + "' and '" + date2
				+ "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalFemaleJul(int year) {

		String s = String.valueOf(year);

		String date1 = "01-JUL-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-AUG-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where gender='1' and created_date BETWEEN '" + date1
				+ "' and '" + date2 + "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalAug(int year) {

		String s = String.valueOf(year);

		String date1 = "01-AUG-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-SEP-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where created_date BETWEEN '" + date1 + "' and '" + date2
				+ "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalFemaleAug(int year) {

		String s = String.valueOf(year);

		String date1 = "01-AUG-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-SEP-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where gender='1' and created_date BETWEEN '" + date1
				+ "' and '" + date2 + "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalSep(int year) {

		String s = String.valueOf(year);

		String date1 = "01-SEP-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-OCT-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where created_date BETWEEN '" + date1 + "' and '" + date2
				+ "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalFemaleSep(int year) {

		String s = String.valueOf(year);

		String date1 = "01-SEP-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-OCT-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where gender='1' and created_date BETWEEN '" + date1
				+ "' and '" + date2 + "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalOct(int year) {

		String s = String.valueOf(year);

		String date1 = "01-OCT-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-NOV-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where created_date BETWEEN '" + date1 + "' and '" + date2
				+ "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalFemaleOct(int year) {

		String s = String.valueOf(year);

		String date1 = "01-OCT-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-NOV-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where gender='1' and created_date BETWEEN '" + date1
				+ "' and '" + date2 + "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalNov(int year) {

		String s = String.valueOf(year);

		String date1 = "01-NOV-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-DEC-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where created_date BETWEEN '" + date1 + "' and '" + date2
				+ "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalFemaleNov(int year) {

		String s = String.valueOf(year);

		String date1 = "01-NOV-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "01-DEC-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where gender='1' and created_date BETWEEN '" + date1
				+ "' and '" + date2 + "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalDec(int year) {

		String s = String.valueOf(year);

		String date1 = "01-DEC-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "31-DEC-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where created_date BETWEEN '" + date1 + "' and '" + date2
				+ "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public Integer getTotalFemaleDec(int year) {

		String s = String.valueOf(year);

		String date1 = "01-DEC-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String date2 = "31-DEC-" + s.substring(2, 4) + " 11.02.49.590000000 PM";

		String sql = "select count(id) countEmp from employee where gender='1' and created_date BETWEEN '" + date1
				+ "' and '" + date2 + "'";

		Query query = session.createSQLQuery(sql).addScalar("countEmp", IntegerType.INSTANCE);
		List<Integer> list = query.list();
		return list.get(0);
	}

	public List<SumDuureg> getSumDuuregSearch(String name, AimagNiislelEnum aimagId) {
		try {
			Criteria crit = session.createCriteria(SumDuureg.class);

			if (name != null)
				crit.add(Restrictions.eq("name", name));

			if (aimagId != null)
				crit.add(Restrictions.eq("aimagId", aimagId));

			return crit.list();

		} catch (HibernateException e) {
			return null;
		}

	}

	public Organization getOrgByNameDuureg(String name, SumDuureg sumDuureg) {
		try {
			Criteria crit = session.createCriteria(Organization.class);

			if (name != null)
				crit.add(Restrictions.eq("name", name));

			if (name != null)
				crit.add(Restrictions.eq("sumId", sumDuureg));

			crit.add(Restrictions.eq("orgType", OrgTypeEnum.HELTES));

			if (crit.list() != null && !crit.list().isEmpty())
				return (Organization) crit.list().get(0);
			else
				return null;

		} catch (HibernateException e) {
			return null;
		}
	}

	public List<Organization> getOrgListByAssoc() {
		try {
			String sql = "select org.id , org.name from organization org left join job_org_assoc ass on ass.org_id=org.id";
			SQLQuery query = session.createSQLQuery(sql).addScalar("id", LongType.INSTANCE).addScalar("name",
					StringType.INSTANCE);
			query.setResultTransformer(Transformers.aliasToBean(Organization.class));
			List<Organization> list = query.list();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return (new ArrayList<Organization>());
		}
	}
	
	public List<Job> getJobListByOrg(Organization org) {
		try {
			Criteria crit = session.createCriteria(Job.class);

			if (org != null)
				crit.add(Restrictions.eq("org", org));

			return crit.list();

		} catch (HibernateException e) {
			return null;
		}
	}

	public void saveOrUpdate(BaseObject object, boolean hasMessage) {
		// TODO Auto-generated method stub

	}
}
