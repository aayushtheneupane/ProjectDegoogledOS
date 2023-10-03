package com.google.android.apps.photosgo.oneup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public final class OneUpActionsView extends LinearLayout {

    /* renamed from: a */
    public final View f4877a;

    /* renamed from: b */
    public final View f4878b;

    /* renamed from: c */
    public final View f4879c;

    /* renamed from: d */
    public final View f4880d;

    public OneUpActionsView(Context context) {
        this(context, (AttributeSet) null);
    }

    public OneUpActionsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OneUpActionsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.oneup_actions_view, this, true);
        this.f4877a = findViewById(R.id.oneup_share);
        this.f4878b = findViewById(R.id.oneup_auto_enhance);
        this.f4879c = findViewById(R.id.oneup_edit);
        this.f4880d = findViewById(R.id.oneup_delete);
    }

    /* renamed from: a */
    public static void m4816a(View view, int i) {
        int i2 = i - 1;
        if (i2 == 0) {
            view.setVisibility(0);
            view.setActivated(true);
        } else if (i2 != 1) {
            view.setVisibility(8);
        } else {
            view.setVisibility(0);
            view.setActivated(false);
        }
    }
}
