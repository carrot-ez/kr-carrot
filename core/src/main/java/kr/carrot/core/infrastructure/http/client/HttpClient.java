package kr.carrot.core.infrastructure.http.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class HttpClient implements HttpOperations {
    private final RestTemplate restTemplate;

    public HttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> ResponseEntity<T> get(URI uri, Class<T> clazz) {
        RequestEntity<Void> requestEntity = RequestEntity.get(uri)
                .build();

        return restTemplate.exchange(requestEntity, clazz);
    }

    @Override
    public <T> ResponseEntity<T> get(URI uri, HttpHeaders headers, Class<T> clazz) {
        RequestEntity<Void> requestEntity = RequestEntity.get(uri)
                .headers(headers)
                .build();

        return restTemplate.exchange(requestEntity, clazz);
    }
}
