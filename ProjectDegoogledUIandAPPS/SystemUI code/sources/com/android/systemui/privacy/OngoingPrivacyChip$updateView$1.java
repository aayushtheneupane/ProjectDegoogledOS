package com.android.systemui.privacy;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: OngoingPrivacyChip.kt */
final class OngoingPrivacyChip$updateView$1 extends Lambda implements Function2<PrivacyDialogBuilder, ViewGroup, Unit> {
    final /* synthetic */ int $networkTraffic;
    final /* synthetic */ OngoingPrivacyChip this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OngoingPrivacyChip$updateView$1(OngoingPrivacyChip ongoingPrivacyChip, int i) {
        super(2);
        this.this$0 = ongoingPrivacyChip;
        this.$networkTraffic = i;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((PrivacyDialogBuilder) obj, (ViewGroup) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(PrivacyDialogBuilder privacyDialogBuilder, ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(privacyDialogBuilder, "dialogBuilder");
        Intrinsics.checkParameterIsNotNull(viewGroup, "iconsContainer");
        viewGroup.removeAllViews();
        int i = 0;
        for (T next : privacyDialogBuilder.generateIcons()) {
            int i2 = i + 1;
            if (i >= 0) {
                Drawable drawable = (Drawable) next;
                drawable.mutate();
                drawable.setTint(this.this$0.iconColor);
                ImageView imageView = new ImageView(this.this$0.getContext());
                imageView.setImageDrawable(drawable);
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                viewGroup.addView(imageView, this.this$0.iconSize, this.this$0.iconSize);
                if (i != 0) {
                    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                    if (layoutParams != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                        marginLayoutParams.setMarginStart((!this.this$0.getExpanded() || this.$networkTraffic != 0) ? this.this$0.iconMarginCollapsed : this.this$0.iconMarginExpanded);
                        imageView.setLayoutParams(marginLayoutParams);
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                    }
                }
                i = i2;
            } else {
                CollectionsKt.throwIndexOverflow();
                throw null;
            }
        }
    }
}
