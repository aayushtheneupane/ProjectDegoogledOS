package com.android.incallui;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Trace;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.android.dialer.function.Supplier;
import com.android.dialer.phonenumbercache.ContactInfoHelper;
import com.android.dialer.phonenumbercache.PhoneNumberCache;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.strictmode.StrictModeUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;

public class CallerInfoAsyncQuery {
    private static final String[] DIRECTORY_PROJECTION = {"_id"};

    private static class CallerInfoAsyncQueryHandler extends AsyncQueryHandler {
        private CallerInfo callerInfo;
        /* access modifiers changed from: private */
        public Context queryContext;
        private Uri queryUri;

        class CallerInfoWorkerHandler extends AsyncQueryHandler.WorkerHandler {
            CallerInfoWorkerHandler(Looper looper) {
                super(CallerInfoAsyncQueryHandler.this, looper);
            }

            public void handleMessage(Message message) {
                Cursor cursor;
                AsyncQueryHandler.WorkerArgs workerArgs = (AsyncQueryHandler.WorkerArgs) message.obj;
                CookieWrapper cookieWrapper = (CookieWrapper) workerArgs.cookie;
                if (cookieWrapper == null) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unexpected command (CookieWrapper is null): ");
                    outline13.append(message.what);
                    outline13.append(" ignored by CallerInfoWorkerHandler, passing onto parent.");
                    Bindings.m34d(this, outline13.toString());
                    CallerInfoAsyncQueryHandler.super.handleMessage(message);
                    return;
                }
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("Processing event: ");
                outline132.append(cookieWrapper.event);
                outline132.append(" token (arg1): ");
                outline132.append(message.arg1);
                outline132.append(" command: ");
                outline132.append(message.what);
                outline132.append(" query URI: ");
                outline132.append(CallerInfoAsyncQuery.access$500(workerArgs.uri));
                Bindings.m34d(this, outline132.toString());
                int i = cookieWrapper.event;
                if (i == 1) {
                    ContentResolver contentResolver = CallerInfoAsyncQueryHandler.this.queryContext.getContentResolver();
                    if (contentResolver == null) {
                        Bindings.m36e(this, "Content Resolver is null!");
                        return;
                    }
                    try {
                        cursor = contentResolver.query(workerArgs.uri, workerArgs.projection, workerArgs.selection, workerArgs.selectionArgs, workerArgs.orderBy);
                        if (cursor != null) {
                            cursor.getCount();
                        }
                    } catch (Exception e) {
                        Bindings.m37e((Object) this, "Exception thrown during handling EVENT_ARG_QUERY", e);
                        cursor = null;
                    }
                    workerArgs.result = cursor;
                    CallerInfoAsyncQueryHandler.this.updateData(message.arg1, cookieWrapper, cursor);
                } else if (i == 2 || i == 3 || i == 4) {
                    CallerInfoAsyncQueryHandler.this.updateData(message.arg1, cookieWrapper, (Cursor) workerArgs.result);
                }
                Message obtainMessage = workerArgs.handler.obtainMessage(message.what);
                obtainMessage.obj = workerArgs;
                obtainMessage.arg1 = message.arg1;
                obtainMessage.sendToTarget();
            }
        }

        /* synthetic */ CallerInfoAsyncQueryHandler(Context context, Uri uri, C06291 r3) {
            super(context.getContentResolver());
            this.queryContext = context;
            this.queryUri = uri;
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [android.os.Handler, com.android.incallui.CallerInfoAsyncQuery$CallerInfoAsyncQueryHandler$CallerInfoWorkerHandler] */
        /* access modifiers changed from: protected */
        public Handler createHandler(Looper looper) {
            return new CallerInfoWorkerHandler(looper);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(int i, Object obj, Cursor cursor) {
            Bindings.m34d(this, "##### onQueryComplete() #####   query complete for token: " + i);
            CookieWrapper cookieWrapper = (CookieWrapper) obj;
            if (cookieWrapper.listener != null) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("notifying listener: ");
                outline13.append(cookieWrapper.listener.getClass().toString());
                outline13.append(" for token: ");
                outline13.append(i);
                outline13.append(this.callerInfo);
                Bindings.m34d(this, outline13.toString());
                cookieWrapper.listener.onQueryComplete(i, cookieWrapper.cookie, this.callerInfo);
            }
            this.queryContext = null;
            this.queryUri = null;
            this.callerInfo = null;
        }

        public void startQuery(int i, Object obj, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
            super.startQuery(i, obj, uri, strArr, str, strArr2, str2);
        }

        /* access modifiers changed from: package-private */
        public void updateData(int i, Object obj, Cursor cursor) {
            boolean isClosed;
            try {
                Bindings.m34d(this, "##### updateData() #####  for token: " + i);
                CookieWrapper cookieWrapper = (CookieWrapper) obj;
                if (cookieWrapper == null) {
                    Bindings.m34d(this, "Cookie is null, ignoring onQueryComplete() request.");
                    if (cursor != null && !isClosed) {
                        return;
                    }
                    return;
                }
                if (this.callerInfo == null) {
                    if (this.queryContext == null || this.queryUri == null) {
                        throw new QueryPoolException("Bad context or query uri, or CallerInfoAsyncQuery already released.");
                    }
                    int i2 = cookieWrapper.event;
                    if (i2 == 3) {
                        CallerInfo callerInfo2 = new CallerInfo();
                        callerInfo2.markAsEmergency(this.queryContext);
                        this.callerInfo = callerInfo2;
                    } else if (i2 == 4) {
                        CallerInfo callerInfo3 = new CallerInfo();
                        callerInfo3.markAsVoiceMail(this.queryContext);
                        this.callerInfo = callerInfo3;
                    } else {
                        this.callerInfo = CallerInfo.getCallerInfo(this.queryContext, this.queryUri, cursor);
                        Bindings.m34d(this, "==> Got mCallerInfo: " + this.callerInfo);
                        Context context = this.queryContext;
                        String str = cookieWrapper.number;
                        CallerInfo callerInfo4 = this.callerInfo;
                        if (!callerInfo4.contactExists && PhoneNumberHelper.isUriNumber(str)) {
                            String usernameFromUriNumber = PhoneNumberHelper.getUsernameFromUriNumber(str);
                            if (PhoneNumberUtils.isGlobalPhoneNumber(usernameFromUriNumber)) {
                                Uri withAppendedPath = Uri.withAppendedPath(ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI, Uri.encode(usernameFromUriNumber));
                                callerInfo4 = CallerInfo.getCallerInfo(context, withAppendedPath, context.getContentResolver().query(withAppendedPath, (String[]) null, (String) null, (String[]) null, (String) null));
                            }
                        }
                        if (callerInfo4 != this.callerInfo) {
                            this.callerInfo = callerInfo4;
                            Bindings.m34d(this, "#####async contact look up with numeric username" + this.callerInfo);
                        }
                        this.callerInfo.countryIso = cookieWrapper.countryIso;
                        if (TextUtils.isEmpty(this.callerInfo.name)) {
                            CallerInfo callerInfo5 = this.callerInfo;
                            Context context2 = this.queryContext;
                            String str2 = cookieWrapper.number;
                            if (!TextUtils.isEmpty(callerInfo5.phoneNumber)) {
                                str2 = callerInfo5.phoneNumber;
                            }
                            callerInfo5.geoDescription = PhoneNumberHelper.getGeoDescription(context2, str2, callerInfo5.countryIso);
                        }
                        if (!TextUtils.isEmpty(cookieWrapper.number)) {
                            this.callerInfo.phoneNumber = cookieWrapper.number;
                        }
                    }
                    Bindings.m34d(this, "constructing CallerInfo object for token: " + i);
                    OnQueryCompleteListener onQueryCompleteListener = cookieWrapper.listener;
                    if (onQueryCompleteListener != null) {
                        onQueryCompleteListener.onDataLoaded(i, cookieWrapper.cookie, this.callerInfo);
                    }
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
    }

    private static final class CookieWrapper {
        public Object cookie;
        public String countryIso;
        public int event;
        public OnQueryCompleteListener listener;
        public String number;

        private CookieWrapper() {
        }

        /* synthetic */ CookieWrapper(C06291 r1) {
        }
    }

    private static final class DirectoryQueryCompleteListenerFactory {
        private final Context context;
        private int count;
        private boolean isListenerCalled = false;
        /* access modifiers changed from: private */
        public final OnQueryCompleteListener listener;

        private class DirectoryQueryCompleteListener implements OnQueryCompleteListener {
            private final long directoryId;

            DirectoryQueryCompleteListener(long j) {
                this.directoryId = j;
            }

            public void onDataLoaded(int i, Object obj, CallerInfo callerInfo) {
                DirectoryQueryCompleteListenerFactory.this.listener.onDataLoaded(i, obj, callerInfo);
            }

            public void onQueryComplete(int i, Object obj, CallerInfo callerInfo) {
                DirectoryQueryCompleteListenerFactory.this.onDirectoryQueryComplete(i, obj, callerInfo, this.directoryId);
            }
        }

        DirectoryQueryCompleteListenerFactory(Context context2, int i, OnQueryCompleteListener onQueryCompleteListener) {
            this.count = i;
            this.listener = onQueryCompleteListener;
            this.context = context2;
        }

        /* access modifiers changed from: private */
        public void onDirectoryQueryComplete(int i, Object obj, CallerInfo callerInfo, long j) {
            boolean z;
            synchronized (this) {
                z = true;
                this.count--;
                if (this.isListenerCalled || (!callerInfo.contactExists && this.count != 0)) {
                    z = false;
                } else {
                    this.isListenerCalled = true;
                }
            }
            if (z && this.listener != null) {
                PhoneNumberCache.get(this.context).getCachedNumberLookupService();
                boolean z2 = callerInfo.contactExists;
                this.listener.onQueryComplete(i, obj, callerInfo);
            }
        }
    }

    interface OnQueryCompleteListener {
        void onDataLoaded(int i, Object obj, CallerInfo callerInfo);

        void onQueryComplete(int i, Object obj, CallerInfo callerInfo);
    }

    private static class QueryPoolException extends SQLException {
        QueryPoolException(String str) {
            super(str);
        }
    }

    static /* synthetic */ boolean access$000(int i, Context context, CallerInfo callerInfo, OnQueryCompleteListener onQueryCompleteListener, Object obj) {
        Context context2 = context;
        Trace.beginSection("CallerInfoAsyncQuery.startOtherDirectoriesQuery");
        if (r8 == 0) {
            Trace.endSection();
            return false;
        }
        OnQueryCompleteListener onQueryCompleteListener2 = onQueryCompleteListener;
        DirectoryQueryCompleteListenerFactory directoryQueryCompleteListenerFactory = new DirectoryQueryCompleteListenerFactory(context, r8, onQueryCompleteListener);
        for (long j : (long[]) StrictModeUtils.bypass(new Supplier(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final Object get() {
                return CallerInfoAsyncQuery.lambda$startOtherDirectoriesQuery$0(this.f$0);
            }
        })) {
            startQueryInternal(i, context, callerInfo, new DirectoryQueryCompleteListenerFactory.DirectoryQueryCompleteListener(j), obj, ContactInfoHelper.getContactInfoLookupUri(callerInfo.phoneNumber, j));
        }
        Trace.endSection();
        return true;
    }

    static /* synthetic */ String access$500(Uri uri) {
        if (uri == null) {
            return "";
        }
        String uri2 = uri.toString();
        int lastIndexOf = uri2.lastIndexOf(47);
        if (lastIndexOf <= 0) {
            return uri2;
        }
        return uri2.substring(0, lastIndexOf) + "/xxxxxxx";
    }

    static /* synthetic */ long[] lambda$startOtherDirectoriesQuery$0(Context context) {
        ArrayList arrayList = new ArrayList();
        Cursor query = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "directories_enterprise"), DIRECTORY_PROJECTION, (String) null, (String[]) null, (String) null);
        if (query != null) {
            int columnIndex = query.getColumnIndex("_id");
            while (query.moveToNext()) {
                long j = query.getLong(columnIndex);
                if (ContactsContract.Directory.isRemoteDirectoryId(j)) {
                    arrayList.add(Long.valueOf(j));
                }
            }
            query.close();
        }
        long[] jArr = new long[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            jArr[i] = ((Long) arrayList.get(i)).longValue();
        }
        return jArr;
    }

    static void startQuery(int i, final Context context, final CallerInfo callerInfo, final OnQueryCompleteListener onQueryCompleteListener, Object obj) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("- number: ");
        outline13.append(callerInfo.phoneNumber);
        outline13.toString();
        "- cookie: " + obj;
        startQueryInternal(i, context, callerInfo, new OnQueryCompleteListener() {
            public void onDataLoaded(int i, Object obj, CallerInfo callerInfo) {
                onQueryCompleteListener.onDataLoaded(i, obj, callerInfo);
            }

            public void onQueryComplete(int i, Object obj, CallerInfo callerInfo) {
                OnQueryCompleteListener onQueryCompleteListener;
                if (((callerInfo != null && callerInfo.contactExists) || !CallerInfoAsyncQuery.access$000(i, context, callerInfo, onQueryCompleteListener, obj)) && (onQueryCompleteListener = onQueryCompleteListener) != null && callerInfo != null) {
                    onQueryCompleteListener.onQueryComplete(i, obj, callerInfo);
                }
            }
        }, obj, ContactInfoHelper.getContactInfoLookupUri(callerInfo.phoneNumber, -1));
    }

    private static void startQueryInternal(int i, Context context, CallerInfo callerInfo, OnQueryCompleteListener onQueryCompleteListener, Object obj, Uri uri) {
        if (context == null || uri == null) {
            throw new QueryPoolException("Bad context or query uri.");
        }
        CallerInfoAsyncQueryHandler callerInfoAsyncQueryHandler = new CallerInfoAsyncQueryHandler(context, uri, (C06291) null);
        CookieWrapper cookieWrapper = new CookieWrapper((C06291) null);
        cookieWrapper.listener = onQueryCompleteListener;
        cookieWrapper.cookie = obj;
        String str = callerInfo.phoneNumber;
        cookieWrapper.number = str;
        cookieWrapper.countryIso = callerInfo.countryIso;
        if (PhoneNumberHelper.isLocalEmergencyNumber(context, str)) {
            cookieWrapper.event = 3;
        } else if (callerInfo.isVoiceMailNumber()) {
            cookieWrapper.event = 4;
        } else {
            cookieWrapper.event = 1;
        }
        callerInfoAsyncQueryHandler.startQuery(i, cookieWrapper, uri, CallerInfo.getDefaultPhoneLookupProjection(), (String) null, (String[]) null, (String) null);
    }
}
