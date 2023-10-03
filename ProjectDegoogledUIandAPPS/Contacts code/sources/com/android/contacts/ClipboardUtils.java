package com.android.contacts;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ClipboardUtils {
    public static void copyText(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z) {
        if (!TextUtils.isEmpty(charSequence2)) {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
            if (charSequence == null) {
                charSequence = "";
            }
            clipboardManager.setPrimaryClip(ClipData.newPlainText(charSequence, charSequence2));
            if (z) {
                Toast.makeText(context, context.getString(R.string.toast_text_copied), 0).show();
            }
        }
    }
}
