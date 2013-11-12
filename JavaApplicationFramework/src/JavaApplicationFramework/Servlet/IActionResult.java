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

/**
 * Implementing classes define how a controller action responds.
 */
public interface IActionResult 
{    
    /**
     * Execute the response.
     * @param request The request.
     * @param response The response.
     * @throws ServletException
     * @throws IOException 
     */
    void DoAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
