/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.generic.util.HelperProperties;

import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
/**
 *
 * @author nath
 */
public class HelperProperties {

    private final String PAQUETE_RESOURCE = "com.pd.conf.conf";

        ResourceBundle resourceBundle = ResourceBundle.getBundle(PAQUETE_RESOURCE);

   public String getString (String key) {
       return resourceBundle.getString(key);
   }

    public String getString(String key, List<String> params) {

        String cadena = resourceBundle.getString(key);
		    for (int i = 0; i < params.size(); i++) {
		 	   cadena = cadena.replace("{"+String.valueOf(i)+"}", params.get(i));
		    }
		    return cadena;
    }

}
