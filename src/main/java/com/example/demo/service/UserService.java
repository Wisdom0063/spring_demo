package com.example.demo.service;

import com.example.demo.dto.model.user.UserDto;
import com.example.demo.model.user.User;

/**
 * Created by Arpit Khandelwal.
 */
public interface UserService {
    /**
     * Register a new user
     *
     * @param User
     * @return
     */
    UserDto signup(User user);

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    UserDto findUserByEmail(String email);

    // /**
    //  * Update profile of the user
    //  *
    //  * @param userDto
    //  * @return
    //  */
    // UserDto updateProfile(UserDto userDto);

    // /**
    //  * Update password
    //  *
    //  * @param newPassword
    //  * @return
    //  */
    // UserDto changePassword(UserDto userDto, String newPassword);
}
