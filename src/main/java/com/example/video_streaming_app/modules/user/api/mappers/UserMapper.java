package com.example.video_streaming_app.modules.user.api.mappers;


import com.example.video_streaming_app.modules.user.api.dto.user.UserDto;
import com.example.video_streaming_app.modules.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);
    List<UserDto> toDto(List<User> users);
    User toEntity(UserDto userDto);

}