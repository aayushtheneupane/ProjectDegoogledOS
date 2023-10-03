package com.google.common.collect;

import android.support.p016v4.media.session.C0107q;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public final class LinkedHashMultimap extends AbstractSetMultimap {
    static final double VALUE_SET_LOAD_FACTOR = 1.0d;
    private static final long serialVersionUID = 1;
    /* access modifiers changed from: private */

    /* renamed from: kN */
    public transient ValueEntry f2448kN;
    transient int valueSetCapacity;

    final class ValueEntry extends ImmutableEntry implements C1661ha {
        ValueEntry nextInValueBucket;
        ValueEntry predecessorInMultimap;
        C1661ha predecessorInValueSet;
        final int smearedValueHash;
        ValueEntry successorInMultimap;
        C1661ha successorInValueSet;

        ValueEntry(Object obj, Object obj2, int i, ValueEntry valueEntry) {
            super(obj, obj2);
            this.smearedValueHash = i;
            this.nextInValueBucket = valueEntry;
        }

        /* renamed from: O */
        public C1661ha mo8806O() {
            return this.successorInValueSet;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo8809a(Object obj, int i) {
            return this.smearedValueHash == i && C0107q.m131b(getValue(), obj);
        }

        /* renamed from: b */
        public void mo8811b(C1661ha haVar) {
            this.predecessorInValueSet = haVar;
        }

        /* renamed from: bl */
        public ValueEntry mo8812bl() {
            return this.predecessorInMultimap;
        }

        /* renamed from: cl */
        public ValueEntry mo8813cl() {
            return this.successorInMultimap;
        }

        /* renamed from: l */
        public C1661ha mo8814l() {
            return this.predecessorInValueSet;
        }

        /* renamed from: a */
        public void mo8808a(C1661ha haVar) {
            this.successorInValueSet = haVar;
        }

        /* renamed from: b */
        public void mo8810b(ValueEntry valueEntry) {
            this.successorInMultimap = valueEntry;
        }

        /* renamed from: a */
        public void mo8807a(ValueEntry valueEntry) {
            this.predecessorInMultimap = valueEntry;
        }
    }

    final class ValueSet extends C1668jb implements C1661ha {
        /* access modifiers changed from: private */

        /* renamed from: VP */
        public C1661ha f2449VP;

        /* renamed from: WP */
        private C1661ha f2450WP;
        ValueEntry[] hashTable;
        private final Object key;
        /* access modifiers changed from: private */
        public int modCount = 0;
        private int size = 0;

        ValueSet(Object obj, int i) {
            this.key = obj;
            this.f2449VP = this;
            this.f2450WP = this;
            this.hashTable = new ValueEntry[C1578K.m4254a(i, LinkedHashMultimap.VALUE_SET_LOAD_FACTOR)];
        }

        private int mask() {
            return this.hashTable.length - 1;
        }

        /* renamed from: O */
        public C1661ha mo8806O() {
            return this.f2449VP;
        }

        public boolean add(Object obj) {
            int A = C1578K.m4253A(obj);
            int mask = mask() & A;
            ValueEntry valueEntry = this.hashTable[mask];
            ValueEntry valueEntry2 = valueEntry;
            while (true) {
                boolean z = false;
                if (valueEntry2 == null) {
                    ValueEntry valueEntry3 = new ValueEntry(this.key, obj, A, valueEntry);
                    LinkedHashMultimap.m4272a(this.f2450WP, (C1661ha) valueEntry3);
                    LinkedHashMultimap.m4272a((C1661ha) valueEntry3, (C1661ha) this);
                    ValueEntry bl = LinkedHashMultimap.this.f2448kN.mo8812bl();
                    bl.mo8810b(valueEntry3);
                    valueEntry3.mo8807a(bl);
                    ValueEntry a = LinkedHashMultimap.this.f2448kN;
                    valueEntry3.mo8810b(a);
                    a.mo8807a(valueEntry3);
                    ValueEntry[] valueEntryArr = this.hashTable;
                    valueEntryArr[mask] = valueEntry3;
                    this.size++;
                    this.modCount++;
                    int i = this.size;
                    int length = valueEntryArr.length;
                    if (((double) i) > ((double) length) * LinkedHashMultimap.VALUE_SET_LOAD_FACTOR && length < 1073741824) {
                        z = true;
                    }
                    if (z) {
                        ValueEntry[] valueEntryArr2 = new ValueEntry[(this.hashTable.length * 2)];
                        this.hashTable = valueEntryArr2;
                        int length2 = valueEntryArr2.length - 1;
                        for (C1661ha haVar = this.f2449VP; haVar != this; haVar = haVar.mo8806O()) {
                            ValueEntry valueEntry4 = (ValueEntry) haVar;
                            int i2 = valueEntry4.smearedValueHash & length2;
                            valueEntry4.nextInValueBucket = valueEntryArr2[i2];
                            valueEntryArr2[i2] = valueEntry4;
                        }
                    }
                    return true;
                } else if (valueEntry2.mo8809a(obj, A)) {
                    return false;
                } else {
                    valueEntry2 = valueEntry2.nextInValueBucket;
                }
            }
        }

        public void clear() {
            Arrays.fill(this.hashTable, (Object) null);
            this.size = 0;
            for (C1661ha haVar = this.f2449VP; haVar != this; haVar = haVar.mo8806O()) {
                ValueEntry valueEntry = (ValueEntry) haVar;
                ValueEntry bl = valueEntry.mo8812bl();
                ValueEntry cl = valueEntry.mo8813cl();
                bl.mo8810b(cl);
                cl.mo8807a(bl);
            }
            LinkedHashMultimap.m4272a((C1661ha) this, (C1661ha) this);
            this.modCount++;
        }

        public boolean contains(Object obj) {
            int A = C1578K.m4253A(obj);
            for (ValueEntry valueEntry = this.hashTable[mask() & A]; valueEntry != null; valueEntry = valueEntry.nextInValueBucket) {
                if (valueEntry.mo8809a(obj, A)) {
                    return true;
                }
            }
            return false;
        }

        public Iterator iterator() {
            return new C1658ga(this);
        }

        /* renamed from: l */
        public C1661ha mo8814l() {
            return this.f2450WP;
        }

        public boolean remove(Object obj) {
            int A = C1578K.m4253A(obj);
            int mask = mask() & A;
            ValueEntry valueEntry = null;
            for (ValueEntry valueEntry2 = this.hashTable[mask]; valueEntry2 != null; valueEntry2 = valueEntry2.nextInValueBucket) {
                if (valueEntry2.mo8809a(obj, A)) {
                    if (valueEntry == null) {
                        this.hashTable[mask] = valueEntry2.nextInValueBucket;
                    } else {
                        valueEntry.nextInValueBucket = valueEntry2.nextInValueBucket;
                    }
                    LinkedHashMultimap.m4273b(valueEntry2.mo8814l(), valueEntry2.mo8806O());
                    ValueEntry bl = valueEntry2.mo8812bl();
                    ValueEntry cl = valueEntry2.mo8813cl();
                    bl.mo8810b(cl);
                    cl.mo8807a(bl);
                    this.size--;
                    this.modCount++;
                    return true;
                }
                valueEntry = valueEntry2;
            }
            return false;
        }

        public int size() {
            return this.size;
        }

        /* renamed from: a */
        public void mo8808a(C1661ha haVar) {
            this.f2449VP = haVar;
        }

        /* renamed from: b */
        public void mo8811b(C1661ha haVar) {
            this.f2450WP = haVar;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m4273b(C1661ha haVar, C1661ha haVar2) {
        haVar.mo8808a(haVar2);
        haVar2.mo8811b(haVar);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.f2448kN = new ValueEntry((Object) null, (Object) null, 0, (ValueEntry) null);
        ValueEntry valueEntry = this.f2448kN;
        valueEntry.mo8810b(valueEntry);
        valueEntry.mo8807a(valueEntry);
        this.valueSetCapacity = objectInputStream.readInt();
        int readInt = objectInputStream.readInt();
        LinkedHashMap linkedHashMap = new LinkedHashMap(C1633Xa.m4545db(readInt));
        for (int i = 0; i < readInt; i++) {
            Object readObject = objectInputStream.readObject();
            linkedHashMap.put(readObject, mo8805z(readObject));
        }
        int readInt2 = objectInputStream.readInt();
        for (int i2 = 0; i2 < readInt2; i2++) {
            ((Collection) linkedHashMap.get(objectInputStream.readObject())).add(objectInputStream.readObject());
        }
        mo8582b((Map) linkedHashMap);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.valueSetCapacity);
        objectOutputStream.writeInt(keySet().size());
        for (Object writeObject : keySet()) {
            objectOutputStream.writeObject(writeObject);
        }
        objectOutputStream.writeInt(size());
        for (Map.Entry entry : entries()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    /* renamed from: a */
    public boolean mo8798a(Object obj, Object obj2) {
        Collection collection = (Collection) mo8589ja().get(obj);
        return collection != null && collection.contains(obj2);
    }

    public void clear() {
        super.clear();
        ValueEntry valueEntry = this.f2448kN;
        valueEntry.mo8810b(valueEntry);
        valueEntry.mo8807a(valueEntry);
    }

    public Set entries() {
        return super.entries();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C1635Ya) {
            return mo8589ja().equals(((C1635Ya) obj).mo8589ja());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: gl */
    public Iterator mo8800gl() {
        return new C1655fa(this);
    }

    public int hashCode() {
        return mo8589ja().hashCode();
    }

    /* renamed from: ja */
    public /* bridge */ /* synthetic */ Map mo8589ja() {
        return super.mo8589ja();
    }

    public /* bridge */ /* synthetic */ Set keySet() {
        return super.keySet();
    }

    public boolean remove(Object obj, Object obj2) {
        Collection collection = (Collection) mo8589ja().get(obj);
        return collection != null && collection.remove(obj2);
    }

    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    public String toString() {
        return mo8589ja().toString();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: z */
    public Collection mo8805z(Object obj) {
        return new ValueSet(obj, this.valueSetCapacity);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: hl */
    public Set m4278hl() {
        return new LinkedHashSet(this.valueSetCapacity);
    }

    /* renamed from: a */
    static /* synthetic */ void m4272a(C1661ha haVar, C1661ha haVar2) {
        haVar.mo8808a(haVar2);
        haVar2.mo8811b(haVar);
    }
}
