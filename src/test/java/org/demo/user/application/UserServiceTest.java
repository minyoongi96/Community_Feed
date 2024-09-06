package org.demo.user.application;

import org.demo.fake.FakeObjectFactory;
import org.demo.user.application.dto.CreateUserRequestDto;
import org.demo.user.application.interfaces.UserRepository;
import org.demo.user.domain.User;
import org.demo.user.domain.UserInfo;
import org.demo.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();
//    private final UserRepository userRepository = new FakeUserRepository();

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser(){
        // given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        // when
        User savedUser = userService.createUser(dto);

        // then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo foundUserInfo = foundUser.getUserInfo();
        assertEquals(savedUser.getId(), foundUser.getId());
        assertEquals(foundUserInfo.getName(), "test");
    }
}
