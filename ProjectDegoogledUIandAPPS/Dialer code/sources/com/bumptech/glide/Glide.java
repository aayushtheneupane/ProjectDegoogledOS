package com.bumptech.glide;

import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.appcompat.R$style;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.data.InputStreamRewinder;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.BitmapPreFiller;
import com.bumptech.glide.load.model.AssetUriLoader;
import com.bumptech.glide.load.model.ByteArrayLoader;
import com.bumptech.glide.load.model.ByteBufferEncoder;
import com.bumptech.glide.load.model.ByteBufferFileLoader;
import com.bumptech.glide.load.model.DataUrlLoader;
import com.bumptech.glide.load.model.FileLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.MediaStoreFileLoader;
import com.bumptech.glide.load.model.ResourceLoader;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.model.StringLoader;
import com.bumptech.glide.load.model.UnitModelLoader;
import com.bumptech.glide.load.model.UriLoader;
import com.bumptech.glide.load.model.UrlUriLoader;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.model.stream.HttpUriLoader;
import com.bumptech.glide.load.model.stream.MediaStoreImageThumbLoader;
import com.bumptech.glide.load.model.stream.MediaStoreVideoThumbLoader;
import com.bumptech.glide.load.model.stream.UrlLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableEncoder;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.ResourceBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.UnitBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;
import com.bumptech.glide.load.resource.bytes.ByteBufferRewinder;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
import com.bumptech.glide.load.resource.drawable.UnitDrawableDecoder;
import com.bumptech.glide.load.resource.file.FileDecoder;
import com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableEncoder;
import com.bumptech.glide.load.resource.gif.GifFrameResourceDecoder;
import com.bumptech.glide.load.resource.gif.StreamGifDecoder;
import com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.BitmapDrawableTranscoder;
import com.bumptech.glide.load.resource.transcode.DrawableBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.module.ManifestParser;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Glide implements ComponentCallbacks2 {
    private static volatile Glide glide;
    private static volatile boolean isInitializing;
    private final ArrayPool arrayPool;
    private final BitmapPool bitmapPool;
    private final ConnectivityMonitorFactory connectivityMonitorFactory;
    private final Engine engine;
    private final GlideContext glideContext;
    private final List<RequestManager> managers = new ArrayList();
    private final MemoryCache memoryCache;
    private final Registry registry;
    private final RequestManagerRetriever requestManagerRetriever;

    Glide(Context context, Engine engine2, MemoryCache memoryCache2, BitmapPool bitmapPool2, ArrayPool arrayPool2, RequestManagerRetriever requestManagerRetriever2, ConnectivityMonitorFactory connectivityMonitorFactory2, int i, RequestOptions requestOptions, Map<Class<?>, TransitionOptions<?, ?>> map) {
        Context context2 = context;
        MemoryCache memoryCache3 = memoryCache2;
        BitmapPool bitmapPool3 = bitmapPool2;
        ArrayPool arrayPool3 = arrayPool2;
        MemoryCategory memoryCategory = MemoryCategory.NORMAL;
        this.engine = engine2;
        this.bitmapPool = bitmapPool3;
        this.arrayPool = arrayPool3;
        this.memoryCache = memoryCache3;
        this.requestManagerRetriever = requestManagerRetriever2;
        this.connectivityMonitorFactory = connectivityMonitorFactory2;
        new BitmapPreFiller(memoryCache3, bitmapPool3, (DecodeFormat) requestOptions.getOptions().get(Downsampler.DECODE_FORMAT));
        Resources resources = context.getResources();
        this.registry = new Registry();
        this.registry.register((ImageHeaderParser) new DefaultImageHeaderParser());
        Downsampler downsampler = new Downsampler(this.registry.getImageHeaderParsers(), resources.getDisplayMetrics(), bitmapPool3, arrayPool3);
        ByteBufferGifDecoder byteBufferGifDecoder = new ByteBufferGifDecoder(context2, this.registry.getImageHeaderParsers(), bitmapPool3, arrayPool3);
        ResourceDecoder<ParcelFileDescriptor, Bitmap> parcel = VideoDecoder.parcel(bitmapPool2);
        ByteBufferBitmapDecoder byteBufferBitmapDecoder = new ByteBufferBitmapDecoder(downsampler);
        StreamBitmapDecoder streamBitmapDecoder = new StreamBitmapDecoder(downsampler, arrayPool3);
        ResourceDrawableDecoder resourceDrawableDecoder = new ResourceDrawableDecoder(context2);
        ResourceLoader.StreamFactory streamFactory = new ResourceLoader.StreamFactory(resources);
        ResourceLoader.UriFactory uriFactory = new ResourceLoader.UriFactory(resources);
        ResourceLoader.FileDescriptorFactory fileDescriptorFactory = new ResourceLoader.FileDescriptorFactory(resources);
        ResourceLoader.AssetFileDescriptorFactory assetFileDescriptorFactory = new ResourceLoader.AssetFileDescriptorFactory(resources);
        BitmapEncoder bitmapEncoder = new BitmapEncoder(arrayPool3);
        BitmapBytesTranscoder bitmapBytesTranscoder = new BitmapBytesTranscoder();
        GifDrawableBytesTranscoder gifDrawableBytesTranscoder = new GifDrawableBytesTranscoder();
        ContentResolver contentResolver = context.getContentResolver();
        Registry registry2 = this.registry;
        registry2.append(ByteBuffer.class, new ByteBufferEncoder());
        registry2.append(InputStream.class, new StreamEncoder(arrayPool3));
        registry2.append("Bitmap", ByteBuffer.class, Bitmap.class, byteBufferBitmapDecoder);
        registry2.append("Bitmap", InputStream.class, Bitmap.class, streamBitmapDecoder);
        registry2.append("Bitmap", ParcelFileDescriptor.class, Bitmap.class, parcel);
        registry2.append("Bitmap", AssetFileDescriptor.class, Bitmap.class, VideoDecoder.asset(bitmapPool2));
        registry2.append(Bitmap.class, Bitmap.class, UnitModelLoader.Factory.getInstance());
        registry2.append("Bitmap", Bitmap.class, Bitmap.class, new UnitBitmapDecoder());
        registry2.append(Bitmap.class, bitmapEncoder);
        registry2.append("BitmapDrawable", ByteBuffer.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, byteBufferBitmapDecoder));
        registry2.append("BitmapDrawable", InputStream.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, streamBitmapDecoder));
        registry2.append("BitmapDrawable", ParcelFileDescriptor.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, parcel));
        registry2.append(BitmapDrawable.class, new BitmapDrawableEncoder(bitmapPool3, bitmapEncoder));
        registry2.append("Gif", InputStream.class, GifDrawable.class, new StreamGifDecoder(this.registry.getImageHeaderParsers(), byteBufferGifDecoder, arrayPool3));
        registry2.append("Gif", ByteBuffer.class, GifDrawable.class, byteBufferGifDecoder);
        registry2.append(GifDrawable.class, new GifDrawableEncoder());
        registry2.append(GifDecoder.class, GifDecoder.class, UnitModelLoader.Factory.getInstance());
        registry2.append("Bitmap", GifDecoder.class, Bitmap.class, new GifFrameResourceDecoder(bitmapPool3));
        registry2.append(Uri.class, Drawable.class, resourceDrawableDecoder);
        registry2.append(Uri.class, Bitmap.class, new ResourceBitmapDecoder(resourceDrawableDecoder, bitmapPool3));
        registry2.register((DataRewinder.Factory<?>) new ByteBufferRewinder.Factory());
        registry2.append(File.class, ByteBuffer.class, new ByteBufferFileLoader.Factory());
        registry2.append(File.class, InputStream.class, new FileLoader.StreamFactory());
        registry2.append(File.class, File.class, new FileDecoder());
        registry2.append(File.class, ParcelFileDescriptor.class, new FileLoader.FileDescriptorFactory());
        registry2.append(File.class, File.class, UnitModelLoader.Factory.getInstance());
        registry2.register((DataRewinder.Factory<?>) new InputStreamRewinder.Factory(arrayPool3));
        registry2.append(Integer.TYPE, InputStream.class, streamFactory);
        ResourceLoader.FileDescriptorFactory fileDescriptorFactory2 = fileDescriptorFactory;
        registry2.append(Integer.TYPE, ParcelFileDescriptor.class, fileDescriptorFactory2);
        registry2.append(Integer.class, InputStream.class, streamFactory);
        registry2.append(Integer.class, ParcelFileDescriptor.class, fileDescriptorFactory2);
        ResourceLoader.UriFactory uriFactory2 = uriFactory;
        registry2.append(Integer.class, Uri.class, uriFactory2);
        ResourceLoader.AssetFileDescriptorFactory assetFileDescriptorFactory2 = assetFileDescriptorFactory;
        registry2.append(Integer.TYPE, AssetFileDescriptor.class, assetFileDescriptorFactory2);
        registry2.append(Integer.class, AssetFileDescriptor.class, assetFileDescriptorFactory2);
        registry2.append(Integer.TYPE, Uri.class, uriFactory2);
        registry2.append(String.class, InputStream.class, new DataUrlLoader.StreamFactory());
        registry2.append(String.class, InputStream.class, new StringLoader.StreamFactory());
        registry2.append(String.class, ParcelFileDescriptor.class, new StringLoader.FileDescriptorFactory());
        registry2.append(String.class, AssetFileDescriptor.class, new StringLoader.AssetFileDescriptorFactory());
        registry2.append(Uri.class, InputStream.class, new HttpUriLoader.Factory());
        registry2.append(Uri.class, InputStream.class, new AssetUriLoader.StreamFactory(context.getAssets()));
        registry2.append(Uri.class, ParcelFileDescriptor.class, new AssetUriLoader.FileDescriptorFactory(context.getAssets()));
        Context context3 = context;
        registry2.append(Uri.class, InputStream.class, new MediaStoreImageThumbLoader.Factory(context3));
        registry2.append(Uri.class, InputStream.class, new MediaStoreVideoThumbLoader.Factory(context3));
        ContentResolver contentResolver2 = contentResolver;
        registry2.append(Uri.class, InputStream.class, new UriLoader.StreamFactory(contentResolver2));
        registry2.append(Uri.class, ParcelFileDescriptor.class, new UriLoader.FileDescriptorFactory(contentResolver2));
        registry2.append(Uri.class, AssetFileDescriptor.class, new UriLoader.AssetFileDescriptorFactory(contentResolver2));
        registry2.append(Uri.class, InputStream.class, new UrlUriLoader.StreamFactory());
        registry2.append(URL.class, InputStream.class, new UrlLoader.StreamFactory());
        registry2.append(Uri.class, File.class, new MediaStoreFileLoader.Factory(context3));
        registry2.append(GlideUrl.class, InputStream.class, new HttpGlideUrlLoader.Factory());
        registry2.append(byte[].class, ByteBuffer.class, new ByteArrayLoader.ByteBufferFactory());
        registry2.append(byte[].class, InputStream.class, new ByteArrayLoader.StreamFactory());
        registry2.append(Uri.class, Uri.class, UnitModelLoader.Factory.getInstance());
        registry2.append(Drawable.class, Drawable.class, UnitModelLoader.Factory.getInstance());
        registry2.append(Drawable.class, Drawable.class, new UnitDrawableDecoder());
        registry2.register(Bitmap.class, BitmapDrawable.class, new BitmapDrawableTranscoder(resources));
        BitmapBytesTranscoder bitmapBytesTranscoder2 = bitmapBytesTranscoder;
        registry2.register(Bitmap.class, byte[].class, bitmapBytesTranscoder2);
        GifDrawableBytesTranscoder gifDrawableBytesTranscoder2 = gifDrawableBytesTranscoder;
        registry2.register(Drawable.class, byte[].class, new DrawableBytesTranscoder(bitmapPool3, bitmapBytesTranscoder2, gifDrawableBytesTranscoder2));
        registry2.register(GifDrawable.class, byte[].class, gifDrawableBytesTranscoder2);
        this.glideContext = new GlideContext(context, arrayPool2, this.registry, new ImageViewTargetFactory(), requestOptions, map, engine2, i);
    }

    public static Glide get(Context context) {
        if (glide == null) {
            synchronized (Glide.class) {
                if (glide == null) {
                    if (!isInitializing) {
                        isInitializing = true;
                        initializeGlide(context, new GlideBuilder());
                        isInitializing = false;
                    } else {
                        throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
                    }
                }
            }
        }
        return glide;
    }

    private static RequestManagerRetriever getRetriever(Context context) {
        R$style.checkNotNull(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return get(context).requestManagerRetriever;
    }

    @Deprecated
    public static synchronized void init(Glide glide2) {
        synchronized (Glide.class) {
            if (glide != null) {
                tearDown();
            }
            glide = glide2;
        }
    }

    private static void initializeGlide(Context context, GlideBuilder glideBuilder) {
        GeneratedAppGlideModule generatedAppGlideModule;
        Context applicationContext = context.getApplicationContext();
        GeneratedRequestManagerFactory generatedRequestManagerFactory = null;
        try {
            generatedAppGlideModule = (GeneratedAppGlideModule) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable("Glide", 5)) {
                Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
            generatedAppGlideModule = null;
        } catch (InstantiationException e) {
            throwIncorrectGlideModule(e);
            throw null;
        } catch (IllegalAccessException e2) {
            throwIncorrectGlideModule(e2);
            throw null;
        } catch (NoSuchMethodException e3) {
            throwIncorrectGlideModule(e3);
            throw null;
        } catch (InvocationTargetException e4) {
            throwIncorrectGlideModule(e4);
            throw null;
        }
        List<GlideModule> emptyList = Collections.emptyList();
        if (generatedAppGlideModule == null || generatedAppGlideModule.isManifestParsingEnabled()) {
            emptyList = new ManifestParser(applicationContext).parse();
        }
        if (generatedAppGlideModule != null) {
            GeneratedAppGlideModuleImpl generatedAppGlideModuleImpl = (GeneratedAppGlideModuleImpl) generatedAppGlideModule;
            if (!Collections.emptySet().isEmpty()) {
                Set emptySet = Collections.emptySet();
                Iterator<GlideModule> it = emptyList.iterator();
                while (it.hasNext()) {
                    GlideModule next = it.next();
                    if (emptySet.contains(next.getClass())) {
                        if (Log.isLoggable("Glide", 3)) {
                            String valueOf = String.valueOf(next);
                            StringBuilder sb = new StringBuilder(valueOf.length() + 46);
                            sb.append("AppGlideModule excludes manifest GlideModule: ");
                            sb.append(valueOf);
                            Log.d("Glide", sb.toString());
                        }
                        it.remove();
                    }
                }
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            for (GlideModule glideModule : emptyList) {
                String valueOf2 = String.valueOf(glideModule.getClass());
                StringBuilder sb2 = new StringBuilder(valueOf2.length() + 38);
                sb2.append("Discovered GlideModule from manifest: ");
                sb2.append(valueOf2);
                Log.d("Glide", sb2.toString());
            }
        }
        if (generatedAppGlideModule != null) {
            GeneratedAppGlideModuleImpl generatedAppGlideModuleImpl2 = (GeneratedAppGlideModuleImpl) generatedAppGlideModule;
            generatedRequestManagerFactory = new GeneratedRequestManagerFactory();
        }
        glideBuilder.setRequestManagerFactory(generatedRequestManagerFactory);
        for (GlideModule applyOptions : emptyList) {
            applyOptions.applyOptions(applicationContext, glideBuilder);
        }
        if (generatedAppGlideModule != null) {
            generatedAppGlideModule.applyOptions(applicationContext, glideBuilder);
        }
        Glide build = glideBuilder.build(applicationContext);
        for (GlideModule registerComponents : emptyList) {
            registerComponents.registerComponents(applicationContext, build, build.registry);
        }
        if (generatedAppGlideModule != null) {
            generatedAppGlideModule.registerComponents(applicationContext, build, build.registry);
        }
        applicationContext.registerComponentCallbacks(build);
        glide = build;
    }

    public static synchronized void tearDown() {
        synchronized (Glide.class) {
            if (glide != null) {
                glide.getContext().getApplicationContext().unregisterComponentCallbacks(glide);
                glide.engine.shutdown();
            }
            glide = null;
        }
    }

    private static void throwIncorrectGlideModule(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    public static RequestManager with(Context context) {
        return getRetriever(context).get(context);
    }

    public ArrayPool getArrayPool() {
        return this.arrayPool;
    }

    public BitmapPool getBitmapPool() {
        return this.bitmapPool;
    }

    /* access modifiers changed from: package-private */
    public ConnectivityMonitorFactory getConnectivityMonitorFactory() {
        return this.connectivityMonitorFactory;
    }

    public Context getContext() {
        return this.glideContext.getBaseContext();
    }

    /* access modifiers changed from: package-private */
    public GlideContext getGlideContext() {
        return this.glideContext;
    }

    public Registry getRegistry() {
        return this.registry;
    }

    public RequestManagerRetriever getRequestManagerRetriever() {
        return this.requestManagerRetriever;
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
        Util.assertMainThread();
        ((LruCache) this.memoryCache).clearMemory();
        this.bitmapPool.clearMemory();
        this.arrayPool.clearMemory();
    }

    public void onTrimMemory(int i) {
        Util.assertMainThread();
        ((LruResourceCache) this.memoryCache).trimMemory(i);
        this.bitmapPool.trimMemory(i);
        this.arrayPool.trimMemory(i);
    }

    /* access modifiers changed from: package-private */
    public void registerRequestManager(RequestManager requestManager) {
        synchronized (this.managers) {
            if (!this.managers.contains(requestManager)) {
                this.managers.add(requestManager);
            } else {
                throw new IllegalStateException("Cannot register already registered manager");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean removeFromManagers(Target<?> target) {
        synchronized (this.managers) {
            for (RequestManager untrack : this.managers) {
                if (untrack.untrack(target)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void unregisterRequestManager(RequestManager requestManager) {
        synchronized (this.managers) {
            if (this.managers.contains(requestManager)) {
                this.managers.remove(requestManager);
            } else {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
        }
    }

    public static RequestManager with(Fragment fragment) {
        return getRetriever(fragment.getActivity()).get(fragment);
    }

    public static RequestManager with(View view) {
        return getRetriever(view.getContext()).get(view);
    }

    public static synchronized void init(Context context, GlideBuilder glideBuilder) {
        synchronized (Glide.class) {
            if (glide != null) {
                tearDown();
            }
            initializeGlide(context, glideBuilder);
        }
    }
}
