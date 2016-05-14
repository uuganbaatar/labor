package mn.odi.labor.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.services.Response;

public class ExportUtil {

	private SimpleDateFormat df = new SimpleDateFormat(
			Constants.DEFAULT_FULL_DAY_FORMAT);

	private Response response;

	private Messages messages;

	private String title;

	private String subTitle;

	private String fileName;

	private Map<String, String> filters;

	private HSSFWorkbook document;

	private HSSFSheet sheet;

	private Map<String, HSSFCellStyle> styles;

	private Map<Integer, Double> columnWidth = new HashMap<Integer, Double>();

	private StatisticUtil statisticData;

	private TableUtil tableData;

	public ExportUtil(StatisticUtil statisticData) {
		this.statisticData = statisticData;
	}

	public ExportUtil(TableUtil tableData) {
		this.tableData = tableData;
	}

	public void init(Response response, Messages messages,
			Map<String, String> filters, String title, String subTitle,
			String excelName) {
		this.response = response;
		this.messages = messages;

		/*
		 * шүүлтүүр бүхий хүснэгтийг гаргаж байгаа тохиолдолд ямар шүүлтүүрээр
		 * харуулж байгааг гаргахад хэрэглэнэ
		 */
		this.filters = filters;

		/* хүснэгтийн дээд талын үндсэн гарчиг */
		this.title = title;

		/* хүснэгтийн дээд талын дэд гарчиг */
		this.subTitle = subTitle;

		/* excel файлын нэр */
		this.fileName = excelName;

		document = new HSSFWorkbook();

		sheet = ExcelUtil.createSheet(document);

		sheet.setMargin((short) 0, 0.5);

		styles = ExcelUtil.createStyles(document);

	}

	private void generateStatistic() {
		int sheetNumber = 0;
		int rowIndex = 1;
		int colIndex = 1;

		double rowHeight = 1.5;

		/*
		 * TITLE
		 */
		ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex, title,
				styles.get(ExcelUtil.STYLES_TITLE), statisticData.getCols()
						.size() + 2);

		ExcelUtil.setRowHeight(sheet, rowIndex, rowHeight);

		if (subTitle != null) {
			rowIndex++;

			ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex,
					subTitle, styles.get(ExcelUtil.STYLES_SUBTITLE),
					statisticData.getCols().size() + 2);

			ExcelUtil.setRowHeight(sheet, rowIndex, rowHeight);
		}

		rowIndex += 2;

		/*
		 * FILTERS
		 */
		if (filters != null && !filters.isEmpty()) {
			String cellValue;

			for (String filter : filters.keySet()) {
				cellValue = messages.get(filter) + ": " + filters.get(filter);

				ExcelAPI.setCellValue(document, sheetNumber, rowIndex,
						colIndex, cellValue, styles
								.get(ExcelUtil.STYLES_PLAIN_RIGHT),
						statisticData.getCols().size() + 2);

				ExcelUtil.setRowHeight(sheet, rowIndex, rowHeight);

				rowIndex++;
			}

			rowIndex++;
		}

		ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex,
				messages.get("exportedDate") + ": " + df.format(new Date()),
				styles.get(ExcelUtil.STYLES_PLAIN_RIGHT), statisticData
						.getCols().size() + 2);

		rowIndex++;

		/*
		 * HEADERS
		 */
		ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex,
				messages.get("number-label"),
				styles.get(ExcelUtil.STYLES_HEADER));

		colIndex++;

		ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex, "",
				styles.get(ExcelUtil.STYLES_HEADER));

		colIndex++;

		for (String colKey : statisticData.getCols().keySet()) {
			ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex,
					colKey, styles.get(ExcelUtil.STYLES_HEADER));

			setPrefferedColumnWidth(colIndex, colKey);

			colIndex++;
		}

		ExcelUtil.setRowHeight(sheet, rowIndex, rowHeight);

		rowIndex++;

		/*
		 * DATAS
		 */
		int rowNumber = 1;

		for (String rowKey : statisticData.getRows().keySet()) {
			colIndex = 1;

			ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex,
					String.valueOf(rowNumber),
					styles.get(ExcelUtil.STYLES_NORMAL));

			setPrefferedColumnWidth(colIndex, String.valueOf(rowNumber));

			colIndex++;

			ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex,
					rowKey, styles.get(ExcelUtil.STYLES_NORMAL_LEFT));

			setPrefferedColumnWidth(colIndex, rowKey);

			colIndex++;

			for (String colKey : statisticData.getCols().keySet()) {
				ExcelAPI.setCellValue(document, sheetNumber, rowIndex,
						colIndex, statisticData.getStringValue(rowKey, colKey),
						styles.get(ExcelUtil.STYLES_NORMAL));

				setPrefferedColumnWidth(colIndex, colKey);

				colIndex++;
			}

			ExcelUtil.setRowHeight(sheet, rowIndex, rowHeight);

			rowIndex++;

			rowNumber++;
		}

		/*
		 * COLUMN WIDTHS
		 */
		ExcelUtil.setColumnWidths(sheet, 0, 0.5, 1, 0.8);

		for (Integer colNumber : columnWidth.keySet()) {
			ExcelUtil.setColumnWidth(sheet, colNumber,
					columnWidth.get(colNumber));
		}

	}

	private void generateReport() {
		int sheetNumber = 0;
		int rowIndex = 1;
		int colIndex = 0;

		double rowHeight = 1.5;

		int rowSize = tableData.getHeaderRow().size();
		int colSize = 0; // tableData.getHeaderRow().get(rowSize -
							// 1).getCols().size();

		// System.err.println();

		for (TableColumn tCol : tableData.getHeaderRow().get(0).getCols()) {
			colSize += tCol.getColspan();

			// System.err.print(tCol.getColspan() + " ,");
		}

		// System.err.println();
		//
		// System.err.println("colSize: " + colSize);

		int[][] colIndxs = new int[rowSize][colSize + 1];

		for (int r = 0; r < rowSize; r++) {

			// System.err.println();
			//
			// System.err.print("r[" + r + "]: ");

			colIndxs[r][0] = colSize;

			// System.err.print(colIndxs[r][0] + " ,");

			for (int c = 1; c <= colSize; c++) {
				colIndxs[r][c] = c;

				// System.err.print(colIndxs[r][c] + " ,");
			}

			// System.err.println();
		}

		// System.err.println();


		/*
		 * TITLE
		 */
		ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex, title,
				styles.get(ExcelUtil.STYLES_TITLE), colSize);

		ExcelUtil.setRowHeight(sheet, rowIndex, rowHeight);

		if (subTitle != null) {
			rowIndex++;

			ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex,
					subTitle, styles.get(ExcelUtil.STYLES_SUBTITLE), colSize);

			ExcelUtil.setRowHeight(sheet, rowIndex, rowHeight);
		}

		rowIndex += 2;

		/*
		 * FILTERS
		 */
		if (filters != null && !filters.isEmpty()) {
			String cellValue;

			for (String filter : filters.keySet()) {
				cellValue = messages.get(filter) + ": " + filters.get(filter);

				ExcelAPI.setCellValue(document, sheetNumber, rowIndex,
						colIndex, cellValue,
						styles.get(ExcelUtil.STYLES_PLAIN_RIGHT), colSize);

				ExcelUtil.setRowHeight(sheet, rowIndex, rowHeight);

				rowIndex++;
			}

			rowIndex++;
		}

		ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex,
				messages.get("exportedDate") + ": " + df.format(new Date()),
				styles.get(ExcelUtil.STYLES_PLAIN_RIGHT), colSize);

		rowIndex++;

		/*
		 * HEADERS
		 */
		ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex,
				messages.get("number-label"), styles
						.get(ExcelUtil.STYLES_HEADER_WRAP), 1, tableData
						.getHeaderRow().size());

		// colIndex++;

		int rowIndx = 1;
		int colIndx;
		int countOfRowspanCol;

		for (TableRow tRow : tableData.getHeaderRow()) {
			colIndx = 1;
			countOfRowspanCol = 0;

			for (TableColumn tCol : tRow.getCols()) {

				colIndex = colIndxs[rowIndx - 1][colIndx];

				ExcelAPI.setCellValue(document, sheetNumber, rowIndex,
						colIndex, tCol.getValue(),
						styles.get(ExcelUtil.STYLES_HEADER_WRAP),
						tCol.getColspan(), tCol.getRowspan());

				setPrefferedColumnWidth(colIndex, tCol.getValue());

				colIndxs = generateColIndexs(colIndxs, rowIndx, colIndx,
						tCol.getColspan(), tCol.getRowspan(), countOfRowspanCol);

				// System.err.println("# VALUE: " + tCol.getValue()
				// + ", ROW INDX: " + rowIndx + ", COL INDX: " + colIndx
				// + ", COLSPAN: " + tCol.getColspan() + ", ROWSPAN: "
				// + tCol.getRowspan() + ", ROWSPAN COUNT: "
				// + countOfRowspanCol);
				//
				// for (int r = 0; r < colIndxs.length; r++) {
				//
				// System.err.print("row[" + r + "]: ");
				//
				// for (int c = 0; c < colIndxs[r].length; c++) {
				// System.err.print("[" + c + " : " + colIndxs[r][c]
				// + "]; ");
				// }
				//
				// System.err.println();
				// }
				//
				// System.err.println();

				colIndx++;


				if (tCol.getRowspan() > 1) {
					countOfRowspanCol++;
				} else {
					countOfRowspanCol = 0;
				}

			}

			ExcelUtil.setRowHeight(sheet, rowIndex, rowHeight);

			rowIndx++;
			rowIndex++;
		}

		/*
		 * DATAS
		 */
		int rowNumber = 1;

		for (TableRow tRow : tableData.getRows()) {
			colIndex = 0;

			ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex,
					String.valueOf(rowNumber),
					styles.get(ExcelUtil.STYLES_NORMAL_CENTER));

			setPrefferedColumnWidth(colIndex, String.valueOf(rowNumber));

			colIndex++;

			for (TableColumn tCol : tRow.getCols()) {
				ExcelAPI.setCellValue(document, sheetNumber, rowIndex,
						colIndex, tCol.getValue(),
						styles.get(ExcelUtil.STYLES_PLAIN_CENTER_WRAP_BORDER));

				setPrefferedColumnWidth(colIndex, tCol.getValue());

				colIndex++;
			}

			// ExcelAPI.setCellValue(document, sheetNumber, rowIndex, colIndex,
			// tRow, styles.get(ExcelUtil.STYLES_NORMAL_LEFT));
			//
			// setPrefferedColumnWidth(colIndex, tRow);
			//
			// colIndex++;

			ExcelUtil.setRowHeight(sheet, rowIndex, rowHeight);

			rowIndex++;

			rowNumber++;
		}

		/*
		 * COLUMN WIDTHS
		 */
		ExcelUtil.setColumnWidths(sheet, 0, 0.5, 1, 0.8);

		for (Integer colNumber : columnWidth.keySet()) {
			ExcelUtil.setColumnWidth(sheet, colNumber,
					columnWidth.get(colNumber));
		}

	}


	private int[][] generateColIndexs(int[][] colIndxs, int rowIndx,
			int colIndx, int colspan, int rowspan, int countOfRowspanCol) {

		if (colspan > 1) {
			colIndxs[rowIndx - 1][0] -= (colspan - 1);

			for (int c = colIndx + 1; c <= colIndxs[rowIndx - 1][0]; c++) {
				colIndxs[rowIndx - 1][c] = colIndxs[rowIndx - 1][c + colspan
						- 1];
			}

			for (int c = colIndxs[rowIndx - 1][0] + 1; c < colIndxs[rowIndx - 1].length; c++) {
				colIndxs[rowIndx - 1][c] = 0;
			}
		}

		if (rowspan > 1) {
			colIndx -= countOfRowspanCol;

			for (int r = rowIndx; r < (rowIndx + rowspan - 1); r++) {


				if (colIndxs[r][colIndx] < colIndxs[r - 1][colIndx]) {
					int counter = 1;

					while (colIndxs[r][colIndx + counter] < colIndxs[r - 1][colIndx]) {
						counter++;
					}

					colIndx += counter;
				}


				colIndxs[r][0] -= colspan;

				for (int c = colIndx; c <= colIndxs[r][0]; c++) {
					colIndxs[r][c] = colIndxs[r][c + colspan];
				}

				for (int c = colIndxs[r][0] + 1; c < colIndxs[r].length; c++) {
					colIndxs[r][c] = 0;
				}
			}
		}

		return colIndxs;
	}



	public boolean exportToExcel() {
		boolean b = false;

		try {
			if (statisticData != null) {
				generateStatistic();
			} else if (tableData != null) {
				generateReport();
			}

			OutputStream out = response
					.getOutputStream("application/vnd.ms-excel");

			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + ".xls\"");

			document.write(out);

			out.close();

			b = true;

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return b;
	}

	/* Тухайн баганы өргөнийг автоматаар тохируулах функц */
	void setPrefferedColumnWidth(Integer colIndex, String text) {
		if (text != null) {
			if (text.length() > 31) {
				if (columnWidth.get(colIndex) == null
						|| columnWidth.get(colIndex) < 5.0)
					columnWidth.put(colIndex, 5.0);
			} else if (text.length() > 23) {
				if (columnWidth.get(colIndex) == null
						|| columnWidth.get(colIndex) < 4.0)
					columnWidth.put(colIndex, 4.0);
			} else if (text.length() > 15) {
				if (columnWidth.get(colIndex) == null
						|| columnWidth.get(colIndex) < 3.0)
					columnWidth.put(colIndex, 3.0);
			} else if (text.length() > 7) {
				if (columnWidth.get(colIndex) == null
						|| columnWidth.get(colIndex) < 2.0)
					columnWidth.put(colIndex, 2.0);
			}
			if (text.length() > 3) {
				if (columnWidth.get(colIndex) == null
						|| columnWidth.get(colIndex) < 1.0)
					columnWidth.put(colIndex, 1.0);
			} else {
				if (columnWidth.get(colIndex) == null
						|| columnWidth.get(colIndex) < 0.5)
					columnWidth.put(colIndex, 0.5);
			}
		}
	}

	void setPrefferedColumnWidth(Integer colIndex, String text, Integer colspan) {
		int cols = 1;

		if (colspan != null) {
			cols = colspan;
		}

		if (text != null) {
			if (text.length() > (31 + cols * 3)) {
				if (columnWidth.get(colIndex) == null
						|| columnWidth.get(colIndex) < 5.0)
					columnWidth.put(colIndex, 5.0);
			} else if (text.length() > (23 + cols * 3)) {
				if (columnWidth.get(colIndex) == null
						|| columnWidth.get(colIndex) < 4.0)
					columnWidth.put(colIndex, 4.0);
			} else if (text.length() > (15 + cols * 3)) {
				if (columnWidth.get(colIndex) == null
						|| columnWidth.get(colIndex) < 3.0)
					columnWidth.put(colIndex, 3.0);
			} else if (text.length() > (7 + cols * 3)) {
				if (columnWidth.get(colIndex) == null
						|| columnWidth.get(colIndex) < 2.0)
					columnWidth.put(colIndex, 2.0);
			}
			if (text.length() > (3 + cols * 3)) {
				if (columnWidth.get(colIndex) == null
						|| columnWidth.get(colIndex) < 1.0)
					columnWidth.put(colIndex, 1.0);
			} else {
				if (columnWidth.get(colIndex) == null
						|| columnWidth.get(colIndex) < 0.5)
					columnWidth.put(colIndex, 0.5);
			}
		}
	}
}