package com.android.systemui.p006qs.tileimpl;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.settingslib.Utils;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.plugins.p005qs.QSIconView;
import com.android.systemui.plugins.p005qs.QSTile;
import java.util.Objects;

/* renamed from: com.android.systemui.qs.tileimpl.QSTileView */
public class QSTileView extends QSTileBaseView {
    private ColorStateList mColorLabelDefault;
    private ColorStateList mColorLabelUnavailable;
    private View mDivider;
    private View mExpandIndicator;
    private View mExpandSpace;
    protected TextView mLabel;
    private ViewGroup mLabelContainer;
    private ImageView mPadLock;
    protected TextView mSecondLine;
    private int mState;

    public QSTileView(Context context, QSIconView qSIconView) {
        this(context, qSIconView, false);
    }

    public QSTileView(Context context, QSIconView qSIconView, boolean z) {
        super(context, qSIconView, z);
        setClipChildren(false);
        setClipToPadding(false);
        setClickable(true);
        setId(View.generateViewId());
        createLabel();
        setOrientation(1);
        setGravity(49);
        this.mColorLabelDefault = Utils.getColorAttr(getContext(), 16842806);
        this.mColorLabelUnavailable = Utils.getColorAttr(getContext(), 16842808);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        FontSizeUtils.updateFontSize(this.mLabel, C1775R$dimen.qs_tile_text_size);
        FontSizeUtils.updateFontSize(this.mSecondLine, C1775R$dimen.qs_tile_text_size);
    }

    public int getDetailY() {
        return getTop() + this.mLabelContainer.getTop() + (this.mLabelContainer.getHeight() / 2);
    }

    /* access modifiers changed from: protected */
    public void createLabel() {
        this.mLabelContainer = (ViewGroup) LayoutInflater.from(getContext()).inflate(C1779R$layout.qs_tile_label, this, false);
        this.mLabelContainer.setClipChildren(false);
        this.mLabelContainer.setClipToPadding(false);
        this.mLabel = (TextView) this.mLabelContainer.findViewById(C1777R$id.tile_label);
        this.mPadLock = (ImageView) this.mLabelContainer.findViewById(C1777R$id.restricted_padlock);
        this.mDivider = this.mLabelContainer.findViewById(C1777R$id.underline);
        this.mExpandIndicator = this.mLabelContainer.findViewById(C1777R$id.expand_indicator);
        this.mExpandSpace = this.mLabelContainer.findViewById(C1777R$id.expand_space);
        this.mSecondLine = (TextView) this.mLabelContainer.findViewById(C1777R$id.app_label);
        addView(this.mLabelContainer);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mLabel.getLineCount() > 2 || (!TextUtils.isEmpty(this.mSecondLine.getText()) && this.mSecondLine.getLineHeight() > this.mSecondLine.getHeight())) {
            this.mLabel.setSingleLine();
            super.onMeasure(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void handleStateChanged(QSTile.State state) {
        ColorStateList colorStateList;
        super.handleStateChanged(state);
        if (!Objects.equals(this.mLabel.getText(), state.label) || this.mState != state.state) {
            TextView textView = this.mLabel;
            if (state.state == 0) {
                colorStateList = this.mColorLabelUnavailable;
            } else {
                colorStateList = this.mColorLabelDefault;
            }
            textView.setTextColor(colorStateList);
            this.mState = state.state;
            this.mLabel.setText(state.label);
        }
        int i = 0;
        if (!Objects.equals(this.mSecondLine.getText(), state.secondaryLabel)) {
            this.mSecondLine.setText(state.secondaryLabel);
            this.mSecondLine.setVisibility(TextUtils.isEmpty(state.secondaryLabel) ? 8 : 0);
        }
        boolean z = state.dualTarget;
        this.mExpandIndicator.setVisibility(8);
        this.mExpandSpace.setVisibility(8);
        Drawable drawable = null;
        this.mLabelContainer.setContentDescription(z ? state.dualLabelContentDescription : null);
        if (z != this.mLabelContainer.isClickable()) {
            this.mLabelContainer.setClickable(z);
            this.mLabelContainer.setLongClickable(z);
            ViewGroup viewGroup = this.mLabelContainer;
            if (z) {
                drawable = newTileBackground();
            }
            viewGroup.setBackground(drawable);
        }
        this.mLabel.setEnabled(!state.disabledByPolicy);
        ImageView imageView = this.mPadLock;
        if (!state.disabledByPolicy) {
            i = 8;
        }
        imageView.setVisibility(i);
    }

    public void init(View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnLongClickListener onLongClickListener) {
        super.init(onClickListener, onClickListener2, onLongClickListener);
        this.mLabelContainer.setOnClickListener(onClickListener2);
        this.mLabelContainer.setOnLongClickListener(onLongClickListener);
        this.mLabelContainer.setClickable(false);
        this.mLabelContainer.setLongClickable(false);
    }

    public void setHideLabel(boolean z) {
        this.mLabelContainer.setVisibility(z ? 8 : 0);
    }
}
