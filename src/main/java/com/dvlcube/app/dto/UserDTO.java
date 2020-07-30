package com.dvlcube.app.dto;

import com.dvlcube.utils.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import java.util.Date;

@Getter
@Setter
@Builder
public class UserDTO extends BaseDTO {
    private Long id;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private String pic;

    @Column(nullable = false)
    private Boolean verified = false;

    @JsonIgnore
    private String password;

    private Date lastActivity;

    private int requests;
}
