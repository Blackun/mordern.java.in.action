package com.blackun.chapter9;

import com.blackun.chapter4.model.Dish;
import com.blackun.chapter4.model.Dish.CaloricLevel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

public class RefactoringTest {
	private static final Logger logger = LogManager.getLogger(RefactoringTest.class);

	private List<Dish> menu;

	@Before
	public void before(){
		menu = Arrays.asList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rich", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH)
		);
	}


	@Test
	public void test_Rennable_리팩터링(){
		// 익명 클래스를 사용한 이전 코드
		Runnable r1 = new Runnable() {
			@Override public void run() {
				logger.info("r1.run() : {}", "Hello");
			}
		};

		// 람다 표현식을 사용한 최신 코드
		Runnable r2 = () -> logger.info("r2.run() : {}", "Hello");

		// 실행
		r1.run();
		r2.run();
	}

	@Test
	public void test_새도_변수(){
		int a = 10;

		Runnable r1 = () -> {
			// 컴파일 에러가 나서 주석 처리
			// int a = 2;
			System.out.println(a);
		};

		Runnable r2 = new Runnable() {
			@Override public void run() {
				// 모든 것이 잘 작동한다.
				int a = 2;
				System.out.println(a);
			}
		};

		r1.run();
		r2.run();
	}

	interface Task{
		public void execute();
	}

	public static void doSomething(Runnable r){
		r.run();
	}
	public static void doSomething(Task a){
		a.execute();
	}

	@Test
	public void test_동일_시그니처_오류(){
		doSomething(new Task(){
			@Override public void execute() {
				System.out.println("Danger danger!!");
			}
		});

		// 아래 코드는 모호함으로 인해 컴파일 에러가 발생한다.
		// doSomething(()->System.out.println("Danger danger!!"));
		// 다음과 같이 수정
		doSomething((Task)()->System.out.println("Danger danger!!"));
	}

	@Test
	public void test_람다표현식을_메서드참조로_리팩터링(){
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
																.collect(
																	groupingBy(dish->{
																		if(dish.getCalories() <= 400) return CaloricLevel.DIET;
																		else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
																		else return CaloricLevel.FAT;
																	})
																);

		logger.info("dishesByCaloricLevel : {}", dishesByCaloricLevel);

		// 메소드 참조 방식으로 변경
		dishesByCaloricLevel = menu.stream()
				.collect(groupingBy(Dish::getCaloricLevel));

		logger.info("dishesByCaloricLevel : {}", dishesByCaloricLevel);
	}

	@Test
	public void test_코드의_의도가_보이는_저수준_리듀싱(){
		int totalCalories = menu.stream().map(Dish::getCalories)
								.reduce(0, (c1, c2) -> c1 + c2);

		logger.info("totalCalories : {}", totalCalories);

		// 코드의 의미가 더 명확해졌다.
		totalCalories = menu.stream().collect(summingInt(Dish::getCalories));

		logger.info("totalCalories : {}", totalCalories);
	}

	@Test
	public void test_명령형_코드를_스트림_API로(){
		List<String> dishNames = new ArrayList<>();
		for(Dish dish: menu){
			if(dish.getCalories() > 300){
				dishNames.add(dish.getName());
			}
		}

		logger.info("dishNames : {}", dishNames);
	
		// 스트림 API를 이용하면 문제를 더 직접적으로 기술할 수 있을 뿐 아니라
		// 쉽게 병렬화 할 수 있다.
		dishNames = menu.parallelStream()
				.filter(d->d.getCalories()>300)
				.map(Dish::getName)
				.collect(toList());

		logger.info("dishNames : {}", dishNames);
	}

}
