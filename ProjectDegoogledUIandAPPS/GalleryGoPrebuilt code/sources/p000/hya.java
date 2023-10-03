package p000;

import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* renamed from: hya */
/* compiled from: PG */
public final class hya extends hxa {

    /* renamed from: a */
    private static volatile boolean f13608a = false;

    /* renamed from: b */
    private final Logger f13609b;

    /* renamed from: a */
    public final String mo8243a() {
        return this.f13609b.getName();
    }

    /* renamed from: a */
    public final boolean mo7301a(Level level) {
        return this.f13609b.isLoggable(level);
    }

    /* renamed from: a */
    private final void m12438a(LogRecord logRecord, boolean z) {
        if (!z || this.f13609b.isLoggable(logRecord.getLevel())) {
            this.f13609b.log(logRecord);
            return;
        }
        Filter filter = this.f13609b.getFilter();
        if (filter != null) {
            filter.isLoggable(logRecord);
        }
        if (this.f13609b.getClass() != Logger.class && !f13608a) {
            Logger logger = Logger.getLogger(String.valueOf(this.f13609b.getName()).concat(".__forced__"));
            try {
                logger.setLevel(Level.ALL);
                logger.log(logRecord);
            } catch (SecurityException e) {
                f13608a = true;
                Logger.getLogger("").logp(Level.SEVERE, "com.google.common.flogger.backend.system.AbstractBackend", "forceLoggingViaChildLogger", "Forcing log statements with Flogger has been partially disabled.\nThe Flogger library cannot modify logger log levels, which is necessary to force log statements. This is likely due to an installed SecurityManager.\nForced log statements will still be published directly to log handlers, but will not be visible to the 'log(LogRecord)' method of Logger sub-classes.\n");
                m12439a(this.f13609b, logRecord);
            }
        } else {
            m12439a(this.f13609b, logRecord);
        }
    }

    /* renamed from: a */
    private static void m12439a(Logger logger, LogRecord logRecord) {
        Logger parent;
        for (Handler publish : logger.getHandlers()) {
            publish.publish(logRecord);
        }
        if (logger.getUseParentHandlers() && (parent = logger.getParent()) != null) {
            m12439a(parent, logRecord);
        }
    }

    public hya(Logger logger) {
        this.f13609b = logger;
    }

    /* renamed from: a */
    public final void mo7300a(RuntimeException runtimeException, hwz hwz) {
        m12438a((LogRecord) new hyb(runtimeException, hwz), hwz.mo8216k());
    }

    /* renamed from: a */
    public final void mo7299a(hwz hwz) {
        m12438a((LogRecord) new hyb(hwz, (byte[]) null), hwz.mo8216k());
    }
}
