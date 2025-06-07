package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.*;

@DisplayName("Проверки формы регистрации")
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
    static void preparingEnvironment() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browser.size", "1920x1080");
        Configuration.browserVersion = System.getProperty("browser.version", "128.0");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 10000;
        Configuration.remote = "eager";
        Configuration.remote = String.format(
                "https://%s:%s@%s/wd/hub",
                System.getProperty("selenoid.login", "user1"),
                System.getProperty("selenoid.password", "1234"),
                System.getProperty("selenoid.url", "selenoid.autotests.cloud")
        );

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }


    @Feature("Отправка формы регистрации")
    @Owner("p.barinova")
    @DisplayName("Отправка формы с корректно заполненными всеми полями")
    @Tag("base")
    @Test
    void fillFormTest() {

        step("Открыть страницу регистрации", () -> {
            registrationPage.openPage();
        });

        step("Убрать со страницы баннеры", () -> {
            registrationPage.removeBanners();
        });

        step("Заполнить все поля формы", () -> {
            registrationPage.setFirstName(firstName)
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
                    .setCity(userCity);
        });

        step("Отправить форму", () -> {
            registrationPage.submitForm();
        });

        step("Проверка результатов", () -> {
            registrationPage.checkResultTableVisibility()
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
        });
    }

    @Feature("Отправка формы регистрации")
    @Owner("p.barinova")
    @DisplayName("Отправка формы с корректно заполненными обязательными полями")
    @Tag("base")
    @Test
    void fillOnlyRequiredFieldsTest() {

        step("Открыть страницу регистрации", () -> {
            registrationPage.openPage();
        });

        step("Убрать со страницы баннеры", () -> {
            registrationPage.removeBanners();
        });

        step("Заполнить обязательные поля формы", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setGender(userGender)
                    .setUserNumber(userNumber);
        });

        step("Отправить форму", () -> {
            registrationPage.submitForm();
        });

        step("Проверка результатов", () -> {
            registrationPage.checkResultTableVisibility()
                    .checkResult("Student Name", firstName + " " + lastName)
                    .checkResult("Gender", userGender)
                    .checkResult("Mobile", userNumber);
        });
    }

    @Feature("Отправка формы регистрации")
    @Owner("p.barinova")
    @DisplayName("Отправка формы с пустыми полями")
    @Tag("base")
    @Test
    void submitEmptyFormTest() {

        step("Открыть страницу регистрации", () -> {
            registrationPage.openPage();
        });

        step("Отправить пустую форму", () -> {
            registrationPage.submitForm();
        });

        step("Проверка, что таблицы с результатами нет на странице", () -> {
            registrationPage.checkResultTableUnvisibility();
        });
    }
}


