package com.android.systemui.privacy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.systemui.C1784R$string;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PrivacyItem.kt */
public enum PrivacyType {
    TYPE_CAMERA(C1784R$string.privacy_type_camera, 17303112),
    TYPE_MICROPHONE(C1784R$string.privacy_type_microphone, 17303117),
    TYPE_LOCATION(C1784R$string.privacy_type_location, 17303116);
    
    private final int iconId;
    private final int nameId;

    private PrivacyType(int i, int i2) {
        this.nameId = i;
        this.iconId = i2;
    }

    public final String getName(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return context.getResources().getString(this.nameId);
    }

    public final Drawable getIcon(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return context.getResources().getDrawable(this.iconId, context.getTheme());
    }
}
