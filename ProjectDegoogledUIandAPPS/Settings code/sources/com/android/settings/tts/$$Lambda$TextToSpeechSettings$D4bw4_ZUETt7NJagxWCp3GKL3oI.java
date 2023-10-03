package com.android.settings.tts;

import android.util.Pair;
import java.util.Comparator;

/* renamed from: com.android.settings.tts.-$$Lambda$TextToSpeechSettings$D4bw4_ZUETt7NJagxWCp3GKL3oI  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$TextToSpeechSettings$D4bw4_ZUETt7NJagxWCp3GKL3oI implements Comparator {
    public static final /* synthetic */ $$Lambda$TextToSpeechSettings$D4bw4_ZUETt7NJagxWCp3GKL3oI INSTANCE = new $$Lambda$TextToSpeechSettings$D4bw4_ZUETt7NJagxWCp3GKL3oI();

    private /* synthetic */ $$Lambda$TextToSpeechSettings$D4bw4_ZUETt7NJagxWCp3GKL3oI() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((String) ((Pair) obj).first).compareToIgnoreCase((String) ((Pair) obj2).first);
    }
}
