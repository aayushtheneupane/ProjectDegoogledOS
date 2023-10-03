package com.android.messaging.p041ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.p016v4.media.session.C0107q;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.text.BidiFormatter;
import androidx.core.text.TextDirectionHeuristicsCompat;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0908U;
import com.android.messaging.datamodel.data.C0909V;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.datamodel.p037a.C0785e;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.PersonItemView */
public class PersonItemView extends LinearLayout implements C0908U, View.OnLayoutChangeListener {
    protected final C0785e mBinding = C0784d.m1316r(this);
    private ContactIconView mContactIconView;
    /* access modifiers changed from: private */
    public C1117ca mListener;

    /* renamed from: mh */
    private TextView f1668mh;

    /* renamed from: rh */
    private TextView f1669rh;

    /* renamed from: sh */
    private View f1670sh;

    public PersonItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(getContext()).inflate(R.layout.person_item_view, this, true);
    }

    /* renamed from: Zm */
    private void m2629Zm() {
        String displayName = getDisplayName();
        if (TextUtils.isEmpty(displayName)) {
            this.f1668mh.setVisibility(8);
            return;
        }
        this.f1668mh.setVisibility(0);
        this.f1668mh.setText(displayName);
    }

    private String getDisplayName() {
        int measuredWidth = this.f1668mh.getMeasuredWidth();
        String displayName = ((C0909V) this.mBinding.getData()).getDisplayName();
        if (measuredWidth == 0 || TextUtils.isEmpty(displayName) || !displayName.contains(",")) {
            return displayName;
        }
        return BidiFormatter.getInstance().unicodeWrap(C1486ya.m3849a(displayName, this.f1668mh.getPaint(), measuredWidth, getContext().getString(R.string.plus_one), getContext().getString(R.string.plus_n)).toString(), TextDirectionHeuristicsCompat.LTR);
    }

    /* renamed from: Mb */
    public Intent mo7063Mb() {
        return ((C0909V) this.mBinding.getData()).mo6127Mb();
    }

    /* renamed from: Nb */
    public void mo7064Nb() {
        this.mContactIconView.performClick();
    }

    /* renamed from: b */
    public void mo6378b(C0909V v) {
        this.mBinding.mo5929a(v);
        updateViewAppearance();
    }

    /* renamed from: d */
    public void mo7066d(C0909V v) {
        if (this.mBinding.isBound()) {
            if (!((C0909V) this.mBinding.getData()).equals(v)) {
                this.mBinding.unbind();
            } else {
                return;
            }
        }
        if (v != null) {
            this.mBinding.mo5930b(v);
            ((C0909V) this.mBinding.getData()).mo6379a((C0908U) this);
            this.f1668mh.setContentDescription(C0107q.m124a(getResources(), getDisplayName()));
        }
        updateViewAppearance();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mBinding.mo5937zf();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mBinding.isBound()) {
            this.mBinding.detach();
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.f1668mh = (TextView) findViewById(R.id.name);
        this.f1669rh = (TextView) findViewById(R.id.details);
        this.mContactIconView = (ContactIconView) findViewById(R.id.contact_icon);
        this.f1670sh = findViewById(R.id.details_container);
        this.f1668mh.addOnLayoutChangeListener(this);
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.mBinding.isBound() && view == this.f1668mh) {
            m2629Zm();
        }
    }

    /* renamed from: q */
    public void mo7071q(boolean z) {
        this.f1670sh.setVisibility(z ? 8 : 0);
    }

    /* access modifiers changed from: protected */
    public void updateViewAppearance() {
        if (this.mBinding.isBound()) {
            m2629Zm();
            String details = ((C0909V) this.mBinding.getData()).getDetails();
            if (TextUtils.isEmpty(details)) {
                this.f1669rh.setVisibility(8);
            } else {
                this.f1669rh.setVisibility(0);
                this.f1669rh.setText(details);
            }
            this.mContactIconView.mo6929a(((C0909V) this.mBinding.getData()).mo6132rf(), ((C0909V) this.mBinding.getData()).getContactId(), ((C0909V) this.mBinding.getData()).mo6131m(), ((C0909V) this.mBinding.getData()).mo6133sf());
            return;
        }
        this.f1668mh.setText("");
        this.mContactIconView.mo6930f((Uri) null);
    }

    /* renamed from: y */
    public void mo7073y(int i) {
        this.f1669rh.setTextColor(i);
    }

    /* renamed from: z */
    public void mo7074z(int i) {
        this.f1668mh.setTextColor(i);
    }

    /* renamed from: a */
    public void mo7065a(C1117ca caVar) {
        this.mListener = caVar;
        if (this.mListener != null) {
            setOnClickListener(new C1084aa(this));
            C1115ba baVar = new C1115ba(this);
            setOnLongClickListener(baVar);
            this.mContactIconView.setOnLongClickListener(baVar);
        }
    }

    /* renamed from: a */
    public void mo6377a(C0909V v, Exception exc) {
        this.mBinding.mo5929a(v);
        updateViewAppearance();
    }
}
