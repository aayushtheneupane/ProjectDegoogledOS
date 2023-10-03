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
import com.android.messaging.p041ui.appsettings.C1104t;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.data.aa */
public class C0915aa extends C0781a implements LoaderManager.LoaderCallbacks {
    private final Context mContext;
    private C0912Y mListener;
    private LoaderManager mLoaderManager;

    /* renamed from: xz */
    private final C0911X f1213xz = new C0911X();

    public C0915aa(Context context, C0912Y y) {
        this.mListener = y;
        this.mContext = context;
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
    public void mo6395a(LoaderManager loaderManager, C0784d dVar) {
        Bundle bundle = new Bundle();
        bundle.putString("bindingId", dVar.getBindingId());
        this.mLoaderManager = loaderManager;
        this.mLoaderManager.initLoader(1, bundle, this);
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        C1424b.equals(1, i);
        String string = bundle.getString("bindingId");
        if (mo5926W(string)) {
            return new C0837b(string, this.mContext, MessagingContentProvider.f1024Fb, C0901M.f1157Wu, "sub_id <> ?", new String[]{String.valueOf(-2)}, (String) null);
        }
        C1430e.m3630w("MessagingApp", "Creating self loader after unbinding");
        return null;
    }

    public void onLoadFinished(Loader loader, Object obj) {
        Cursor cursor = (Cursor) obj;
        if (mo5926W(((C0837b) loader).getBindingId())) {
            this.f1213xz.mo6387c(cursor);
            ((C1104t) this.mListener).mo7188a(this);
            return;
        }
        C1430e.m3630w("MessagingApp", "Self loader finished after unbinding");
    }

    public void onLoaderReset(Loader loader) {
        if (mo5926W(((C0837b) loader).getBindingId())) {
            this.f1213xz.mo6387c((Cursor) null);
        } else {
            C1430e.m3630w("MessagingApp", "Self loader reset after unbinding");
        }
    }

    /* renamed from: xf */
    public List mo6399xf() {
        List Q = this.f1213xz.mo6384Q(true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(C0913Z.m1898m(this.mContext));
        int J = this.f1213xz.mo6383J(true);
        if (C1464na.m3759Zj() && J > 0) {
            Iterator it = Q.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ParticipantData participantData = (ParticipantData) it.next();
                if (!participantData.mo6361yh()) {
                    if (J <= 1) {
                        arrayList.add(C0913Z.m1897c(this.mContext, participantData.getSubId()));
                        break;
                    }
                    arrayList.add(C0913Z.m1896a(this.mContext, participantData));
                }
            }
        } else {
            arrayList.add(C0913Z.m1897c(this.mContext, -1));
        }
        return arrayList;
    }
}
