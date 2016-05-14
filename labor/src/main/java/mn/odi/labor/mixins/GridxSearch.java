/* 
 * Package Name: mn.odi.scc.mixins
 * File Name: GridxSearch.java
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
package mn.odi.labor.mixins;

import java.util.List;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Node;


/**
 * @author Tserentogtokh
 *
 */
@MixinAfter
public class GridxSearch {

	@AfterRender
	void afterRender(final MarkupWriter writer) {
		Element form = writer.getElement();
		Element tableSearch = form.getElementById("gridxSearchTable");
		Element theadSearch = (Element) tableSearch.find("thead");
		Node trSearch = (Node) theadSearch.find("tr");

		List<Node> nodes = form.getChildren();

		boolean hasResult = false;

		if (nodes != null && nodes.size() > 0) {

			Node node = nodes.get(nodes.size() - 1);

			if (node instanceof Element) {
				Element el = (Element) node;

				Element tr = el.find("table/thead/tr");
				if (trSearch != null && tr != null) {
					trSearch.moveToBottom(tr);
					hasResult = true;
				}
			}
		}

		// if (!hasResult) {
		// trSearch.remove();
		// }
	}
}