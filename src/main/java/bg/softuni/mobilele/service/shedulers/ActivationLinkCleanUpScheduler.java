package bg.softuni.mobilele.service.shedulers;

import bg.softuni.mobilele.service.UserActivationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActivationLinkCleanUpScheduler {

    private final UserActivationService userActivationService;

    public ActivationLinkCleanUpScheduler(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    //    @Scheduled(cron = "*/10 * * * * *")
    @Scheduled(fixedRate = 10_000,
    initialDelay = 10_000)
    public void cleanUp() {

        System.out.println("Trigger of cleanup activation links!"  + LocalDateTime.now());

        userActivationService.cleanUpObsoleteActivationLink();

    }
}
