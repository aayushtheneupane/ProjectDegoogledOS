package com.android.settingslib.suggestions;

import android.content.Context;
import android.service.settings.suggestions.Suggestion;
import com.android.settingslib.utils.AsyncLoader;
import java.util.List;

@Deprecated
public class SuggestionLoader extends AsyncLoader<List<Suggestion>> {
    private final SuggestionController mSuggestionController;

    /* access modifiers changed from: protected */
    public void onDiscardResult(List<Suggestion> list) {
    }

    public SuggestionLoader(Context context, SuggestionController suggestionController) {
        super(context);
        this.mSuggestionController = suggestionController;
    }

    public List<Suggestion> loadInBackground() {
        this.mSuggestionController.getSuggestions();
        throw null;
    }
}
