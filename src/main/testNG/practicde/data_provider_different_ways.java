package practicde;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class data_provider_different_ways {

    @Test(dataProvider = "data")
    public void test(String first,String second,String trird){
        System.out.println(first+" ::::: "+second+":::"+trird);
    }
    @DataProvider
    public Object[][] data(){

        Object[][] data=new Object[2][3];
        data[0][0]="[0][0]";
        data[0][1]="[0][1]";
        data[0][2]="[0][2]";
        data[1][0]="[1][0]";
        data[1][1]="[1][1]";
        data[1][2]="[1][2]";

        return data;
    }

    //==========================================================================================================================================


    @DataProvider
    public Object[][] datafreeStyle(){
        return new Object[][]{
                {"[0][0]","[0][1]","[0][2]"},
                {"[1][0]","[1][1]","[1][2]"}
        };
    }
    @Test(dataProvider = "datafreeStyle")
    public void testFreestyle(String first,String second,String trird){
        System.out.println(first+" ::::: "+second+":::"+trird);
    }

    //========================================================================================================================================================

    @DataProvider(name = "great")
    public Object[][] dataNameStyle(){
        return new Object[][]{
                {"[0][0]","[0][1]","[0][2]"},
                {"[1][0]","[1][1]","[1][2]"}
        };
    }
    @Test(dataProvider = "great")
    public void testByName(String first,String second,String trird){
        System.out.println(first+" ::::: "+second+":::"+trird);
    }

    //============================================ getting data from external class =========================================================================================

    @Test(dataProvider = "take dataProvider from another class",dataProviderClass =dataProvider.class)
    public void testByGettingDataFromAnotherClass(String first,String second,String trird){
        System.out.println(first+" ::::: "+second+":::"+trird);
    }

    //========================================= Same dataProvide with different data array for different tests ========================================================================================================

    @Test(dataProvider = "creat",dataProviderClass = dataProvider.class)
    public void test1(String first,String second,String trird){
        System.out.println(first+" ::::: "+second+":::"+trird);
    }

    @Test(dataProvider = "creat",dataProviderClass = dataProvider.class)
    public void test2(String first,String second){
        System.out.println(first+" ::::: "+second);
    }

    //===================================================================================================================================================================
}
