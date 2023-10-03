package com.android.dialer.preferredsim.suggestion.stub;

import android.content.Context;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.preferredsim.suggestion.SuggestionProvider;
import com.google.common.base.Optional;

public class StubSuggestionProvider implements SuggestionProvider {
    public Optional<SuggestionProvider.Suggestion> getSuggestion(Context context, String str) {
        return Optional.absent();
    }

    public void reportIncorrectSuggestion(Context context, String str, PhoneAccountHandle phoneAccountHandle) {
    }

    public void reportUserSelection(Context context, String str, PhoneAccountHandle phoneAccountHandle, boolean z) {
    }
}
