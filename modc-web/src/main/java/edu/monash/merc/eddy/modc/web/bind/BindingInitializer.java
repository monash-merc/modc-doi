package edu.monash.merc.eddy.modc.web.bind;

import edu.monash.merc.eddy.modc.web.support.DateTypeEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Created by simonyu on 6/08/2014.
 */
public class BindingInitializer implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, new DateTypeEditor());
    }
}
