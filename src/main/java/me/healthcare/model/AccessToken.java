package me.healthcare.model;

/**
 * Created by alex on 5/25/14.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken {

    @JsonProperty("Error")
    public String error;

    @JsonProperty("ErrorDescription")
    public String errorDescription;

    @JsonProperty("ErrorCode")
    public int errorCode;

    @JsonProperty("APIName")
    public String apiName;

    @JsonProperty("AccessToken")
    public String accessToken;

    @JsonProperty("Expires")
    public int expirationDate;

    @JsonProperty("RefreshToken")
    public String refreshToken;

    @JsonProperty("UserID")
    public String userId;
}
