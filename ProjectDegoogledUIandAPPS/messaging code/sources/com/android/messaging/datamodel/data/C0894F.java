package com.android.messaging.datamodel.data;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.android.messaging.datamodel.C0837b;
import com.android.messaging.datamodel.C0965z;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.datamodel.data.F */
class C0894F implements LoaderManager.LoaderCallbacks {
    final /* synthetic */ C0896H this$0;

    /* synthetic */ C0894F(C0896H h, C0893E e) {
        this.this$0 = h;
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        String string = bundle.getString("bindingId");
        if (!this.this$0.mo5926W(string)) {
            C1430e.m3630w("MessagingApp", "Loader created after unbinding the media picker");
            return null;
        } else if (i == 1) {
            return new C0965z(string, this.this$0.mContext);
        } else {
            C1424b.fail("Unknown loader id for gallery picker!");
            return null;
        }
    }

    public void onLoadFinished(Loader loader, Object obj) {
        Cursor cursor = (Cursor) obj;
        if (!this.this$0.mo5926W(((C0837b) loader).getBindingId())) {
            C1430e.m3630w("MessagingApp", "Loader finished after unbinding the media picker");
        } else if (loader.getId() != 1) {
            C1424b.fail("Unknown loader id for gallery picker!");
        } else {
            this.this$0.mListener.mo6227a(this.this$0, cursor, 1);
        }
    }

    public void onLoaderReset(Loader loader) {
        if (!this.this$0.mo5926W(((C0837b) loader).getBindingId())) {
            C1430e.m3630w("MessagingApp", "Loader reset after unbinding the media picker");
        } else if (loader.getId() != 1) {
            C1424b.fail("Unknown loader id for media picker!");
        } else {
            this.this$0.mListener.mo6227a(this.this$0, (Object) null, 1);
        }
    }
}
