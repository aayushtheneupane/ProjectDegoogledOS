package com.android.messaging.p041ui;

import android.view.View;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.Ga */
class C1044Ga implements View.OnLayoutChangeListener {
    final /* synthetic */ VCardDetailFragment this$0;

    C1044Ga(VCardDetailFragment vCardDetailFragment) {
        this.this$0 = vCardDetailFragment;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.this$0.mListView.setIndicatorBounds(this.this$0.mListView.getWidth() - this.this$0.getResources().getDimensionPixelSize(R.dimen.vcard_detail_group_indicator_width), this.this$0.mListView.getWidth());
    }
}
