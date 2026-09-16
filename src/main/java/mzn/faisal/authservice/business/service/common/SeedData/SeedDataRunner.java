package mzn.faisal.authservice.business.service.common.SeedData;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeedDataRunner implements CommandLineRunner {

    private final SeedDataService seedDataService;

    @Override
    public void run(String... args) {
        seedDataService.Seed();
    }
}
