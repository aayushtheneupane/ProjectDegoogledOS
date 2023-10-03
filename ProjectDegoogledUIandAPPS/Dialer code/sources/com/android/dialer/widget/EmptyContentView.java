package com.android.dialer.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;

public class EmptyContentView extends LinearLayout implements View.OnClickListener {
    private int actionLabel;
    private TextView actionView;
    private TextView descriptionView;
    private ImageView imageView;
    private OnEmptyViewActionButtonClickedListener onActionButtonClickedListener;

    public interface OnEmptyViewActionButtonClickedListener {
        void onEmptyViewActionButtonClicked();
    }

    public EmptyContentView(Context context) {
        this(context, (AttributeSet) null, 0, 0);
    }

    public int getActionLabel() {
        return this.actionLabel;
    }

    /* access modifiers changed from: protected */
    public void inflateLayout() {
        setOrientation(1);
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.empty_content_view, this);
    }

    public void onClick(View view) {
        OnEmptyViewActionButtonClickedListener onEmptyViewActionButtonClickedListener = this.onActionButtonClickedListener;
        if (onEmptyViewActionButtonClickedListener != null) {
            onEmptyViewActionButtonClickedListener.onEmptyViewActionButtonClicked();
        }
    }

    public void setActionClickedListener(OnEmptyViewActionButtonClickedListener onEmptyViewActionButtonClickedListener) {
        this.onActionButtonClickedListener = onEmptyViewActionButtonClickedListener;
    }

    public void setActionLabel(int i) {
        this.actionLabel = i;
        if (i == 0) {
            this.actionView.setText((CharSequence) null);
            this.actionView.setVisibility(8);
            return;
        }
        this.actionView.setText(i);
        this.actionView.setVisibility(0);
    }

    public void setDescription(int i) {
        if (i == 0) {
            this.descriptionView.setText((CharSequence) null);
            this.descriptionView.setVisibility(8);
            return;
        }
        this.descriptionView.setText(i);
        this.descriptionView.setVisibility(0);
    }

    public void setImage(int i) {
        if (i == 0) {
            this.imageView.setImageDrawable((Drawable) null);
            this.imageView.setVisibility(8);
            return;
        }
        this.imageView.setImageResource(i);
        this.imageView.setVisibility(0);
    }

    public EmptyContentView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public EmptyContentView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public EmptyContentView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        inflateLayout();
        setClickable(true);
        this.imageView = (ImageView) findViewById(R.id.empty_list_view_image);
        this.descriptionView = (TextView) findViewById(R.id.empty_list_view_message);
        this.actionView = (TextView) findViewById(R.id.empty_list_view_action);
        this.actionView.setOnClickListener(this);
        this.imageView.setImageTintList(ColorStateList.valueOf(((AospThemeImpl) ThemeComponent.get(context).theme()).getColorIconSecondary()));
    }
}
