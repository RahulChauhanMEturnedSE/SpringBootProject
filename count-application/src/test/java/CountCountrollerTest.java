import com.codetest.springboot.countApplication.count_application.CountController;
import com.codetest.springboot.countApplication.count_application.LocalStorage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CountCountrollerTest {

    @InjectMocks
    CountController countController;

    @Before
    public void before() throws Exception {

        LocalStorage.counterStore.put("Sample", 0);
    }

    @Test
    public void getCounterListTest() {
        Assert.assertEquals(LocalStorage.counterStore.toString(), countController.getCounterList());
    }

    @Test
    public void getCounterListTest2() {
        LocalStorage.counterStore.clear();
        Assert.assertEquals("No counter available", countController.getCounterList());
    }

    @Test
    public void getCounterValueTest1() {
        Assert.assertEquals("Not Valid Counter", countController.getCounterValue(null));
    }

    @Test
    public void getCounterValueTest2() {
        LocalStorage.counterStore.put("Dummy", 1);
        Assert.assertEquals("1", countController.getCounterValue("Dummy"));
    }

    @Test
    public void createCounterTest1() {
        LocalStorage.counterStore.put("Dummy", 1);
        Assert.assertEquals("Counter already exists", countController.createCounter("Dummy"));
    }

    @Test
    public void createCounterTest2() {
        Assert.assertEquals("Counter is added succesfully", countController.createCounter("Dummy2"));
    }

    @Test
    public void incrementCounterTest1() {
        LocalStorage.counterStore.put("incrementCounter", 0);
        Assert.assertEquals("Counter is incremented", countController.incrementCounter("incrementCounter"));
        Assert.assertEquals("1", countController.getCounterValue("incrementCounter"));
    }

    @Test
    public void incrementCounterTest2() {
        Assert.assertEquals("No counter available with given name", countController.incrementCounter("notCounter"));
    }
}
