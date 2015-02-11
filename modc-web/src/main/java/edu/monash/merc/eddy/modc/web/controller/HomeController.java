package edu.monash.merc.eddy.modc.web.controller;

import edu.monash.merc.eddy.modc.web.conts.MConts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by simonyu on 4/08/2014.
 */
@Controller
public class HomeController extends BaseController {

    @RequestMapping(value = "/home")
    public String home() {
//        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return "home";
    }
}
