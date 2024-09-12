package com.dutq.lock.management.lock_management.services;

import com.dutq.lock.management.lock_management.dtos.PassCodeResponse;
import com.dutq.lock.management.lock_management.dtos.api.ErrorResponse;
import com.dutq.lock.management.lock_management.entites.LockDTO;
import com.dutq.lock.management.lock_management.entites.LockListResponse;
import com.dutq.lock.management.lock_management.exceptions.EUAPIRestException;
import com.dutq.lock.management.lock_management.utility.EUAPIResponse;
import com.dutq.lock.management.lock_management.utility.EUAPIResponseParser;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class LockService {
  private RestTemplate restTemplate;
  private AccessTokenService accessTokenService;

  @Value("${eu.api.client.id}")
  private String clientId;

  @Value("${eu.api.base.url.v3}")
  private String BASE_URL;

  public LockService(RestTemplate restTemplate, AccessTokenService accessTokenService) {
    this.accessTokenService = accessTokenService;
    this.restTemplate = restTemplate;
  }

  public List<LockDTO> getLocks(int pageSize, int pageNumber) {

    String baseUrl = BASE_URL + "/lock/list";
    String url =
        UriComponentsBuilder.fromHttpUrl(baseUrl)
            .queryParam("clientId", clientId)
            .queryParam("accessToken", accessTokenService.getAccessToken())
            .queryParam("pageNo", String.valueOf(pageNumber))
            .queryParam("pageSize", String.valueOf(pageSize))
            .queryParam("date", Instant.now().toEpochMilli())
            .toUriString();

    String lockListString = restTemplate.getForObject(url, String.class);
    EUAPIResponseParser<LockListResponse> parser =
        new EUAPIResponseParser<>(LockListResponse.class);
    EUAPIResponse<LockListResponse> lockListResponse = parser.parse(lockListString);

    if (lockListResponse.getError().isPresent()) {
      throw new EUAPIRestException(lockListResponse.getError().get().getErrmsg());
    }

    return lockListResponse
        .getData()
        .map(
            lockList ->
                lockList.getList().stream()
                    .map(
                        l ->
                            LockDTO.builder()
                                .lockId(String.valueOf(l.getLockId()))
                                .lockAlias(l.getLockAlias())
                                .electricQuantity(l.getElectricQuantity())
                                .build())
                    .collect(Collectors.toList()))
        .orElse(Collections.emptyList());
  }

  public void unlock(String lockId) {
    MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
    form.set("clientId", clientId);
    form.set("accessToken", accessTokenService.getAccessToken());
    form.set("lockId", lockId);
    form.set("date", Instant.now().toEpochMilli() + "");
    String url = BASE_URL + "/lock/unlock";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(form, headers);
    ResponseEntity<String> response =
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    EUAPIResponseParser<ErrorResponse> parser = new EUAPIResponseParser<>(ErrorResponse.class);
    EUAPIResponse<ErrorResponse> unlockResponse = parser.parse(response.getBody());

    if (unlockResponse.getError().isPresent()) {
      log.error("EU API error {}", unlockResponse.getError().get());
      throw new EUAPIRestException(unlockResponse.getError().get().getErrmsg());
    }
  }

  public String getOpenCode(String lockId) {
    RestTemplate restTemplate = new RestTemplate();

    // Define base URL
    String baseUrl = BASE_URL + "/keyboardPwd/get";

    MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
    form.set("clientId", clientId);
    form.set("accessToken", accessTokenService.getAccessToken());
    form.set("lockId", lockId);
    form.set("date", Instant.now().toEpochMilli() + "");
    form.set("keyboardPwdType", "3");
    form.set("keyboardPwdName", "code");
    form.set("startDate", Instant.now().toEpochMilli() + "");
    form.set("endDate", Instant.now().plus(Duration.ofHours(1)).toEpochMilli() + "");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(form, headers);
    ResponseEntity<String> response =
        restTemplate.exchange(baseUrl, HttpMethod.POST, requestEntity, String.class);
    EUAPIResponseParser<PassCodeResponse> parser =
        new EUAPIResponseParser<>(PassCodeResponse.class);
    EUAPIResponse<PassCodeResponse> getCodeResponse = parser.parse(response.getBody());

    if (getCodeResponse.getError().isPresent()) {
      log.error("EU API error {}", getCodeResponse.getError().get());
      throw new EUAPIRestException(getCodeResponse.getError().get().getErrmsg());
    }

    if (getCodeResponse.getData().isEmpty()) {
      log.error("EU API response does not have data");
      throw new EUAPIRestException("Could not get code");
    }

    return getCodeResponse.getData().get().getKeyboardPwd();
  }
}
