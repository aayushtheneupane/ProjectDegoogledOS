package com.android.messaging.p041ui.conversation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0917ba;
import com.android.messaging.datamodel.data.C0919ca;
import com.android.messaging.util.C1486ya;
import java.util.List;

/* renamed from: com.android.messaging.ui.conversation.SimSelectorView */
public class SimSelectorView extends FrameLayout implements C1206ya {

    /* renamed from: eg */
    private ListView f1847eg;
    /* access modifiers changed from: private */

    /* renamed from: fg */
    public boolean f1848fg;
    /* access modifiers changed from: private */

    /* renamed from: gg */
    public int f1849gg;
    private final C1132Ba mAdapter = new C1132Ba(this, getContext());
    private C1134Ca mListener;

    public SimSelectorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean isOpen() {
        return this.f1848fg;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1847eg = (ListView) findViewById(R.id.sim_list);
        this.f1847eg.setAdapter(this.mAdapter);
        setOnClickListener(new C1208za(this));
    }

    /* renamed from: s */
    public void mo7451s(int i) {
        this.f1849gg = i;
    }

    /* renamed from: a */
    public void mo7445a(C0919ca caVar) {
        C1132Ba ba = this.mAdapter;
        List Eh = caVar.mo6418Eh();
        ba.clear();
        ba.addAll(Eh);
        ba.notifyDataSetChanged();
    }

    /* renamed from: b */
    public void mo7448b(boolean z, boolean z2) {
        boolean z3 = this.f1848fg;
        boolean z4 = true;
        if (!z || this.mAdapter.getCount() <= 1) {
            z4 = false;
        }
        this.f1848fg = z4;
        boolean z5 = this.f1848fg;
        if (z3 != z5) {
            C1134Ca ca = this.mListener;
            if (ca != null) {
                ((C1192ra) ca).this$0.onVisibilityChanged(z5);
            }
            int i = 8;
            if (z2) {
                setVisibility(0);
                setAlpha(this.f1848fg ? 0.0f : 1.0f);
                animate().alpha(this.f1848fg ? 1.0f : 0.0f).setDuration((long) C1486ya.f2356_K).withEndAction(new C1130Aa(this));
            } else {
                setVisibility(this.f1848fg ? 0 : 8);
            }
            ListView listView = this.f1847eg;
            if (this.f1848fg) {
                i = 0;
            }
            listView.setVisibility(i);
            if (z2) {
                this.f1847eg.clearAnimation();
                TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, this.f1848fg ? 1.0f : 0.0f, 1, this.f1848fg ? 0.0f : 1.0f);
                translateAnimation.setInterpolator(C1486ya.EASE_OUT_INTERPOLATOR);
                translateAnimation.setDuration((long) C1486ya.f2356_K);
                this.f1847eg.startAnimation(translateAnimation);
            }
        }
    }

    /* renamed from: a */
    public void mo7446a(C1134Ca ca) {
        this.mListener = ca;
    }

    /* renamed from: b */
    public void mo7447b(C0917ba baVar) {
        ((C1192ra) this.mListener).this$0.this$0.mHost.mo7390a(baVar);
        mo7448b(false, true);
    }
}
