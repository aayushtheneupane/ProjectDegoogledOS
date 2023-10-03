package com.android.messaging.datamodel.data;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.android.messaging.datamodel.C0837b;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.util.C1424b;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.data.l */
class C0929l implements LoaderManager.LoaderCallbacks {
    final /* synthetic */ C0931n this$0;

    /* synthetic */ C0929l(C0931n nVar, C0923f fVar) {
        this.this$0 = nVar;
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        C1424b.equals(4, i);
        String string = bundle.getString("bindingId");
        if (this.this$0.mo5926W(string)) {
            return new C0837b(string, this.this$0.mContext, MessagingContentProvider.f1024Fb, C0901M.f1157Wu, "sub_id <> ?", new String[]{String.valueOf(-2)}, (String) null);
        }
        C0632a.m1021a(C0632a.m1011Pa("Creating self loader after unbinding mConversationId = "), this.this$0.f1236Ka, "bugle_datamodel");
        return null;
    }

    public void onLoadFinished(Loader loader, Object obj) {
        Cursor cursor = (Cursor) obj;
        if (this.this$0.mo5926W(((C0837b) loader).getBindingId())) {
            this.this$0.f1243xz.mo6387c(cursor);
            this.this$0.f1245zz.mo6421j(this.this$0.f1243xz.mo6384Q(true));
            this.this$0.mListeners.mo6219c(this.this$0);
            return;
        }
        C0632a.m1021a(C0632a.m1011Pa("Self loader finished after unbinding mConversationId = "), this.this$0.f1236Ka, "bugle_datamodel");
    }

    public void onLoaderReset(Loader loader) {
        if (this.this$0.mo5926W(((C0837b) loader).getBindingId())) {
            this.this$0.f1243xz.mo6387c((Cursor) null);
        } else {
            C0632a.m1021a(C0632a.m1011Pa("Self loader reset after unbinding mConversationId = "), this.this$0.f1236Ka, "bugle_datamodel");
        }
    }
}
