package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.ResultsTableComponent;

public class PracticeFormTestsWithPageObjects {

    RegistrationPage registrationPage = new RegistrationPage();

        @BeforeAll
        static void prepareEnvironment(){
            Configuration.browserSize = "1920x1080";
            Configuration.baseUrl = "https://demoqa.com";
            Configuration.pageLoadStrategy = "eager";
        }

        @Test
        void fillFormTest() {

            registrationPage.openPage()
                    .removeBanners()
                    .setFirstName("Alina")
                    .setLastName("Inova")
                    .setEmail("test@test.ru")
                    .setGender("Female")
                    .setUserNumber("7776665544")
                    .setDateOfBirth("3", "May", "2005")
                    .setSubjects("Maths")
                    .setHobbies("Music")
                    .uploadPicture()
                    .setAddress("Some address")
                    .setState("NCR")
                    .setCity("Delhi")
                    .submitForm()
                    .checkResultTableVisibility()
                    .checkResult("Student Name", "Alina Inova")
                    .checkResult("Student Email", "test@test.ru")
                    .checkResult("Gender", "Female")
                    .checkResult("Mobile", "7776665544")
                    .checkResult("Date of Birth", "03 May,2005")
                    .checkResult("Subjects", "Maths")
                    .checkResult("Hobbies", "Music")
                    .checkResult("Picture", "mem-kot.jpg")
                    .checkResult("Address", "Some address")
                    .checkResult("State and City", "NCR Delhi");
        }

        @Test

        void fillOnlyRequiredFields () {

            registrationPage.openPage()
                    .removeBanners()
                    .setFirstName("Alina")
                    .setLastName("Inova")
                    .setGender("Female")
                    .setUserNumber("7776665544")
                    .submitForm()
                    .checkResultTableVisibility()
                    .checkResult("Student Name", "Alina Inova")
                    .checkResult("Gender", "Female")
                    .checkResult("Mobile", "7776665544");

        }

        @Test

        void submitEmptyForm () {

            registrationPage.openPage()
                    .submitForm()
                    .checkResultTableUnvisibility();
        }
    }


