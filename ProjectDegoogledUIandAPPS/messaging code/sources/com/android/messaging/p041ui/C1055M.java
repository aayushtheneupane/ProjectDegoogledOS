package com.android.messaging.p041ui;

import com.android.messaging.C0967f;

/* renamed from: com.android.messaging.ui.M */
public class C1055M extends C1057N {
    public C1055M(C1051K[] kArr) {
        super(kArr);
    }

    public CharSequence getPageTitle(int i) {
        return ((C1051K) getViewHolder(i, false)).getPageTitle(C0967f.get().getApplicationContext());
    }
}
