import org.junit.Test;
import pages.MainPage;
import pages.ResultPage;
import steps.BaseSteps;
import util.RunListener;

public class AfishaTest extends BaseSteps {

    @Test
    public void Test(){

        RunListener runListener = new RunListener();
        runListener.runListener();

        MainPage mainPage = new MainPage(runListener.getEventDriver());

        mainPage.selectGoToCinema();
        mainPage.selectWhenList();
        mainPage.selectWhen("Завтра");
        mainPage.selectSubwayList();
        mainPage.selectSubway("Курская",2);
        mainPage.selectGenreList();
        mainPage.selectGenre("драма");
        mainPage.selectGenre("комедия");
        mainPage.selectThreeDCheckbox("2D");

        mainPage.selectChoose();

        ResultPage resultPage = new ResultPage(driver);

        resultPage.checkTitle();
        resultPage.whenCheck(mainPage.getWhenTyped());
        resultPage.subwaysCheck(mainPage.getSubwaysSelected());
        resultPage.genresCheck(mainPage.getGenresSelected());
        resultPage.threeDCheckboxCheck(mainPage.getThreeDCheckboxSelected());

    }
}
