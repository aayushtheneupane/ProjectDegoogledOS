package com.android.p032ex.photo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.loader.content.C0475f;
import com.android.p032ex.photo.p035b.C0715b;
import p000a.p008d.p009a.C0035a;

/* renamed from: com.android.ex.photo.n */
class C0733n implements C0035a {
    final /* synthetic */ C0734o this$0;

    /* synthetic */ C0733n(C0734o oVar, C0727h hVar) {
        this.this$0 = oVar;
    }

    /* renamed from: a */
    public void mo220a(C0475f fVar) {
    }

    /* renamed from: a */
    public void mo221a(C0475f fVar, Object obj) {
        Drawable b = ((C0715b) obj).mo5727b(this.this$0.mActivity.getResources());
        C0713b ea = this.this$0.mActivity.mo5701ea();
        int id = fVar.getId();
        if (id != 1) {
            if (id == 2) {
                C0734o.m1159a(this.this$0, b);
            }
        } else if (b == null) {
            ea.setLogo((Drawable) null);
        } else {
            ea.setLogo(b);
        }
    }

    public C0475f onCreateLoader(int i, Bundle bundle) {
        String string = bundle.getString("image_uri");
        if (i == 1) {
            return this.this$0.mo5775a(1, bundle, string);
        }
        if (i != 2) {
            return null;
        }
        return this.this$0.mo5775a(2, bundle, string);
    }
}
