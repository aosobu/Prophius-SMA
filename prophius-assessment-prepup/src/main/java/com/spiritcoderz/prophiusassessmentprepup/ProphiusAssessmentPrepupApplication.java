package com.spiritcoderz.prophiusassessmentprepup;

import com.spiritcoderz.prophiusassessmentprepup.users.repository.FollowerEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
@EnableScheduling
@RequiredArgsConstructor
public class ProphiusAssessmentPrepupApplication {

	private final CacheManager cacheManager;
	private final FollowerEntityManager followerEntityManager;
	public static void main(String[] args) {
		SpringApplication.run(ProphiusAssessmentPrepupApplication.class, args);
	}

}