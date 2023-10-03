package com.android.dialer.preferredsim;

import android.telecom.PhoneAccountHandle;
import com.android.contacts.common.widget.SelectPhoneAccountDialogOptions;
import com.android.dialer.preferredsim.AutoValue_PreferredAccountWorker_Result;
import com.android.dialer.preferredsim.suggestion.SuggestionProvider;
import com.google.auto.value.AutoValue;
import com.google.common.base.Optional;

public interface PreferredAccountWorker {

    @AutoValue
    public static abstract class Result {

        public static abstract class Builder {
            public abstract Result build();

            public abstract Builder setDataId(String str);

            public abstract Builder setSuggestion(SuggestionProvider.Suggestion suggestion);
        }

        public static Builder builder(PhoneAccountHandle phoneAccountHandle) {
            AutoValue_PreferredAccountWorker_Result.Builder builder = new AutoValue_PreferredAccountWorker_Result.Builder();
            builder.setSelectedPhoneAccountHandle(phoneAccountHandle);
            return builder;
        }

        public abstract Optional<String> getDataId();

        public abstract Optional<SelectPhoneAccountDialogOptions.Builder> getDialogOptionsBuilder();

        public abstract Optional<PhoneAccountHandle> getSelectedPhoneAccountHandle();

        public abstract Optional<SuggestionProvider.Suggestion> getSuggestion();

        public static Builder builder(SelectPhoneAccountDialogOptions.Builder builder) {
            AutoValue_PreferredAccountWorker_Result.Builder builder2 = new AutoValue_PreferredAccountWorker_Result.Builder();
            builder2.setDialogOptionsBuilder(builder);
            return builder2;
        }
    }
}
