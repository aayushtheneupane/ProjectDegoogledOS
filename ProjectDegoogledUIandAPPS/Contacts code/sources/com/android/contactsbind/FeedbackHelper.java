package com.android.contactsbind;

import android.content.Context;
import android.util.Log;

public final class FeedbackHelper {
    public static void sendFeedback(Context context, String str, String str2, Throwable th) {
        if (str2 == null) {
            str2 = th.getMessage();
        }
        Log.e(str, str2, th);
    }
}
