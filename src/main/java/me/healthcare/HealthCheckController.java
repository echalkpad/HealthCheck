package me.healthcare;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HealthCheckController {

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView auth() {
    	
        String authUrl = Config.authorizationUrl
                + "?client_id=" + Config.clientId
                + "&redirect_uri=" + Config.redirectUrl
                + "&APIName=OpenApiBP&response_type=code";

        return new ModelAndView("redirect:" + authUrl);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(@RequestParam(value="code", required=false, defaultValue="") String code, Model model, HttpServletRequest request) {
    	code = code.trim();
    	if (code.isEmpty()) {
    		model.addAttribute("message", request.getAttributeNames().nextElement());
            return "welcome";
    	}
    	else
    	{
    		if (getAccessToken(code, request)) {
    			return "bp";
    		}
    		else {
        		model.addAttribute("message", "ups...");
    			return "error";
    		}
    	}
    }
    
    private boolean getAccessToken(String code, HttpServletRequest request)
    {
    	return true;
    }
}
