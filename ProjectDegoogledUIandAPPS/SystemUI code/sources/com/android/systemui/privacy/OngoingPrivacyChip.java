package com.android.systemui.privacy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.android.systemui.C1774R$color;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1784R$string;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OngoingPrivacyChip.kt */
public final class OngoingPrivacyChip extends LinearLayout {
    private FrameLayout back;
    private final Drawable backgroundDrawable;
    private PrivacyDialogBuilder builder;
    private boolean expanded;
    /* access modifiers changed from: private */
    public final int iconColor;
    /* access modifiers changed from: private */
    public final int iconMarginCollapsed;
    /* access modifiers changed from: private */
    public final int iconMarginExpanded;
    /* access modifiers changed from: private */
    public final int iconSize;
    private LinearLayout iconsContainer;
    private List<PrivacyItem> privacyList;
    private final int sidePadding;

    public OngoingPrivacyChip(Context context) {
        this(context, (AttributeSet) null, 0, 0, 14, (DefaultConstructorMarker) null);
    }

    public OngoingPrivacyChip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, (DefaultConstructorMarker) null);
    }

    public OngoingPrivacyChip(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OngoingPrivacyChip(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OngoingPrivacyChip(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.iconMarginExpanded = context.getResources().getDimensionPixelSize(C1775R$dimen.ongoing_appops_chip_icon_margin_expanded);
        this.iconMarginCollapsed = context.getResources().getDimensionPixelSize(C1775R$dimen.ongoing_appops_chip_icon_margin_collapsed);
        this.iconSize = context.getResources().getDimensionPixelSize(C1775R$dimen.ongoing_appops_chip_icon_size);
        this.iconColor = context.getResources().getColor(C1774R$color.status_bar_clock_color, context.getTheme());
        this.sidePadding = context.getResources().getDimensionPixelSize(C1775R$dimen.ongoing_appops_chip_side_padding);
        this.backgroundDrawable = context.getDrawable(C1776R$drawable.privacy_chip_bg);
        this.builder = new PrivacyDialogBuilder(context, CollectionsKt__CollectionsKt.emptyList());
        this.privacyList = CollectionsKt__CollectionsKt.emptyList();
    }

    public final boolean getExpanded() {
        return this.expanded;
    }

    public final void setExpanded(boolean z) {
        if (z != this.expanded) {
            this.expanded = z;
            updateView();
        }
    }

    public final PrivacyDialogBuilder getBuilder() {
        return this.builder;
    }

    public final void setPrivacyList(List<PrivacyItem> list) {
        Intrinsics.checkParameterIsNotNull(list, "value");
        this.privacyList = list;
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        this.builder = new PrivacyDialogBuilder(context, list);
        updateView();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        View findViewById = findViewById(C1777R$id.background);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.background)");
        this.back = (FrameLayout) findViewById;
        View findViewById2 = findViewById(C1777R$id.icons_container);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.icons_container)");
        this.iconsContainer = (LinearLayout) findViewById2;
    }

    private final void updateView() {
        int i = Settings.System.getInt(getContext().getContentResolver(), "network_traffic_location", 0);
        FrameLayout frameLayout = this.back;
        if (frameLayout != null) {
            frameLayout.setBackground((!this.expanded || i != 0) ? null : this.backgroundDrawable);
            int i2 = (!this.expanded || i != 0) ? 0 : this.sidePadding;
            FrameLayout frameLayout2 = this.back;
            if (frameLayout2 != null) {
                frameLayout2.setPaddingRelative(i2, 0, i2, 0);
                OngoingPrivacyChip$updateView$1 ongoingPrivacyChip$updateView$1 = new OngoingPrivacyChip$updateView$1(this, i);
                if (!this.privacyList.isEmpty()) {
                    generateContentDescription();
                    PrivacyDialogBuilder privacyDialogBuilder = this.builder;
                    LinearLayout linearLayout = this.iconsContainer;
                    if (linearLayout != null) {
                        ongoingPrivacyChip$updateView$1.invoke(privacyDialogBuilder, (ViewGroup) linearLayout);
                        LinearLayout linearLayout2 = this.iconsContainer;
                        if (linearLayout2 != null) {
                            ViewGroup.LayoutParams layoutParams = linearLayout2.getLayoutParams();
                            if (layoutParams != null) {
                                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                                layoutParams2.gravity = ((!this.expanded || i != 0) ? 8388613 : 1) | 16;
                                LinearLayout linearLayout3 = this.iconsContainer;
                                if (linearLayout3 != null) {
                                    linearLayout3.setLayoutParams(layoutParams2);
                                } else {
                                    Intrinsics.throwUninitializedPropertyAccessException("iconsContainer");
                                    throw null;
                                }
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
                            }
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("iconsContainer");
                            throw null;
                        }
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("iconsContainer");
                        throw null;
                    }
                } else {
                    LinearLayout linearLayout4 = this.iconsContainer;
                    if (linearLayout4 != null) {
                        linearLayout4.removeAllViews();
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("iconsContainer");
                        throw null;
                    }
                }
                requestLayout();
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("back");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("back");
        throw null;
    }

    private final void generateContentDescription() {
        String joinTypes = this.builder.joinTypes();
        setContentDescription(getContext().getString(C1784R$string.ongoing_privacy_chip_content_multiple_apps, new Object[]{joinTypes}));
    }
}
