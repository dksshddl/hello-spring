package dksshddl.cloudpractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudPracticeApplicationTests {

	@Autowired
	HttpService httpService;

	@Test
	void feignTest() {
		httpService.test();
	}

}
