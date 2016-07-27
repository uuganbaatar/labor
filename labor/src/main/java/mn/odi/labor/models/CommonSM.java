package mn.odi.labor.models;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.util.AbstractSelectModel;

public class CommonSM<T> extends AbstractSelectModel implements Serializable {

	private static final long serialVersionUID = -8482329933943780056L;

	private final List<OptionModel> options = CollectionFactory.newList();

	private static final Logger log = Logger.getLogger("labor");

	private List<T> list = new ArrayList<T>();
	private Class clazz;
	private Method method;

	public CommonSM(Class<T> _clazz, List<T> _list, String titleField) {
		this.list = _list;
		this.clazz = _clazz;
		try {
			this.method = clazz.getMethod(titleField.trim());
			this.setModel();
		} catch (NoSuchMethodException nsme) {
			System.err.println(nsme.getMessage());
		}

		if (method == null
				|| method.getReturnType() == null
				|| !method.getReturnType().getCanonicalName()
						.equals("java.lang.String")) {
			throw new IllegalArgumentException("Wrong titleField argument");
		}
	}

	private void setModel() {
		if (list != null && this.list.size() > 0) {
			for (T type : list) {
				try {
					options.add(new OptionModelImpl((String) method
							.invoke(type), type));
				} catch (IllegalAccessException e) {
					log.error("context", e);
				} catch (IllegalArgumentException e) {
					log.error("context", e);
				} catch (InvocationTargetException e) {
					log.error("context", e);
				}
			}
		}
	}

	/**
	 * Returns null.
	 */
	public List<OptionGroupModel> getOptionGroups() {
		return null;
	}

	/**
	 * Returns the option groupos created in the constructor.
	 */
	public List<OptionModel> getOptions() {
		return options;
	}
}