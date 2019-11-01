package com.blackun.chapter7;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class WordCounterTest {

	private static final Logger logger = LogManager.getLogger(WordCounterTest.class);

	private final String SENTENCE = "Nel      mezzo del cammin di nostra vita "
			+ " mi ritorovai in una    selva  oscura "
			+ " ch    la  dritta via era    smarrita ";

	private int countWords(Stream<Character> stream){
		WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
													WordCounter::accumulate,
													WordCounter::combine);
		return wordCounter.getCounter();
	}

	@Test
	public void test1(){
		Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
		Stream<Character> stream = StreamSupport.stream(spliterator, true);

		logger.info("Found {} words", countWords(stream));
	}
}
