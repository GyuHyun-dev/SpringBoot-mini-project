package com.mysite.board;

import lombok.Getter;

@Getter
public enum UserRole {
	
	// 로그인시 어드민인지 유저인지 권한 부여
	ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
