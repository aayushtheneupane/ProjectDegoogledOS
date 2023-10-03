package com.android.dialer.preferredsim;

import android.telecom.PhoneAccountHandle;
import com.android.contacts.common.widget.SelectPhoneAccountDialogOptions;
import com.android.dialer.preferredsim.PreferredAccountWorker;
import com.android.dialer.preferredsim.suggestion.SuggestionProvider;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;

final class AutoValue_PreferredAccountWorker_Result extends PreferredAccountWorker.Result {
    private final Optional<String> dataId;
    private final Optional<SelectPhoneAccountDialogOptions.Builder> dialogOptionsBuilder;
    private final Optional<PhoneAccountHandle> selectedPhoneAccountHandle;
    private final Optional<SuggestionProvider.Suggestion> suggestion;

    static final class Builder extends PreferredAccountWorker.Result.Builder {
        private Optional<String> dataId = Optional.absent();
        private Optional<SelectPhoneAccountDialogOptions.Builder> dialogOptionsBuilder = Optional.absent();
        private Optional<PhoneAccountHandle> selectedPhoneAccountHandle = Optional.absent();
        private Optional<SuggestionProvider.Suggestion> suggestion = Optional.absent();

        Builder() {
        }

        public PreferredAccountWorker.Result build() {
            return new AutoValue_PreferredAccountWorker_Result(this.selectedPhoneAccountHandle, this.dialogOptionsBuilder, this.dataId, this.suggestion, (C05531) null);
        }

        public PreferredAccountWorker.Result.Builder setDataId(String str) {
            if (str != null) {
                this.dataId = Optional.m67of(str);
                return this;
            }
            throw new NullPointerException("Null dataId");
        }

        /* access modifiers changed from: package-private */
        public PreferredAccountWorker.Result.Builder setDialogOptionsBuilder(SelectPhoneAccountDialogOptions.Builder builder) {
            if (builder != null) {
                this.dialogOptionsBuilder = Optional.m67of(builder);
                return this;
            }
            throw new NullPointerException("Null dialogOptionsBuilder");
        }

        /* access modifiers changed from: package-private */
        public PreferredAccountWorker.Result.Builder setSelectedPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle) {
            if (phoneAccountHandle != null) {
                this.selectedPhoneAccountHandle = Optional.m67of(phoneAccountHandle);
                return this;
            }
            throw new NullPointerException("Null selectedPhoneAccountHandle");
        }

        public PreferredAccountWorker.Result.Builder setSuggestion(SuggestionProvider.Suggestion suggestion2) {
            if (suggestion2 != null) {
                this.suggestion = Optional.m67of(suggestion2);
                return this;
            }
            throw new NullPointerException("Null suggestion");
        }
    }

    /* synthetic */ AutoValue_PreferredAccountWorker_Result(Optional optional, Optional optional2, Optional optional3, Optional optional4, C05531 r5) {
        this.selectedPhoneAccountHandle = optional;
        this.dialogOptionsBuilder = optional2;
        this.dataId = optional3;
        this.suggestion = optional4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PreferredAccountWorker.Result)) {
            return false;
        }
        PreferredAccountWorker.Result result = (PreferredAccountWorker.Result) obj;
        if (this.selectedPhoneAccountHandle.equals(((AutoValue_PreferredAccountWorker_Result) result).selectedPhoneAccountHandle)) {
            AutoValue_PreferredAccountWorker_Result autoValue_PreferredAccountWorker_Result = (AutoValue_PreferredAccountWorker_Result) result;
            if (this.dialogOptionsBuilder.equals(autoValue_PreferredAccountWorker_Result.dialogOptionsBuilder) && this.dataId.equals(autoValue_PreferredAccountWorker_Result.dataId) && this.suggestion.equals(autoValue_PreferredAccountWorker_Result.suggestion)) {
                return true;
            }
        }
        return false;
    }

    public Optional<String> getDataId() {
        return this.dataId;
    }

    public Optional<SelectPhoneAccountDialogOptions.Builder> getDialogOptionsBuilder() {
        return this.dialogOptionsBuilder;
    }

    public Optional<PhoneAccountHandle> getSelectedPhoneAccountHandle() {
        return this.selectedPhoneAccountHandle;
    }

    public Optional<SuggestionProvider.Suggestion> getSuggestion() {
        return this.suggestion;
    }

    public int hashCode() {
        return this.suggestion.hashCode() ^ ((((((this.selectedPhoneAccountHandle.hashCode() ^ 1000003) * 1000003) ^ this.dialogOptionsBuilder.hashCode()) * 1000003) ^ this.dataId.hashCode()) * 1000003);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Result{selectedPhoneAccountHandle=");
        outline13.append(this.selectedPhoneAccountHandle);
        outline13.append(", dialogOptionsBuilder=");
        outline13.append(this.dialogOptionsBuilder);
        outline13.append(", dataId=");
        outline13.append(this.dataId);
        outline13.append(", suggestion=");
        return GeneratedOutlineSupport.outline11(outline13, this.suggestion, "}");
    }
}
