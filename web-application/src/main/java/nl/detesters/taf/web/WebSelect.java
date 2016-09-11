package nl.detesters.taf.web;

import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSelect extends WebElement {
    private static final Logger LOG = LoggerFactory.getLogger(WebSelect.class);
    private Select selectElement;

    public WebSelect(){
    }

    public void select(String visibleText){
        getSelect().selectByVisibleText(visibleText);
    }

    private Select getSelect(){
        if (selectElement == null){
            selectElement = new Select(this);
        }
        return selectElement;
    }
}
