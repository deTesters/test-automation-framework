package nl.detesters.taf.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebRadioButton extends WebButton{
    private static final Logger LOG = LoggerFactory.getLogger(WebRadioButton.class);

    /**
     * Radio button is checked.
     *
     * @return boolean
     */
    public boolean isChecked() {
        LOG.debug("Checking if radio button [" + super.getText() + "] is checked.");
        return getAttribute("checked").equals("true");
    }
}
