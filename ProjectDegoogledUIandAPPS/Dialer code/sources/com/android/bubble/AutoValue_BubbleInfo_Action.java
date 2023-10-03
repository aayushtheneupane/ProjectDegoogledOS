package com.android.bubble;

import android.app.PendingIntent;
import android.graphics.drawable.Drawable;
import com.android.bubble.BubbleInfo;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_BubbleInfo_Action extends BubbleInfo.Action {
    private final boolean checkable;
    private final boolean checked;
    private final Drawable iconDrawable;
    private final PendingIntent intent;
    private final CharSequence name;
    private final Drawable secondaryIconDrawable;

    static final class Builder extends BubbleInfo.Action.Builder {
        private Boolean checkable;
        private Boolean checked;
        private Drawable iconDrawable;
        private PendingIntent intent;
        private CharSequence name;
        private Drawable secondaryIconDrawable;

        Builder() {
        }

        public BubbleInfo.Action build() {
            String str = "";
            if (this.iconDrawable == null) {
                str = GeneratedOutlineSupport.outline8(str, " iconDrawable");
            }
            if (this.name == null) {
                str = GeneratedOutlineSupport.outline8(str, " name");
            }
            if (this.intent == null) {
                str = GeneratedOutlineSupport.outline8(str, " intent");
            }
            if (this.checkable == null) {
                str = GeneratedOutlineSupport.outline8(str, " checkable");
            }
            if (this.checked == null) {
                str = GeneratedOutlineSupport.outline8(str, " checked");
            }
            if (str.isEmpty()) {
                return new AutoValue_BubbleInfo_Action(this.iconDrawable, this.secondaryIconDrawable, this.name, this.intent, this.checkable.booleanValue(), this.checked.booleanValue(), (C02441) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public BubbleInfo.Action.Builder setCheckable(boolean z) {
            this.checkable = Boolean.valueOf(z);
            return this;
        }

        public BubbleInfo.Action.Builder setChecked(boolean z) {
            this.checked = Boolean.valueOf(z);
            return this;
        }

        public BubbleInfo.Action.Builder setIconDrawable(Drawable drawable) {
            if (drawable != null) {
                this.iconDrawable = drawable;
                return this;
            }
            throw new NullPointerException("Null iconDrawable");
        }

        public BubbleInfo.Action.Builder setIntent(PendingIntent pendingIntent) {
            if (pendingIntent != null) {
                this.intent = pendingIntent;
                return this;
            }
            throw new NullPointerException("Null intent");
        }

        public BubbleInfo.Action.Builder setName(CharSequence charSequence) {
            if (charSequence != null) {
                this.name = charSequence;
                return this;
            }
            throw new NullPointerException("Null name");
        }

        public BubbleInfo.Action.Builder setSecondaryIconDrawable(Drawable drawable) {
            this.secondaryIconDrawable = drawable;
            return this;
        }
    }

    /* synthetic */ AutoValue_BubbleInfo_Action(Drawable drawable, Drawable drawable2, CharSequence charSequence, PendingIntent pendingIntent, boolean z, boolean z2, C02441 r7) {
        this.iconDrawable = drawable;
        this.secondaryIconDrawable = drawable2;
        this.name = charSequence;
        this.intent = pendingIntent;
        this.checkable = z;
        this.checked = z2;
    }

    public boolean equals(Object obj) {
        Drawable drawable;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BubbleInfo.Action)) {
            return false;
        }
        AutoValue_BubbleInfo_Action autoValue_BubbleInfo_Action = (AutoValue_BubbleInfo_Action) obj;
        if (!this.iconDrawable.equals(autoValue_BubbleInfo_Action.iconDrawable) || ((drawable = this.secondaryIconDrawable) != null ? !drawable.equals(autoValue_BubbleInfo_Action.secondaryIconDrawable) : autoValue_BubbleInfo_Action.secondaryIconDrawable != null) || !this.name.equals(autoValue_BubbleInfo_Action.name) || !this.intent.equals(autoValue_BubbleInfo_Action.intent) || this.checkable != autoValue_BubbleInfo_Action.checkable || this.checked != autoValue_BubbleInfo_Action.checked) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (this.iconDrawable.hashCode() ^ 1000003) * 1000003;
        Drawable drawable = this.secondaryIconDrawable;
        int i = 1231;
        int hashCode2 = (((((((hashCode ^ (drawable == null ? 0 : drawable.hashCode())) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.intent.hashCode()) * 1000003) ^ (this.checkable ? 1231 : 1237)) * 1000003;
        if (!this.checked) {
            i = 1237;
        }
        return hashCode2 ^ i;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Action{iconDrawable=");
        outline13.append(this.iconDrawable);
        outline13.append(", secondaryIconDrawable=");
        outline13.append(this.secondaryIconDrawable);
        outline13.append(", name=");
        outline13.append(this.name);
        outline13.append(", intent=");
        outline13.append(this.intent);
        outline13.append(", checkable=");
        outline13.append(this.checkable);
        outline13.append(", checked=");
        outline13.append(this.checked);
        outline13.append("}");
        return outline13.toString();
    }
}
