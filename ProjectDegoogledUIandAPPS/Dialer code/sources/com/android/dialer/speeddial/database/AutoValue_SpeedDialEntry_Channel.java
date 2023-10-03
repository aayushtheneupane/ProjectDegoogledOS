package com.android.dialer.speeddial.database;

import com.android.dialer.speeddial.database.SpeedDialEntry;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_SpeedDialEntry_Channel extends SpeedDialEntry.Channel {
    private final String label;
    private final String number;
    private final int phoneType;
    private final int technology;

    static final class Builder extends SpeedDialEntry.Channel.Builder {
        private String label;
        private String number;
        private Integer phoneType;
        private Integer technology;

        Builder() {
        }

        public SpeedDialEntry.Channel build() {
            String str = "";
            if (this.number == null) {
                str = GeneratedOutlineSupport.outline8(str, " number");
            }
            if (this.phoneType == null) {
                str = GeneratedOutlineSupport.outline8(str, " phoneType");
            }
            if (this.label == null) {
                str = GeneratedOutlineSupport.outline8(str, " label");
            }
            if (this.technology == null) {
                str = GeneratedOutlineSupport.outline8(str, " technology");
            }
            if (str.isEmpty()) {
                return new AutoValue_SpeedDialEntry_Channel(this.number, this.phoneType.intValue(), this.label, this.technology.intValue(), (C05731) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public SpeedDialEntry.Channel.Builder setLabel(String str) {
            if (str != null) {
                this.label = str;
                return this;
            }
            throw new NullPointerException("Null label");
        }

        public SpeedDialEntry.Channel.Builder setNumber(String str) {
            if (str != null) {
                this.number = str;
                return this;
            }
            throw new NullPointerException("Null number");
        }

        public SpeedDialEntry.Channel.Builder setPhoneType(int i) {
            this.phoneType = Integer.valueOf(i);
            return this;
        }

        public SpeedDialEntry.Channel.Builder setTechnology(int i) {
            this.technology = Integer.valueOf(i);
            return this;
        }

        /* synthetic */ Builder(SpeedDialEntry.Channel channel, C05731 r2) {
            this.number = channel.number();
            this.phoneType = Integer.valueOf(channel.phoneType());
            this.label = channel.label();
            this.technology = Integer.valueOf(channel.technology());
        }
    }

    /* synthetic */ AutoValue_SpeedDialEntry_Channel(String str, int i, String str2, int i2, C05731 r5) {
        this.number = str;
        this.phoneType = i;
        this.label = str2;
        this.technology = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpeedDialEntry.Channel)) {
            return false;
        }
        SpeedDialEntry.Channel channel = (SpeedDialEntry.Channel) obj;
        if (this.number.equals(((AutoValue_SpeedDialEntry_Channel) channel).number)) {
            AutoValue_SpeedDialEntry_Channel autoValue_SpeedDialEntry_Channel = (AutoValue_SpeedDialEntry_Channel) channel;
            if (this.phoneType == autoValue_SpeedDialEntry_Channel.phoneType && this.label.equals(autoValue_SpeedDialEntry_Channel.label) && this.technology == autoValue_SpeedDialEntry_Channel.technology) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.technology ^ ((((((this.number.hashCode() ^ 1000003) * 1000003) ^ this.phoneType) * 1000003) ^ this.label.hashCode()) * 1000003);
    }

    public String label() {
        return this.label;
    }

    public String number() {
        return this.number;
    }

    public int phoneType() {
        return this.phoneType;
    }

    public int technology() {
        return this.technology;
    }

    public SpeedDialEntry.Channel.Builder toBuilder() {
        return new Builder(this, (C05731) null);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Channel{number=");
        outline13.append(this.number);
        outline13.append(", phoneType=");
        outline13.append(this.phoneType);
        outline13.append(", label=");
        outline13.append(this.label);
        outline13.append(", technology=");
        outline13.append(this.technology);
        outline13.append("}");
        return outline13.toString();
    }
}
