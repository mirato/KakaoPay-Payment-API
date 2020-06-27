package com.payment.api.validate;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check allow paramter.
 * 허용파라메터를 체크한다.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowParamsValidator.class)
@Documented
public @interface AllowParams {

	String message() default "The parameter values are not allowed.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] values();
}
