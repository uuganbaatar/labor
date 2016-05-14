/* 
 * Package Name: mn.odi.scc.components
 * File Name: BreadcrumbComponent.java
 * 
 * Created By: Tserentogtokh.D
 * Created Date: 2014/04/15
 * 
 * History
 * ------------------------------------------------------------------------------
 * Date							Programmer						Description
 * ------------------------------------------------------------------------------
 * 2014/04/15 1.0.0 			Tserentogtokh.D				    Шинээр үүсгэв.
 * ------------------------------------------------------------------------------
 * 
 * ALL RIGHTS RESERVED COPYRIGHT (C) 2014 Od Innovation CO.,LTD SOFTWARE DIVSION
*/
package mn.odi.labor.components;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import se.unbound.tapestry.breadcrumbs.BreadCrumbInfo;
import se.unbound.tapestry.breadcrumbs.BreadCrumbList;

/**
 * @author Tserentogtokh
 *
 */
public class BreadcrumbComponent {

	@SessionState
	private BreadCrumbList breadCrumbList;
	
	@Property
	private BreadCrumbInfo breadCrumb;
	
}