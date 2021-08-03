package uk.co.mulecode.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EchoController {

    @GetMapping("/echo/**")
    public Map<String, Object> getEcho(@RequestHeader Map<String, String> headers,
                                       @RequestParam Map<String, String> allParams) {
        return Map.of(
                "requestParam", allParams,
                "requestHeader", headers
        );
    }

    @PostMapping("/echo/**")
    public Map<String, Object> postEcho(@RequestBody Map<String, String> body,
                                        @RequestHeader Map<String, String> headers,
                                        @RequestParam Map<String, String> allParams) {
        return Map.of(
                "requestParam", allParams,
                "requestHeader", headers,
                "requestBody", body
        );
    }
}
