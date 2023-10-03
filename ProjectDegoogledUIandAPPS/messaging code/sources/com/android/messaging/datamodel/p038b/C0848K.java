package com.android.messaging.datamodel.p038b;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.rastermill.FrameSequence;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.b.K */
public class C0848K extends C0879s {
    public C0848K(Context context, C0849L l) {
        super(context, l);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Jh */
    public InputStream mo6085Jh() {
        return this.mContext.getContentResolver().openInputStream(((C0849L) this.mDescriptor).uri);
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public C0881u mo6116k(List list) {
        C0878r rVar;
        C0883w d;
        if (this.mDescriptor.isStatic || !mo6086Lh()) {
            Bitmap Mh = mo6087Mh();
            if (Mh != null) {
                rVar = new C0873m(getKey(), Mh, this.mOrientation);
            } else {
                throw new RuntimeException("failed decoding bitmap");
            }
        } else {
            String key = getKey();
            InputStream Jh = mo6085Jh();
            try {
                FrameSequence decodeStream = FrameSequence.decodeStream(Jh);
                if (decodeStream == null) {
                    rVar = null;
                } else {
                    rVar = new C0878r(key, decodeStream);
                }
                if (rVar == null) {
                    throw new RuntimeException("Error decoding gif");
                }
            } finally {
                try {
                    Jh.close();
                } catch (IOException unused) {
                }
            }
        }
        if (!(!((C0849L) this.mDescriptor).f1102FC || list == null || (d = rVar.mo6109d(this)) == null)) {
            list.add(d);
            if (rVar instanceof C0873m) {
                rVar.mo6153R(false);
            }
        }
        return rVar;
    }
}
