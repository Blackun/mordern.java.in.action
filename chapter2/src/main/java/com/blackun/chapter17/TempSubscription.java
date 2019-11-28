package com.blackun.chapter17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.*;

public class TempSubscription implements Subscription {

	private static final ExecutorService executor = Executors.newSingleThreadExecutor();

	private final Subscriber<? super TempInfo> subscriber;
	private final String town;

	public TempSubscription(Subscriber<? super TempInfo> subscriber, String town){
		this.subscriber = subscriber;
		this.town = town;
	}


	@Override public void request(long n) {
		executor.submit(()-> {
					// Subscriber가 만든 요청을 한개씩 반복
					for (long i = 0l; i < n; i++) {
						try {
							// 현재 온도를 Subscriber로 전달
							subscriber.onNext(TempInfo.fetch(town));
						} catch (Exception ex) {
							// 온도 가져오기를 실패하면 Subscriber로 에러를 전달
							subscriber.onError(ex);
							break;
						}
					}
				});
	}

	@Override public void cancel() {
		// 구독이 취소되면 완료(onComplete) 신호를 Subscriber로 전달
		subscriber.onComplete();

	}
}
