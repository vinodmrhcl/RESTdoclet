package com.iggroup.oss.sample.domain.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation used to mark attributes or methods to ignore
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Ignore {

}
