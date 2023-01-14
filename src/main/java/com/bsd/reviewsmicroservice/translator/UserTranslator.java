package com.bsd.reviewsmicroservice.translator;

import com.bsd.reviewsmicroservice.domain.User;
import com.bsd.reviewsmicroservice.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserTranslator {

    public UserDto generateUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRoleType(user.getRoleType());

        return userDto;
    }

    public List<UserDto> generateUserDtoList(List<User> users) {
        return users.stream().map(this::generateUserDto).collect(Collectors.toList());
    }
}
