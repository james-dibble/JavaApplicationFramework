// --------------------------------------------------------------------------------------------------------------------
// <copyright file="MySqlPersistenceManager.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * A context to manage interaction to a MySQL persistence source.
 */
public final class MySqlPersistenceManager implements IPersistenceManager
{
    private final Connection _connection;
    private final MapperDictionary _mappers;
    private List<String> _statementsToCommit;

    /**
     * Initialises a new instance of the MySqlPersistenceManager class.
     *
     * @param dataSource A Spring data source.
     * @param mappers A collection of know type mappers.
     * @throws SQLException The data source could not create a connection.
     */
    public MySqlPersistenceManager(DriverManagerDataSource dataSource, MapperDictionary mappers) throws SQLException
    {
        this(dataSource.getConnection(), mappers);
    }

    /**
     * Initialises a new instance of the MySqlPersistenceManager class.
     *
     * @param connection A JDBC connection to the MySQL source.
     * @param mappers A collection of know type mappers.
     */
    public MySqlPersistenceManager(Connection connection, MapperDictionary mappers)
    {
        this._connection = connection;
        this._mappers = mappers;
        this._statementsToCommit = new Stack<>();
    }

    @Override
    public <T extends IPersistableObject> T Find(IPersistenceSearcher<T> searcher)
    {
        IMapper mapper = this.GetMapperForType(searcher.Type());

        String query = mapper.GetFindQuery(searcher);

        T result = null;

        try
        {
            Statement statement = this._connection.createStatement();

            ResultSet results = statement.executeQuery(query);

            result = (T) mapper.FindSingle(results);
        }
        catch (SQLException ex)
        {
        }

        return result;
    }

    @Override
    public <T extends IPersistableObject> Iterable<T> FindCollectionOf(IPersistenceSearcher<T> searcher)
    {
        IMapper mapper = this.GetMapperForType(searcher.Type());

        String query = mapper.GetFindQuery(searcher);

        Iterable<T> result = new ArrayList<>();

        try
        {
            Statement statement = this._connection.createStatement();

            ResultSet results = statement.executeQuery(query);

            result = mapper.FindCollectionOf(results);
        }
        catch (SQLException ex)
        {
        }

        return result;
    }

    @Override
    public void Add(IPersistableObject objectToSave)
    {
        if (!objectToSave.IsNewObject())
        {
            this.Change(objectToSave);
        }

        IMapper mapper = this.GetMapperForType(objectToSave.getClass());

        Iterable<String> queries = mapper.GetObjectCreateQueries(objectToSave);

        this._statementsToCommit.addAll((Collection<? extends String>) queries);
    }

    @Override
    public void Change(IPersistableObject objectToSave)
    {
        if (objectToSave.IsNewObject())
        {
            this.Add(objectToSave);
        }

        IMapper mapper = this.GetMapperForType(objectToSave.getClass());

        Iterable<String> queries = mapper.GetObjectSaveQueries(objectToSave);

        this._statementsToCommit.addAll((Collection<? extends String>) queries);
    }

    @Override
    public void Commit() throws SQLException
    {
        try
        {
            for (String statement : this._statementsToCommit)
            {
                Statement sqlStatement = this._connection.createStatement();
                sqlStatement.executeUpdate(statement);
            }
        }
        catch (SQLException ex)
        {
            this._statementsToCommit.clear();
            throw ex;
        }
        finally
        {
            this._statementsToCommit.clear();
        }
    }

    @Override
    public void Dispose() throws SQLException
    {
        this._connection.close();
    }

    private IMapper GetMapperForType(Class type)
    {
        IMapper mapper = this._mappers.get(type);

        if (mapper == null)
        {
            String message = String.format("A mapper has not been registered for type %s", type.toString());
            throw new UnsupportedOperationException(message);
        }

        return mapper;
    }
}
