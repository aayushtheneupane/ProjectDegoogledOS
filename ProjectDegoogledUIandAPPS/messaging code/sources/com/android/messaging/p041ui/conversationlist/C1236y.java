package com.android.messaging.p041ui.conversationlist;

import android.database.Cursor;
import androidx.recyclerview.widget.C0586qa;
import com.android.messaging.datamodel.data.C0909V;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.p041ui.C1117ca;
import com.android.messaging.p041ui.PersonItemView;

/* renamed from: com.android.messaging.ui.conversationlist.y */
public class C1236y extends C0586qa implements C1117ca {

    /* renamed from: Sg */
    private final C0909V f1958Sg = new C1235x(this);
    /* access modifiers changed from: private */
    public final C0934q mData = new C0934q();
    final /* synthetic */ C1237z this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1236y(C1237z zVar, PersonItemView personItemView) {
        super(personItemView);
        this.this$0 = zVar;
        personItemView.mo7065a((C1117ca) this);
    }

    /* renamed from: c */
    public void mo7597c(Cursor cursor) {
        this.mData.mo6513c(cursor);
        ((PersonItemView) this.itemView).mo7066d(this.f1958Sg);
    }

    /* renamed from: c */
    public boolean mo7225c(C0909V v) {
        return false;
    }

    /* renamed from: a */
    public void mo7224a(C0909V v) {
        this.this$0.mHostInterface.mo7574b(this.mData);
    }
}
