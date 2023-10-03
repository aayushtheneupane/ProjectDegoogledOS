package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.p002v7.appcompat.R$style;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.StandardGifDecoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

class GifFrameLoader {
    private final BitmapPool bitmapPool;
    private final List<FrameCallback> callbacks = new ArrayList();
    private DelayTarget current;
    private Bitmap firstFrame;
    private final GifDecoder gifDecoder;
    private final Handler handler;
    private boolean isCleared;
    private boolean isLoadPending;
    private boolean isRunning;
    private DelayTarget next;
    private OnEveryFrameListener onEveryFrameListener;
    private DelayTarget pendingTarget;
    private RequestBuilder<Bitmap> requestBuilder;
    final RequestManager requestManager;
    private boolean startFromFirstFrame;

    static class DelayTarget extends SimpleTarget<Bitmap> {
        private final Handler handler;
        final int index;
        private Bitmap resource;
        private final long targetTime;

        DelayTarget(Handler handler2, int i, long j) {
            this.handler = handler2;
            this.index = i;
            this.targetTime = j;
        }

        /* access modifiers changed from: package-private */
        public Bitmap getResource() {
            return this.resource;
        }

        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
            this.resource = bitmap;
            this.handler.sendMessageAtTime(this.handler.obtainMessage(1, this), this.targetTime);
        }
    }

    public interface FrameCallback {
        void onFrameReady();
    }

    private class FrameLoaderCallback implements Handler.Callback {
        FrameLoaderCallback() {
        }

        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                GifFrameLoader.this.onFrameReady((DelayTarget) message.obj);
                return true;
            } else if (i != 2) {
                return false;
            } else {
                GifFrameLoader.this.requestManager.clear((DelayTarget) message.obj);
                return false;
            }
        }
    }

    interface OnEveryFrameListener {
        void onFrameReady();
    }

    GifFrameLoader(Glide glide, GifDecoder gifDecoder2, int i, int i2, Transformation<Bitmap> transformation, Bitmap bitmap) {
        BitmapPool bitmapPool2 = glide.getBitmapPool();
        RequestManager with = Glide.with(glide.getContext());
        RequestBuilder<Bitmap> apply = Glide.with(glide.getContext()).asBitmap().apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).useAnimationPool(true).skipMemoryCache(true).override(i, i2));
        this.requestManager = with;
        Handler handler2 = new Handler(Looper.getMainLooper(), new FrameLoaderCallback());
        this.bitmapPool = bitmapPool2;
        this.handler = handler2;
        this.requestBuilder = apply;
        this.gifDecoder = gifDecoder2;
        setFrameTransformation(transformation, bitmap);
    }

    private void loadNextFrame() {
        if (this.isRunning && !this.isLoadPending) {
            if (this.startFromFirstFrame) {
                R$style.checkArgument(this.pendingTarget == null, "Pending target must be null when starting from the first frame");
                ((StandardGifDecoder) this.gifDecoder).resetFrameIndex();
                this.startFromFirstFrame = false;
            }
            DelayTarget delayTarget = this.pendingTarget;
            if (delayTarget != null) {
                this.pendingTarget = null;
                onFrameReady(delayTarget);
                return;
            }
            this.isLoadPending = true;
            long uptimeMillis = SystemClock.uptimeMillis() + ((long) ((StandardGifDecoder) this.gifDecoder).getNextDelay());
            ((StandardGifDecoder) this.gifDecoder).advance();
            this.next = new DelayTarget(this.handler, ((StandardGifDecoder) this.gifDecoder).getCurrentFrameIndex(), uptimeMillis);
            this.requestBuilder.apply(new RequestOptions().signature(new ObjectKey(Double.valueOf(Math.random())))).load((Object) this.gifDecoder).into(this.next);
        }
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.callbacks.clear();
        Bitmap bitmap = this.firstFrame;
        if (bitmap != null) {
            this.bitmapPool.put(bitmap);
            this.firstFrame = null;
        }
        this.isRunning = false;
        DelayTarget delayTarget = this.current;
        if (delayTarget != null) {
            this.requestManager.clear(delayTarget);
            this.current = null;
        }
        DelayTarget delayTarget2 = this.next;
        if (delayTarget2 != null) {
            this.requestManager.clear(delayTarget2);
            this.next = null;
        }
        DelayTarget delayTarget3 = this.pendingTarget;
        if (delayTarget3 != null) {
            this.requestManager.clear(delayTarget3);
            this.pendingTarget = null;
        }
        ((StandardGifDecoder) this.gifDecoder).clear();
        this.isCleared = true;
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer getBuffer() {
        return ((StandardGifDecoder) this.gifDecoder).getData().asReadOnlyBuffer();
    }

    /* access modifiers changed from: package-private */
    public Bitmap getCurrentFrame() {
        DelayTarget delayTarget = this.current;
        return delayTarget != null ? delayTarget.getResource() : this.firstFrame;
    }

    /* access modifiers changed from: package-private */
    public int getCurrentIndex() {
        DelayTarget delayTarget = this.current;
        if (delayTarget != null) {
            return delayTarget.index;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public Bitmap getFirstFrame() {
        return this.firstFrame;
    }

    /* access modifiers changed from: package-private */
    public int getFrameCount() {
        return ((StandardGifDecoder) this.gifDecoder).getFrameCount();
    }

    /* access modifiers changed from: package-private */
    public int getHeight() {
        return getCurrentFrame().getHeight();
    }

    /* access modifiers changed from: package-private */
    public int getSize() {
        return ((StandardGifDecoder) this.gifDecoder).getByteSize() + Util.getBitmapByteSize(getCurrentFrame().getWidth(), getCurrentFrame().getHeight(), getCurrentFrame().getConfig());
    }

    /* access modifiers changed from: package-private */
    public int getWidth() {
        return getCurrentFrame().getWidth();
    }

    /* access modifiers changed from: package-private */
    public void onFrameReady(DelayTarget delayTarget) {
        OnEveryFrameListener onEveryFrameListener2 = this.onEveryFrameListener;
        if (onEveryFrameListener2 != null) {
            onEveryFrameListener2.onFrameReady();
        }
        this.isLoadPending = false;
        if (this.isCleared) {
            this.handler.obtainMessage(2, delayTarget).sendToTarget();
        } else if (!this.isRunning) {
            this.pendingTarget = delayTarget;
        } else {
            if (delayTarget.getResource() != null) {
                Bitmap bitmap = this.firstFrame;
                if (bitmap != null) {
                    this.bitmapPool.put(bitmap);
                    this.firstFrame = null;
                }
                DelayTarget delayTarget2 = this.current;
                this.current = delayTarget;
                int size = this.callbacks.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    }
                    this.callbacks.get(size).onFrameReady();
                }
                if (delayTarget2 != null) {
                    this.handler.obtainMessage(2, delayTarget2).sendToTarget();
                }
            }
            loadNextFrame();
        }
    }

    /* access modifiers changed from: package-private */
    public void setFrameTransformation(Transformation<Bitmap> transformation, Bitmap bitmap) {
        R$style.checkNotNull(transformation, "Argument must not be null");
        R$style.checkNotNull(bitmap, "Argument must not be null");
        this.firstFrame = bitmap;
        this.requestBuilder = this.requestBuilder.apply(new RequestOptions().transform(transformation));
    }

    /* access modifiers changed from: package-private */
    public void setOnEveryFrameReadyListener(OnEveryFrameListener onEveryFrameListener2) {
        this.onEveryFrameListener = onEveryFrameListener2;
    }

    /* access modifiers changed from: package-private */
    public void subscribe(FrameCallback frameCallback) {
        if (this.isCleared) {
            throw new IllegalStateException("Cannot subscribe to a cleared frame loader");
        } else if (!this.callbacks.contains(frameCallback)) {
            boolean isEmpty = this.callbacks.isEmpty();
            this.callbacks.add(frameCallback);
            if (isEmpty && !this.isRunning) {
                this.isRunning = true;
                this.isCleared = false;
                loadNextFrame();
            }
        } else {
            throw new IllegalStateException("Cannot subscribe twice in a row");
        }
    }

    /* access modifiers changed from: package-private */
    public void unsubscribe(FrameCallback frameCallback) {
        this.callbacks.remove(frameCallback);
        if (this.callbacks.isEmpty()) {
            this.isRunning = false;
        }
    }
}
