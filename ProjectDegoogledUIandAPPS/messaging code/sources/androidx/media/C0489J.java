package androidx.media;

import android.os.Bundle;
import android.support.p016v4.p017os.ResultReceiver;
import android.text.TextUtils;

/* renamed from: androidx.media.J */
class C0489J {
    final /* synthetic */ MediaBrowserServiceCompat this$0;

    C0489J(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
        this.this$0 = mediaBrowserServiceCompat;
    }

    /* renamed from: a */
    public void mo4553a(String str, ResultReceiver resultReceiver, C0490K k) {
        if (!TextUtils.isEmpty(str) && resultReceiver != null) {
            this.this$0.mHandler.mo4559b(new C0484E(this, k, str, resultReceiver));
        }
    }

    /* renamed from: b */
    public void mo4554b(String str, Bundle bundle, ResultReceiver resultReceiver, C0490K k) {
        if (!TextUtils.isEmpty(str) && resultReceiver != null) {
            this.this$0.mHandler.mo4559b(new C0488I(this, k, str, bundle, resultReceiver));
        }
    }

    /* renamed from: a */
    public void mo4552a(String str, Bundle bundle, ResultReceiver resultReceiver, C0490K k) {
        if (!TextUtils.isEmpty(str) && resultReceiver != null) {
            this.this$0.mHandler.mo4559b(new C0487H(this, k, str, bundle, resultReceiver));
        }
    }
}
