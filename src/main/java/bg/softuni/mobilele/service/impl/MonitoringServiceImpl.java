package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.service.MonitoringService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MonitoringServiceImpl implements MonitoringService {

    private final Counter offerSearches;

    public MonitoringServiceImpl(MeterRegistry meterRegistry) {
        offerSearches = Counter
                .builder("offer_search_cnt")
                .description("How many offer searches we have performed")
                .register(meterRegistry);
    }

    @Override
    public void logOfferSearch() {
        offerSearches.increment();
    }
}
