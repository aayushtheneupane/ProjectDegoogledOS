package p000;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: ips */
/* compiled from: PG */
public final class ips {

    /* renamed from: a */
    private static final Logger f14631a = Logger.getLogger(ips.class.getName());

    private ips() {
    }

    /* renamed from: a */
    public static ipl m14309a(iqk iqk, iqk iqk2) {
        ipk ipk;
        try {
            Collection collection = (Collection) iqk2.mo2097a();
            if (collection.isEmpty()) {
                ipk = ipk.f14623c;
            } else {
                ipk = collection.size() == 1 ? new ipq((ipk) ife.m12903f((Iterable) collection)) : new ipn(collection);
            }
            return ipk.mo7603a(((ioi) iqk).f14599a);
        } catch (RuntimeException e) {
            f14631a.logp(Level.SEVERE, "dagger.producers.monitoring.internal.Monitors", "createMonitorForComponent", "RuntimeException while constructing monitor factories.", e);
            return ipl.f14624a;
        }
    }

    /* renamed from: a */
    public static void m14312a(RuntimeException runtimeException, ipk ipk, Object obj) {
        Logger logger = f14631a;
        Level level = Level.SEVERE;
        String valueOf = String.valueOf(ipk);
        String valueOf2 = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 100 + String.valueOf(valueOf2).length());
        sb.append("RuntimeException while calling ProductionComponentMonitor.Factory.create on factory ");
        sb.append(valueOf);
        sb.append(" with component ");
        sb.append(valueOf2);
        logger.logp(level, "dagger.producers.monitoring.internal.Monitors", "logCreateException", sb.toString(), runtimeException);
    }

    /* renamed from: a */
    public static void m14311a(RuntimeException runtimeException, ipg ipg, String str, Object obj) {
        Logger logger = f14631a;
        Level level = Level.SEVERE;
        String valueOf = String.valueOf(ipg);
        String valueOf2 = String.valueOf(obj);
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 65 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
        sb.append("RuntimeException while calling ProducerMonitor.");
        sb.append(str);
        sb.append(" on monitor ");
        sb.append(valueOf);
        sb.append(" with ");
        sb.append(valueOf2);
        logger.logp(level, "dagger.producers.monitoring.internal.Monitors", "logProducerMonitorArgMethodException", sb.toString(), runtimeException);
    }

    /* renamed from: a */
    public static void m14313a(RuntimeException runtimeException, ipl ipl, iph iph) {
        Logger logger = f14631a;
        Level level = Level.SEVERE;
        String valueOf = String.valueOf(ipl);
        String valueOf2 = String.valueOf(iph);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 100 + String.valueOf(valueOf2).length());
        sb.append("RuntimeException while calling ProductionComponentMonitor.producerMonitorFor on monitor ");
        sb.append(valueOf);
        sb.append(" with token ");
        sb.append(valueOf2);
        logger.logp(level, "dagger.producers.monitoring.internal.Monitors", "logProducerMonitorForException", sb.toString(), runtimeException);
    }

    /* renamed from: a */
    public static void m14310a(RuntimeException runtimeException, ipg ipg, String str) {
        Logger logger = f14631a;
        Level level = Level.SEVERE;
        String valueOf = String.valueOf(ipg);
        StringBuilder sb = new StringBuilder(str.length() + 59 + String.valueOf(valueOf).length());
        sb.append("RuntimeException while calling ProducerMonitor.");
        sb.append(str);
        sb.append(" on monitor ");
        sb.append(valueOf);
        logger.logp(level, "dagger.producers.monitoring.internal.Monitors", "logProducerMonitorMethodException", sb.toString(), runtimeException);
    }
}
