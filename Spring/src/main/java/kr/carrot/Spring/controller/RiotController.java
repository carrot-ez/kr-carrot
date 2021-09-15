package kr.carrot.Spring.controller;

import kr.carrot.Spring.dto.com.ComResponseDto;
import kr.carrot.Spring.dto.req.ApiKeyReq;
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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api-key")
    public ComResponseDto<?> registerApiKey(@RequestBody ApiKeyReq apiKeyReq) {

        String result = riotService.registerApiKey(apiKeyReq.getApiKey());
        return ComResponseDto.success(result);
    }

    @GetMapping("/summoner/{summonerName}/{count}")
    public ComResponseDto<?> retrieveSummonerInfo(@PathVariable String summonerName, @PathVariable int count) {

        SummonerHistory summonerHistory = riotService.getSummonerHistory(summonerName, count);
        return ComResponseDto.success(summonerHistory);
    }
}
