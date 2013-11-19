// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Controller.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import JavaApplicationFramework.Servlet.ActionAttribute.HttpMethod;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A base class for all controllers.
 */
public abstract class Controller extends HttpServlet
{
    private Iterable<IActionFilter> _filters;

    /**
     * Gets the base path of this controller.
     *
     * @return The base path of this controller.
     */
    protected abstract String ControllerPath();

    /**
     * Inject objects into controllers defined by annotations.
     */
    @Override
    public void init() throws ServletException
    {
        ServletContext context = this.getServletContext();

        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(InjectAttribute.class))
            {
                try
                {
                    field.setAccessible(true);
                    String className = field.getType().getName();
                    Object objectToInject = context.getAttribute(className);
                    field.set(this, objectToInject);
                }
                catch (IllegalArgumentException | IllegalAccessException ex)
                {
                    throw new ServletException(ex);
                }
            }
        }
    }

    /**
     * Process an HTTP GET request.
     *
     * @param request The request.
     * @param response The response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected final void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response, HttpMethod.GET);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
        {
            throw new ServletException(ex);
        }
    }

    /**
     * Process an HTTP POST request.
     *
     * @param request The request.
     * @param response The response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected final void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response, HttpMethod.POST);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
        {
            throw new ServletException(ex);
        }
    }

    /**
     * Retrieve an integer value from the request parameters.
     *
     * @param request The request.
     * @param param The parameter to find.
     * @return The parameter as an integer.
     */
    protected static int GetRequestParam(HttpServletRequest request, String param)
    {
        return Integer.parseInt(request.getParameter(param));
    }

    /**
     * Pull a named object from a session and cast it to it's original type.
     *
     * @param <T> The type of the object in the session.
     * @param session The session.
     * @param attribute The key for the object in the session.
     * @return The named object from the session.
     */
    protected static <T> T GetSessionAttribute(HttpSession session, String attribute)
    {
        Object sessionObject;

        if ((sessionObject = session.getAttribute(attribute)) != null)
        {
            return (T) sessionObject;
        }

        return null;
    }

    private Iterable<IActionFilter> GetFilters()
    {
        if (this._filters == null)
        {
            this._filters = (Iterable<IActionFilter>) this.getServletContext().getAttribute(ActionFilterCollection.class.getName());
        }

        return this._filters;
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod)
            throws ServletException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        String path = request.getServletPath().replace(this.ControllerPath(), "");

        Method[] methods = this.getClass().getMethods();

        for (IActionFilter filter : this.GetFilters())
        {
            for (Method method : methods)
            {
                if (filter.CanApplyAction(method, request, httpMethod, path))
                {
                    IActionResult result = filter.ApplyAction(this, method, request);

                    result.DoAction(request, response);
                    return;
                }

            }
        }
    }
}
