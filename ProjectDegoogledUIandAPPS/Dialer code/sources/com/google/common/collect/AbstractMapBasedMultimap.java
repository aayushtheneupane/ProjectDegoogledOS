package com.google.common.collect;

import WrappedCollection.WrappedIterator;
import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Spliterator;

abstract class AbstractMapBasedMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 2447537837011683357L;
    /* access modifiers changed from: private */
    public transient Map<K, Collection<V>> map;
    /* access modifiers changed from: private */
    public transient int totalSize;

    private class AsMap extends Maps$ViewCachingAbstractMap<K, Collection<V>> {
        final transient Map<K, Collection<V>> submap;

        class AsMapEntries extends Maps$EntrySet<K, Collection<V>> {
            AsMapEntries() {
            }

            public boolean contains(Object obj) {
                return Collections2.safeContains(AsMap.this.submap.entrySet(), obj);
            }

            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return new AsMapIterator();
            }

            /* access modifiers changed from: package-private */
            public Map<K, Collection<V>> map() {
                return AsMap.this;
            }

            public boolean remove(Object obj) {
                if (!Collections2.safeContains(AsMap.this.submap.entrySet(), obj)) {
                    return false;
                }
                AbstractMapBasedMultimap.access$400(AbstractMapBasedMultimap.this, ((Map.Entry) obj).getKey());
                return true;
            }

            public Spliterator<Map.Entry<K, Collection<V>>> spliterator() {
                return Collections2.map(AsMap.this.submap.entrySet().spliterator(), 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: RETURN  
                      (wrap: java.util.Spliterator<java.util.Map$Entry<K, java.util.Collection<V>>> : 0x0013: INVOKE  (r2v2 java.util.Spliterator<java.util.Map$Entry<K, java.util.Collection<V>>>) = 
                      (wrap: java.util.Spliterator<java.util.Map$Entry<K, java.util.Collection<V>>> : 0x0008: INVOKE  (r0v3 java.util.Spliterator<java.util.Map$Entry<K, java.util.Collection<V>>>) = 
                      (wrap: java.util.Set<java.util.Map$Entry<K, java.util.Collection<V>>> : 0x0004: INVOKE  (r0v2 java.util.Set<java.util.Map$Entry<K, java.util.Collection<V>>>) = 
                      (wrap: java.util.Map<K, java.util.Collection<V>> : 0x0002: IGET  (r0v1 java.util.Map<K, java.util.Collection<V>>) = 
                      (wrap: com.google.common.collect.AbstractMapBasedMultimap$AsMap : 0x0000: IGET  (r0v0 com.google.common.collect.AbstractMapBasedMultimap$AsMap) = 
                      (r2v0 'this' com.google.common.collect.AbstractMapBasedMultimap$AsMap$AsMapEntries A[THIS])
                     com.google.common.collect.AbstractMapBasedMultimap.AsMap.AsMapEntries.this$1 com.google.common.collect.AbstractMapBasedMultimap$AsMap)
                     com.google.common.collect.AbstractMapBasedMultimap.AsMap.submap java.util.Map)
                     java.util.Map.entrySet():java.util.Set type: INTERFACE)
                     java.util.Set.spliterator():java.util.Spliterator type: INTERFACE)
                      (wrap: com.google.common.collect.-$$Lambda$IlCt6YHMNbjdh9aTmFntexV042I : 0x0010: CONSTRUCTOR  (r1v0 com.google.common.collect.-$$Lambda$IlCt6YHMNbjdh9aTmFntexV042I) = 
                      (wrap: com.google.common.collect.AbstractMapBasedMultimap$AsMap : 0x000c: IGET  (r2v1 com.google.common.collect.AbstractMapBasedMultimap$AsMap) = 
                      (r2v0 'this' com.google.common.collect.AbstractMapBasedMultimap$AsMap$AsMapEntries A[THIS])
                     com.google.common.collect.AbstractMapBasedMultimap.AsMap.AsMapEntries.this$1 com.google.common.collect.AbstractMapBasedMultimap$AsMap)
                     call: com.google.common.collect.-$$Lambda$IlCt6YHMNbjdh9aTmFntexV042I.<init>(com.google.common.collect.AbstractMapBasedMultimap$AsMap):void type: CONSTRUCTOR)
                     com.google.common.collect.Collections2.map(java.util.Spliterator, java.util.function.Function):java.util.Spliterator type: STATIC)
                     in method: com.google.common.collect.AbstractMapBasedMultimap.AsMap.AsMapEntries.spliterator():java.util.Spliterator<java.util.Map$Entry<K, java.util.Collection<V>>>, dex: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: INVOKE  (r2v2 java.util.Spliterator<java.util.Map$Entry<K, java.util.Collection<V>>>) = 
                      (wrap: java.util.Spliterator<java.util.Map$Entry<K, java.util.Collection<V>>> : 0x0008: INVOKE  (r0v3 java.util.Spliterator<java.util.Map$Entry<K, java.util.Collection<V>>>) = 
                      (wrap: java.util.Set<java.util.Map$Entry<K, java.util.Collection<V>>> : 0x0004: INVOKE  (r0v2 java.util.Set<java.util.Map$Entry<K, java.util.Collection<V>>>) = 
                      (wrap: java.util.Map<K, java.util.Collection<V>> : 0x0002: IGET  (r0v1 java.util.Map<K, java.util.Collection<V>>) = 
                      (wrap: com.google.common.collect.AbstractMapBasedMultimap$AsMap : 0x0000: IGET  (r0v0 com.google.common.collect.AbstractMapBasedMultimap$AsMap) = 
                      (r2v0 'this' com.google.common.collect.AbstractMapBasedMultimap$AsMap$AsMapEntries A[THIS])
                     com.google.common.collect.AbstractMapBasedMultimap.AsMap.AsMapEntries.this$1 com.google.common.collect.AbstractMapBasedMultimap$AsMap)
                     com.google.common.collect.AbstractMapBasedMultimap.AsMap.submap java.util.Map)
                     java.util.Map.entrySet():java.util.Set type: INTERFACE)
                     java.util.Set.spliterator():java.util.Spliterator type: INTERFACE)
                      (wrap: com.google.common.collect.-$$Lambda$IlCt6YHMNbjdh9aTmFntexV042I : 0x0010: CONSTRUCTOR  (r1v0 com.google.common.collect.-$$Lambda$IlCt6YHMNbjdh9aTmFntexV042I) = 
                      (wrap: com.google.common.collect.AbstractMapBasedMultimap$AsMap : 0x000c: IGET  (r2v1 com.google.common.collect.AbstractMapBasedMultimap$AsMap) = 
                      (r2v0 'this' com.google.common.collect.AbstractMapBasedMultimap$AsMap$AsMapEntries A[THIS])
                     com.google.common.collect.AbstractMapBasedMultimap.AsMap.AsMapEntries.this$1 com.google.common.collect.AbstractMapBasedMultimap$AsMap)
                     call: com.google.common.collect.-$$Lambda$IlCt6YHMNbjdh9aTmFntexV042I.<init>(com.google.common.collect.AbstractMapBasedMultimap$AsMap):void type: CONSTRUCTOR)
                     com.google.common.collect.Collections2.map(java.util.Spliterator, java.util.function.Function):java.util.Spliterator type: STATIC in method: com.google.common.collect.AbstractMapBasedMultimap.AsMap.AsMapEntries.spliterator():java.util.Spliterator<java.util.Map$Entry<K, java.util.Collection<V>>>, dex: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:314)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	... 59 more
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0010: CONSTRUCTOR  (r1v0 com.google.common.collect.-$$Lambda$IlCt6YHMNbjdh9aTmFntexV042I) = 
                      (wrap: com.google.common.collect.AbstractMapBasedMultimap$AsMap : 0x000c: IGET  (r2v1 com.google.common.collect.AbstractMapBasedMultimap$AsMap) = 
                      (r2v0 'this' com.google.common.collect.AbstractMapBasedMultimap$AsMap$AsMapEntries A[THIS])
                     com.google.common.collect.AbstractMapBasedMultimap.AsMap.AsMapEntries.this$1 com.google.common.collect.AbstractMapBasedMultimap$AsMap)
                     call: com.google.common.collect.-$$Lambda$IlCt6YHMNbjdh9aTmFntexV042I.<init>(com.google.common.collect.AbstractMapBasedMultimap$AsMap):void type: CONSTRUCTOR in method: com.google.common.collect.AbstractMapBasedMultimap.AsMap.AsMapEntries.spliterator():java.util.Spliterator<java.util.Map$Entry<K, java.util.Collection<V>>>, dex: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	... 63 more
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.google.common.collect.-$$Lambda$IlCt6YHMNbjdh9aTmFntexV042I, state: NOT_LOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	... 69 more
                    */
                /*
                    this = this;
                    com.google.common.collect.AbstractMapBasedMultimap$AsMap r0 = com.google.common.collect.AbstractMapBasedMultimap.AsMap.this
                    java.util.Map<K, java.util.Collection<V>> r0 = r0.submap
                    java.util.Set r0 = r0.entrySet()
                    java.util.Spliterator r0 = r0.spliterator()
                    com.google.common.collect.AbstractMapBasedMultimap$AsMap r2 = com.google.common.collect.AbstractMapBasedMultimap.AsMap.this
                    com.google.common.collect.-$$Lambda$IlCt6YHMNbjdh9aTmFntexV042I r1 = new com.google.common.collect.-$$Lambda$IlCt6YHMNbjdh9aTmFntexV042I
                    r1.<init>(r2)
                    java.util.Spliterator r2 = com.google.common.collect.Collections2.map(r0, r1)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.AsMap.AsMapEntries.spliterator():java.util.Spliterator");
            }
        }

        class AsMapIterator implements Iterator<Map.Entry<K, Collection<V>>> {
            Collection<V> collection;
            final Iterator<Map.Entry<K, Collection<V>>> delegateIterator = AsMap.this.submap.entrySet().iterator();

            AsMapIterator() {
            }

            public boolean hasNext() {
                return this.delegateIterator.hasNext();
            }

            public Object next() {
                Map.Entry next = this.delegateIterator.next();
                this.collection = (Collection) next.getValue();
                return AsMap.this.wrapEntry(next);
            }

            public void remove() {
                this.delegateIterator.remove();
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                int unused = abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize - this.collection.size();
                this.collection.clear();
            }
        }

        AsMap(Map<K, Collection<V>> map) {
            this.submap = map;
        }

        public void clear() {
            if (this.submap == AbstractMapBasedMultimap.this.map) {
                AbstractMapBasedMultimap.this.clear();
                return;
            }
            Iterator<Map.Entry<K, Collection<V>>> it = this.submap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                Collection collection = (Collection) next.getValue();
                wrapEntry(next);
                it.remove();
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                int unused = abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize - collection.size();
                collection.clear();
            }
        }

        public boolean containsKey(Object obj) {
            Map<K, Collection<V>> map = this.submap;
            if (map != null) {
                try {
                    return map.containsKey(obj);
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            } else {
                throw new NullPointerException();
            }
        }

        /* access modifiers changed from: protected */
        public Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new AsMapEntries();
        }

        public boolean equals(Object obj) {
            return this == obj || this.submap.equals(obj);
        }

        public Object get(Object obj) {
            Collection collection = (Collection) Collections2.safeGet(this.submap, obj);
            if (collection == null) {
                return null;
            }
            return AbstractMapBasedMultimap.this.wrapCollection(obj, collection);
        }

        public int hashCode() {
            return this.submap.hashCode();
        }

        public Set<K> keySet() {
            return AbstractMapBasedMultimap.this.keySet();
        }

        public Object remove(Object obj) {
            Collection remove = this.submap.remove(obj);
            if (remove == null) {
                return null;
            }
            Collection createCollection = AbstractMapBasedMultimap.this.createCollection();
            createCollection.addAll(remove);
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            int unused = abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize - remove.size();
            remove.clear();
            return createCollection;
        }

        public int size() {
            return this.submap.size();
        }

        public String toString() {
            return this.submap.toString();
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, Collection<V>> wrapEntry(Map.Entry<K, Collection<V>> entry) {
            K key = entry.getKey();
            return new ImmutableEntry(key, AbstractMapBasedMultimap.this.wrapCollection(key, entry.getValue()));
        }
    }

    private abstract class Itr<T> implements Iterator<T> {
        Collection<V> collection = null;
        K key = null;
        final Iterator<Map.Entry<K, Collection<V>>> keyIterator;
        Iterator<V> valueIterator = Iterators$EmptyModifiableIterator.INSTANCE;

        Itr() {
            this.keyIterator = AbstractMapBasedMultimap.this.map.entrySet().iterator();
        }

        public boolean hasNext() {
            return this.keyIterator.hasNext() || this.valueIterator.hasNext();
        }

        public T next() {
            if (!this.valueIterator.hasNext()) {
                Map.Entry next = this.keyIterator.next();
                this.key = next.getKey();
                this.collection = (Collection) next.getValue();
                this.valueIterator = this.collection.iterator();
            }
            C08552 r2 = (C08552) this;
            return new ImmutableEntry(this.key, this.valueIterator.next());
        }

        public void remove() {
            this.valueIterator.remove();
            if (this.collection.isEmpty()) {
                this.keyIterator.remove();
            }
            AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
        }
    }

    private class KeySet extends Maps$KeySet<K, Collection<V>> {
        KeySet(Map<K, Collection<V>> map) {
            super(map);
        }

        public void clear() {
            Iterator<Map.Entry<K, V>> it = this.map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                next.getKey();
                MoreObjects.checkState(next != null, "no calls to next() since the last call to remove()");
                Collection collection = (Collection) next.getValue();
                it.remove();
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                int unused = abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize - collection.size();
                collection.clear();
            }
        }

        public boolean containsAll(Collection<?> collection) {
            return map().keySet().containsAll(collection);
        }

        public boolean equals(Object obj) {
            return this == obj || map().keySet().equals(obj);
        }

        public int hashCode() {
            return map().keySet().hashCode();
        }

        public Iterator<K> iterator() {
            final Iterator<Map.Entry<K, V>> it = this.map.entrySet().iterator();
            return new Iterator<K>() {
                Map.Entry<K, Collection<V>> entry;

                public boolean hasNext() {
                    return it.hasNext();
                }

                public K next() {
                    this.entry = (Map.Entry) it.next();
                    return this.entry.getKey();
                }

                public void remove() {
                    MoreObjects.checkState(this.entry != null, "no calls to next() since the last call to remove()");
                    Collection value = this.entry.getValue();
                    it.remove();
                    AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                    int unused = abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize - value.size();
                    value.clear();
                }
            };
        }

        public boolean remove(Object obj) {
            int i;
            Collection collection = (Collection) this.map.remove(obj);
            if (collection != null) {
                i = collection.size();
                collection.clear();
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                int unused = abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize - i;
            } else {
                i = 0;
            }
            if (i > 0) {
                return true;
            }
            return false;
        }

        public Spliterator<K> spliterator() {
            return map().keySet().spliterator();
        }
    }

    private class RandomAccessWrappedList extends AbstractMapBasedMultimap<K, V>.WrappedList implements RandomAccess {
        RandomAccessWrappedList(AbstractMapBasedMultimap abstractMapBasedMultimap, K k, List<V> list, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k, list, wrappedCollection);
        }
    }

    private class SortedAsMap extends AbstractMapBasedMultimap<K, V>.AsMap implements SortedMap<K, Collection<V>> {
        SortedSet<K> sortedKeySet;

        SortedAsMap(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        public K firstKey() {
            return sortedMap().firstKey();
        }

        public SortedMap<K, Collection<V>> headMap(K k) {
            return new SortedAsMap(sortedMap().headMap(k));
        }

        public K lastKey() {
            return sortedMap().lastKey();
        }

        /* access modifiers changed from: package-private */
        public SortedMap<K, Collection<V>> sortedMap() {
            return (SortedMap) this.submap;
        }

        public SortedMap<K, Collection<V>> subMap(K k, K k2) {
            return new SortedAsMap(sortedMap().subMap(k, k2));
        }

        public SortedMap<K, Collection<V>> tailMap(K k) {
            return new SortedAsMap(sortedMap().tailMap(k));
        }

        /* access modifiers changed from: package-private */
        public SortedSet<K> createKeySet() {
            return new SortedKeySet(sortedMap());
        }

        public SortedSet<K> keySet() {
            SortedSet<K> sortedSet = this.sortedKeySet;
            if (sortedSet != null) {
                return sortedSet;
            }
            SortedSet<K> createKeySet = createKeySet();
            this.sortedKeySet = createKeySet;
            return createKeySet;
        }
    }

    private class SortedKeySet extends AbstractMapBasedMultimap<K, V>.KeySet implements SortedSet<K> {
        SortedKeySet(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        public K first() {
            return sortedMap().firstKey();
        }

        public SortedSet<K> headSet(K k) {
            return new SortedKeySet(sortedMap().headMap(k));
        }

        public K last() {
            return sortedMap().lastKey();
        }

        /* access modifiers changed from: package-private */
        public SortedMap<K, Collection<V>> sortedMap() {
            return (SortedMap) this.map;
        }

        public SortedSet<K> subSet(K k, K k2) {
            return new SortedKeySet(sortedMap().subMap(k, k2));
        }

        public SortedSet<K> tailSet(K k) {
            return new SortedKeySet(sortedMap().tailMap(k));
        }
    }

    class WrappedNavigableSet extends AbstractMapBasedMultimap<K, V>.WrappedSortedSet implements NavigableSet<V> {
        WrappedNavigableSet(K k, NavigableSet<V> navigableSet, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k, navigableSet, wrappedCollection);
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [com.google.common.collect.AbstractMapBasedMultimap<K, V>$WrappedCollection] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.util.NavigableSet<V> wrap(java.util.NavigableSet<V> r5) {
            /*
                r4 = this;
                com.google.common.collect.AbstractMapBasedMultimap$WrappedNavigableSet r0 = new com.google.common.collect.AbstractMapBasedMultimap$WrappedNavigableSet
                com.google.common.collect.AbstractMapBasedMultimap r1 = com.google.common.collect.AbstractMapBasedMultimap.this
                K r2 = r4.key
                com.google.common.collect.AbstractMapBasedMultimap<K, V>$WrappedCollection r3 = r4.ancestor
                if (r3 != 0) goto L_0x000b
                goto L_0x000c
            L_0x000b:
                r4 = r3
            L_0x000c:
                r0.<init>(r2, r5, r4)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.WrappedNavigableSet.wrap(java.util.NavigableSet):java.util.NavigableSet");
        }

        public V ceiling(V v) {
            return ((NavigableSet) this.delegate).ceiling(v);
        }

        public Iterator<V> descendingIterator() {
            return new WrappedCollection.WrappedIterator(((NavigableSet) this.delegate).descendingIterator());
        }

        public NavigableSet<V> descendingSet() {
            return wrap(((NavigableSet) this.delegate).descendingSet());
        }

        public V floor(V v) {
            return ((NavigableSet) this.delegate).floor(v);
        }

        /* access modifiers changed from: package-private */
        public SortedSet getSortedSetDelegate() {
            return (NavigableSet) this.delegate;
        }

        public NavigableSet<V> headSet(V v, boolean z) {
            return wrap(((NavigableSet) this.delegate).headSet(v, z));
        }

        public V higher(V v) {
            return ((NavigableSet) this.delegate).higher(v);
        }

        public V lower(V v) {
            return ((NavigableSet) this.delegate).lower(v);
        }

        public V pollFirst() {
            refreshIfEmpty();
            return Collections2.pollNext(new WrappedCollection.WrappedIterator());
        }

        public V pollLast() {
            return Collections2.pollNext(new WrappedCollection.WrappedIterator(((NavigableSet) this.delegate).descendingIterator()));
        }

        public NavigableSet<V> subSet(V v, boolean z, V v2, boolean z2) {
            return wrap(((NavigableSet) this.delegate).subSet(v, z, v2, z2));
        }

        public NavigableSet<V> tailSet(V v, boolean z) {
            return wrap(((NavigableSet) this.delegate).tailSet(v, z));
        }
    }

    private class WrappedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements Set<V> {
        WrappedSet(K k, Set<V> set) {
            super(k, set, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
        }

        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean removeAllImpl = Collections2.removeAllImpl((Set<?>) (Set) this.delegate, collection);
            if (removeAllImpl) {
                int size2 = this.delegate.size();
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                int unused = abstractMapBasedMultimap.totalSize = (size2 - size) + abstractMapBasedMultimap.totalSize;
                removeIfEmpty();
            }
            return removeAllImpl;
        }
    }

    private class WrappedSortedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements SortedSet<V> {
        WrappedSortedSet(K k, SortedSet<V> sortedSet, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k, sortedSet, wrappedCollection);
        }

        public Comparator<? super V> comparator() {
            return getSortedSetDelegate().comparator();
        }

        public V first() {
            refreshIfEmpty();
            return getSortedSetDelegate().first();
        }

        /* access modifiers changed from: package-private */
        public SortedSet<V> getSortedSetDelegate() {
            return (SortedSet) this.delegate;
        }

        /* JADX WARNING: type inference failed for: r3v1, types: [com.google.common.collect.AbstractMapBasedMultimap<K, V>$WrappedCollection] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.SortedSet<V> headSet(V r5) {
            /*
                r4 = this;
                r4.refreshIfEmpty()
                com.google.common.collect.AbstractMapBasedMultimap$WrappedSortedSet r0 = new com.google.common.collect.AbstractMapBasedMultimap$WrappedSortedSet
                com.google.common.collect.AbstractMapBasedMultimap r1 = com.google.common.collect.AbstractMapBasedMultimap.this
                K r2 = r4.key
                java.util.SortedSet r3 = r4.getSortedSetDelegate()
                java.util.SortedSet r5 = r3.headSet(r5)
                com.google.common.collect.AbstractMapBasedMultimap<K, V>$WrappedCollection r3 = r4.ancestor
                if (r3 != 0) goto L_0x0016
                goto L_0x0017
            L_0x0016:
                r4 = r3
            L_0x0017:
                r0.<init>(r2, r5, r4)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.WrappedSortedSet.headSet(java.lang.Object):java.util.SortedSet");
        }

        public V last() {
            refreshIfEmpty();
            return getSortedSetDelegate().last();
        }

        /* JADX WARNING: type inference failed for: r6v1, types: [com.google.common.collect.AbstractMapBasedMultimap<K, V>$WrappedCollection] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.SortedSet<V> subSet(V r5, V r6) {
            /*
                r4 = this;
                r4.refreshIfEmpty()
                com.google.common.collect.AbstractMapBasedMultimap$WrappedSortedSet r0 = new com.google.common.collect.AbstractMapBasedMultimap$WrappedSortedSet
                com.google.common.collect.AbstractMapBasedMultimap r1 = com.google.common.collect.AbstractMapBasedMultimap.this
                K r2 = r4.key
                java.util.SortedSet r3 = r4.getSortedSetDelegate()
                java.util.SortedSet r5 = r3.subSet(r5, r6)
                com.google.common.collect.AbstractMapBasedMultimap<K, V>$WrappedCollection r6 = r4.ancestor
                if (r6 != 0) goto L_0x0016
                goto L_0x0017
            L_0x0016:
                r4 = r6
            L_0x0017:
                r0.<init>(r2, r5, r4)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.WrappedSortedSet.subSet(java.lang.Object, java.lang.Object):java.util.SortedSet");
        }

        /* JADX WARNING: type inference failed for: r3v1, types: [com.google.common.collect.AbstractMapBasedMultimap<K, V>$WrappedCollection] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.SortedSet<V> tailSet(V r5) {
            /*
                r4 = this;
                r4.refreshIfEmpty()
                com.google.common.collect.AbstractMapBasedMultimap$WrappedSortedSet r0 = new com.google.common.collect.AbstractMapBasedMultimap$WrappedSortedSet
                com.google.common.collect.AbstractMapBasedMultimap r1 = com.google.common.collect.AbstractMapBasedMultimap.this
                K r2 = r4.key
                java.util.SortedSet r3 = r4.getSortedSetDelegate()
                java.util.SortedSet r5 = r3.tailSet(r5)
                com.google.common.collect.AbstractMapBasedMultimap<K, V>$WrappedCollection r3 = r4.ancestor
                if (r3 != 0) goto L_0x0016
                goto L_0x0017
            L_0x0016:
                r4 = r3
            L_0x0017:
                r0.<init>(r2, r5, r4)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.WrappedSortedSet.tailSet(java.lang.Object):java.util.SortedSet");
        }
    }

    protected AbstractMapBasedMultimap(Map<K, Collection<V>> map2) {
        MoreObjects.checkArgument(map2.isEmpty());
        this.map = map2;
    }

    static /* synthetic */ int access$208(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i + 1;
        return i;
    }

    static /* synthetic */ int access$210(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i - 1;
        return i;
    }

    static /* synthetic */ void access$400(AbstractMapBasedMultimap abstractMapBasedMultimap, Object obj) {
        Map<K, Collection<V>> map2 = abstractMapBasedMultimap.map;
        if (map2 != null) {
            Collection<V> collection = null;
            try {
                collection = map2.remove(obj);
            } catch (ClassCastException | NullPointerException unused) {
            }
            Collection collection2 = collection;
            if (collection2 != null) {
                int size = collection2.size();
                collection2.clear();
                abstractMapBasedMultimap.totalSize -= size;
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public List<V> wrapList(K k, List<V> list, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
        return list instanceof RandomAccess ? new RandomAccessWrappedList(this, k, list, wrappedCollection) : new WrappedList(k, list, wrappedCollection);
    }

    /* access modifiers changed from: package-private */
    public Map<K, Collection<V>> backingMap() {
        return this.map;
    }

    public void clear() {
        for (Collection<V> clear : this.map.values()) {
            clear.clear();
        }
        this.map.clear();
        this.totalSize = 0;
    }

    /* access modifiers changed from: package-private */
    public Map<K, Collection<V>> createAsMap() {
        Map<K, Collection<V>> map2 = this.map;
        if (map2 instanceof NavigableMap) {
            return new NavigableAsMap((NavigableMap) map2);
        }
        if (map2 instanceof SortedMap) {
            return new SortedAsMap((SortedMap) map2);
        }
        return new AsMap(map2);
    }

    /* access modifiers changed from: package-private */
    public abstract Collection<V> createCollection();

    /* access modifiers changed from: package-private */
    public Collection<V> createCollection(K k) {
        return createCollection();
    }

    /* access modifiers changed from: package-private */
    public Set<K> createKeySet() {
        Map<K, Collection<V>> map2 = this.map;
        if (map2 instanceof NavigableMap) {
            return new NavigableKeySet((NavigableMap) map2);
        }
        if (map2 instanceof SortedMap) {
            return new SortedKeySet((SortedMap) map2);
        }
        return new KeySet(map2);
    }

    public Collection<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new AbstractMapBasedMultimap<K, V>.Itr<Map.Entry<K, V>>(this) {
        };
    }

    /* access modifiers changed from: package-private */
    public Spliterator<Map.Entry<K, V>> entrySpliterator() {
        return Collections2.flatMap(this.map.entrySet().spliterator(), $$Lambda$AbstractMapBasedMultimap$GMPqKIl8YmjBXAn2_BtVZGwD6YY.INSTANCE, 64, (long) size());
    }

    public Collection<V> get(K k) {
        Collection collection = this.map.get(k);
        if (collection == null) {
            collection = createCollection(k);
        }
        return wrapCollection(k, collection);
    }

    /* access modifiers changed from: package-private */
    public final void setMap(Map<K, Collection<V>> map2) {
        this.map = map2;
        this.totalSize = 0;
        for (Collection next : map2.values()) {
            MoreObjects.checkArgument(!next.isEmpty());
            this.totalSize = next.size() + this.totalSize;
        }
    }

    public int size() {
        return this.totalSize;
    }

    /* access modifiers changed from: package-private */
    public Collection<V> wrapCollection(K k, Collection<V> collection) {
        if (collection instanceof NavigableSet) {
            return new WrappedNavigableSet(k, (NavigableSet) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
        }
        if (collection instanceof SortedSet) {
            return new WrappedSortedSet(k, (SortedSet) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
        }
        if (collection instanceof Set) {
            return new WrappedSet(k, (Set) collection);
        }
        if (collection instanceof List) {
            return wrapList(k, (List) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
        }
        return new WrappedCollection(k, collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
    }

    private class WrappedList extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements List<V> {

        private class WrappedListIterator extends AbstractMapBasedMultimap<K, V>.WrappedIterator implements ListIterator<V> {
            WrappedListIterator() {
                super();
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [com.google.common.collect.AbstractMapBasedMultimap$WrappedList$WrappedListIterator, com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection$WrappedIterator] */
            private ListIterator<V> getDelegateListIterator() {
                validateIterator();
                return (ListIterator) this.delegateIterator;
            }

            public void add(V v) {
                boolean isEmpty = WrappedList.this.isEmpty();
                getDelegateListIterator().add(v);
                AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
                if (isEmpty) {
                    WrappedList.this.addToMap();
                }
            }

            public boolean hasPrevious() {
                return getDelegateListIterator().hasPrevious();
            }

            public int nextIndex() {
                return getDelegateListIterator().nextIndex();
            }

            public V previous() {
                return getDelegateListIterator().previous();
            }

            public int previousIndex() {
                return getDelegateListIterator().previousIndex();
            }

            public void set(V v) {
                getDelegateListIterator().set(v);
            }

            public WrappedListIterator(int i) {
                super(((List) WrappedList.this.delegate).listIterator(i));
            }
        }

        WrappedList(K k, List<V> list, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k, list, wrappedCollection);
        }

        public void add(int i, V v) {
            refreshIfEmpty();
            boolean isEmpty = this.delegate.isEmpty();
            ((List) this.delegate).add(i, v);
            AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
            if (isEmpty) {
                addToMap();
            }
        }

        public boolean addAll(int i, Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = ((List) this.delegate).addAll(i, collection);
            if (addAll) {
                int size2 = this.delegate.size();
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                int unused = abstractMapBasedMultimap.totalSize = (size2 - size) + abstractMapBasedMultimap.totalSize;
                if (size == 0) {
                    addToMap();
                }
            }
            return addAll;
        }

        public V get(int i) {
            refreshIfEmpty();
            return ((List) this.delegate).get(i);
        }

        public int indexOf(Object obj) {
            refreshIfEmpty();
            return ((List) this.delegate).indexOf(obj);
        }

        public int lastIndexOf(Object obj) {
            refreshIfEmpty();
            return ((List) this.delegate).lastIndexOf(obj);
        }

        public ListIterator<V> listIterator() {
            refreshIfEmpty();
            return new WrappedListIterator();
        }

        public V remove(int i) {
            refreshIfEmpty();
            V remove = ((List) this.delegate).remove(i);
            AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
            removeIfEmpty();
            return remove;
        }

        public V set(int i, V v) {
            refreshIfEmpty();
            return ((List) this.delegate).set(i, v);
        }

        /* JADX WARNING: type inference failed for: r5v1, types: [com.google.common.collect.AbstractMapBasedMultimap<K, V>$WrappedCollection] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.List<V> subList(int r4, int r5) {
            /*
                r3 = this;
                r3.refreshIfEmpty()
                com.google.common.collect.AbstractMapBasedMultimap r0 = com.google.common.collect.AbstractMapBasedMultimap.this
                K r1 = r3.key
                java.util.Collection<V> r2 = r3.delegate
                java.util.List r2 = (java.util.List) r2
                java.util.List r4 = r2.subList(r4, r5)
                com.google.common.collect.AbstractMapBasedMultimap<K, V>$WrappedCollection r5 = r3.ancestor
                if (r5 != 0) goto L_0x0014
                goto L_0x0015
            L_0x0014:
                r3 = r5
            L_0x0015:
                java.util.List r3 = r0.wrapList(r1, r4, r3)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.WrappedList.subList(int, int):java.util.List");
        }

        public ListIterator<V> listIterator(int i) {
            refreshIfEmpty();
            return new WrappedListIterator(i);
        }
    }

    class NavigableAsMap extends AbstractMapBasedMultimap<K, V>.SortedAsMap implements NavigableMap<K, Collection<V>> {
        NavigableAsMap(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        public Map.Entry<K, Collection<V>> ceilingEntry(K k) {
            Map.Entry ceilingEntry = ((NavigableMap) this.submap).ceilingEntry(k);
            if (ceilingEntry == null) {
                return null;
            }
            return wrapEntry(ceilingEntry);
        }

        public K ceilingKey(K k) {
            return ((NavigableMap) this.submap).ceilingKey(k);
        }

        /* access modifiers changed from: package-private */
        public Set createKeySet() {
            return new NavigableKeySet((NavigableMap) this.submap);
        }

        public NavigableSet<K> descendingKeySet() {
            return new NavigableAsMap(((NavigableMap) this.submap).descendingMap()).navigableKeySet();
        }

        public NavigableMap<K, Collection<V>> descendingMap() {
            return new NavigableAsMap(((NavigableMap) this.submap).descendingMap());
        }

        public Map.Entry<K, Collection<V>> firstEntry() {
            Map.Entry firstEntry = ((NavigableMap) this.submap).firstEntry();
            if (firstEntry == null) {
                return null;
            }
            return wrapEntry(firstEntry);
        }

        public Map.Entry<K, Collection<V>> floorEntry(K k) {
            Map.Entry floorEntry = ((NavigableMap) this.submap).floorEntry(k);
            if (floorEntry == null) {
                return null;
            }
            return wrapEntry(floorEntry);
        }

        public K floorKey(K k) {
            return ((NavigableMap) this.submap).floorKey(k);
        }

        public NavigableMap<K, Collection<V>> headMap(K k, boolean z) {
            return new NavigableAsMap(((NavigableMap) this.submap).headMap(k, z));
        }

        public Map.Entry<K, Collection<V>> higherEntry(K k) {
            Map.Entry higherEntry = ((NavigableMap) this.submap).higherEntry(k);
            if (higherEntry == null) {
                return null;
            }
            return wrapEntry(higherEntry);
        }

        public K higherKey(K k) {
            return ((NavigableMap) this.submap).higherKey(k);
        }

        public NavigableSet<K> keySet() {
            SortedSet<K> sortedSet = this.sortedKeySet;
            if (sortedSet == null) {
                sortedSet = createKeySet();
                this.sortedKeySet = sortedSet;
            }
            return (NavigableSet) sortedSet;
        }

        public Map.Entry<K, Collection<V>> lastEntry() {
            Map.Entry lastEntry = ((NavigableMap) this.submap).lastEntry();
            if (lastEntry == null) {
                return null;
            }
            return wrapEntry(lastEntry);
        }

        public Map.Entry<K, Collection<V>> lowerEntry(K k) {
            Map.Entry lowerEntry = ((NavigableMap) this.submap).lowerEntry(k);
            if (lowerEntry == null) {
                return null;
            }
            return wrapEntry(lowerEntry);
        }

        public K lowerKey(K k) {
            return ((NavigableMap) this.submap).lowerKey(k);
        }

        public NavigableSet<K> navigableKeySet() {
            return keySet();
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, Collection<V>> pollAsMapEntry(Iterator<Map.Entry<K, Collection<V>>> it) {
            Object obj;
            if (!it.hasNext()) {
                return null;
            }
            Map.Entry next = it.next();
            Collection createCollection = AbstractMapBasedMultimap.this.createCollection();
            createCollection.addAll((Collection) next.getValue());
            it.remove();
            Object key = next.getKey();
            if (createCollection instanceof NavigableSet) {
                obj = Collections2.unmodifiableNavigableSet((NavigableSet) createCollection);
            } else if (createCollection instanceof SortedSet) {
                obj = Collections.unmodifiableSortedSet((SortedSet) createCollection);
            } else if (createCollection instanceof Set) {
                obj = Collections.unmodifiableSet((Set) createCollection);
            } else if (createCollection instanceof List) {
                obj = Collections.unmodifiableList((List) createCollection);
            } else {
                obj = Collections.unmodifiableCollection(createCollection);
            }
            return new ImmutableEntry(key, obj);
        }

        public Map.Entry<K, Collection<V>> pollFirstEntry() {
            return pollAsMapEntry(entrySet().iterator());
        }

        public Map.Entry<K, Collection<V>> pollLastEntry() {
            return pollAsMapEntry(new NavigableAsMap(((NavigableMap) this.submap).descendingMap()).entrySet().iterator());
        }

        /* access modifiers changed from: package-private */
        public SortedMap sortedMap() {
            return (NavigableMap) this.submap;
        }

        public NavigableMap<K, Collection<V>> subMap(K k, boolean z, K k2, boolean z2) {
            return new NavigableAsMap(((NavigableMap) this.submap).subMap(k, z, k2, z2));
        }

        public NavigableMap<K, Collection<V>> tailMap(K k, boolean z) {
            return new NavigableAsMap(((NavigableMap) this.submap).tailMap(k, z));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: createKeySet  reason: collision with other method in class */
        public SortedSet m128createKeySet() {
            return new NavigableKeySet((NavigableMap) this.submap);
        }

        public SortedMap headMap(Object obj) {
            return new NavigableAsMap(((NavigableMap) this.submap).headMap(obj, false));
        }

        public SortedMap subMap(Object obj, Object obj2) {
            return new NavigableAsMap(((NavigableMap) this.submap).subMap(obj, true, obj2, false));
        }

        public SortedMap tailMap(Object obj) {
            return new NavigableAsMap(((NavigableMap) this.submap).tailMap(obj, true));
        }
    }

    class NavigableKeySet extends AbstractMapBasedMultimap<K, V>.SortedKeySet implements NavigableSet<K> {
        NavigableKeySet(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        public K ceiling(K k) {
            return ((NavigableMap) this.map).ceilingKey(k);
        }

        public Iterator<K> descendingIterator() {
            return new NavigableKeySet(((NavigableMap) this.map).descendingMap()).iterator();
        }

        public NavigableSet<K> descendingSet() {
            return new NavigableKeySet(((NavigableMap) this.map).descendingMap());
        }

        public K floor(K k) {
            return ((NavigableMap) this.map).floorKey(k);
        }

        public NavigableSet<K> headSet(K k, boolean z) {
            return new NavigableKeySet(((NavigableMap) this.map).headMap(k, z));
        }

        public K higher(K k) {
            return ((NavigableMap) this.map).higherKey(k);
        }

        public K lower(K k) {
            return ((NavigableMap) this.map).lowerKey(k);
        }

        public K pollFirst() {
            Iterator<Map.Entry<K, V>> it = this.map.entrySet().iterator();
            if (!it.hasNext()) {
                return null;
            }
            Map.Entry next = it.next();
            K key = next.getKey();
            MoreObjects.checkState(next != null, "no calls to next() since the last call to remove()");
            Collection collection = (Collection) next.getValue();
            it.remove();
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            int unused = abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize - collection.size();
            collection.clear();
            return key;
        }

        public K pollLast() {
            return Collections2.pollNext(new NavigableKeySet(((NavigableMap) this.map).descendingMap()).iterator());
        }

        /* access modifiers changed from: package-private */
        public SortedMap sortedMap() {
            return (NavigableMap) this.map;
        }

        public NavigableSet<K> subSet(K k, boolean z, K k2, boolean z2) {
            return new NavigableKeySet(((NavigableMap) this.map).subMap(k, z, k2, z2));
        }

        public NavigableSet<K> tailSet(K k, boolean z) {
            return new NavigableKeySet(((NavigableMap) this.map).tailMap(k, z));
        }

        public SortedSet headSet(Object obj) {
            return new NavigableKeySet(((NavigableMap) this.map).headMap(obj, false));
        }

        public SortedSet subSet(Object obj, Object obj2) {
            return new NavigableKeySet(((NavigableMap) this.map).subMap(obj, true, obj2, false));
        }

        public SortedSet tailSet(Object obj) {
            return new NavigableKeySet(((NavigableMap) this.map).tailMap(obj, true));
        }
    }

    private class WrappedCollection extends AbstractCollection<V> {
        final AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor;
        final Collection<V> ancestorDelegate;
        Collection<V> delegate;
        final K key;

        WrappedCollection(K k, Collection<V> collection, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            Collection<V> collection2;
            this.key = k;
            this.delegate = collection;
            this.ancestor = wrappedCollection;
            if (wrappedCollection == null) {
                collection2 = null;
            } else {
                collection2 = wrappedCollection.delegate;
            }
            this.ancestorDelegate = collection2;
        }

        public boolean add(V v) {
            refreshIfEmpty();
            boolean isEmpty = this.delegate.isEmpty();
            boolean add = this.delegate.add(v);
            if (add) {
                AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
                if (isEmpty) {
                    addToMap();
                }
            }
            return add;
        }

        public boolean addAll(Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = this.delegate.addAll(collection);
            if (addAll) {
                int size2 = this.delegate.size();
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                int unused = abstractMapBasedMultimap.totalSize = (size2 - size) + abstractMapBasedMultimap.totalSize;
                if (size == 0) {
                    addToMap();
                }
            }
            return addAll;
        }

        /* access modifiers changed from: package-private */
        public void addToMap() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.addToMap();
            } else {
                AbstractMapBasedMultimap.this.map.put(this.key, this.delegate);
            }
        }

        public void clear() {
            int size = size();
            if (size != 0) {
                this.delegate.clear();
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                int unused = abstractMapBasedMultimap.totalSize = abstractMapBasedMultimap.totalSize - size;
                removeIfEmpty();
            }
        }

        public boolean contains(Object obj) {
            refreshIfEmpty();
            return this.delegate.contains(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            refreshIfEmpty();
            return this.delegate.containsAll(collection);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            refreshIfEmpty();
            return this.delegate.equals(obj);
        }

        public int hashCode() {
            refreshIfEmpty();
            return this.delegate.hashCode();
        }

        public Iterator<V> iterator() {
            refreshIfEmpty();
            return new WrappedIterator();
        }

        /* access modifiers changed from: package-private */
        public void refreshIfEmpty() {
            Collection<V> collection;
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.refreshIfEmpty();
                if (this.ancestor.delegate != this.ancestorDelegate) {
                    throw new ConcurrentModificationException();
                }
            } else if (this.delegate.isEmpty() && (collection = (Collection) AbstractMapBasedMultimap.this.map.get(this.key)) != null) {
                this.delegate = collection;
            }
        }

        public boolean remove(Object obj) {
            refreshIfEmpty();
            boolean remove = this.delegate.remove(obj);
            if (remove) {
                AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
                removeIfEmpty();
            }
            return remove;
        }

        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean removeAll = this.delegate.removeAll(collection);
            if (removeAll) {
                int size2 = this.delegate.size();
                AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                int unused = abstractMapBasedMultimap.totalSize = (size2 - size) + abstractMapBasedMultimap.totalSize;
                removeIfEmpty();
            }
            return removeAll;
        }

        /* access modifiers changed from: package-private */
        public void removeIfEmpty() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.removeIfEmpty();
            } else if (this.delegate.isEmpty()) {
                AbstractMapBasedMultimap.this.map.remove(this.key);
            }
        }

        public boolean retainAll(Collection<?> collection) {
            if (collection != null) {
                int size = size();
                boolean retainAll = this.delegate.retainAll(collection);
                if (retainAll) {
                    int size2 = this.delegate.size();
                    AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
                    int unused = abstractMapBasedMultimap.totalSize = (size2 - size) + abstractMapBasedMultimap.totalSize;
                    removeIfEmpty();
                }
                return retainAll;
            }
            throw new NullPointerException();
        }

        public int size() {
            refreshIfEmpty();
            return this.delegate.size();
        }

        public Spliterator<V> spliterator() {
            refreshIfEmpty();
            return this.delegate.spliterator();
        }

        public String toString() {
            refreshIfEmpty();
            return this.delegate.toString();
        }

        class WrappedIterator implements Iterator<V> {
            final Iterator<V> delegateIterator;
            final Collection<V> originalDelegate = WrappedCollection.this.delegate;

            WrappedIterator() {
                Iterator<V> it;
                Collection<V> collection = WrappedCollection.this.delegate;
                if (collection instanceof List) {
                    it = ((List) collection).listIterator();
                } else {
                    it = collection.iterator();
                }
                this.delegateIterator = it;
            }

            public boolean hasNext() {
                validateIterator();
                return this.delegateIterator.hasNext();
            }

            public V next() {
                validateIterator();
                return this.delegateIterator.next();
            }

            public void remove() {
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
                WrappedCollection.this.removeIfEmpty();
            }

            /* access modifiers changed from: package-private */
            public void validateIterator() {
                WrappedCollection.this.refreshIfEmpty();
                if (WrappedCollection.this.delegate != this.originalDelegate) {
                    throw new ConcurrentModificationException();
                }
            }

            WrappedIterator(Iterator<V> it) {
                this.delegateIterator = it;
            }
        }
    }
}
