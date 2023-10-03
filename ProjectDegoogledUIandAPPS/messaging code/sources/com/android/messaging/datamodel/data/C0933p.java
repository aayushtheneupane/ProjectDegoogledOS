package com.android.messaging.datamodel.data;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.android.messaging.datamodel.C0837b;
import com.android.messaging.datamodel.C0944e;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.p037a.C0781a;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.receiver.SmsReceiver;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import java.util.HashSet;

/* renamed from: com.android.messaging.datamodel.data.p */
public class C0933p extends C0781a implements LoaderManager.LoaderCallbacks {

    /* renamed from: Ez */
    private static final String[] f1246Ez = {"_id", "normalized_destination"};

    /* renamed from: Cz */
    private final boolean f1247Cz;

    /* renamed from: Dz */
    private final HashSet f1248Dz = new HashSet();
    private Bundle mArgs;
    private final Context mContext;
    private C0932o mListener;
    private LoaderManager mLoaderManager;

    public C0933p(Context context, C0932o oVar, boolean z) {
        this.mListener = oVar;
        this.mContext = context;
        this.f1247Cz = z;
    }

    /* renamed from: K */
    public void mo6485K(boolean z) {
        C0947h.get().mo6586I(z);
        if (z) {
            C0944e.m2078Ud();
            SmsReceiver.m2319Na();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: Te */
    public void mo5924Te() {
        this.mListener = null;
        LoaderManager loaderManager = this.mLoaderManager;
        if (loaderManager != null) {
            loaderManager.destroyLoader(1);
            this.mLoaderManager.destroyLoader(2);
            this.mLoaderManager = null;
        }
    }

    /* renamed from: a */
    public void mo6486a(LoaderManager loaderManager, C0784d dVar) {
        this.mArgs = new Bundle();
        this.mArgs.putString("bindingId", dVar.getBindingId());
        this.mLoaderManager = loaderManager;
        this.mLoaderManager.initLoader(1, this.mArgs, this);
        this.mLoaderManager.initLoader(2, this.mArgs, this);
    }

    /* renamed from: df */
    public HashSet mo6487df() {
        return this.f1248Dz;
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        String string = bundle.getString("bindingId");
        if (!mo5926W(string)) {
            C1430e.m3630w("MessagingAppDataModel", "Creating loader after unbinding list");
        } else if (i == 1) {
            return new C0837b(string, this.mContext, MessagingContentProvider.f1020Bb, C0934q.f1249Wu, this.f1247Cz ? "(archive_status = 1)" : "(archive_status = 0)", (String[]) null, "sort_timestamp DESC");
        } else if (i == 2) {
            return new C0837b(string, this.mContext, MessagingContentProvider.f1024Fb, f1246Ez, "blocked=1", (String[]) null, (String) null);
        } else {
            C1424b.fail("Unknown loader id");
        }
        return null;
    }

    public void onLoadFinished(Loader loader, Object obj) {
        Cursor cursor = (Cursor) obj;
        C0837b bVar = (C0837b) loader;
        if (mo5926W(bVar.getBindingId())) {
            int id = bVar.getId();
            if (id == 1) {
                this.mListener.mo6483a(this, cursor);
            } else if (id != 2) {
                C1424b.fail("Unknown loader id");
            } else {
                this.f1248Dz.clear();
                boolean z = false;
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    this.f1248Dz.add(cursor.getString(1));
                }
                C0932o oVar = this.mListener;
                if (cursor.getCount() > 0) {
                    z = true;
                }
                oVar.mo6484e(z);
            }
        } else {
            C1430e.m3630w("MessagingAppDataModel", "Loader finished after unbinding list");
        }
    }

    public void onLoaderReset(Loader loader) {
        C0837b bVar = (C0837b) loader;
        if (mo5926W(bVar.getBindingId())) {
            int id = bVar.getId();
            if (id == 1) {
                this.mListener.mo6483a(this, (Cursor) null);
            } else if (id != 2) {
                C1424b.fail("Unknown loader id");
            } else {
                this.mListener.mo6484e(false);
            }
        } else {
            C1430e.m3630w("MessagingAppDataModel", "Loader reset after unbinding list");
        }
    }

    /* renamed from: re */
    public boolean mo6491re() {
        return C0947h.get().mo6606be().mo5920re();
    }
}
