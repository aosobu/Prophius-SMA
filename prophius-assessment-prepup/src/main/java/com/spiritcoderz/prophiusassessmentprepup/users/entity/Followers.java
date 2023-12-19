package com.spiritcoderz.prophiusassessmentprepup.users.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "followers")
public class Followers {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer followerId;
}
