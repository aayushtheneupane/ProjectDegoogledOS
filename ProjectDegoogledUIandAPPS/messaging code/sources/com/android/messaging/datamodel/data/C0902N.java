package com.android.messaging.datamodel.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.android.messaging.datamodel.action.C0819i;
import com.android.messaging.datamodel.action.UpdateDestinationBlockedAction;
import com.android.messaging.util.C1426c;

/* renamed from: com.android.messaging.datamodel.data.N */
public class C0902N extends C0909V {

    /* renamed from: Jv */
    private final String f1185Jv;

    /* renamed from: Rj */
    private final long f1186Rj;

    /* renamed from: Rz */
    private final String f1187Rz;
    private final Uri mAvatarUri;
    private final String mNormalizedDestination;

    /* renamed from: yv */
    private final String f1188yv;

    public C0902N(ParticipantData participantData) {
        this.mAvatarUri = C1426c.m3601c(participantData);
        this.f1186Rj = participantData.getContactId();
        this.f1185Jv = participantData.mo6342m();
        this.mNormalizedDestination = participantData.mo6353sf();
        String str = null;
        if (TextUtils.isEmpty(participantData.mo6349ph())) {
            this.f1188yv = participantData.mo6351rh();
            this.f1187Rz = null;
            return;
        }
        this.f1188yv = participantData.mo6349ph();
        this.f1187Rz = !participantData.mo6328Ah() ? participantData.mo6351rh() : str;
    }

    /* renamed from: Mb */
    public Intent mo6127Mb() {
        return null;
    }

    public long getContactId() {
        return this.f1186Rj;
    }

    public String getDetails() {
        return this.f1187Rz;
    }

    public String getDisplayName() {
        return this.f1188yv;
    }

    /* renamed from: l */
    public void mo6322l(Context context) {
        UpdateDestinationBlockedAction.m1458a(this.mNormalizedDestination, false, (String) null, C0819i.m1495k(context));
    }

    /* renamed from: m */
    public String mo6131m() {
        return this.f1185Jv;
    }

    /* renamed from: rf */
    public Uri mo6132rf() {
        return this.mAvatarUri;
    }

    /* renamed from: sf */
    public String mo6133sf() {
        return this.mNormalizedDestination;
    }
}
