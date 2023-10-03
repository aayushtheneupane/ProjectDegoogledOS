package com.bumptech.glide.load.engine;

import android.support.p000v4.util.Pools$Pool;
import android.support.p002v7.appcompat.R$style;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DecodePath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadPath<Data, ResourceType, Transcode> {
    private final List<? extends DecodePath<Data, ResourceType, Transcode>> decodePaths;
    private final String failureMessage;
    private final Pools$Pool<List<Throwable>> listPool;

    public LoadPath(Class<Data> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<DecodePath<Data, ResourceType, Transcode>> list, Pools$Pool<List<Throwable>> pools$Pool) {
        this.listPool = pools$Pool;
        if (!list.isEmpty()) {
            this.decodePaths = list;
            String simpleName = cls.getSimpleName();
            String simpleName2 = cls2.getSimpleName();
            String simpleName3 = cls3.getSimpleName();
            StringBuilder sb = new StringBuilder(simpleName3.length() + simpleName2.length() + simpleName.length() + 21);
            sb.append("Failed LoadPath{");
            sb.append(simpleName);
            sb.append("->");
            sb.append(simpleName2);
            sb.append("->");
            sb.append(simpleName3);
            sb.append("}");
            this.failureMessage = sb.toString();
            return;
        }
        throw new IllegalArgumentException("Must not be empty.");
    }

    public Resource<Transcode> load(DataRewinder<Data> dataRewinder, Options options, int i, int i2, DecodePath.DecodeCallback<ResourceType> decodeCallback) throws GlideException {
        Resource<Transcode> resource;
        List acquire = this.listPool.acquire();
        R$style.checkNotNull(acquire, "Argument must not be null");
        try {
            int size = this.decodePaths.size();
            resource = null;
            for (int i3 = 0; i3 < size; i3++) {
                resource = ((DecodePath) this.decodePaths.get(i3)).decode(dataRewinder, i, i2, options, decodeCallback);
                if (resource != null) {
                    break;
                }
            }
        } catch (GlideException e) {
            acquire.add(e);
        } catch (Throwable th) {
            this.listPool.release(acquire);
            throw th;
        }
        if (resource != null) {
            this.listPool.release(acquire);
            return resource;
        }
        throw new GlideException(this.failureMessage, (List<Throwable>) new ArrayList(acquire));
    }

    public String toString() {
        String arrays = Arrays.toString(this.decodePaths.toArray());
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport.outline1(arrays, 22));
        sb.append("LoadPath{decodePaths=");
        sb.append(arrays);
        sb.append('}');
        return sb.toString();
    }
}
