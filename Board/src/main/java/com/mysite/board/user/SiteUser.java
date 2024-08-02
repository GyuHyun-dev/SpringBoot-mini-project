package com.mysite.board.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)  // 유일값만 저장 가능 중복저장 x
	private String username;
	
	private String password;
	
	@Column(unique = true)  // 유일값만 저장 가능 중복저장 x
	private String email;
	
}
