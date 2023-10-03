package com.android.dialer.calllog.datasources.voicemail;

import android.content.ContentValues;
import android.content.Context;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.calllog.datasources.CallLogDataSource;
import com.android.dialer.calllog.datasources.CallLogMutations;
import com.android.dialer.compat.telephony.TelephonyManagerCompat;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.util.PermissionsUtil;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Map;
import java.util.concurrent.Callable;

public class VoicemailDataSource implements CallLogDataSource {
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutor;

    VoicemailDataSource(Context context, ListeningExecutorService listeningExecutorService) {
        this.appContext = context;
        this.backgroundExecutor = listeningExecutorService;
    }

    public ListenableFuture<Void> clearData() {
        return Futures.immediateFuture(null);
    }

    public ListenableFuture<Void> fill(CallLogMutations callLogMutations) {
        if (PermissionsUtil.hasReadPhoneStatePermissions(this.appContext)) {
            return this.backgroundExecutor.submit(new Callable(callLogMutations) {
                private final /* synthetic */ CallLogMutations f$1;

                {
                    this.f$1 = r2;
                }

                public final Object call() {
                    return VoicemailDataSource.this.lambda$fill$0$VoicemailDataSource(this.f$1);
                }
            });
        }
        for (Map.Entry<Long, ContentValues> value : callLogMutations.getInserts().entrySet()) {
            ((ContentValues) value.getValue()).put("is_voicemail_call", 0);
        }
        return Futures.immediateFuture(null);
    }

    public String getLoggingName() {
        return "VoicemailDataSource";
    }

    public ListenableFuture<Boolean> isDirty() {
        return Futures.immediateFuture(false);
    }

    public /* synthetic */ Void lambda$fill$0$VoicemailDataSource(CallLogMutations callLogMutations) throws Exception {
        TelecomManager telecomManager = (TelecomManager) this.appContext.getSystemService(TelecomManager.class);
        for (Map.Entry<Long, ContentValues> value : callLogMutations.getInserts().entrySet()) {
            ContentValues contentValues = (ContentValues) value.getValue();
            PhoneAccountHandle composePhoneAccountHandle = TelecomUtil.composePhoneAccountHandle(contentValues.getAsString("phone_account_component_name"), contentValues.getAsString("phone_account_id"));
            try {
                if (telecomManager.isVoiceMailNumber(composePhoneAccountHandle, DialerPhoneNumber.parseFrom(contentValues.getAsByteArray("number")).getNormalizedNumber())) {
                    contentValues.put("is_voicemail_call", 1);
                    contentValues.put("voicemail_call_tag", TelephonyManagerCompat.getTelephonyManagerForPhoneAccountHandle(this.appContext, composePhoneAccountHandle).getVoiceMailAlphaTag());
                } else {
                    contentValues.put("is_voicemail_call", 0);
                }
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalStateException(e);
            }
        }
        return null;
    }

    public ListenableFuture<Void> onSuccessfulFill() {
        return Futures.immediateFuture(null);
    }

    public void registerContentObservers() {
    }

    public void unregisterContentObservers() {
    }
}
