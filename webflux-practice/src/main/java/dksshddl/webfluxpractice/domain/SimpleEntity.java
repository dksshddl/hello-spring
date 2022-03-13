package dksshddl.webfluxpractice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class SimpleEntity {
    private String name;
    private String description;

    public SimpleEntity() {}

    public SimpleEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
