package com.example.demo.dto.mapper;

// import com.starterkit.springboot.brs.dto.model.user.RoleDto;
import com.example.demo.dto.model.user.UserDto;
import com.example.demo.model.user.User;
import org.springframework.stereotype.Component;
// import org.modelmapper.ModelMapper;
// import java.util.HashSet;
// import java.util.stream.Collectors;

/**
 * Created by Arpit Khandelwal.
 */
@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setEmail(user.getEmail())
                .setName(user.getName())
                .setId(user.getId())
                .setCreatedAt(user.getCreatedAt())
                .setDateOfBirth(user.getDateOfBirth())
                .setAge(user.getAge());
                // .setRoles(new HashSet<RoleDto>(user
                //         .getRoles()
                //         .stream()
                //         .map(role -> new ModelMapper().map(role, RoleDto.class))
                //         .collect(Collectors.toSet())));
    }

}
