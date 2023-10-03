package com.android.dialer.blocking;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.provider.BlockedNumberContract;
import android.telephony.PhoneNumberUtils;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.database.Selection;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public final class Blocking {

    public static final class BlockingFailedException extends Exception {
        BlockingFailedException(Throwable th) {
            super(th);
        }
    }

    static /* synthetic */ Void lambda$block$0(ImmutableCollection immutableCollection, String str, Context context) throws Exception {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator it = immutableCollection.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            ContentValues contentValues = new ContentValues();
            contentValues.put("original_number", str2);
            String formatNumberToE164 = PhoneNumberUtils.formatNumberToE164(str2, str);
            if (formatNumberToE164 != null) {
                contentValues.put("e164_number", formatNumberToE164);
            }
            arrayList.add(ContentProviderOperation.newInsert(BlockedNumberContract.BlockedNumbers.CONTENT_URI).withValues(contentValues).build());
        }
        try {
            context.getContentResolver().applyBatch("com.android.blockednumber", arrayList);
            return null;
        } catch (OperationApplicationException | RemoteException | SecurityException e) {
            throw new BlockingFailedException(e);
        }
    }

    static /* synthetic */ Void lambda$unblock$1(ImmutableCollection immutableCollection, String str, Context context) throws Exception {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator it = immutableCollection.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            Selection is = Selection.column("original_number").mo5867is("=", str2);
            String formatNumberToE164 = PhoneNumberUtils.formatNumberToE164(str2, str);
            if (formatNumberToE164 != null) {
                Selection.Builder buildUpon = is.buildUpon();
                buildUpon.mo5864or(Selection.column("e164_number").mo5867is("=", formatNumberToE164));
                is = buildUpon.build();
            }
            arrayList.add(ContentProviderOperation.newDelete(BlockedNumberContract.BlockedNumbers.CONTENT_URI).withSelection(is.getSelection(), is.getSelectionArgs()).build());
        }
        try {
            context.getContentResolver().applyBatch("com.android.blockednumber", arrayList);
            return null;
        } catch (OperationApplicationException | RemoteException | SecurityException e) {
            throw new BlockingFailedException(e);
        }
    }

    public static ListenableFuture<Void> unblock(Context context, ImmutableCollection<String> immutableCollection, String str) {
        return DialerExecutorComponent.get(context).backgroundExecutor().submit(new Callable(str, context) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ Context f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                Blocking.lambda$unblock$1(ImmutableCollection.this, this.f$1, this.f$2);
                return null;
            }
        });
    }
}
