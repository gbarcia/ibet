package ve.edu.ucab.ibet.generic.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NullCipher;
import org.apache.commons.codec.binary.Base64;

/**
 * Clase que reune metodos utilitarios a usar en el proyecto
 * @author Gerardo Barcia
 * @version 1.0
 */
public abstract class UtilMethods {

    private static final java.text.SimpleDateFormat GOOGLE_DATETIME_TO_HTML = new java.text.SimpleDateFormat("dd/MM/yyyy");
    private static final java.text.SimpleDateFormat HTML_DB_TO_DATE = new java.text.SimpleDateFormat("yyyy-MM-dd H:mm:ss.S");

    /**
     * Metodo que extrae el año de una fecha.
     * @param fecha Date
     * @return Integer
     * **/
    public static Integer extraerAnio(Date fecha) {
        Calendar cFecha = Calendar.getInstance();
        cFecha.setTimeInMillis(fecha.getTime());
        return cFecha.get(Calendar.YEAR);
    }
    /**
     * Metodo que convierte una fecha de cadena en un objeto Date
     * en el formato yyyy-MM-dd
     * @param fecha el objeto String de la fecha a convertir
     * @return Objeto tipo Date en formatio yyyy-MM-dd
     */
    public static Date stringToFecha(String fecha) {
        try {
            java.text.SimpleDateFormat formatoFecha = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = formatoFecha.parse(fecha);
            return date;
        } catch (ParseException ex) {
            return null;
        }
    }
    /**
     * Metodo que convierte un string en un objeto tipo Date en formato
     * yyyy-MMM-dd
     * @param fecha objeto Date a convertir en cadena
     * @return cadena de String en formato yyyy-MM-dd
     */
    public static String fechaToString(Date fecha) {

        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleFormat.format(fecha);
        return date;
    }

    /**
     * Meotodo para convertir una fecha en el formato yyyy-MM-dd
     * @param fecha Fecha a dar formato
     * @return Date en formato yyyy-MM-dd
     */
    public static Date convertirFechaFormato (Date fecha) {
        String fechaCadena = fechaToString(fecha);
        Date fechaDate = stringToFecha(fechaCadena);
        return fechaDate;
    }

    /**
     * Metodo que extrae el mes de una fecha.
     * @param fecha Date
     * @return Integer
     * **/
    public static Integer extraerMes(Date fecha) {
        Calendar cFecha = Calendar.getInstance();
        cFecha.setTimeInMillis(fecha.getTime());
        return (cFecha.get(Calendar.MONTH) + 1);
    }

    /**
     * Metodo que extrae el dia de una fecha.
     * @param fecha Date
     * @return Integer
     * **/
    public static Integer extraerDia(Date fecha) {
        Calendar cFecha = Calendar.getInstance();
        cFecha.setTimeInMillis(fecha.getTime());
        return cFecha.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Metodo que compara los meses de dos fechas.
     * @param fecha1 Date
     * @param fecha2 Date
     * @return Integer
     * **/
    public static Integer compararMeses(Date fecha1, Date fecha2) {
        Calendar cFecha1 = Calendar.getInstance();
        Calendar cFecha2 = Calendar.getInstance();
        cFecha1.setTimeInMillis(fecha1.getTime());
        cFecha2.setTimeInMillis(fecha2.getTime());
        Integer mes1 = cFecha1.get(Calendar.MONTH);
        Integer mes2 = cFecha2.get(Calendar.MONTH);
        return mes1.compareTo(mes2);
    }

    /**
     * Metodo que busca la misma fecha del mes siguiente.
     * @param fecha Date
     * @return Date
     * **/
    public static Date fechaMesSiguiente(Date fecha) {
        Calendar cFecha = Calendar.getInstance();
        cFecha.setTimeInMillis(fecha.getTime());
        cFecha.add(Calendar.MONTH, 1);
        return new Date(cFecha.getTimeInMillis());
    }

    /**
     * Metodo que coloca en 01 el dia de una fecha.
     * @param fecha Date
     * @return Date
     * **/
    public static Date primeroDelMes(Date fecha) {
        Calendar cFecha = Calendar.getInstance();
        cFecha.setTimeInMillis(fecha.getTime());
        cFecha.set(Calendar.DAY_OF_MONTH, 1);
        return new Date(cFecha.getTimeInMillis());
    }

    /**
     * Metodo que compara dos fechas
     * @param fecha Date
     * @return Date
     * **/
    public static boolean fechaEsMayor(Calendar fecha) {
        //se crea un objeto calendario con la fecha actual.
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTime(new java.util.Date());


        boolean mayor = true;
        if (fecha.equals(fechaActual) || fecha.before(fechaActual))//Si son iguales o es anterior
        {
            mayor = false;
        } else if (fecha.after(fechaActual))//Si la primera es posterior
        {
            mayor = true;
        }

        return mayor;
    }

    /**
     * Metodo que dada una fecha retorna un entero q indica el dia de semana en que cayo.
     * 1 sunday - domingo
     * 2 monday - lunes
     * 3 tuesday - martes
     * 4 wednesday - miercoles
     * 5 thursday - jueves
     * 6 friday - viernes
     * 7 saturday - sabado
     * @param fecha Date
     * @return int
     * **/
    public static int diaSemana(Date fecha) {
        Calendar calen = Calendar.getInstance();
        calen.setTime(fecha);

        int diaSemana = calen.get(Calendar.DAY_OF_WEEK);
        return diaSemana;
    }

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

    /**
     * Toma una fecha y retorna un string con la fecha formateada como
     * DD/MM/YYYY
     * @param x Fecha a formatear
     * @return
     */
    public static final String dateToGoogleDate(java.util.Date x) {
        if (x == null) {
            return "";
        }
        return GOOGLE_DATETIME_TO_HTML.format(x);
    }

    public static final String dateToHTMLDBDate(java.util.Date x) {
        if (x == null) {
            return "";
        }
        return HTML_DB_TO_DATE.format(x);
    }

    public static final boolean esCorreoValido(String email) {
        if (email == null) {
            return false;
        }

        return java.util.regex.Pattern.matches("^[\\w-\\.]{1,}\\@([\\da-zA-Z-]{1,}\\.){1,}[\\da-zA-Z-]{2,4}$", email);
    }

    /**
     * @param object <b>Variable de tipo String</b>
     * @return Funcion que devuelve un booleano que indica si el contenido
     *         dentro del objeto String es numerico.
     */
    public static boolean esNumerico(String object) {
        try {
            Integer.parseInt(object);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @param object <b>Variable de tipo String</b>
     * @return Funcion que devuelve un booleano que indica si el contenido
     *         dentro del objeto String es numerico doble.
     */
    public static boolean esNumericoDouble(String object) {
        try {
            Double.parseDouble(object);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esNumeroCedula(String object) {
        if (object == null) {
            return false;
        }

        return java.util.regex.Pattern.matches("\\d{6,8}", object);
    }

    /**
     * @param object <b>Variable de tipo String</b>
     * @return Funcion que devuelve un booleano que indica si un combo fue seleccionado.
     * */
    public static boolean esSeleccionadoCombo(int object) {
        return (object != -1);
    }

    /**
     * @param object <b>Variable de tipo String</b>
     * @return Funcion que devuelve un booleano que indica si un combo fue seleccionado.
     * */
    public static boolean esSeleccionadoCombo(long object) {
        return (object != -1);
    }

    /**
     * @param obejct <b>Variable de tipo String</b>
     * @return Funcion que valida que una cadena no tenga caracteres extra#os.
     * */
    public static boolean esCadenaValido(String object) {

        Pattern patron = Pattern.compile("[^0-9A-Za-z]");
        Matcher match = patron.matcher(object);

        return (match.find());
    }

    /**
     * Retorna una lista con el n#mero y nombre de los meses del a#o
     * Gregoriano (Enero a Diciembre). El n#mero del primer mes es 0
     * y del #ltimo es 11, es decir, 0=Enero, 1=Febrero, ..., 11=Diciembre.
     *
     * @return objeto de tipo <code>HashMap<Integer, String></code>
     *         con los meses del a#o.
     */
    public static Map<Integer, String> getMeses() {
        Map<Integer, String> hm = new TreeMap<Integer, String>();

        hm.put(0, "Enero");
        hm.put(1, "Febrero");
        hm.put(2, "Marzo");
        hm.put(3, "Abril");
        hm.put(4, "Mayo");
        hm.put(5, "Junio");
        hm.put(6, "Julio");
        hm.put(7, "Agosto");
        hm.put(8, "Septiembre");
        hm.put(9, "Octubre");
        hm.put(10, "Noviembre");
        hm.put(11, "Diciembre");

        return hm;
    }

    /**
     * Retorna el nombre del mes dado su n#mero.
     * El n#mero del primer mes es 0 y del #ltimo es 11, es decir,
     * 0=Enero, 1=Febrero, ..., 11=Diciembre.
     *
     * @param mes
     *        n#mero del mes.
     *
     * @return nombre del mes si 0 <= <code>mes</code> <= 11,
     *         cadena vac#a en caso contrario.
     */
    public static String getNombreMes(Integer mes) {

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        if (mes < 0 || mes > 11) {
            return "Undecimber";
        }
        return meses[mes];
    }

    /**
     * M#todo para llenar la lista de meses
     * @param object <b>Variable de tipo String</b>
     * @return Funcion que devuelve una lista de todos los meses.
     */
    public static List<String> getListaMeses() {
        List<String> listaMeses = new ArrayList<String>();

        listaMeses.add("Enero");
        listaMeses.add("Febrero");
        listaMeses.add("Marzo");
        listaMeses.add("Abril");
        listaMeses.add("Mayo");
        listaMeses.add("Junio");
        listaMeses.add("Julio");
        listaMeses.add("Agosto");
        listaMeses.add("Septiembre");
        listaMeses.add("Octubre");
        listaMeses.add("Noviembre");
        listaMeses.add("Diciembre");

        return listaMeses;
    }

    /**
     * Metodo que se encarga de obtener el numero del mes para llenar una variable de tipo Date
     * @param String mes
     * @return Integer
     */
    public static Integer getNumeroMes(String mes) {

        String s;

        if (mes != null) {
            s = mes.trim();

            if (s.equalsIgnoreCase("Enero")) {
                return 0;
            } else if (s.equalsIgnoreCase("Febrero")) {
                return 1;
            } else if (s.equalsIgnoreCase("Marzo")) {
                return 2;
            } else if (s.equalsIgnoreCase("Abril")) {
                return 3;
            } else if (s.equalsIgnoreCase("Mayo")) {
                return 4;
            } else if (s.equalsIgnoreCase("Junio")) {
                return 5;
            } else if (s.equalsIgnoreCase("Julio")) {
                return 6;
            } else if (s.equalsIgnoreCase("Agosto")) {
                return 7;
            } else if (s.equalsIgnoreCase("Septiembre")) {
                return 8;
            } else if (s.equalsIgnoreCase("Octubre")) {
                return 9;
            } else if (s.equalsIgnoreCase("Noviembre")) {
                return 10;
            } else if (s.equalsIgnoreCase("Diciembre")) {
                return 11;
            }
        }

        return 0;
    }

    /**
     * Metodo que se encarga de llenar una lista de dias segun el mes
     * @param String mes
     * @param String anio
     * @return Retorna una lista de dias de tipo Integer
     * **/
    public static List<Integer> getListaDias(String mes, String anio) {
        List<Integer> listaDias = new ArrayList<Integer>();
        Integer dia = 0;

        if (mes.equals("Enero") || mes.equals("Marzo") || mes.equals("Mayo") || mes.equals("Julio") || mes.equals("Agosto") || mes.equals("Octubre") || mes.equals("Diciembre")) {
            for (int i = 0; i < 31; i++) {
                dia++;
                listaDias.add(dia);
            }
        }
        if (mes.equals("Abril") || mes.equals("Junio") || mes.equals("Septiembre") || mes.equals("Noviembre")) {
            for (int i = 0; i < 30; i++) {
                dia++;
                listaDias.add(dia);
            }
        }
        if (mes.equals("Febrero")) {
            Integer anioMes = new Integer(anio);
            Boolean bisiesto = false;

            if (anioMes % 100 == 0) {
                if (anioMes % 400 == 0) {
                    bisiesto = true;
                } else {
                    bisiesto = false;
                }
            } else {
                if (anioMes % 4 == 0) {
                    bisiesto = true;
                } else {
                    bisiesto = false;

                }
            }

            if (bisiesto) {
                for (int i = 0; i < 29; i++) {
                    dia++;
                    listaDias.add(dia);
                }
            } else {
                for (int i = 0; i < 28; i++) {
                    dia++;
                    listaDias.add(dia);
                }
            }
        }
        return listaDias;
    }

    public static List<Integer> getListaAnios(int atras, int adelante) {
        List<Integer> listaAnios = new ArrayList<Integer>();
        Calendar c = Calendar.getInstance();
        int anioActual = c.get(Calendar.YEAR);
        int anio = 0;
        listaAnios.add(anioActual);

        anio = anioActual;
        for (int i = 0; i < atras; i++) {
            anio--;
            listaAnios.add(anio);
        }

        anio = anioActual;
        for (int i = 0; i < adelante; i++) {
            anio++;
            listaAnios.add(anio);
        }

        Collections.sort(listaAnios);

        return listaAnios;
    }

    /**
     * Retorna una fecha con formato dd/mm/yyyy.
     * @param date fecha a formatear.
     * @return fecha con formato dd/mm/yyyy si es una fecha v#lida,
     *         <code>null</code> en caso contrario.
     */
    public static String formatDate(java.sql.Date date) {
        try {
            return GOOGLE_DATETIME_TO_HTML.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retorna una representacion literal del objeto formateado como un n#mero
     * decimal. El formato es el asignado a Venezuela, es decir, se usa
     * el punto (.) como separador de miles, la coma (,) como separador
     * de decimales y dos d#gitos para la fracci#n decimal.
     *
     * @param obj
     *        el objeto a formatear.
     * @return string con formato si el objeto puede ser formateado,
     *         <code>null</code> en caso contrario.
     */
    public static String formatDecimal(Object obj) {
        return format(obj, "###,##0.00");
    }

    /**
     * Retorna una representacion literal del objeto formateado como un numero
     * entero. El formato es el asignado a Venezuela, es decir, se usa
     * el punto (.) como separador de miles.
     *
     * @param obj
     *        el objeto a formatear.
     * @return string con formato si el objeto puede ser formateado,
     *         <code>null</code> en caso contrario.
     */
    public static String formatInteger(Object obj) {
        return format(obj, "###,###");
    }

    /**
     * Retorna una representacion literal del objeto formateado con un
     * patr#n espec#fico.
     *
     * @param obj
     *        el objeto a formatear.
     * @param pattern
     *        patr#n del formato.
     * @return string con formato si el objeto puede ser formateado,
     *         <code>null</code> en caso contrario.
     */
    public static String format(Object obj, String pattern) {
        Locale loc = new Locale("es", "VE");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(loc);
        DecimalFormat decimal = new DecimalFormat(pattern, simbolos);

        try {
            return decimal.format(obj);
        } catch (Exception e) {
            return null;
        }
    }

    public static int obtenerUltimoDiaMes(String anio, String mes) {
        Calendar c = Calendar.getInstance();
        c.set(new Integer(anio), new Integer(mes) + 1, 1);
        int maxDate = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDate;
    }

    public static String obtenerFechaActual() {
        //Asigno la fecha del sistema como fecha de creacion del reporte
        Calendar cal = new GregorianCalendar();
        String sFecha = "Fecha de creación: " + cal.get(Calendar.DAY_OF_MONTH) + " de " + UtilMethods.getNombreMes(cal.get(Calendar.MONTH)) + " de " + cal.get(Calendar.YEAR);

        return sFecha;
    }

    public static String obtenerFechaActualEspanol() {
        //Asigno la fecha del sistema como fecha de creacion del reporte
        Calendar cal = new GregorianCalendar();
        String sFecha = cal.get(Calendar.DAY_OF_MONTH) + " de " + UtilMethods.getNombreMes(cal.get(Calendar.MONTH)) + " de " + cal.get(Calendar.YEAR);

        return sFecha;
    }

    public static Date obtenerfechaMasUno(Date fecha) {
        //Asigno la fecha del sistema como fecha de creacion del reporte
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);

        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date fecha2 = new Date(cal.getTimeInMillis());

        return fecha2;
    }

    public static String obtenerPeriodoEspanol(Date fecha) {
        //Asigno la fecha del sistema como fecha de creacion del reporte
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        String sFecha = UtilMethods.getNombreMes(cal.get(Calendar.MONTH)) + " " + cal.get(Calendar.YEAR);

        return sFecha;
    }

    public static String removerAcentos(String inputString) {
        String salida = inputString;
        salida = salida.replace('á', 'a');
        salida = salida.replace('é', 'e');
        salida = salida.replace('í', 'i');
        salida = salida.replace('ó', 'o');
        salida = salida.replace('ú', 'u');
        salida = salida.replace('ñ', 'n');
        salida = salida.replace('Á', 'A');
        salida = salida.replace('É', 'E');
        salida = salida.replace('Í', 'I');
        salida = salida.replace('Ó', 'O');
        salida = salida.replace('Ú', 'U');
        salida = salida.replace('Ñ', 'N');
        return salida;
    }

    public static String obtenerIniciales(String sToken) {
        String iniciales = "";
        StringTokenizer tokens = new StringTokenizer(sToken, " ");
        boolean primeraLetra = true;
        while (tokens.hasMoreTokens()) { // Mientras existan datos
            String palabra = tokens.nextToken(); //obtengo mes y año
            palabra = palabra.trim();
            palabra = palabra.toUpperCase();

            //Casos especiales
            if ((palabra.equalsIgnoreCase("DE"))) {
                continue;
            }

            if ((palabra.equalsIgnoreCase("IVSS"))) {
                if (primeraLetra) {
                    primeraLetra = false;
                    iniciales = palabra + " ";
                } else {
                    iniciales = iniciales + " " + palabra + " ";
                }
            } else {
                char caracter = palabra.charAt(0);
                if (!(Character.isDigit(caracter))) {
                    if (primeraLetra) {
                        primeraLetra = false;
                        iniciales = caracter + ".";
                    } else {
                        iniciales = iniciales + caracter + ".";
                    }
                }
            }
        }// End del While, no hay mas tokens
        return iniciales;
    }

    @SuppressWarnings("unsafe")
    public static String encrypt(String str) {
        Cipher ecipher = new NullCipher();
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return Base64.encodeBase64String(enc);

        } catch (BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        return null;
    }

    @SuppressWarnings("unsafe")
    public static String decrypt(String str) {
        Cipher dcipher = new NullCipher();
        try {

            // Decode base64 to get bytes
            byte[] dec = Base64.decodeBase64(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");

        } catch (BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        return null;
    }
}
