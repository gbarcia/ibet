package ve.edu.ucab.ibet.generic.util.helpers.impl;

import java.util.List;
import java.util.ResourceBundle;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;

/**
 * Implementacion del Helper para obtener datos del archivo
 * conf.properties de la aplicacion
 * @author Gerardo Barcia
 * @version 1.0
 */
public class HelperPropertiesImpl implements IHelperProperties {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("ve.edu.ucab.ibet.conf.conf");

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    public String getString(String key, List<String> params) {

        String cadena = resourceBundle.getString(key);
        for (int i = 0; i < params.size(); i++) {
            cadena = cadena.replace("{" + String.valueOf(i) + "}", params.get(i));
        }
        return cadena;
    }
}
