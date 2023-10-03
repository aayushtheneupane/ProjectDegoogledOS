package p000;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: hyk */
/* compiled from: PG */
public enum hyk {
    TIME_HOUR_OF_DAY_PADDED('H'),
    TIME_HOUR_OF_DAY('k'),
    TIME_HOUR_12H_PADDED('I'),
    TIME_HOUR_12H('l'),
    TIME_MINUTE_OF_HOUR_PADDED('M'),
    TIME_SECONDS_OF_MINUTE_PADDED('S'),
    TIME_MILLIS_OF_SECOND_PADDED('L'),
    TIME_NANOS_OF_SECOND_PADDED('N'),
    TIME_AM_PM('p'),
    TIME_TZ_NUMERIC('z'),
    TIME_TZ_SHORT('Z'),
    TIME_EPOCH_SECONDS('s'),
    TIME_EPOCH_MILLIS('Q'),
    DATE_MONTH_FULL('B'),
    DATE_MONTH_SHORT('b'),
    DATE_MONTH_SHORT_ALT('h'),
    DATE_DAY_FULL('A'),
    DATE_DAY_SHORT('a'),
    DATE_CENTURY_PADDED('C'),
    DATE_YEAR_PADDED('Y'),
    DATE_YEAR_OF_CENTURY_PADDED('y'),
    DATE_DAY_OF_YEAR_PADDED('j'),
    DATE_MONTH_PADDED('m'),
    DATE_DAY_OF_MONTH_PADDED('d'),
    DATE_DAY_OF_MONTH('e'),
    DATETIME_HOURS_MINUTES('R'),
    DATETIME_HOURS_MINUTES_SECONDS('T'),
    DATETIME_HOURS_MINUTES_SECONDS_12H('r'),
    DATETIME_MONTH_DAY_YEAR('D'),
    DATETIME_YEAR_MONTH_DAY('F'),
    DATETIME_FULL('c');
    

    /* renamed from: a */
    public static final Map f13627a = null;

    /* renamed from: b */
    public final char f13652b;

    static {
        int i;
        HashMap hashMap = new HashMap();
        hyk[] values = values();
        int length = values.length;
        while (i < length) {
            hyk hyk = values[i];
            if (hashMap.put(Character.valueOf(hyk.f13652b), hyk) == null) {
                i++;
            } else {
                String valueOf = String.valueOf(hyk);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 28);
                sb.append("duplicate format character: ");
                sb.append(valueOf);
                throw new IllegalStateException(sb.toString());
            }
        }
        f13627a = Collections.unmodifiableMap(hashMap);
    }

    private hyk(char c) {
        this.f13652b = c;
    }
}
