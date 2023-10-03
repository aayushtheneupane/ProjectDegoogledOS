package p003j$.util;

import android.support.p002v7.widget.RecyclerView;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import p003j$.util.Spliterator;
import p003j$.util.function.Consumer;
import p003j$.util.function.DoubleConsumer;
import p003j$.util.function.IntConsumer;
import p003j$.util.function.LongConsumer;

/* renamed from: j$.util.Spliterators */
public abstract class Spliterators {
    private static final Spliterator.OfDouble EMPTY_DOUBLE_SPLITERATOR = new EmptySpliterator.OfDouble();
    private static final Spliterator.OfInt EMPTY_INT_SPLITERATOR = new EmptySpliterator.OfInt();
    private static final Spliterator.OfLong EMPTY_LONG_SPLITERATOR = new EmptySpliterator.OfLong();
    private static final Spliterator EMPTY_SPLITERATOR = new EmptySpliterator.OfRef();

    public static Spliterator spliterator(Object[] objArr, int i, int i2, int i3) {
        objArr.getClass();
        checkFromToBounds(objArr.length, i, i2);
        return new ArraySpliterator(objArr, i, i2, i3);
    }

    private static void checkFromToBounds(int i, int i2, int i3) {
        if (i2 > i3) {
            StringBuilder sb = new StringBuilder(40);
            sb.append("origin(");
            sb.append(i2);
            sb.append(") > fence(");
            sb.append(i3);
            sb.append(")");
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        } else if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException(i2);
        } else if (i3 > i) {
            throw new ArrayIndexOutOfBoundsException(i3);
        }
    }

    public static Spliterator spliterator(Collection collection, int i) {
        collection.getClass();
        return new IteratorSpliterator(collection, i);
    }

    public static Iterator iterator(final Spliterator spliterator) {
        spliterator.getClass();
        return new Object() {
            Object nextElement;
            boolean valueReady = false;

            public void forEachRemaining(Consumer consumer) {
                Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
            }

            public void remove() {
                Iterator$$CC.remove$$dflt$$(this);
            }

            public void accept(Object obj) {
                this.valueReady = true;
                this.nextElement = obj;
            }

            public boolean hasNext() {
                if (!this.valueReady) {
                    Spliterator.this.tryAdvance(this);
                }
                return this.valueReady;
            }

            public Object next() {
                if (this.valueReady || hasNext()) {
                    this.valueReady = false;
                    return this.nextElement;
                }
                throw new NoSuchElementException();
            }
        };
    }

    /* renamed from: j$.util.Spliterators$EmptySpliterator */
    abstract class EmptySpliterator {
        public int characteristics() {
            return 16448;
        }

        public long estimateSize() {
            return 0;
        }

        public Spliterator trySplit() {
            return null;
        }

        EmptySpliterator() {
        }

        public boolean tryAdvance(Object obj) {
            obj.getClass();
            return false;
        }

        public void forEachRemaining(Object obj) {
            obj.getClass();
        }

        /* renamed from: j$.util.Spliterators$EmptySpliterator$OfRef */
        final class OfRef extends EmptySpliterator implements Spliterator {
            public Comparator getComparator() {
                Spliterator$$CC.getComparator$$dflt$$(this);
                throw null;
            }

            public long getExactSizeIfKnown() {
                return Spliterator$$CC.getExactSizeIfKnown$$dflt$$(this);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(Consumer consumer) {
                super.forEachRemaining(consumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return super.tryAdvance(consumer);
            }

            OfRef() {
            }
        }

        /* renamed from: j$.util.Spliterators$EmptySpliterator$OfInt */
        final class OfInt extends EmptySpliterator implements Spliterator.OfInt {
            public void forEachRemaining(Consumer consumer) {
                Spliterator$OfInt$$CC.forEachRemaining$$dflt$$(this, consumer);
            }

            public Comparator getComparator() {
                Spliterator$$CC.getComparator$$dflt$$(this);
                throw null;
            }

            public long getExactSizeIfKnown() {
                return Spliterator$$CC.getExactSizeIfKnown$$dflt$$(this);
            }

            public boolean tryAdvance(Consumer consumer) {
                return Spliterator$OfInt$$CC.tryAdvance$$dflt$$(this, consumer);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                super.forEachRemaining(intConsumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return super.tryAdvance(intConsumer);
            }

            OfInt() {
            }
        }

        /* renamed from: j$.util.Spliterators$EmptySpliterator$OfLong */
        final class OfLong extends EmptySpliterator implements Spliterator.OfLong {
            public void forEachRemaining(Consumer consumer) {
                Spliterator$OfLong$$CC.forEachRemaining$$dflt$$(this, consumer);
            }

            public Comparator getComparator() {
                Spliterator$$CC.getComparator$$dflt$$(this);
                throw null;
            }

            public long getExactSizeIfKnown() {
                return Spliterator$$CC.getExactSizeIfKnown$$dflt$$(this);
            }

            public boolean tryAdvance(Consumer consumer) {
                return Spliterator$OfLong$$CC.tryAdvance$$dflt$$(this, consumer);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                super.forEachRemaining(longConsumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return super.tryAdvance(longConsumer);
            }

            OfLong() {
            }
        }

        /* renamed from: j$.util.Spliterators$EmptySpliterator$OfDouble */
        final class OfDouble extends EmptySpliterator implements Spliterator.OfDouble {
            public void forEachRemaining(Consumer consumer) {
                Spliterator$OfDouble$$CC.forEachRemaining$$dflt$$(this, consumer);
            }

            public Comparator getComparator() {
                Spliterator$$CC.getComparator$$dflt$$(this);
                throw null;
            }

            public long getExactSizeIfKnown() {
                return Spliterator$$CC.getExactSizeIfKnown$$dflt$$(this);
            }

            public boolean tryAdvance(Consumer consumer) {
                return Spliterator$OfDouble$$CC.tryAdvance$$dflt$$(this, consumer);
            }

            public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                super.forEachRemaining(doubleConsumer);
            }

            public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return super.tryAdvance(doubleConsumer);
            }

            OfDouble() {
            }
        }
    }

    /* renamed from: j$.util.Spliterators$ArraySpliterator */
    final class ArraySpliterator implements Spliterator {
        private final Object[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public long getExactSizeIfKnown() {
            return Spliterator$$CC.getExactSizeIfKnown$$dflt$$(this);
        }

        public boolean hasCharacteristics(int i) {
            return Spliterator$$CC.hasCharacteristics$$dflt$$(this, i);
        }

        public ArraySpliterator(Object[] objArr, int i, int i2, int i3) {
            this.array = objArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 64 | 16384;
        }

        public Spliterator trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            Object[] objArr = this.array;
            this.index = i2;
            return new ArraySpliterator(objArr, i, i2, this.characteristics);
        }

        public void forEachRemaining(Consumer consumer) {
            int i;
            if (consumer != null) {
                Object[] objArr = this.array;
                int length = objArr.length;
                int i2 = this.fence;
                if (length >= i2 && (i = this.index) >= 0) {
                    this.index = i2;
                    if (i < i2) {
                        do {
                            consumer.accept(objArr[i]);
                            i++;
                        } while (i < i2);
                        return;
                    }
                    return;
                }
                return;
            }
            throw null;
        }

        public boolean tryAdvance(Consumer consumer) {
            if (consumer != null) {
                int i = this.index;
                if (i < 0 || i >= this.fence) {
                    return false;
                }
                Object[] objArr = this.array;
                this.index = i + 1;
                consumer.accept(objArr[i]);
                return true;
            }
            throw null;
        }

        public long estimateSize() {
            return (long) (this.fence - this.index);
        }

        public int characteristics() {
            return this.characteristics;
        }

        public Comparator getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: j$.util.Spliterators$IteratorSpliterator */
    class IteratorSpliterator implements Spliterator {
        private int batch;
        private final int characteristics;
        private final Collection collection;
        private long est;

        /* renamed from: it */
        private Iterator f16483it = null;

        public long getExactSizeIfKnown() {
            return Spliterator$$CC.getExactSizeIfKnown$$dflt$$(this);
        }

        public boolean hasCharacteristics(int i) {
            return Spliterator$$CC.hasCharacteristics$$dflt$$(this, i);
        }

        public IteratorSpliterator(Collection collection2, int i) {
            this.collection = collection2;
            this.characteristics = (i & 4096) == 0 ? i | 64 | 16384 : i;
        }

        public Spliterator trySplit() {
            long j;
            Iterator it = this.f16483it;
            if (it == null) {
                it = this.collection.iterator();
                this.f16483it = it;
                j = (long) this.collection.size();
                this.est = j;
            } else {
                j = this.est;
            }
            if (j <= 1 || !it.hasNext()) {
                return null;
            }
            int i = this.batch + 1024;
            if (((long) i) > j) {
                i = (int) j;
            }
            if (i > 33554432) {
                i = 33554432;
            }
            Object[] objArr = new Object[i];
            int i2 = 0;
            do {
                objArr[i2] = it.next();
                i2++;
                if (i2 >= i || !it.hasNext()) {
                    this.batch = i2;
                    long j2 = this.est;
                }
                objArr[i2] = it.next();
                i2++;
                break;
            } while (!it.hasNext());
            this.batch = i2;
            long j22 = this.est;
            if (j22 != RecyclerView.FOREVER_NS) {
                this.est = j22 - ((long) i2);
            }
            return new ArraySpliterator(objArr, 0, i2, this.characteristics);
        }

        public void forEachRemaining(Consumer consumer) {
            if (consumer != null) {
                Iterator it = this.f16483it;
                if (it == null) {
                    it = this.collection.iterator();
                    this.f16483it = it;
                    this.est = (long) this.collection.size();
                }
                Iterator$$Dispatch.forEachRemaining(it, consumer);
                return;
            }
            throw null;
        }

        public boolean tryAdvance(Consumer consumer) {
            if (consumer != null) {
                if (this.f16483it == null) {
                    this.f16483it = this.collection.iterator();
                    this.est = (long) this.collection.size();
                }
                if (!this.f16483it.hasNext()) {
                    return false;
                }
                consumer.accept(this.f16483it.next());
                return true;
            }
            throw null;
        }

        public long estimateSize() {
            if (this.f16483it != null) {
                return this.est;
            }
            this.f16483it = this.collection.iterator();
            long size = (long) this.collection.size();
            this.est = size;
            return size;
        }

        public int characteristics() {
            return this.characteristics;
        }

        public Comparator getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }
}
