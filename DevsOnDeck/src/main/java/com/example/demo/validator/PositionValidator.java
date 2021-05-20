package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.models.Position;

@Component
public class PositionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Position position = (Position) target;
		
		if(position.getPosSkills().size()>5) {
			errors.reject("skills length", "No more than 5 skills needed");
		}
	}

}
