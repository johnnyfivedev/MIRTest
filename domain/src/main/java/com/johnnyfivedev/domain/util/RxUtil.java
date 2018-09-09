package com.johnnyfivedev.domain.util;

import io.reactivex.disposables.Disposable;

public class RxUtil {

    public static boolean shouldDispose(Disposable disposable) {
        return disposable != null && !disposable.isDisposed();
    }

    public static void disposeIfNeeded(Disposable disposable) {
        if (shouldDispose(disposable)) {
            disposable.dispose();
        }
    }
}