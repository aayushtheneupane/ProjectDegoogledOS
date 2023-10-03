package com.android.messaging.datamodel.data;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.android.messaging.datamodel.C0837b;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.p037a.C0781a;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.datamodel.data.b */
public class C0916b extends C0781a implements LoaderManager.LoaderCallbacks {
    private final Context mContext;
    private C0914a mListener;
    private LoaderManager mLoaderManager;

    public C0916b(Context context, C0914a aVar) {
        this.mContext = context;
        this.mListener = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Te */
    public void mo5924Te() {
        this.mListener = null;
        LoaderManager loaderManager = this.mLoaderManager;
        if (loaderManager != null) {
            loaderManager.destroyLoader(1);
            this.mLoaderManager = null;
        }
    }

    /* renamed from: a */
    public void mo6400a(LoaderManager loaderManager, C0784d dVar) {
        Bundle bundle = new Bundle();
        bundle.putString("bindingId", dVar.getBindingId());
        this.mLoaderManager = loaderManager;
        this.mLoaderManager.initLoader(1, bundle, this);
    }

    /* renamed from: j */
    public C0902N mo6401j(Cursor cursor) {
        return new C0902N(ParticipantData.m1839k(cursor));
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        C1424b.m3592ia(z);
        String string = bundle.getString("bindingId");
        if (!mo5926W(string)) {
            return null;
        }
        return new C0837b(string, this.mContext, MessagingContentProvider.f1024Fb, C0901M.f1157Wu, "blocked=1", (String[]) null, (String) null);
    }

    public void onLoadFinished(Loader loader, Object obj) {
        Cursor cursor = (Cursor) obj;
        boolean z = true;
        if (loader.getId() != 1) {
            z = false;
        }
        C1424b.m3592ia(z);
        C1424b.m3592ia(mo5926W(((C0837b) loader).getBindingId()));
        this.mListener.mo6394b(cursor);
    }

    public void onLoaderReset(Loader loader) {
        boolean z = true;
        if (loader.getId() != 1) {
            z = false;
        }
        C1424b.m3592ia(z);
        C1424b.m3592ia(mo5926W(((C0837b) loader).getBindingId()));
        this.mListener.mo6394b((Cursor) null);
    }
}
