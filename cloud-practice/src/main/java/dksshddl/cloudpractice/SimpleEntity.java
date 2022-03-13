package dksshddl.cloudpractice;

import dksshddl.cloudpractice.client.SimpleClient;
import lombok.Getter;

@Getter
public class SimpleEntity {
    String mappedName;
    String mappedDesc;
    public SimpleEntity() {}
    public SimpleEntity(String mappedName, String mappedDesc) {
        this.mappedName = mappedName;
        this.mappedDesc = mappedDesc;
    }
}
