// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ConnectionFactory.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * * Implementing classes define methods for creating JDBC Connections
 */
public class ConnectionFactory implements IConnectionFactory
{
    /**
     * Create a connection to the specified database.
     *
     * @param connectionString
     * @return
     */
    @Override
    public Connection CreateConnection(String connectionString, String userName, String password) throws SQLException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(connectionString, userName, password);
            return connection;
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
