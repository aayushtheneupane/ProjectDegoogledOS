package com.android.messaging.p041ui.appsettings;

import android.content.Context;
import android.preference.EditTextPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;
import androidx.core.text.BidiFormatter;
import androidx.core.text.TextDirectionHeuristicsCompat;
import com.android.messaging.R;
import com.android.messaging.util.C1474sa;

/* renamed from: com.android.messaging.ui.appsettings.PhoneNumberPreference */
public class PhoneNumberPreference extends EditTextPreference {

    /* renamed from: Xd */
    private String f1720Xd = "";
    private int mSubId;

    public PhoneNumberPreference(Context context, AttributeSet attributeSet) {
        super(new ContextThemeWrapper(context, R.style.BugleThemeDialog), attributeSet);
    }

    /* renamed from: b */
    public void mo7141b(String str, int i) {
        this.f1720Xd = str;
        this.mSubId = i;
    }

    /* access modifiers changed from: protected */
    public void onBindDialogView(View view) {
        super.onBindDialogView(view);
        if (TextUtils.isEmpty(getText()) && !TextUtils.isEmpty(this.f1720Xd)) {
            getEditText().setText(BidiFormatter.getInstance().unicodeWrap(C1474sa.get(this.mSubId).mo8222Ka(this.f1720Xd), TextDirectionHeuristicsCompat.LTR));
        }
        getEditText().setInputType(3);
    }

    /* access modifiers changed from: protected */
    public void onBindView(View view) {
        String str;
        String text = getText();
        if (TextUtils.isEmpty(text)) {
            text = this.f1720Xd;
        }
        if (!TextUtils.isEmpty(text)) {
            str = C1474sa.get(this.mSubId).mo8220Ia(text);
        } else {
            str = getContext().getString(R.string.unknown_phone_number_pref_display_value);
        }
        setSummary(BidiFormatter.getInstance().unicodeWrap(str, TextDirectionHeuristicsCompat.LTR));
        super.onBindView(view);
    }

    /* access modifiers changed from: protected */
    public void onDialogClosed(boolean z) {
        if (z && this.f1720Xd != null) {
            String obj = getEditText().getText().toString();
            C1474sa saVar = C1474sa.get(this.mSubId);
            if (saVar.mo8222Ka(obj).equals(saVar.mo8222Ka(this.f1720Xd))) {
                setText("");
                return;
            }
        }
        super.onDialogClosed(z);
    }

    public void setText(String str) {
        super.setText(str);
        notifyChanged();
    }
}
