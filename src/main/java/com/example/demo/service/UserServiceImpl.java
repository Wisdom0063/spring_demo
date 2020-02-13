package com.example.demo.service;

import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.dto.model.user.UserDto;
import com.example.demo.exception.DemoException;
import com.example.demo.exception.EntityType;
import com.example.demo.exception.ExceptionType;
import com.example.demo.model.user.User;
import com.example.demo.dao.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

// import java.util.Arrays;
// import java.util.HashSet;
import java.util.Optional;

import static com.example.demo.exception.EntityType.USER;
import static com.example.demo.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.example.demo.exception.ExceptionType.ENTITY_NOT_FOUND;

/**
 * Created by Arpit Khandelwal.
 */
@Component
public class UserServiceImpl implements UserService {
    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    // @Autowired
    // private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private BusReservationService busReservationService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto signup(User user) {
        User userData = userRepository.findByEmail(user.getEmail());
        if (userData == null) {
            user = new User().setAge(user.getAge()).setEmail(user.getEmail()).setDateOfBirth(user.getDateOfBirth())
                    .setName(user.getName());
            return UserMapper.toUserDto(userRepository.save(user));
        }
        throw exception(USER, DUPLICATE_ENTITY, user.getEmail());
    }

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    public UserDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw exception(USER, ENTITY_NOT_FOUND, email);
    }

    // /**
    // * Update User Profile
    // *
    // * @param userDto
    // * @return
    // */
    // @Override
    // public UserDto updateProfile(UserDto userDto) {
    // Optional<User> user =
    // Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
    // if (user.isPresent()) {
    // User userModel = user.get();
    // userModel.setFirstName(userDto.getFirstName())
    // .setLastName(userDto.getLastName())
    // .setMobileNumber(userDto.getMobileNumber());
    // return UserMapper.toUserDto(userRepository.save(userModel));
    // }
    // throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    // }

    // /**
    // * Change Password
    // *
    // * @param userDto
    // * @param newPassword
    // * @return
    // */
    // @Override
    // public UserDto changePassword(UserDto userDto, String newPassword) {
    // Optional<User> user =
    // Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
    // if (user.isPresent()) {
    // User userModel = user.get();
    // userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
    // return UserMapper.toUserDto(userRepository.save(userModel));
    // }
    // throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    // }

    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return DemoException.throwException(entityType, exceptionType, args);
    }
}
