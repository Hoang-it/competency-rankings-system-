package com.fa.training.demo.entities;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Arrays;

@Getter
@Setter
// Using for verify google reCaptcha v2
public class ReCaptchaResponse {

    private boolean success;
    private String hostname;
    private String challenge_ts;

    @JsonProperty("error-codes")
    private String[] errorCodes;

    @Override
    public String toString() {
        return "ReCaptchaResponse{" +
                "success=" + success +
                ", hostname='" + hostname + '\'' +
                ", challenge_ts='" + challenge_ts + '\'' +
                ", errorCodes=" + Arrays.toString(errorCodes) +
                '}';
    }
}
