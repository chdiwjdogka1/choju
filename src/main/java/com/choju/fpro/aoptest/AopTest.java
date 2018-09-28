package com.choju.fpro.aoptest;
	
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AopTest {
	/*
	 * 포인트컷(pointcut) + 어드바이스(advice)를 합쳐서
	 * 애스팩트(aspect)라고 함.
	 * pointcut은 호출되는 위치 지정과 관련된
	 * advice는 해당 위치에서 어떤 기능을 수행할 것인지를 정의하는것.
	 */
	
	//포인트컷 정의
	/*
	 *(* com.choju.aaa..*ment.*(..))
	 * 1. * : 모든 리턴타입
	 * 2. com.choju.aaa 적용할 패키지 지정
	 * 3. com.choju.aaa..*ment : com.choju.aaa 패키지에 소속되어 있는 모든 패키지 중에서
	 * 							 클래스 이름이 ment로 끝나는 모든 클래스를 지정함
	 * 4. *(..) : 앞에 *은 모든 메소드를 의미하며 예를 들어 get* 형식으로 쓰면 get으로 시작하는
	 * 			  모든 메소드(..)은 모든 매개변수를 포함한다는 의미(개수, 타입 상관없음)
	 */
	
	//포인트컷 정의
	@Pointcut("execution(* com.choju.fpro..*Service.*(..))")
	public void allPointcut() {
		
	}
	
	//아래의 두 메소드는 어드바이스 정의
	@Before("allPointcut()")
	public void printLog() {
		System.out.println("=====AOP TEST Before Annotation=====");
	}
	
	@AfterReturning("allPointcut()")
	public void printLogAfter() {
		System.out.println("=====AOP TEST After Returning Annotation=====");
	}
}
