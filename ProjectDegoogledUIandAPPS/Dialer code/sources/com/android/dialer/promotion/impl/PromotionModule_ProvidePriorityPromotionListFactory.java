package com.android.dialer.promotion.impl;

import android.support.p002v7.appcompat.R$style;
import com.android.dialer.promotion.Promotion;
import com.google.common.collect.ImmutableList;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PromotionModule_ProvidePriorityPromotionListFactory implements Factory<ImmutableList<Promotion>> {
    private final Provider<DuoPromotion> duoPromotionProvider;
    private final Provider<RttPromotion> rttPromotionProvider;

    public PromotionModule_ProvidePriorityPromotionListFactory(Provider<RttPromotion> provider, Provider<DuoPromotion> provider2) {
        this.rttPromotionProvider = provider;
        this.duoPromotionProvider = provider2;
    }

    public Object get() {
        ImmutableList of = ImmutableList.m76of(this.rttPromotionProvider.get(), this.duoPromotionProvider.get());
        R$style.checkNotNull1(of, "Cannot return null from a non-@Nullable @Provides method");
        return of;
    }
}
