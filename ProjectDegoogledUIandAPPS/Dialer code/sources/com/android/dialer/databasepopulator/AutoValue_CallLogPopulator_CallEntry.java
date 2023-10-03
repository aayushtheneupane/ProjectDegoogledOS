package com.android.dialer.databasepopulator;

import com.android.dialer.databasepopulator.CallLogPopulator;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_CallLogPopulator_CallEntry extends CallLogPopulator.CallEntry {
    private final String number;
    private final int presentation;
    private final long timeMillis;
    private final int type;

    static final class Builder extends CallLogPopulator.CallEntry.Builder {
        private String number;
        private Integer presentation;
        private Long timeMillis;
        private Integer type;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public CallLogPopulator.CallEntry build() {
            String str = "";
            if (this.number == null) {
                str = GeneratedOutlineSupport.outline8(str, " number");
            }
            if (this.type == null) {
                str = GeneratedOutlineSupport.outline8(str, " type");
            }
            if (this.presentation == null) {
                str = GeneratedOutlineSupport.outline8(str, " presentation");
            }
            if (this.timeMillis == null) {
                str = GeneratedOutlineSupport.outline8(str, " timeMillis");
            }
            if (str.isEmpty()) {
                return new AutoValue_CallLogPopulator_CallEntry(this.number, this.type.intValue(), this.presentation.intValue(), this.timeMillis.longValue(), (C04681) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        /* access modifiers changed from: package-private */
        public CallLogPopulator.CallEntry.Builder setNumber(String str) {
            if (str != null) {
                this.number = str;
                return this;
            }
            throw new NullPointerException("Null number");
        }

        /* access modifiers changed from: package-private */
        public CallLogPopulator.CallEntry.Builder setPresentation(int i) {
            this.presentation = Integer.valueOf(i);
            return this;
        }

        /* access modifiers changed from: package-private */
        public CallLogPopulator.CallEntry.Builder setTimeMillis(long j) {
            this.timeMillis = Long.valueOf(j);
            return this;
        }

        /* access modifiers changed from: package-private */
        public CallLogPopulator.CallEntry.Builder setType(int i) {
            this.type = Integer.valueOf(i);
            return this;
        }
    }

    /* synthetic */ AutoValue_CallLogPopulator_CallEntry(String str, int i, int i2, long j, C04681 r6) {
        this.number = str;
        this.type = i;
        this.presentation = i2;
        this.timeMillis = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CallLogPopulator.CallEntry)) {
            return false;
        }
        CallLogPopulator.CallEntry callEntry = (CallLogPopulator.CallEntry) obj;
        if (this.number.equals(callEntry.getNumber()) && this.type == ((AutoValue_CallLogPopulator_CallEntry) callEntry).type && this.presentation == callEntry.getPresentation() && this.timeMillis == callEntry.getTimeMillis()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public String getNumber() {
        return this.number;
    }

    /* access modifiers changed from: package-private */
    public int getPresentation() {
        return this.presentation;
    }

    /* access modifiers changed from: package-private */
    public long getTimeMillis() {
        return this.timeMillis;
    }

    /* access modifiers changed from: package-private */
    public int getType() {
        return this.type;
    }

    public int hashCode() {
        long j = this.timeMillis;
        return ((int) (j ^ (j >>> 32))) ^ ((((((this.number.hashCode() ^ 1000003) * 1000003) ^ this.type) * 1000003) ^ this.presentation) * 1000003);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("CallEntry{number=");
        outline13.append(this.number);
        outline13.append(", type=");
        outline13.append(this.type);
        outline13.append(", presentation=");
        outline13.append(this.presentation);
        outline13.append(", timeMillis=");
        outline13.append(this.timeMillis);
        outline13.append("}");
        return outline13.toString();
    }
}
