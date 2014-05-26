package me.healthcare.controllers;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import me.healthcare.Config;
import me.healthcare.model.AccessToken;
import me.healthcare.model.BloodPressureData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class HealthCheckController {

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView auth() {
    	
        String getCodeUrl = Config.authorizationUrl
                + "?client_id=" + Config.clientId
                + "&redirect_uri=" + Config.redirectUrl
                + "&APIName=OpenApiBP&response_type=code";

        return new ModelAndView("redirect:" + getCodeUrl);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(@RequestParam(value="code", required=false, defaultValue="") String code,
                       Model model, HttpServletRequest request) {
    	code = code.trim();
    	if (code.isEmpty()) {
            return "welcome";
    	}
    	else {
            AccessToken accessToken = getAccessToken(code);
            if (accessToken.errorCode == 0 && accessToken.accessToken != null) {
                BloodPressureData data = getBloodPressureData(accessToken);
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
    
    private AccessToken getAccessToken(String code) {

        String authUrl = Config.authorizationUrl
                + "?client_id=" + Config.clientId
                + "&client_secret=" + Config.clientSecret
                + "&redirect_uri=" + Config.redirectUrl
                + "&code=" + code
                + "&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(authUrl, AccessToken.class);
    }

    private BloodPressureData getBloodPressureData(AccessToken token) {
        Long currentTime = new Date().getTime();
        String bpUrl = Config.apiUrl
                + "user/" + token.userId + "/bp.json/"
                + "?access_token=" + token.accessToken
                + "&client_id=" + Config.clientId
                + "&client_secret=" + Config.clientSecret
                + "&redirect_uri=" + Config.redirectUrl
                + "&sc=" + Config.sc
                + "&sv=" + Config.svBp
                + "&start_time=1314752818"
                //+ "&end_time=" + currentTime
                + "&page_index=1"
                + "&locale=en_US"
                ;

        BloodPressureData data = new RestTemplate().getForObject(bpUrl, BloodPressureData.class);

        //String resp = new RestTemplate().getForObject(bpUrl, String.class);

        //BloodPressureData data = new BloodPressureData();

        return data;
    }
}
