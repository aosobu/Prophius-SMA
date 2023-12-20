package com.spiritcoderz.prophiusassessmentprepup.users;

import com.spiritcoderz.prophiusassessmentprepup.users.api.*;
import com.spiritcoderz.prophiusassessmentprepup.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest userRequest) {
        return ResponseEntity.ok(userService.registerUser(userRequest));
    }

    @PostMapping("/follow")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody FollowRequest followRequest) {
        return ResponseEntity.ok(userService.executeFollowRequest(followRequest));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> retrieve(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmailCredential(email));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> retrieve(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserByIdCredential(id));
    }

    @PutMapping("/password")
    public ResponseEntity<UserResponse> editUserDetail(@Valid @RequestBody UpdatePasswordRequest updateRequest) {
        return ResponseEntity.ok(userService.updateUserPassword(updateRequest));
    }

    @PutMapping("/profile-image")
    public ResponseEntity<UserResponse> editUserDetail(@Valid @ModelAttribute UpdateImageRequest updateRequest) {
        return ResponseEntity.ok(userService.updateUserImage(updateRequest));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<UserResponse> delete(@PathVariable String email) {
        return ResponseEntity.ok(userService.deleteUser(email));
    }
}
