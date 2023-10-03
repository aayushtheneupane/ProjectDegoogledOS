package com.android.messaging.p041ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.messaging.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.ui.sa */
public class C1380sa {

    /* renamed from: lG */
    private final List f2196lG;
    /* access modifiers changed from: private */
    public final C1369oa mAction;
    private final Context mContext;
    private final int mDuration;
    /* access modifiers changed from: private */
    public C1378ra mListener;
    private final TextView mMessageView;
    private final View mRootView = LayoutInflater.from(this.mContext).inflate(R.layout.snack_bar, (ViewGroup) null);
    private final String mText;

    /* renamed from: nG */
    private final C1376qa f2197nG;

    /* renamed from: oG */
    private final View f2198oG;

    /* renamed from: rG */
    private final View f2199rG = this.mRootView.findViewById(R.id.snack_bar);

    /* renamed from: sG */
    private final TextView f2200sG;

    /* renamed from: tG */
    private final FrameLayout f2201tG;

    /* synthetic */ C1380sa(C1371pa paVar, C1367na naVar) {
        this.mContext = paVar.mContext;
        this.mText = paVar.f2186kG;
        this.mDuration = paVar.mDuration;
        this.mAction = paVar.mAction;
        this.f2197nG = paVar.f2188nG;
        this.f2198oG = paVar.f2189oG;
        if (paVar.f2187lG == null) {
            this.f2196lG = new ArrayList();
        } else {
            this.f2196lG = paVar.f2187lG;
        }
        this.f2200sG = (TextView) this.mRootView.findViewById(R.id.snack_bar_action);
        this.mMessageView = (TextView) this.mRootView.findViewById(R.id.snack_bar_message);
        this.f2201tG = (FrameLayout) this.mRootView.findViewById(R.id.snack_bar_message_wrapper);
        C1369oa oaVar = this.mAction;
        if (oaVar == null || oaVar.mo7985Yi() == null) {
            this.f2200sG.setVisibility(8);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f2201tG.getLayoutParams();
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.snack_bar_left_right_margin);
            marginLayoutParams.leftMargin = dimensionPixelSize;
            marginLayoutParams.rightMargin = dimensionPixelSize;
            this.f2201tG.setLayoutParams(marginLayoutParams);
        } else {
            this.f2200sG.setVisibility(0);
            this.f2200sG.setText(this.mAction.mo7984Xi());
            this.f2200sG.setOnClickListener(new C1367na(this));
        }
        if (this.mText == null) {
            this.mMessageView.setVisibility(8);
            return;
        }
        this.mMessageView.setVisibility(0);
        this.mMessageView.setText(this.mText);
    }

    /* renamed from: Xi */
    public String mo7997Xi() {
        C1369oa oaVar = this.mAction;
        if (oaVar == null) {
            return null;
        }
        return oaVar.mo7984Xi();
    }

    /* renamed from: _i */
    public List mo7998_i() {
        return this.f2196lG;
    }

    /* renamed from: aj */
    public View mo8000aj() {
        return this.f2198oG;
    }

    /* renamed from: bj */
    public C1376qa mo8001bj() {
        return this.f2197nG;
    }

    /* renamed from: cj */
    public View mo8002cj() {
        return this.f2199rG;
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public View getRootView() {
        return this.mRootView;
    }

    /* renamed from: hf */
    public String mo8006hf() {
        return this.mText;
    }

    public void setEnabled(boolean z) {
        this.f2200sG.setClickable(z);
    }

    /* renamed from: a */
    public void mo7999a(C1378ra raVar) {
        this.mListener = raVar;
    }
}
