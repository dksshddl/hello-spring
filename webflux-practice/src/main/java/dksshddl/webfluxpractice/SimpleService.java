package dksshddl.webfluxpractice;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SimpleService {
    public Mono<List<String>> getData() {
        return Mono.fromCallable(() -> List.of("a", "b", "c"));
    }
}
