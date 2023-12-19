package com.spiritcoderz.prophiusassessmentprepup.comments.entity;

import com.spiritcoderz.prophiusassessmentprepup.commons.entity.AbstractAuditable;
import com.spiritcoderz.prophiusassessmentprepup.users.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends AbstractAuditable {
    @Id
    @GeneratedValue
    private Integer id;
    private String content;
    private Integer userId;
    private Integer postId;
}
