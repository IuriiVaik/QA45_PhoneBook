package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    static WebDriver driver;

    public static void setDriver(WebDriver wd) {
        driver = wd;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementContainsText(WebElement element, String text) {
        return element.getText().contains(text);
    }

    public boolean validateUrl(String url, int time) {
        try {
            return new WebDriverWait(driver, time)
                    .until(ExpectedConditions.urlContains(url));
        }catch (org.openqa.selenium.TimeoutException e){
            e.printStackTrace();
            System.out.println("created exception");
            return false;
        }
    }

    public void clickWait(WebElement element, int time){
        new WebDriverWait(driver, time)
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }
}