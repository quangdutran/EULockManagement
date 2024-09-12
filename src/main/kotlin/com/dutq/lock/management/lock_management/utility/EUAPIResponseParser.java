package com.dutq.lock.management.lock_management.utility;

import com.dutq.lock.management.lock_management.dtos.api.ErrorResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class EUAPIResponseParser<T> {

    private final Class<T> type;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EUAPIResponseParser(Class<T> type) {
        this.type = type;
    }

    public EUAPIResponse<T> parse(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);

            Optional<ErrorResponse> error = Optional.empty();
            Optional<T> data = Optional.empty();
            if (rootNode.has("errcode")) {
                int errorCode = rootNode.get("errcode").asInt();
                if (errorCode != 0) {
                    error = Optional.of(objectMapper.readValue(responseBody, ErrorResponse.class));
                }
            } else {
                data = Optional.of(objectMapper.readValue(responseBody, type));
            }
            return EUAPIResponse.<T>builder().data(data).error(error).build();
        } catch (Exception e) {
            log.error("Could not parse the EU API response", e);
            throw new RuntimeException("Internal Error");
        }
    }
}
