// --------------------------------------------------------------------------------------------------------------------
// <copyright file="RedirectToAction.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectToAction implements IActionResult
{
    private final String _action;
    
    public RedirectToAction(String action)
    {
        this._action = action;
    }

    @Override
    public void DoAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.sendRedirect(request.getContextPath().concat(this._action));
    }
}