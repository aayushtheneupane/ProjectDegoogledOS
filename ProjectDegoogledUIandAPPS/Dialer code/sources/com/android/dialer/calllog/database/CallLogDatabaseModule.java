package com.android.dialer.calllog.database;

import android.content.ContentValues;
import com.android.dialer.common.Assert;
import java.util.function.Predicate;

public class CallLogDatabaseModule {
    public static void check(ContentValues contentValues, int i) {
        checkBooleanColumn("is_read", contentValues, i);
        checkBooleanColumn("new", contentValues, i);
        checkBooleanColumn("is_voicemail_call", contentValues, i);
        checkColumn("call_type", contentValues, i, $$Lambda$CallLogDatabaseModule$AfKA6tmxPWyegMYETUquh9sWXds.INSTANCE);
    }

    private static void checkBooleanColumn(String str, ContentValues contentValues, int i) {
        checkColumn(str, contentValues, i, new Predicate(str) {
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final boolean test(Object obj) {
                return CallLogDatabaseModule.lambda$checkBooleanColumn$0(this.f$0, (ContentValues) obj);
            }
        });
    }

    private static void checkColumn(String str, ContentValues contentValues, int i, Predicate<ContentValues> predicate) {
        if (i != 1) {
            if (i != 2) {
                throw new UnsupportedOperationException(String.format("Unsupported operation: %s", new Object[]{Integer.valueOf(i)}));
            } else if (!contentValues.containsKey(str)) {
                return;
            }
        }
        Assert.checkArgument(predicate.test(contentValues), "Column %s contains invalid value: %s", str, contentValues.get(str));
    }

    static /* synthetic */ boolean lambda$checkBooleanColumn$0(String str, ContentValues contentValues) {
        Integer asInteger = contentValues.getAsInteger(str);
        if (asInteger == null || (asInteger.intValue() != 0 && asInteger.intValue() != 1)) {
            return false;
        }
        return true;
    }

    static /* synthetic */ boolean lambda$checkCallTypeColumn$1(ContentValues contentValues) {
        Integer asInteger = contentValues.getAsInteger("call_type");
        if (asInteger == null || (asInteger.intValue() != 1 && asInteger.intValue() != 2 && asInteger.intValue() != 3 && asInteger.intValue() != 4 && asInteger.intValue() != 5 && asInteger.intValue() != 6 && asInteger.intValue() != 7)) {
            return false;
        }
        return true;
    }
}
