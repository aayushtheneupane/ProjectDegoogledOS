package com.android.settings.homepage.contextualcards.legacysuggestion;

import android.app.PendingIntent;
import com.android.settings.homepage.contextualcards.ContextualCard;

public class LegacySuggestionContextualCard extends ContextualCard {
    private final PendingIntent mPendingIntent;

    public int getCardType() {
        return 2;
    }

    public LegacySuggestionContextualCard(Builder builder) {
        super((ContextualCard.Builder) builder);
        this.mPendingIntent = builder.mPendingIntent;
    }

    public PendingIntent getPendingIntent() {
        return this.mPendingIntent;
    }

    public static class Builder extends ContextualCard.Builder {
        /* access modifiers changed from: private */
        public PendingIntent mPendingIntent;

        public Builder setPendingIntent(PendingIntent pendingIntent) {
            this.mPendingIntent = pendingIntent;
            return this;
        }

        public Builder setCardType(int i) {
            throw new IllegalArgumentException("Cannot change card type for " + Builder.class.getName());
        }

        public LegacySuggestionContextualCard build() {
            return new LegacySuggestionContextualCard(this);
        }
    }
}
