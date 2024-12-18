package pages;

import dto.ContactDtoLombok;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactsPage extends BasePage{
    public ContactsPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver,10), this);
    }

    @FindBy(xpath = "//button[text()='Sign Out']")
    WebElement btnSignOut;
    @FindBy(xpath = "//a[text()='ADD']")
    WebElement btnAdd;
    @FindBy(xpath = "//div[@class='contact-page_leftdiv__yhyke']/div/div[last()]")
    WebElement lastElementContactList;
    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM']")
    WebElement firstElementContactList;
    @FindBy(xpath = "//button[text()='Remove']")
    WebElement btnRemove;
    @FindBy(xpath = "//button[text()='Edit']")
    WebElement btnEdit;
//======================================================= edit

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement inputName;
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement inputLastName;
    @FindBy(xpath = "//input[@placeholder='Phone']")
    WebElement inputPhone;
    @FindBy(xpath = "//input[@placeholder='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@placeholder='Address']")
    WebElement inputAddress;
    @FindBy(xpath = "//input[@placeholder='desc']")
    WebElement inputDescription;
    @FindBy(xpath = "//button[text()='Save']")
    WebElement btnSave;
    @FindBy(xpath = "//div[@class='contact-item-detailed_card__50dTS']")
    WebElement cardContact;

    public boolean validateCardContact(ContactDtoLombok contact){
        System.out.println(cardContact.getText());
        System.out.println("============================================");
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions. textToBePresentInElement(cardContact, contact.getName()));
        System.out.println(cardContact.getText());
        String cardContactText = cardContact.getText();
        return (cardContactText.contains(contact.getName())
                && cardContactText.contains(contact.getLastName())
                && cardContactText.contains(contact.getPhone())
                && cardContactText.contains(contact.getEmail())
                && cardContactText.contains(contact.getAddress()));
    }

    public void deleteFirstContact(){
        clickWait(firstElementContactList, 3);
        clickWait(btnRemove, 3);
        pause(3);
    }

    public boolean validateLastElementContactList(ContactDtoLombok contact){
        System.out.println(lastElementContactList.getText());
        return lastElementContactList.getText().contains(contact.getName());
    }

    public void clickBtnSignOut(){
        //pause(3);
        //btnSignOut.click();
        clickWait(btnSignOut, 5);
    }

    public void clickBtnAdd(){
        btnAdd.click();
    }


    public boolean isSignOutPresent(){
        //pause(3);
        return btnSignOut.isDisplayed();
    }


    public int quantityContacts() {
//        pause(5);
//        return driver.findElements(
//                By.xpath("//div[@class='contact-item_card__2SOIM']")).size();
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//div[@class='contact-item_card__2SOIM']"))).size();
    }


    public void editContact(ContactDtoLombok contact) {
        firstElementContactList.click();
        btnEdit.click();
        inputName.clear();
        inputName.sendKeys(contact.getName());

        inputLastName.clear();
        inputLastName.sendKeys(contact.getLastName());

        inputPhone.clear();
        inputPhone.sendKeys(contact.getPhone());

        inputEmail.clear();
        inputEmail.sendKeys(contact.getEmail());

        inputAddress.clear();
        inputAddress.sendKeys(contact.getAddress());

        btnSave.click();


    }
}