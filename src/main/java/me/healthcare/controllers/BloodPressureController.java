package me.healthcare.controllers;

import javax.servlet.http.HttpServletRequest;

import me.healthcare.model.AccessToken;
import me.healthcare.model.BloodPressureData;
import me.healthcare.restconsumers.HealthLabsConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BloodPressureController {

    private final HealthLabsConsumer restConsumer;

    @Autowired
    public BloodPressureController(HealthLabsConsumer restConsumer) {
        this.restConsumer = restConsumer;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView auth() {
        String codeUrl = restConsumer.getCodeUrl("OpenApiBP");
        return new ModelAndView("redirect:" + codeUrl);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(@RequestParam(value="code", required=false, defaultValue="") String code,
                       Model model, HttpServletRequest request) {
        code = code.trim();
        AccessToken accessToken = (AccessToken)request.getSession().getAttribute("accessToken");

        // Show login prompt
    	if (code.isEmpty() && accessToken == null) {
            return "welcome";
    	}

        if (accessToken == null)
            accessToken = restConsumer.getAccessToken(code);

        if (accessToken.errorCode == 0 && accessToken.accessToken != null) {
            request.getSession().setAttribute("accessToken", accessToken);

            BloodPressureData data = restConsumer.getBloodPressureData(accessToken);
            model.addAttribute("data", data);
            return "bp";
        }
        else {
            model.addAttribute("error", accessToken.error);
            model.addAttribute("errorCode", accessToken.errorCode);
            model.addAttribute("errorDescription", accessToken.errorDescription);
            return "error";
        }
    }
}
