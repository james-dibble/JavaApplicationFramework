/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaApplicationFramework.Mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author james
 */
public class MapperTests {
    private FakeDomainObject _fakeObject;
    
    @Before
    public void Setup() {
        this._fakeObject = new FakeDomainObject();
    }
    
    @Test
    public void TestFindSingleReturnsNullIsEmptyResultSet() throws SQLException{
        ResultSet fakeResultSet = Mockito.mock(ResultSet.class);
        
        Mockito.when(fakeResultSet.first()).thenReturn(false);
        
        IMapper<FakeDomainObject> fakeMapper = new FakeMapper();
        
        FakeDomainObject actual = fakeMapper.FindSingle(fakeResultSet);
        
        Assert.assertNull(actual);
    }
    
    @Test
    public void TestFindSingleReturnObject() throws SQLException{
        ResultSet fakeResultSet = Mockito.mock(ResultSet.class);
        
        Mockito.when(fakeResultSet.first()).thenReturn(true);
        
        IMapper<FakeDomainObject> fakeMapper = new FakeMapper();
        
        FakeDomainObject actual = fakeMapper.FindSingle(fakeResultSet);
        
        Assert.assertEquals(this._fakeObject, actual);
    }
    
    @Test
    public void TestFindCollectionOfReturnsEmptyCollectionWhenEmptyResultSet() throws SQLException{
        ResultSet fakeResultSet = Mockito.mock(ResultSet.class);
        
        Mockito.when(fakeResultSet.first()).thenReturn(false);
        
        IMapper<FakeDomainObject> fakeMapper = new FakeMapper();
        
        ArrayList<FakeDomainObject> actual = (ArrayList)fakeMapper.FindCollectionOf(fakeResultSet);
        
        Assert.assertTrue(actual.isEmpty());
    }
    
    @Test
    public void TestFindCollectionOfReturnsCollection() throws SQLException{
        ResultSet fakeResultSet = Mockito.mock(ResultSet.class);
        
        Mockito.when(fakeResultSet.first()).thenReturn(true);
        Mockito.when(fakeResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        
        IMapper<FakeDomainObject> fakeMapper = new FakeMapper();
        
        ArrayList<FakeDomainObject> actual = (ArrayList)fakeMapper.FindCollectionOf(fakeResultSet);
        
        Assert.assertEquals(3, actual.size());
    }
    
    private class FakeDomainObject implements IUniqueObject<Integer>{
        @Override
        public Integer GetId() {
            return 1;
        }

        @Override
        public boolean IsNewObject() {
            return true;
        }
    }
    
    private class FakeMapper extends Mapper<FakeDomainObject>{

        @Override
        public Class GetMappedType() {
            return FakeDomainObject.class;
        }

        @Override
        public String GetFindQuery(IPersistenceSearcher<FakeDomainObject> searcher) {
            return new String();
        }

        @Override
        public Iterable<String> GetObjectCreateQueries(FakeDomainObject objectToSave) {
            return new Stack<>();
        }

        @Override
        public Iterable<String> GetObjectSaveQueries(FakeDomainObject objectToSave) {
            return new Stack<>();
        }

        @Override
        protected FakeDomainObject MapFromResultSet(ResultSet results) {
            return _fakeObject;
        }
    }
}