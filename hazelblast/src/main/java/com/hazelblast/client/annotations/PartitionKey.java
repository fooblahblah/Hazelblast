package com.hazelblast.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An parameter annotation that needs to be placed on a partition call. This parameter will be used to figure out
 * the correct partition.
 * <p/>
 * All partitioned methods, should have 1 PartitionKey annotated argument.
 *
 * @author Peter Veentjer.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PartitionKey {

    String property() default "";
}
