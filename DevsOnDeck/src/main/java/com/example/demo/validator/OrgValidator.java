package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.models.Organization;

@Component
public class OrgValidator  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Organization org = (Organization) target;
		
		if(!org.getPassConfirm().equals(org.getPassword())) {
			errors.rejectValue("passConfirm", "Match", "Passwords do not match!");
		}
		
	}

}
