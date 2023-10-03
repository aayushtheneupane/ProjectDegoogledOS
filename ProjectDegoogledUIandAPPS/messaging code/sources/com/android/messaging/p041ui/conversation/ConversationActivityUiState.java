package com.android.messaging.p041ui.conversation;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.conversation.ConversationActivityUiState */
public class ConversationActivityUiState implements Parcelable, Cloneable {
    public static final Parcelable.Creator CREATOR = new C1185o();

    /* renamed from: Ka */
    private String f1784Ka;

    /* renamed from: NG */
    private int f1785NG;

    /* renamed from: OG */
    private boolean f1786OG = false;

    /* renamed from: PG */
    private int f1787PG;
    private C1187p mHost;

    ConversationActivityUiState(String str) {
        this.f1784Ka = str;
        this.f1785NG = str == null ? 2 : 1;
    }

    /* renamed from: f */
    private void m2814f(int i, boolean z) {
        boolean z2 = true;
        this.f1787PG++;
        int i2 = this.f1785NG;
        if (i != i2) {
            this.f1785NG = i;
            int i3 = this.f1785NG;
            m2815ro();
            if (this.f1787PG <= 0) {
                z2 = false;
            }
            C1424b.m3592ia(z2);
            C1187p pVar = this.mHost;
            if (pVar != null) {
                pVar.mo7327a(i2, i3, z);
            }
        }
        int i4 = this.f1787PG - 1;
        this.f1787PG = i4;
        if (i4 < 0) {
            C1424b.fail("Unbalanced Ui updates!");
        }
    }

    /* renamed from: ro */
    private void m2815ro() {
        boolean z = true;
        if ((this.f1785NG == 2) != (this.f1784Ka == null)) {
            z = false;
        }
        C1424b.m3592ia(z);
    }

    /* renamed from: Ue */
    public String mo7334Ue() {
        return this.f1784Ka;
    }

    /* renamed from: Z */
    public void mo7335Z(boolean z) {
        if (this.f1785NG == 3 && !z) {
            m2814f(4, false);
        } else if (this.f1785NG == 4 && z) {
            m2814f(3, false);
        }
    }

    /* renamed from: a */
    public void mo7336a(C1187p pVar) {
        this.mHost = pVar;
    }

    public int describeContents() {
        return 0;
    }

    /* renamed from: ej */
    public int mo7339ej() {
        int i = this.f1785NG;
        if (i == 2) {
            return 1;
        }
        int i2 = 3;
        if (i != 3) {
            i2 = 4;
            if (i != 4) {
                if (i == 5) {
                    return 2;
                }
                C1424b.fail("Invalid contact picking mode for ConversationActivity!");
                return 0;
            }
        }
        return i2;
    }

    /* renamed from: fj */
    public void mo7340fj() {
        if (this.f1785NG == 5) {
            this.f1786OG = true;
            m2814f(3, true);
            return;
        }
        C1424b.fail("Invalid conversation activity state: can't add more participants!");
    }

    /* renamed from: gj */
    public void mo7341gj() {
        int i = this.f1785NG;
        C1424b.m3592ia((i == 2 || i == 3 || i == 4) ? false : true);
        if (this.f1785NG == 5) {
            m2814f(1, true);
        }
    }

    /* renamed from: hj */
    public boolean mo7342hj() {
        int i = this.f1785NG;
        return i == 3 || i == 4 || i == 2 || i == 5;
    }

    /* renamed from: ij */
    public boolean mo7343ij() {
        int i = this.f1785NG;
        return i == 5 || i == 1;
    }

    /* renamed from: p */
    public boolean mo7344p() {
        if (!this.f1786OG) {
            return false;
        }
        this.f1786OG = false;
        return true;
    }

    /* renamed from: ta */
    public void mo7345ta(String str) {
        int i;
        int i2 = this.f1785NG;
        if (i2 == 2) {
            i = 5;
        } else {
            if (!(i2 == 3 || i2 == 4)) {
                C1424b.fail("Invalid conversation activity state: can't create conversation!");
            }
            i = 1;
        }
        this.f1784Ka = str;
        m2814f(i, true);
    }

    /* access modifiers changed from: package-private */
    public void testSetUiState(int i) {
        this.f1785NG = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f1785NG);
        parcel.writeString(this.f1784Ka);
    }

    /* access modifiers changed from: protected */
    public ConversationActivityUiState clone() {
        try {
            return (ConversationActivityUiState) super.clone();
        } catch (CloneNotSupportedException unused) {
            C1424b.fail("ConversationActivityUiState: failed to clone(). Is there a mutable reference?");
            return null;
        }
    }

    /* synthetic */ ConversationActivityUiState(Parcel parcel, C1185o oVar) {
        this.f1785NG = parcel.readInt();
        this.f1784Ka = parcel.readString();
        m2815ro();
    }
}
