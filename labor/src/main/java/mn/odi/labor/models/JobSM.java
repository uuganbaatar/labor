package mn.odi.labor.models;

import java.io.Serializable;
import java.util.List;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.util.AbstractSelectModel;

import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.Organization;
import mn.odi.labor.entities.labor.Job;

public class JobSM extends AbstractSelectModel implements Serializable {

	private static final long serialVersionUID = -4089874863547817451L;

	private final List<OptionModel> options = CollectionFactory.newList();

	List<Job> jobList;

	public JobSM(SccDAO sccDao) {
		jobList = sccDao.getJobList();
		setLists(jobList);
	}

	public JobSM(SccDAO sccDao, Organization org) {
		if (org != null) {
			jobList = sccDao.getJobListByOrg(org);
			setLists(jobList);
		}
	}

	public void setLists(List<Job> list) {
		if (list != null) {
			for (Job value : list) {
				options.add(new OptionModelImpl(value.getJobName() == null ? value
						.getJobName() : value.getJobName(), value));
			}
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
