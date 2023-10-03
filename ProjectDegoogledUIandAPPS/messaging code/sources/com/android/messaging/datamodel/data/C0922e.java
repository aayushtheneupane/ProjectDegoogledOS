package com.android.messaging.datamodel.data;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.android.messaging.datamodel.C0837b;
import com.android.messaging.datamodel.C0957r;
import com.android.messaging.datamodel.p037a.C0781a;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.sms.C1024t;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.datamodel.data.e */
public class C0922e extends C0781a implements LoaderManager.LoaderCallbacks {
    private final Context mContext;
    private C0920d mListener;
    private LoaderManager mLoaderManager;

    /* renamed from: rz */
    private final C0957r f1231rz = new C0957r();

    public C0922e(Context context, C0920d dVar) {
        this.mListener = dVar;
        this.mContext = context;
    }

    /* renamed from: ka */
    public static boolean m1931ka(int i) {
        return i < C1024t.get(-1).mo6848ti();
    }

    /* renamed from: la */
    public static boolean m1932la(int i) {
        return i > C1024t.get(-1).mo6848ti();
    }

    /* access modifiers changed from: protected */
    /* renamed from: Te */
    public void mo5924Te() {
        this.mListener = null;
        LoaderManager loaderManager = this.mLoaderManager;
        if (loaderManager != null) {
            loaderManager.destroyLoader(1);
            this.mLoaderManager.destroyLoader(2);
            this.mLoaderManager.destroyLoader(3);
            this.mLoaderManager = null;
        }
        this.f1231rz.mo6639ee();
    }

    /* renamed from: a */
    public void mo6429a(LoaderManager loaderManager, C0784d dVar) {
        Bundle bundle = new Bundle();
        bundle.putString("bindingId", dVar.getBindingId());
        this.mLoaderManager = loaderManager;
        this.mLoaderManager.initLoader(1, bundle, this);
        this.mLoaderManager.initLoader(2, bundle, this);
        this.mLoaderManager.initLoader(3, bundle, this);
    }

    /* JADX WARNING: type inference failed for: r0v10, types: [com.android.messaging.datamodel.g] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.content.Loader onCreateLoader(int r18, android.os.Bundle r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            java.lang.String r2 = "bindingId"
            r3 = r19
            java.lang.String r4 = r3.getString(r2)
            boolean r2 = r0.mo5926W(r4)
            if (r2 == 0) goto L_0x005a
            r2 = 1
            if (r1 == r2) goto L_0x004f
            r2 = 2
            if (r1 == r2) goto L_0x0031
            r2 = 3
            if (r1 == r2) goto L_0x0021
            java.lang.String r0 = "Unknown loader id for contact picker!"
            com.android.messaging.util.C1424b.fail(r0)
            goto L_0x0061
        L_0x0021:
            com.android.messaging.datamodel.b r1 = new com.android.messaging.datamodel.b
            android.content.Context r5 = r0.mContext
            android.net.Uri r6 = com.android.messaging.datamodel.MessagingContentProvider.f1024Fb
            java.lang.String[] r7 = com.android.messaging.datamodel.data.C0901M.f1157Wu
            r8 = 0
            r9 = 0
            r10 = 0
            r3 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            return r1
        L_0x0031:
            android.content.Context r12 = r0.mContext
            boolean r0 = com.android.messaging.util.ContactUtil.m3525Kj()
            if (r0 != 0) goto L_0x003e
            com.android.messaging.datamodel.g r0 = com.android.messaging.datamodel.C0946g.m2098Wd()
            goto L_0x004a
        L_0x003e:
            com.android.messaging.datamodel.y r0 = new com.android.messaging.datamodel.y
            java.lang.String[] r13 = com.android.messaging.util.C1471r.f2337Wu
            r14 = 0
            r15 = 0
            r16 = 0
            r11 = r0
            r11.<init>(r12, r13, r14, r15, r16)
        L_0x004a:
            com.android.messaging.datamodel.b r0 = r0.mo6583J(r4)
            return r0
        L_0x004f:
            android.content.Context r0 = r0.mContext
            com.android.messaging.datamodel.g r0 = com.android.messaging.util.ContactUtil.getPhones(r0)
            com.android.messaging.datamodel.b r0 = r0.mo6583J(r4)
            return r0
        L_0x005a:
            java.lang.String r0 = "MessagingApp"
            java.lang.String r1 = "Loader created after unbinding the contacts list"
            com.android.messaging.util.C1430e.m3630w(r0, r1)
        L_0x0061:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.data.C0922e.onCreateLoader(int, android.os.Bundle):android.content.Loader");
    }

    public void onLoadFinished(Loader loader, Object obj) {
        Cursor build;
        Cursor cursor = (Cursor) obj;
        if (mo5926W(((C0837b) loader).getBindingId())) {
            int id = loader.getId();
            if (id == 1) {
                this.mListener.onAllContactsCursorUpdated(cursor);
                this.f1231rz.mo6640g(cursor);
            } else if (id == 2) {
                this.f1231rz.mo6641h(cursor);
            } else if (id != 3) {
                C1424b.fail("Unknown loader id for contact picker!");
            } else {
                this.mListener.onContactCustomColorLoaded(this);
            }
            if (loader.getId() != 3 && (build = this.f1231rz.build()) != null) {
                this.mListener.onFrequentContactsCursorUpdated(build);
                return;
            }
            return;
        }
        C1430e.m3630w("MessagingApp", "Loader finished after unbinding the contacts list");
    }

    public void onLoaderReset(Loader loader) {
        if (mo5926W(((C0837b) loader).getBindingId())) {
            int id = loader.getId();
            if (id == 1) {
                this.mListener.onAllContactsCursorUpdated((Cursor) null);
                this.f1231rz.mo6640g((Cursor) null);
            } else if (id == 2) {
                this.mListener.onFrequentContactsCursorUpdated((Cursor) null);
                this.f1231rz.mo6641h((Cursor) null);
            } else if (id != 3) {
                C1424b.fail("Unknown loader id for contact picker!");
            } else {
                this.mListener.onContactCustomColorLoaded(this);
            }
        } else {
            C1430e.m3630w("MessagingApp", "Loader reset after unbinding the contacts list");
        }
    }
}
