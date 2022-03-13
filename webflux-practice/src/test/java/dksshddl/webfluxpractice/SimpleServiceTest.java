package dksshddl.webfluxpractice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.scheduler.Schedulers;

@Slf4j
@SpringBootTest
public class SimpleServiceTest {
    @Autowired
    SimpleService simpleService;

    @Test
    void test() {
        simpleService.getData()
                .log()
                .publishOn(Schedulers.newParallel("p1"))
                .doOnNext((e) -> System.out.println(e.hashCode()+ " " +e.get(0) + ", thread name: " + Thread.currentThread().getName()))
                .doOnSuccess((e)->System.out.println(e.hashCode()+ " " +"success"))
                .log()
                .publishOn(Schedulers.newParallel("p2"))
                .doOnNext((e) -> System.out.println((e.hashCode()+ " " +e.get(1) + ", thread name: " + Thread.currentThread().getName())))
                .doOnSuccess((e)->System.out.println(e.hashCode()+ " " +"success"))
                .log()
                .publishOn(Schedulers.newParallel("p3"))
                .doOnNext((e) -> System.out.println((e.hashCode()+ " " +e.get(2) + ", thread name: " + Thread.currentThread().getName())))
                .doOnSuccess((e)->System.out.println(e.hashCode()+ " " +"success"))
                .block();

    }
}
