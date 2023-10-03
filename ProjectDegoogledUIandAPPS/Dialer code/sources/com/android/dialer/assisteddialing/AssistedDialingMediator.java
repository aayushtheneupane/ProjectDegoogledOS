package com.android.dialer.assisteddialing;

import java.util.Optional;

public interface AssistedDialingMediator {
    Optional<TransformationInfo> attemptAssistedDial(String str);

    boolean isPlatformEligible();

    Optional<String> userHomeCountryCode();
}
