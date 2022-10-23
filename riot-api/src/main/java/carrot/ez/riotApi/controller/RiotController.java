package carrot.ez.riotApi.controller;

import carrot.ez.riotApi.dto.SummonerDto;
import carrot.ez.riotApi.dto.com.ComResponseDto;
import carrot.ez.riotApi.dto.req.ApiKeyReq;
import carrot.ez.riotApi.dto.res.SummonerHistory;
import carrot.ez.riotApi.service.RiotService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
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
