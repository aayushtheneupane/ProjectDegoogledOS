package com.android.messaging.datamodel.action;

import android.content.Context;
import android.content.res.Resources;
import android.support.p016v4.media.session.C0107q;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.C1480va;

/* renamed from: com.android.messaging.datamodel.action.i */
public class C0819i {
    /* renamed from: Oa */
    private static void m1492Oa(int i) {
        C1480va.getMainThreadHandler().post(new C0816f(i));
    }

    /* renamed from: a */
    static void m1494a(String str, boolean z, int i, boolean z2, int i2, boolean z3) {
        if (!z && i == 2) {
            C1474sa saVar = C1474sa.get(i2);
            if (saVar.mo8227jk()) {
                if (z3) {
                    m1492Oa(R.string.send_message_failure_airplane_mode);
                    return;
                } else {
                    m1492Oa(R.string.download_message_failure_airplane_mode);
                    return;
                }
            } else if (!z2 && !saVar.isMobileDataEnabled()) {
                if (z3) {
                    m1492Oa(R.string.send_message_failure_no_data);
                    return;
                } else {
                    m1492Oa(R.string.download_message_failure_no_data);
                    return;
                }
            }
        }
        if (!C0107q.m134f(C0967f.get().getApplicationContext())) {
            return;
        }
        if (C0947h.get().mo6588L(str) && z) {
            m1492Oa(z3 ? R.string.send_message_success : R.string.download_message_success);
        } else if (C0947h.get().mo6589M(str) && !z) {
            m1492Oa(z3 ? R.string.send_message_failure : R.string.download_message_failure);
        }
    }

    /* renamed from: k */
    public static C0806U m1495k(Context context) {
        return new C0818h(context);
    }

    /* renamed from: a */
    public static void m1493a(String str, ParticipantData participantData, MessageData messageData) {
        String str2;
        Context applicationContext = C0967f.get().getApplicationContext();
        if (C0107q.m134f(applicationContext) && C0947h.get().mo6588L(str)) {
            Resources resources = applicationContext.getResources();
            Object[] objArr = new Object[2];
            objArr[0] = participantData == null ? resources.getString(R.string.unknown_sender) : participantData.mo6330P(false);
            if (messageData == null) {
                str2 = "";
            } else {
                str2 = messageData.mo6274hf();
            }
            objArr[1] = str2;
            C1480va.getMainThreadHandler().post(new C0817g(resources.getString(R.string.incoming_message_announcement, objArr)));
        }
    }
}
