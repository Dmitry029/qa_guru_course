import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideLesson1Test {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://github.com/selenide/selenide";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1366x768";
    }

    /**
     * - Откройте страницу Selenide в Github
     * - Перейдите в раздел Wiki проекта
     * - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
     * - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
     */
    @Test
    void checkInWikiForTextAvailability() {
        open("");
        // move to wiki page
        $("#wiki-tab").click();
        // find the SoftAssertions page in the list of Pages and open it
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box a[href*='Soft']")
                .shouldBe(visible).click();

        //check that there is an example code for JUnit5 inside the page
        // check for example text
        $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class}) " +
                "class Tests { " +
                "  @Test " +
                "  void test() { " +
                "    Configuration.assertionMode = SOFT; " +
                "    open(\"page.html\"); " +
                " " +
                "    $(\"#first\").should(visible).click(); " +
                "    $(\"#second\").should(visible).click(); " +
                "  } " +
                "}"
        ));

        $("#wiki-body").shouldHave(text("class Tests {\n" +
                "  @RegisterExtension \n" +
                "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                "\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"
        ));
    }
}
