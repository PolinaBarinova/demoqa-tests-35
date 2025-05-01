package utils;

import com.github.javafaker.Faker;

public class RandomUtils {

    public static Faker faker = new Faker();

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomUserEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public static String getRandomPhoneNumber() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public static String getRandomDay() {
        int day = faker.number().numberBetween(1, 32);
        return String.format("%02d", day);
    }

    public static String getRandomMonth() {
        return faker.options().option("January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December");
    }

    public static String getRandomYear() {
        return String.valueOf(faker.number().numberBetween(1900, 2101));
    }

    public static String getRandomSubject() {
        return faker.options().option("Hindi", "English", "Maths", "Physics", "Chemistry",
                "Biology", "Computer Science", "Commerce", "Accounting", "Economics", "Arts", "Social Studies",
                "History", "Civics");
    }

    public static String getRandomHobby() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public static String getRandomAddress() {
        return faker.address().streetAddress();
    }

    public static String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public static String getRandomCityByState(String userState) {
        switch (userState) {
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Jaiselmer");
            default:
                return "Unknown City";
        }
    }
}
