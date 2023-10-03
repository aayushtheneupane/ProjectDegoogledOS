package com.android.voicemail.impl.scheduling;

import android.os.Bundle;

public interface Policy {
    void onBeforeExecute();

    void onCompleted();

    void onCreate(BaseTask baseTask, Bundle bundle);

    void onDuplicatedTaskAdded();

    void onFail();
}
