package com.android.dialer.main.impl.toolbar;

import android.view.MenuItem;

public interface SearchBarListener {

    public interface VoiceSearchResultCallback {
    }

    boolean onMenuItemClicked(MenuItem menuItem);

    void onSearchBackButtonClicked();

    void onSearchBarClicked();

    void onSearchQueryUpdated(String str);

    void onVoiceButtonClicked(VoiceSearchResultCallback voiceSearchResultCallback);
}
