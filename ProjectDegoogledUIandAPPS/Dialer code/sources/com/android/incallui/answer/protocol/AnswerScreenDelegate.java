package com.android.incallui.answer.protocol;

import com.android.incallui.incalluilock.InCallUiLock;

public interface AnswerScreenDelegate {
    InCallUiLock acquireInCallUiLock(String str);

    boolean isActionTimeout();

    void onAnswer(boolean z);

    void onAnswerAndReleaseButtonDisabled();

    void onAnswerAndReleaseButtonEnabled();

    void onAnswerAndReleaseCall();

    void onAnswerScreenUnready();

    void onReject();

    void onRejectCallWithMessage(String str);

    void onSpeakEasyCall();

    void updateWindowBackgroundColor(float f);
}
