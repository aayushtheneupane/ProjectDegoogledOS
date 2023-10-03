package com.android.messaging.p041ui.conversationsettings;

import android.app.Activity;
import android.content.DialogInterface;
import com.android.messaging.datamodel.data.C0906S;
import com.android.messaging.datamodel.p037a.C0784d;

/* renamed from: com.android.messaging.ui.conversationsettings.b */
class C1239b implements DialogInterface.OnClickListener {
    final /* synthetic */ PeopleAndOptionsFragment this$0;
    final /* synthetic */ Activity val$activity;

    C1239b(PeopleAndOptionsFragment peopleAndOptionsFragment, Activity activity) {
        this.this$0 = peopleAndOptionsFragment;
        this.val$activity = activity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ((C0906S) this.this$0.mBinding.getData()).mo6369a((C0784d) this.this$0.mBinding, true);
        this.val$activity.setResult(1);
        this.val$activity.finish();
    }
}
