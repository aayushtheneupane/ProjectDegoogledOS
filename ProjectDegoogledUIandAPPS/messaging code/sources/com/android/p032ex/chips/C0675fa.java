package com.android.p032ex.chips;

import android.os.AsyncTask;
import com.android.p032ex.chips.p033a.C0660b;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.android.ex.chips.fa */
class C0675fa extends AsyncTask {
    final /* synthetic */ C0697qa this$0;

    /* synthetic */ C0675fa(C0697qa qaVar, C0650S s) {
        this.this$0 = qaVar;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        ArrayList arrayList = ((ArrayList[]) objArr)[0];
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C0660b bVar = (C0660b) it.next();
            if (bVar != null) {
                arrayList2.add(this.this$0.createAddressText(bVar.getEntry()));
            }
        }
        this.this$0.getAdapter().getMatchingRecipients(arrayList2, new C0673ea(this, arrayList));
        return null;
    }
}
