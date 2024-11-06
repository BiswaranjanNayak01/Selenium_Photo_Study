package practicde;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class dataProvider {

    @DataProvider(name = "take dataProvider from another class")
    public Object[][] dataNameStyle(){
        return new Object[][]{
                {"[0][0]","[0][1]","[0][2]"},
                {"[1][0]","[1][1]","[1][2]"}
        };
    }
    @DataProvider(name = "creat")
    public Object[][] mutlipleTests(Method m){
       Object[][] dataProvider=null;
       if (m.getName().equals("test1")) {
           dataProvider= new Object[][]{
                   {"[0][0]", "[0][1]", "[0][2]"},
                   {"[1][0]", "[1][1]", "[1][2]"}
           };
       } else if (m.getName().equals("test2")) {
           dataProvider= new Object[][]{
                   {"[0][0]", "[0][1]"},
                   {"[1][0]", "[1][1]"}
           };
       }
        return dataProvider;
    }
}
