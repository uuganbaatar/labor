/* 
 * Package Name: mn.odi.scc.util
 * File Name: TableUtil.java
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

public class TableUtil {
	/*
	 * PROPERTIES
	 */

	private String name;

	private List<TableRow> headerRows = new ArrayList<TableRow>();

	private List<TableRow> rows = new ArrayList<TableRow>();

	/*
	 * CONSTRUCTOR
	 */

	public TableUtil() {

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

	public List<TableRow> getRows() {
		return rows;
	}

	public void setRows(List<TableRow> rows) {
		this.rows = rows;
	}

	public List<TableRow> getHeaderRow() {
		return headerRows;
	}

	public void setHeaderRow(List<TableRow> headerRow) {
		this.headerRows = headerRow;
	}

	/*
	 * BUSINESS METHODS
	 */

	private TableRow getTableRow(String name) {
		TableRow retRow = null;

		for (TableRow tRow : headerRows) {
			if (tRow.getName().equals(name)) {
				retRow = tRow;

				break;
			}
		}

		for (TableRow tRow : rows) {
			if (tRow.getName().equals(name)) {
				retRow = tRow;

				break;
			}
		}

		return retRow;
	}

	public boolean addHeaderRow(String name) {
		TableRow tRow = new TableRow(name);

		return headerRows.add(tRow);
	}

	public boolean addRow(String name) {
		TableRow tRow = new TableRow(name);

		return rows.add(tRow);
	}

	public boolean addRow(String name, boolean bold) {
		TableRow tRow = new TableRow(name);
		tRow.setIsBold(bold);
		return rows.add(tRow);
	}

	public boolean addCol(String rowName, String colName, String value) {
		TableRow tRow = getTableRow(rowName);

		if (tRow == null) {
			return false;
		}

		return tRow.addCol(colName, value, 1, 1);
	}

	public boolean addCol(String rowName, String colName, String value,
			int colspan, int rowspan) {
		TableRow tRow = getTableRow(rowName);

		if (tRow == null) {
			return false;
		}

		return tRow.addCol(colName, value, colspan, rowspan);
	}

	public TableColumn getTableColumn(String rowName, String colName) {
		TableRow tRow = getTableRow(rowName);

		if (tRow == null) {
			return null;
		}

		return tRow.getTableColumn(colName);
	}

	public boolean updateCol(String rowName, String colName, String colValue,
			Integer colspan, Integer rowspan) {
		TableRow tRow = getTableRow(rowName);

		return tRow.updateCol(colName, colValue, colspan, rowspan);
	}

	public boolean addOrUpdateCol(String rowName, String colName, String value,
			int colspan, int rowspan) {
		TableRow tRow = getTableRow(rowName);

		if (tRow == null) {
			return false;
		}

		TableColumn tCol = tRow.getTableColumn(colName);

		if (tCol == null) {
			return tRow.addCol(colName, value, colspan, rowspan);
		} else {
			return tRow.updateCol(colName, value, colspan, rowspan);
		}
	}
}