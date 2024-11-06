package practicde;

import org.testng.*;
import org.testng.annotations.*;

public class testNG_practice {
    @BeforeSuite
    public void BeforeSuite1(){
        System.out.println("BeforeSuite1");
    }
    @BeforeSuite
    public void BeforeSuite(){
        System.out.println("BeforeSuite");
    }
    @BeforeTest
    public void BeforeTest(){
        System.out.println("BeforeTest");
    }
    @BeforeClass
    public void BeforeClass(){
        System.out.println("BeforeClass");
    }
    @BeforeGroups(value = "Champian")
    public void BeforeGroups(){
        System.out.println("BeforeGroups");
    }
    @BeforeMethod
    public void BeforeMethod(){
        System.out.println("BeforeMethod");
    }
    @Test
    public void test(){
        System.out.println("test");
    }
    @Test(groups = "Champian")
    public void test0(){
        System.out.println("Test 0");
    }
    @Test(groups = "Champian",priority = 1)
    public void test1(){
        System.out.println("Test 1");
    }
    @Test(groups = "Champian",priority = 2)
    public void test2(){
        System.out.println("Test 2");
    }
    @AfterMethod
    public void AfterMethod(){
        System.out.println("AfterMethod");
    }

    @AfterClass
    public void AfterClass(){
        System.out.println("AfterClass");
    }
    @AfterTest
    public void AfterTest(){
        System.out.println("AfterTest");
    }
    @AfterSuite
    public void AfterSuite(){
        System.out.println("AfterSuite");
    }
    @AfterGroups(value = "Champian")
    public void AfterGroups(){
        System.out.println("AfterGroups");
    }
//========================================================================================================================
@Test(invocationCount=4)
public void editUser(){
    Assert.assertEquals("","");
        System.out.println("editUser...");
}
}
