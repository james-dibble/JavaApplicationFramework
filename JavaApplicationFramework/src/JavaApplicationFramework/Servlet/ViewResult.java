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

public class ViewResult implements IActionResult
{
    private final Object _model;
    private final String _view;
    
    public ViewResult(String view)
    {
        this(view, null);
    }
    
    public ViewResult(String view, Object model)
    {
        this._view = view;
        this._model = model;
    }
    
    @Override
    public void DoAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("model", this._model);
        request.getRequestDispatcher(this._view).forward(request, response);
    }

}
