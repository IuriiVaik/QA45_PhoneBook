package tests;

import data_provider.DPContact;
import dto.ContactDtoLombok;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

public class AddContactTests extends ApplicationManager {

    private final SoftAssert softAssert = new SoftAssert();
    private final UserDto user = new UserDto("qa_mail@mail.com", "Qwerty123!");
    private AddPage addPage;

    @BeforeMethod
    public void login() {
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginPage(getDriver()).typeLoginForm(user);
        new ContactsPage(getDriver()).clickBtnAdd();
        addPage = new AddPage(getDriver());
    }

    @Test
    public void addNewContactPositiveTest() {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Name123")
                .lastName("Last name123")
                .phone("1234567890")
                .email("lastname@mail.com")
                .address("address st.1")
                .description("description")
                .build();
        addPage.typeContactForm(contact);
        Assert.assertTrue(new ContactsPage(getDriver()).validateLastElementContactList(contact),
                "Contact was not successfully added!");
    }

    @Test
    public void addNewContactNegativeTest_emptyName() {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("")
                .lastName("Last name123")
                .phone("1234567890")
                .email("lastname@mail.com")
                .address("address st.1")
                .description("description")
                .build();
        addPage.typeContactForm(contact);
        Assert.assertFalse(addPage.validateUrlContacts());

    }

    @Test
    public void addNewContactNegativeTest_wrongPhone() {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("name 123")
                .lastName("Last name123")
                .phone("1qwwwerer")
                .email("lastname@mail.com")
                .address("address st.1")
                .description("description")
                .build();
        addPage.typeContactForm(contact);

        String message = addPage.closeAlertAndReturnText();
        softAssert.assertTrue(message.contains("Phone number must contain only digits! And length min 10, max 15!"),
                "Unexpected alert message: " + message);
        softAssert.assertFalse(addPage.validateUrlContacts(), "The form should not have been submitted!");
        softAssert.assertAll();
    }

    @Test
    public void addNewContactNegativeTest_wrongEmail() {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("name 123")
                .lastName("Last name123")
                .phone("1234567890")
                .email("lastname")
                .address("address st.1")
                .description("description")
                .build();
        addPage.typeContactForm(contact);

        String message = addPage.closeAlertAndReturnText();
        softAssert.assertTrue(message.contains("Email not valid"), "Unexpected alert message: " + message);
        softAssert.assertFalse(addPage.validateUrlContacts(), "The form should not have been submitted!");
        softAssert.assertAll();
    }

    @Test(dataProvider = "positiveContactsDP", dataProviderClass = DPContact.class)
    public void addNewContactPositiveTest(ContactDtoLombok contact) {
        addPage.typeContactForm(contact);
        Assert.assertTrue(new ContactsPage(getDriver()).validateLastElementContactList(contact),
                "Contact was not successfully added!");
    }

    @Test(dataProvider = "negativeContactsDP", dataProviderClass = DPContact.class)
    public void addNewContactNegativeTest(ContactDtoLombok contact, String expectedError) {
        addPage.typeContactForm(contact);

        String message = addPage.closeAlertAndReturnText();
        Assert.assertTrue(message.contains(expectedError),
                "Expected error: " + expectedError + " but got: " + message);
    }
}
