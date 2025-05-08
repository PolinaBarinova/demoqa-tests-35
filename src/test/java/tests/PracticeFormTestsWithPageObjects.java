package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static utils.RandomUtils.*;

public class PracticeFormTestsWithPageObjects {

    RegistrationPage registrationPage = new RegistrationPage();

    String firstName = getRandomFirstName();
    String lastName = getRandomLastName();
    String userEmail = getRandomUserEmail();
    String userGender = getRandomGender();
    String userNumber = getRandomPhoneNumber();
    String dayOfBirth = getRandomDay();
    String monthOfBirth = getRandomMonth();
    String yearOfBirth = getRandomYear();
    String userSubject = getRandomSubject();
    String userHobby = getRandomHobby();
    String userAddress = getRandomAddress();
    String userState = getRandomState();
    String userCity = getRandomCityByState(userState);

    @BeforeAll
    static void prepareEnvironment() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(userGender)
                .setUserNumber(userNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(userSubject)
                .setHobbies(userHobby)
                .uploadPicture()
                .setAddress(userAddress)
                .setState(userState)
                .setCity(userCity)
                .submitForm()
                .checkResultTableVisibility()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", userGender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", userSubject)
                .checkResult("Hobbies", userHobby)
                .checkResult("Picture", "mem-kot.jpg")
                .checkResult("Address", userAddress)
                .checkResult("State and City", userState + " " + userCity);
    }

    @Test
    void fillOnlyRequiredFieldsTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(userGender)
                .setUserNumber(userNumber)
                .submitForm()
                .checkResultTableVisibility()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", userGender)
                .checkResult("Mobile", userNumber);
    }

    @Test
    void submitEmptyFormTest() {
        registrationPage.openPage()
                .submitForm()
                .checkResultTableUnvisibility();
    }
}


