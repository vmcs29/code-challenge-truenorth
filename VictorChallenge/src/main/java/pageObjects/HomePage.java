package pageObjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.CommonUtilities;
import utilities.Log;

import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    /**
     * Home Page object extended from Base Page
     * @author victor.carrillo
     **/

    WebElement ele;
    List<WebElement> list ;
    By byHeaderTitle = By.cssSelector("header>h1");
    By byFormTitle = By.cssSelector("label[for='username']");
    By byFormTextField = By.cssSelector("input#username");
    By byFormGoButton = By.cssSelector("button.submit");
    By byResultSearchArea = By.cssSelector("section.output-area");
    By byResultList = By.cssSelector("ul>li");
    By byResultListNames = By.cssSelector("ul>li>p>a");
    By byResultListDescriptions = By.cssSelector("ul>li>p.repo-description");
    By bySuccessMessage = By.xpath("//strong[contains(text(),'Success!')]");
    By byErrorMessage = By.xpath("//strong[contains(text(),'Github user not found')]");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    /** Actions **/
    public boolean isHeaderDisplayed(){
        Log.info("Validate if element is displayed");
        return driver.findElement(byHeaderTitle).isDisplayed();
    }

    public boolean isSearchFormDisplayed(){
        Log.info("Validate if the entire search form is displayed");
        return getElementPresenceOfElementLocated(byFormTitle, 3).isDisplayed() &&
                getElementPresenceOfElementLocated(byFormTextField, 3).isDisplayed() &&
                getElementPresenceOfElementLocated(byFormGoButton, 3).isDisplayed();
    }

    public boolean isSearchResultSectionDisplayed(){
        Log.info("Validate if element is displayed");
        return driver.findElement(byResultSearchArea).isDisplayed();
    }

    public String getTitleText(){
        Log.info("Get title of the App");
        ele = getElementPresenceOfElementLocated(byHeaderTitle,5);
        return returnText(ele);
    }

    public void clickGoButton(){
        Log.info("Click on Go Button");
        ele = getElementPresenceOfElementLocated(byFormGoButton,5);
        ele.click();
    }

    public void clickEnterKey(){
        Log.info("Click on key");
        ele = getElementPresenceOfElementLocated(byFormGoButton,5);
        ele.sendKeys(Keys.ENTER);
    }

    public void inputSearchField(String textToSearch){
        Log.info("Enter search to find");
        ele = getElementPresenceOfElementLocated(byFormTextField,5);
        ele.sendKeys(textToSearch);
    }


    public boolean isResultsSearchDisplayed(){
        Log.info("Validate Results are displayed");
        list = getListElements(byResultList, 5);
        return driver.findElement(byResultList).isDisplayed();
    }

    public void iterateElementsInSearch(){
        List<WebElement> list =driver.findElements(byResultList) ;

        System.out.println("Result Items are: ");
        for (WebElement ele : list) {
            WebElement nameElement = ele.findElement(byResultListNames);
            String name = nameElement.getText();

            WebElement descriptionElement = ele.findElement(byResultListDescriptions);
            String description = descriptionElement.getText();
            System.out.println("Name : " + name);
            System.out.println("Description : "+ description);
            System.out.println("***********************");
             }
    }

    public boolean isSuccessMessageDisplayed(){
        Log.info("Validate if element is displayed");
        return driver.findElement(bySuccessMessage).isDisplayed();
    }

    public boolean isErrorMessageDisplayed(){
        Log.info("Validate if element is displayed");
        return driver.findElement(byErrorMessage).isDisplayed();
    }

}

