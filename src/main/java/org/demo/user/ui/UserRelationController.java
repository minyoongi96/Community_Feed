package org.demo.user.ui;

import lombok.RequiredArgsConstructor;
import org.demo.user.application.UserRelationService;
import org.demo.user.application.dto.FollowUserRequestDto;
import org.demo.user.application.dto.GetUserListResponseDto;
import org.demo.user.repository.jpa.JpaUserListQueryRepository;
import org.demo.user.ui.common.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class UserRelationController {
    private final UserRelationService userRelationService;

    private final JpaUserListQueryRepository jpaUserListQueryRepository;

    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto dto){
        userRelationService.follow(dto);
        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    public Response<Void> unfollowUser(@RequestBody FollowUserRequestDto dto){
        userRelationService.unfollow(dto);
        return Response.ok(null);
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable(name = "userId") Long userId){
        List<GetUserListResponseDto> result = jpaUserListQueryRepository.getFollowingUserList(userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable(name = "userId") Long userId){
        List<GetUserListResponseDto> result = jpaUserListQueryRepository.getFollowerUserList(userId);
        return Response.ok(result);
    }
}
