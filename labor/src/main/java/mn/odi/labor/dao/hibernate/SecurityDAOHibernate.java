package mn.odi.labor.dao.hibernate;

import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import mn.odi.labor.entities.common.User;

public class SecurityDAOHibernate extends HibernateDaoSupport implements UserDetailsService {

	@Inject
	private Messages messages;

	public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
		User user = null;

		if (login != null) {
			Session session = getSessionFactory().getCurrentSession();

			Criteria crit = session.createCriteria(User.class);

			crit.add(Restrictions.eq("username", login));

			user = (User) crit.uniqueResult();

		}

		if (user == null) {
			logger.info("user with login: " + login + " not found in database");

			throw new UsernameNotFoundException("user not found in database");
		}

		logger.debug("Create User for acegi features for User with login: " + login);

		org.springframework.security.core.userdetails.User acegiUser = new org.springframework.security.core.userdetails.User(
				login, user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(),
				user.isAccountNonLocked(), user.getAuthorities());

		logger.info("user with login: " + login + " authenticated");
		logger.info(acegiUser.getAuthorities());
		logger.info(acegiUser.isCredentialsNonExpired());
		logger.info(acegiUser.isEnabled());
		return acegiUser;
	}

}