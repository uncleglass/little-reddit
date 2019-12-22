package pl.uncleglass.littlereddit;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class LittleRedditApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittleRedditApplication.class, args);
    }

    @Bean
    PrettyTime getPrettyTime() {//TODO move it to separate configuration file
        return new PrettyTime();
    }

}
