// --------------------------------------------------------------------------------------------------------------------
// <copyright file="JsonResult.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * An IActionResult that creates a response transforming a model into JSON.
 */
public class JsonResult implements IActionResult
{
    private final Object _model;
    
    /**
     * Initialises a new instance of the JsonResult class.
     * @param model The object to place into the response in JSON form.
     */
    public JsonResult(Object model)
    {
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
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json");

        String json = new Gson().toJson(this._model);

        response.getWriter().write(json);
        response.getWriter().flush();
    }
}
