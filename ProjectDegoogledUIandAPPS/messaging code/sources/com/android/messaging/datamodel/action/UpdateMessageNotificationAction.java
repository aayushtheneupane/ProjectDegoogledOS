package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.datamodel.C0944e;

public class UpdateMessageNotificationAction extends Action {
    public static final Parcelable.Creator CREATOR = new C0808W();

    private UpdateMessageNotificationAction() {
    }

    /* renamed from: Le */
    public static void m1460Le() {
        new UpdateMessageNotificationAction().start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        C0944e.m2090a(true, (String) null, 1);
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ UpdateMessageNotificationAction(Parcel parcel, C0808W w) {
        super(parcel);
    }
}
