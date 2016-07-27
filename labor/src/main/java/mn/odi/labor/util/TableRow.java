/* 
 * Package Name: mn.odi.scc.util
 * File Name: TableRow.java
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
import java.util.List;

public class TableRow {

	/*
	 * PROPERTIES
	 */

	private String name;

	private List<TableColumn> cols = new ArrayList<TableColumn>();

	private Boolean isBold;

	/*
	 * CONSTRUCTOR
	 */

	public TableRow() {
	}

	public TableRow(String name) {
		this.name = name;
	}

	public TableRow(String name, Boolean bold) {
		this.name = name;
		this.isBold = bold;
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

	public List<TableColumn> getCols() {
		return cols;
	}

	public void setCols(List<TableColumn> cols) {
		this.cols = cols;
	}

	public Boolean getIsBold() {
		return isBold;
	}

	public void setIsBold(Boolean isBold) {
		this.isBold = isBold;
	}

	/*
	 * BUSINESS METHODS
	 */

	public boolean addCol(String name, String value, int colspan, int rowspan) {
		TableColumn tCol = new TableColumn(name, value, colspan, rowspan);

		return cols.add(tCol);
	}

	public boolean addHeaderCol(String name, int colspan, int rowspan) {
		TableColumn tCol = new TableColumn(name, colspan, rowspan);

		return cols.add(tCol);
	}

	public TableColumn getTableColumn(String name) {
		TableColumn retCol = null;

		for (TableColumn tCol : cols) {

			if (tCol.getName().equals(name)) {
				retCol = tCol;

				break;
			}
		}

		return retCol;
	}

	public boolean updateCol(String colName, String value, Integer colspan,
			Integer rowspan) {
		TableColumn tCol = getTableColumn(colName);

		boolean b = false;

		if (value != null) {
			tCol.setValue(value);
			b = true;
		}

		if (colspan != null) {
			tCol.setColspan(colspan);
			b = true;
		}

		if (rowspan != null) {
			tCol.setRowspan(rowspan);
			b = true;
		}

		return b;
	}

}