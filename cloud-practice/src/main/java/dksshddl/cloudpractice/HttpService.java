package dksshddl.cloudpractice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dksshddl.cloudpractice.client.SimpleClient;
import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.config.ConfigData;
import org.springframework.cloud.openfeign.support.PageableSpringQueryMapEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.server.PathContainer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class HttpService {

    SimpleClient simpleClient;

    @Autowired
    Decoder decoder;
    @Autowired
    Encoder encoder;

    public List<SimpleEntity> test() {
        log.info("start reuqest http service");
        long l1 = System.currentTimeMillis();

        simpleClient = Feign.builder()
                .encoder(encoder)
                .mapAndDecode((response, type) -> {
                    long l2 = System.currentTimeMillis();
                    String str = "";
                    try {
                        str = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
                        str = str.replace("name", "mappedName");
                        str = str.replace("description", "mappedDesc");
                        log.info("decoding time: {} \n{}", (System.currentTimeMillis() - l2), str);
                        return response.toBuilder().body(str.getBytes()).build();
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }, decoder)
                .logger(new Logger() {
                    @Override
                    protected void log(String configKey, String format, Object... args) {
                        log.info(String.format(format, args));
                    }
                })
                .logLevel(Logger.Level.FULL)
                .contract(new SpringMvcContract())
                .target(SimpleClient.class, "name");

        Map<String, Object> header = new HashMap<>();
        header.put("test", "test header");
        header.put("test2", "test header2");
        QueryVO qv = QueryVO.builder().param1("a").param2("b").param3("c").param4("d").param5("e").build();
        QueryVO qv2 = QueryVO.builder().param1("a").param2("b").param3("c").param4("d").param5("e").build();
        Map<String, String> query = new HashMap<>();
        query.put("param1", "a");
        query.put("param2", "b");
        query.put("param3", "c");

//        List<SimpleEntity> entity00 = simpleClient.getTest00(URI.create("http://localhost:8081/api/entity"), query);
//        SimpleEntity entity0 = simpleClient.getTest0(URI.create("http://localhost:8081/api/entity"));
          List<SimpleEntity> entity1 = simpleClient.getTest1(URI.create("http://localhost:8081/api/entityWithBody"), new SimpleEntity2("m", "n"));
//        SimpleEntity entity2 = simpleClient.getTest2(URI.create("http://localhost:8081/api/entity"), header);
//        SimpleEntity entity3 = simpleClient.getTest3(URI.create("http://localhost:8081/api/entity"), "header key", new SimpleEntity("m", "n"));


//            SimpleEntity entity = simpleClient.getTest(URI.create("http://localhost:8081/api/entity"),
//                                                      new SimpleEntity("m", "n"));

        log.info("method run time: {}", System.currentTimeMillis() - l1);
        for (SimpleEntity en : entity1) {
            log.info("- entity mappedName: {}", en.getMappedName());
            log.info("- entity mappedDesc: {}", en.getMappedDesc());
        }

        return entity1;
    }
}

