package com.spiritcoderz.prophiusassessmentprepup;

import com.spiritcoderz.prophiusassessmentprepup.comments.repository.CommentRepository;
import com.spiritcoderz.prophiusassessmentprepup.posts.entity.Post;
import com.spiritcoderz.prophiusassessmentprepup.posts.entity.PostLike;
import com.spiritcoderz.prophiusassessmentprepup.posts.repository.PostEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.posts.repository.PostLikeEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.Followers;
import com.spiritcoderz.prophiusassessmentprepup.users.entity.User;
import com.spiritcoderz.prophiusassessmentprepup.users.enums.Role;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.FollowerEntityManager;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.FollowerRepository;
import com.spiritcoderz.prophiusassessmentprepup.users.repository.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
@EnableScheduling
public class ProphiusAssessmentPrepupApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProphiusAssessmentPrepupApplication.class, args);
	}
}