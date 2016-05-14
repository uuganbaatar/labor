package mn.odi.labor.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.BaseObject;
import mn.odi.labor.entities.common.User;

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

}
