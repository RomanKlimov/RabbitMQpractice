package ru.itis.rabbitproducer.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserDTO {
    private String login;
    private String email;
    private String phone;
}
