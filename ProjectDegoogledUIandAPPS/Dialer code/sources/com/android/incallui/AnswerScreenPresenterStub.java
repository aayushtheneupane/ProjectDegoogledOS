package com.android.incallui;

import com.android.incallui.answer.protocol.AnswerScreenDelegate;
import com.android.incallui.incalluilock.InCallUiLock;

public class AnswerScreenPresenterStub implements AnswerScreenDelegate {
    public InCallUiLock acquireInCallUiLock(String str) {
        return InCallPresenter.getInstance().acquireInCallUiLock(str);
    }

    public boolean isActionTimeout() {
        return false;
    }

    public void onAnswer(boolean z) {
    }

    public void onAnswerAndReleaseButtonDisabled() {
    }

    public void onAnswerAndReleaseButtonEnabled() {
    }

    public void onAnswerAndReleaseCall() {
    }

    public void onAnswerScreenUnready() {
    }

    public void onReject() {
    }

    public void onRejectCallWithMessage(String str) {
    }

    public void onSpeakEasyCall() {
    }

    public void updateWindowBackgroundColor(float f) {
    }
}
