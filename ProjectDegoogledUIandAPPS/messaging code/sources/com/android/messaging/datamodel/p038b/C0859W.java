package com.android.messaging.datamodel.p038b;

import android.content.Context;
import android.graphics.Bitmap;
import com.android.messaging.util.C1431ea;
import java.io.InputStream;

/* renamed from: com.android.messaging.datamodel.b.W */
public class C0859W extends C0879s {
    public C0859W(Context context, C0849L l) {
        super(context, l);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Gh */
    public Bitmap mo6140Gh() {
        C1431ea eaVar = new C1431ea();
        try {
            eaVar.mo8063v(((C0849L) this.mDescriptor).uri);
            Bitmap frameAtTime = eaVar.getFrameAtTime();
            if (frameAtTime != null) {
                ((C0849L) this.mDescriptor).mo6083u(frameAtTime.getWidth(), frameAtTime.getHeight());
            }
            return frameAtTime;
        } finally {
            eaVar.release();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: Jh */
    public InputStream mo6085Jh() {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Kh */
    public boolean mo6141Kh() {
        return true;
    }
}
