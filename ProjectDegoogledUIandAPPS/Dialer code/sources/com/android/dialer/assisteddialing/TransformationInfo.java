package com.android.dialer.assisteddialing;

import android.os.Bundle;
import com.android.dialer.assisteddialing.AutoValue_TransformationInfo;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class TransformationInfo {

    public static abstract class Builder {
        public abstract TransformationInfo build();

        public abstract Builder setOriginalNumber(String str);

        public abstract Builder setTransformedNumber(String str);

        public abstract Builder setTransformedNumberCountryCallingCode(int i);

        public abstract Builder setUserHomeCountryCode(String str);

        public abstract Builder setUserRoamingCountryCode(String str);
    }

    public static Builder builder() {
        return new AutoValue_TransformationInfo.Builder();
    }

    public abstract String originalNumber();

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("TRANSFORMATION_INFO_ORIGINAL_NUMBER", originalNumber());
        bundle.putString("TRANSFORMATION_INFO_TRANSFORMED_NUMBER", transformedNumber());
        bundle.putString("TRANSFORMATION_INFO_USER_HOME_COUNTRY_CODE", userHomeCountryCode());
        bundle.putString("TRANSFORMATION_INFO_USER_ROAMING_COUNTRY_CODE", userRoamingCountryCode());
        bundle.putInt("TRANSFORMED_NUMBER_COUNTRY_CALLING_CODE", transformedNumberCountryCallingCode());
        return bundle;
    }

    public abstract String transformedNumber();

    public abstract int transformedNumberCountryCallingCode();

    public abstract String userHomeCountryCode();

    public abstract String userRoamingCountryCode();
}
