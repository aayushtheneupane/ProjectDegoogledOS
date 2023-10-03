package com.android.incallui.answer.impl.classifier;

import android.os.SystemClock;
import java.util.ArrayList;

class HistoryEvaluator {
    private final ArrayList<Data> gestureWeights = new ArrayList<>();
    private long lastUpdate = SystemClock.elapsedRealtime();
    private final ArrayList<Data> strokes = new ArrayList<>();

    private static class Data {
        public float weight = 1.0f;

        public Data(float f) {
        }
    }

    private void decayValue() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.lastUpdate;
        if (elapsedRealtime > j) {
            float pow = (float) Math.pow(0.8999999761581421d, (double) (((float) (elapsedRealtime - j)) / 50.0f));
            decayValue(this.strokes, pow);
            decayValue(this.gestureWeights, pow);
            this.lastUpdate = elapsedRealtime;
        }
    }

    public void addGesture(float f) {
        decayValue();
        this.gestureWeights.add(new Data(f));
    }

    public void addStroke(float f) {
        decayValue();
        this.strokes.add(new Data(f));
    }

    private void decayValue(ArrayList<Data> arrayList, float f) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList.get(i).weight *= f;
        }
        while (!arrayList.isEmpty()) {
            float f2 = arrayList.get(0).weight;
            if (f2 <= 1.0E-5f && f2 >= -1.0E-5f) {
                arrayList.remove(0);
            } else {
                return;
            }
        }
    }
}
