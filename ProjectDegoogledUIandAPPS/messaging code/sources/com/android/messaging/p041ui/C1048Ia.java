package com.android.messaging.p041ui;

import android.content.Context;
import android.net.Uri;
import com.android.messaging.datamodel.MediaScratchFileProvider;
import com.android.messaging.datamodel.data.C0921da;
import com.android.messaging.util.C1478ua;
import com.android.messaging.util.C1488za;

/* renamed from: com.android.messaging.ui.Ia */
class C1048Ia extends C1478ua {

    /* renamed from: Md */
    final /* synthetic */ Uri f1648Md;
    final /* synthetic */ VCardDetailFragment this$0;

    C1048Ia(VCardDetailFragment vCardDetailFragment, Uri uri) {
        this.this$0 = vCardDetailFragment;
        this.f1648Md = uri;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Object mo6323a(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        if (this.this$0.f1681Fa != null) {
            return this.this$0.f1681Fa;
        }
        return C1488za.m3864D(this.f1648Md);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        Uri uri = (Uri) obj;
        if (uri != null) {
            Uri unused = this.this$0.f1681Fa = uri;
            if (this.this$0.getActivity() != null) {
                MediaScratchFileProvider.m1256a(uri, ((C0921da) this.this$0.mBinding.getData()).getDisplayName());
                C1040Ea.get().mo6975e((Context) this.this$0.getActivity(), uri);
            }
        }
    }
}
