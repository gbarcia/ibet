package ve.edu.ucab.ibet.generic.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.util.TipoOperadorBusqueda;
import ve.edu.ucab.ibet.generic.util.UtilMethods;

/**
 * Clase para implementar el dao generico
 * @author Gerardo Barcia
 * @version 1.0
 */
public class GenericDao extends HibernateDaoSupport implements IGenericDao {

    public GenericDao() {
        //System.out.println("Creando un nuevo DAO");
    }

    public List findByProperties(Class c, String[] propertyNames,
            Object[] propertyValues, Integer[] operators) {
        return findByProperties(c, propertyNames, propertyValues, operators, -1, -1, null);
    }

    public List findByProperties(Class c, String[] propertyNames,
            Object[] propertyValues, Integer[] operators, List<String> orderAtts) {
        return findByProperties(c, propertyNames, propertyValues, operators, -1, -1, orderAtts);
    }

    public List findByProperties(Class c, String[] propertyNames,
            Object[] propertyValues, Integer[] operators, int start, int offset) {
        return findByProperties(c, propertyNames, propertyValues, operators, start, offset, null);
    }

    public List findByProperties(Class c, String[] propertyNames, Object[] propertyValues, Integer[] operators,
            int start, int offset,
            List<String> orderAtts) {
        Criteria criteria = getSession().createCriteria(c);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        int i;
        int j = 0;
        for (i = 0; i < propertyNames.length; i++) {
            switch (operators[i]) {
                case TipoOperadorBusqueda.LIKE:
                    criteria.add(Restrictions.ilike(propertyNames[i], propertyValues[j].toString(), MatchMode.ANYWHERE));
                    break;
                case TipoOperadorBusqueda.EQUAL:
                    criteria.add(Restrictions.eq(propertyNames[i], propertyValues[j]));
                    break;
                case TipoOperadorBusqueda.GREATER_THAN:
                    criteria.add(Restrictions.gt(propertyNames[i], propertyValues[j]));
                    break;
                case TipoOperadorBusqueda.LESS_THAN:
                    criteria.add(Restrictions.lt(propertyNames[i], propertyValues[j]));
                    break;
                case TipoOperadorBusqueda.GREATER_EQUAL:
                    criteria.add(Restrictions.ge(propertyNames[i], propertyValues[j]));
                    break;
                case TipoOperadorBusqueda.LESS_EQUAL:
                    criteria.add(Restrictions.le(propertyNames[i], propertyValues[j]));
                    break;
                case TipoOperadorBusqueda.NOT_EQUAL:
                    criteria.add(Restrictions.ne(propertyNames[i], propertyValues[j]));
                    break;
                case TipoOperadorBusqueda.BETWEEN:
                    criteria.add(Restrictions.and(Restrictions.ge(propertyNames[i],
                            propertyValues[j++]), Restrictions.le(propertyNames[i],
                            propertyValues[j])));
                    i++;
                    break;
                case TipoOperadorBusqueda.IS_NULL:
                    criteria.add(Restrictions.isNull(propertyNames[i++]));
                    break;
                case TipoOperadorBusqueda.IS_NOT_NULL:
                    criteria.add(Restrictions.isNotNull(propertyNames[i++]));
                    break;
                default:
            }
            j++;
        }

        if (start >= 0 && offset > 0) {
            criteria.setFirstResult(start).setMaxResults(offset);
        }

        if (UtilMethods.isSet(orderAtts)) {
            for (String attribute : orderAtts) {
                criteria.addOrder(Order.asc(attribute));
            }
        }
        List result = criteria.list();

        return result;
    }

    /**
     * Funcion que devuelve una lista de registros segun ciertos parametros.
     * @param nombrePojo Nombre del tipo de pogo que se almacenara en la lista de resultado.
     * @param propertyNames Arreglo de atributos que estan involucrados en la busqueda.
     * @param propertyValues Arreglo de valores que deben tener los atributos.
     * @param operators Arreglo de operadores logicos que definen la condicion entre los atributos y los valores.
     * @param logicalOperator tipo de operador logico con el que se enlazara las condiciones de la busqueda.
     * @return Una lista de registros.
     * **/
    public List findByProperties(String nombrePojo, String[] propertyNames,
            Object[] propertyValues, int[] operators, String logicalOperator) {
        return findByProperties(nombrePojo, propertyNames, propertyValues, operators, logicalOperator, null, null);
    }

    public List findByProperties(String nombrePojo, String[] propertyNames,
            Object[] propertyValues, int[] operators, String logicalOperator,
            List<String> orderAtts) {
        return findByProperties(nombrePojo, propertyNames, propertyValues, operators, logicalOperator, orderAtts, null);
    }

    public List findByProperties(String nombrePojo, String[] propertyNames,
            Object[] propertyValues, int[] operators, String logicalOperator,
            List<String> orderAtts, Integer statusId) {
        try {

            String queryString = "from " + nombrePojo + " as model where (";

            int numPropertyNames = propertyNames.length;

            for (int i = 0; i < numPropertyNames; i++) {

                String name = propertyNames[i];
                Object value = propertyValues[i];
                int operator = operators[i];

                if (value instanceof String) {

                    if (operator == TipoOperadorBusqueda.LIKE) {

                        queryString = queryString + " lower(model." + name + ") LIKE ? ";
                        String temp = "%" + value.toString().toLowerCase() + "%";
                        propertyValues[i] = temp;
                    } else {
                        //Por defecto busca con el operador igual..
                        queryString = queryString + " model." + name + " " + operator + " ? ";
                    }
                } else {
                    //es Integer o Double, OJO: verificar el Double..
                    queryString = queryString + " model." + name + " " + operator + " ? ";
                }
                if ((i + 1) < numPropertyNames) {
                    //hay mas de uno..
                    queryString = queryString + logicalOperator;
                }
            }
            queryString = queryString + ") ";
            if (statusId != null) {
                queryString += "AND model.status.id = " + statusId;
            }
            if (orderAtts != null && orderAtts.size() > 0) {
                queryString += " ORDER BY ";
                int size = orderAtts.size();
                for (int i = 0; i < size; i++) {
                    String attribute = orderAtts.get(i);
                    queryString = queryString + " model." + attribute;
                    if (i < (size - 1)) {
                        queryString = queryString + ",";
                    }
                }
                queryString += " ASC";
            }
            return getHibernateTemplate().find(queryString, propertyValues);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public void insertar(Object obj) {
        getHibernateTemplate().save(obj);
    }

    public Object findByPropertyUnique(Class c, String propertyName, Object propertyValue) {
        try {
            Criteria criteria = getSession().createCriteria(c);
            criteria.add((Restrictions.eq(propertyName, propertyValue)));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            Object result = criteria.uniqueResult();

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List findByProperty(Class c, String propertyName,
            Object propertyValue) {
        return findByProperty(c, propertyName, propertyValue, null, -1, -1);
    }

    public List findByProperty(Class c, String propertyName,
            Object propertyValue, List<String> orderAtts) {
        return findByProperty(c, propertyName, propertyValue, orderAtts, -1, -1);
    }

    public List findByProperty(Class c, String propertyName,
            Object propertyValue, int start, int offset) {
        return findByProperty(c, propertyName, propertyValue, null, start, offset);
    }

    public List findByProperty(Class c, String propertyName,
            Object propertyValue, List<String> orderAtts, int start, int offset) {
        try {
            Criteria criteria = getSession().createCriteria(c);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add((Restrictions.eq(propertyName, propertyValue)));
            if (start >= 0 && offset >= 0) {
                criteria.setFirstResult(start);
                criteria.setMaxResults(offset);
            }
            if (orderAtts != null && orderAtts.size() > 0) {
                for (String attribute : orderAtts) {
                    criteria.addOrder(Order.asc(attribute));
                }
            }
            List result = criteria.list();

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminar(Object o) {
        getHibernateTemplate().delete(o);
    }

    public List listar(Class c, int start, int offset) {
        return listar(c, null, start, offset);
    }

    public List listar(Class c) {
        return listar(c, null, -1, -1);
    }

    public List listar(Class c, List<String> orderAtts) {
        return listar(c, orderAtts, -1, -1);
    }

    public List listar(Class c, List<String> orderAtts, int start, int offset) {
        Criteria crit = getSession().createCriteria(c);
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (start >= 0 && offset >= 0) {
            crit = crit.setFirstResult(start).setMaxResults(offset);
        }

        if (UtilMethods.isSet(orderAtts) && orderAtts.size() > 0) {
            for (String attribute : orderAtts) {
                if (attribute.indexOf(".") != -1) {
                    String att = attribute.substring(attribute.lastIndexOf(".") + 1);
                    String prop = attribute.substring(0, attribute.indexOf("."));
                    crit = crit.createCriteria(prop);
                    crit.addOrder(Order.asc(att));
                } else {
                    crit.addOrder(Order.asc(attribute));
                }
            }
        }
        List result = crit.list();

        return result;
    }

    public void limpiar() {
        getHibernateTemplate().clear();
    }

    public void modificar(Object o) {
        getHibernateTemplate().update(o);
    }

    public int countAll(Class c) {
        Criteria crit = getSession().createCriteria(c);
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        crit.setProjection(Projections.countDistinct("id"));
        Integer result = new Integer((Integer) crit.uniqueResult());

        return result;
    }

    public int countByProperties(Class c, String[] propertyNames,
            Object[] propertyValues, Integer[] operators) {
        Criteria criteria = getSession().createCriteria(c);
        criteria.setProjection(Projections.countDistinct("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        int i;
        int j = 0;
        for (i = 0; i < propertyNames.length; i++) {
            switch (operators[i]) {
                case TipoOperadorBusqueda.LIKE:
                    criteria.add(Restrictions.ilike(propertyNames[i], propertyValues[j].toString(), MatchMode.ANYWHERE));
                    break;
                case TipoOperadorBusqueda.EQUAL:
                    criteria.add(Restrictions.eq(propertyNames[i], propertyValues[j]));
                    break;
                case TipoOperadorBusqueda.GREATER_THAN:
                    criteria.add(Restrictions.gt(propertyNames[i], propertyValues[j]));
                    break;
                case TipoOperadorBusqueda.LESS_THAN:
                    criteria.add(Restrictions.lt(propertyNames[i], propertyValues[j]));
                    break;
                case TipoOperadorBusqueda.GREATER_EQUAL:
                    criteria.add(Restrictions.ge(propertyNames[i], propertyValues[j]));
                    break;
                case TipoOperadorBusqueda.LESS_EQUAL:
                    criteria.add(Restrictions.le(propertyNames[i], propertyValues[j]));
                    break;
                case TipoOperadorBusqueda.NOT_EQUAL:
                    criteria.add(Restrictions.ne(propertyNames[i], propertyValues[j]));
                    break;
                case TipoOperadorBusqueda.BETWEEN:
                    criteria.add(Restrictions.and(Restrictions.ge(propertyNames[i],
                            propertyValues[j++]), Restrictions.le(propertyNames[i],
                            propertyValues[j])));
                    i++;
                    break;
                case TipoOperadorBusqueda.IS_NULL:
                    criteria.add(Restrictions.isNull(propertyNames[i++]));
                    break;
                case TipoOperadorBusqueda.IS_NOT_NULL:
                    criteria.add(Restrictions.isNotNull(propertyNames[i++]));
                    break;
                default:
            }
            j++;
        }
        Integer result = new Integer((Integer) criteria.uniqueResult());

        return result;
    }

    public int countByProperties(String nombrePojo, String[] propertyNames,
            Object[] propertyValues, int[] operators, String logicalOperator, Integer statusId) {
        try {

            String queryString = "Select count(model.id) from " + nombrePojo + " as model where (";

            int numPropertyNames = propertyNames.length;

            for (int i = 0; i < numPropertyNames; i++) {

                String name = propertyNames[i];
                Object value = propertyValues[i];
                int operator = operators[i];

                if (value instanceof String) {

                    if (operator == TipoOperadorBusqueda.LIKE) {

                        queryString = queryString + " lower(model." + name + ") LIKE ? ";
                        String temp = "%" + value.toString().toLowerCase() + "%";
                        propertyValues[i] = temp;
                    } else {
                        //Por defecto busca con el operador igual..
                        queryString = queryString + " model." + name + " " + operator + " ? ";
                    }
                } else {
                    //es Integer o Double, OJO: verificar el Double..
                    queryString = queryString + " model." + name + " " + operator + " ? ";
                }
                if ((i + 1) < numPropertyNames) {
                    //hay mas de uno..
                    queryString = queryString + logicalOperator;
                }
            }
            queryString = queryString + ") ";
            if (statusId != null) {
                queryString += "AND model.status.id = " + statusId;
            }
            List listaResultados = getHibernateTemplate().find(queryString, propertyValues);
            Integer result = null;
            if (listaResultados == null) {
                result = 0;
            } else {
                result = new Integer(listaResultados.get(0).toString());
            }

            return result;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public int countByQueryString(String queryString, Object[] values) {
        Query query = getSession().createQuery(queryString);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return new Integer(query.uniqueResult().toString());
    }

    public List ejecutarQueryList(String queryString) {
        Query query = getSession().createQuery(queryString);
        return query.list();
    }

    public List ejecutarQueryList(String queryString, Object[] values) {
        return ejecutarQueryList(queryString, values, -1, -1);
    }

    public List ejecutarQueryList(String queryString, Object[] values,
            int start, int offset) {
        Query query = getSession().createQuery(queryString);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        if (start >= 0 && offset >= 0) {
            query.setFirstResult(start);
            query.setMaxResults(offset);
        }
        return query.list();
    }

    public void eliminarTodos(Collection lista) {
        getHibernateTemplate().deleteAll(lista);
    }

    public void evict(Object obj) {
        getHibernateTemplate().evict(obj);
    }

    public void merge(Object obj) {
        getHibernateTemplate().merge(obj);
    }

    public void persistir(Object obj) {
        getHibernateTemplate().persist(obj);
    }
    @SuppressWarnings("unchecked")
    public Integer getNextId(Object obj) {
        List<Object> listaObjetos = new ArrayList<Object>();
        Integer returnValue = 0;
        String queryId = "select max(x.id) from " + obj.getClass().getSimpleName() + " x";
        listaObjetos.addAll(this.ejecutarQueryList(queryId, new Object[0]));
        if (listaObjetos.size() > 0) {
            if (listaObjetos.get(0) != null) {
                returnValue = new Integer(listaObjetos.get(0).toString()) + 1;
            } else {
                returnValue = 1;
            }
        } else {
            returnValue = 1;
        }
        return returnValue;
    }

    public void sincronizarEstadoBD() {
        getSession().flush();
    }

    public void refrescar(Object obj) {
        getSession().refresh(obj);
    }

    public List ejecutarSQLQuery(String query) {
        SQLQuery sql = getSession().createSQLQuery(query);
        return sql.list();
    }

    public void ejecturarSQLQueryManipulacion(String query) {
        SQLQuery sql = getSession().createSQLQuery(query);
        sql.executeUpdate();
    }

    public Object ejecutarQueryUnique(String queryString, Object[] values) {
        Query query = getSession().createQuery(queryString);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query.uniqueResult();
    }

}