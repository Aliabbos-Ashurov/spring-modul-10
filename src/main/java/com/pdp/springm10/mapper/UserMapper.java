package com.pdp.springm10.mapper;

import com.pdp.springm10.dto.UserDTO;
import com.pdp.springm10.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  12:47
 **/
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDTO toUserDTO(User user);

    User toUser(UserDTO dto);
}
