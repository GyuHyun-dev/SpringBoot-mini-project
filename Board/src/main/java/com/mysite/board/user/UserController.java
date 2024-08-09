package com.mysite.board.user;

import java.security.Principal;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysite.board.PasswordGenerator;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

	// 서비스 객체 생성
	private final UserService userService;
	private final EmailService emailService;
	private final PasswordResetTokenStore tokenStore;

	// GET으로 요청되면 회원 가입을 위한 템플릿을 렌더링
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "signup_form";
	}

	// POST로 요청되면 회원 가입을 진행
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}

		// 비밀번호 동일한지 검증
		if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다."); // 매개변수에는 필드명 , 오류코드 ,
																									// 오류메시지 입력
			return "signup_form";
		}

		// 이미등록된 회원일 시 안내
		try {
			userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "signup_form";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup_form";
		}

		// userService.create 메서드를 사용하여 사용자로부터 전달받은 데이터를 저장한다.
		userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
		return "redirect:/";
	}

	// 로그인
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}

	// 비밀번호 찾기
	@GetMapping("/findingpassword")
	public String findingpassword() {
		return "finding_password";
	}
	
}
