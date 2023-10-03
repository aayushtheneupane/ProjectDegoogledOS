package com.android.systemui.volume;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class VolumeDialogControllerImpl_Factory implements Factory<VolumeDialogControllerImpl> {
    private final Provider<Context> contextProvider;

    public VolumeDialogControllerImpl_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public VolumeDialogControllerImpl get() {
        return provideInstance(this.contextProvider);
    }

    public static VolumeDialogControllerImpl provideInstance(Provider<Context> provider) {
        return new VolumeDialogControllerImpl(provider.get());
    }

    public static VolumeDialogControllerImpl_Factory create(Provider<Context> provider) {
        return new VolumeDialogControllerImpl_Factory(provider);
    }
}
