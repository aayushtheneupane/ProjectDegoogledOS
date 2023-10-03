package p009io.grpc.internal;

import com.google.common.base.MoreObjects;
import java.util.IdentityHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: io.grpc.internal.SharedResourceHolder */
public final class SharedResourceHolder {
    private static final SharedResourceHolder holder = new SharedResourceHolder(new ScheduledExecutorFactory() {
        public ScheduledExecutorService createScheduledExecutor() {
            return Executors.newSingleThreadScheduledExecutor(GrpcUtil.getThreadFactory("grpc-shared-destroyer-%d", true));
        }
    });
    /* access modifiers changed from: private */
    public ScheduledExecutorService destroyer;
    private final ScheduledExecutorFactory destroyerFactory;
    /* access modifiers changed from: private */
    public final IdentityHashMap<Resource<?>, Instance> instances = new IdentityHashMap<>();

    /* renamed from: io.grpc.internal.SharedResourceHolder$Instance */
    private static class Instance {
        ScheduledFuture<?> destroyTask;
        final Object payload;
        int refcount;

        Instance(Object obj) {
            this.payload = obj;
        }
    }

    /* renamed from: io.grpc.internal.SharedResourceHolder$Resource */
    public interface Resource<T> {
        void close(T t);

        T create();
    }

    /* renamed from: io.grpc.internal.SharedResourceHolder$ScheduledExecutorFactory */
    interface ScheduledExecutorFactory {
    }

    SharedResourceHolder(ScheduledExecutorFactory scheduledExecutorFactory) {
        this.destroyerFactory = scheduledExecutorFactory;
    }

    public static <T> T get(Resource<T> resource) {
        return holder.getInternal(resource);
    }

    public static <T> T release(Resource<T> resource, T t) {
        holder.releaseInternal(resource, t);
        return null;
    }

    /* access modifiers changed from: package-private */
    public synchronized <T> T getInternal(Resource<T> resource) {
        Instance instance;
        instance = this.instances.get(resource);
        if (instance == null) {
            instance = new Instance(resource.create());
            this.instances.put(resource, instance);
        }
        if (instance.destroyTask != null) {
            instance.destroyTask.cancel(false);
            instance.destroyTask = null;
        }
        instance.refcount++;
        return instance.payload;
    }

    /* access modifiers changed from: package-private */
    public synchronized <T> T releaseInternal(final Resource<T> resource, final T t) {
        final Instance instance = this.instances.get(resource);
        if (instance != null) {
            boolean z = false;
            MoreObjects.checkArgument(t == instance.payload, "Releasing the wrong instance");
            MoreObjects.checkState(instance.refcount > 0, "Refcount has already reached zero");
            instance.refcount--;
            if (instance.refcount == 0) {
                if (instance.destroyTask == null) {
                    z = true;
                }
                MoreObjects.checkState(z, "Destroy task already scheduled");
                if (this.destroyer == null) {
                    this.destroyer = ((C09501) this.destroyerFactory).createScheduledExecutor();
                }
                instance.destroyTask = this.destroyer.schedule(new LogExceptionRunnable(new Runnable() {
                    public void run() {
                        synchronized (SharedResourceHolder.this) {
                            if (instance.refcount == 0) {
                                resource.close(t);
                                SharedResourceHolder.this.instances.remove(resource);
                                if (SharedResourceHolder.this.instances.isEmpty()) {
                                    SharedResourceHolder.this.destroyer.shutdown();
                                    ScheduledExecutorService unused = SharedResourceHolder.this.destroyer = null;
                                }
                            }
                        }
                    }
                }), 1, TimeUnit.SECONDS);
            }
        } else {
            throw new IllegalArgumentException("No cached instance found for " + resource);
        }
        return null;
    }
}
