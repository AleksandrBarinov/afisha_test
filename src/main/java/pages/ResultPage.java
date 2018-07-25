package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResultPage {

    @FindBy(xpath = "//*[@class='input-group input-group_fixed']//child::div//*[@class='dropdown__text js-dates__title']")
    WebElement when;

    @FindBy(xpath = "//*[@class='input-group input-group_fixed']//*[contains(@class,'subway')]//parent::span/parent::span")
    WebElement subwayList;

    @FindBy(xpath = "//*[@class='tag tag_close js-filter_selected_item margin_left_10']//*[contains(@class,'tag__text')]")
    WebElement genreList;

    @FindBy(xpath = "//*[@class='margin_top_20 js-module']//*[@name='is_2d']")
    WebElement twoD;

    @FindBy(xpath = "//*[@class='margin_top_20 js-module']//*[@name='is_3d']")
    WebElement threeD;

    @FindBy(xpath = "//*[@class='title__title']")
    WebElement title;

    public ResultPage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }

    public void checkTitle(){
        assertEquals("Киноафиша Москвы",title.getText());
    }

    public void whenCheck(String whenTyped) {
        String whenText = when.getText();
        assertEquals(whenText, whenTyped);
        System.out.println("когда: " + whenText);
    }

    public void subwaysCheck(ArrayList subwaysSelected){

        ArrayList<String> subwaysText = (ArrayList<String>)subwaysSelected.clone();

        List<String> subwaysActual = new ArrayList<String>();

            for (String s : subwaysText) {

                //String element = subwayList.findElement(By.xpath("child::span[text()='"+s+"']")).getText();
                String element = subwayList.findElement(By.xpath("parent::div[@data-id='"+s+"']")).getAttribute("data-id");
                subwaysActual.add(element);

            }

            System.out.println("subways data-id's: " + subwaysActual);

        assertEquals(subwaysSelected, subwaysActual);
    }

    public void genresCheck(ArrayList genresSelected) throws InterruptedException {

        ArrayList<String> genresText = (ArrayList<String>)genresSelected.clone();

        List<String> genresActual = new ArrayList<String>();

            for (String s : genresText){

                Thread.sleep(500);
                String element = genreList.findElement(By.xpath("//parent::span/child::span[text()='"+s+"']")).getText();
                genresActual.add(element);

            }

            System.out.println(genresActual);

        assertEquals(genresSelected, genresActual);
    }

    public void threeDCheckboxCheck(String menuItem){

        if(menuItem == "2D"){

            Assert.assertTrue(twoD.isSelected());
            Assert.assertFalse(threeD.isSelected());
            System.out.println("2D is selected");

        }

        if(menuItem == "3D"){

            Assert.assertFalse(twoD.isSelected());
            Assert.assertTrue(threeD.isSelected());
            System.out.println("3D is selected");

        }
    }
    

}

