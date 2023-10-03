package com.android.messaging.datamodel.data;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.android.messaging.datamodel.C0837b;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.action.C0819i;
import com.android.messaging.datamodel.action.UpdateDestinationBlockedAction;
import com.android.messaging.datamodel.p037a.C0781a;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.data.S */
public class C0906S extends C0781a implements LoaderManager.LoaderCallbacks {

    /* renamed from: Ka */
    private final String f1206Ka;
    private final Context mContext;
    private C0905Q mListener;
    private LoaderManager mLoaderManager;

    /* renamed from: wz */
    private final C0938u f1207wz = new C0938u();

    public C0906S(String str, Context context, C0905Q q) {
        this.mListener = q;
        this.mContext = context;
        this.f1206Ka = str;
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

    /* renamed from: Ue */
    public String mo6367Ue() {
        return this.f1206Ka;
    }

    /* renamed from: a */
    public void mo6368a(LoaderManager loaderManager, C0784d dVar) {
        Bundle bundle = new Bundle();
        bundle.putString("bindingId", dVar.getBindingId());
        this.mLoaderManager = loaderManager;
        this.mLoaderManager.initLoader(1, bundle, this);
        this.mLoaderManager.initLoader(2, bundle, this);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        String string = bundle.getString("bindingId");
        if (!mo5926W(string)) {
            C1430e.m3630w("MessagingApp", "Loader created after unbinding PeopleAndOptionsFragment");
            return null;
        } else if (i == 1) {
            return new C0837b(string, this.mContext, MessagingContentProvider.m1269n(this.f1206Ka), new String[0], (String) null, (String[]) null, (String) null);
        } else if (i != 2) {
            C1424b.fail("Unknown loader id for PeopleAndOptionsFragment!");
            return null;
        } else {
            return new C0837b(string, this.mContext, MessagingContentProvider.m1270o(this.f1206Ka), C0901M.f1157Wu, (String) null, (String[]) null, (String) null);
        }
    }

    public void onLoadFinished(Loader loader, Object obj) {
        Cursor cursor = (Cursor) obj;
        if (mo5926W(((C0837b) loader).getBindingId())) {
            int id = loader.getId();
            if (id == 1) {
                this.mListener.mo6365a(this, cursor);
            } else if (id != 2) {
                C1424b.fail("Unknown loader id for PeopleAndOptionsFragment!");
            } else {
                this.f1207wz.mo6574c(cursor);
                this.mListener.mo6366a(this, (List) this.f1207wz.mo6571Jg());
            }
        } else {
            C1430e.m3630w("MessagingApp", "Loader finished after unbinding PeopleAndOptionsFragment");
        }
    }

    public void onLoaderReset(Loader loader) {
        if (mo5926W(((C0837b) loader).getBindingId())) {
            int id = loader.getId();
            if (id == 1) {
                this.mListener.mo6365a(this, (Cursor) null);
            } else if (id != 2) {
                C1424b.fail("Unknown loader id for PeopleAndOptionsFragment!");
            } else {
                this.f1207wz.mo6574c((Cursor) null);
            }
        } else {
            C1430e.m3630w("MessagingApp", "Loader reset after unbinding PeopleAndOptionsFragment");
        }
    }

    /* renamed from: a */
    public void mo6369a(C0784d dVar, boolean z) {
        String bindingId = dVar.getBindingId();
        ParticipantData Ye = this.f1207wz.mo6573Ye();
        if (mo5926W(bindingId) && Ye != null) {
            UpdateDestinationBlockedAction.m1458a(Ye.mo6353sf(), z, this.f1206Ka, C0819i.m1495k(this.mContext));
        }
    }
}
