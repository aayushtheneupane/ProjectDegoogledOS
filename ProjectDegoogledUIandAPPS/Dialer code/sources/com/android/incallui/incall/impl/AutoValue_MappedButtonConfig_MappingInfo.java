package com.android.incallui.incall.impl;

import com.android.incallui.incall.impl.MappedButtonConfig;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_MappedButtonConfig_MappingInfo extends MappedButtonConfig.MappingInfo {
    private final int conflictOrder;
    private final int mutuallyExclusiveButton;
    private final int slot;
    private final int slotOrder;

    static final class Builder extends MappedButtonConfig.MappingInfo.Builder {
        private Integer conflictOrder;
        private Integer mutuallyExclusiveButton;
        private Integer slot;
        private Integer slotOrder;

        Builder() {
        }

        public MappedButtonConfig.MappingInfo build() {
            String str = "";
            if (this.slot == null) {
                str = GeneratedOutlineSupport.outline8(str, " slot");
            }
            if (this.slotOrder == null) {
                str = GeneratedOutlineSupport.outline8(str, " slotOrder");
            }
            if (this.conflictOrder == null) {
                str = GeneratedOutlineSupport.outline8(str, " conflictOrder");
            }
            if (this.mutuallyExclusiveButton == null) {
                str = GeneratedOutlineSupport.outline8(str, " mutuallyExclusiveButton");
            }
            if (str.isEmpty()) {
                return new AutoValue_MappedButtonConfig_MappingInfo(this.slot.intValue(), this.slotOrder.intValue(), this.conflictOrder.intValue(), this.mutuallyExclusiveButton.intValue(), (C07131) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public MappedButtonConfig.MappingInfo.Builder setConflictOrder(int i) {
            this.conflictOrder = Integer.valueOf(i);
            return this;
        }

        public MappedButtonConfig.MappingInfo.Builder setMutuallyExclusiveButton(int i) {
            this.mutuallyExclusiveButton = Integer.valueOf(i);
            return this;
        }

        public MappedButtonConfig.MappingInfo.Builder setSlot(int i) {
            this.slot = Integer.valueOf(i);
            return this;
        }

        public MappedButtonConfig.MappingInfo.Builder setSlotOrder(int i) {
            this.slotOrder = Integer.valueOf(i);
            return this;
        }
    }

    /* synthetic */ AutoValue_MappedButtonConfig_MappingInfo(int i, int i2, int i3, int i4, C07131 r5) {
        this.slot = i;
        this.slotOrder = i2;
        this.conflictOrder = i3;
        this.mutuallyExclusiveButton = i4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MappedButtonConfig.MappingInfo)) {
            return false;
        }
        MappedButtonConfig.MappingInfo mappingInfo = (MappedButtonConfig.MappingInfo) obj;
        if (this.slot == ((AutoValue_MappedButtonConfig_MappingInfo) mappingInfo).slot) {
            AutoValue_MappedButtonConfig_MappingInfo autoValue_MappedButtonConfig_MappingInfo = (AutoValue_MappedButtonConfig_MappingInfo) mappingInfo;
            if (this.slotOrder == autoValue_MappedButtonConfig_MappingInfo.slotOrder && this.conflictOrder == autoValue_MappedButtonConfig_MappingInfo.conflictOrder && this.mutuallyExclusiveButton == mappingInfo.getMutuallyExclusiveButton()) {
                return true;
            }
        }
        return false;
    }

    public int getConflictOrder() {
        return this.conflictOrder;
    }

    public int getMutuallyExclusiveButton() {
        return this.mutuallyExclusiveButton;
    }

    public int getSlot() {
        return this.slot;
    }

    public int getSlotOrder() {
        return this.slotOrder;
    }

    public int hashCode() {
        return this.mutuallyExclusiveButton ^ ((((((this.slot ^ 1000003) * 1000003) ^ this.slotOrder) * 1000003) ^ this.conflictOrder) * 1000003);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("MappingInfo{slot=");
        outline13.append(this.slot);
        outline13.append(", slotOrder=");
        outline13.append(this.slotOrder);
        outline13.append(", conflictOrder=");
        outline13.append(this.conflictOrder);
        outline13.append(", mutuallyExclusiveButton=");
        outline13.append(this.mutuallyExclusiveButton);
        outline13.append("}");
        return outline13.toString();
    }
}
