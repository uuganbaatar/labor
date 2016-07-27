package mn.odi.labor.components;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Import(library = { "context:js/charts/chart.js" })
public class Charts {

	@Inject
	private JavaScriptSupport jsSupport;

	@Property
	@Parameter(required = true)
	private Object chartData;

	@Property
	@Parameter(required = true)
	private String chartType;

	void setupRender() {

		jsSupport.addScript("chart", chartType, getData());

	}

	private Object getData() {
		return chartData;
	}

	public boolean isPie() {
		if (chartType.equals("pie"))
			return true;

		return false;
	}

	public boolean isBar() 
	{
		if (chartType.equals("bar"))
			return true;

		return false;
	}
	
	public boolean isArea() 
	{
		if (chartType.equals("area"))
			return true;

		return false;
	}

}
