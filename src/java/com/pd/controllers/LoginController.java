/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.controllers;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author nath
 */
public class LoginController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
            throws Exception {
        String now = (new Date().toString());
        return new ModelAndView("login","now",now);
    }

}
