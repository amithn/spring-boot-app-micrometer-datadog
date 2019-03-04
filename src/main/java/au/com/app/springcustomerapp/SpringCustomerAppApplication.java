package au.com.app.springcustomerapp;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Random;

@SpringBootApplication
@RestController
public class SpringCustomerAppApplication {
	private Timer timer = null;

	@Autowired
	private MeterRegistry meterRegistry;


	@Value("${DEPLOYMENT_TYPE:DONT_KNOW}")
	private String deploymentType;

	public static void main(String[] args) {
		SpringApplication.run(SpringCustomerAppApplication.class, args);
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@Timed("helloworld_request")
	public Hello helloWorld() {

//		Timer timer = Timer
//				.builder("my.timer")
//				.description("a description of what this timer does") // optional
//				.tags("region", "test") // optional
//				.register(meterRegistry);
//
//		timer.record(() -> predictionAlgorithm());

		predictionAlgorithm();
		return new Hello("Hello from Cloud Foundry Spinnaker - TEST RUN 4  : " + deploymentType);
	}

	@Timed("PREDICTION_ALGORITHM")
	public void predictionAlgorithm() {
		try {
			int millis = new Random().nextInt(5);
			int seconds = millis * 10;
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public Result add(@RequestBody  Customer customer) {
		// Save the database
		return new Result( customer.getName().toLowerCase() , "OK");
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("deploymentType = " + deploymentType);

	}
}
