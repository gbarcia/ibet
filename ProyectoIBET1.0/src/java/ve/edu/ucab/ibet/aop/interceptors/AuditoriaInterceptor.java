package ve.edu.ucab.ibet.aop.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;

/**
 * Interceptor para la auditoria de los metodos con log4j
 * @author Gerardo Barcia
 * @version 1.0
 */
public class AuditoriaInterceptor implements MethodInterceptor, ThrowsAdvice {

    private Logger log = Logger.getLogger(getClass());

    public Object invoke(MethodInvocation metodo) throws Throwable {
        final long startTime = System.currentTimeMillis();
        try {
            log.info("Se va a ejecutar el metodo: " + metodo.toString() +
                    "de la clase: " + metodo.getClass().getName());
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
            log.info("[ " + (System.currentTimeMillis() - startTime) + " ms" + " ] " +
                    metodo.getMethod().getDeclaringClass().getSimpleName() + "." +
                    metodo.getMethod().getName() + "(" +
                    arguments + ")");
        }
    }

    public void afterThrowing(RuntimeException runtimeException) {
        ExcepcionNegocio exNegocio = null;
        String mensaje = runtimeException.getClass().toString() + " ==> ";
        if (runtimeException instanceof ExcepcionNegocio) {
            exNegocio = (ExcepcionNegocio) runtimeException;
            mensaje += exNegocio.getKeyError();
        } else {
            mensaje += runtimeException.getMessage();
            runtimeException.printStackTrace();
        }
        log.error(mensaje);
    }
}
