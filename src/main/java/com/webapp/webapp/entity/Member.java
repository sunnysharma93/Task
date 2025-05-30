package com.webapp.webapp.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {

        private Long id;
        private String email;
        private String password;
        private String username;
        private LocalDateTime createdAt;private boolean deleted;

}
