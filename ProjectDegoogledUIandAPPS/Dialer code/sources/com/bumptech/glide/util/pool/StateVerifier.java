package com.bumptech.glide.util.pool;

public abstract class StateVerifier {

    private static class DefaultStateVerifier extends StateVerifier {
        private volatile boolean isReleased;

        DefaultStateVerifier() {
            super((C08371) null);
        }

        public void setRecycled(boolean z) {
            this.isReleased = z;
        }

        public void throwIfRecycled() {
            if (this.isReleased) {
                throw new IllegalStateException("Already released");
            }
        }
    }

    /* synthetic */ StateVerifier(C08371 r1) {
    }

    public static StateVerifier newInstance() {
        return new DefaultStateVerifier();
    }

    /* access modifiers changed from: package-private */
    public abstract void setRecycled(boolean z);

    public abstract void throwIfRecycled();
}
