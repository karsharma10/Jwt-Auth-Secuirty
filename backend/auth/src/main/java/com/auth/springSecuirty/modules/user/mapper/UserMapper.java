package com.auth.springSecuirty.modules.user.mapper;

import com.auth.springSecuirty.modules.user.dto.UserDto;
import com.auth.springSecuirty.modules.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User userMapDtoToEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    public UserDto userMapEntityToDto(User userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }
}
