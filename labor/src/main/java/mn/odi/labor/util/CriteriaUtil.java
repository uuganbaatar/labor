/* 
 * Package Name: mn.odi.scc.util
 * File Name: CriteriaUtil.java
 * 
 * Created By: Tserentogtokh.D
 * Created Date: 2014/04/14
 * 
 * History
 * ------------------------------------------------------------------------------
 * Date							Programmer						Description
 * ------------------------------------------------------------------------------
 * 2014/04/14 1.0.0 			Tserentogtokh.D				    Шинээр үүсгэв.
 * ------------------------------------------------------------------------------
 * 
 * ALL RIGHTS RESERVED COPYRIGHT (C) 2014 Od Innovation CO.,LTD SOFTWARE DIVSION
 */
package mn.odi.labor.util;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public class CriteriaUtil {

	/*
	 * PROPERTIES
	 */

	private Class<?> clss;

	private List<Criterion> criterions;

	private Map<String, String> alias;

	private List<Order> orders;

	private String[] joinColumns;

	private String[] properties;

	private int pageStart;

	private int rowsPerPage;

	/*
	 * CONSTRUCTOR
	 */

	public CriteriaUtil() {

	}

	/*
	 * GETTER and SETTER
	 */

	/*
	 * BUSINESS METHODS
	 */

}