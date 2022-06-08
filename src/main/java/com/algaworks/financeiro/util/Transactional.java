package com.algaworks.financeiro.util;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Transactional {

}
