package dksshddl.gatewaypractice.sleuth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SleuthService {

    public void doWork() throws InterruptedException {
        Thread.sleep(1000L);
        log.info("do work");
    }
}
