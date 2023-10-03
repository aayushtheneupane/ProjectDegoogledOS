package com.android.messaging.p041ui.conversation;

import android.content.Context;
import android.support.p016v4.media.session.C0107q;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0889A;
import com.android.messaging.datamodel.data.C0941x;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1448fa;
import com.android.messaging.util.C1451h;
import com.android.messaging.util.C1452ha;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.conversation.b */
class C1159b implements C0941x {

    /* renamed from: MG */
    final /* synthetic */ boolean f1865MG;
    final /* synthetic */ ComposeMessageView this$0;

    C1159b(ComposeMessageView composeMessageView, boolean z) {
        this.this$0 = composeMessageView;
        this.f1865MG = z;
    }

    /* renamed from: b */
    public void mo6581b(C0889A a, int i) {
        this.this$0.mBinding.mo5929a(a);
        if (i == 0) {
            MessageData d = ((C0889A) this.this$0.mBinding.getData()).mo6184d((C0784d) this.this$0.mBinding);
            if (d.hasContent()) {
                C1451h Hd = C1451h.m3724Hd();
                Context applicationContext = C0967f.get().getApplicationContext();
                if (Hd.getBoolean(applicationContext.getString(R.string.send_sound_pref_key), applicationContext.getResources().getBoolean(R.bool.send_sound_pref_default))) {
                    C1452ha.get().mo8183a(applicationContext, R.raw.message_sent, (C1448fa) null);
                }
                this.this$0.mHost.mo7389a(d);
                ComposeMessageView.m2758d(this.this$0);
                if (C0107q.m134f(this.this$0.getContext())) {
                    C0107q.m128a((View) this.this$0, (AccessibilityManager) null, (CharSequence) C0967f.get().getApplicationContext().getResources().getString(R.string.sending_message));
                }
            }
        } else if (i == 1) {
            C1486ya.m3848Pa(R.string.cant_send_message_while_loading_attachments);
        } else if (i == 2) {
            this.this$0.mHost.mo7384U();
        } else if (i == 3) {
            C1424b.m3592ia(this.f1865MG);
            this.this$0.mHost.mo7394a(true, false);
        } else if (i == 4) {
            C1424b.m3592ia(this.f1865MG);
            this.this$0.mHost.mo7394a(true, true);
        } else if (i == 5) {
            C1486ya.m3848Pa(R.string.cant_send_message_without_active_subscription);
        }
    }
}
