package com.android.messaging.p041ui.conversation;

import android.content.Context;
import android.support.p016v4.media.session.C0107q;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.core.util.Pair;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0917ba;
import com.android.messaging.datamodel.data.C0919ca;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1480va;

/* renamed from: com.android.messaging.ui.conversation.Z */
class C1156Z extends C1148Q {

    /* renamed from: YG */
    private SimSelectorView f1851YG;

    /* renamed from: ZG */
    private Pair f1852ZG;

    /* renamed from: _G */
    private boolean f1853_G;

    /* renamed from: aH */
    private String f1854aH;
    final /* synthetic */ C1158aa this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1156Z(C1158aa aaVar, C1147P p) {
        super(p, false);
        this.this$0 = aaVar;
    }

    /* renamed from: n */
    private boolean m2968n(boolean z, boolean z2) {
        if (!C1464na.m3759Zj()) {
            return false;
        }
        if (this.f1853_G) {
            this.f1851YG.mo7448b(z, z2);
            if (this.f1851YG.isOpen() == z) {
                return true;
            }
            return false;
        }
        this.f1852ZG = new Pair(Boolean.valueOf(z), Boolean.valueOf(z2));
        return false;
    }

    /* renamed from: aa */
    public boolean mo7435aa(boolean z) {
        boolean n = m2968n(false, z);
        this.this$0.mHost.mo7398d(false);
        return n;
    }

    /* renamed from: b */
    public void mo7461b(C0919ca caVar) {
        if (this.f1851YG == null) {
            this.f1851YG = this.this$0.mHost.mo7378C();
            this.f1851YG.mo7451s(this.this$0.mHost.mo7385a());
            this.f1851YG.mo7446a((C1134Ca) new C1192ra(this));
        }
        this.f1851YG.mo7445a(caVar);
        this.f1853_G = caVar != null && caVar.hasData();
        if (this.f1852ZG != null && this.f1853_G) {
            C1424b.m3592ia(C1464na.m3759Zj());
            C1480va.getMainThreadHandler().post(new C1190qa(this, ((Boolean) this.f1852ZG.first).booleanValue(), ((Boolean) this.f1852ZG.second).booleanValue()));
            this.f1852ZG = null;
        }
    }

    /* renamed from: d */
    public void mo7462d(C0917ba baVar) {
        this.f1854aH = baVar == null ? null : baVar.displayName;
    }

    public boolean show(boolean z) {
        Context applicationContext = C0967f.get().getApplicationContext();
        if (C0107q.m134f(applicationContext) && !TextUtils.isEmpty(this.f1854aH)) {
            C0107q.m128a((View) this.f1851YG, (AccessibilityManager) null, (CharSequence) applicationContext.getString(R.string.selected_sim_content_message, new Object[]{this.f1854aH}));
        }
        boolean n = m2968n(true, z);
        this.this$0.mHost.mo7398d(true);
        return n;
    }
}
