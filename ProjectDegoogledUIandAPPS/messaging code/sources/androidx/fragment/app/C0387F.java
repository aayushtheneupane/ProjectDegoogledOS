package androidx.fragment.app;

import java.util.ArrayList;

/* renamed from: androidx.fragment.app.F */
class C0387F implements C0386E {
    final int mFlags;
    final int mId;
    final String mName;
    final /* synthetic */ C0389H this$0;

    C0387F(C0389H h, String str, int i, int i2) {
        this.this$0 = h;
        this.mName = str;
        this.mId = i;
        this.mFlags = i2;
    }

    public boolean generateOps(ArrayList arrayList, ArrayList arrayList2) {
        C0433s peekChildFragmentManager;
        C0424j jVar = this.this$0.mPrimaryNav;
        if (jVar != null && this.mId < 0 && this.mName == null && (peekChildFragmentManager = jVar.peekChildFragmentManager()) != null && peekChildFragmentManager.popBackStackImmediate()) {
            return false;
        }
        return this.this$0.popBackStackState(arrayList, arrayList2, this.mName, this.mId, this.mFlags);
    }
}
