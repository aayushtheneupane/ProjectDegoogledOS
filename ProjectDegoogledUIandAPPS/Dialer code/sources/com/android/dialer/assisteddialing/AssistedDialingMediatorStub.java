package com.android.dialer.assisteddialing;

import java.util.Optional;

public final class AssistedDialingMediatorStub implements AssistedDialingMediator {
    public Optional<TransformationInfo> attemptAssistedDial(String str) {
        return Optional.empty();
    }

    public boolean isPlatformEligible() {
        return false;
    }

    public Optional<String> userHomeCountryCode() {
        return Optional.empty();
    }
}
