package dksshddl.cloudpractice.client;

import dksshddl.cloudpractice.QueryVO;
import dksshddl.cloudpractice.SimpleEntity;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;
import java.util.List;
import java.util.Map;

public interface SimpleClient {

    @GetMapping
    List<SimpleEntity> getTest0(URI uri);
    @GetMapping
    List<SimpleEntity> getTest00(URI uri, @SpringQueryMap Map<String, String> query);
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    List<SimpleEntity> getTest1(URI uri, @RequestBody Object body);
    @GetMapping
    List<SimpleEntity> getTest2(URI uri, @RequestHeader Map<String, Object> header);
    @PostMapping
    List<SimpleEntity> getTest3(URI uri,
                          @RequestHeader String header,
                          @RequestBody SimpleEntity body);
}
