package guchi.the.hasky.datastructures.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface ForTestsOnly {
}
