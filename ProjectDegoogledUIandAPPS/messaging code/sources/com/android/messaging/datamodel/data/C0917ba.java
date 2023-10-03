package com.android.messaging.datamodel.data;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.android.messaging.R;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1426c;
import java.util.Locale;

/* renamed from: com.android.messaging.datamodel.data.ba */
public class C0917ba {
    public final String displayName;

    /* renamed from: lC */
    public final String f1214lC;

    /* renamed from: mC */
    public final Uri f1215mC;

    /* renamed from: nC */
    public final Uri f1216nC;

    /* renamed from: oC */
    public final int f1217oC;

    /* renamed from: pC */
    public final String f1218pC;

    private C0917ba(String str, Uri uri, Uri uri2, String str2, int i, String str3) {
        this.f1214lC = str;
        this.f1215mC = uri;
        this.f1216nC = uri2;
        this.displayName = str2;
        this.f1217oC = i;
        this.f1218pC = str3;
    }

    /* renamed from: a */
    static C0917ba m1908a(ParticipantData participantData, Context context) {
        C1424b.m3592ia(participantData.mo6362zh());
        C1424b.m3592ia(participantData.mo6358wh());
        int nh = participantData.mo6346nh();
        String format = String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(nh)});
        String uh = participantData.mo6357uh();
        if (TextUtils.isEmpty(uh)) {
            uh = context.getString(R.string.sim_slot_identifier, new Object[]{Integer.valueOf(nh)});
        }
        return new C0917ba(participantData.getId(), C1426c.m3599a(participantData, format, false, false), C1426c.m3599a(participantData, format, true, false), uh, participantData.mo6355th(), participantData.mo6344mh());
    }
}
