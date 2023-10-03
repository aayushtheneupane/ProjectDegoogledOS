package com.android.incallui;

import android.content.ContentValues;
import android.content.Context;
import android.support.p002v7.appcompat.R$style;
import android.telecom.Call;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.dialer.phonelookup.database.contract.PhoneLookupHistoryContract;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.FutureCallback;

class PhoneLookupHistoryRecorder$1 implements FutureCallback<PhoneLookupInfo> {
    final /* synthetic */ Context val$appContext;
    final /* synthetic */ Call val$call;

    PhoneLookupHistoryRecorder$1(Context context, Call call) {
        this.val$appContext = context;
        this.val$call = call;
    }

    public void onFailure(Throwable th) {
        LogUtil.m10w("PhoneLookupHistoryRecorder.onFailure", "could not write PhoneLookupHistory", th);
    }

    public void onSuccess(Object obj) {
        Optional optional;
        PhoneLookupInfo phoneLookupInfo = (PhoneLookupInfo) obj;
        Assert.checkArgument(phoneLookupInfo != null);
        Context context = this.val$appContext;
        Call call = this.val$call;
        Assert.isWorkerThread();
        Assert.isWorkerThread();
        String number = R$style.getNumber(call);
        if (TextUtils.isEmpty(number)) {
            optional = Optional.absent();
        } else {
            optional = Optional.fromNullable(PhoneNumberUtils.formatNumberToE164(number, R$style.getCurrentCountryIso(context)));
        }
        if (!optional.isPresent()) {
            String number2 = R$style.getNumber(call);
            if (TextUtils.isEmpty(number2)) {
                optional = Optional.absent();
            } else {
                optional = Optional.m67of(PhoneNumberUtils.normalizeNumber(number2));
            }
        }
        if (!optional.isPresent()) {
            LogUtil.m10w("PhoneLookupHistoryRecorder.onSuccess", "couldn't get a number", new Object[0]);
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone_lookup_info", phoneLookupInfo.toByteArray());
        contentValues.put("last_modified", Long.valueOf(System.currentTimeMillis()));
        this.val$appContext.getContentResolver().update(PhoneLookupHistoryContract.PhoneLookupHistory.contentUriForNumber((String) optional.get()), contentValues, (String) null, (String[]) null);
    }
}
