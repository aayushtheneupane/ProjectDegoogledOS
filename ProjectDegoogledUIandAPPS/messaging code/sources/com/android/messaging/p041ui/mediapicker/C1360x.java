package com.android.messaging.p041ui.mediapicker;

import android.graphics.Rect;
import android.net.Uri;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.MediaPickerMessagePartData;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.mediapicker.x */
class C1360x {
    final /* synthetic */ C1362y this$1;

    C1360x(C1362y yVar) {
        this.this$1 = yVar;
    }

    /* renamed from: La */
    public void mo7967La(int i) {
        if (i == 2) {
            C1486ya.m3848Pa(R.string.camera_media_failure);
        }
        this.this$1.this$0.m3213po();
    }

    /* renamed from: a */
    public void mo7968a(Uri uri, String str, int i, int i2) {
        this.this$1.this$0.f2007xF.stop();
        if (this.this$1.this$0.f2008yF || uri == null) {
            boolean unused = this.this$1.this$0.f2008yF = false;
        } else {
            Rect rect = new Rect();
            if (this.this$1.this$0.mView != null) {
                this.this$1.this$0.mView.getGlobalVisibleRect(rect);
            }
            this.this$1.this$0.f2118Dj.mo7896a((MessagePartData) new MediaPickerMessagePartData(rect, str, uri, i, i2), true);
        }
        this.this$1.this$0.m3213po();
    }

    /* renamed from: b */
    public void mo7969b(Exception exc) {
        C1486ya.m3848Pa(R.string.camera_media_failure);
        this.this$1.this$0.m3213po();
    }
}
