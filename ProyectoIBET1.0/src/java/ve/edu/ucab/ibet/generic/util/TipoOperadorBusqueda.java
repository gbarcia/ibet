package ve.edu.ucab.ibet.generic.util;

/**
 * Clase para representar constantes a usar en el generic dao
 * para operadores SQL
 * @author GerardoBarcia
 * @version 1.0
 */
public abstract class TipoOperadorBusqueda {

    public static final int LIKE = 1;
    public static final int EQUAL = 2;
    public static final int LESS_THAN = 3;
    public static final int GREATER_THAN = 4;
    public static final int GREATER_EQUAL = 5;
    public static final int LESS_EQUAL = 6;
    public static final int NOT_EQUAL = 7;
    public static final int BETWEEN = 8;
    public static final int IS_NULL = 9;
    public static final int IS_NOT_NULL = 10;
}
