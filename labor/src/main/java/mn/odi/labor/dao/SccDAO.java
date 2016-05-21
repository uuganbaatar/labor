package mn.odi.labor.dao;

import java.util.Date;
import java.util.List;

import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.entities.common.BaseObject;
import mn.odi.labor.entities.common.User;

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

	public List<GeneralType> getGeneralTypeList();
}