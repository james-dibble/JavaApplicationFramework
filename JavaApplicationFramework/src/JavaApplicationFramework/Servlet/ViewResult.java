// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ViewResult.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An IActionResult that creates a response that executes a view.
 */
public class ViewResult implements IActionResult
{
    private final Object _model;
    private final String _view;
    
    /**
     * Initialises a new instance of the ViewResult class.
     * @param view The path of the view to place into the response.
     */
    public ViewResult(String view)
    {
        this(view, null);
    }
    
    /**
     * Initialises a new instance of the ViewResult class.
     * @param view The path of the view to place into the response.
     * @param model The model for the given view to pull data from.
     */
    public ViewResult(String view, Object model)
    {
        this._view = view;
        this._model = model;
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
        request.setAttribute("model", this._model);
        request.getRequestDispatcher(this._view).forward(request, response);
    }

}
