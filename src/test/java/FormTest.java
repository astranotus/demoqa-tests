import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {

     @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    private static final String USERNAME = "Name ";
    private static final String USERLASTNAME = "LastName";
    private static final String USEREMAIL = "2@a.ru";
    private static final String SEX = "Male";
    private static final String USERPHONE = "7777777777";
    private static final String DATEOFBIRTH = "08 June,1999";
    private static final String SUBJECT = "Physics";
    private static final String HOBBIES = "Sports";
    private static final String PICTURE = "img.jpg";
    private static final String ADDRESS = "Kolotushkina street 5";
    private static final String STATE = "NCR";
    private static final String CITY = "Delhi";



    @Test
    void testUI() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue(USERNAME);
        $("#lastName").setValue(USERLASTNAME);
        $("#userEmail").sendKeys(USEREMAIL);
        $(byText("Male")).click();
        $("#userNumber").sendKeys(USERPHONE);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__day--008").click();
        $("#subjectsInput").setValue("p").pressEnter();
        $(byText(HOBBIES)).click();
        $("#uploadPicture").uploadFromClasspath(PICTURE);
        $("#currentAddress").sendKeys(ADDRESS);
        //$("#react-select-3-input").setValue(STATE).pressEnter();
        $(byText("Select State")).click();
        $(byText(STATE)).click();

        $("#city").click();
        $("#react-select-4-input").setValue(CITY).pressEnter();
        $("#submit").click();
        $("#example-modal-sizes-title-lg").isDisplayed();
        $(".table-responsive").shouldHave(
                text(USERNAME+USERLASTNAME),
                text(USEREMAIL),
                text(SEX),
                text(USERPHONE),
                text(DATEOFBIRTH),
                text(SUBJECT),
                text(HOBBIES),
                text(PICTURE),
                text(ADDRESS),
                text(STATE + " " + CITY));
    }
}


