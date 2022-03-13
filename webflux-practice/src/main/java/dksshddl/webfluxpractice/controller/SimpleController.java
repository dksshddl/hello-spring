package dksshddl.webfluxpractice.controller;

import dksshddl.webfluxpractice.domain.SimpleEntity;
import dksshddl.webfluxpractice.SimpleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class SimpleController {

    private final SimpleService simpleService;

    @GetMapping("/get")
    public Mono<List<String>> getTest() {
        return simpleService.getData();
    }

    @GetMapping("/entity")
    public Mono<List<SimpleEntity>> getEntity0() {
        return Mono.fromCallable(()-> List.of(new SimpleEntity("n1", "d1"), new SimpleEntity("n2", "d2"),
                new SimpleEntity("n3", "d3")));
    }

    @PostMapping(value = "/entityWithBody", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<SimpleEntity>> getEntity1(@RequestBody SimpleEntity body) {
        log.info(body + "");
        return Mono.fromCallable(()-> List.of(new SimpleEntity("n1", "d1"), new SimpleEntity("n2", "d2"),
                new SimpleEntity("n3", "d3")));
    }

    @GetMapping(value = "/entityWithHeader")
    public Mono<List<SimpleEntity>> getEntity2(@RequestHeader(value = "test") String accept) {
        return Mono.fromCallable(()-> List.of(new SimpleEntity("n1", "d1"), new SimpleEntity("n2", "d2"),
                new SimpleEntity("n3", "d3")));
    }

    @PostMapping(value = "/entityWithBoth", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<SimpleEntity>> getEntity3(@RequestBody Object body, @RequestHeader Map<String, Object> header) {
        return Mono.fromCallable(()-> List.of(new SimpleEntity("n1", "d1"), new SimpleEntity("n2", "d2"),
                new SimpleEntity("n3", "d3")));
    }

}
