package com.android.dialer.calllogutils;

import android.content.res.Resources;
import com.android.dialer.R;
import com.android.dialer.duo.Duo;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.searchfragment.list.NewSearchFragment;

public class CallTypeHelper {
    private final CharSequence answeredElsewhereName;
    private final CharSequence blockedName;
    private final CharSequence incomingDuoCall = this.incomingVideoName;
    private final CharSequence incomingName;
    private final CharSequence incomingPulledName;
    private final CharSequence incomingVideoName;
    private final CharSequence incomingVideoPulledName;
    private final CharSequence missedName;
    private final CharSequence missedVideoName;
    private final CharSequence outgoingDuoCall = this.outgoingVideoName;
    private final CharSequence outgoingName;
    private final CharSequence outgoingPulledName;
    private final CharSequence outgoingVideoName;
    private final CharSequence outgoingVideoPulledName;
    private final CharSequence rejectedName;
    private final CharSequence voicemailName;

    public CallTypeHelper(Resources resources, Duo duo) {
        this.incomingName = resources.getString(R.string.type_incoming);
        this.incomingPulledName = resources.getString(R.string.type_incoming_pulled);
        this.outgoingName = resources.getString(R.string.type_outgoing);
        this.outgoingPulledName = resources.getString(R.string.type_outgoing_pulled);
        this.missedName = resources.getString(R.string.type_missed);
        this.incomingVideoName = resources.getString(R.string.type_incoming_video);
        this.incomingVideoPulledName = resources.getString(R.string.type_incoming_video_pulled);
        this.outgoingVideoName = resources.getString(R.string.type_outgoing_video);
        this.outgoingVideoPulledName = resources.getString(R.string.type_outgoing_video_pulled);
        this.missedVideoName = resources.getString(R.string.type_missed_video);
        this.voicemailName = resources.getString(R.string.type_voicemail);
        this.rejectedName = resources.getString(R.string.type_rejected);
        this.blockedName = resources.getString(R.string.type_blocked);
        this.answeredElsewhereName = resources.getString(R.string.type_answered_elsewhere);
        ((DuoStub) duo).getIncomingCallTypeText();
    }

    public CharSequence getCallTypeText(int i, boolean z, boolean z2, boolean z3) {
        switch (i) {
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                if (z) {
                    if (z2) {
                        return this.incomingVideoPulledName;
                    }
                    if (z3) {
                        return this.incomingDuoCall;
                    }
                    return this.incomingVideoName;
                } else if (z2) {
                    return this.incomingPulledName;
                } else {
                    return this.incomingName;
                }
            case 2:
                if (z) {
                    if (z2) {
                        return this.outgoingVideoPulledName;
                    }
                    if (z3) {
                        return this.outgoingDuoCall;
                    }
                    return this.outgoingVideoName;
                } else if (z2) {
                    return this.outgoingPulledName;
                } else {
                    return this.outgoingName;
                }
            case 3:
                if (z) {
                    return this.missedVideoName;
                }
                return this.missedName;
            case 4:
                return this.voicemailName;
            case 5:
                return this.rejectedName;
            case 6:
                return this.blockedName;
            case 7:
                return this.answeredElsewhereName;
            default:
                return this.missedName;
        }
    }
}
