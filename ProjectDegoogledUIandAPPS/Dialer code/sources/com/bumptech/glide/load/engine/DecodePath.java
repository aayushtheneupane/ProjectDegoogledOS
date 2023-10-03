package com.bumptech.glide.load.engine;

import android.support.p000v4.util.Pools$Pool;
import android.support.p002v7.appcompat.R$style;
import android.util.Log;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DecodePath<DataType, ResourceType, Transcode> {
    private final Class<DataType> dataClass;
    private final List<? extends ResourceDecoder<DataType, ResourceType>> decoders;
    private final String failureMessage;
    private final Pools$Pool<List<Throwable>> listPool;
    private final ResourceTranscoder<ResourceType, Transcode> transcoder;

    interface DecodeCallback<ResourceType> {
    }

    public DecodePath(Class<DataType> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<? extends ResourceDecoder<DataType, ResourceType>> list, ResourceTranscoder<ResourceType, Transcode> resourceTranscoder, Pools$Pool<List<Throwable>> pools$Pool) {
        this.dataClass = cls;
        this.decoders = list;
        this.transcoder = resourceTranscoder;
        this.listPool = pools$Pool;
        String simpleName = cls.getSimpleName();
        String simpleName2 = cls2.getSimpleName();
        String simpleName3 = cls3.getSimpleName();
        StringBuilder sb = new StringBuilder(simpleName3.length() + simpleName2.length() + simpleName.length() + 23);
        sb.append("Failed DecodePath{");
        sb.append(simpleName);
        sb.append("->");
        sb.append(simpleName2);
        sb.append("->");
        sb.append(simpleName3);
        sb.append("}");
        this.failureMessage = sb.toString();
    }

    private Resource<ResourceType> decodeResourceWithList(DataRewinder<DataType> dataRewinder, int i, int i2, Options options, List<Throwable> list) throws GlideException {
        int size = this.decoders.size();
        Resource<ResourceType> resource = null;
        for (int i3 = 0; i3 < size; i3++) {
            ResourceDecoder resourceDecoder = (ResourceDecoder) this.decoders.get(i3);
            try {
                if (resourceDecoder.handles(dataRewinder.rewindAndGet(), options)) {
                    resource = resourceDecoder.decode(dataRewinder.rewindAndGet(), i, i2, options);
                }
            } catch (IOException | OutOfMemoryError | RuntimeException e) {
                if (Log.isLoggable("DecodePath", 2)) {
                    String valueOf = String.valueOf(resourceDecoder);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 26);
                    sb.append("Failed to decode data for ");
                    sb.append(valueOf);
                    Log.v("DecodePath", sb.toString(), e);
                }
                list.add(e);
            }
            if (resource != null) {
                break;
            }
        }
        if (resource != null) {
            return resource;
        }
        throw new GlideException(this.failureMessage, (List<Throwable>) new ArrayList(list));
    }

    /* JADX INFO: finally extract failed */
    public Resource<Transcode> decode(DataRewinder<DataType> dataRewinder, int i, int i2, Options options, DecodeCallback<ResourceType> decodeCallback) throws GlideException {
        List acquire = this.listPool.acquire();
        R$style.checkNotNull(acquire, "Argument must not be null");
        try {
            Resource<ResourceType> decodeResourceWithList = decodeResourceWithList(dataRewinder, i, i2, options, acquire);
            this.listPool.release(acquire);
            return this.transcoder.transcode(((DecodeJob.DecodeCallback) decodeCallback).onResourceDecoded(decodeResourceWithList), options);
        } catch (Throwable th) {
            this.listPool.release(acquire);
            throw th;
        }
    }

    public String toString() {
        String valueOf = String.valueOf(this.dataClass);
        String valueOf2 = String.valueOf(this.decoders);
        String valueOf3 = String.valueOf(this.transcoder);
        StringBuilder sb = new StringBuilder(valueOf3.length() + valueOf2.length() + valueOf.length() + 47);
        sb.append("DecodePath{ dataClass=");
        sb.append(valueOf);
        sb.append(", decoders=");
        sb.append(valueOf2);
        sb.append(", transcoder=");
        sb.append(valueOf3);
        sb.append('}');
        return sb.toString();
    }
}
