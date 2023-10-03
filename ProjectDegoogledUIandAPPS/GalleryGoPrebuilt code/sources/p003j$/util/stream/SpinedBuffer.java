package p003j$.util.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import p003j$.util.DesugarArrays;
import p003j$.util.Spliterator;
import p003j$.util.Spliterator$$CC;
import p003j$.util.Spliterators;
import p003j$.util.function.Consumer;

/* renamed from: j$.util.stream.SpinedBuffer */
abstract class SpinedBuffer extends AbstractSpinedBuffer implements Consumer, Iterable {
    protected Object[] curChunk = new Object[(1 << this.initialChunkPower)];
    protected Object[][] spine;

    SpinedBuffer() {
    }

    /* access modifiers changed from: protected */
    public long capacity() {
        int i = this.spineIndex;
        if (i == 0) {
            return (long) this.curChunk.length;
        }
        return ((long) this.spine[i].length) + this.priorElementCount[i];
    }

    private void inflateSpine() {
        if (this.spine == null) {
            Object[][] objArr = new Object[8][];
            this.spine = objArr;
            this.priorElementCount = new long[8];
            objArr[0] = this.curChunk;
        }
    }

    /* access modifiers changed from: protected */
    public final void ensureCapacity(long j) {
        long capacity = capacity();
        if (j > capacity) {
            inflateSpine();
            int i = this.spineIndex;
            while (true) {
                i++;
                if (j > capacity) {
                    Object[][] objArr = this.spine;
                    if (i >= objArr.length) {
                        int length = objArr.length * 2;
                        this.spine = (Object[][]) Arrays.copyOf(objArr, length);
                        this.priorElementCount = Arrays.copyOf(this.priorElementCount, length);
                    }
                    int chunkSize = chunkSize(i);
                    Object[][] objArr2 = this.spine;
                    objArr2[i] = new Object[chunkSize];
                    long[] jArr = this.priorElementCount;
                    int i2 = i - 1;
                    jArr[i] = jArr[i2] + ((long) objArr2[i2].length);
                    capacity += (long) chunkSize;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void increaseCapacity() {
        ensureCapacity(capacity() + 1);
    }

    public void clear() {
        Object[][] objArr = this.spine;
        if (objArr != null) {
            this.curChunk = objArr[0];
            int i = 0;
            while (true) {
                Object[] objArr2 = this.curChunk;
                if (i >= objArr2.length) {
                    break;
                }
                objArr2[i] = null;
                i++;
            }
            this.spine = null;
            this.priorElementCount = null;
        } else {
            for (int i2 = 0; i2 < this.elementIndex; i2++) {
                this.curChunk[i2] = null;
            }
        }
        this.elementIndex = 0;
        this.spineIndex = 0;
    }

    public Iterator iterator() {
        return Spliterators.iterator(spliterator());
    }

    public void forEach(Consumer consumer) {
        for (int i = 0; i < this.spineIndex; i++) {
            for (Object accept : this.spine[i]) {
                consumer.accept(accept);
            }
        }
        for (int i2 = 0; i2 < this.elementIndex; i2++) {
            consumer.accept(this.curChunk[i2]);
        }
    }

    public void accept(Object obj) {
        if (this.elementIndex == this.curChunk.length) {
            inflateSpine();
            int i = this.spineIndex;
            int i2 = i + 1;
            Object[][] objArr = this.spine;
            if (i2 >= objArr.length || objArr[i + 1] == null) {
                increaseCapacity();
            }
            this.elementIndex = 0;
            int i3 = this.spineIndex + 1;
            this.spineIndex = i3;
            this.curChunk = this.spine[i3];
        }
        Object[] objArr2 = this.curChunk;
        int i4 = this.elementIndex;
        this.elementIndex = i4 + 1;
        objArr2[i4] = obj;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList();
        arrayList.getClass();
        forEach(SpinedBuffer$$Lambda$0.get$Lambda(arrayList));
        String valueOf = String.valueOf(arrayList.toString());
        return valueOf.length() != 0 ? "SpinedBuffer:".concat(valueOf) : new String("SpinedBuffer:");
    }

    /* renamed from: j$.util.stream.SpinedBuffer$1Splitr  reason: invalid class name */
    class AnonymousClass1Splitr implements Spliterator {
        final int lastSpineElementFence;
        final int lastSpineIndex;
        Object[] splChunk;
        int splElementIndex;
        int splSpineIndex;

        public int characteristics() {
            return 16464;
        }

        public Comparator getComparator() {
            Spliterator$$CC.getComparator$$dflt$$(this);
            throw null;
        }

        public long getExactSizeIfKnown() {
            return Spliterator$$CC.getExactSizeIfKnown$$dflt$$(this);
        }

        static {
            Class<SpinedBuffer> cls = SpinedBuffer.class;
        }

        {
            this.splSpineIndex = r2;
            this.lastSpineIndex = r3;
            this.splElementIndex = r4;
            this.lastSpineElementFence = r5;
            Object[][] objArr = SpinedBuffer.this.spine;
            this.splChunk = objArr == null ? SpinedBuffer.this.curChunk : objArr[r2];
        }

        public long estimateSize() {
            int i = this.splSpineIndex;
            int i2 = this.lastSpineIndex;
            if (i == i2) {
                return ((long) this.lastSpineElementFence) - ((long) this.splElementIndex);
            }
            long[] jArr = SpinedBuffer.this.priorElementCount;
            return ((jArr[i2] + ((long) this.lastSpineElementFence)) - jArr[i]) - ((long) this.splElementIndex);
        }

        public boolean tryAdvance(Consumer consumer) {
            consumer.getClass();
            int i = this.splSpineIndex;
            int i2 = this.lastSpineIndex;
            if (i >= i2 && (i != i2 || this.splElementIndex >= this.lastSpineElementFence)) {
                return false;
            }
            Object[] objArr = this.splChunk;
            int i3 = this.splElementIndex;
            this.splElementIndex = i3 + 1;
            consumer.accept(objArr[i3]);
            if (this.splElementIndex == this.splChunk.length) {
                this.splElementIndex = 0;
                int i4 = this.splSpineIndex + 1;
                this.splSpineIndex = i4;
                Object[][] objArr2 = SpinedBuffer.this.spine;
                if (objArr2 != null && i4 <= this.lastSpineIndex) {
                    this.splChunk = objArr2[i4];
                }
            }
            return true;
        }

        public void forEachRemaining(Consumer consumer) {
            int i;
            consumer.getClass();
            int i2 = this.splSpineIndex;
            int i3 = this.lastSpineIndex;
            if (i2 < i3 || (i2 == i3 && this.splElementIndex < this.lastSpineElementFence)) {
                int i4 = this.splElementIndex;
                int i5 = this.splSpineIndex;
                while (true) {
                    i = this.lastSpineIndex;
                    if (i5 >= i) {
                        break;
                    }
                    Object[] objArr = SpinedBuffer.this.spine[i5];
                    while (i4 < objArr.length) {
                        consumer.accept(objArr[i4]);
                        i4++;
                    }
                    i4 = 0;
                    i5++;
                }
                Object[] objArr2 = this.splSpineIndex == i ? this.splChunk : SpinedBuffer.this.spine[i];
                int i6 = this.lastSpineElementFence;
                while (i4 < i6) {
                    consumer.accept(objArr2[i4]);
                    i4++;
                }
                this.splSpineIndex = this.lastSpineIndex;
                this.splElementIndex = this.lastSpineElementFence;
            }
        }

        public Spliterator trySplit() {
            int i = this.splSpineIndex;
            int i2 = this.lastSpineIndex;
            if (i < i2) {
                SpinedBuffer spinedBuffer = SpinedBuffer.this;
                AnonymousClass1Splitr r0 = new AnonymousClass1Splitr(i, i2 - 1, this.splElementIndex, spinedBuffer.spine[i2 - 1].length);
                int i3 = this.lastSpineIndex;
                this.splSpineIndex = i3;
                this.splElementIndex = 0;
                this.splChunk = SpinedBuffer.this.spine[i3];
                return r0;
            } else if (i != i2) {
                return null;
            } else {
                int i4 = this.lastSpineElementFence;
                int i5 = this.splElementIndex;
                int i6 = (i4 - i5) / 2;
                if (i6 == 0) {
                    return null;
                }
                Spliterator spliterator = DesugarArrays.spliterator(this.splChunk, i5, i5 + i6);
                this.splElementIndex += i6;
                return spliterator;
            }
        }
    }

    public Spliterator spliterator() {
        return new AnonymousClass1Splitr(0, this.spineIndex, 0, this.elementIndex);
    }
}
