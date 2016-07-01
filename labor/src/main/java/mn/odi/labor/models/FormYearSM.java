package mn.odi.labor.models;

import java.util.List;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.util.AbstractSelectModel;

public class FormYearSM extends AbstractSelectModel {

	private final List<OptionModel> options = CollectionFactory.newList();

	public FormYearSM(List<Integer> data) {
		setLists(data);
	}

	public void setLists(List<Integer> list) {

		for (Integer value : list) {
			options.add(new OptionModelImpl(value.toString()));
		}

	}

	public List<OptionGroupModel> getOptionGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OptionModel> getOptions() {
		// TODO Auto-generated method stub
		return options;
	}

}
