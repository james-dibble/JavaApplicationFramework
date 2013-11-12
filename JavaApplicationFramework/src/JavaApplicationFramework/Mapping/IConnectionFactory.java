// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IConnectionFactory.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementing classes define methods for creating JDBC Connections
 */
public interface IConnectionFactory
{
    /**
     * Create a connection to the specified database.
     *
     * @param connectionString
     * @return
     */
    Connection CreateConnection(String connectionString, String userName, String password) throws SQLException;
}