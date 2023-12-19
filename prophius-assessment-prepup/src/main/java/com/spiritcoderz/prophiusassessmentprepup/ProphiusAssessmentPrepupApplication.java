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
public class ProphiusAssessmentPrepupApplication implements CommandLineRunner {
	public final UserEntityManager userDataSource;
	public final CommentRepository commentRepository;
	private final FollowerRepository followerRepository;
	private final PostLikeEntityManager postLikeEntityManager;
	private CacheManager cacheManager;
	private final FollowerEntityManager followerEntityManager;

	private final PostEntityManager postEntityManager;


	@Autowired
	public ProphiusAssessmentPrepupApplication(UserEntityManager userDataSourcel,
											   CommentRepository commentRepository,
											   FollowerRepository followerRepository,
											   FollowerEntityManager followerEntityManager,
											   CacheManager cacheManager,
											   PostLikeEntityManager postLikeEntityManager,
											   PostEntityManager postEntityManager) {
		this.userDataSource = userDataSourcel;
		this.commentRepository = commentRepository;
		this.followerRepository = followerRepository;
		this.followerEntityManager = followerEntityManager;
		this.cacheManager = cacheManager;
		this.postLikeEntityManager = postLikeEntityManager;
		this.postEntityManager = postEntityManager;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProphiusAssessmentPrepupApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		PostLike postLike = new PostLike();
//		postLike.setPostId(2);
//		postLike.setCounted(false);
//
//		List<PostLike> postLikeList = new ArrayList<>();
//		postLikeList.add(postLike);

		//cacheManager.getCache("post-like").put("post-like-list-01", postLikeList);

		String email = "aosobu.dev@gmail.com";
		User user = User
				.builder()
				.username(email.substring(0, 10))
				.email(email)
				.profilePictureFilePath("http://localhost/images/default.png")
				.isEnabled(true)
				.password(new BCryptPasswordEncoder().encode("123456789"))
				.role(Role.USER)
				.lastLoginDate(LocalDateTime.now())
				.build();
		User savedUser = userDataSource.saveUser(user);


		//List<PostLike> postLikeList2 = cacheManager.getCache("post-like").get("post-like-list-01", ArrayList.class);

		String email2 = "abrown.dev@gmail.com";
		User user2 = User
				.builder()
				.username(email2.substring(0, 10))
				.email(email2)
				.profilePictureFilePath("http://localhost/images/default.png")
				.isEnabled(true)
				.password(new BCryptPasswordEncoder().encode("123456789"))
				.role(Role.USER)
				.build();
		User savedUser2 = userDataSource.saveUser(user2);
		System.out.println("Saved User " + savedUser2);

		String email3 = "agold.dev@gmail.com";
		User user3 = User
				.builder()
				.username(email3.substring(0, 10))
				.email(email3)
				.profilePictureFilePath("http://localhost/images/default.png")
				.isEnabled(true)
				.password(new BCryptPasswordEncoder().encode("123456789"))
				.role(Role.USER)
				.build();
		User savedUser3 = userDataSource.saveUser(user3);
		System.out.println("Saved User " + savedUser3);

		String email4 = "aforex.dev@gmail.com";
		User user4 = User
				.builder()
				.username(email4.substring(0, 10))
				.email(email4)
				.profilePictureFilePath("http://localhost/images/default.png")
				.isEnabled(true)
				.password(new BCryptPasswordEncoder().encode("12345"))
				.role(Role.USER)
				.build();
		User savedUser4 = userDataSource.saveUser(user4);
		System.out.println("Saved User " + savedUser4);

		String email5 = "abronze.dev@gmail.com";
		User user5 = User
				.builder()
				.username(email5.substring(0, 10))
				.email(email5)
				.profilePictureFilePath("http://localhost/images/default.png")
				.isEnabled(true)
				.password(new BCryptPasswordEncoder().encode("123456789"))
				.role(Role.USER)
				.build();
		User savedUser5 = userDataSource.saveUser(user5);
		System.out.println("Saved User " + savedUser5);

		//create followers
		Followers followers = new Followers();
		followers.setFollowerId(2);
		followers.setUserId(1);

		Followers followers2 = new Followers();
		followers2.setFollowerId(3);
		followers2.setUserId(1);

		Followers followers3 = new Followers();
		followers3.setFollowerId(4);
		followers3.setUserId(1);

		// set followers for user 2
		Followers followers4 = new Followers();
		followers4.setFollowerId(1);
		followers4.setUserId(2);

		Followers followers5 = new Followers();
		followers5.setFollowerId(4);
		followers5.setUserId(2);

		// set followers for user 3
		Followers followers6 = new Followers();
		followers6.setFollowerId(1);
		followers6.setUserId(3);

		Followers followers7 = new Followers();
		followers7.setFollowerId(5);
		followers7.setUserId(3);

		followerRepository.save(followers);
		followerRepository.save(followers2);
		followerRepository.save(followers3);
		followerRepository.save(followers4);
		followerRepository.save(followers5);
		followerRepository.save(followers6);
		followerRepository.save(followers7);

		Post post = Post.builder().content("hello there post !!!").userId(1).build();
		postEntityManager.savePost(post);

		Post post2 = Post.builder().content("hello there again post !!!").userId(1).build();
		postEntityManager.savePost(post2);

		PostLike postLike = new PostLike();
		postLike.setPostId(1);
		PostLike postLike2 = new PostLike();
		postLike2.setPostId(1);
		PostLike postLike3 = new PostLike();
		postLike3.setPostId(1);

		postLikeEntityManager.savePostLike(postLike);
		postLikeEntityManager.savePostLike(postLike2);
		postLikeEntityManager.savePostLike(postLike3);
	}
}