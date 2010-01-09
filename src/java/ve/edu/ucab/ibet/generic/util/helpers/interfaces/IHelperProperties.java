package ve.edu.ucab.ibet.generic.util.helpers.interfaces;

import java.util.List;

/**
 * Interfaz para obtener los datos del archivo conf de la aplicacion
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IHelperProperties {

    public String getString(String key);

    public String getString(String key, List<String> params);

}
