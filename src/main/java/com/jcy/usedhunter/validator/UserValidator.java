package com.jcy.usedhunter.validator;


import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jcy.usedhunter.domain.User;

public class UserValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
//		return User.class.equals(clazz); // 검증하려는 객체가 User타입인지 확인
		return User.class.isAssignableFrom(clazz); // clazz가 User 또는 그 자손인지 확인
	}

	@Override
	public void validate(Object target, Errors errors) { 
		System.out.println("UserValidator.validate() is called");

		User user = (User)target;
		
		String id = user.getId();
		String pwd = user.getPwd();
		String name = user.getName();
		String email = user.getEmail();
		
		
//		if(id==null || "".equals(id.trim())) {
//			errors.rejectValue("id", "required");
//		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required"); // required.user.id
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required"); // required.user.pwd
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required"); // required.user.name
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required"); // required.user.email
		
		if(id==null || id.length() <  5 || id.length() > 12) {
			errors.rejectValue("id", "invalidLength", new String[]{"5","12"}, null);
		}
		if(pwd==null || pwd.length() <  5 || pwd.length() > 12) {
			errors.rejectValue("pwd", "invalidLength", new String[]{"5","12"}, null);
		}
	}
}