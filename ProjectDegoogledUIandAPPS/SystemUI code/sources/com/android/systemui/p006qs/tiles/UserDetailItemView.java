package com.android.systemui.p006qs.tiles;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.internal.util.ArrayUtils;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.R$styleable;
import com.android.systemui.statusbar.phone.UserAvatarView;

/* renamed from: com.android.systemui.qs.tiles.UserDetailItemView */
public class UserDetailItemView extends LinearLayout {
    protected static int layoutResId = C1779R$layout.qs_user_detail_item;
    private Typeface mActivatedTypeface;
    private UserAvatarView mAvatar;
    private TextView mName;
    private Typeface mRegularTypeface;
    private View mRestrictedPadlock;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public UserDetailItemView(Context context) {
        this(context, (AttributeSet) null);
    }

    public UserDetailItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UserDetailItemView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public UserDetailItemView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.UserDetailItemView, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            if (index == R$styleable.UserDetailItemView_regularFontFamily) {
                this.mRegularTypeface = Typeface.create(obtainStyledAttributes.getString(index), 0);
            } else if (index == R$styleable.UserDetailItemView_activatedFontFamily) {
                this.mActivatedTypeface = Typeface.create(obtainStyledAttributes.getString(index), 0);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public static UserDetailItemView convertOrInflate(Context context, View view, ViewGroup viewGroup) {
        if (!(view instanceof UserDetailItemView)) {
            view = LayoutInflater.from(context).inflate(layoutResId, viewGroup, false);
        }
        return (UserDetailItemView) view;
    }

    public void bind(String str, Bitmap bitmap, int i) {
        this.mName.setText(str);
        this.mAvatar.setAvatarWithBadge(bitmap, i);
    }

    public void bind(String str, Drawable drawable, int i) {
        this.mName.setText(str);
        this.mAvatar.setDrawableWithBadge(drawable, i);
    }

    public void setAvatarEnabled(boolean z) {
        this.mAvatar.setEnabled(z);
    }

    public void setDisabledByAdmin(boolean z) {
        this.mRestrictedPadlock.setVisibility(z ? 0 : 8);
        this.mName.setEnabled(!z);
        this.mAvatar.setEnabled(!z);
    }

    public void setEnabled(boolean z) {
        this.mName.setEnabled(z);
        this.mAvatar.setEnabled(z);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.mAvatar = (UserAvatarView) findViewById(C1777R$id.user_picture);
        this.mName = (TextView) findViewById(C1777R$id.user_name);
        if (this.mRegularTypeface == null) {
            this.mRegularTypeface = this.mName.getTypeface();
        }
        if (this.mActivatedTypeface == null) {
            this.mActivatedTypeface = this.mName.getTypeface();
        }
        updateTypeface();
        this.mRestrictedPadlock = findViewById(C1777R$id.restricted_padlock);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        FontSizeUtils.updateFontSize(this.mName, C1775R$dimen.qs_detail_item_secondary_text_size);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        updateTypeface();
    }

    private void updateTypeface() {
        this.mName.setTypeface(ArrayUtils.contains(getDrawableState(), 16843518) ? this.mActivatedTypeface : this.mRegularTypeface);
    }
}
