package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    private SelenideElement resultTable = $(".table-responsive");


    public ResultsTableComponent checkResult(String key, String value) {
        resultTable.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }

    public ResultsTableComponent сheckTableVisibility() {
        resultTable.shouldBe(visible);
        return this;
    }

    public ResultsTableComponent checkTableUnvisibility() {
        resultTable.shouldNotBe(visible);
        return this;
    }
}
