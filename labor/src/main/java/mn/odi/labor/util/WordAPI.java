package mn.odi.labor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hpsf.CustomProperties;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.LineSpacingDescriptor;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.ParagraphProperties;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Context;

public class WordAPI {

	@Inject
	private Context context;

	public POIFSFileSystem openWord(String wordFileName)
			throws FileNotFoundException, IOException {

		File fl = new File(context.getRealFile(
				Constants.FILE_PATH_DOCUMENT + wordFileName).getPath());

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fl));

		return fs;
	}

	public static void setCellValue(HWPFDocument doc, int page, int paragrapch,
			int character, String value) {
		String osName = System.getProperty("os.name");

		Range r = doc.getRange();
		Section s = r.getSection(page);

		Paragraph p = r.insertAfter(new ParagraphProperties(), 0);
		p.setJustification((byte) 3);
		CharacterRun run1 = p.insertAfter(value);

	}
	public static void getSpace(HWPFDocument doc, int page, int paragraph,
			int character) {
		Range r = doc.getRange();
		Section s = r.getSection(page);

		Paragraph p = s.insertBefore(new ParagraphProperties(), 0);
		LineSpacingDescriptor line = new LineSpacingDescriptor();
		line.setDyaLine((short) 0);
		p.setSpacingAfter(50);
		p.setSpacingBefore(100);
		p.setLineSpacing(line);

		CharacterRun run = p.insertBefore("\n");
		run.setCharacterSpacing(500);

	}

	public static void setHeaderValue(HWPFDocument doc, int page,
			int paragraph, int character, String value) {
		// getSpace(doc, page, paragraph, character);
		Range r = doc.getRange();
		Section s = r.getSection(page);
		ParagraphProperties pro = new ParagraphProperties();
		pro.setWordWrapped(true);

		Paragraph p = s.insertBefore(pro, 0);

		CharacterRun run = p.insertBefore(value);
		// run.setCapitalized(true);
		run.setItalic(true);
		DocumentSummaryInformation dsi = doc.getDocumentSummaryInformation();
		CustomProperties cp = dsi.getCustomProperties();
		if (cp == null)
			cp = new CustomProperties();
		cp.put("myProperty", "foo bar baz");
		dsi.setCustomProperties(cp);
		// getSpace(doc, page, paragraph, character);

	}
}
