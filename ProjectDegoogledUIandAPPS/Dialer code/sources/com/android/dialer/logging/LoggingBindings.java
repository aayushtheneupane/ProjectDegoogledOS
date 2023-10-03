package com.android.dialer.logging;

import com.android.dialer.logging.AutoValue_LoggingBindings_ContactsProviderMatchInfo;
import com.google.auto.value.AutoValue;

public interface LoggingBindings {

    @AutoValue
    public static abstract class ContactsProviderMatchInfo {

        public static abstract class Builder {
            public abstract ContactsProviderMatchInfo build();

            public abstract Builder setInputNumberHasPostdialDigits(boolean z);

            public abstract Builder setInputNumberLength(int i);

            public abstract Builder setInputNumberValid(boolean z);

            public abstract Builder setMatchedContact(boolean z);

            public abstract Builder setMatchedNumberHasPostdialDigits(boolean z);

            public abstract Builder setMatchedNumberLength(int i);
        }

        public static Builder builder() {
            AutoValue_LoggingBindings_ContactsProviderMatchInfo.Builder builder = new AutoValue_LoggingBindings_ContactsProviderMatchInfo.Builder();
            builder.setMatchedContact(false);
            builder.setMatchedNumberLength(0);
            builder.setMatchedNumberHasPostdialDigits(false);
            return builder;
        }
    }
}
