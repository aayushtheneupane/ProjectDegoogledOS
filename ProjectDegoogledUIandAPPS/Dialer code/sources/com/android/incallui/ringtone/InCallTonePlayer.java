package com.android.incallui.ringtone;

import com.android.incallui.async.PausableExecutor;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class InCallTonePlayer {
    private final PausableExecutor executor;
    private CountDownLatch numPlayingTones;
    private final ToneGeneratorFactory toneGeneratorFactory;

    private static class ToneGeneratorInfo {
        public final int stream;
        public final int tone;
        public final int toneLengthMillis;
        public final int volume;

        public ToneGeneratorInfo(int i, int i2, int i3, int i4) {
            this.tone = i;
            this.volume = i2;
            this.toneLengthMillis = i3;
            this.stream = i4;
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("ToneGeneratorInfo{toneLengthMillis=");
            outline13.append(this.toneLengthMillis);
            outline13.append(", tone=");
            outline13.append(this.tone);
            outline13.append(", volume=");
            outline13.append(this.volume);
            outline13.append('}');
            return outline13.toString();
        }
    }

    public InCallTonePlayer(ToneGeneratorFactory toneGeneratorFactory2, PausableExecutor pausableExecutor) {
        this.toneGeneratorFactory = (ToneGeneratorFactory) Objects.requireNonNull(toneGeneratorFactory2);
        this.executor = (PausableExecutor) Objects.requireNonNull(pausableExecutor);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        if (r5 != null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        if (r5 == null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0058, code lost:
        r5.countDown();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        ((com.android.incallui.async.PausableExecutorImpl) r4.executor).milestone();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0062, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void playOnBackgroundThread(com.android.incallui.ringtone.InCallTonePlayer.ToneGeneratorInfo r5) {
        /*
            r4 = this;
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x004a }
            r1.<init>()     // Catch:{ InterruptedException -> 0x004a }
            java.lang.String r2 = "Starting tone "
            r1.append(r2)     // Catch:{ InterruptedException -> 0x004a }
            r1.append(r5)     // Catch:{ InterruptedException -> 0x004a }
            java.lang.String r1 = r1.toString()     // Catch:{ InterruptedException -> 0x004a }
            com.android.incallui.Bindings.m40v(r4, r1)     // Catch:{ InterruptedException -> 0x004a }
            com.android.incallui.ringtone.ToneGeneratorFactory r1 = r4.toneGeneratorFactory     // Catch:{ InterruptedException -> 0x004a }
            int r2 = r5.stream     // Catch:{ InterruptedException -> 0x004a }
            int r3 = r5.volume     // Catch:{ InterruptedException -> 0x004a }
            android.media.ToneGenerator r0 = r1.newInCallToneGenerator(r2, r3)     // Catch:{ InterruptedException -> 0x004a }
            int r1 = r5.tone     // Catch:{ InterruptedException -> 0x004a }
            r0.startTone(r1)     // Catch:{ InterruptedException -> 0x004a }
            com.android.incallui.async.PausableExecutor r1 = r4.executor     // Catch:{ InterruptedException -> 0x004a }
            com.android.incallui.async.PausableExecutorImpl r1 = (com.android.incallui.async.PausableExecutorImpl) r1
            r1.milestone()     // Catch:{ InterruptedException -> 0x004a }
            java.util.concurrent.CountDownLatch r1 = r4.numPlayingTones     // Catch:{ InterruptedException -> 0x004a }
            if (r1 == 0) goto L_0x0040
            java.util.concurrent.CountDownLatch r1 = r4.numPlayingTones     // Catch:{ InterruptedException -> 0x004a }
            int r5 = r5.toneLengthMillis     // Catch:{ InterruptedException -> 0x004a }
            long r2 = (long) r5     // Catch:{ InterruptedException -> 0x004a }
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x004a }
            r1.await(r2, r5)     // Catch:{ InterruptedException -> 0x004a }
            com.android.incallui.async.PausableExecutor r5 = r4.executor     // Catch:{ InterruptedException -> 0x004a }
            com.android.incallui.async.PausableExecutorImpl r5 = (com.android.incallui.async.PausableExecutorImpl) r5
            r5.milestone()     // Catch:{ InterruptedException -> 0x004a }
        L_0x0040:
            r0.release()
            java.util.concurrent.CountDownLatch r5 = r4.numPlayingTones
            if (r5 == 0) goto L_0x005b
            goto L_0x0058
        L_0x0048:
            r5 = move-exception
            goto L_0x0063
        L_0x004a:
            java.lang.String r5 = "Interrupted while playing in-call tone."
            com.android.incallui.Bindings.m42w(r4, r5)     // Catch:{ all -> 0x0048 }
            if (r0 == 0) goto L_0x0054
            r0.release()
        L_0x0054:
            java.util.concurrent.CountDownLatch r5 = r4.numPlayingTones
            if (r5 == 0) goto L_0x005b
        L_0x0058:
            r5.countDown()
        L_0x005b:
            com.android.incallui.async.PausableExecutor r4 = r4.executor
            com.android.incallui.async.PausableExecutorImpl r4 = (com.android.incallui.async.PausableExecutorImpl) r4
            r4.milestone()
            return
        L_0x0063:
            if (r0 == 0) goto L_0x0068
            r0.release()
        L_0x0068:
            java.util.concurrent.CountDownLatch r0 = r4.numPlayingTones
            if (r0 == 0) goto L_0x006f
            r0.countDown()
        L_0x006f:
            com.android.incallui.async.PausableExecutor r4 = r4.executor
            com.android.incallui.async.PausableExecutorImpl r4 = (com.android.incallui.async.PausableExecutorImpl) r4
            r4.milestone()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.ringtone.InCallTonePlayer.playOnBackgroundThread(com.android.incallui.ringtone.InCallTonePlayer$ToneGeneratorInfo):void");
    }

    public boolean isPlayingTone() {
        CountDownLatch countDownLatch = this.numPlayingTones;
        return countDownLatch != null && countDownLatch.getCount() > 0;
    }

    public void play(int i) {
        if (isPlayingTone()) {
            throw new IllegalStateException("Tone already playing");
        } else if (i == 4) {
            final ToneGeneratorInfo toneGeneratorInfo = new ToneGeneratorInfo(22, 80, Integer.MAX_VALUE, 0);
            this.numPlayingTones = new CountDownLatch(1);
            this.executor.execute(new Runnable() {
                public void run() {
                    InCallTonePlayer.this.playOnBackgroundThread(toneGeneratorInfo);
                }
            });
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline5("Bad tone: ", i));
        }
    }

    public void stop() {
        CountDownLatch countDownLatch = this.numPlayingTones;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }
}
