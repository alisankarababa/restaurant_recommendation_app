package com.tech.user_service.mapper;

import com.tech.user_service.dto.UserDto;
import com.tech.user_service.entity.User;
import com.tech.user_service.request_body.UserRequestBody;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserDto userToUserDto(User user);

    List<UserDto> userListToUserDtoList(List<User> userList);
    User userRequestBodyToUser(UserRequestBody requestBody);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserRequestBody requestBody);

}
