package com.blackun.chapter7;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class WordCounterTest {

	private static final Logger logger = LogManager.getLogger(WordCounterTest.class);

	private final String SENTENCE = "Nel      mezzo del cammin di nostra vita "
			+ "mi ritorovai in una    selva  oscura "
			+ "ch    la  dritta via era    smarrita";

	private int countWords(Stream<Character> stream){
		WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
													WordCounter::accumulate,
													WordCounter::combine);
		return wordCounter.getCounter();
	}

	@Test
	public void test1(){
		Stream<Character> stream = IntStream.range(0, SENTENCE.length())
											.mapToObj(SENTENCE::charAt);

		logger.info("Found {} words", countWords(stream));
	}
}
