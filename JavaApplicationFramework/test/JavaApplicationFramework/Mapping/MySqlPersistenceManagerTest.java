package JavaApplicationFramework.Mapping;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;

public class MySqlPersistenceManagerTest {

    private Connection _fakeConnection;

    @Before
    public void Setup() {
        this._fakeConnection = Mockito.mock(Connection.class);
    }

    @Test
    public void TestFind() throws SQLException {
        FakeDomainObject expected = new FakeDomainObject(1, false);
        PersistenceSearcher<FakeDomainObject> searcher = new PersistenceSearcher<>(FakeDomainObject.class);
        String fakeQuery = "A fake query.";
        ResultSet fakeResultSet = Mockito.mock(ResultSet.class);

        Statement fakeStatement = Mockito.mock(Statement.class);
        Mockito.when(fakeStatement.executeQuery(fakeQuery))
                .thenReturn(fakeResultSet);

        Mockito.when(this._fakeConnection.createStatement())
                .thenReturn(fakeStatement);

        IMapper fakeMapper = Mockito.mock(Mapper.class);
        Mockito.when(fakeMapper.GetMappedType())
                .thenReturn(FakeDomainObject.class);

        Mockito.when(fakeMapper.GetFindQuery(searcher))
                .thenReturn(fakeQuery);

        Mockito.when(fakeMapper.FindSingle(fakeResultSet))
                .thenReturn(expected);

        MapperDictionary mappers = new MapperDictionary();
        mappers.put(FakeDomainObject.class, fakeMapper);

        IPersistenceManager fakeManager = new MySqlPersistenceManager(this._fakeConnection, mappers);

        FakeDomainObject actual = fakeManager.Find(searcher);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void TestFindCollectionOf() throws SQLException {
        FakeDomainObject expectedObject = new FakeDomainObject(1, false);
        ArrayList<FakeDomainObject> expected = new ArrayList<>();
        expected.add(expectedObject);
        expected.add(expectedObject);

        PersistenceSearcher<FakeDomainObject> searcher = new PersistenceSearcher<>(FakeDomainObject.class);
        String fakeQuery = "A fake query.";
        ResultSet fakeResultSet = Mockito.mock(ResultSet.class);

        Statement fakeStatement = Mockito.mock(Statement.class);
        Mockito.when(fakeStatement.executeQuery(fakeQuery))
                .thenReturn(fakeResultSet);

        Mockito.when(this._fakeConnection.createStatement())
                .thenReturn(fakeStatement);

        IMapper fakeMapper = Mockito.mock(Mapper.class);
        Mockito.when(fakeMapper.GetMappedType())
                .thenReturn(FakeDomainObject.class);

        Mockito.when(fakeMapper.GetFindQuery(searcher))
                .thenReturn(fakeQuery);

        Mockito.when(fakeMapper.FindCollectionOf(fakeResultSet))
                .thenReturn(expected);

        MapperDictionary mappers = new MapperDictionary();
        mappers.put(FakeDomainObject.class, fakeMapper);

        IPersistenceManager fakeManager = new MySqlPersistenceManager(this._fakeConnection, mappers);

        Iterable<FakeDomainObject> actual = fakeManager.FindCollectionOf(searcher);

        Assert.assertEquals(expected, actual);
        Assert.assertTrue(expected.size() == 2);
    }

    @Test
    public void TestAdd() throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        FakeDomainObject fakeObject = new FakeDomainObject(1, true);
        String fakeQuery = "A fake query";
        ArrayList saveQueries = new ArrayList();
        saveQueries.add(fakeQuery);

        IMapper fakeMapper = Mockito.mock(Mapper.class);
        Mockito.when(fakeMapper.GetMappedType())
                .thenReturn(FakeDomainObject.class);

        Mockito.when(fakeMapper.GetObjectCreateQueries(fakeObject))
                .thenReturn(saveQueries);

        MapperDictionary mappers = new MapperDictionary();
        mappers.put(FakeDomainObject.class, fakeMapper);

        IPersistenceManager fakeManager = new MySqlPersistenceManager(this._fakeConnection, mappers);

        fakeManager.Add(fakeObject);

        List<String> queries = GetQueries(fakeManager);

        Assert.assertTrue(queries.size() == 1);
    }

    @Test
    public void TestChange() throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        FakeDomainObject fakeObject = new FakeDomainObject(1, false);
        String fakeQuery = "A fake query";
        ArrayList saveQueries = new ArrayList();
        saveQueries.add(fakeQuery);

        IMapper fakeMapper = Mockito.mock(Mapper.class);
        Mockito.when(fakeMapper.GetMappedType())
                .thenReturn(FakeDomainObject.class);

        Mockito.when(fakeMapper.GetObjectSaveQueries(fakeObject))
                .thenReturn(saveQueries);

        MapperDictionary mappers = new MapperDictionary();
        mappers.put(FakeDomainObject.class, fakeMapper);

        IPersistenceManager fakeManager = new MySqlPersistenceManager(this._fakeConnection, mappers);

        fakeManager.Change(fakeObject);

        List<String> queries = GetQueries(fakeManager);

        Assert.assertTrue(queries.size() == 1);
    }

    @Test
    public void TestCommit() throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        FakeDomainObject fakeObject = new FakeDomainObject(1, false);
        String fakeQuery = "A fake query";
        ArrayList saveQueries = new ArrayList();
        saveQueries.add(fakeQuery);

        IMapper fakeMapper = Mockito.mock(Mapper.class);
        Mockito.when(fakeMapper.GetMappedType())
                .thenReturn(FakeDomainObject.class);

        Mockito.when(fakeMapper.GetObjectSaveQueries(fakeObject))
                .thenReturn(saveQueries);

        MapperDictionary mappers = new MapperDictionary();
        mappers.put(FakeDomainObject.class, fakeMapper);

        IPersistenceManager fakeManager = new MySqlPersistenceManager(this._fakeConnection, mappers);

        fakeManager.Change(fakeObject);

        Statement fakeStatement = Mockito.mock(Statement.class);
        Mockito.when(fakeStatement.executeUpdate(fakeQuery))
                .thenReturn(1);
        Mockito.when(this._fakeConnection.createStatement())
                .thenReturn(fakeStatement);

        fakeManager.Commit();

        List<String> queries = GetQueries(fakeManager);

        Assert.assertTrue(queries.isEmpty());
    }

    @Test
    public void TestCommitClearsListOnException() throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        FakeDomainObject fakeObject = new FakeDomainObject(1, false);
        String fakeQuery = "A fake query";
        ArrayList saveQueries = new ArrayList();
        saveQueries.add(fakeQuery);

        IMapper fakeMapper = Mockito.mock(Mapper.class);
        Mockito.when(fakeMapper.GetMappedType())
                .thenReturn(FakeDomainObject.class);

        Mockito.when(fakeMapper.GetObjectSaveQueries(fakeObject))
                .thenReturn(saveQueries);

        MapperDictionary mappers = new MapperDictionary();
        mappers.put(FakeDomainObject.class, fakeMapper);

        IPersistenceManager fakeManager = new MySqlPersistenceManager(this._fakeConnection, mappers);

        fakeManager.Change(fakeObject);
        fakeManager.Change(fakeObject);

        Statement fakeStatement = Mockito.mock(Statement.class);
        Mockito.when(fakeStatement.executeUpdate(fakeQuery))
                .thenReturn(1)
                .thenThrow(new SQLException());

        Mockito.when(this._fakeConnection.createStatement())
                .thenReturn(fakeStatement);

        try {
            fakeManager.Commit();
        } catch (SQLException ex) {
            Assert.assertNotNull(ex);
        }

        List<String> queries = GetQueries(fakeManager);

        Assert.assertTrue(queries.isEmpty());
    }

    private static List<String> GetQueries(IPersistenceManager manager) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field queries = manager.getClass().getDeclaredField("_statementsToCommit");
        queries.setAccessible(true);
        return (List<String>) queries.get(manager);
    }

    private class FakeDomainObject implements IUniqueObject<Integer> {

        private final int _id;
        private final boolean _isNew;

        public FakeDomainObject(int id, boolean isNewObject) {
            this._id = id;
            this._isNew = isNewObject;
        }

        @Override
        public Integer GetId() {
            return this._id;
        }

        @Override
        public boolean IsNewObject() {
            return this._isNew;
        }
    }
}