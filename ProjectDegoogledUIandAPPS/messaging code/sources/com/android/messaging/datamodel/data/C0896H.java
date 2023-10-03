package com.android.messaging.datamodel.data;

import android.app.LoaderManager;
import android.content.Context;
import android.os.Bundle;
import com.android.messaging.datamodel.p037a.C0781a;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1451h;

/* renamed from: com.android.messaging.datamodel.data.H */
public class C0896H extends C0781a {

    /* renamed from: Qz */
    private final C0894F f1156Qz = new C0894F(this, (C0893E) null);
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public C0895G mListener;
    private LoaderManager mLoaderManager;

    public C0896H(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Te */
    public void mo5924Te() {
        LoaderManager loaderManager = this.mLoaderManager;
        if (loaderManager != null) {
            loaderManager.destroyLoader(1);
            this.mLoaderManager = null;
        }
    }

    public void destroyLoader(int i) {
        this.mLoaderManager.destroyLoader(i);
    }

    /* renamed from: na */
    public void mo6231na(int i) {
        C1451h.m3724Hd().putInt("selected_media_picker_chooser_index", i);
    }

    /* renamed from: qf */
    public int mo6232qf() {
        return C1451h.m3724Hd().getInt("selected_media_picker_chooser_index", -1);
    }

    /* renamed from: a */
    public void mo6228a(int i, C0784d dVar, Bundle bundle, C0895G g) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("bindingId", dVar.getBindingId());
        if (i == 1) {
            this.mLoaderManager.initLoader(i, bundle, this.f1156Qz).forceLoad();
        } else {
            C1424b.fail("Unsupported loader id for media picker!");
        }
        this.mListener = g;
    }

    /* renamed from: a */
    public void mo6229a(LoaderManager loaderManager) {
        this.mLoaderManager = loaderManager;
    }
}
