package au.com.app.springcustomerapp;

import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PredictionService {
  @Timed("prediction.algorithm")
  public Integer predictionAlgorithm() {
    try {
      int millis = new Random().nextInt(5);
      int seconds = millis * 10;
      Thread.sleep(seconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return 0;
  }
}
