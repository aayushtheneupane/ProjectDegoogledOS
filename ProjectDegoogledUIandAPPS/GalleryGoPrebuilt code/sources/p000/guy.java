package p000;

import android.support.p002v7.widget.RecyclerView;
import java.util.concurrent.TimeUnit;

/* renamed from: guy */
/* compiled from: PG */
public enum guy {
    DONT_CARE(RecyclerView.FOREVER_NS),
    SAME_WEEK(TimeUnit.MILLISECONDS.convert(7, TimeUnit.DAYS)),
    SAME_DAY(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)),
    FEW_HOURS(TimeUnit.MILLISECONDS.convert(4, TimeUnit.HOURS)),
    ONE_HOUR(TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)),
    HALF_HOUR(TimeUnit.MILLISECONDS.convert(30, TimeUnit.MINUTES)),
    TEN_MINUTES(TimeUnit.MILLISECONDS.convert(10, TimeUnit.MINUTES)),
    FEW_MINUTES(TimeUnit.MILLISECONDS.convert(4, TimeUnit.MINUTES)),
    ONE_MINUTE(TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES)),
    FEW_SECONDS(TimeUnit.MILLISECONDS.convert(4, TimeUnit.SECONDS));
    

    /* renamed from: b */
    public final long f12113b;

    private guy(long j) {
        this.f12113b = j;
    }
}
