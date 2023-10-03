package com.android.settings.accounts;

import android.content.Context;
import android.icu.text.ListFormatter;
import android.os.UserHandle;
import android.text.BidiFormatter;
import android.text.TextUtils;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.accounts.AuthenticatorHelper;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;

public class TopLevelAccountEntryPreferenceController extends BasePreferenceController {
    public int getAvailabilityStatus() {
        return 1;
    }

    public TopLevelAccountEntryPreferenceController(Context context, String str) {
        super(context, str);
    }

    public CharSequence getSummary() {
        AuthenticatorHelper authenticatorHelper = new AuthenticatorHelper(this.mContext, UserHandle.of(UserHandle.myUserId()), (AuthenticatorHelper.OnAccountsUpdateListener) null);
        String[] enabledAccountTypes = authenticatorHelper.getEnabledAccountTypes();
        BidiFormatter instance = BidiFormatter.getInstance();
        ArrayList arrayList = new ArrayList();
        if (enabledAccountTypes == null || enabledAccountTypes.length == 0) {
            arrayList.add(this.mContext.getString(C1715R.string.account_dashboard_default_summary));
        } else {
            int min = Math.min(3, enabledAccountTypes.length);
            for (int i = 0; i < enabledAccountTypes.length && min > 0; i++) {
                CharSequence labelForType = authenticatorHelper.getLabelForType(this.mContext, enabledAccountTypes[i]);
                if (!TextUtils.isEmpty(labelForType)) {
                    arrayList.add(instance.unicodeWrap(labelForType));
                    min--;
                }
            }
        }
        return ListFormatter.getInstance().format(arrayList);
    }
}
