package com.android.dialer.preferredsim;

import android.content.ContentValues;
import android.content.Context;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.common.Assert;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.preferredsim.suggestion.SimSuggestionComponent;
import com.android.dialer.preferredsim.suggestion.SuggestionProvider;
import com.android.dialer.preferredsim.suggestion.stub.StubSuggestionProvider;

public class PreferredAccountRecorder {
    private final String dataId;
    private final String number;
    private final SuggestionProvider.Suggestion suggestion;

    private static class UserSelectionReporter implements DialerExecutor.Worker<Context, Void> {
        private final String number;
        private final PhoneAccountHandle phoneAccountHandle;
        private final boolean remember;

        public UserSelectionReporter(PhoneAccountHandle phoneAccountHandle2, String str, boolean z) {
            Assert.isNotNull(phoneAccountHandle2);
            this.phoneAccountHandle = phoneAccountHandle2;
            Assert.isNotNull(str);
            this.number = str;
            this.remember = z;
        }

        public Object doInBackground(Object obj) throws Throwable {
            Context context = (Context) obj;
            ((StubSuggestionProvider) SimSuggestionComponent.get(context).getSuggestionProvider()).reportUserSelection(context, this.number, this.phoneAccountHandle, this.remember);
            return null;
        }
    }

    private static class WritePreferredAccountWorker implements DialerExecutor.Worker<WritePreferredAccountWorkerInput, Void> {
        /* synthetic */ WritePreferredAccountWorker(C05541 r1) {
        }

        public Object doInBackground(Object obj) throws Throwable {
            WritePreferredAccountWorkerInput writePreferredAccountWorkerInput = (WritePreferredAccountWorkerInput) obj;
            ContentValues contentValues = new ContentValues();
            contentValues.put("preferred_phone_account_component_name", writePreferredAccountWorkerInput.phoneAccountHandle.getComponentName().flattenToString());
            contentValues.put("preferred_phone_account_id", writePreferredAccountWorkerInput.phoneAccountHandle.getId());
            writePreferredAccountWorkerInput.context.getContentResolver().update(PreferredSimFallbackContract.CONTENT_URI, contentValues, "data_id = ?", new String[]{String.valueOf(writePreferredAccountWorkerInput.dataId)});
            return null;
        }
    }

    private static class WritePreferredAccountWorkerInput {
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public final String dataId;
        /* access modifiers changed from: private */
        public final PhoneAccountHandle phoneAccountHandle;

        WritePreferredAccountWorkerInput(Context context2, String str, PhoneAccountHandle phoneAccountHandle2) {
            Assert.isNotNull(context2);
            this.context = context2;
            Assert.isNotNull(str);
            this.dataId = str;
            Assert.isNotNull(phoneAccountHandle2);
            this.phoneAccountHandle = phoneAccountHandle2;
        }
    }

    public PreferredAccountRecorder(String str, SuggestionProvider.Suggestion suggestion2, String str2) {
        this.number = str;
        this.suggestion = suggestion2;
        this.dataId = str2;
    }

    public void record(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
        SuggestionProvider.Suggestion suggestion2 = this.suggestion;
        if (suggestion2 != null) {
            if (suggestion2.phoneAccountHandle.equals(phoneAccountHandle)) {
                ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.DUAL_SIM_SELECTION_SUGGESTED_SIM_SELECTED);
            } else {
                ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.DUAL_SIM_SELECTION_NON_SUGGESTED_SIM_SELECTED);
            }
        }
        if (this.dataId != null && z) {
            ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.DUAL_SIM_SELECTION_PREFERRED_SET);
            ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(new WritePreferredAccountWorker((C05541) null)).build().executeParallel(new WritePreferredAccountWorkerInput(context, this.dataId, phoneAccountHandle));
        }
        if (this.number != null) {
            ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(new UserSelectionReporter(phoneAccountHandle, this.number, z)).build().executeParallel(context);
        }
    }
}
