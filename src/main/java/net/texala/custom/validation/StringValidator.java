package net.texala.custom.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringValidator implements ConstraintValidator<StringValidation, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null && value.length() > 4) {
			return true;
		}
		return false;
	}

}
