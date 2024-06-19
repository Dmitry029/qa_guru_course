import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ToolsQaTests {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillForm() {
        Selenide.open("/automation-practice-form");
        String firstName = "TestFirstName";
        String lastName = "TestLastName";
        String email = "test@test.com";
        String mobile = "0123456789";
        String dateOfBirth = "05.20.1999";
        String fileName = "smile.jpg";
        String address = "Test street 1";

        // set First name
        $("#firstName").setValue(firstName);
        // set Last name
        $("#lastName").setValue(lastName);
        // set email
        $("#userEmail-wrapper input").setValue(email);
        // select gender
        $x("//*[contains(@class, 'custom-control-label') and text()='Male']")
                .should(visible).click();
        // set mobile
        $("#userNumber").setValue(mobile);
        // set date of birth
        setDateOfBirth(dateOfBirth);
        // select subject
        $("#subjectsContainer").click();
        $x("//*[contains(@class, 'subjects-auto-complete__control--is-focused')]//input")
                .sendKeys("m");
        $("#react-select-2-option-2").click();
        // select hobbies
        $x("//*[contains(@class, 'custom-control-label') and text()='Sports']")
                .scrollIntoView(true).click();
        // select picture
        $("#uploadPicture").uploadFromClasspath(fileName);
        // enter address
        $("#currentAddress").click();
        $("#currentAddress").setValue(address);
        // select state
        $("#state").scrollIntoView(true).click();
        $("#react-select-3-option-1").click();
        // select city
        $("#city").click();
        $("#react-select-4-option-1").click();
        // submit and close
        $("#submit").click();
        $("#closeLargeModal").click();
    }

    private void setDateOfBirth(String date) {
        $("#dateOfBirthInput").click();
        $("#dateOfBirthInput").sendKeys(Keys.CONTROL + "a");
        $("#dateOfBirthInput").sendKeys(date);
        $("#dateOfBirthInput").sendKeys(Keys.ENTER);
    }
}