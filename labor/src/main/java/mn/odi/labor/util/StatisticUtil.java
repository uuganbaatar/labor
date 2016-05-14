/* 
 * Package Name: mn.odi.scc.util
 * File Name: StatisticUtil.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticUtil {
	/*
	 * CONSTANTS
	 */
	private final static Integer VALUE_ZERO = 0;

	private final static String VALUE_STRING_ZERO = "-";

	/*
	 * PROPERTIES
	 */

	private String name;
	
	private String title;
	
	private String subTitle;

	private Map<String, List<String>> rows;

	private Map<String, List<String>> cols;

	private Map<String, Map<String, Integer>> values;

	/*
	 * CONSTRUCTOR
	 */

	public StatisticUtil() {

	}

	/*
	 * GETTER and SETTER
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Map<String, List<String>> getRows() {
		
		//Map<String, List<String>> treeMap = new TreeMap<String, List<String>>(rows);
		
		return rows;
	}

	public void setRows(Map<String, List<String>> rows) {
		this.rows = rows;
	}

	public Map<String, List<String>> getCols() {
		return cols;
	}

	public void setCols(Map<String, List<String>> cols) {
		this.cols = cols;
	}

	/*
	 * BUSINESS METHODS
	 */
	/**
	 * Property-uud null baival default baidald beldeh.
	 */
	private void checkProperties() {
		if (values == null) {
			values = new HashMap<String, Map<String, Integer>>();
		}

		if (rows == null) {
			rows = new HashMap<String, List<String>>();
		}

		if (cols == null) {
			cols = new HashMap<String, List<String>>();
		}
	}

	public boolean addValue(String mainRow, String mainCol, String subRow,
			String subCol, Integer value) {
		this.checkProperties();

		String row, col;

		try {

			if (subRow == null) {
				rows.put(mainRow, null);

				row = mainRow;
			} else {
				if (!rows.containsKey(mainRow)) {
					rows.put(mainRow, new ArrayList<String>());
					rows.get(mainRow).add(subRow);
				} else {
					if (!rows.get(mainRow).contains(subRow)) {
						rows.get(mainRow).add(subRow);
					}
				}

				row = subRow;
			}

			if (subCol == null) {
				cols.put(mainCol, null);

				col = mainCol;
			} else {
				if (!cols.containsKey(mainCol)) {
					cols.put(mainCol, new ArrayList<String>());
					cols.get(mainCol).add(subCol);
				} else {
					if (!cols.get(mainCol).contains(subCol)) {
						cols.get(mainCol).add(subCol);
					}
				}

				col = subCol;
			}

			if (!values.containsKey(row)) {
				values.put(row, new HashMap<String, Integer>());
			}

			values.get(row).put(col, value);

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	public int getValue(String row, String col) {
		Integer value = values.get(row).get(col);

		if (value == null) {
			value = VALUE_ZERO;
		}

		return value.intValue();
	}

	public String getStringValue(String row, String col) {
		int value = getValue(row, col);

		if (value > 0) {
			return String.valueOf(value);
		} else {
			return VALUE_STRING_ZERO;
		}
	}
}