package com.fa.training.demo.service;

import com.fa.training.demo.entities.ReCaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReCaptchaService {

    @Value("${recaptcha.key.secret}")
    private String keySecret;

    @Value("${recaptcha.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * validate ReCaptcha from google
     */
    public boolean validateReCaptcha(String gRecaptchaResponse) {
        String params = "?secret=" + keySecret + "&response=" + gRecaptchaResponse;
        ReCaptchaResponse reCaptchaResponse =
                restTemplate.exchange(url + params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
        if (reCaptchaResponse.isSuccess()) {
            return true;
        }
        return false;
    }
}
