package kr.carrot.Spring.controller;

import kr.carrot.Spring.dto.com.ComResponseDto;
import kr.carrot.Spring.dto.res.SummonerHistory;
import kr.carrot.Spring.service.RiotService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RiotController {

    private final RiotService riotService;

    @PostMapping("/api-key")
    public HttpEntity<?> registerApiKey(String apiKey) {

        ComResponseDto<?> body = null;
        HttpStatus status = null;

        try {
            String result = riotService.registerApiKey(apiKey);
            body = ComResponseDto.success(result);
            status = HttpStatus.OK;
        }
        // TODO CHANGE CATCH BLOCK
        catch (Exception e) {
            body = ComResponseDto.fail(e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(body, status);
    }

    @GetMapping("/summoner/{summonerName}/{count}")
    public HttpEntity<?> retrieveSummonerInfo(@PathVariable String summonerName, @PathVariable int count) {

        ComResponseDto<?> body = null;
        HttpStatus status = null;

        try {
            SummonerHistory summonerHistory = riotService.getSummonerHistory(summonerName, count);
            body = ComResponseDto.success(summonerHistory);
            status = HttpStatus.OK;
        }
        catch (Exception e) {
            body = ComResponseDto.fail(e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(body, status);
    }
}
