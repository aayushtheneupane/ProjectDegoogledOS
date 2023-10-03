package com.android.incallui.answer.impl.hint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public interface AnswerHint {
    void onAnswered();

    void onBounceEnd();

    void onBounceStart();

    void onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, View view, TextView textView);
}
