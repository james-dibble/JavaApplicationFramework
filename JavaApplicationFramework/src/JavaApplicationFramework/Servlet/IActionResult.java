// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ActionResult.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IActionResult 
{    
    void DoAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
