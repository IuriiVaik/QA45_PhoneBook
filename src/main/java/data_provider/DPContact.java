package data_provider;

import dto.ContactDtoLombok;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.RandomUtils.*;

public class DPContact {

    @DataProvider(name = "newContactDP")
    public ContactDtoLombok[] newContactDP() {
        return new ContactDtoLombok[]{
                createRandomContact(),
                createRandomContact(),
                createRandomContact()
        };
    }

    private ContactDtoLombok createRandomContact() {
        return ContactDtoLombok.builder()
                .name("dp_" + generateString(5))
                .lastName(generateString(10))
                .email(generateEmail(7))
                .phone(generatePhone(12))
                .address("Address " + generateString(10))
                .description("Random description")
                .build();
    }

    @DataProvider(name = "contactsFromFile")
    public Iterator<Object[]> contactsFromFile() {
        List<Object[]> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data_provider/contact_table.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                ContactDtoLombok contact = ContactDtoLombok.builder()
                        .name(data[0].trim())
                        .lastName(data[1].trim())
                        .phone(data[2].trim())
                        .email(data[3].trim())
                        .address(data[4].trim())
                        .description(data[5].trim())
                        .build();
                boolean isPositive = Boolean.parseBoolean(data[6].trim());
                contacts.add(new Object[]{contact, isPositive});
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading contacts file!", e);
        }
        return contacts.iterator();
    }

    @DataProvider(name = "positiveContactsDP")
    public Object[][] positiveContactsDP() {
        return new Object[][]{
                {ContactDtoLombok.builder()
                        .name("Harry")
                        .lastName("Potter")
                        .phone("1234567890")
                        .email("harry.potter@gmail.com")
                        .address("123 Main St")
                        .description("Friend")
                        .build()},
                {ContactDtoLombok.builder()
                        .name("Hermione")
                        .lastName("Granger")
                        .phone("9876543210")
                        .email("hermione.granger@gmail.com")
                        .address("456 Elm St")
                        .description("Colleague")
                        .build()},
                {ContactDtoLombok.builder()
                        .name("Ron")
                        .lastName("Weasley")
                        .phone("1112223333")
                        .email("ron.weasley@yahoo.com")
                        .address("789 Oak St")
                        .description("Family")
                        .build()}
        };
    }

    @DataProvider(name = "negativeContactsDP")
    public Object[][] negativeContactsDP() {
        return new Object[][]{
                {ContactDtoLombok.builder()
                        .name("")
                        .lastName("Dumbledore")
                        .phone("1234567890")
                        .email("albus.dumbledore@gmail.com")
                        .address("Hogwarts")
                        .description("Headmaster")
                        .build(), "Name is required!"},
                {ContactDtoLombok.builder()
                        .name("Minerva")
                        .lastName("McGonagall")
                        .phone("abcd123")
                        .email("minerva.mcg@gmail.com")
                        .address("Hogwarts")
                        .description("Professor")
                        .build(), "Phone number must contain only digits! And length min 10, max 15!"},
                {ContactDtoLombok.builder()
                        .name("Severus")
                        .lastName("Snape")
                        .phone("1234567890")
                        .email("severus.snape")
                        .address("Hogwarts")
                        .description("Potions Master")
                        .build(), "Email not valid"}
        };
    }
}