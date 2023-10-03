package com.google.common.collect;

import java.io.Serializable;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

final class ImmutableMapValues<K, V> extends ImmutableCollection<V> {
    /* access modifiers changed from: private */
    public final ImmutableMap<K, V> map;

    private static class SerializedForm<V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<?, V> map;

        SerializedForm(ImmutableMap<?, V> immutableMap) {
            this.map = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.map.values();
        }
    }

    ImmutableMapValues(ImmutableMap<K, V> immutableMap) {
        this.map = immutableMap;
    }

    public ImmutableList<V> asList() {
        final ImmutableList<Map.Entry<K, V>> asList = this.map.entrySet().asList();
        return new ImmutableAsList<V>() {
            /* access modifiers changed from: package-private */
            public ImmutableCollection<V> delegateCollection() {
                return ImmutableMapValues.this;
            }

            public V get(int i) {
                return ((Map.Entry) asList.get(i)).getValue();
            }
        };
    }

    public boolean contains(Object obj) {
        boolean z;
        if (obj != null) {
            UnmodifiableIterator it = iterator();
            while (true) {
                if (it.hasNext()) {
                    if (obj.equals(it.next())) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public void forEach(Consumer<? super V> consumer) {
        if (consumer != null) {
            this.map.forEach(new BiConsumer(consumer) {
                private final /* synthetic */ Consumer f$0;

                {
                    this.f$0 = r1;
                }

                public final void accept(Object obj, Object obj2) {
                    this.f$0.accept(obj2);
                }
            });
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return true;
    }

    public int size() {
        return this.map.size();
    }

    public Spliterator<V> spliterator() {
        return Collections2.map(this.map.entrySet().spliterator(), $$Lambda$73uG8GvDFSgCg7ViZNTbzvdqilI.INSTANCE);
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this.map);
    }

    public UnmodifiableIterator<V> iterator() {
        return new UnmodifiableIterator<V>() {
            final UnmodifiableIterator<Map.Entry<K, V>> entryItr = ImmutableMapValues.this.map.entrySet().iterator();

            public boolean hasNext() {
                return this.entryItr.hasNext();
            }

            public V next() {
                return this.entryItr.next().getValue();
            }
        };
    }
}
