package com.android.systemui.util;

import android.content.Context;
import com.android.systemui.util.InjectionInflationController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* renamed from: com.android.systemui.util.InjectionInflationController_ViewAttributeProvider_ProvideContextFactory */
public final class C1613x240b4695 implements Factory<Context> {
    public static Context proxyProvideContext(InjectionInflationController.ViewAttributeProvider viewAttributeProvider) {
        Context provideContext = viewAttributeProvider.provideContext();
        Preconditions.checkNotNull(provideContext, "Cannot return null from a non-@Nullable @Provides method");
        return provideContext;
    }
}
