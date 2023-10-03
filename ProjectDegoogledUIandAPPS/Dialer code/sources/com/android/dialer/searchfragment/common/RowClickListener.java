package com.android.dialer.searchfragment.common;

import com.android.dialer.dialercontact.DialerContact;

public interface RowClickListener {
    void openCallAndShare(DialerContact dialerContact);

    void placeDuoCall(String str);

    void placeVideoCall(String str, int i);

    void placeVoiceCall(String str, int i);
}
