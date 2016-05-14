package mn.odi.labor.services;

import java.io.IOException;

import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.dao.hibernate.SccDAOHibernate;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.hibernate.HibernateTransactionAdvisor;
import org.apache.tapestry5.hibernate.HibernateTransactionDecorator;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.validator.ValidatorMacro;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;
import org.slf4j.Logger;

import se.unbound.tapestry.breadcrumbs.BreadCrumbSymbols;

public class AppModule {

	public static void bind(ServiceBinder binder) {

		binder.bind(SccDAO.class, SccDAOHibernate.class);

	}

	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {

		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "mn");

		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");

		configuration.add(SymbolConstants.APPLICATION_VERSION, "0.0.1-SNAPSHOT");

		configuration.add(SymbolConstants.HMAC_PASSPHRASE, "ANY_STRING_12345");
		configuration.add("acegi.loginform.url", "/login");
		configuration.add("acegi.afterlogout.page", "/login");
		configuration.add("spring-security.check.url", "/j_spring_security_check");
		configuration.add(SymbolConstants.HOSTNAME, "localhost");
		configuration.add(SymbolConstants.HOSTPORT, "8082");

		configuration.add(JQuerySymbolConstants.JQUERY_ALIAS, "$");
		configuration.add(JQuerySymbolConstants.SUPPRESS_PROTOTYPE, "true");

		configuration.add(BreadCrumbSymbols.DISCARD_DUPLICATES, "true");
		configuration.add(BreadCrumbSymbols.MAX_CRUMBS_TO_SAVE, "10");

	}

	public RequestFilter buildTimingFilter(final Logger log) {
		return new RequestFilter() {
			public boolean service(Request request, Response response, RequestHandler handler) throws IOException {
				long startTime = System.currentTimeMillis();

				try {

					return handler.service(request, response);
				} finally {
					long elapsed = System.currentTimeMillis() - startTime;

					log.info(String.format("Request time: %d ms", elapsed));
				}
			}
		};
	}

	public void contributeRequestHandler(OrderedConfiguration<RequestFilter> configuration,
			@Local RequestFilter filter) {

		configuration.add("Timing", filter);
	}

	@Match("*DAO")
	public static <T> T decorateTransactionally(HibernateTransactionDecorator decorator, Class<T> serviceInterface,
			T delegate, String serviceId) {
		return decorator.build(serviceInterface, delegate, serviceId);
	}

	@Match("*DAO")
	public static void adviseTransactions(HibernateTransactionAdvisor advisor, MethodAdviceReceiver receiver) {
		advisor.addTransactionCommitAdvice(receiver);
	}

	@Contribute(ValidatorMacro.class)
	public static void combinePasswordValidators(MappedConfiguration<String, String> configuration) {
		configuration.add("date", "regexp=^(0[1-9]|[1-9]|1[0-2])/(0[1-9]|[1-9]|[1-2][0-9]|3[0-1])/(19|20)[0-9]{2}$");
	}

}