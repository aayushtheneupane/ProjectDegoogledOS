package com.android.messaging.datamodel.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.messaging.R;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.datamodel.p038b.C0839B;
import com.android.messaging.datamodel.p038b.C0840C;
import com.android.messaging.datamodel.p038b.C0846I;
import com.android.messaging.datamodel.p038b.C0852O;
import com.android.messaging.datamodel.p038b.C0853P;
import com.android.messaging.datamodel.p038b.C0858V;
import com.android.messaging.datamodel.p038b.C0883w;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1426c;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.data.da */
public class C0921da extends C0909V implements C0839B {

    /* renamed from: Tz */
    private static final Uri f1227Tz = C1426c.m3598a((Uri) null, (CharSequence) null, (String) null, (String) null);

    /* renamed from: Ea */
    private final Uri f1228Ea;

    /* renamed from: Rz */
    private String f1229Rz;

    /* renamed from: Sz */
    private C0853P f1230Sz;
    private final C0783c mBinding = C0784d.m1315q(this);
    private final Context mContext;

    public C0921da(Context context, Uri uri) {
        this.mContext = context;
        this.f1229Rz = this.mContext.getString(R.string.loading_vcard);
        this.f1228Ea = uri;
    }

    /* renamed from: Mb */
    public Intent mo6127Mb() {
        return null;
    }

    /* renamed from: V */
    public void mo5925V(String str) {
        super.mo5925V(str);
        this.mBinding.mo5930b(new C0852O(this.f1228Ea).mo6164a(this.mContext, this));
        C0840C.get().mo6080a((C0883w) this.mBinding.getData());
    }

    /* renamed from: X */
    public void mo5927X(String str) {
        super.mo5927X(str);
        this.mBinding.unbind();
        C0853P p = this.f1230Sz;
        if (p != null) {
            p.release();
            this.f1230Sz = null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0921da)) {
            return false;
        }
        return this.f1228Ea.equals(((C0921da) obj).f1228Ea);
    }

    public long getContactId() {
        return -1;
    }

    public String getDetails() {
        return this.f1229Rz;
    }

    public String getDisplayName() {
        if (!mo6428wf()) {
            return null;
        }
        List Wh = this.f1230Sz.mo6125Wh();
        C1424b.m3592ia(Wh.size() > 0);
        if (Wh.size() == 1) {
            return ((C0858V) Wh.get(0)).getDisplayName();
        }
        return this.mContext.getResources().getQuantityString(R.plurals.vcard_multiple_display_name, Wh.size(), new Object[]{Integer.valueOf(Wh.size())});
    }

    /* renamed from: m */
    public String mo6131m() {
        return null;
    }

    public void onMediaResourceLoadError(C0883w wVar, Exception exc) {
        this.mBinding.mo5935yf();
        this.f1229Rz = this.mContext.getString(R.string.failed_loading_vcard);
        mo6380a(exc);
    }

    public void onMediaResourceLoaded(C0883w wVar, C0846I i, boolean z) {
        C0853P p = (C0853P) i;
        C1424b.m3592ia(this.f1230Sz == null);
        this.mBinding.mo5935yf();
        this.f1229Rz = this.mContext.getString(R.string.vcard_tap_hint);
        this.f1230Sz = p;
        this.f1230Sz.mo6100Oh();
        mo6381tf();
    }

    /* renamed from: rf */
    public Uri mo6132rf() {
        if (mo6428wf()) {
            List Wh = this.f1230Sz.mo6125Wh();
            C1424b.m3592ia(Wh.size() > 0);
            if (Wh.size() == 1) {
                return ((C0858V) Wh.get(0)).mo6139rf();
            }
        }
        return f1227Tz;
    }

    /* renamed from: sf */
    public String mo6133sf() {
        return null;
    }

    /* renamed from: uf */
    public C0853P mo6426uf() {
        if (mo6428wf()) {
            return this.f1230Sz;
        }
        return null;
    }

    /* renamed from: vf */
    public Uri mo6427vf() {
        if (mo6428wf()) {
            return this.f1228Ea;
        }
        return null;
    }

    /* renamed from: wf */
    public boolean mo6428wf() {
        return isBound() && this.f1230Sz != null;
    }
}
