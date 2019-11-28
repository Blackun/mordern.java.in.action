package com.blackun.chapter17;

import java.util.concurrent.Flow.*;

// 화씨를 섭씨로 변환하는 Processor
// TempInfo를 다른 TempInfo로 변환하는 프로세
public class TempProcessor implements Processor<TempInfo, TempInfo> {

	private Subscriber<? super TempInfo> subscriber;

	@Override public void subscribe(Subscriber<? super TempInfo> subscriber) {
		this.subscriber = subscriber;
	}

	@Override public void onNext(TempInfo temp) {
		// 섭씨로 변환한 다음 TempInfo를 다시 전송
		subscriber.onNext(new TempInfo(temp.getTown(), (temp.getTemp() - 32)*5/9));
	}

	// 나머지 모든 신호는 업스트림 구독자에 전달
	@Override public void onSubscribe(Subscription subscription) {
		subscriber.onSubscribe(subscription);
	}

	@Override public void onError(Throwable throwable) {
		subscriber.onError(throwable);
	}

	@Override public void onComplete() {
		subscriber.onComplete();
	}
}
