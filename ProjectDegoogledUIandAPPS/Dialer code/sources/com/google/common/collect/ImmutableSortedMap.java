package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class ImmutableSortedMap<K, V> extends ImmutableSortedMapFauxverideShim<K, V> implements NavigableMap<K, V> {
    private static final ImmutableSortedMap<Comparable, Object> NATURAL_EMPTY_MAP = new ImmutableSortedMap<>(ImmutableSortedSet.emptySet(NaturalOrdering.INSTANCE), ImmutableList.m74of(), (ImmutableSortedMap) null);
    private static final long serialVersionUID = 0;
    private transient ImmutableSortedMap<K, V> descendingMap;
    /* access modifiers changed from: private */
    public final transient RegularImmutableSortedSet<K> keySet;
    /* access modifiers changed from: private */
    public final transient ImmutableList<V> valueList;

    public static class Builder<K, V> extends ImmutableMap.Builder<K, V> {
        private final Comparator<? super K> comparator;

        public Builder(Comparator<? super K> comparator2) {
            super(4);
            if (comparator2 != null) {
                this.comparator = comparator2;
                return;
            }
            throw new NullPointerException();
        }

        public ImmutableMap build() {
            int i = this.size;
            if (i == 0) {
                return ImmutableSortedMap.emptyMap(this.comparator);
            }
            if (i != 1) {
                return ImmutableSortedMap.access$100(this.comparator, false, this.entries, i);
            }
            return ImmutableSortedMap.m91of(this.comparator, this.entries[0].getKey(), this.entries[0].getValue());
        }

        public ImmutableMap.Builder put(Object obj, Object obj2) {
            super.put(obj, obj2);
            return this;
        }

        public ImmutableMap.Builder putAll(Map map) {
            super.putAll(map);
            return this;
        }

        public ImmutableMap.Builder put(Map.Entry entry) {
            super.put(entry);
            return this;
        }

        public ImmutableMap.Builder putAll(Iterable iterable) {
            super.putAll(iterable);
            return this;
        }
    }

    private static class SerializedForm extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;
        private final Comparator<Object> comparator;

        SerializedForm(ImmutableSortedMap<?, ?> immutableSortedMap) {
            super(immutableSortedMap);
            this.comparator = immutableSortedMap.comparator();
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return createMap(new Builder(this.comparator));
        }
    }

    static {
        Ordering.natural();
    }

    ImmutableSortedMap(RegularImmutableSortedSet<K> regularImmutableSortedSet, ImmutableList<V> immutableList, ImmutableSortedMap<K, V> immutableSortedMap) {
        this.keySet = regularImmutableSortedSet;
        this.valueList = immutableList;
        this.descendingMap = immutableSortedMap;
    }

    static /* synthetic */ ImmutableSortedMap access$100(final Comparator comparator, boolean z, Map.Entry[] entryArr, int i) {
        if (i == 0) {
            return emptyMap(comparator);
        }
        if (i == 1) {
            return m91of(comparator, entryArr[0].getKey(), entryArr[0].getValue());
        }
        Object[] objArr = new Object[i];
        Object[] objArr2 = new Object[i];
        if (z) {
            for (int i2 = 0; i2 < i; i2++) {
                Object key = entryArr[i2].getKey();
                Object value = entryArr[i2].getValue();
                Collections2.checkEntryNotNull(key, value);
                objArr[i2] = key;
                objArr2[i2] = value;
            }
        } else {
            Arrays.sort(entryArr, 0, i, new Comparator<Map.Entry<K, V>>() {
                public int compare(Object obj, Object obj2) {
                    return comparator.compare(((Map.Entry) obj).getKey(), ((Map.Entry) obj2).getKey());
                }
            });
            Object key2 = entryArr[0].getKey();
            objArr[0] = key2;
            objArr2[0] = entryArr[0].getValue();
            Object obj = key2;
            int i3 = 1;
            while (i3 < i) {
                Object key3 = entryArr[i3].getKey();
                Object value2 = entryArr[i3].getValue();
                Collections2.checkEntryNotNull(key3, value2);
                objArr[i3] = key3;
                objArr2[i3] = value2;
                ImmutableMap.checkNoConflict(comparator.compare(obj, key3) != 0, "key", entryArr[i3 - 1], entryArr[i3]);
                i3++;
                obj = key3;
            }
        }
        return new ImmutableSortedMap(new RegularImmutableSortedSet(new RegularImmutableList(objArr), comparator), new RegularImmutableList(objArr2), (ImmutableSortedMap) null);
    }

    static <K, V> ImmutableSortedMap<K, V> emptyMap(Comparator<? super K> comparator) {
        if (Ordering.natural().equals(comparator)) {
            return m90of();
        }
        return new ImmutableSortedMap<>(ImmutableSortedSet.emptySet(comparator), ImmutableList.m74of(), (ImmutableSortedMap) null);
    }

    private ImmutableSortedMap<K, V> getSubMap(int i, int i2) {
        if (i == 0 && i2 == size()) {
            return this;
        }
        if (i == i2) {
            return emptyMap(comparator());
        }
        return new ImmutableSortedMap<>(this.keySet.getSubSet(i, i2), this.valueList.subList(i, i2), (ImmutableSortedMap) null);
    }

    /* renamed from: of */
    public static <K, V> ImmutableSortedMap<K, V> m90of() {
        return NATURAL_EMPTY_MAP;
    }

    public Map.Entry<K, V> ceilingEntry(K k) {
        return tailMap(k, true).firstEntry();
    }

    public K ceilingKey(K k) {
        return Collections2.keyOrNull(ceilingEntry(k));
    }

    public Comparator<? super K> comparator() {
        return keySet().comparator();
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return isEmpty() ? ImmutableSet.m86of() : new ImmutableMapEntrySet<K, V>() {
            /* access modifiers changed from: package-private */
            public ImmutableList<Map.Entry<K, V>> createAsList() {
                return new ImmutableAsList<Map.Entry<K, V>>() {
                    /* access modifiers changed from: package-private */
                    public ImmutableCollection<Map.Entry<K, V>> delegateCollection() {
                        return AnonymousClass1EntrySet.this;
                    }

                    public Spliterator<Map.Entry<K, V>> spliterator() {
                        return Collections2.indexed(size(), 1297, 
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: RETURN  
                              (wrap: java.util.Spliterator<java.util.Map$Entry<K, V>> : 0x000b: INVOKE  (r2v2 java.util.Spliterator<java.util.Map$Entry<K, V>>) = 
                              (wrap: int : 0x0000: INVOKE  (r0v0 int) = 
                              (r2v0 'this' com.google.common.collect.ImmutableSortedMap$1EntrySet$1 A[THIS])
                             com.google.common.collect.ImmutableAsList.size():int type: VIRTUAL)
                              (1297 int)
                              (wrap: com.google.common.collect.-$$Lambda$7PoTp3aIQo7HSLflqKWOsi7MUOA : 0x0006: CONSTRUCTOR  (r1v0 com.google.common.collect.-$$Lambda$7PoTp3aIQo7HSLflqKWOsi7MUOA) = 
                              (r2v0 'this' com.google.common.collect.ImmutableSortedMap$1EntrySet$1 A[THIS])
                             call: com.google.common.collect.-$$Lambda$7PoTp3aIQo7HSLflqKWOsi7MUOA.<init>(com.google.common.collect.ImmutableSortedMap$1EntrySet$1):void type: CONSTRUCTOR)
                             com.google.common.collect.Collections2.indexed(int, int, java.util.function.IntFunction):java.util.Spliterator type: STATIC)
                             in method: com.google.common.collect.ImmutableSortedMap.1EntrySet.1.spliterator():java.util.Spliterator<java.util.Map$Entry<K, V>>, dex: classes.dex
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
                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:314)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
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
                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                            	at jadx.core.codegen.InsnGen.makeTernary(InsnGen.java:955)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:476)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:314)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
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
                            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000b: INVOKE  (r2v2 java.util.Spliterator<java.util.Map$Entry<K, V>>) = 
                              (wrap: int : 0x0000: INVOKE  (r0v0 int) = 
                              (r2v0 'this' com.google.common.collect.ImmutableSortedMap$1EntrySet$1 A[THIS])
                             com.google.common.collect.ImmutableAsList.size():int type: VIRTUAL)
                              (1297 int)
                              (wrap: com.google.common.collect.-$$Lambda$7PoTp3aIQo7HSLflqKWOsi7MUOA : 0x0006: CONSTRUCTOR  (r1v0 com.google.common.collect.-$$Lambda$7PoTp3aIQo7HSLflqKWOsi7MUOA) = 
                              (r2v0 'this' com.google.common.collect.ImmutableSortedMap$1EntrySet$1 A[THIS])
                             call: com.google.common.collect.-$$Lambda$7PoTp3aIQo7HSLflqKWOsi7MUOA.<init>(com.google.common.collect.ImmutableSortedMap$1EntrySet$1):void type: CONSTRUCTOR)
                             com.google.common.collect.Collections2.indexed(int, int, java.util.function.IntFunction):java.util.Spliterator type: STATIC in method: com.google.common.collect.ImmutableSortedMap.1EntrySet.1.spliterator():java.util.Spliterator<java.util.Map$Entry<K, V>>, dex: classes.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:314)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                            	... 94 more
                            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: CONSTRUCTOR  (r1v0 com.google.common.collect.-$$Lambda$7PoTp3aIQo7HSLflqKWOsi7MUOA) = 
                              (r2v0 'this' com.google.common.collect.ImmutableSortedMap$1EntrySet$1 A[THIS])
                             call: com.google.common.collect.-$$Lambda$7PoTp3aIQo7HSLflqKWOsi7MUOA.<init>(com.google.common.collect.ImmutableSortedMap$1EntrySet$1):void type: CONSTRUCTOR in method: com.google.common.collect.ImmutableSortedMap.1EntrySet.1.spliterator():java.util.Spliterator<java.util.Map$Entry<K, V>>, dex: classes.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                            	... 98 more
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.google.common.collect.-$$Lambda$7PoTp3aIQo7HSLflqKWOsi7MUOA, state: NOT_LOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                            	... 104 more
                            */
                        /*
                            this = this;
                            int r0 = r2.size()
                            com.google.common.collect.-$$Lambda$7PoTp3aIQo7HSLflqKWOsi7MUOA r1 = new com.google.common.collect.-$$Lambda$7PoTp3aIQo7HSLflqKWOsi7MUOA
                            r1.<init>(r2)
                            r2 = 1297(0x511, float:1.817E-42)
                            java.util.Spliterator r2 = com.google.common.collect.Collections2.indexed(r0, r2, r1)
                            return r2
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ImmutableSortedMap.AnonymousClass1EntrySet.C08681.spliterator():java.util.Spliterator");
                    }

                    public Map.Entry<K, V> get(int i) {
                        return new AbstractMap.SimpleImmutableEntry(ImmutableSortedMap.this.keySet.asList().get(i), ImmutableSortedMap.this.valueList.get(i));
                    }
                };
            }

            public void forEach(Consumer<? super Map.Entry<K, V>> consumer) {
                asList().forEach(consumer);
            }

            /* access modifiers changed from: package-private */
            public ImmutableMap<K, V> map() {
                return ImmutableSortedMap.this;
            }

            public Spliterator<Map.Entry<K, V>> spliterator() {
                return asList().spliterator();
            }

            public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
                return asList().iterator();
            }
        };
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<K> createKeySet() {
        throw new AssertionError("should never be called");
    }

    /* access modifiers changed from: package-private */
    public ImmutableCollection<V> createValues() {
        throw new AssertionError("should never be called");
    }

    public Map.Entry<K, V> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) entrySet().asList().get(0);
    }

    public K firstKey() {
        return keySet().first();
    }

    public Map.Entry<K, V> floorEntry(K k) {
        return headMap(k, true).lastEntry();
    }

    public K floorKey(K k) {
        return Collections2.keyOrNull(floorEntry(k));
    }

    public void forEach(BiConsumer<? super K, ? super V> biConsumer) {
        if (biConsumer != null) {
            ImmutableList<K> asList = this.keySet.asList();
            for (int i = 0; i < size(); i++) {
                biConsumer.accept(asList.get(i), this.valueList.get(i));
            }
            return;
        }
        throw new NullPointerException();
    }

    public V get(Object obj) {
        int indexOf = this.keySet.indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        return this.valueList.get(indexOf);
    }

    public Map.Entry<K, V> higherEntry(K k) {
        return tailMap(k, false).firstEntry();
    }

    public K higherKey(K k) {
        return Collections2.keyOrNull(higherEntry(k));
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return this.keySet.isPartialView() || this.valueList.isPartialView();
    }

    public Map.Entry<K, V> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) entrySet().asList().get(size() - 1);
    }

    public K lastKey() {
        return keySet().last();
    }

    public Map.Entry<K, V> lowerEntry(K k) {
        return headMap(k, false).lastEntry();
    }

    public K lowerKey(K k) {
        return Collections2.keyOrNull(lowerEntry(k));
    }

    @Deprecated
    public final Map.Entry<K, V> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Map.Entry<K, V> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.valueList.size();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    /* access modifiers changed from: private */
    /* renamed from: of */
    public static <K, V> ImmutableSortedMap<K, V> m91of(Comparator<? super K> comparator, K k, V v) {
        ImmutableList of = ImmutableList.m75of(k);
        if (comparator != null) {
            return new ImmutableSortedMap<>(new RegularImmutableSortedSet(of, comparator), new SingletonImmutableList(v), (ImmutableSortedMap) null);
        }
        throw new NullPointerException();
    }

    public ImmutableSortedSet<K> descendingKeySet() {
        return this.keySet.descendingSet();
    }

    public ImmutableSortedMap<K, V> descendingMap() {
        ImmutableSortedMap<K, V> immutableSortedMap = this.descendingMap;
        if (immutableSortedMap != null) {
            return immutableSortedMap;
        }
        if (isEmpty()) {
            return emptyMap(Ordering.from(comparator()).reverse());
        }
        return new ImmutableSortedMap<>((RegularImmutableSortedSet) this.keySet.descendingSet(), this.valueList.reverse(), this);
    }

    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        return super.entrySet();
    }

    public ImmutableSortedSet<K> navigableKeySet() {
        return this.keySet;
    }

    public ImmutableCollection<V> values() {
        return this.valueList;
    }

    public ImmutableSortedMap<K, V> headMap(K k) {
        return headMap(k, false);
    }

    public ImmutableSortedSet<K> keySet() {
        return this.keySet;
    }

    public ImmutableSortedMap<K, V> subMap(K k, K k2) {
        return subMap(k, true, k2, false);
    }

    public ImmutableSortedMap<K, V> tailMap(K k) {
        return tailMap(k, true);
    }

    public ImmutableSortedMap<K, V> headMap(K k, boolean z) {
        RegularImmutableSortedSet<K> regularImmutableSortedSet = this.keySet;
        if (k != null) {
            return getSubMap(0, regularImmutableSortedSet.headIndex(k, z));
        }
        throw new NullPointerException();
    }

    public ImmutableSortedMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
        if (k == null) {
            throw new NullPointerException();
        } else if (k2 != null) {
            MoreObjects.checkArgument(comparator().compare(k, k2) <= 0, "expected fromKey <= toKey but %s > %s", k, k2);
            return headMap(k2, z2).tailMap(k, z);
        } else {
            throw new NullPointerException();
        }
    }

    public ImmutableSortedMap<K, V> tailMap(K k, boolean z) {
        RegularImmutableSortedSet<K> regularImmutableSortedSet = this.keySet;
        if (k != null) {
            return getSubMap(regularImmutableSortedSet.tailIndex(k, z), size());
        }
        throw new NullPointerException();
    }
}
