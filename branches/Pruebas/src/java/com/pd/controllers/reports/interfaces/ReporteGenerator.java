/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.controllers.reports.interfaces;

import com.pd.generic.excepciones.GeneralException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author nath
 */
public interface ReporteGenerator {

    public ModelAndView generarReportePDF(HttpServletRequest request,
            HttpServletResponse response) throws GeneralException;

    public ModelAndView generarReporteXLS(HttpServletRequest request,
            HttpServletResponse response) throws GeneralException;

    public Map getModel() throws GeneralException;

    public List getData() throws GeneralException;
}
