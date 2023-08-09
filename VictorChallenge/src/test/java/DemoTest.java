
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import utilities.Log;

public class DemoTest extends BaseTest {

    private final HomePage homePage = new HomePage(getDriver());

    @Test(priority = 1)
    public void validateBasicElements(){
        Log.info("Executing TEST CASE 1");
        Assert.assertTrue(homePage.isHeaderDisplayed());
        Assert.assertTrue(homePage.isSearchFormDisplayed());
        Assert.assertTrue(homePage.isSearchResultSectionDisplayed());
    }

    @Test(priority = 2)
    public void validateTitleOfTheApp(){
        Log.info("Executing TEST CASE 2");
        Assert.assertEquals(homePage.getTitleText(),"Get Github Repos");
    }

    @Test(priority = 3)
    public void validateSearchResults(){
        Log.info("Executing TEST CASE 3");
        homePage.inputSearchField("victor");
        homePage.clickGoButton();
        Assert.assertTrue(homePage.isResultsSearchDisplayed());
    }

    @Test(priority = 4)
    public void validateSearchResultDetails(){
        Log.info("Executing TEST CASE 4");
        homePage.inputSearchField("victor");
        homePage.clickGoButton();
        Assert.assertTrue(homePage.isSuccessMessageDisplayed());
        homePage.iterateElementsInSearch();
    }

    @Test(priority = 5)
    public void validateErrorMessage() {
        Log.info("Executing TEST CASE 5");
        homePage.clickEnterKey();
        Assert.assertTrue(homePage.isErrorMessageDisplayed());
    }

}
