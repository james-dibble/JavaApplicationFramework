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

/**
 * An IActionResult that can be created to send the response as a different action.
 */
public class RedirectToAction implements IActionResult
{
    private final String _action;
    
    /**
     * Initialises a new instance of the RedirectToAction class.
     * @param action The path of the action to response with.
     */
    public RedirectToAction(String action)
    {
        this._action = action;
    }

    /**
     * Execute the response.
     * @param request The request.
     * @param response The response.
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void DoAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.sendRedirect(request.getContextPath().concat(this._action));
    }
}