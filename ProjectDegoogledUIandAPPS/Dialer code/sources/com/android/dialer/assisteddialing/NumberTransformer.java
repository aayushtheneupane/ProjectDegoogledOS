package com.android.dialer.assisteddialing;

import android.text.TextUtils;
import com.android.dialer.assisteddialing.AutoValue_TransformationInfo;
import com.android.dialer.common.LogUtil;
import com.android.dialer.function.Supplier;
import com.android.dialer.strictmode.StrictModeUtils;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber$PhoneNumber;
import java.util.Optional;

final class NumberTransformer {
    private final Constraints constraints;
    private final PhoneNumberUtil phoneNumberUtil = ((PhoneNumberUtil) StrictModeUtils.bypass($$Lambda$XWIX271cUXtGdJ1QbbwlCydZkzg.INSTANCE));

    NumberTransformer(Constraints constraints2) {
        this.constraints = constraints2;
    }

    /* access modifiers changed from: package-private */
    public Optional<TransformationInfo> doAssistedDialingTransformation(String str, String str2, String str3) {
        if (!this.constraints.meetsPreconditions(str, str2, str3)) {
            LogUtil.m9i("NumberTransformer.doAssistedDialingTransformation", "assisted dialing failed preconditions", new Object[0]);
            return Optional.empty();
        }
        Phonenumber$PhoneNumber phonenumber$PhoneNumber = (Phonenumber$PhoneNumber) StrictModeUtils.bypass(new Supplier(str, str2) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object get() {
                return NumberTransformer.this.lambda$doAssistedDialingTransformation$0$NumberTransformer(this.f$1, this.f$2);
            }
        });
        if (phonenumber$PhoneNumber == null) {
            return Optional.empty();
        }
        String str4 = (String) StrictModeUtils.bypass(new Supplier(phonenumber$PhoneNumber, str3) {
            private final /* synthetic */ Phonenumber$PhoneNumber f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object get() {
                return NumberTransformer.this.lambda$doAssistedDialingTransformation$1$NumberTransformer(this.f$1, this.f$2);
            }
        });
        if (TextUtils.isEmpty(str4)) {
            LogUtil.m9i("NumberTransformer.doAssistedDialingTransformation", "formatNumberForMobileDialing returned an empty string", new Object[0]);
            return Optional.empty();
        }
        AutoValue_TransformationInfo.Builder builder = new AutoValue_TransformationInfo.Builder();
        builder.setOriginalNumber(str);
        builder.setTransformedNumber(str4);
        builder.setUserHomeCountryCode(str2);
        builder.setUserRoamingCountryCode(str3);
        builder.setTransformedNumberCountryCallingCode(phonenumber$PhoneNumber.getCountryCode());
        return Optional.of(builder.build());
    }

    public /* synthetic */ Phonenumber$PhoneNumber lambda$doAssistedDialingTransformation$0$NumberTransformer(String str, String str2) {
        try {
            return this.phoneNumberUtil.parse(str, str2);
        } catch (NumberParseException unused) {
            LogUtil.m9i("NumberTransformer.doAssistedDialingTransformation", "number failed to parse", new Object[0]);
            return null;
        }
    }

    public /* synthetic */ String lambda$doAssistedDialingTransformation$1$NumberTransformer(Phonenumber$PhoneNumber phonenumber$PhoneNumber, String str) {
        return this.phoneNumberUtil.formatNumberForMobileDialing(phonenumber$PhoneNumber, str, true);
    }
}
