package com.android.dialer.enrichedcall.stub;

import com.android.dialer.enrichedcall.EnrichedCallManager;
import com.android.dialer.enrichedcall.RcsVideoShareFactory;
import com.android.incallui.videotech.VideoTech;
import com.android.incallui.videotech.empty.EmptyVideoTech;

/* renamed from: com.android.dialer.enrichedcall.stub.-$$Lambda$StubEnrichedCallModule$wrr8NfG-RhVMWOxc7hQlZBxKXT8  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$StubEnrichedCallModule$wrr8NfGRhVMWOxc7hQlZBxKXT8 implements RcsVideoShareFactory {
    public static final /* synthetic */ $$Lambda$StubEnrichedCallModule$wrr8NfGRhVMWOxc7hQlZBxKXT8 INSTANCE = new $$Lambda$StubEnrichedCallModule$wrr8NfGRhVMWOxc7hQlZBxKXT8();

    private /* synthetic */ $$Lambda$StubEnrichedCallModule$wrr8NfGRhVMWOxc7hQlZBxKXT8() {
    }

    public final VideoTech newRcsVideoShare(EnrichedCallManager enrichedCallManager, VideoTech.VideoTechListener videoTechListener, String str) {
        return new EmptyVideoTech();
    }
}
