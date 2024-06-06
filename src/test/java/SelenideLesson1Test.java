import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SelenideLesson1Test {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://github.com/selenide/selenide";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void selenideWikiSoftAssertionsPageHasCodeForeJUnit5Test() {
        Selenide.open("/");
        // move to wiki page
        $("#wiki-tab").click();
        // find the SoftAssertions page in the list of Pages and open it
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box a[href*='Soft']")
                .shouldBe(Condition.visible).click();

        //check that there is an example code for JUnit5 inside the page
        // check for header
        $(withText("Using JUnit5 extend test class"))
                .shouldBe(Condition.visible);
        // check for example text
        $x("//*[@class='markdown-heading' and .//*[contains(@id, 'using-junit5')]]/following-sibling::div[1]")
                .shouldBe(Condition.visible);
    }
}
