package com.android.incallui.incall.impl;

import android.support.p000v4.util.ArrayMap;
import android.support.p002v7.appcompat.R$style;
import android.util.ArraySet;
import com.android.dialer.common.Assert;
import com.android.incallui.incall.impl.AutoValue_MappedButtonConfig_MappingInfo;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

final class MappedButtonConfig {
    private final Map<Integer, MappingInfo> mapping = new ArrayMap();
    private final List<Integer> orderedMappedSlots;

    @AutoValue
    static abstract class MappingInfo {

        static abstract class Builder {
            Builder() {
            }

            public abstract MappingInfo build();

            public abstract Builder setConflictOrder(int i);

            public abstract Builder setMutuallyExclusiveButton(int i);

            public abstract Builder setSlotOrder(int i);
        }

        MappingInfo() {
        }

        static Builder builder(int i) {
            AutoValue_MappedButtonConfig_MappingInfo.Builder builder = new AutoValue_MappedButtonConfig_MappingInfo.Builder();
            builder.setSlot(i);
            builder.setSlotOrder(Integer.MAX_VALUE);
            builder.setConflictOrder(Integer.MAX_VALUE);
            builder.setMutuallyExclusiveButton(-1);
            return builder;
        }

        public abstract int getConflictOrder();

        public abstract int getMutuallyExclusiveButton();

        public abstract int getSlot();

        public abstract int getSlotOrder();
    }

    public MappedButtonConfig(Map<Integer, MappingInfo> map) {
        Map<Integer, MappingInfo> map2 = this.mapping;
        Assert.isNotNull(map);
        map2.putAll(map);
        ArraySet arraySet = new ArraySet();
        for (Map.Entry<Integer, MappingInfo> value : this.mapping.entrySet()) {
            arraySet.add(Integer.valueOf(((MappingInfo) value.getValue()).getSlot()));
        }
        ArrayList arrayList = new ArrayList(arraySet);
        Collections.sort(arrayList);
        this.orderedMappedSlots = arrayList;
    }

    public List<Integer> getButtonsForSlot(int i) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.mapping.entrySet()) {
            if (((MappingInfo) next.getValue()).getSlot() == i) {
                arrayList.add((Integer) next.getKey());
            }
        }
        return arrayList;
    }

    public Comparator<Integer> getConflictComparator() {
        return new Comparator<Integer>() {
            public int compare(Object obj, Object obj2) {
                return MappedButtonConfig.this.lookupMappingInfo(((Integer) obj).intValue()).getConflictOrder() - MappedButtonConfig.this.lookupMappingInfo(((Integer) obj2).intValue()).getConflictOrder();
            }
        };
    }

    public List<Integer> getOrderedMappedSlots() {
        if (this.mapping.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(this.orderedMappedSlots);
    }

    public Comparator<Integer> getSlotComparator() {
        return new Comparator<Integer>() {
            public int compare(Object obj, Object obj2) {
                MappingInfo lookupMappingInfo = MappedButtonConfig.this.lookupMappingInfo(((Integer) obj).intValue());
                MappingInfo lookupMappingInfo2 = MappedButtonConfig.this.lookupMappingInfo(((Integer) obj2).intValue());
                if (lookupMappingInfo.getSlot() == lookupMappingInfo2.getSlot()) {
                    return lookupMappingInfo.getSlotOrder() - lookupMappingInfo2.getSlotOrder();
                }
                throw new IllegalArgumentException("lhs and rhs don't go in the same slot");
            }
        };
    }

    public MappingInfo lookupMappingInfo(int i) {
        MappingInfo mappingInfo = this.mapping.get(Integer.valueOf(i));
        if (mappingInfo != null) {
            return mappingInfo;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unknown InCallButtonId: ");
        outline13.append(R$style.toString2(i));
        throw new IllegalArgumentException(outline13.toString());
    }
}
