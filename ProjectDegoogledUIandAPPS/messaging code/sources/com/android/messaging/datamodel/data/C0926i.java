package com.android.messaging.datamodel.data;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.C0837b;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.util.C1424b;
import com.android.messaging.widget.WidgetConversationProvider;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.data.i */
class C0926i implements LoaderManager.LoaderCallbacks {
    final /* synthetic */ C0931n this$0;

    /* synthetic */ C0926i(C0931n nVar, C0923f fVar) {
        this.this$0 = nVar;
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        C1424b.equals(1, i);
        String string = bundle.getString("bindingId");
        if (this.this$0.mo5926W(string)) {
            return new C0837b(string, this.this$0.mContext, MessagingContentProvider.m1269n(this.this$0.f1236Ka), C0934q.f1249Wu, (String) null, (String[]) null, (String) null);
        }
        C0632a.m1021a(C0632a.m1011Pa("Creating messages loader after unbinding mConversationId = "), this.this$0.f1236Ka, "bugle_datamodel");
        return null;
    }

    public void onLoadFinished(Loader loader, Object obj) {
        Cursor cursor = (Cursor) obj;
        if (!this.this$0.mo5926W(((C0837b) loader).getBindingId())) {
            C0632a.m1021a(C0632a.m1011Pa("Meta data loader finished after unbinding mConversationId = "), this.this$0.f1236Ka, "bugle_datamodel");
        } else if (cursor.moveToNext()) {
            boolean z = true;
            if (cursor.getCount() != 1) {
                z = false;
            }
            C1424b.m3592ia(z);
            this.this$0.f1244yz.mo6513c(cursor);
            this.this$0.mListeners.mo6215a(this.this$0);
        } else {
            C0632a.m1021a(C0632a.m1011Pa("Meta data loader returned nothing for mConversationId = "), this.this$0.f1236Ka, "bugle_datamodel");
            this.this$0.mListeners.mo6218b(this.this$0.f1236Ka);
            WidgetConversationProvider.m3886a(C0967f.get().getApplicationContext(), this.this$0.f1236Ka);
        }
    }

    public void onLoaderReset(Loader loader) {
        if (this.this$0.mo5926W(((C0837b) loader).getBindingId())) {
            C0934q unused = this.this$0.f1244yz = new C0934q();
            this.this$0.mListeners.mo6215a(this.this$0);
            return;
        }
        C0632a.m1021a(C0632a.m1011Pa("Meta data loader reset after unbinding mConversationId = "), this.this$0.f1236Ka, "bugle_datamodel");
    }
}
