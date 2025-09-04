package com.coffee.controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final WebClient webClient;

    public AIController(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://dashscope.aliyuncs.com/compatible-mode").build();
    }

    @PostMapping("/stream")
    public SseEmitter streamAI(@RequestBody Map<String, Object> payload) {
        SseEmitter emitter = new SseEmitter(0L); // 无限超时
        Map<String, Object> body = new HashMap<>();
        body.put("model", "qwen3-30b-a3b-thinking-2507");
        body.put("messages",  payload.get("content"));  //
        body.put("stream", true);
        // 异步调用
        new Thread(() -> {
            try {
                Flux<String> responseFlux = webClient.post()
                        .uri("/v1/chat/completions")
                        .header("Authorization", "Bearer sk-51d1402d515e4afd9d86b434a09ecc48")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(body)
                        .retrieve()
                        .bodyToFlux(String.class);

                responseFlux
                        .delayElements(Duration.ofMillis(100)) // 防止过快
                        .doOnNext(chunk -> {
                            try {
                                emitter.send(SseEmitter.event().name("ai-stream").data(chunk));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        })
                        .doOnComplete(emitter::complete)
                        .subscribe();

            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;
    }
}


