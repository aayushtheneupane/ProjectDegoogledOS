package com.android.messaging.p041ui.conversation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.text.format.Formatter;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0936s;
import com.android.messaging.datamodel.data.C0938u;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1410N;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.C1485y;
import java.util.Iterator;

/* renamed from: com.android.messaging.ui.conversation.va */
public class C1200va {
    /* renamed from: a */
    public static void m3041a(Context context, C0936s sVar, C0938u uVar, ParticipantData participantData) {
        C1410N.m3547Nj();
        m3045p(context, m3044b(context, sVar, uVar, participantData));
    }

    /* renamed from: b */
    private static String m3044b(Context context, C0936s sVar, C0938u uVar, ParticipantData participantData) {
        String str;
        if (sVar.mo6551kg()) {
            Resources resources = C0967f.get().getApplicationContext().getResources();
            StringBuilder sb = new StringBuilder();
            sb.append(resources.getString(R.string.message_type_label));
            sb.append(resources.getString(R.string.text_message));
            String Ag = sVar.mo6525Ag();
            if (!TextUtils.isEmpty(Ag)) {
                sb.append(10);
                sb.append(resources.getString(R.string.from_label));
                sb.append(Ag);
            }
            String a = m3040a(uVar, sVar.mo6556pg(), sVar.mo6546gg(), sVar.mo6559sg());
            if (!TextUtils.isEmpty(a)) {
                sb.append(10);
                sb.append(resources.getString(R.string.to_address_label));
                sb.append(a);
            }
            if (sVar.mo6546gg() && sVar.mo6527Cg() != 0) {
                sb.append(10);
                sb.append(resources.getString(R.string.sent_label));
                sb.append(C1485y.m3837D(sVar.mo6527Cg()).toString());
            }
            m3043a(resources, sb, sVar);
            m3042a(resources, participantData, sb);
            C1410N.m3547Nj();
            return sb.toString();
        }
        Resources resources2 = C0967f.get().getApplicationContext().getResources();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(resources2.getString(R.string.message_type_label));
        sb2.append(resources2.getString(R.string.multimedia_message));
        String Ag2 = sVar.mo6525Ag();
        sb2.append(10);
        sb2.append(resources2.getString(R.string.from_label));
        if (TextUtils.isEmpty(Ag2)) {
            Ag2 = resources2.getString(R.string.hidden_sender_address);
        }
        sb2.append(Ag2);
        String a2 = m3040a(uVar, sVar.mo6556pg(), sVar.mo6546gg(), sVar.mo6559sg());
        if (!TextUtils.isEmpty(a2)) {
            sb2.append(10);
            sb2.append(resources2.getString(R.string.to_address_label));
            sb2.append(a2);
        }
        m3043a(resources2, sb2, sVar);
        sb2.append(10);
        sb2.append(resources2.getString(R.string.subject_label));
        if (!TextUtils.isEmpty(C1029y.m2438b(resources2, sVar.mo6554mg()))) {
            sb2.append(sVar.mo6554mg());
        }
        sb2.append(10);
        sb2.append(resources2.getString(R.string.priority_label));
        int Gg = sVar.mo6531Gg();
        if (Gg == 128) {
            str = resources2.getString(R.string.priority_low);
        } else if (Gg != 130) {
            str = resources2.getString(R.string.priority_normal);
        } else {
            str = resources2.getString(R.string.priority_high);
        }
        sb2.append(str);
        if (sVar.mo6530Fg() > 0) {
            sb2.append(10);
            sb2.append(resources2.getString(R.string.message_size_label));
            sb2.append(Formatter.formatFileSize(context, (long) sVar.mo6530Fg()));
        }
        m3042a(resources2, participantData, sb2);
        C1410N.m3547Nj();
        return sb2.toString();
    }

    /* renamed from: p */
    private static void m3045p(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            new AlertDialog.Builder(context, R.style.BugleThemeDialog).setTitle(R.string.message_details_title).setMessage(str).setCancelable(true).show();
        }
    }

    /* renamed from: a */
    private static void m3043a(Resources resources, StringBuilder sb, C0936s sVar) {
        int i;
        if (sVar.mo6546gg()) {
            i = R.string.received_label;
        } else {
            i = sVar.mo6550jg() ? R.string.sent_label : -1;
        }
        if (i >= 0) {
            sb.append(10);
            sb.append(resources.getString(i));
            sb.append(C1485y.m3837D(sVar.mo6558rg()).toString());
        }
    }

    /* renamed from: a */
    private static String m3040a(C0938u uVar, String str, boolean z, String str2) {
        StringBuilder sb = new StringBuilder();
        Iterator it = uVar.iterator();
        while (it.hasNext()) {
            ParticipantData participantData = (ParticipantData) it.next();
            if (!TextUtils.equals(participantData.getId(), str) && (!participantData.mo6362zh() || (participantData.getId().equals(str2) && z))) {
                String sf = participantData.mo6353sf();
                if (!TextUtils.isEmpty(sf)) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(sf);
                }
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static void m3042a(Resources resources, ParticipantData participantData, StringBuilder sb) {
        if (C1464na.m3759Zj() && participantData != null && C1474sa.getDefault().mo8205bk() >= 2) {
            C1424b.m3592ia(participantData.mo6362zh());
            sb.append(10);
            sb.append(resources.getString(R.string.sim_label));
            if (participantData.mo6358wh() && !participantData.mo6361yh()) {
                String uh = participantData.mo6357uh();
                if (TextUtils.isEmpty(uh)) {
                    sb.append(resources.getString(R.string.sim_slot_identifier, new Object[]{Integer.valueOf(participantData.mo6346nh())}));
                    return;
                }
                sb.append(uh);
            }
        }
    }
}
