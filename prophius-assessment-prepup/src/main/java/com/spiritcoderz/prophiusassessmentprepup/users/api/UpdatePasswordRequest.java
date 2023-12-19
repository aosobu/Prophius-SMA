package com.spiritcoderz.prophiusassessmentprepup.users.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePasswordRequest {
    @NotNull
    private Integer userId;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 50)
    private String password;
}
