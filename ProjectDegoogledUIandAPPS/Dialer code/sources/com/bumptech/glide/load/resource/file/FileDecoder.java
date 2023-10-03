package com.bumptech.glide.load.resource.file;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.File;
import java.io.IOException;

public class FileDecoder implements ResourceDecoder<File, File> {
    public Resource decode(Object obj, int i, int i2, Options options) throws IOException {
        return new FileResource((File) obj);
    }

    public boolean handles(Object obj, Options options) throws IOException {
        File file = (File) obj;
        return true;
    }
}
