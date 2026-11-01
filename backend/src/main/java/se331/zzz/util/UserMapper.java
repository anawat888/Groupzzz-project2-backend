package se331.zzz.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.zzz.dto.LevelSummaryDTO;
import se331.zzz.dto.UserDTO;
import se331.zzz.entity.Level;
import se331.zzz.user.User;
import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "level", source = "level.id")
    @Mapping(target = "levelName", source = "level.name")
    @Mapping(target = "status", expression = "java(user.isEnabled() ? \"Active\" : \"Inactive\")")
    UserDTO getUserDto(User user);

    List<LevelSummaryDTO> getBadgeDto(List<Level> levels);
    List<UserDTO> getUserDto(List<User> users);
}
