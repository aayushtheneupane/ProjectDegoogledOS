package com.android.incallui.incall.impl;

import com.android.dialer.common.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

final class ButtonChooser {
    private final MappedButtonConfig config;

    public ButtonChooser(MappedButtonConfig mappedButtonConfig) {
        Assert.isNotNull(mappedButtonConfig);
        this.config = mappedButtonConfig;
    }

    public List<Integer> getButtonPlacement(int i, Set<Integer> set, Set<Integer> set2) {
        boolean z;
        Assert.isNotNull(set);
        Assert.checkArgument(i >= 0);
        if (i == 0 || set.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        List<Integer> orderedMappedSlots = this.config.getOrderedMappedSlots();
        for (int i2 = 0; i2 < orderedMappedSlots.size() && arrayList.size() < i; i2++) {
            List<Integer> buttonsForSlot = this.config.getButtonsForSlot(orderedMappedSlots.get(i2).intValue());
            Collections.sort(buttonsForSlot, this.config.getSlotComparator());
            int i3 = 0;
            while (true) {
                if (i3 >= buttonsForSlot.size()) {
                    break;
                } else if (set.contains(buttonsForSlot.get(i3))) {
                    arrayList.add(buttonsForSlot.get(i3));
                    arrayList2.addAll(buttonsForSlot.subList(i3 + 1, buttonsForSlot.size()));
                    break;
                } else {
                    i3++;
                }
            }
        }
        Collections.sort(arrayList2, this.config.getConflictComparator());
        for (Integer num : arrayList2) {
            if (arrayList.size() >= i) {
                break;
            } else if (set.contains(num) && !set2.contains(num)) {
                int mutuallyExclusiveButton = this.config.lookupMappingInfo(num.intValue()).getMutuallyExclusiveButton();
                if (mutuallyExclusiveButton == -1) {
                    z = false;
                } else {
                    z = set.contains(Integer.valueOf(mutuallyExclusiveButton));
                }
                if (!z) {
                    arrayList.add(num);
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }
}
