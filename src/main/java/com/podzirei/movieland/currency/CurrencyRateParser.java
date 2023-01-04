package com.podzirei.movieland.currency;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
@CurrencyParser
@Slf4j
public class CurrencyRateParser {

    private final String URL_TO_NBU_RATES = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private final RestTemplate restTemplate = new RestTemplate();
    private volatile List<CurrencyDataDto> cachedExchangeRate;

    public List<CurrencyDataDto> getAllRates(){
        if (cachedExchangeRate.isEmpty()){
            updateCurrencyCache();
        }
        return new CopyOnWriteArrayList<>(cachedExchangeRate);
    }

    @SneakyThrows
    @Scheduled(cron = "0 0 8-10 * * *", zone="Europe/Kiev")
    @PostConstruct
    @Transactional(readOnly = true)
    public void updateCurrencyCache() {
        ResponseEntity<List<CurrencyDataDto>> responseEntity =
                restTemplate.exchange(
                        URL_TO_NBU_RATES,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        }
                );
        cachedExchangeRate = responseEntity.getBody();
        log.info("exchange rates cache updated");
    }
}

@Component
@Retention(RetentionPolicy.RUNTIME)
@interface CurrencyParser {

}