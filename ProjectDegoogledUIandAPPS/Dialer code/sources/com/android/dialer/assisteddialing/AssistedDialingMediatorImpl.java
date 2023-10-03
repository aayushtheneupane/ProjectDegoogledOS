package com.android.dialer.assisteddialing;

import com.android.dialer.common.LogUtil;
import java.util.Optional;

final class AssistedDialingMediatorImpl implements AssistedDialingMediator {
    private final LocationDetector locationDetector;
    private final NumberTransformer numberTransformer;

    AssistedDialingMediatorImpl(LocationDetector locationDetector2, NumberTransformer numberTransformer2) {
        if (locationDetector2 == null) {
            throw new NullPointerException("locationDetector was null");
        } else if (numberTransformer2 != null) {
            this.locationDetector = locationDetector2;
            this.numberTransformer = numberTransformer2;
        } else {
            throw new NullPointerException("numberTransformer was null");
        }
    }

    public Optional<TransformationInfo> attemptAssistedDial(String str) {
        Optional<String> upperCaseUserHomeCountry = this.locationDetector.getUpperCaseUserHomeCountry();
        Optional<String> upperCaseUserRoamingCountry = this.locationDetector.getUpperCaseUserRoamingCountry();
        if (upperCaseUserHomeCountry.isPresent() && upperCaseUserRoamingCountry.isPresent()) {
            return this.numberTransformer.doAssistedDialingTransformation(str, upperCaseUserHomeCountry.get(), upperCaseUserRoamingCountry.get());
        }
        LogUtil.m9i("AssistedDialingMediator.attemptAssistedDial", "Unable to determine country codes", new Object[0]);
        return Optional.empty();
    }

    public boolean isPlatformEligible() {
        return true;
    }

    public Optional<String> userHomeCountryCode() {
        return this.locationDetector.getUpperCaseUserHomeCountry();
    }
}
