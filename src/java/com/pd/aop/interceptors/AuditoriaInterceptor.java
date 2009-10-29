/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.aop.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 *
 * @author nath
 */
public class AuditoriaInterceptor implements MethodInterceptor {

    private Logger log = Logger.getLogger(getClass());

    public Object invoke(MethodInvocation metodo) throws Throwable {
        final long startTime = System.currentTimeMillis();
        try {
            log.info("Se va a ejecutar el metodo: " + metodo.toString() + "de la clase: "
                    + metodo.getClass().getName());
            return metodo.proceed();
        } finally {
            // generamos la lista de argumentos que recibe el metodo separados por una coma
            String arguments = new String();
            for (int i = 0; i < metodo.getArguments().length; i++) {
                arguments += metodo.getArguments()[i] + " ,";
            }

            // el metodo recibe al menos un argumento quitamos el espacio y la coma del final
            if (arguments.length() > 0) {
                arguments = arguments.substring(0, arguments.length() - 2);
            }
            System.out.println("auditoria interceptor");
            log.info("[ " + (System.currentTimeMillis() - startTime) + " ms" + " ] " +
                    metodo.getMethod().getDeclaringClass().getSimpleName() + "." +
                    metodo.getMethod().getName() + "(" +
                    arguments + ")");
        }
    }
}
