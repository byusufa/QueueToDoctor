package uz.pdp.doctor.dto;

import lombok.*;
import uz.pdp.doctor.entity.Attachment;
import uz.pdp.doctor.entity.Role;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddReqDto {

    private String firstName;
    private String lastName;
    private String phone;
    private String username;
    private String password;
    private Float experience;
    private String specialty;
    private List<Role> roles;
    private Attachment attachment;



}
