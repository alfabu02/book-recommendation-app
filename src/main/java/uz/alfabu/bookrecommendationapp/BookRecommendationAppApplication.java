package uz.alfabu.bookrecommendationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import uz.alfabu.bookrecommendationapp.config.AppTokenConfig;

@EnableAsync
@EnableScheduling
@EnableConfigurationProperties({AppTokenConfig.class})
@SpringBootApplication
public class BookRecommendationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookRecommendationAppApplication.class, args);
    }

}
