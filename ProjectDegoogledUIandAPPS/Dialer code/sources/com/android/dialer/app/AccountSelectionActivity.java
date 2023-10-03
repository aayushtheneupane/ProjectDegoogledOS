package com.android.dialer.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.text.TextUtils;
import com.android.contacts.common.widget.SelectPhoneAccountDialogFragment;
import com.android.contacts.common.widget.SelectPhoneAccountDialogOptions;
import com.android.dialer.R;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.util.CallUtil;
import java.util.ArrayList;
import java.util.List;

public class AccountSelectionActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public CallInitiationType$Type initiationType;
    private SelectPhoneAccountDialogFragment.SelectPhoneAccountListener listener = new SelectPhoneAccountDialogFragment.SelectPhoneAccountListener() {
        public void onDialogDismissed(String str) {
            AccountSelectionActivity.this.finish();
        }

        public void onPhoneAccountSelected(PhoneAccountHandle phoneAccountHandle, boolean z, String str) {
            AccountSelectionActivity.this.startActivity(new CallIntentBuilder(AccountSelectionActivity.this.number, AccountSelectionActivity.this.initiationType).setPhoneAccountHandle(phoneAccountHandle).build());
            AccountSelectionActivity.this.finish();
        }
    };
    /* access modifiers changed from: private */
    public String number;

    public static Intent createIntent(Context context, String str, CallInitiationType$Type callInitiationType$Type) {
        List<PhoneAccount> callCapablePhoneAccounts;
        if (TextUtils.isEmpty(str) || (callCapablePhoneAccounts = CallUtil.getCallCapablePhoneAccounts(context, "tel")) == null || callCapablePhoneAccounts.size() <= 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (PhoneAccount accountHandle : callCapablePhoneAccounts) {
            arrayList.add(accountHandle.getAccountHandle());
        }
        return new Intent(context, AccountSelectionActivity.class).putExtra("number", str).putExtra("accountHandles", arrayList).putExtra("type", callInitiationType$Type.ordinal());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.number = getIntent().getStringExtra("number");
        this.initiationType = CallInitiationType$Type.values()[getIntent().getIntExtra("type", 0)];
        if (getFragmentManager().findFragmentByTag("dialog") == null) {
            SelectPhoneAccountDialogOptions.Builder builderWithAccounts = R$style.builderWithAccounts(getIntent().getParcelableArrayListExtra("accountHandles"));
            builderWithAccounts.setTitle(R.string.call_via_dialog_title);
            builderWithAccounts.setCanSetDefault(false);
            SelectPhoneAccountDialogFragment.newInstance((SelectPhoneAccountDialogOptions) builderWithAccounts.build(), this.listener).show(getFragmentManager(), "dialog");
        }
    }
}
