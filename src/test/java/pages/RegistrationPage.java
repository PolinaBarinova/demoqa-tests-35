package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {

    CalendarComponent calendarComponent = new CalendarComponent();

    private SelenideElement firstNameInput = $("#firstName"),

    lastNameInput = $("#lastName"),
    userEmailInput = $("#userEmail"),
    genderWrapper = $("#genterWrapper"),
    userNumberInput = $("#userNumber"),
    calendarInput = $("#dateOfBirthInput"),
    subjectsInput = $("#subjectsInput"),
    hobbiesWrapper = $("#hobbiesWrapper"),
    uploadPicture = $("#uploadPicture"),
    currentAddressInput = $("#currentAddress"),
    stateButton = $("#state"),
    stateInput = $("#react-select-3-input"),
    cityButton = $("#city"),
    cityInput = $("#react-select-4-input"),
    submitButton = $("#submit");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(String value) {
        hobbiesWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage uploadPicture() {
        uploadPicture.uploadFromClasspath("mem-kot.jpg");
        return this;
    }

    public RegistrationPage setAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public RegistrationPage setState(String value) {
        stateButton.click();
        stateInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setCity(String value) {
        cityButton.click();
        cityInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage submitForm() {
        submitButton.click();
        return this;
    }

}
