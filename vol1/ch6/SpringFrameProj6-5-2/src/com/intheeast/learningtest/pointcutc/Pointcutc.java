package com.intheeast.learningtest.pointcutc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;


public class Pointcutc{
	
	@Test
	public void classNamePointcutAdvisor() {
		NameMatchMethodPointcut classMethodPointcut = new NameMatchMethodPointcut() {
			public ClassFilter getClassFilter() {
				return new ClassFilter() {
					public boolean matches(Class<?> clazz) {
						return clazz.getSimpleName().startsWith("HelloT");
					}
				};
			}
		};
		classMethodPointcut.setMappedName("sayH*");
		
		checkAdviced(new HelloTarget(), classMethodPointcut, true);
		
	}
	
	private void checkAdviced(Object target, Pointcut pointcut, boolean adviced) {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(target);
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
		Hello proxiedHello = (Hello) pfBean.getObject();
		
		if(adviced) {
			assertEquals(proxiedHello.sayHello("Toby"), "HELLO TOBY");
			assertEquals(proxiedHello.sayHi("Toby"), "HI TOBY");
			assertEquals(proxiedHello.sayThankYou("Toby"), "Thank You Toby");
		}else {
			assertEquals(proxiedHello.sayHello("Toby"), "Hello Toby");
			assertEquals(proxiedHello.sayHi("Toby"), "Hi Toby");
			assertEquals(proxiedHello.sayThankYou("Toby"), "Thank You Toby");
		}
	}
	
	static interface Hello{
		String sayHello(String name);
		String sayHi(String name);
		String sayThankYou(String name);
	}
	
	static class HelloTarget implements Hello{
		public String sayHello(String name) {return "Hello " + name;}
		public String sayHi(String name) {return "Hi " + name;}
		public String sayThankYou(String name) {return "Thank You " + name;}
	}

}
