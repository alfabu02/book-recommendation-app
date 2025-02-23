package uz.alfabu.bookrecommendationapp.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import uz.alfabu.bookrecommendationapp.service.contract.VerificationCodeService;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ScheduledTasks {
    private final VerificationCodeService verificationCodeService;

    @Scheduled(cron = "0 30 * * * *")
    public void scheduledTask() {
        log.info("expired code cleared!");
        verificationCodeService.clearExpiredCodes();
    }

}
