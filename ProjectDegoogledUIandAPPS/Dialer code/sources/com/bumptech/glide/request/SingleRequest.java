package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p000v4.util.Pools$Pool;
import android.util.Log;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;

public final class SingleRequest<R> implements Request, SizeReadyCallback, ResourceCallback, FactoryPools.Poolable {
    private static final boolean IS_VERBOSE_LOGGABLE = Log.isLoggable("Request", 2);
    private static final Pools$Pool<SingleRequest<?>> POOL = FactoryPools.simple(150, new FactoryPools.Factory<SingleRequest<?>>() {
        public Object create() {
            return new SingleRequest();
        }
    });
    private TransitionFactory<? super R> animationFactory;
    private Context context;
    private Engine engine;
    private Drawable errorDrawable;
    private Drawable fallbackDrawable;
    private GlideContext glideContext;
    private int height;
    private boolean isCallingCallbacks;
    private Engine.LoadStatus loadStatus;
    private Object model;
    private int overrideHeight;
    private int overrideWidth;
    private Drawable placeholderDrawable;
    private Priority priority;
    private RequestCoordinator requestCoordinator;
    private RequestListener<R> requestListener;
    private RequestOptions requestOptions;
    private Resource<R> resource;
    private long startTime;
    private final StateVerifier stateVerifier;
    private Status status;
    private final String tag;
    private Target<R> target;
    private RequestListener<R> targetListener;
    private Class<R> transcodeClass;
    private int width;

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CANCELLED,
        CLEARED,
        PAUSED
    }

    SingleRequest() {
        this.tag = IS_VERBOSE_LOGGABLE ? String.valueOf(super.hashCode()) : null;
        this.stateVerifier = StateVerifier.newInstance();
    }

    private void assertNotCallingCallbacks() {
        if (this.isCallingCallbacks) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    private Drawable getFallbackDrawable() {
        if (this.fallbackDrawable == null) {
            this.fallbackDrawable = this.requestOptions.getFallbackDrawable();
            if (this.fallbackDrawable == null && this.requestOptions.getFallbackId() > 0) {
                this.fallbackDrawable = loadDrawable(this.requestOptions.getFallbackId());
            }
        }
        return this.fallbackDrawable;
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderDrawable == null) {
            this.placeholderDrawable = this.requestOptions.getPlaceholderDrawable();
            if (this.placeholderDrawable == null && this.requestOptions.getPlaceholderId() > 0) {
                this.placeholderDrawable = loadDrawable(this.requestOptions.getPlaceholderId());
            }
        }
        return this.placeholderDrawable;
    }

    private boolean isFirstReadyResource() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || !requestCoordinator2.isAnyResourceSet();
    }

    private Drawable loadDrawable(int i) {
        return DrawableDecoderCompat.getDrawable(this.glideContext, i, this.requestOptions.getTheme() != null ? this.requestOptions.getTheme() : this.context.getTheme());
    }

    private void logV(String str) {
        String str2 = this.tag;
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport.outline1(str2, GeneratedOutlineSupport.outline1(str, 7)));
        sb.append(str);
        sb.append(" this: ");
        sb.append(str2);
        Log.v("Request", sb.toString());
    }

    public static <R> SingleRequest<R> obtain(Context context2, GlideContext glideContext2, Object obj, Class<R> cls, RequestOptions requestOptions2, int i, int i2, Priority priority2, Target<R> target2, RequestListener<R> requestListener2, RequestListener<R> requestListener3, RequestCoordinator requestCoordinator2, Engine engine2, TransitionFactory<? super R> transitionFactory) {
        SingleRequest<R> acquire = POOL.acquire();
        if (acquire == null) {
            acquire = new SingleRequest<>();
        }
        acquire.context = context2;
        acquire.glideContext = glideContext2;
        acquire.model = obj;
        acquire.transcodeClass = cls;
        acquire.requestOptions = requestOptions2;
        acquire.overrideWidth = i;
        acquire.overrideHeight = i2;
        acquire.priority = priority2;
        acquire.target = target2;
        acquire.targetListener = requestListener2;
        acquire.requestListener = requestListener3;
        acquire.requestCoordinator = requestCoordinator2;
        acquire.engine = engine2;
        acquire.animationFactory = transitionFactory;
        acquire.status = Status.PENDING;
        return acquire;
    }

    private void releaseResource(Resource<?> resource2) {
        this.engine.release(resource2);
        this.resource = null;
    }

    private void setErrorPlaceholder() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        if (requestCoordinator2 == null || requestCoordinator2.canNotifyStatusChanged(this)) {
            Drawable drawable = null;
            if (this.model == null) {
                drawable = getFallbackDrawable();
            }
            if (drawable == null) {
                if (this.errorDrawable == null) {
                    this.errorDrawable = this.requestOptions.getErrorPlaceholder();
                    if (this.errorDrawable == null && this.requestOptions.getErrorId() > 0) {
                        this.errorDrawable = loadDrawable(this.requestOptions.getErrorId());
                    }
                }
                drawable = this.errorDrawable;
            }
            if (drawable == null) {
                drawable = getPlaceholderDrawable();
            }
            this.target.onLoadFailed(drawable);
        }
    }

    public void begin() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.startTime = LogTime.getLogTime();
        if (this.model == null) {
            if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                this.width = this.overrideWidth;
                this.height = this.overrideHeight;
            }
            onLoadFailed(new GlideException("Received null model"), getFallbackDrawable() == null ? 5 : 3);
            return;
        }
        Status status2 = this.status;
        if (status2 == Status.RUNNING) {
            throw new IllegalArgumentException("Cannot restart a running request");
        } else if (status2 == Status.COMPLETE) {
            onResourceReady(this.resource, DataSource.MEMORY_CACHE);
        } else {
            this.status = Status.WAITING_FOR_SIZE;
            if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                onSizeReady(this.overrideWidth, this.overrideHeight);
            } else {
                this.target.getSize(this);
            }
            Status status3 = this.status;
            if (status3 == Status.RUNNING || status3 == Status.WAITING_FOR_SIZE) {
                RequestCoordinator requestCoordinator2 = this.requestCoordinator;
                if (requestCoordinator2 == null || requestCoordinator2.canNotifyStatusChanged(this)) {
                    this.target.onLoadStarted(getPlaceholderDrawable());
                }
            }
            if (IS_VERBOSE_LOGGABLE) {
                double elapsedMillis = LogTime.getElapsedMillis(this.startTime);
                StringBuilder sb = new StringBuilder(47);
                sb.append("finished run method in ");
                sb.append(elapsedMillis);
                logV(sb.toString());
            }
        }
    }

    public void clear() {
        Util.assertMainThread();
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        if (this.status != Status.CLEARED) {
            assertNotCallingCallbacks();
            this.stateVerifier.throwIfRecycled();
            this.target.removeCallback(this);
            this.status = Status.CANCELLED;
            Engine.LoadStatus loadStatus2 = this.loadStatus;
            if (loadStatus2 != null) {
                loadStatus2.cancel();
                this.loadStatus = null;
            }
            Resource<R> resource2 = this.resource;
            if (resource2 != null) {
                releaseResource(resource2);
            }
            RequestCoordinator requestCoordinator2 = this.requestCoordinator;
            if (requestCoordinator2 == null || requestCoordinator2.canNotifyCleared(this)) {
                this.target.onLoadCleared(getPlaceholderDrawable());
            }
            this.status = Status.CLEARED;
        }
    }

    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }

    public boolean isCancelled() {
        Status status2 = this.status;
        return status2 == Status.CANCELLED || status2 == Status.CLEARED;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0044 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isEquivalentTo(com.bumptech.glide.request.Request r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof com.bumptech.glide.request.SingleRequest
            r1 = 0
            if (r0 == 0) goto L_0x0045
            com.bumptech.glide.request.SingleRequest r4 = (com.bumptech.glide.request.SingleRequest) r4
            int r0 = r3.overrideWidth
            int r2 = r4.overrideWidth
            if (r0 != r2) goto L_0x0045
            int r0 = r3.overrideHeight
            int r2 = r4.overrideHeight
            if (r0 != r2) goto L_0x0045
            java.lang.Object r0 = r3.model
            java.lang.Object r2 = r4.model
            boolean r0 = com.bumptech.glide.util.Util.bothModelsNullEquivalentOrEquals(r0, r2)
            if (r0 == 0) goto L_0x0045
            java.lang.Class<R> r0 = r3.transcodeClass
            java.lang.Class<R> r2 = r4.transcodeClass
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0045
            com.bumptech.glide.request.RequestOptions r0 = r3.requestOptions
            com.bumptech.glide.request.RequestOptions r2 = r4.requestOptions
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0045
            com.bumptech.glide.Priority r0 = r3.priority
            com.bumptech.glide.Priority r2 = r4.priority
            if (r0 != r2) goto L_0x0045
            com.bumptech.glide.request.RequestListener<R> r3 = r3.requestListener
            if (r3 == 0) goto L_0x0040
            com.bumptech.glide.request.RequestListener<R> r3 = r4.requestListener
            if (r3 == 0) goto L_0x0045
            goto L_0x0044
        L_0x0040:
            com.bumptech.glide.request.RequestListener<R> r3 = r4.requestListener
            if (r3 != 0) goto L_0x0045
        L_0x0044:
            r1 = 1
        L_0x0045:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.isEquivalentTo(com.bumptech.glide.request.Request):boolean");
    }

    public boolean isFailed() {
        return this.status == Status.FAILED;
    }

    public boolean isResourceSet() {
        return this.status == Status.COMPLETE;
    }

    public boolean isRunning() {
        Status status2 = this.status;
        return status2 == Status.RUNNING || status2 == Status.WAITING_FOR_SIZE;
    }

    public void onLoadFailed(GlideException glideException) {
        onLoadFailed(glideException, 5);
    }

    /* JADX INFO: finally extract failed */
    public void onResourceReady(Resource<?> resource2, DataSource dataSource) {
        this.stateVerifier.throwIfRecycled();
        this.loadStatus = null;
        if (resource2 == null) {
            String valueOf = String.valueOf(this.transcodeClass);
            StringBuilder sb = new StringBuilder(valueOf.length() + 82);
            sb.append("Expected to receive a Resource<R> with an object of ");
            sb.append(valueOf);
            sb.append(" inside, but instead got null.");
            onLoadFailed(new GlideException(sb.toString()), 5);
            return;
        }
        Object obj = resource2.get();
        if (obj == null || !this.transcodeClass.isAssignableFrom(obj.getClass())) {
            this.engine.release(resource2);
            this.resource = null;
            String valueOf2 = String.valueOf(this.transcodeClass);
            String str = "";
            String valueOf3 = String.valueOf(obj != null ? obj.getClass() : str);
            String valueOf4 = String.valueOf(obj);
            String valueOf5 = String.valueOf(resource2);
            if (obj == null) {
                str = " To indicate failure return a null Resource object, rather than a Resource object containing null data.";
            }
            StringBuilder sb2 = new StringBuilder(str.length() + valueOf5.length() + valueOf4.length() + valueOf3.length() + valueOf2.length() + 71);
            sb2.append("Expected to receive an object of ");
            sb2.append(valueOf2);
            sb2.append(" but instead got ");
            sb2.append(valueOf3);
            sb2.append("{");
            sb2.append(valueOf4);
            sb2.append("} inside Resource{");
            sb2.append(valueOf5);
            onLoadFailed(new GlideException(GeneratedOutlineSupport.outline12(sb2, "}.", str)), 5);
            return;
        }
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        if (!(requestCoordinator2 == null || requestCoordinator2.canSetImage(this))) {
            this.engine.release(resource2);
            this.resource = null;
            this.status = Status.COMPLETE;
            return;
        }
        boolean isFirstReadyResource = isFirstReadyResource();
        this.status = Status.COMPLETE;
        this.resource = resource2;
        if (this.glideContext.getLogLevel() <= 3) {
            String simpleName = obj.getClass().getSimpleName();
            String valueOf6 = String.valueOf(dataSource);
            String valueOf7 = String.valueOf(this.model);
            int i = this.width;
            int i2 = this.height;
            double elapsedMillis = LogTime.getElapsedMillis(this.startTime);
            StringBuilder sb3 = new StringBuilder(valueOf7.length() + valueOf6.length() + simpleName.length() + 95);
            sb3.append("Finished loading ");
            sb3.append(simpleName);
            sb3.append(" from ");
            sb3.append(valueOf6);
            sb3.append(" for ");
            sb3.append(valueOf7);
            sb3.append(" with size [");
            sb3.append(i);
            sb3.append("x");
            sb3.append(i2);
            sb3.append("] in ");
            sb3.append(elapsedMillis);
            sb3.append(" ms");
            Log.d("Glide", sb3.toString());
        }
        this.isCallingCallbacks = true;
        try {
            if (this.requestListener != null) {
                this.requestListener.onResourceReady(obj, this.model, this.target, dataSource, isFirstReadyResource);
            }
            if (this.targetListener != null) {
                this.targetListener.onResourceReady(obj, this.model, this.target, dataSource, isFirstReadyResource);
            }
            this.target.onResourceReady(obj, this.animationFactory.build(dataSource, isFirstReadyResource));
            this.isCallingCallbacks = false;
            RequestCoordinator requestCoordinator3 = this.requestCoordinator;
            if (requestCoordinator3 != null) {
                requestCoordinator3.onRequestSuccess(this);
            }
        } catch (Throwable th) {
            this.isCallingCallbacks = false;
            throw th;
        }
    }

    public void onSizeReady(int i, int i2) {
        int i3;
        int i4 = i;
        int i5 = i2;
        this.stateVerifier.throwIfRecycled();
        if (IS_VERBOSE_LOGGABLE) {
            double elapsedMillis = LogTime.getElapsedMillis(this.startTime);
            StringBuilder sb = new StringBuilder(43);
            sb.append("Got onSizeReady in ");
            sb.append(elapsedMillis);
            logV(sb.toString());
        }
        if (this.status == Status.WAITING_FOR_SIZE) {
            this.status = Status.RUNNING;
            float sizeMultiplier = this.requestOptions.getSizeMultiplier();
            if (i4 != Integer.MIN_VALUE) {
                i4 = Math.round(((float) i4) * sizeMultiplier);
            }
            this.width = i4;
            if (i5 == Integer.MIN_VALUE) {
                i3 = i5;
            } else {
                i3 = Math.round(sizeMultiplier * ((float) i5));
            }
            this.height = i3;
            if (IS_VERBOSE_LOGGABLE) {
                double elapsedMillis2 = LogTime.getElapsedMillis(this.startTime);
                StringBuilder sb2 = new StringBuilder(59);
                sb2.append("finished setup for calling load in ");
                sb2.append(elapsedMillis2);
                logV(sb2.toString());
            }
            Engine engine2 = this.engine;
            GlideContext glideContext2 = this.glideContext;
            Engine.LoadStatus load = engine2.load(glideContext2, this.model, this.requestOptions.getSignature(), this.width, this.height, this.requestOptions.getResourceClass(), this.transcodeClass, this.priority, this.requestOptions.getDiskCacheStrategy(), this.requestOptions.getTransformations(), this.requestOptions.isTransformationRequired(), this.requestOptions.isScaleOnlyOrNoTransform(), this.requestOptions.getOptions(), this.requestOptions.isMemoryCacheable(), this.requestOptions.getUseUnlimitedSourceGeneratorsPool(), this.requestOptions.getUseAnimationPool(), this.requestOptions.getOnlyRetrieveFromCache(), this);
            this.loadStatus = load;
            if (this.status != Status.RUNNING) {
                this.loadStatus = null;
            }
            if (IS_VERBOSE_LOGGABLE) {
                double elapsedMillis3 = LogTime.getElapsedMillis(this.startTime);
                StringBuilder sb3 = new StringBuilder(48);
                sb3.append("finished onSizeReady in ");
                sb3.append(elapsedMillis3);
                logV(sb3.toString());
            }
        }
    }

    public void pause() {
        Util.assertMainThread();
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        if (this.status != Status.CLEARED) {
            assertNotCallingCallbacks();
            this.stateVerifier.throwIfRecycled();
            this.target.removeCallback(this);
            this.status = Status.CANCELLED;
            Engine.LoadStatus loadStatus2 = this.loadStatus;
            if (loadStatus2 != null) {
                loadStatus2.cancel();
                this.loadStatus = null;
            }
            Resource<R> resource2 = this.resource;
            if (resource2 != null) {
                releaseResource(resource2);
            }
            RequestCoordinator requestCoordinator2 = this.requestCoordinator;
            if (requestCoordinator2 == null || requestCoordinator2.canNotifyCleared(this)) {
                this.target.onLoadCleared(getPlaceholderDrawable());
            }
            this.status = Status.CLEARED;
        }
        this.status = Status.PAUSED;
    }

    public void recycle() {
        assertNotCallingCallbacks();
        this.context = null;
        this.glideContext = null;
        this.model = null;
        this.transcodeClass = null;
        this.requestOptions = null;
        this.overrideWidth = -1;
        this.overrideHeight = -1;
        this.target = null;
        this.requestListener = null;
        this.targetListener = null;
        this.requestCoordinator = null;
        this.animationFactory = null;
        this.loadStatus = null;
        this.errorDrawable = null;
        this.placeholderDrawable = null;
        this.fallbackDrawable = null;
        this.width = -1;
        this.height = -1;
        POOL.release(this);
    }

    /* JADX INFO: finally extract failed */
    private void onLoadFailed(GlideException glideException, int i) {
        this.stateVerifier.throwIfRecycled();
        int logLevel = this.glideContext.getLogLevel();
        if (logLevel <= i) {
            String valueOf = String.valueOf(this.model);
            int i2 = this.width;
            int i3 = this.height;
            StringBuilder sb = new StringBuilder(valueOf.length() + 52);
            sb.append("Load failed for ");
            sb.append(valueOf);
            sb.append(" with size [");
            sb.append(i2);
            sb.append("x");
            sb.append(i3);
            sb.append("]");
            Log.w("Glide", sb.toString(), glideException);
            if (logLevel <= 4) {
                glideException.logRootCauses("Glide");
            }
        }
        this.loadStatus = null;
        this.status = Status.FAILED;
        this.isCallingCallbacks = true;
        try {
            if (this.requestListener != null) {
                this.requestListener.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource());
            }
            if (this.targetListener != null) {
                this.targetListener.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource());
            }
            setErrorPlaceholder();
            this.isCallingCallbacks = false;
            RequestCoordinator requestCoordinator2 = this.requestCoordinator;
            if (requestCoordinator2 != null) {
                requestCoordinator2.onRequestFailed(this);
            }
        } catch (Throwable th) {
            this.isCallingCallbacks = false;
            throw th;
        }
    }
}
