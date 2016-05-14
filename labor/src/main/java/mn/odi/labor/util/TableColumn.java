/* 
 * Package Name: mn.odi.scc.util
 * File Name: TableColumn.java
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

public class TableColumn {

	/*
	 * PROPERTIES
	 */

	private String name;

	private String value;

	private int colspan;

	private int rowspan;

	/*
	 * CONSTRUCTOR
	 */

	public TableColumn(String name, int colspan, int rowspan) {
		this.name = name;
		this.colspan = colspan;
		this.rowspan = rowspan;
	}

	public TableColumn(String name, String value, int colspan, int rowspan) {
		this.name = name;
		this.value = value;
		this.colspan = colspan;
		this.rowspan = rowspan;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	/*
	 * BUSINESS METHODS
	 */
}