package me.healthcare.restconsumers;

import me.healthcare.Config;
import me.healthcare.model.AccessToken;
import me.healthcare.model.BloodPressureData;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HealthLabsConsumer {

    public String getCodeUrl(String apiName) {
        String codeUrl = Config.authorizationUrl
                + "?client_id=" + Config.clientId
                + "&redirect_uri=" + Config.redirectUrl
                + "&APIName=" + apiName
                + "&response_type=code";

        return codeUrl;
    }

    public AccessToken getAccessToken(String code) {

        String authUrl = Config.authorizationUrl
                + "?client_id=" + Config.clientId
                + "&client_secret=" + Config.clientSecret
                + "&redirect_uri=" + Config.redirectUrl
                + "&code=" + code
                + "&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(authUrl, AccessToken.class);
    }

    public BloodPressureData getBloodPressureData(AccessToken token) {
        //Long currentTime = new Date().getTime();
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
