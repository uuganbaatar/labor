/* 
 * Package Name: mn.odi.scc.mixins
 * File Name: MoveGridxControls.java
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

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.dom.Element;


/**
 * @author Tserentogtokh
 *
 */
@MixinAfter
public class MoveGridxControls {

	private final static String ATTRIBUTE_NAME = "class";
	private final static String DEFAULT_CLASS_NAME = "title";
	private final static String ELEMENT_ID = "controlBtns";

	@Parameter(required = false, defaultPrefix = BindingConstants.LITERAL)
	private String className;

	@AfterRender
	void afterRender(final MarkupWriter writer) {
		String cName = (className == null) ? DEFAULT_CLASS_NAME : className;

		Element btn = writer.getElement().getElementByAttributeValue(
				ATTRIBUTE_NAME, cName);

		Element span = writer.getDocument().getElementById(ELEMENT_ID);

		if (span != null && btn != null) {
			span.moveToBottom(btn);
		}
	}
}