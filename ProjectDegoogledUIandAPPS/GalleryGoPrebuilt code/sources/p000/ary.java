package p000;

import android.content.res.AssetManager;
import java.io.InputStream;

/* renamed from: ary */
/* compiled from: PG */
public final class ary extends arf {
    public ary(AssetManager assetManager, String str) {
        super(assetManager, str);
    }

    /* renamed from: a */
    public final Class mo1510a() {
        return InputStream.class;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1515a(Object obj) {
        ((InputStream) obj).close();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo1513a(AssetManager assetManager, String str) {
        return assetManager.open(str);
    }
}
