
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.Timeout;

/**
 *
 * @author Rayen CHEIKH M'HAMED, L'HADI YOUSFI, Benjamin
 */

public class DataframeTest {
    
    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);
    
    private Dataframe employees, Df;
    private static Map<String,List<?>> data;
    
    static List<String> employeeNames;
    static List<Integer> employeeIDs;
    static List<Double> salaries;
    static List<Integer> yearsOfService;
    
    @BeforeClass
    public static void initclass()  {
        employeeNames = new ArrayList<String>(Arrays.asList("John", "Emily", "Michael", "Sarah", "David", "Emma"));
        employeeIDs = new ArrayList<Integer>(Arrays.asList(1001, 1002, 1003, 1004, 1005, 1006));   
        salaries = new ArrayList<Double>(Arrays.asList(60000.0, 75000.0, 90000.0, 65000.0, 80000.0, 70000.0));      
        yearsOfService = new ArrayList<Integer>(Arrays.asList(5, 3, 8, 2, 6, 4)); 
    }
    
    
    
    @Before
    public void init() throws FileNotFoundException, IOException, BadArgumentException   {
        data = new HashMap<String,List<?>>();
        data.put("Employee Name", employeeNames);
        data.put("Employee ID", employeeIDs);
        data.put("Salary", salaries);
        data.put("Years of Service", yearsOfService);
        
        employees = new Dataframe(data);
        Df = new Dataframe();
    }
    
    @After
    public void end() {
        data = null;
        employees = null;
    } 
    
    @Test (expected = BadArgumentException.class)
    public void testConstructor() throws Exception{
        List<Integer> list1  = Arrays.asList(1, 2, 3);        
        List<Integer> list2 = Arrays.asList(4, 5); 
        
        data = new HashMap<String, List<?>>();
        data.put("list1", list1);
        data.put("list2", list2);
        
        Dataframe df = new Dataframe(data);
    }  
    
    @Test (expected = BadArgumentException.class)
    public void testConstructor2() throws Exception{
        data = null;
        Dataframe df = new Dataframe(data);
    }
    
    @Test (expected = BadArgumentException.class)
    public void testConstructor3() throws Exception{
        data = new HashMap<String, List<?>>();
        Dataframe df = new Dataframe(data);
    }
    
    @Test (expected = BadArgumentException.class)
    public void testConstructorlistenull() throws Exception{
        List<Integer> list1  = Arrays.asList(1, 2, 3);        
        List<Integer> list2 = null; 
        
        data = new HashMap<String, List<?>>();
        data.put("list1", list1);
        data.put("list2", list2);
        
        Dataframe df = new Dataframe(data);
    }   

    @Test
    public void testgetDataFrameSize() {
    
        assertEquals(Df.getDataFrameSize(),0);
        assertEquals(employees.getDataFrameSize(),6);
       
    }
    
    @Test
    public void testGetCol() {
        Column c = employees.getcol("Employee Name");
       
        assertEquals(c.getLabel(),"Employee Name");
       
    }
    
    public void testinsertCol()  {
        Column c = new Column("testCol1","Integer",  Arrays.asList(1, 2));

        
        Df.insertCol(c);
        assertEquals(c, Df.getcol("testCol1"));
        
    }

    @Test
    public void displayTest() throws Exception {
        employees.display();
    }
   
    @Test
    public void testSelectLines()  {
        Dataframe d = employees.selectLine(0, 2);
        assertEquals(3, d.getDataFrameSize());
    }


    @Test
    public void testdisplayFromTo() throws Exception {
        employees.displayFromTo(0, employees.getDataFrameSize());
    }
    
    @Test (expected = BadArgumentException.class)
    public void testdisplayFromTo2() throws Exception {
        employees.displayFromTo(-20, employees.getDataFrameSize());
    }
    
    @Test 
    public void testdisplayFromBegginingTo() throws Exception {
        employees.displayFromBegginingTo(1); 
    }

    @Test
    public void testdisplayFromEndTo() throws Exception {
        employees.displayFromEndTo(1); 
    }
    
    @Test (expected = BadArgumentException.class)
    public void testdisplayFromBegginingTo2() throws Exception {
        employees.displayFromBegginingTo(-100); 
    }

    @Test (expected = BadArgumentException.class)
    public void testdisplayFromEndTo2() throws Exception {
        employees.displayFromEndTo(-100); 
    }
    
    @Test
    public void testMax() throws Exception {
       
        assertEquals(90000.0, employees.max("Salary"), 0.01);
    }
    
    @Test
    public void testSum() throws Exception {
      
        assertEquals(440000.0, employees.sum("Salary"), 0.01);
    }
    
    @Test
    public void testMin() throws Exception {
       
        assertEquals(60000.0, employees.min("Salary"), 0.01);
    }
    
    @Test
    public void testMean() throws Exception {
       
        assertEquals(73333.33, employees.mean("Salary"), 0.01);
    }

}