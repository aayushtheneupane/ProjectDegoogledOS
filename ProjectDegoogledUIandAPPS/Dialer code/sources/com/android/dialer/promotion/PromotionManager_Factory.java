package com.android.dialer.promotion;

import com.google.common.collect.ImmutableList;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PromotionManager_Factory implements Factory<PromotionManager> {
    private final Provider<ImmutableList<Promotion>> priorityPromotionListProvider;

    public PromotionManager_Factory(Provider<ImmutableList<Promotion>> provider) {
        this.priorityPromotionListProvider = provider;
    }

    public Object get() {
        return new PromotionManager(this.priorityPromotionListProvider.get());
    }
}
