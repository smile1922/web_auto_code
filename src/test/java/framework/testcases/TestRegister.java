package framework.testcases;

import framework.listener.RetryListener;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRegister {
    @Test
    public void register(){
        int a=1;
        int b=2;
        Assert.assertEquals(a,b);
    }
}
