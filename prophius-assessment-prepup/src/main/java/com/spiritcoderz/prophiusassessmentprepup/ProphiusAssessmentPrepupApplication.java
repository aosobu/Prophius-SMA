package com.spiritcoderz.prophiusassessmentprepup;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
@EnableScheduling
@RequiredArgsConstructor
public class ProphiusAssessmentPrepupApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProphiusAssessmentPrepupApplication.class, args);
	}

}