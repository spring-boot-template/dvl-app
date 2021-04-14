package com.dvlcube.app.dto;

import com.dvlcube.utils.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    @Positive
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotEmpty
    private String pic;

    @NotNull
    private Boolean verified = false;

    @NotBlank
    private String password;

    @Positive
    private Integer requests;

    private Date lastActivity;
}
