package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.sms.C1002A;
import com.android.messaging.util.C1424b;

public class HandleLowStorageAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0828r();

    private HandleLowStorageAction(int i, long j) {
        this.f1057Oy.putInt("sub_op_code", i);
        this.f1057Oy.putLong("cutoff_duration_millis", j);
    }

    /* renamed from: j */
    public static void m1373j(long j) {
        new HandleLowStorageAction(100, j).start();
    }

    /* renamed from: k */
    public static void m1374k(long j) {
        new HandleLowStorageAction(101, j).start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        int i = this.f1057Oy.getInt("sub_op_code");
        long j = this.f1057Oy.getLong("cutoff_duration_millis");
        if (i == 100) {
            C1002A.m2326b(0, j);
        } else if (i != 101) {
            C1424b.fail("Unsupported action type!");
        } else {
            C1002A.m2326b(1, j);
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ HandleLowStorageAction(Parcel parcel, C0828r rVar) {
        super(parcel);
    }
}
