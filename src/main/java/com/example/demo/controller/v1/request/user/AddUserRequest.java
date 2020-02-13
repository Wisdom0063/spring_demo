package com.example.demo.controller.v1.request.user;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * Created by Arpit Khandelwal.
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUserRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String email;

    @NotNull(message = "age is required")
    private Integer age;

    @NotNull(message = "{constraints.NotEmpty.message}")
    private Date dateOfBirth;

    @NotEmpty(message = "Password is required")
    private String password;
    
}