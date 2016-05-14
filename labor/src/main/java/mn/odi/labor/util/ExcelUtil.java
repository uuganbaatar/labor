package mn.odi.labor.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelUtil {
	public static final String DEFAULT_REPORT_SHEET_NAME = " SCC REPORT ";
	public static final int DEFAULT_COLUMN_WIDTH = 256 * 10; // 10 chars

	public static final String STYLES_TITLE = "title";
	public static final String STYLES_SUBTITLE = "sub-title";
	public static final String STYLES_BOLD = "bold";
	public static final String STYLES_PLAIN = "plain";
	public static final String STYLES_PLAIN_TXT = "plain-text";
	public static final String STYLES_PLAIN_TXT_RIGHT = "plain-text-right";
	public static final String STYLES_PLAIN_TXT_LEFT = "plain-text-left";
	public static final String STYLES_TOTAL = "total";
	public static final String STYLES_TOTAL_RIGHT = "total-right";
	public static final String STYLES_PLAIN_LEFT_ITALIC = "plain-left-italic";
	public static final String STYLES_PLAIN_HEADER = "plain-header";
	public static final String STYLES_PLAIN_LEFT = "plain-left";
	public static final String STYLES_PLAIN_LEFT_WRAP = "plain-left-wrap";
	public static final String STYLES_PLAIN_LEFT_WRAP_BORDER = "plain-left-wrap-border";
	public static final String STYLES_PLAIN_CENTER_WRAP_BORDER = "plain-center-wrap-border";
	public static final String STYLES_PLAIN_RIGHT = "plain-right";
	public static final String STYLES_BOLD_LEFT = "bold-left";
	public static final String STYLES_BOLD_RIGHT = "bold-right";
	public static final String STYLES_HEADER = "header";
	public static final String STYLES_HEADER_LEFT = "header-left";
	public static final String STYLES_HEADER_RIGHT = "header-right";
	public static final String STYLES_HEADER_WRAP = "header-wrap";
	public static final String STYLES_HEADER_VERTICAL = "header-vertical";
	public static final String STYLES_NORMAL = "normal";
	public static final String STYLES_NORMAL_LEFT = "normal-left";
	public static final String STYLES_NORMAL_RIGHT = "normal-right";
	public static final String STYLES_NORMAL_CENTER = "normal-center";
	public static final String STYLES_NORMAL_BOLD = "normal-bold";
	public static final String STYLES_NORMAL_BOLD_LEFT = "normal-bold-left";
	public static final String STYLES_NORMAL_BOLD_RIGHT = "normal-bold-right";
	public static final String STYLES_SUMMARY = "summary";
	public static final String STYLES_SUMMARY_RIGHT = "summary-right";
	public static final String STYLES_SUMMARY_PERCENT = "summary-percent";
	public static final String STYLES_NUMBER_RIGHT = "number-right";
	public static final String STYLES_CURRENCY_RIGHT = "currency-right";
	public static final String STYLES_PERCENT = "percent";
	public static final String STYLES_RESULTS_DATA = "results_data";
	public static final String STYLES_RESULTS_DATA_RIGHT = "results_data-right";
	public static final String STYLES_RESULTS_DATA_CENTER = "results_data-center";
	public static final String STYLES_DESCRIPTION = "description";
	public static final String STYLES_TODAY_DATE = "today_date";

	// sheet selected
	public static final boolean DEFAULT_SHEET_SELECTED = true;

	// sheet.setZoom(numerator,denominator);
	public static final int DEFAULT_NUMERATOR = 3;
	public static final int DEFAULT_DENOMINATOR = 4;

	// grid lines
	public static final boolean DEFAULT_PRINT_GRIDLINES = false;
	public static final boolean DEFAULT_DISPLAY_GRIDLINES = false;

	public static HSSFSheet createSheet(HSSFWorkbook wb) {
		HSSFSheet sheet = wb.createSheet(DEFAULT_REPORT_SHEET_NAME);

		sheet.setSelected(DEFAULT_SHEET_SELECTED);
		sheet.setPrintGridlines(DEFAULT_PRINT_GRIDLINES);
		sheet.setDisplayGridlines(DEFAULT_DISPLAY_GRIDLINES);
		sheet.setZoom(DEFAULT_NUMERATOR, DEFAULT_DENOMINATOR);

		return sheet;
	}

	/**
	 * @param wb
	 *            - Workbook
	 * @param sheetName
	 *            - Sheet name
	 * 
	 * @return HSSFSheet
	 */
	public static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName) {
		HSSFSheet sheet = wb.createSheet(sheetName);

		sheet.setSelected(DEFAULT_SHEET_SELECTED);
		sheet.setPrintGridlines(DEFAULT_PRINT_GRIDLINES);
		sheet.setDisplayGridlines(DEFAULT_DISPLAY_GRIDLINES);
		sheet.setZoom(DEFAULT_NUMERATOR, DEFAULT_DENOMINATOR);

		return sheet;
	}

	public static Map<String, HSSFCellStyle> createStyles(HSSFWorkbook wb) {

		Map<String, HSSFCellStyle> styles = new HashMap<String, HSSFCellStyle>();

		HSSFCellStyle style = null;

		HSSFDataFormat dformat = wb.createDataFormat();

		HSSFFont titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 14);
		titleFont.setFontName(HSSFFont.FONT_ARIAL);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFFont subTitleFont = wb.createFont();
		subTitleFont.setFontHeightInPoints((short) 12);
		subTitleFont.setFontName(HSSFFont.FONT_ARIAL);
		subTitleFont.setItalic(true);

		HSSFFont boldFont = wb.createFont();
		boldFont.setFontHeightInPoints((short) 12);
		boldFont.setFontName(HSSFFont.FONT_ARIAL);
		boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFFont plainFont = wb.createFont();
		plainFont.setFontHeightInPoints((short) 12);
		plainFont.setFontName(HSSFFont.FONT_ARIAL);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(titleFont);
		styles.put(STYLES_TITLE, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(subTitleFont);
		styles.put(STYLES_SUBTITLE, style);

		HSSFFont plainTextFont = wb.createFont();
		plainFont.setFontHeightInPoints((short) 10);
		plainFont.setFontName(HSSFFont.FONT_ARIAL);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(boldFont);
		styles.put(STYLES_BOLD, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(plainFont);
		styles.put(STYLES_PLAIN, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(plainTextFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_PLAIN_TXT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(plainTextFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_PLAIN_TXT_RIGHT, style);

		plainTextFont = wb.createFont();
		plainTextFont.setFontHeightInPoints((short) 12);
		plainTextFont.setFontName(HSSFFont.FONT_ARIAL);
		plainTextFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(plainTextFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_PLAIN_TXT_LEFT, style);

		HSSFFont plainTotalFont = wb.createFont();
		plainTotalFont.setFontHeightInPoints((short) 14);
		plainTotalFont.setFontName(HSSFFont.FONT_ARIAL);
		plainTotalFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(plainTotalFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_TOTAL, style);

		plainTotalFont = wb.createFont();
		plainTotalFont.setFontHeightInPoints((short) 14);
		plainTotalFont.setFontName(HSSFFont.FONT_ARIAL);
		plainTotalFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(plainTotalFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_TOTAL_RIGHT, style);

		style = wb.createCellStyle();
		HSSFFont plainFontItalic = wb.createFont();
		plainFontItalic.setFontHeightInPoints((short) 10);
		plainFontItalic.setFontName(HSSFFont.FONT_ARIAL);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		plainFontItalic.setItalic(true);
		style.setFont(plainFontItalic);
		styles.put(STYLES_PLAIN_LEFT_ITALIC, style);

		plainTextFont = wb.createFont();
		plainTextFont.setFontHeightInPoints((short) 12);
		plainTextFont.setFontName(HSSFFont.FONT_ARIAL);
		plainTextFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		style = wb.createCellStyle();
		plainTextFont = wb.createFont();
		plainTextFont.setFontHeightInPoints((short) 16);
		plainTextFont.setFontName(HSSFFont.FONT_ARIAL);
		plainTextFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(plainTextFont);

		styles.put(STYLES_PLAIN_HEADER, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(plainFont);
		styles.put(STYLES_PLAIN_LEFT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(plainFont);
		style.setWrapText(true);
		styles.put(STYLES_PLAIN_LEFT_WRAP, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(plainFont);
		style.setWrapText(true);
		style.setBorderTop(HSSFCellStyle.BORDER_DOTTED);
		style.setBorderLeft(HSSFCellStyle.BORDER_DOTTED);
		style.setBorderRight(HSSFCellStyle.BORDER_DOTTED);
		style.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
		styles.put(STYLES_PLAIN_LEFT_WRAP_BORDER, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(plainFont);
		style.setWrapText(true);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styles.put(STYLES_PLAIN_CENTER_WRAP_BORDER, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(plainFont);
		styles.put(STYLES_PLAIN_RIGHT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(boldFont);
		styles.put(STYLES_BOLD_LEFT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(boldFont);
		styles.put(STYLES_BOLD_RIGHT, style);

		HSSFFont headerFont = wb.createFont();
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setFontName(HSSFFont.FONT_ARIAL);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(headerFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
		styles.put(STYLES_HEADER, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(headerFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
		styles.put(STYLES_HEADER_LEFT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(headerFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
		styles.put(STYLES_HEADER_RIGHT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(headerFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
		style.setWrapText(true);
		styles.put(STYLES_HEADER_WRAP, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(headerFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
		style.setRotation((short) 90);
		styles.put(STYLES_HEADER_VERTICAL, style);

		HSSFFont normalFont = wb.createFont();
		normalFont.setFontHeightInPoints((short) 12);
		normalFont.setFontName(HSSFFont.FONT_ARIAL);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(normalFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_NORMAL, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(normalFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_NORMAL_LEFT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(normalFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_NORMAL_RIGHT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(normalFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_NORMAL_CENTER, style);

		HSSFFont normalBoldFont = wb.createFont();
		normalBoldFont.setFontHeightInPoints((short) 12);
		normalBoldFont.setFontName(HSSFFont.FONT_ARIAL);
		normalBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(normalBoldFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_NORMAL_BOLD, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(normalBoldFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_NORMAL_BOLD_LEFT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(normalBoldFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_NORMAL_BOLD_RIGHT, style);

		HSSFFont summaryFont = wb.createFont();
		summaryFont.setFontHeightInPoints((short) 12);
		summaryFont.setFontName(HSSFFont.FONT_ARIAL);
		summaryFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
		style.setFont(summaryFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		styles.put(STYLES_SUMMARY, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
		style.setFont(summaryFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setDataFormat(dformat.getFormat(Constants.NUMBER_FORMAT));
		styles.put(STYLES_SUMMARY_RIGHT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
		style.setFont(summaryFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setDataFormat(dformat.getFormat(Constants.PERCENT_FORMAT));
		styles.put(STYLES_SUMMARY_PERCENT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(normalFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setDataFormat(dformat.getFormat(Constants.NUMBER_FORMAT));
		styles.put(STYLES_NUMBER_RIGHT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(normalFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setDataFormat(dformat.getFormat(Constants.CURRENCY_FORMAT));
		styles.put(STYLES_CURRENCY_RIGHT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(normalFont);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setDataFormat(dformat.getFormat(Constants.PERCENT_FORMAT));
		styles.put(STYLES_PERCENT, style);

		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setFontHeightInPoints((short) 12);
		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style = createBorderedStyle(style, HSSFCellStyle.BORDER_THIN,
				HSSFColor.BLACK.index);
		style.setFont(font);
		styles.put(STYLES_RESULTS_DATA, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style = createBorderedStyle(style, HSSFCellStyle.BORDER_THIN,
				HSSFColor.BLACK.index);
		style.setFont(font);
		styles.put(STYLES_RESULTS_DATA_RIGHT, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style = createBorderedStyle(style, HSSFCellStyle.BORDER_THIN,
				HSSFColor.BLACK.index);
		style.setFont(font);
		styles.put(STYLES_RESULTS_DATA_CENTER, style);

		font = wb.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setFontHeightInPoints((short) 12);
		font.setItalic(true);
		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(font);
		styles.put(STYLES_DESCRIPTION, style);

		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(font);
		styles.put(STYLES_TODAY_DATE, style);

		HSSFPalette pl = wb.getCustomPalette();
		pl.setColorAtIndex(HSSFColor.GREY_80_PERCENT.index, (byte) 211,
				(byte) 211, (byte) 211);
		pl.setColorAtIndex(HSSFColor.GREY_40_PERCENT.index, (byte) 160,
				(byte) 160, (byte) 160);

		return styles;
	}

	public static HSSFCellStyle createBorderedStyle(HSSFCellStyle style,
			short border, short borderColor) {
		style.setBorderRight(border);
		style.setRightBorderColor(borderColor);
		style.setBorderBottom(border);
		style.setBottomBorderColor(borderColor);
		style.setBorderLeft(border);
		style.setLeftBorderColor(borderColor);
		style.setBorderTop(border);
		style.setTopBorderColor(borderColor);
		return style;
	}

	public static void setColumnWidths(HSSFSheet sheet, double... ds) {
		for (int i = 0; i < ds.length; i += 2) {
			int index = (int) ds[i];
			double scale = ds[i + 1];

			setColumnWidth(sheet, index, scale);
		}
	}

	public static void setColumnWidths(HSSFSheet sheet,
			Map<Integer, Double> columnWiths) {
		for (Integer key : columnWiths.keySet()) {
			setColumnWidth(sheet, key, columnWiths.get(key));
		}
	}

	public static void setColumnWidth(HSSFSheet sheet, int columnIndex,
			double scale) {
		if (scale > 0) {
			sheet.setColumnWidth(columnIndex,
					(int) (scale * DEFAULT_COLUMN_WIDTH));
		}
	}

	public static void setRowHeights(HSSFSheet sheet, double... ds) {
		for (int i = 0; i < ds.length; i += 2) {
			int index = (int) ds[i];
			double scale = ds[i + 1];

			setRowHeight(sheet, index, scale);
		}
	}

	public static void setRowHeight(HSSFSheet sheet, int rowIndex, double scale) {
		HSSFRow row = sheet.getRow(rowIndex);
		if (scale > 0 && row != null) {
			row.setHeightInPoints((float) (row.getHeightInPoints() * scale));
		}
	}
}