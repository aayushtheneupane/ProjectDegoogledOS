package com.android.messaging.util;

import android.content.DialogInterface;
import android.widget.ArrayAdapter;

/* renamed from: com.android.messaging.util.A */
class C1395A implements DialogInterface.OnClickListener {

    /* renamed from: WJ */
    final /* synthetic */ ArrayAdapter f2204WJ;

    C1395A(ArrayAdapter arrayAdapter) {
        this.f2204WJ = arrayAdapter;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ((C1407K) this.f2204WJ.getItem(i)).run();
    }
}
