package tests;

import data_provider.DPContact;
import dto.ContactDtoLombok;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

import static utils.RandomUtils.*;

public class EditContactTests extends ApplicationManager {

    SoftAssert softAssert = new SoftAssert();
    UserDto user = new UserDto("qa_mail@mail.com", "Qwerty123!");

    ContactsPage contactsPage;

    @BeforeMethod
    public void login(){
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        contactsPage = new ContactsPage(getDriver());
    }

    @Test
    public void editContactPositiveTest(){
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .email(generateEmail(7))
                .phone(generatePhone(12))
                .address("Address "+generateString(10))
                .build();
        contactsPage.editContact(contact);
        Assert.assertTrue(contactsPage.validateCardContact(contact));
    }

    @Test(dataProvider = "newContactDP", dataProviderClass = DPContact.class)
    public void editContactTestDP(ContactDtoLombok contact){
        contactsPage.editContact(contact);
        Assert.assertTrue(contactsPage.validateCardContact(contact));
    }

    @Test(dataProvider = "newContactDPFile", dataProviderClass = DPContact.class)
    public void editContactTestDPfromFile(ContactDtoLombok contact){
        contactsPage.editContact(contact);
        Assert.assertTrue(contactsPage.validateCardContact(contact));
    }


}