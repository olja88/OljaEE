package com.oljalatinovic.oljaee.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
@Constraint(validatedBy = {EmailValidator.class})
@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message() default "{com.oljalatinovic.oljaee.invalid.email}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
