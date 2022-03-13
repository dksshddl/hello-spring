package dksshddl.cloudpractice;

import lombok.Getter;

@Getter
public class SimpleEntity2 {
    private String name;
    private String description;

    public SimpleEntity2() {}

    public SimpleEntity2(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
