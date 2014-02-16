package com.oljalatinovic.oljaee.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */

@NotNull
@Size(min = 1, max = 10)
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Documented
public @interface Login {

    // ======================================
    // =             Attributes             =
    // ======================================

    String message() default "{com.oljalatinovic.oljaee.login.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
