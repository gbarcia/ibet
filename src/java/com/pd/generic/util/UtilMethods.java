/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.generic.util;

/**
 *
 * @author nath
 */
public abstract class UtilMethods {
    public static final boolean isSet(String x) {
		if (x == null) {
			return false;
		}

		x = x.toLowerCase();

		if (x.indexOf("null") > -1) {
			x = x.replaceAll("null", "");
		}

		return (x.trim().length() > 0);
	}

	public static final boolean isSet(java.util.Date x) {
		return ((x != null) && (x.getTime() > 0));
	}

	public static final boolean isSet(java.sql.Date x) {
		return (x != null);
	}

	public static final boolean isSet(Float x) {
		return (x != null);
	}

	public static final boolean isSet(Object x) {
		return (x != null);
	}
}
