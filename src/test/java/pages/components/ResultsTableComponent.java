package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    private SelenideElement resultTable = $(".table-responsive");


    public void checkResults(String key, String value) {
        resultTable.$(byText(key)).parent().shouldHave(text(value));
    }

    public void сheckTableVisibility() {
        resultTable.shouldBe(visible);
    }

    public void checkTableUnvisibility() {
        resultTable.shouldNotBe(visible);
    }
}
