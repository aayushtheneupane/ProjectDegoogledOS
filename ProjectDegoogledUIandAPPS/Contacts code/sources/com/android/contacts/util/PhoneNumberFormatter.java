package com.android.contacts.util;

import android.content.Context;
import android.os.AsyncTask;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.widget.TextView;
import com.android.contacts.GeoUtil;
import com.android.contacts.compat.PhoneNumberFormattingTextWatcherCompat;

public final class PhoneNumberFormatter {

    private static class TextWatcherLoadAsyncTask extends AsyncTask<Void, Void, PhoneNumberFormattingTextWatcher> {
        private final String mCountryCode;
        private final boolean mFormatAfterWatcherSet;
        private final TextView mTextView;

        public TextWatcherLoadAsyncTask(String str, TextView textView, boolean z) {
            this.mCountryCode = str;
            this.mTextView = textView;
            this.mFormatAfterWatcherSet = z;
        }

        /* access modifiers changed from: protected */
        public PhoneNumberFormattingTextWatcher doInBackground(Void... voidArr) {
            return PhoneNumberFormattingTextWatcherCompat.newInstance(this.mCountryCode);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(PhoneNumberFormattingTextWatcher phoneNumberFormattingTextWatcher) {
            if (phoneNumberFormattingTextWatcher != null && !isCancelled()) {
                this.mTextView.addTextChangedListener(phoneNumberFormattingTextWatcher);
                if (this.mFormatAfterWatcherSet) {
                    phoneNumberFormattingTextWatcher.afterTextChanged(this.mTextView.getEditableText());
                }
            }
        }
    }

    public static final void setPhoneNumberFormattingTextWatcher(Context context, TextView textView, boolean z) {
        new TextWatcherLoadAsyncTask(GeoUtil.getCurrentCountryIso(context), textView, z).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[]) null);
    }
}
