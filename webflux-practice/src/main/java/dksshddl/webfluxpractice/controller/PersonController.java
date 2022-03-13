package dksshddl.webfluxpractice.controller;

import dksshddl.webfluxpractice.domain.Person;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PersonController {

    @GetMapping("/person")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Mono<Person> getPerson() {
        return Mono.just(new Person("dksshddl"));
    }
}
