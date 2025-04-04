package com.url.shortner.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role="USER_ROLE";

    
}
