package com.android.messaging.datamodel.data;

import android.content.Context;
import android.text.TextUtils;
import com.android.messaging.R;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.datamodel.data.Z */
public class C0913Z {

    /* renamed from: jC */
    private final String f1210jC;

    /* renamed from: kC */
    private final String f1211kC;
    private final int mSubId;
    private final int mType;

    /* renamed from: yv */
    private final String f1212yv;

    private C0913Z(String str, String str2, String str3, int i, int i2) {
        this.f1212yv = str;
        this.f1210jC = str2;
        this.f1211kC = str3;
        this.mType = i;
        this.mSubId = i2;
    }

    /* renamed from: a */
    public static C0913Z m1896a(Context context, ParticipantData participantData) {
        String str;
        C1424b.m3592ia(participantData.mo6362zh());
        C1424b.m3592ia(participantData.mo6358wh());
        if (TextUtils.isEmpty(participantData.mo6344mh())) {
            str = context.getString(R.string.sim_settings_unknown_number);
        } else {
            str = participantData.mo6344mh();
        }
        String string = context.getString(R.string.sim_specific_settings, new Object[]{participantData.mo6357uh()});
        return new C0913Z(string, str, string, 2, participantData.getSubId());
    }

    /* renamed from: c */
    public static C0913Z m1897c(Context context, int i) {
        return new C0913Z(context.getString(R.string.advanced_settings), (String) null, context.getString(R.string.advanced_settings_activity_title), 2, i);
    }

    /* renamed from: m */
    public static C0913Z m1898m(Context context) {
        return new C0913Z(context.getString(R.string.general_settings), (String) null, context.getString(R.string.general_settings_activity_title), 1, -1);
    }

    /* renamed from: Ch */
    public String mo6389Ch() {
        return this.f1211kC;
    }

    /* renamed from: Dh */
    public String mo6390Dh() {
        return this.f1210jC;
    }

    public String getDisplayName() {
        return this.f1212yv;
    }

    public int getSubId() {
        return this.mSubId;
    }

    public int getType() {
        return this.mType;
    }
}
