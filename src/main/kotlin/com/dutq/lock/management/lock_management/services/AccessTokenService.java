package com.dutq.lock.management.lock_management.services;

import com.dutq.lock.management.lock_management.dtos.NewTokenResponse;
import com.dutq.lock.management.lock_management.dtos.RefreshTokenResponse;
import com.dutq.lock.management.lock_management.exceptions.EUAPIRestException;
import com.dutq.lock.management.lock_management.utility.EUAPIResponse;
import com.dutq.lock.management.lock_management.utility.EUAPIResponseParser;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Service
@Slf4j
public class AccessTokenService {
    private String refreshToken;

    @Value("${eu.api.base.url}")
    private String baseURL;

    @Value("${eu.api.client.id}")
    private String clientId;

    @Value("${eu.api.client.secret}")
    private String clientSecret;

    @Value("${eu.api.client.username}")
    private String username;

    @Value("${eu.api.refresh.password}")
    private String password;

    private String accessToken;
    private Instant tokenExpiryTime;
    private final RestTemplate restTemplate;
    private final Lock lock = new ReentrantLock();

    public AccessTokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.accessToken = "";
        this.tokenExpiryTime = Instant.now();
        this.refreshToken = "";
    }

    public String getAccessToken() {
        if (StringUtils.isBlank(refreshToken)) {
            getNewToken();
        }
        else if (Instant.now().isAfter(tokenExpiryTime)) {
            refreshToken();
        }
        return accessToken;
    }

    private void refreshToken() {
        lock.lock();
        try {
            if (Instant.now().isAfter(tokenExpiryTime)) {
                // Call API to get a new token
                RefreshTokenResponse response = refresh();
                this.accessToken = response.getAccess_token();
                this.tokenExpiryTime = Instant.now().plusSeconds(response.getExpires_in());
                this.refreshToken = response.getRefresh_token();
            }
        } finally {
            lock.unlock();
        }
    }

    private void getNewToken() {
        lock.lock();
        try {
            RefreshTokenResponse response = getNewAccessToken();
            this.accessToken = response.getAccess_token();
            this.tokenExpiryTime = Instant.now().plusSeconds(response.getExpires_in());
            this.refreshToken = response.getRefresh_token();
        } finally {
            lock.unlock();
        }
    }

    private RefreshTokenResponse getNewAccessToken() {
        MultiValueMap<String, String> form =
                new LinkedMultiValueMap<String, String>();
        form.set("clientId", clientId);
        form.set("clientSecret", clientSecret);
        form.set("username", username);
        form.set("password", password);
        String url = baseURL + "/oauth2/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(form, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        EUAPIResponseParser<NewTokenResponse> parser = new EUAPIResponseParser<>(NewTokenResponse.class);
        EUAPIResponse<NewTokenResponse> refreshResponse = parser.parse(response.getBody());

        if (refreshResponse.getError().isPresent()) {
            log.error("EU API error {}", refreshResponse.getError().get());
            throw new EUAPIRestException(refreshResponse.getError().get().getErrmsg());
        }
        return refreshResponse.getData().get();

    }

    private RefreshTokenResponse refresh() {
        MultiValueMap<String, String> form =
                new LinkedMultiValueMap<String, String>();
        form.set("clientId", clientId);
        form.set("clientSecret", clientSecret);
        form.set("grant_type", "refresh_token");
        form.set("refresh_token", refreshToken);
        String url = baseURL + "/oauth2/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(form, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        EUAPIResponseParser<RefreshTokenResponse> parser = new EUAPIResponseParser<>(RefreshTokenResponse.class);
        EUAPIResponse<RefreshTokenResponse> refreshResponse = parser.parse(response.getBody());

        if (refreshResponse.getError().isPresent()) {
            log.error("EU API error {}", refreshResponse.getError().get());
            throw new EUAPIRestException(refreshResponse.getError().get().getErrmsg());
        }
        return refreshResponse.getData().get();

    }
}
