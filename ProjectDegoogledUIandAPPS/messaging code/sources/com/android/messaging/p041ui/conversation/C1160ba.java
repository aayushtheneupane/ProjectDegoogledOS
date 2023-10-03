package com.android.messaging.p041ui.conversation;

import android.view.View;
import androidx.recyclerview.widget.C0586qa;

/* renamed from: com.android.messaging.ui.conversation.ba */
public class C1160ba extends C0586qa {
    final View mView;

    public C1160ba(View view, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        super(view);
        this.mView = view;
        this.mView.setOnClickListener(onClickListener);
        this.mView.setOnLongClickListener(onLongClickListener);
    }
}
