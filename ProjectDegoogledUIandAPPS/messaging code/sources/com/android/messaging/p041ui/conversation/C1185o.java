package com.android.messaging.p041ui.conversation;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.ui.conversation.o */
class C1185o implements Parcelable.Creator {
    C1185o() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ConversationActivityUiState(parcel, (C1185o) null);
    }

    public Object[] newArray(int i) {
        return new ConversationActivityUiState[i];
    }
}
