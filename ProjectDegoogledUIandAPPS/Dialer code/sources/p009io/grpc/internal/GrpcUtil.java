package p009io.grpc.internal;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import p009io.grpc.Metadata;
import p009io.grpc.internal.SharedResourceHolder;

/* renamed from: io.grpc.internal.GrpcUtil */
public final class GrpcUtil {
    public static final Metadata.Key<String> CONTENT_TYPE_KEY = Metadata.Key.m93of("content-type", Metadata.ASCII_STRING_MARSHALLER);
    private static final String IMPLEMENTATION_VERION;
    public static final boolean IS_RESTRICTED_APPENGINE = ("Production".equals(System.getProperty("com.google.appengine.runtime.environment")) && "1.7".equals(System.getProperty("java.specification.version")));
    public static final Metadata.Key<String> MESSAGE_ACCEPT_ENCODING_KEY = Metadata.Key.m93of("grpc-accept-encoding", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> MESSAGE_ENCODING_KEY = Metadata.Key.m93of("grpc-encoding", Metadata.ASCII_STRING_MARSHALLER);
    public static final SharedResourceHolder.Resource<ExecutorService> SHARED_CHANNEL_EXECUTOR = new SharedResourceHolder.Resource<ExecutorService>() {
        public void close(Object obj) {
            ((ExecutorService) obj).shutdown();
        }

        public Object create() {
            return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-default-executor-%d", true));
        }

        public String toString() {
            return "grpc-default-executor";
        }
    };
    static final Supplier<Stopwatch> STOPWATCH_SUPPLIER = new Supplier<Stopwatch>() {
        public Object get() {
            return Stopwatch.createUnstarted();
        }
    };
    public static final Metadata.Key<Long> TIMEOUT_KEY = Metadata.Key.m93of("grpc-timeout", new TimeoutMarshaller());
    public static final SharedResourceHolder.Resource<ScheduledExecutorService> TIMER_SERVICE = new SharedResourceHolder.Resource<ScheduledExecutorService>() {
        public void close(Object obj) {
            ((ScheduledExecutorService) obj).shutdown();
        }

        public Object create() {
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, GrpcUtil.getThreadFactory("grpc-timer-%d", true));
            try {
                newScheduledThreadPool.getClass().getMethod("setRemoveOnCancelPolicy", new Class[]{Boolean.TYPE}).invoke(newScheduledThreadPool, new Object[]{true});
            } catch (NoSuchMethodException unused) {
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
            return newScheduledThreadPool;
        }
    };
    public static final Metadata.Key<String> USER_AGENT_KEY = Metadata.Key.m93of("user-agent", Metadata.ASCII_STRING_MARSHALLER);

    /* renamed from: io.grpc.internal.GrpcUtil$TimeoutMarshaller */
    static class TimeoutMarshaller implements Metadata.AsciiMarshaller<Long> {
        private static final ImmutableMap<Character, TimeUnit> UNITS = ImmutableMap.builder().put('n', TimeUnit.NANOSECONDS).put('u', TimeUnit.MICROSECONDS).put('m', TimeUnit.MILLISECONDS).put('S', TimeUnit.SECONDS).put('M', TimeUnit.MINUTES).put('H', TimeUnit.HOURS).build();

        TimeoutMarshaller() {
        }

        public String toAsciiString(Long l) {
            MoreObjects.checkArgument(l.longValue() >= 0, "Negative timeout");
            UnmodifiableIterator<Map.Entry<Character, TimeUnit>> it = UNITS.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                long convert = ((TimeUnit) next.getValue()).convert(l.longValue(), TimeUnit.NANOSECONDS);
                if (convert < ((long) 100000000)) {
                    return Long.toString(convert) + next.getKey();
                }
            }
            throw new IllegalArgumentException("Timeout too large");
        }
    }

    static {
        Splitter.m70on(',').trimResults();
        String implementationVersion = GrpcUtil.class.getPackage().getImplementationVersion();
        IMPLEMENTATION_VERION = implementationVersion != null ? GeneratedOutlineSupport.outline8("/", implementationVersion) : "";
        TimeUnit.MINUTES.toNanos(1);
        TimeUnit.MINUTES.toNanos(2);
    }

    private GrpcUtil() {
    }

    public static URI authorityToUri(String str) {
        MoreObjects.checkNotNull(str, "authority");
        try {
            return new URI((String) null, str, (String) null, (String) null, (String) null);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid authority: ", str), e);
        }
    }

    public static String getGrpcUserAgent(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            sb.append(str2);
            sb.append(' ');
        }
        sb.append("grpc-java-");
        sb.append(str);
        sb.append(IMPLEMENTATION_VERION);
        return sb.toString();
    }

    public static String getLogId(WithLogId withLogId) {
        return withLogId.getClass().getSimpleName() + "@" + Integer.toHexString(withLogId.hashCode());
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0048 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.concurrent.ThreadFactory getThreadFactory(java.lang.String r6, boolean r7) {
        /*
            java.lang.String r0 = "Couldn't invoke ThreadManager.currentRequestThreadFactory"
            java.lang.String r1 = "com.google.appengine.runtime.environment"
            java.lang.String r1 = java.lang.System.getProperty(r1)
            r2 = 0
            r3 = 0
            if (r1 != 0) goto L_0x000d
            goto L_0x0025
        L_0x000d:
            java.lang.String r1 = "com.google.apphosting.api.ApiProxy"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0025 }
            java.lang.String r4 = "getCurrentEnvironment"
            java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0025 }
            java.lang.reflect.Method r1 = r1.getMethod(r4, r5)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0025 }
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0025 }
            java.lang.Object r1 = r1.invoke(r3, r4)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0025 }
            if (r1 == 0) goto L_0x0025
            r1 = 1
            goto L_0x0026
        L_0x0025:
            r1 = r2
        L_0x0026:
            if (r1 != 0) goto L_0x002d
            java.util.concurrent.ThreadFactory r0 = java.util.concurrent.Executors.defaultThreadFactory()
            goto L_0x0044
        L_0x002d:
            java.lang.String r1 = "com.google.appengine.api.ThreadManager"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ IllegalAccessException -> 0x0073, ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0065, InvocationTargetException -> 0x005c }
            java.lang.String r4 = "currentRequestThreadFactory"
            java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch:{ IllegalAccessException -> 0x0073, ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0065, InvocationTargetException -> 0x005c }
            java.lang.reflect.Method r1 = r1.getMethod(r4, r5)     // Catch:{ IllegalAccessException -> 0x0073, ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0065, InvocationTargetException -> 0x005c }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ IllegalAccessException -> 0x0073, ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0065, InvocationTargetException -> 0x005c }
            java.lang.Object r1 = r1.invoke(r3, r2)     // Catch:{ IllegalAccessException -> 0x0073, ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0065, InvocationTargetException -> 0x005c }
            java.util.concurrent.ThreadFactory r1 = (java.util.concurrent.ThreadFactory) r1     // Catch:{ IllegalAccessException -> 0x0073, ClassNotFoundException -> 0x006c, NoSuchMethodException -> 0x0065, InvocationTargetException -> 0x005c }
            r0 = r1
        L_0x0044:
            boolean r1 = IS_RESTRICTED_APPENGINE
            if (r1 == 0) goto L_0x0049
            return r0
        L_0x0049:
            com.google.common.util.concurrent.ThreadFactoryBuilder r1 = new com.google.common.util.concurrent.ThreadFactoryBuilder
            r1.<init>()
            r1.setThreadFactory(r0)
            r1.setDaemon(r7)
            r1.setNameFormat(r6)
            java.util.concurrent.ThreadFactory r6 = r1.build()
            return r6
        L_0x005c:
            r6 = move-exception
            java.lang.Throwable r6 = r6.getCause()
            com.google.common.base.Throwables.propagate(r6)
            throw r3
        L_0x0065:
            r6 = move-exception
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            r7.<init>(r0, r6)
            throw r7
        L_0x006c:
            r6 = move-exception
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            r7.<init>(r0, r6)
            throw r7
        L_0x0073:
            r6 = move-exception
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            r7.<init>(r0, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p009io.grpc.internal.GrpcUtil.getThreadFactory(java.lang.String, boolean):java.util.concurrent.ThreadFactory");
    }
}
