package com.android.contacts.common.util;

import java.util.ArrayList;

public class StopWatch {
    private final String mLabel;
    private final ArrayList<String> mLapLabels = new ArrayList<>();
    private final ArrayList<Long> mTimes = new ArrayList<>();

    private StopWatch(String str) {
        this.mLabel = str;
        lap("");
    }

    public static StopWatch start(String str) {
        return new StopWatch(str);
    }

    public void lap(String str) {
        this.mTimes.add(Long.valueOf(System.currentTimeMillis()));
        this.mLapLabels.add(str);
    }

    public void stopAndLog(String str, int i) {
        lap("");
        long longValue = this.mTimes.get(0).longValue();
        ArrayList<Long> arrayList = this.mTimes;
        int i2 = 1;
        long longValue2 = arrayList.get(arrayList.size() - 1).longValue() - longValue;
        if (longValue2 >= ((long) i)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mLabel);
            sb.append(",");
            sb.append(longValue2);
            sb.append(": ");
            while (i2 < this.mTimes.size()) {
                long longValue3 = this.mTimes.get(i2).longValue();
                sb.append(this.mLapLabels.get(i2));
                sb.append(",");
                sb.append(longValue3 - longValue);
                sb.append(" ");
                i2++;
                longValue = longValue3;
            }
            sb.toString();
        }
    }
}
