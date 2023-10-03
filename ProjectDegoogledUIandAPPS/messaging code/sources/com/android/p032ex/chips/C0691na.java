package com.android.p032ex.chips;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import com.android.p032ex.chips.p033a.C0660b;
import java.util.List;

/* renamed from: com.android.ex.chips.na */
class C0691na implements Runnable {
    final /* synthetic */ C0693oa this$1;

    /* renamed from: vv */
    final /* synthetic */ List f807vv;

    /* renamed from: wv */
    final /* synthetic */ List f808wv;

    C0691na(C0693oa oaVar, List list, List list2) {
        this.this$1 = oaVar;
        this.f807vv = list;
        this.f808wv = list2;
    }

    public void run() {
        int spanStart;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.this$1.this$0.getText());
        int i = 0;
        for (C0660b bVar : this.f807vv) {
            C0660b bVar2 = (C0660b) this.f808wv.get(i);
            if (bVar2 != null) {
                C0699ra entry = bVar.getEntry();
                C0699ra entry2 = bVar2.getEntry();
                if ((C0646N.m1041a(entry, entry2) == entry2) && (spanStart = spannableStringBuilder.getSpanStart(bVar)) != -1) {
                    int min = Math.min(spannableStringBuilder.getSpanEnd(bVar) + 1, spannableStringBuilder.length());
                    spannableStringBuilder.removeSpan(bVar);
                    SpannableString spannableString = new SpannableString(this.this$1.this$0.createAddressText(bVar2.getEntry()).trim() + " ");
                    spannableString.setSpan(bVar2, 0, spannableString.length() - 1, 33);
                    spannableStringBuilder.replace(spanStart, min, spannableString);
                    bVar2.mo5464a(spannableString.toString());
                    this.f808wv.set(i, (Object) null);
                    this.f807vv.set(i, bVar2);
                }
            }
            i++;
        }
        this.this$1.this$0.setText(spannableStringBuilder);
    }
}
