package mn.odi.labor.models;


import java.io.Serializable;
import java.util.List;

import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.Organization;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.util.AbstractSelectModel;

public class OrgSM extends AbstractSelectModel implements
		Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4089874863547817451L;

	private final List<OptionModel> options = CollectionFactory.newList();

	List<Organization> orglist;

	public OrgSM(SccDAO sccDao) {
		orglist = sccDao.getOrgListByAssoc();
		setLists(orglist);
	}

	public void setLists(List<Organization> list) {

		for (Organization value : list) {
			options.add(new OptionModelImpl(value.toString() == null ? value
					.toString() : value.toString(), value));
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

