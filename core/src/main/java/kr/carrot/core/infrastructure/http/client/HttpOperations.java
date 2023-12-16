package kr.carrot.core.infrastructure.http.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public interface HttpOperations {
    <T> ResponseEntity<T> get(URI uri, Class<T> clazz);

    <T> ResponseEntity<T> get(URI uri, HttpHeaders headers, Class<T> clazz);
}
