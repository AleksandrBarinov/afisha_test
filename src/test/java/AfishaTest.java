import org.junit.Test;
import pages.MainPage;
import pages.ResultPage;
import steps.BaseSteps;
import util.RunListener;

public class AfishaTest extends BaseSteps {

    @Test
    public void Test() throws InterruptedException {

        RunListener runListener = new RunListener();
        runListener.runListener();

        MainPage mainPage = new MainPage(runListener.getEventDriver());

        mainPage.selectGoToCinema();
        mainPage.selectWhenList();
        mainPage.selectWhen("Завтра");
        mainPage.selectThreeDCheckbox("2D");
        mainPage.selectSubwayList();
        mainPage.selectSubway("Курская",2);
        mainPage.selectGenreList();
        mainPage.selectGenre("драма");
        mainPage.selectGenre("комедия");

        mainPage.selectChoose();

        ResultPage resultPage = new ResultPage(driver);

        resultPage.checkTitle();
        resultPage.whenCheck(mainPage.getWhenTyped());
        resultPage.subwaysCheck(mainPage.getSubwaysSelected());
        resultPage.genresCheck(mainPage.getGenresSelected());
        resultPage.threeDCheckboxCheck(mainPage.getThreeDCheckboxSelected());

    }
}
