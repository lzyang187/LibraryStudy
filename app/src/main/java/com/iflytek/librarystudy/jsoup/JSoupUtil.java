package com.iflytek.librarystudy.jsoup;

import com.iflytek.librarystudy.rxjava.operator.RxJavaUtil;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: cyli8
 * @date: 2019-10-17 10:57
 */
public class JSoupUtil {

    public static Observable<Document> connect(final String url) {
        return Observable.create(new ObservableOnSubscribe<Document>() {
            @Override
            public void subscribe(ObservableEmitter<Document> emitter) throws Exception {
                Connection connect = Jsoup.connect(url);
                Document document = connect.get();
                emitter.onNext(document);
            }
        }).compose(RxJavaUtil.<Document>rxScheduler(Schedulers.io()));
    }

    public static Observable<Document> parse(final File file) {
        return Observable.create(new ObservableOnSubscribe<Document>() {
            @Override
            public void subscribe(ObservableEmitter<Document> emitter) throws Exception {
                Document document = Jsoup.parse(file, "utf-8");
                emitter.onNext(document);
            }
        }).compose(RxJavaUtil.<Document>rxScheduler(Schedulers.io()));
    }

}
