package com.auth.springSecuirty.userMapperTests;

import com.auth.springSecuirty.modules.user.dto.UserDto;
import com.auth.springSecuirty.modules.user.entity.User;
import com.auth.springSecuirty.modules.user.mapper.UserMapper;
import com.auth.springSecuirty.utils.TestData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class UserMapperIntegrationTests {

    private final UserMapper userMapper;

    @Autowired
    public UserMapperIntegrationTests(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    public void testThatUserMapperCorrectlyMapsFromUserFromEntityToDto(){
        User testUserAEntity = TestData.createTestUserA();
        UserDto checkUserDto = TestData.createTestUserADto();

        UserDto convertedUserDto = userMapper.userMapEntityToDto(testUserAEntity);

        assertThat(convertedUserDto).isEqualTo(checkUserDto); //check that user entity maps correctly to dto
    }

    @Test
    public void testThatUserMapperCorrectlyMapsFromDtoToEntity(){
        UserDto testUserADto = TestData.createTestUserADto();
        User checkUserDto = TestData.createTestUserA();

        User convertedUserEntity = userMapper.userMapDtoToEntity(testUserADto);

        assertThat(convertedUserEntity).isEqualTo(checkUserDto);
    }
}
