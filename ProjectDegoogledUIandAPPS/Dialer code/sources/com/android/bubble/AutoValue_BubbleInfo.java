package com.android.bubble;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import com.android.bubble.BubbleInfo;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.List;

final class AutoValue_BubbleInfo extends BubbleInfo {
    private final List<BubbleInfo.Action> actions;
    private final Drawable avatar;
    private final int primaryColor;
    private final Icon primaryIcon;
    private final int startingYPosition;

    static final class Builder extends BubbleInfo.Builder {
        private List<BubbleInfo.Action> actions;
        private Drawable avatar;
        private Integer primaryColor;
        private Icon primaryIcon;
        private Integer startingYPosition;

        Builder() {
        }

        public BubbleInfo build() {
            String str = "";
            if (this.primaryColor == null) {
                str = GeneratedOutlineSupport.outline8(str, " primaryColor");
            }
            if (this.primaryIcon == null) {
                str = GeneratedOutlineSupport.outline8(str, " primaryIcon");
            }
            if (this.startingYPosition == null) {
                str = GeneratedOutlineSupport.outline8(str, " startingYPosition");
            }
            if (this.actions == null) {
                str = GeneratedOutlineSupport.outline8(str, " actions");
            }
            if (str.isEmpty()) {
                return new AutoValue_BubbleInfo(this.primaryColor.intValue(), this.primaryIcon, this.avatar, this.startingYPosition.intValue(), this.actions, (C02431) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public BubbleInfo.Builder setActions(List<BubbleInfo.Action> list) {
            if (list != null) {
                this.actions = list;
                return this;
            }
            throw new NullPointerException("Null actions");
        }

        public BubbleInfo.Builder setPrimaryColor(int i) {
            this.primaryColor = Integer.valueOf(i);
            return this;
        }

        public BubbleInfo.Builder setPrimaryIcon(Icon icon) {
            if (icon != null) {
                this.primaryIcon = icon;
                return this;
            }
            throw new NullPointerException("Null primaryIcon");
        }

        public BubbleInfo.Builder setStartingYPosition(int i) {
            this.startingYPosition = Integer.valueOf(i);
            return this;
        }
    }

    /* synthetic */ AutoValue_BubbleInfo(int i, Icon icon, Drawable drawable, int i2, List list, C02431 r6) {
        this.primaryColor = i;
        this.primaryIcon = icon;
        this.avatar = drawable;
        this.startingYPosition = i2;
        this.actions = list;
    }

    public boolean equals(Object obj) {
        Drawable drawable;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BubbleInfo)) {
            return false;
        }
        AutoValue_BubbleInfo autoValue_BubbleInfo = (AutoValue_BubbleInfo) obj;
        if (this.primaryColor != autoValue_BubbleInfo.primaryColor || !this.primaryIcon.equals(autoValue_BubbleInfo.primaryIcon) || ((drawable = this.avatar) != null ? !drawable.equals(autoValue_BubbleInfo.avatar) : autoValue_BubbleInfo.avatar != null) || this.startingYPosition != autoValue_BubbleInfo.startingYPosition || !this.actions.equals(autoValue_BubbleInfo.actions)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (((this.primaryColor ^ 1000003) * 1000003) ^ this.primaryIcon.hashCode()) * 1000003;
        Drawable drawable = this.avatar;
        return this.actions.hashCode() ^ ((((hashCode ^ (drawable == null ? 0 : drawable.hashCode())) * 1000003) ^ this.startingYPosition) * 1000003);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("BubbleInfo{primaryColor=");
        outline13.append(this.primaryColor);
        outline13.append(", primaryIcon=");
        outline13.append(this.primaryIcon);
        outline13.append(", avatar=");
        outline13.append(this.avatar);
        outline13.append(", startingYPosition=");
        outline13.append(this.startingYPosition);
        outline13.append(", actions=");
        return GeneratedOutlineSupport.outline11(outline13, this.actions, "}");
    }
}
