package com.android.settings.homepage.contextualcards.legacysuggestion;

import android.app.PendingIntent;
import android.content.Context;
import android.service.settings.suggestions.Suggestion;
import android.util.ArrayMap;
import android.util.Log;
import com.android.settings.homepage.contextualcards.ContextualCard;
import com.android.settings.homepage.contextualcards.ContextualCardController;
import com.android.settings.homepage.contextualcards.ContextualCardUpdateListener;
import com.android.settings.homepage.contextualcards.legacysuggestion.LegacySuggestionContextualCard;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.suggestions.SuggestionController;
import com.android.settingslib.utils.ThreadUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LegacySuggestionContextualCardController implements ContextualCardController, LifecycleObserver, OnStart, OnStop, SuggestionController.ServiceConnectionListener {
    private ContextualCardUpdateListener mCardUpdateListener;
    private final Context mContext;
    SuggestionController mSuggestionController;

    public void onActionClick(ContextualCard contextualCard) {
    }

    public void onDismissed(ContextualCard contextualCard) {
    }

    public void onServiceDisconnected() {
    }

    public LegacySuggestionContextualCardController(Context context) {
        this.mContext = context;
        if (!this.mContext.getResources().getBoolean(C1715R.bool.config_use_legacy_suggestion)) {
            Log.w("LegacySuggestCardCtrl", "Legacy suggestion contextual card disabled, skipping.");
            return;
        }
        this.mSuggestionController = new SuggestionController(this.mContext, FeatureFactory.getFactory(this.mContext).getSuggestionFeatureProvider(this.mContext).getSuggestionServiceComponent(), this);
    }

    public void onPrimaryClick(ContextualCard contextualCard) {
        try {
            ((LegacySuggestionContextualCard) contextualCard).getPendingIntent().send();
        } catch (PendingIntent.CanceledException unused) {
            Log.w("LegacySuggestCardCtrl", "Failed to start suggestion " + contextualCard.getTitleText());
        }
    }

    public void setCardUpdateListener(ContextualCardUpdateListener contextualCardUpdateListener) {
        this.mCardUpdateListener = contextualCardUpdateListener;
    }

    public void onStart() {
        SuggestionController suggestionController = this.mSuggestionController;
        if (suggestionController != null) {
            suggestionController.start();
        }
    }

    public void onStop() {
        SuggestionController suggestionController = this.mSuggestionController;
        if (suggestionController != null) {
            suggestionController.stop();
        }
    }

    public void onServiceConnected() {
        loadSuggestions();
    }

    private void loadSuggestions() {
        ThreadUtils.postOnBackgroundThread((Runnable) new Runnable() {
            public final void run() {
                LegacySuggestionContextualCardController.this.mo10313x4dd0bbd5();
            }
        });
    }

    /* renamed from: lambda$loadSuggestions$1$LegacySuggestionContextualCardController */
    public /* synthetic */ void mo10313x4dd0bbd5() {
        String str;
        SuggestionController suggestionController = this.mSuggestionController;
        if (suggestionController != null && this.mCardUpdateListener != null) {
            List<Suggestion> suggestions = suggestionController.getSuggestions();
            if (suggestions == null) {
                str = "null";
            } else {
                str = String.valueOf(suggestions.size());
            }
            Log.d("LegacySuggestCardCtrl", "Loaded suggests: " + str);
            ArrayList arrayList = new ArrayList();
            if (suggestions != null) {
                for (Suggestion next : suggestions) {
                    LegacySuggestionContextualCard.Builder builder = new LegacySuggestionContextualCard.Builder();
                    if (next.getIcon() != null) {
                        builder.setIconDrawable(next.getIcon().loadDrawable(this.mContext));
                    }
                    builder.setPendingIntent(next.getPendingIntent());
                    builder.setName(next.getId());
                    builder.setTitleText(next.getTitle().toString());
                    builder.setSummaryText(next.getSummary().toString());
                    builder.setViewType(C1715R.layout.legacy_suggestion_tile);
                    arrayList.add(builder.build());
                }
            }
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put(2, arrayList);
            ThreadUtils.postOnMainThread(new Runnable(arrayMap) {
                private final /* synthetic */ Map f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    LegacySuggestionContextualCardController.this.mo10312x466b86b6(this.f$1);
                }
            });
        }
    }

    /* renamed from: lambda$loadSuggestions$0$LegacySuggestionContextualCardController */
    public /* synthetic */ void mo10312x466b86b6(Map map) {
        this.mCardUpdateListener.onContextualCardUpdated(map);
    }
}
