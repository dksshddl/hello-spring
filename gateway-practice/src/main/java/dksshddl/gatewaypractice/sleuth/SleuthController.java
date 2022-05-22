package dksshddl.gatewaypractice.sleuth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
public class SleuthController {

    @Autowired
    private SleuthService sleuthService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public List<String> helloSleuth() throws InterruptedException {
        log.info("start hello sleuth");
        List<String> strs = restTemplate.exchange("http://localhost:8086/str", HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>(){}).getBody();
        sleuthService.doWork();
        return strs;
    }

    @GetMapping("/2")
    public String helloSleuth2() {
        log.info("hello Sleuth2");
        return "success";
    }
}
