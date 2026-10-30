package se331.zzz.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.zzz.dto.UserDTO;
import se331.zzz.user.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "level", source = "level.id")
    @Mapping(target = "levelName", source = "level.name")
    @Mapping(target = "status", expression = "java(user.isEnabled() ? \"Active\" : \"Inactive\")")
    UserDTO getUserDto(User user);
}
