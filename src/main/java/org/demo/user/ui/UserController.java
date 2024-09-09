package org.demo.user.ui;

import lombok.RequiredArgsConstructor;
import org.demo.user.application.UserService;
import org.demo.user.application.dto.CreateUserRequestDto;
import org.demo.user.application.dto.GetUserResponseDto;
import org.demo.user.domain.User;
import org.demo.user.ui.common.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto){
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable Long userId){
        return Response.ok(userService.getUserProfile(userId));
    }
}
