package com.blackun.chapter7;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {
	private final String string;
	private int currentChar = 0;

	public WordCounterSpliterator(String string){
		this.string = string;
	}

	@Override public boolean tryAdvance(Consumer<? super Character> action) {
		// 현재 문자를 소비한다.
		action.accept(string.charAt(currentChar++));
		// 소비할 문자가 남아있으면 true를 반환한다.
		return currentChar < string.length();
	}

	@Override public Spliterator<Character> trySplit() {
		int currentSize = string.length() - currentChar;

		if(currentSize < 10){
			// 파싱할 문자열을 순차 처리할 수 있을 만큼 충분히 작아졌음을
			// 알리는 null을 반환하다.
			return null;
		}

		// 파싱할 문자열의 중간을 분할 위치로 설정한다.
		for(int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++){

			// 다음 공백이 남을 때까지 분할 위치를 뒤로 이동시킨다.
			if(Character.isWhitespace(string.charAt(splitPos))){
				// 처음부터 분할 위치까지 문자열을 파싱할 새로운 WordCounterSpliterator 를 생성한다.
				Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
				// 이 WordCounterSpliterator의 시작 위치를 분할 위치로 설정한다.
				currentChar = splitPos;
				// 공백을 찾았고 문자열을 분리했으므로 루프를 종료한다.
				return spliterator;
			}
		}
		return null;
	}

	@Override public long estimateSize() {
		return string.length() - currentChar;
	}

	@Override public int characteristics() {
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}
}
