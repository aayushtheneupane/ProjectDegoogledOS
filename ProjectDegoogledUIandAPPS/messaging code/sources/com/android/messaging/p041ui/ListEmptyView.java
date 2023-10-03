package com.android.messaging.p041ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.ListEmptyView */
public class ListEmptyView extends LinearLayout {

    /* renamed from: ph */
    private ImageView f1650ph;

    /* renamed from: qh */
    private TextView f1651qh;

    public ListEmptyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: o */
    public void mo7033o(boolean z) {
        this.f1650ph.setVisibility(z ? 0 : 8);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1650ph = (ImageView) findViewById(R.id.empty_image_hint);
        this.f1651qh = (TextView) findViewById(R.id.empty_text_hint);
    }

    /* renamed from: p */
    public void mo7035p(boolean z) {
        int i = z ? 17 : 49;
        ((LinearLayout.LayoutParams) this.f1650ph.getLayoutParams()).gravity = i;
        ((LinearLayout.LayoutParams) this.f1651qh.getLayoutParams()).gravity = i;
        getLayoutParams().height = z ? -2 : -1;
        requestLayout();
    }

    /* renamed from: w */
    public void mo7036w(int i) {
        this.f1650ph.setImageResource(i);
    }

    /* renamed from: x */
    public void mo7037x(int i) {
        this.f1651qh.setText(getResources().getText(i));
    }
}
