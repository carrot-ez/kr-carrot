package kr.carrot.Spring.controller;

import kr.carrot.Spring.dto.SummonerDto;
import kr.carrot.Spring.dto.com.ComResponseDto;
import kr.carrot.Spring.dto.req.ApiKeyReq;
import kr.carrot.Spring.dto.res.SummonerHistory;
import kr.carrot.Spring.entity.KeyEntity;
import kr.carrot.Spring.repository.KeyRepository;
import kr.carrot.Spring.service.RiotService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Cacheable(value = "history", key = "#summonerName")
    @GetMapping("/summoners/histories/{summonerName}")
    public ComResponseDto<SummonerHistory> retrieveSummonerHistories(@PathVariable String summonerName) {
        SummonerHistory history = riotService.getHistory(summonerName);
        return ComResponseDto.success(history);
    }

    @CachePut(value = "history", key = "#summonerName")
    @GetMapping("/summoners/histories/re/{summonerName}")
    public ComResponseDto<SummonerHistory> retrieveSummonerHistoriesRefresh(@PathVariable String summonerName) {
        SummonerHistory history = riotService.getHistory(summonerName);
        return ComResponseDto.success(history);
    }

    @GetMapping("/summoners/{summonerName}")
    public ComResponseDto<SummonerDto> retrieveSummonerInfo(@PathVariable String summonerName) {
        SummonerDto summoner = riotService.findSummonerInfoByName(summonerName)
                .orElseThrow(() -> new IllegalArgumentException("summoner not found"));

        return ComResponseDto.success(summoner);
    }
}
