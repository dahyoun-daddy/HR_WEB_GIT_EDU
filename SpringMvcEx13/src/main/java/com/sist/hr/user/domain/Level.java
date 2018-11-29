package com.sist.hr.user.domain;
/*
 클래스처럼 보이게 하는 상수
서로 관련 있는 상수들을 모아 심볼릭한 명칭의 집합으로 정의한 것
Enum 클래스형을 기반으로 한 클래스형 선언
새로운 열거형을 선언하면, 내부적으로 Enum 클래스형 기반의 새로우누 클래스형이 만들어짐.
 */
public enum Level {
	//BASIC(1),SILVER(2),GOLD(3);
	GOLD(3,null),SILVER(2,GOLD),BASIC(1,SILVER);
	
	
	private final int value;
	private final Level next;
	
	Level(int value,Level next){
		this.value = value;
		this.next  = next;
	}
	
	
	public Level nextLevel() {
		return next;
	}
	
	//DB insert
	public int intValue() {
		return value;
	}
	
	//DB get
	public static Level valueOf(int value) {
		switch(value) {
			case 1: return BASIC;
			case 2: return SILVER;
			case 3: return GOLD;
			default: throw new AssertionError("Unknown value:"+value);
		}
	}
	
	
}


