package au.com.app.springcustomerapp;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
public class SpringCustomerAppApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringCustomerAppApplication.class, args);
  }

  @Bean
  LoggingMeterRegistry loggingMeterRegistry() {
    return new LoggingMeterRegistry(new LoggingRegistryConfig() {
      @Override
      public String get(String key) {
        return null;
      }

      @Override
      public Duration step() {
        return Duration.ofSeconds(10);
      }
    }, Clock.SYSTEM);
  }

  @Bean
  public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry -> registry.config().commonTags("host", "voicestreams", "service", "baseline");
  }

  @Bean
  public TimedAspect timedAspect(MeterRegistry registry) {
    return new TimedAspect(registry);
  }
}
