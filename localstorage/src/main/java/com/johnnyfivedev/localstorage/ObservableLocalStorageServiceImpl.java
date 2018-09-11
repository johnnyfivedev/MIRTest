package com.johnnyfivedev.localstorage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;


public class ObservableLocalStorageServiceImpl implements ObservableLocalStorageService {

    private final LocalStorageService localStorageService;

    private final HashMap<String, LocalStorageListener> subscribers = new HashMap<>();
    private AtomicInteger index = new AtomicInteger(0);


    @Inject
    public ObservableLocalStorageServiceImpl(LocalStorageService localStorageService) {
        this.localStorageService = localStorageService;
    }

    //region ===================== Implementation ======================

    @Override
    public Observable<String> getData(final String key, final String defaultValue) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                LocalStorageListener localStorageListener = new LocalStorageListener() {
                    @Override
                    public void onDataChanged(String data) {
                        emitter.onNext(data);
                    }
                };
                emitter.onNext(localStorageService.getData(key, defaultValue));
                final String subscriberKey = key + "_" + index.addAndGet(1);
                subscribers.put(subscriberKey, localStorageListener);

                emitter.setDisposable(new Disposable() {
                    @Override
                    public void dispose() {
                        subscribers.remove(subscriberKey);
                    }

                    @Override
                    public boolean isDisposed() {
                        return subscribers.containsKey(subscriberKey);
                    }
                });
            }
        });
    }

    @Override
    public Completable saveData(final String key, final String value) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                localStorageService.saveData(key, value);
                notifySubscribers(subscribers, key, value);
                emitter.onComplete();
            }
        });
    }

    //endregion

    //region ===================== Internal logic ======================

    private void notifySubscribers(HashMap<String, LocalStorageListener> subscribers,
                                   final String key,
                                   final String value) {
        HashMap<String, LocalStorageListener> copied = new HashMap<>(subscribers);
        Iterator iterator = copied.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            if (((String) pair.getKey()).contains(key)) {
                copied.get(pair.getKey()).onDataChanged(value);
            }
            iterator.remove();
        }
    }

    //endregion

    //region ===================== Callbacks ======================

    public interface LocalStorageListener {
        void onDataChanged(String data);
    }

    //endregion
}