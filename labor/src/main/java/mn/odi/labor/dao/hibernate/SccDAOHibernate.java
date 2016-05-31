package mn.odi.labor.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.admin.AjiliinBairHurungu;
import mn.odi.labor.entities.admin.CompanyHelber;
import mn.odi.labor.entities.admin.CompanyStatus;
import mn.odi.labor.entities.admin.CompanyTrend;
import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.entities.admin.LavlahGarsan;
import mn.odi.labor.entities.common.BaseObject;
import mn.odi.labor.entities.common.User;
import mn.odi.labor.entities.labor.Employee;
import mn.odi.labor.entities.labor.Job;
import mn.odi.labor.entities.labor.Report;
import mn.odi.labor.entities.labor.ReportDetail;
import mn.odi.labor.entities.labor.ReportStatus;
import mn.odi.labor.enums.JobTypeEnum;
import mn.odi.labor.enums.ReportDetailType;

public class SccDAOHibernate implements SccDAO {
	private Session session;

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
	}

	@CommitAfter
	public void updateObject(Object obj) {
		if (obj != null)
			session.saveOrUpdate(obj);
	}

	@CommitAfter
	public void saveOrUpdateObject(Object obj) {
		try {
			session.saveOrUpdate(obj);
		} catch (Exception e) {
			session.merge(obj);
		}
	}

	public void mergeObject(Object obj) {
		session.merge(obj);
	}

	public void deleteObject(Object obj) {
		session.delete(obj);
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

	public ReportStatus getReportStatusList(Report report, Integer year, Integer month) {
		try {
			Criteria crit = session.createCriteria(ReportStatus.class);

			if (report != null)
				crit.add(Restrictions.eq("reportId", report));

			if (year != null)
				crit.add(Restrictions.eq("year", year));

			if (month != null)
				crit.add(Restrictions.eq("month", month));

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
}
