package p000;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: enf */
/* compiled from: PG */
public final class enf {

    /* renamed from: a */
    public static final ExecutorService f8642a;

    static {
        euf euf = euh.f9040a;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new eqz("GAC_Executor"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        f8642a = Executors.unconfigurableExecutorService(threadPoolExecutor);
    }
}
