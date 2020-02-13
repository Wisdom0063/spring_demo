
package com.example.demo.dto.model.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

// import java.util.Set;

/**
 * Created by Arpit Khandelwal.
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private Date dateOfBirth;
    private Date createdAt;
    private Integer age;
    private Boolean active;

    private String roles = "";

    private String permissions = "";
    private String token;
    // private Set<RoleDto> roles;

    // public String getFullName() {
    //     return firstName != null ? firstName.concat(" ").concat(lastName) : "";
    // }

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return (List<String>) Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList() {
        if (this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
