package com.android.messaging.p041ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import com.android.messaging.R;
import com.android.messaging.sms.C1002A;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.ui.ma */
public class C1272ma extends Fragment {
    private C1272ma() {
    }

    /* renamed from: a */
    static /* synthetic */ void m3189a(C1272ma maVar, int i) {
        FragmentTransaction beginTransaction = maVar.getFragmentManager().beginTransaction();
        C1270la newInstance = C1270la.newInstance(i);
        newInstance.setTargetFragment(maVar, 0);
        newInstance.show(beginTransaction, (String) null);
    }

    public static C1272ma newInstance() {
        return new C1272ma();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        C1264ia newInstance = C1264ia.newInstance();
        newInstance.setTargetFragment(this, 0);
        newInstance.show(beginTransaction, (String) null);
    }

    /* renamed from: a */
    static /* synthetic */ List m3187a(Resources resources) {
        String b = C1002A.m2325b(C1002A.m2323Mi());
        ArrayList arrayList = new ArrayList();
        arrayList.add(resources.getString(R.string.delete_all_media));
        arrayList.add(resources.getString(R.string.delete_oldest_messages, new Object[]{b}));
        return arrayList;
    }
}
