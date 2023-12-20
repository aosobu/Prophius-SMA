package com.spiritcoderz.prophiusassessmentprepup.posts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post_like_temp")
public class PostLike {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer postId;
}
