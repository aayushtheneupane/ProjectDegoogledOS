package com.android.dialer.calllog;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.provider.CallLog;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.NumberAttributes;
import com.android.dialer.calllog.datasources.CallLogMutations;
import com.android.dialer.common.LogUtil;
import com.android.dialer.protos.ProtoParsers;
import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class CallLogCacheUpdater {
    static final int CACHE_UPDATE_LIMIT = 100;
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutor;
    private final CallLogState callLogState;

    CallLogCacheUpdater(Context context, ListeningExecutorService listeningExecutorService, CallLogState callLogState2) {
        this.appContext = context;
        this.backgroundExecutor = listeningExecutorService;
        this.callLogState = callLogState2;
    }

    static /* synthetic */ void lambda$updateCacheInternal$1(ArrayList arrayList, Map.Entry entry) {
        ContentValues contentValues = (ContentValues) entry.getValue();
        if (contentValues.containsKey("number_attributes") && contentValues.containsKey("number")) {
            NumberAttributes numberAttributes = (NumberAttributes) ProtoParsers.getTrusted(contentValues, "number_attributes", NumberAttributes.getDefaultInstance());
            arrayList.add(ContentProviderOperation.newUpdate(ContentUris.withAppendedId(CallLog.Calls.CONTENT_URI, ((Long) entry.getKey()).longValue())).withValue("formatted_number", contentValues.getAsString("formatted_number")).withValue("lookup_uri", numberAttributes.getLookupUri()).withValue("name", numberAttributes.getName()).withValue("normalized_number", ((DialerPhoneNumber) ProtoParsers.getTrusted(contentValues, "number", DialerPhoneNumber.getDefaultInstance())).getNormalizedNumber()).withValue("numberlabel", numberAttributes.getNumberTypeLabel()).withValue("numbertype", 0).withValue("photo_id", Long.valueOf(numberAttributes.getPhotoId())).withValue("photo_uri", numberAttributes.getPhotoUri()).withSelection("name IS NOT ?", new String[]{numberAttributes.getName()}).build());
        }
    }

    public /* synthetic */ Void lambda$updateCache$0$CallLogCacheUpdater(CallLogMutations callLogMutations, Boolean bool) {
        if (!bool.booleanValue()) {
            LogUtil.m9i("CallLogCacheUpdater.updateCache", "not updating cache for initial build", new Object[0]);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Stream.concat(callLogMutations.getInserts().entrySet().stream(), callLogMutations.getUpdates().entrySet().stream()).limit(100).forEach(new Consumer(arrayList) {
            private final /* synthetic */ ArrayList f$0;

            {
                this.f$0 = r1;
            }

            public final void accept(Object obj) {
                CallLogCacheUpdater.lambda$updateCacheInternal$1(this.f$0, (Map.Entry) obj);
            }
        });
        try {
            LogUtil.m9i("CallLogCacheUpdater.updateCache", "updated %d rows", Integer.valueOf(Arrays.stream(this.appContext.getContentResolver().applyBatch("call_log", arrayList)).mapToInt($$Lambda$CallLogCacheUpdater$PDXsbO5vfRm5_q5CqG0DdTVUdPI.INSTANCE).sum()));
            return null;
        } catch (OperationApplicationException | RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public ListenableFuture<Void> updateCache(CallLogMutations callLogMutations) {
        return Futures.transform(this.callLogState.isBuilt(), new Function(callLogMutations) {
            private final /* synthetic */ CallLogMutations f$1;

            {
                this.f$1 = r2;
            }

            public final Object apply(Object obj) {
                return CallLogCacheUpdater.this.lambda$updateCache$0$CallLogCacheUpdater(this.f$1, (Boolean) obj);
            }
        }, this.backgroundExecutor);
    }
}
