package com.android.dialer.promotion;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Optional;

public final class PromotionManager {
    private ImmutableList<Promotion> priorityPromotionList;

    public PromotionManager(ImmutableList<Promotion> immutableList) {
        this.priorityPromotionList = immutableList;
    }

    public Optional<Promotion> getHighestPriorityPromotion(int i) {
        UnmodifiableIterator<Promotion> it = this.priorityPromotionList.iterator();
        while (it.hasNext()) {
            Promotion next = it.next();
            if (next.isEligibleToBeShown()) {
                if (next.getType() == i) {
                    return Optional.of(next);
                }
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
