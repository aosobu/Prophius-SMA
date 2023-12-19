package com.spiritcoderz.prophiusassessmentprepup.users.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spiritcoderz.prophiusassessmentprepup.commons.entity.AbstractAuditable;
import com.spiritcoderz.prophiusassessmentprepup.commons.wrappers.BeanWrapper;
import com.spiritcoderz.prophiusassessmentprepup.posts.entity.Post;
import com.spiritcoderz.prophiusassessmentprepup.users.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user", indexes = @Index(columnList = "email"))
public class User extends AbstractAuditable implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String profilePictureFilePath;
    private String name;
    private String type;
    @Lob
    @Column(name = "profile_image_data",length = 1000)
    private byte[] imageData;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isEnabled;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastLoginDate;
    private transient BeanWrapper beanWrapper;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        beanWrapper = new BeanWrapper();
        return List.of(beanWrapper.getGrantedAuthorityBean(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
