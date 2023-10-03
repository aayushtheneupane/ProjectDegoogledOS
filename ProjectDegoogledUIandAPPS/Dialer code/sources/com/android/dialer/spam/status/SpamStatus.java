package com.android.dialer.spam.status;

import com.google.common.base.Optional;

public interface SpamStatus {
    Optional<Long> getTimestampMillis();

    boolean isSpam();
}
