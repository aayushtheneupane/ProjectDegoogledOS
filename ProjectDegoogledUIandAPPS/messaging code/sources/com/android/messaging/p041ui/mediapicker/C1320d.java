package com.android.messaging.p041ui.mediapicker;

import com.android.messaging.sms.C1024t;
import com.android.messaging.util.C1448fa;

/* renamed from: com.android.messaging.ui.mediapicker.d */
class C1320d implements C1448fa {
    final /* synthetic */ AudioRecordView this$0;

    C1320d(AudioRecordView audioRecordView) {
        this.this$0 = audioRecordView;
    }

    /* renamed from: ba */
    public void mo7850ba() {
        int maxMessageSize = C1024t.get(this.this$0.mHostInterface.mo6582H()).getMaxMessageSize();
        if (this.this$0.f1996dd == 2) {
            C1303ca c = this.this$0.f1994Hg;
            AudioRecordView audioRecordView = this.this$0;
            if (c.mo7776a(audioRecordView, audioRecordView, maxMessageSize)) {
                this.this$0.setMode(3);
            }
        }
    }
}
