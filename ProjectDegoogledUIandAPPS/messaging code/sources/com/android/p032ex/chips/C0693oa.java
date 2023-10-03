package com.android.p032ex.chips;

import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import com.android.p032ex.chips.p033a.C0660b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.android.ex.chips.oa */
class C0693oa extends AsyncTask {
    final /* synthetic */ C0697qa this$0;

    /* synthetic */ C0693oa(C0697qa qaVar, C0650S s) {
        this.this$0 = qaVar;
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public C0660b m1076f(C0699ra raVar) {
        try {
            if (this.this$0.mNoChipMode) {
                return null;
            }
            return this.this$0.constructChipSpan(raVar);
        } catch (NullPointerException e) {
            Log.e("RecipientEditTextView", e.getMessage(), e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        if (this.this$0.mIndividualReplacements != null) {
            this.this$0.mIndividualReplacements.cancel(true);
        }
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, this.this$0.getSortedRecipients());
        if (this.this$0.mHiddenSpans != null) {
            arrayList.addAll(this.this$0.mHiddenSpans);
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C0660b bVar = (C0660b) it.next();
            if (bVar != null) {
                arrayList2.add(this.this$0.createAddressText(bVar.getEntry()));
            }
        }
        this.this$0.getAdapter().getMatchingRecipients(arrayList2, new C0689ma(this, arrayList));
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        ArrayList<C0660b> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, this.this$0.getSortedRecipients());
        if (this.this$0.mHiddenSpans != null) {
            arrayList.addAll(this.this$0.mHiddenSpans);
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (C0660b bVar : arrayList) {
            if (!C0699ra.m1084b(bVar.getEntry().getContactId()) || this.this$0.getSpannable().getSpanStart(bVar) == -1) {
                arrayList2.add((Object) null);
            } else {
                arrayList2.add(m1076f(bVar.getEntry()));
            }
        }
        m1075a((List) arrayList, (List) arrayList2);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1075a(List list, List list2) {
        if (list2 != null && list2.size() > 0) {
            C0691na naVar = new C0691na(this, list, list2);
            if (Looper.myLooper() == Looper.getMainLooper()) {
                naVar.run();
            } else {
                this.this$0.mHandler.post(naVar);
            }
        }
    }
}
