package com.bumptech.glide.load.engine;

import android.os.Build;
import android.os.Trace;
import android.support.p000v4.util.Pools$Pool;
import android.util.Log;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DecodeJob<R> implements DataFetcherGenerator.FetcherReadyCallback, Runnable, Comparable<DecodeJob<?>>, FactoryPools.Poolable {
    private Callback<R> callback;
    private Key currentAttemptingKey;
    private Object currentData;
    private DataSource currentDataSource;
    private DataFetcher<?> currentFetcher;
    private volatile DataFetcherGenerator currentGenerator;
    private Key currentSourceKey;
    private Thread currentThread;
    private final DecodeHelper<R> decodeHelper = new DecodeHelper<>();
    private final DeferredEncodeManager<?> deferredEncodeManager = new DeferredEncodeManager<>();
    private final DiskCacheProvider diskCacheProvider;
    private DiskCacheStrategy diskCacheStrategy;
    private GlideContext glideContext;
    private int height;
    private volatile boolean isCallbackNotified;
    private volatile boolean isCancelled;
    private EngineKey loadKey;
    private boolean onlyRetrieveFromCache;
    private Options options;
    private int order;
    private final Pools$Pool<DecodeJob<?>> pool;
    private Priority priority;
    private final ReleaseManager releaseManager = new ReleaseManager();
    private RunReason runReason;
    private Key signature;
    private Stage stage;
    private long startFetchTime;
    private final StateVerifier stateVerifier = StateVerifier.newInstance();
    private final List<Throwable> throwables = new ArrayList();
    private int width;

    interface Callback<R> {
        void onLoadFailed(GlideException glideException);

        void onResourceReady(Resource<R> resource, DataSource dataSource);

        void reschedule(DecodeJob<?> decodeJob);
    }

    private final class DecodeCallback<Z> implements DecodePath.DecodeCallback<Z> {
        private final DataSource dataSource;

        DecodeCallback(DataSource dataSource2) {
            this.dataSource = dataSource2;
        }

        public Resource<Z> onResourceDecoded(Resource<Z> resource) {
            return DecodeJob.this.onResourceDecoded(this.dataSource, resource);
        }
    }

    private static class DeferredEncodeManager<Z> {
        private ResourceEncoder<Z> encoder;
        private Key key;
        private LockedResource<Z> toEncode;

        DeferredEncodeManager() {
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            this.key = null;
            this.encoder = null;
            this.toEncode = null;
        }

        /* access modifiers changed from: package-private */
        public void encode(DiskCacheProvider diskCacheProvider, Options options) {
            int i = Build.VERSION.SDK_INT;
            Trace.beginSection("DecodeJob.encode");
            try {
                diskCacheProvider.getDiskCache().put(this.key, new DataCacheWriter(this.encoder, this.toEncode, options));
            } finally {
                this.toEncode.unlock();
                int i2 = Build.VERSION.SDK_INT;
                Trace.endSection();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean hasResourceToEncode() {
            return this.toEncode != null;
        }

        /* access modifiers changed from: package-private */
        public <X> void init(Key key2, ResourceEncoder<X> resourceEncoder, LockedResource<X> lockedResource) {
            this.key = key2;
            this.encoder = resourceEncoder;
            this.toEncode = lockedResource;
        }
    }

    interface DiskCacheProvider {
        DiskCache getDiskCache();
    }

    private static class ReleaseManager {
        private boolean isEncodeComplete;
        private boolean isFailed;
        private boolean isReleased;

        ReleaseManager() {
        }

        private boolean isComplete(boolean z) {
            return (this.isFailed || z || this.isEncodeComplete) && this.isReleased;
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean onEncodeComplete() {
            this.isEncodeComplete = true;
            return isComplete(false);
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean onFailed() {
            this.isFailed = true;
            return isComplete(false);
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean release(boolean z) {
            this.isReleased = true;
            return isComplete(z);
        }

        /* access modifiers changed from: package-private */
        public synchronized void reset() {
            this.isEncodeComplete = false;
            this.isReleased = false;
            this.isFailed = false;
        }
    }

    private enum RunReason {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    private enum Stage {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    DecodeJob(DiskCacheProvider diskCacheProvider2, Pools$Pool<DecodeJob<?>> pools$Pool) {
        this.diskCacheProvider = diskCacheProvider2;
        this.pool = pools$Pool;
    }

    private <Data> Resource<R> decodeFromData(DataFetcher<?> dataFetcher, Data data, DataSource dataSource) throws GlideException {
        if (data == null) {
            dataFetcher.cleanup();
            return null;
        }
        try {
            long logTime = LogTime.getLogTime();
            Resource<R> decodeFromFetcher = decodeFromFetcher(data, dataSource);
            if (Log.isLoggable("DecodeJob", 2)) {
                String valueOf = String.valueOf(decodeFromFetcher);
                StringBuilder sb = new StringBuilder(valueOf.length() + 15);
                sb.append("Decoded result ");
                sb.append(valueOf);
                logWithTimeAndKey(sb.toString(), logTime, (String) null);
            }
            return decodeFromFetcher;
        } finally {
            dataFetcher.cleanup();
        }
    }

    private <Data> Resource<R> decodeFromFetcher(Data data, DataSource dataSource) throws GlideException {
        LoadPath<Data, ?, R> loadPath = this.decodeHelper.getLoadPath(data.getClass());
        Options options2 = this.options;
        int i = Build.VERSION.SDK_INT;
        if (options2.get(Downsampler.ALLOW_HARDWARE_CONFIG) == null && (dataSource == DataSource.RESOURCE_DISK_CACHE || this.decodeHelper.isScaleOnlyOrNoTransform())) {
            options2 = new Options();
            options2.putAll(this.options);
            options2.set(Downsampler.ALLOW_HARDWARE_CONFIG, true);
        }
        Options options3 = options2;
        DataRewinder rewinder = this.glideContext.getRegistry().getRewinder(data);
        try {
            return loadPath.load(rewinder, options3, this.width, this.height, new DecodeCallback(dataSource));
        } finally {
            rewinder.cleanup();
        }
    }

    private void decodeFromRetrievedData() {
        Resource<R> resource;
        LockedResource lockedResource;
        LockedResource<R> lockedResource2;
        if (Log.isLoggable("DecodeJob", 2)) {
            long j = this.startFetchTime;
            String valueOf = String.valueOf(this.currentData);
            String valueOf2 = String.valueOf(this.currentSourceKey);
            String valueOf3 = String.valueOf(this.currentFetcher);
            StringBuilder sb = new StringBuilder(valueOf3.length() + valueOf2.length() + valueOf.length() + 30);
            sb.append("data: ");
            sb.append(valueOf);
            sb.append(", cache key: ");
            sb.append(valueOf2);
            logWithTimeAndKey("Retrieved data", j, GeneratedOutlineSupport.outline12(sb, ", fetcher: ", valueOf3));
        }
        try {
            resource = decodeFromData(this.currentFetcher, this.currentData, this.currentDataSource);
        } catch (GlideException e) {
            e.setLoggingDetails(this.currentAttemptingKey, this.currentDataSource);
            this.throwables.add(e);
            resource = null;
        }
        if (resource != null) {
            DataSource dataSource = this.currentDataSource;
            if (resource instanceof Initializable) {
                ((Initializable) resource).initialize();
            }
            if (this.deferredEncodeManager.hasResourceToEncode()) {
                lockedResource2 = LockedResource.obtain(resource);
                lockedResource = lockedResource2;
            } else {
                LockedResource<R> lockedResource3 = resource;
                lockedResource = null;
                lockedResource2 = lockedResource3;
            }
            setNotifiedOrThrow();
            this.callback.onResourceReady(lockedResource2, dataSource);
            this.stage = Stage.ENCODE;
            try {
                if (this.deferredEncodeManager.hasResourceToEncode()) {
                    this.deferredEncodeManager.encode(this.diskCacheProvider, this.options);
                }
                if (this.releaseManager.onEncodeComplete()) {
                    releaseInternal();
                }
            } finally {
                if (lockedResource != null) {
                    lockedResource.unlock();
                }
            }
        } else {
            runGenerators();
        }
    }

    private DataFetcherGenerator getNextGenerator() {
        int ordinal = this.stage.ordinal();
        if (ordinal == 1) {
            return new ResourceCacheGenerator(this.decodeHelper, this);
        }
        if (ordinal == 2) {
            DecodeHelper<R> decodeHelper2 = this.decodeHelper;
            return new DataCacheGenerator(decodeHelper2.getCacheKeys(), decodeHelper2, this);
        } else if (ordinal == 3) {
            return new SourceGenerator(this.decodeHelper, this);
        } else {
            if (ordinal == 5) {
                return null;
            }
            String valueOf = String.valueOf(this.stage);
            throw new IllegalStateException(GeneratedOutlineSupport.outline4(valueOf.length() + 20, "Unrecognized stage: ", valueOf));
        }
    }

    private Stage getNextStage(Stage stage2) {
        int ordinal = stage2.ordinal();
        if (ordinal == 0) {
            return this.diskCacheStrategy.decodeCachedResource() ? Stage.RESOURCE_CACHE : getNextStage(Stage.RESOURCE_CACHE);
        }
        if (ordinal == 1) {
            return this.diskCacheStrategy.decodeCachedData() ? Stage.DATA_CACHE : getNextStage(Stage.DATA_CACHE);
        }
        if (ordinal == 2) {
            return this.onlyRetrieveFromCache ? Stage.FINISHED : Stage.SOURCE;
        }
        if (ordinal == 3 || ordinal == 5) {
            return Stage.FINISHED;
        }
        String valueOf = String.valueOf(stage2);
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline4(valueOf.length() + 20, "Unrecognized stage: ", valueOf));
    }

    private int getPriority() {
        return this.priority.ordinal();
    }

    private void logWithTimeAndKey(String str, long j, String str2) {
        double elapsedMillis = LogTime.getElapsedMillis(j);
        String valueOf = String.valueOf(this.loadKey);
        String concat = str2 != null ? str2.length() != 0 ? ", ".concat(str2) : new String(", ") : "";
        String name = Thread.currentThread().getName();
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport.outline1(name, GeneratedOutlineSupport.outline1(concat, valueOf.length() + GeneratedOutlineSupport.outline1(str, 50))));
        sb.append(str);
        sb.append(" in ");
        sb.append(elapsedMillis);
        sb.append(", load key: ");
        sb.append(valueOf);
        sb.append(concat);
        sb.append(", thread: ");
        sb.append(name);
        Log.v("DecodeJob", sb.toString());
    }

    private void notifyFailed() {
        setNotifiedOrThrow();
        this.callback.onLoadFailed(new GlideException("Failed to load resource", (List<Throwable>) new ArrayList(this.throwables)));
        if (this.releaseManager.onFailed()) {
            releaseInternal();
        }
    }

    private void releaseInternal() {
        this.releaseManager.reset();
        this.deferredEncodeManager.clear();
        this.decodeHelper.clear();
        this.isCallbackNotified = false;
        this.glideContext = null;
        this.signature = null;
        this.options = null;
        this.priority = null;
        this.loadKey = null;
        this.callback = null;
        this.stage = null;
        this.currentGenerator = null;
        this.currentThread = null;
        this.currentSourceKey = null;
        this.currentData = null;
        this.currentDataSource = null;
        this.currentFetcher = null;
        this.startFetchTime = 0;
        this.isCancelled = false;
        this.throwables.clear();
        this.pool.release(this);
    }

    private void runGenerators() {
        this.currentThread = Thread.currentThread();
        this.startFetchTime = LogTime.getLogTime();
        boolean z = false;
        while (!this.isCancelled && this.currentGenerator != null && !(z = this.currentGenerator.startNext())) {
            this.stage = getNextStage(this.stage);
            this.currentGenerator = getNextGenerator();
            if (this.stage == Stage.SOURCE) {
                this.runReason = RunReason.SWITCH_TO_SOURCE_SERVICE;
                this.callback.reschedule(this);
                return;
            }
        }
        if ((this.stage == Stage.FINISHED || this.isCancelled) && !z) {
            notifyFailed();
        }
    }

    private void runWrapped() {
        int ordinal = this.runReason.ordinal();
        if (ordinal == 0) {
            this.stage = getNextStage(Stage.INITIALIZE);
            this.currentGenerator = getNextGenerator();
            runGenerators();
        } else if (ordinal == 1) {
            runGenerators();
        } else if (ordinal == 2) {
            decodeFromRetrievedData();
        } else {
            String valueOf = String.valueOf(this.runReason);
            throw new IllegalStateException(GeneratedOutlineSupport.outline4(valueOf.length() + 25, "Unrecognized run reason: ", valueOf));
        }
    }

    private void setNotifiedOrThrow() {
        this.stateVerifier.throwIfRecycled();
        if (!this.isCallbackNotified) {
            this.isCallbackNotified = true;
            return;
        }
        throw new IllegalStateException("Already notified");
    }

    public void cancel() {
        this.isCancelled = true;
        DataFetcherGenerator dataFetcherGenerator = this.currentGenerator;
        if (dataFetcherGenerator != null) {
            dataFetcherGenerator.cancel();
        }
    }

    public int compareTo(Object obj) {
        DecodeJob decodeJob = (DecodeJob) obj;
        int priority2 = getPriority() - decodeJob.getPriority();
        return priority2 == 0 ? this.order - decodeJob.order : priority2;
    }

    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }

    /* access modifiers changed from: package-private */
    public DecodeJob<R> init(GlideContext glideContext2, Object obj, EngineKey engineKey, Key key, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority2, DiskCacheStrategy diskCacheStrategy2, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, boolean z3, Options options2, Callback<R> callback2, int i3) {
        this.decodeHelper.init(glideContext2, obj, key, i, i2, diskCacheStrategy2, cls, cls2, priority2, options2, map, z, z2, this.diskCacheProvider);
        this.glideContext = glideContext2;
        this.signature = key;
        this.priority = priority2;
        this.loadKey = engineKey;
        this.width = i;
        this.height = i2;
        this.diskCacheStrategy = diskCacheStrategy2;
        this.onlyRetrieveFromCache = z3;
        this.options = options2;
        this.callback = callback2;
        this.order = i3;
        this.runReason = RunReason.INITIALIZE;
        return this;
    }

    public void onDataFetcherFailed(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource) {
        dataFetcher.cleanup();
        GlideException glideException = new GlideException("Fetching data failed", (Throwable) exc);
        glideException.setLoggingDetails(key, dataSource, dataFetcher.getDataClass());
        this.throwables.add(glideException);
        if (Thread.currentThread() != this.currentThread) {
            this.runReason = RunReason.SWITCH_TO_SOURCE_SERVICE;
            this.callback.reschedule(this);
            return;
        }
        runGenerators();
    }

    public void onDataFetcherReady(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2) {
        this.currentSourceKey = key;
        this.currentData = obj;
        this.currentFetcher = dataFetcher;
        this.currentDataSource = dataSource;
        this.currentAttemptingKey = key2;
        if (Thread.currentThread() != this.currentThread) {
            this.runReason = RunReason.DECODE_DATA;
            this.callback.reschedule(this);
            return;
        }
        int i = Build.VERSION.SDK_INT;
        Trace.beginSection("DecodeJob.decodeFromRetrievedData");
        try {
            decodeFromRetrievedData();
        } finally {
            int i2 = Build.VERSION.SDK_INT;
            Trace.endSection();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: com.bumptech.glide.load.engine.ResourceCacheKey} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: com.bumptech.glide.load.engine.DataCacheKey} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v9, resolved type: com.bumptech.glide.load.engine.ResourceCacheKey} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: com.bumptech.glide.load.engine.ResourceCacheKey} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Z> com.bumptech.glide.load.engine.Resource<Z> onResourceDecoded(com.bumptech.glide.load.DataSource r13, com.bumptech.glide.load.engine.Resource<Z> r14) {
        /*
            r12 = this;
            java.lang.Object r0 = r14.get()
            java.lang.Class r8 = r0.getClass()
            com.bumptech.glide.load.DataSource r0 = com.bumptech.glide.load.DataSource.RESOURCE_DISK_CACHE
            r1 = 0
            if (r13 == r0) goto L_0x0020
            com.bumptech.glide.load.engine.DecodeHelper<R> r0 = r12.decodeHelper
            com.bumptech.glide.load.Transformation r0 = r0.getTransformation(r8)
            com.bumptech.glide.GlideContext r2 = r12.glideContext
            int r3 = r12.width
            int r4 = r12.height
            com.bumptech.glide.load.engine.Resource r2 = r0.transform(r2, r14, r3, r4)
            r7 = r0
            r0 = r2
            goto L_0x0022
        L_0x0020:
            r0 = r14
            r7 = r1
        L_0x0022:
            boolean r2 = r14.equals(r0)
            if (r2 != 0) goto L_0x002b
            r14.recycle()
        L_0x002b:
            com.bumptech.glide.load.engine.DecodeHelper<R> r14 = r12.decodeHelper
            boolean r14 = r14.isResourceEncoderAvailable(r0)
            if (r14 == 0) goto L_0x0040
            com.bumptech.glide.load.engine.DecodeHelper<R> r14 = r12.decodeHelper
            com.bumptech.glide.load.ResourceEncoder r1 = r14.getResultEncoder(r0)
            com.bumptech.glide.load.Options r14 = r12.options
            com.bumptech.glide.load.EncodeStrategy r14 = r1.getEncodeStrategy(r14)
            goto L_0x0042
        L_0x0040:
            com.bumptech.glide.load.EncodeStrategy r14 = com.bumptech.glide.load.EncodeStrategy.NONE
        L_0x0042:
            r11 = r1
            r1 = r14
            r14 = r11
            com.bumptech.glide.load.engine.DecodeHelper<R> r2 = r12.decodeHelper
            com.bumptech.glide.load.Key r3 = r12.currentSourceKey
            java.util.List r2 = r2.getLoadData()
            int r4 = r2.size()
            r5 = 0
            r6 = r5
        L_0x0053:
            r9 = 1
            if (r6 >= r4) goto L_0x0069
            java.lang.Object r10 = r2.get(r6)
            com.bumptech.glide.load.model.ModelLoader$LoadData r10 = (com.bumptech.glide.load.model.ModelLoader.LoadData) r10
            com.bumptech.glide.load.Key r10 = r10.sourceKey
            boolean r10 = r10.equals(r3)
            if (r10 == 0) goto L_0x0066
            r5 = r9
            goto L_0x0069
        L_0x0066:
            int r6 = r6 + 1
            goto L_0x0053
        L_0x0069:
            r2 = r5 ^ 1
            com.bumptech.glide.load.engine.DiskCacheStrategy r3 = r12.diskCacheStrategy
            boolean r13 = r3.isResourceCacheable(r2, r13, r1)
            if (r13 == 0) goto L_0x00cb
            if (r14 == 0) goto L_0x00bd
            int r13 = r1.ordinal()
            if (r13 == 0) goto L_0x00aa
            if (r13 != r9) goto L_0x0094
            com.bumptech.glide.load.engine.ResourceCacheKey r13 = new com.bumptech.glide.load.engine.ResourceCacheKey
            com.bumptech.glide.load.engine.DecodeHelper<R> r1 = r12.decodeHelper
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r2 = r1.getArrayPool()
            com.bumptech.glide.load.Key r3 = r12.currentSourceKey
            com.bumptech.glide.load.Key r4 = r12.signature
            int r5 = r12.width
            int r6 = r12.height
            com.bumptech.glide.load.Options r9 = r12.options
            r1 = r13
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x00b3
        L_0x0094:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = java.lang.String.valueOf(r1)
            int r14 = r13.length()
            int r14 = r14 + 18
            java.lang.String r0 = "Unknown strategy: "
            java.lang.String r13 = com.android.tools.p006r8.GeneratedOutlineSupport.outline4(r14, r0, r13)
            r12.<init>(r13)
            throw r12
        L_0x00aa:
            com.bumptech.glide.load.engine.DataCacheKey r13 = new com.bumptech.glide.load.engine.DataCacheKey
            com.bumptech.glide.load.Key r1 = r12.currentSourceKey
            com.bumptech.glide.load.Key r2 = r12.signature
            r13.<init>(r1, r2)
        L_0x00b3:
            com.bumptech.glide.load.engine.LockedResource r0 = com.bumptech.glide.load.engine.LockedResource.obtain(r0)
            com.bumptech.glide.load.engine.DecodeJob$DeferredEncodeManager<?> r12 = r12.deferredEncodeManager
            r12.init(r13, r14, r0)
            goto L_0x00cb
        L_0x00bd:
            com.bumptech.glide.Registry$NoResultEncoderAvailableException r12 = new com.bumptech.glide.Registry$NoResultEncoderAvailableException
            java.lang.Object r13 = r0.get()
            java.lang.Class r13 = r13.getClass()
            r12.<init>(r13)
            throw r12
        L_0x00cb:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.DecodeJob.onResourceDecoded(com.bumptech.glide.load.DataSource, com.bumptech.glide.load.engine.Resource):com.bumptech.glide.load.engine.Resource");
    }

    /* access modifiers changed from: package-private */
    public void release(boolean z) {
        if (this.releaseManager.release(z)) {
            releaseInternal();
        }
    }

    public void reschedule() {
        this.runReason = RunReason.SWITCH_TO_SOURCE_SERVICE;
        this.callback.reschedule(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        if (r1 != null) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r7 = this;
            java.lang.String r0 = "DecodeJob"
            int r1 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = "DecodeJob#run"
            android.os.Trace.beginSection(r1)
            com.bumptech.glide.load.data.DataFetcher<?> r1 = r7.currentFetcher
            boolean r2 = r7.isCancelled     // Catch:{ all -> 0x002b }
            if (r2 == 0) goto L_0x001d
            r7.notifyFailed()     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x0017
            r1.cleanup()
        L_0x0017:
            int r7 = android.os.Build.VERSION.SDK_INT
            android.os.Trace.endSection()
            return
        L_0x001d:
            r7.runWrapped()     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x0025
        L_0x0022:
            r1.cleanup()
        L_0x0025:
            int r7 = android.os.Build.VERSION.SDK_INT
            android.os.Trace.endSection()
            goto L_0x0072
        L_0x002b:
            r2 = move-exception
            r3 = 3
            boolean r3 = android.util.Log.isLoggable(r0, r3)     // Catch:{ all -> 0x0074 }
            if (r3 == 0) goto L_0x005d
            boolean r3 = r7.isCancelled     // Catch:{ all -> 0x0074 }
            com.bumptech.glide.load.engine.DecodeJob$Stage r4 = r7.stage     // Catch:{ all -> 0x0074 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0074 }
            int r5 = r4.length()     // Catch:{ all -> 0x0074 }
            int r5 = r5 + 57
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
            r6.<init>(r5)     // Catch:{ all -> 0x0074 }
            java.lang.String r5 = "DecodeJob threw unexpectedly, isCancelled: "
            r6.append(r5)     // Catch:{ all -> 0x0074 }
            r6.append(r3)     // Catch:{ all -> 0x0074 }
            java.lang.String r3 = ", stage: "
            r6.append(r3)     // Catch:{ all -> 0x0074 }
            r6.append(r4)     // Catch:{ all -> 0x0074 }
            java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x0074 }
            android.util.Log.d(r0, r3, r2)     // Catch:{ all -> 0x0074 }
        L_0x005d:
            com.bumptech.glide.load.engine.DecodeJob$Stage r0 = r7.stage     // Catch:{ all -> 0x0074 }
            com.bumptech.glide.load.engine.DecodeJob$Stage r3 = com.bumptech.glide.load.engine.DecodeJob.Stage.ENCODE     // Catch:{ all -> 0x0074 }
            if (r0 == r3) goto L_0x006b
            java.util.List<java.lang.Throwable> r0 = r7.throwables     // Catch:{ all -> 0x0074 }
            r0.add(r2)     // Catch:{ all -> 0x0074 }
            r7.notifyFailed()     // Catch:{ all -> 0x0074 }
        L_0x006b:
            boolean r7 = r7.isCancelled     // Catch:{ all -> 0x0074 }
            if (r7 == 0) goto L_0x0073
            if (r1 == 0) goto L_0x0025
            goto L_0x0022
        L_0x0072:
            return
        L_0x0073:
            throw r2     // Catch:{ all -> 0x0074 }
        L_0x0074:
            r7 = move-exception
            if (r1 == 0) goto L_0x007a
            r1.cleanup()
        L_0x007a:
            int r0 = android.os.Build.VERSION.SDK_INT
            android.os.Trace.endSection()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.DecodeJob.run():void");
    }

    /* access modifiers changed from: package-private */
    public boolean willDecodeFromCache() {
        Stage nextStage = getNextStage(Stage.INITIALIZE);
        return nextStage == Stage.RESOURCE_CACHE || nextStage == Stage.DATA_CACHE;
    }
}
