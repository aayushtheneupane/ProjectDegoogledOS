package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import com.android.tools.p006r8.GeneratedOutlineSupport;

class AttributeStrategy implements LruPoolStrategy {

    static class Key implements Poolable {
        private Bitmap.Config config;
        private int height;
        private final KeyPool pool;
        private int width;

        public Key(KeyPool keyPool) {
            this.pool = keyPool;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) {
                return false;
            }
            Key key = (Key) obj;
            if (this.width == key.width && this.height == key.height && this.config == key.config) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i = ((this.width * 31) + this.height) * 31;
            Bitmap.Config config2 = this.config;
            return i + (config2 != null ? config2.hashCode() : 0);
        }

        public void offer() {
            this.pool.offer(this);
        }

        public String toString() {
            return AttributeStrategy.getBitmapString(this.width, this.height, this.config);
        }
    }

    static class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        /* access modifiers changed from: protected */
        public Key create() {
            return new Key(this);
        }
    }

    static String getBitmapString(int i, int i2, Bitmap.Config config) {
        String valueOf = String.valueOf(config);
        StringBuilder sb = new StringBuilder(valueOf.length() + 27);
        sb.append("[");
        sb.append(i);
        sb.append("x");
        sb.append(i2);
        return GeneratedOutlineSupport.outline12(sb, "], ", valueOf);
    }
}
