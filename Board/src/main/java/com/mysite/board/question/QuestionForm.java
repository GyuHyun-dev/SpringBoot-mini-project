package com.mysite.board.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
	
	// @NotEmpty Null 및 빈문자열 "" / 허용금지 @Size 최대길이 200 byte 
	
	@NotEmpty(message="제목은 필수항목 입니다.")
	@Size(max=200)
	private String subject;
	
	@NotEmpty(message="내용은 필수항목 입니다.")
	private String content;
}