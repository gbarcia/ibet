package ve.edu.ucab.ibet.generic.dao.interfaces;

import java.util.Collection;
import java.util.List;

/**
 * Interfaz para trabajar con el dao generico
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IGenericDao {

    /*
     * Metodos para listar sin condiciones de busqueda
     */
    public List listar(Class c);

    public List listar(Class c, int start, int offset);

    public List listar(Class c, List<String> orderAtts);

    public List listar(Class c, List<String> orderAtts, int start, int offset);
    /*
     * Metodos CRUD
     */

    public void modificar(Object o);

    public void eliminar(Object o);

    public void insertar(Object o);

    public void eliminarTodos(Collection lista);

    /*
     * Metodos para buscar/listar con condiciones de busqueda
     */
    public Object findByPropertyUnique(Class c, String propertyName, Object propertyValue);

    public List findByProperty(Class c, String propertyName, Object propertyValue);

    public List findByProperty(Class c, String propertyName, Object propertyValue, int start, int offset);

    public List findByProperty(Class c, String propertyName, Object propertyValue, List<String> orderAtts);

    public List findByProperty(Class c, String propertyName, Object propertyValue, List<String> orderAtts, int start, int offset);

    public List findByProperties(Class c, String[] propertyNames, Object[] propertyValues, Integer[] operators);

    public List findByProperties(Class c, String[] propertyNames, Object[] propertyValues, Integer[] operators, List<String> orderAtts);

    public List findByProperties(Class c, String[] propertyNames, Object[] propertyValues, Integer[] operators, int start, int offset);

    public List findByProperties(Class c, String[] propertyNames, Object[] propertyValues,
            Integer[] operators, int start, int offset, List<String> orderAtts);

    public List findByProperties(String nombrePojo, String[] propertyNames, Object[] propertyValues, int[] operators, String logicalOperator);

    public List findByProperties(String nombrePojo, String[] propertyNames, Object[] propertyValues, int[] operators,
            String logicalOperator, List<String> orderAtts);

    public List findByProperties(String nombrePojo, String[] propertyNames, Object[] propertyValues, int[] operators,
            String logicalOperator, List<String> orderAtts, Integer statusId);

    /*
     * Metodos para contar elementos
     */
    public int countAll(Class c);

    public int countByProperties(Class c, String[] propertyNames, Object[] propertyValues, Integer[] operators);

    public int countByProperties(String nombrePojo, String[] propertyNames, Object[] propertyValues, int[] operators, String logicalOperator, Integer statusId);

    public int countByQueryString(String queryString, Object[] values);
    /*
     * Metodos con QueryString
     */

    public List ejecutarQueryList(String queryString);

    public List ejecutarQueryList(String queryString, Object[] values);

    public List ejecutarQueryList(String queryString, Object[] values, int start, int offset);

    public Object ejecutarQueryUnique (String queryString, Object[] values);

    /*
     * Metodos Especiales
     */
    public void limpiar();

    public void merge(Object obj);

    public void evict(Object obj);

    public void persistir(Object obj);

    public Integer getNextId(Object obj);

    public void sincronizarEstadoBD();

    public void refrescar(Object obj);

    public List ejecutarSQLQuery(String query);

    public void ejecturarSQLQueryManipulacion (String query);
}
