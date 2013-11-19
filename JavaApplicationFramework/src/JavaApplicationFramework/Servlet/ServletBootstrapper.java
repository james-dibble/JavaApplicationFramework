// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ServletBootstrapper.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public abstract class ServletBootstrapper implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        ServletContext context = sce.getServletContext();
        
        ActionFilterCollection actionFilters = new ActionFilterCollection();
        actionFilters.add(new AnnotationFilter());
        actionFilters.add(new MethodFilter());
        
        this.Bind(actionFilters, context);
        
        this.InitContext(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
    }
    
    protected abstract void InitContext(ServletContextEvent sce);
    
    protected void Bind(Object objectToBind, Class bindingClass, ServletContext context)
    {
        if(objectToBind.getClass().isAssignableFrom(bindingClass))
        {
            throw new UnsupportedOperationException("Cannot bind an object to class it does not inherit.");
        }
        
        this.Bind(objectToBind, bindingClass.getName(), context);
    }
    
    /**
     * Add a singleton object to the servlet context to be resolved by it's type name.
     * 
     * @param objectToBind The instance to use for it's type.
     * @param context The context to add the binding too.
     */
    protected void Bind(Object objectToBind, ServletContext context)
    {
        this.Bind(objectToBind, objectToBind.getClass().getName(), context);
    }
    
    /**
     * Add a singleton object to the servlet context to be resolved by an identifier.
     * 
     * @param objectToBind The instance to use for it's type.
     * @param bindingFlag The identifier for this object.
     * @param context The context to add the binding too.
     */
    protected void Bind(Object objectToBind, String bindingFlag, ServletContext context)
    {
        context.setAttribute(bindingFlag, objectToBind);
    }
}
