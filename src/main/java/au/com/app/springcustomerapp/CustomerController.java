package au.com.app.springcustomerapp;

import au.com.app.springcustomerapp.model.Customer;
import au.com.app.springcustomerapp.model.Hello;
import au.com.app.springcustomerapp.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
  private final String deploymentType;
  private final PredictionService predictionService;

  public CustomerController(@Value("${DEPLOYMENT_TYPE:DONT_KNOW}") String deploymentType,
                            PredictionService predictionService) {
    this.deploymentType = deploymentType;
    this.predictionService = predictionService;
    System.out.println("deploymentType = " + deploymentType);
  }

  @GetMapping("/hello")
  public Hello helloWorld() {
    predictionService.predictionAlgorithm();
    return new Hello("Hello from Cloud Foundry Spinnaker - TEST RUN 4  : " + deploymentType);
  }

  @PostMapping("/customers")
  public Result add(@RequestBody Customer customer) {
    // Save the database
    return new Result(customer.getName().toLowerCase(), "OK");
  }
}