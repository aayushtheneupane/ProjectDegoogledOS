package com.android.incallui.speakeasy;

import android.support.p002v7.appcompat.R$style;
import com.google.common.base.Optional;
import dagger.internal.Factory;

public enum StubSpeakEasyModule_ProvideSpeakEasyChipFactory implements Factory<Optional<Integer>> {
    INSTANCE;

    public Object get() {
        Optional absent = Optional.absent();
        R$style.checkNotNull1(absent, "Cannot return null from a non-@Nullable @Provides method");
        return absent;
    }
}
