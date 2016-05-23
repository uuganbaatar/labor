package mn.odi.labor.dao;

import java.util.Date;
import java.util.List;

import mn.odi.labor.entities.admin.AjiliinBairHurungu;
import mn.odi.labor.entities.admin.CompanyHelber;
import mn.odi.labor.entities.admin.CompanyStatus;
import mn.odi.labor.entities.admin.CompanyTrend;
import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.entities.admin.LavlahGarsan;
import mn.odi.labor.entities.common.BaseObject;
import mn.odi.labor.entities.common.User;
import mn.odi.labor.entities.labor.Job;

public interface SccDAO {

	public Date getCurrentDate();

	public void saveObject(BaseObject obj);

	public void updateObject(BaseObject obj);

	public void saveOrUpdateObject(BaseObject obj);

	public void saveObject(Object obj);

	public void updateObject(Object obj);

	public void saveOrUpdateObject(Object obj);

	public void mergeObject(Object obj);

	public void deleteObject(Object obj);

	public void deleteBaseObject(BaseObject obj);

	public Object getObject(Class<?> clss, Long id);

	/**
	 * @param clss
	 *            - Entity.
	 * @param id
	 *            - Tuhain entity-ees duudah bichlegin ID.
	 * @param fetchProperty
	 *            - JOIN hiih talbarin ner.
	 * @return Object.
	 */

	public Object getObject(Class<?> clss, Long id, String fetchProperty);

	/**
	 * @param clss
	 *            - Entity.
	 * @param id
	 *            - Tuhain entity-ees duudah bichlegin ID.
	 * @param fetchProperties
	 *            JOIN hiih talbaruudin ner.
	 * @return Object.
	 */
	public Object getObject(Class<?> clss, Long id, List<String> fetchProperties);

	public User getUserByUsername(String username);

	public List<User> getUserList();

	public List<Job> getJobList();
	
	public List<GeneralType> getGeneralTypeList();

	public List<CompanyTrend> getCompanyTrendList();

	public List<AjiliinBairHurungu> getLavlahHurunguList();

	public List<CompanyHelber> getLavlahHelberList();

	public List<CompanyStatus> getLavlahStatusList();
	
	public List<LavlahGarsan> getLavlahEmpGarsanList();

}