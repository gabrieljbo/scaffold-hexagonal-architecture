package basepackage.api.common.exception;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.http.HttpStatus;

@Repeatable(ErrorCodeHttpStatus.List.class)
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorCodeHttpStatus {

    int errorCode();
    HttpStatus status();

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.METHOD })
    @interface List {
	ErrorCodeHttpStatus[] value() default {};
    }

}
