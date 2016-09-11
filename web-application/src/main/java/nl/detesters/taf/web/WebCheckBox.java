package nl.detesters.taf.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebCheckBox extends WebElement{
    private static final Logger LOG = LoggerFactory.getLogger(WebCheckBox.class);

    public boolean isChecked(){
        LOG.debug(String.format("Getting element checked state: %s", isSelected()));
        return isSelected();
    }

    public void setChecked(boolean check){
        if (check != isChecked()){
            LOG.debug(String.format("Setting element checked state to: %s", check));
            click();
        }
    }
}
