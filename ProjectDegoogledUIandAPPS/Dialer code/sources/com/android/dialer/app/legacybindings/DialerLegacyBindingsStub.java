package com.android.dialer.app.legacybindings;

import android.app.Activity;
import android.view.ViewGroup;
import com.android.dialer.app.calllog.CallLogAdapter;
import com.android.dialer.app.calllog.calllogcache.CallLogCache;
import com.android.dialer.app.contactinfo.ContactInfoCache;
import com.android.dialer.app.voicemail.VoicemailPlaybackPresenter;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;

public class DialerLegacyBindingsStub implements DialerLegacyBindings {
    public CallLogAdapter newCallLogAdapter(Activity activity, ViewGroup viewGroup, CallLogAdapter.CallFetcher callFetcher, CallLogAdapter.MultiSelectRemoveView multiSelectRemoveView, CallLogAdapter.OnActionModeStateChangedListener onActionModeStateChangedListener, CallLogCache callLogCache, ContactInfoCache contactInfoCache, VoicemailPlaybackPresenter voicemailPlaybackPresenter, FilteredNumberAsyncQueryHandler filteredNumberAsyncQueryHandler, int i) {
        return new CallLogAdapter(activity, viewGroup, callFetcher, multiSelectRemoveView, onActionModeStateChangedListener, callLogCache, contactInfoCache, voicemailPlaybackPresenter, filteredNumberAsyncQueryHandler, i);
    }
}
