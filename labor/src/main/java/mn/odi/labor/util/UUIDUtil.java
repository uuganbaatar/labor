/* 
 * Package Name: mn.odi.scc.util
 * File Name: UUIDUtil.java
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

import com.activescript.GUID.GUIDException;
import com.activescript.GUID.GUIDGenerator;

public class UUIDUtil {
	// ~ Methods
	// ================================================================
	public static String getUUID() {
		String uuid = null;
		try {
			GUIDGenerator gen = new GUIDGenerator();
			uuid = gen.getUnformatedUUID();
		} catch (GUIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uuid;
	}
}
