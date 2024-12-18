package pages;

import dto.UserDto;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver,10), this);
    }
    @FindBy(xpath = "//input[@name='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@name='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//button[text()='Login']")
    WebElement btnLogin;
    @FindBy(xpath = "//button[text()='Registration']")
    WebElement btnRegistration;

    @FindBy(xpath = "//div[@style='color: red; text-align: center; margin-bottom: 10px;']")
    WebElement errorMessage;

    public void typeLoginForm(String email, String password){
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        btnLogin.click();
    }
    public void typeLoginForm(UserDto user){
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        btnLogin.click();
    }

    public void typeRegistrationForm(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        btnRegistration.click();
    }

    public void closeAlert() {
        Alert alert = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        alert.accept();
    }

    public boolean validateErrorMessageLogin(String text){
        //pause(2);
        return isElementContainsText(errorMessage, text);
    }
}