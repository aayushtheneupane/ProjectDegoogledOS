package com.android.dialer.historyitemactions;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.dialer.widget.ContactPhotoView;
import com.google.common.collect.ImmutableSet;
import java.util.List;

public class HistoryItemActionBottomSheet extends BottomSheetDialog implements View.OnClickListener {
    private LinearLayout contactLayout;
    private final HistoryItemBottomSheetHeaderInfo historyItemBottomSheetHeaderInfo;
    private final List<HistoryItemActionModule> modules;
    private final ViewTreeObserver.OnPreDrawListener onPreDrawListenerForContactLayout = new ViewTreeObserver.OnPreDrawListener() {
        public final boolean onPreDraw() {
            return HistoryItemActionBottomSheet.this.lambda$new$0$HistoryItemActionBottomSheet();
        }
    };

    private HistoryItemActionBottomSheet(Context context, HistoryItemBottomSheetHeaderInfo historyItemBottomSheetHeaderInfo2, List<HistoryItemActionModule> list) {
        super(context, R.style.HistoryItemBottomSheet);
        this.modules = list;
        Assert.isNotNull(historyItemBottomSheetHeaderInfo2);
        this.historyItemBottomSheetHeaderInfo = historyItemBottomSheetHeaderInfo2;
        setContentView(LayoutInflater.from(context).inflate(R.layout.sheet_layout, (ViewGroup) null));
    }

    private boolean isTouchExplorationEnabled() {
        return ((AccessibilityManager) getContext().getSystemService(AccessibilityManager.class)).isTouchExplorationEnabled();
    }

    public static HistoryItemActionBottomSheet show(Context context, HistoryItemBottomSheetHeaderInfo historyItemBottomSheetHeaderInfo2, List<HistoryItemActionModule> list) {
        HistoryItemActionBottomSheet historyItemActionBottomSheet = new HistoryItemActionBottomSheet(context, historyItemBottomSheetHeaderInfo2, list);
        historyItemActionBottomSheet.show();
        return historyItemActionBottomSheet;
    }

    public /* synthetic */ boolean lambda$new$0$HistoryItemActionBottomSheet() {
        View findViewById = findViewById(R.id.contact_layout_root);
        View findViewById2 = findViewById(R.id.touch_outside);
        View findViewById3 = findViewById(R.id.design_bottom_sheet);
        findViewById.setElevation((findViewById2.getHeight() == findViewById3.getHeight() && BottomSheetBehavior.from(findViewById3).getState() == 3) ? (float) getContext().getResources().getDimensionPixelSize(R.dimen.contact_actions_header_elevation) : 0.0f);
        return true;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.contactLayout.getViewTreeObserver().addOnPreDrawListener(this.onPreDrawListenerForContactLayout);
    }

    public void onClick(View view) {
        if (((HistoryItemActionModule) view.getTag()).onClick()) {
            dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.contact_layout_root);
        Assert.isNotNull(linearLayout);
        this.contactLayout = linearLayout;
        if (isTouchExplorationEnabled()) {
            BottomSheetBehavior.from(findViewById(R.id.design_bottom_sheet)).setState(3);
        }
        BottomSheetBehavior.from(findViewById(R.id.design_bottom_sheet)).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            public void onSlide(View view, float f) {
            }

            public void onStateChanged(View view, int i) {
                ImmutableSet immutableSet;
                if (((AccessibilityManager) HistoryItemActionBottomSheet.this.getContext().getSystemService(AccessibilityManager.class)).isTouchExplorationEnabled()) {
                    immutableSet = ImmutableSet.m88of(4, 5, 6);
                } else {
                    immutableSet = ImmutableSet.m87of(5);
                }
                if (immutableSet.contains(Integer.valueOf(i))) {
                    HistoryItemActionBottomSheet.this.cancel();
                }
            }
        });
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
        }
        ((ContactPhotoView) this.contactLayout.findViewById(R.id.contact_photo_view)).setPhoto(this.historyItemBottomSheetHeaderInfo.getPhotoInfo());
        TextView textView = (TextView) this.contactLayout.findViewById(R.id.secondary_text);
        ((TextView) this.contactLayout.findViewById(R.id.primary_text)).setText(this.historyItemBottomSheetHeaderInfo.getPrimaryText());
        if (!TextUtils.isEmpty(this.historyItemBottomSheetHeaderInfo.getSecondaryText())) {
            textView.setText(this.historyItemBottomSheetHeaderInfo.getSecondaryText());
        } else {
            textView.setVisibility(8);
            textView.setText((CharSequence) null);
        }
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.action_container);
        Assert.isNotNull(linearLayout2);
        LinearLayout linearLayout3 = linearLayout2;
        for (HistoryItemActionModule next : this.modules) {
            if (next instanceof DividerModule) {
                linearLayout3.addView(LayoutInflater.from(getContext()).inflate(R.layout.divider_layout, linearLayout3, false));
            } else {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.module_layout, linearLayout3, false);
                ((TextView) inflate.findViewById(R.id.module_text)).setText(next.getStringId());
                ((ImageView) inflate.findViewById(R.id.module_image)).setImageResource(next.getDrawableId());
                if (next.tintDrawable()) {
                    ((ImageView) inflate.findViewById(R.id.module_image)).setImageTintList(ColorStateList.valueOf(((AospThemeImpl) ThemeComponent.get(getContext()).theme()).getColorIcon()));
                }
                inflate.setOnClickListener(this);
                inflate.setTag(next);
                linearLayout3.addView(inflate);
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.contactLayout.getViewTreeObserver().removeOnPreDrawListener(this.onPreDrawListenerForContactLayout);
    }
}
