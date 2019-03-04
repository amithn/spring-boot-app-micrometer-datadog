package au.com.app.springcustomerapp;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricConfig {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(MeterRegistry meterRegistry) {

        return registry -> registry.config()
                .commonTags("host", "voicestreams", "service", "baseline");
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}
