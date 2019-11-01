package com.blackun.chapter6.service;

import com.blackun.chapter6.model.Currency;
import com.blackun.chapter6.model.Transaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionService {

	public Map<Currency, List<Transaction>> toTransactionsByCurrencies(List<Transaction> transactions){
		// 그룹화한 트랜잭션을 저장할 맵을 생성한다.
		Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();

		// 트랜잭션 리스트를 반복한다.
		for(Transaction transaction : transactions){
			// 트랜잭션의 통화를 추출한다.
			Currency currency = transaction.getCurrency();
			List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);

			// 현재 통화를 그룹화하는 맵에 항목이 없으면 항목을 만든다.
			if(transactionsForCurrency == null){
				transactionsForCurrency = new ArrayList<>();
				transactionsByCurrencies.put(currency, transactionsForCurrency);
			}

			// 같은 통화를 가진 트랜잭션 리스트에 현재 탐색중인 트랜잭션을 추가한다.
			transactionsForCurrency.add(transaction);
		}
		return transactionsByCurrencies;
	}
}
