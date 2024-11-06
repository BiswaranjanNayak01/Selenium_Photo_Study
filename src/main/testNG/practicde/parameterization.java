package practicde;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class parameterization {
    @Parameters({"browser"})
    @Test(groups = "babulGroup")
    public void browserWithOptionalAnnotation_will_Pass(@Optional String browser){
        System.out.println(browser+" is taken from testNG_parameterization.xml file as parameter in test level");
        System.out.println("running :: browserWithOptionalAnnotation_will_Pass");
    }
    @Parameters({"browser"})
    @Test(groups = "babulGroup")
    public void browserWithoutOptionalAnnotation_will_Fail(String browser){
        System.out.println(browser+" is taken from testNG_parameterization.xml file as parameter in test level");
        System.out.println("running :: browserWithoutOptionalAnnotation_will_Fail");
    }


    @BeforeGroups(value = "babulGroup")
    @Parameters({"suiteParameter"})
    public void Group(@Optional String suiteParameter){
        System.out.println("group name is babulGroup and "+suiteParameter+" is suiteParameter in suite level");
        //System.out.println("suiteParameter is "+suiteParameter);
    }
}
