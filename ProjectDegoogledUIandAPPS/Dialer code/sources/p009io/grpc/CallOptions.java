package p009io.grpc;

import com.google.common.base.MoreObjects;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.Executor;

/* renamed from: io.grpc.CallOptions */
public final class CallOptions {
    public static final CallOptions DEFAULT = new CallOptions();
    private Attributes affinity = Attributes.EMPTY;
    private String authority;
    private String compressorName;
    private Object[][] customOptions = ((Object[][]) Array.newInstance(Object.class, new int[]{0, 2}));
    private Deadline deadline;
    private Executor executor;
    private boolean waitForReady;

    private CallOptions() {
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        stringHelper.add("deadline", (Object) this.deadline);
        stringHelper.add("authority", (Object) this.authority);
        Class<?> cls = null;
        stringHelper.add("callCredentials", (Object) null);
        stringHelper.add("affinity", (Object) this.affinity);
        Executor executor2 = this.executor;
        if (executor2 != null) {
            cls = executor2.getClass();
        }
        stringHelper.add("executor", (Object) cls);
        stringHelper.add("compressorName", (Object) this.compressorName);
        stringHelper.add("customOptions", (Object) Arrays.deepToString(this.customOptions));
        stringHelper.add("waitForReady", this.waitForReady);
        return stringHelper.toString();
    }

    public CallOptions withExecutor(Executor executor2) {
        CallOptions callOptions = new CallOptions(this);
        callOptions.executor = executor2;
        return callOptions;
    }

    private CallOptions(CallOptions callOptions) {
        this.deadline = callOptions.deadline;
        this.authority = callOptions.authority;
        this.affinity = callOptions.affinity;
        this.executor = callOptions.executor;
        this.compressorName = callOptions.compressorName;
        this.customOptions = callOptions.customOptions;
        this.waitForReady = callOptions.waitForReady;
    }
}
