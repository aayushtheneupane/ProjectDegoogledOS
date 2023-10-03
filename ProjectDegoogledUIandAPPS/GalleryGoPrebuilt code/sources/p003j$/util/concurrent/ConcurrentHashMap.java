package p003j$.util.concurrent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import p003j$.util.Iterator$$CC;
import p003j$.util.Map;
import p003j$.util.Spliterator;
import p003j$.util.Spliterator$$CC;
import p003j$.util.function.Consumer;
import sun.misc.Unsafe;

/* renamed from: j$.util.concurrent.ConcurrentHashMap */
public class ConcurrentHashMap extends AbstractMap implements ConcurrentMap, Serializable, Map, p003j$.util.Map {
    private static final long ABASE;
    private static final int ASHIFT;
    private static final long BASECOUNT;
    private static final long CELLSBUSY;
    private static final long CELLVALUE;
    private static final int MAX_RESIZERS = ((1 << (32 - 16)) - 1);
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static int RESIZE_STAMP_BITS = 16;
    private static final int RESIZE_STAMP_SHIFT = (32 - 16);
    private static final long SIZECTL;
    private static final long TRANSFERINDEX;

    /* renamed from: U */
    private static final Unsafe f16484U;
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("segments", Segment[].class), new ObjectStreamField("segmentMask", Integer.TYPE), new ObjectStreamField("segmentShift", Integer.TYPE)};
    private static final long serialVersionUID = 7249069246763182397L;
    private volatile transient long baseCount;
    private volatile transient int cellsBusy;
    private volatile transient CounterCell[] counterCells;
    private transient EntrySetView entrySet;
    private transient KeySetView keySet;
    private volatile transient Node[] nextTable;
    private volatile transient int sizeCtl;
    volatile transient Node[] table;
    private volatile transient int transferIndex;
    private transient ValuesView values;

    static final int spread(int i) {
        return (i ^ (i >>> 16)) & Integer.MAX_VALUE;
    }

    private static final int tableSizeFor(int i) {
        int i2 = i - 1;
        int i3 = i2 | (i2 >>> 1);
        int i4 = i3 | (i3 >>> 2);
        int i5 = i4 | (i4 >>> 4);
        int i6 = i5 | (i5 >>> 8);
        int i7 = i6 | (i6 >>> 16);
        if (i7 < 0) {
            return 1;
        }
        if (i7 >= 1073741824) {
            return 1073741824;
        }
        return 1 + i7;
    }

    static {
        try {
            Unsafe unsafe = DesugarUnsafe.getUnsafe();
            f16484U = unsafe;
            Class<ConcurrentHashMap> cls = ConcurrentHashMap.class;
            SIZECTL = unsafe.objectFieldOffset(cls.getDeclaredField("sizeCtl"));
            TRANSFERINDEX = f16484U.objectFieldOffset(cls.getDeclaredField("transferIndex"));
            BASECOUNT = f16484U.objectFieldOffset(cls.getDeclaredField("baseCount"));
            CELLSBUSY = f16484U.objectFieldOffset(cls.getDeclaredField("cellsBusy"));
            CELLVALUE = f16484U.objectFieldOffset(CounterCell.class.getDeclaredField("value"));
            Class<Node[]> cls2 = Node[].class;
            ABASE = (long) f16484U.arrayBaseOffset(cls2);
            int arrayIndexScale = f16484U.arrayIndexScale(cls2);
            if (((arrayIndexScale - 1) & arrayIndexScale) == 0) {
                ASHIFT = 31 - Integer.numberOfLeadingZeros(arrayIndexScale);
                return;
            }
            throw new Error("data type scale not a power of two");
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$Node */
    class Node implements Map.Entry, Map.Entry {
        final int hash;
        final Object key;
        volatile Node next;
        volatile Object val;

        Node(int i, Object obj, Object obj2, Node node) {
            this.hash = i;
            this.key = obj;
            this.val = obj2;
            this.next = node;
        }

        public final Object getKey() {
            return this.key;
        }

        public final Object getValue() {
            return this.val;
        }

        public final int hashCode() {
            return this.key.hashCode() ^ this.val.hashCode();
        }

        public final String toString() {
            String valueOf = String.valueOf(this.key);
            String valueOf2 = String.valueOf(this.val);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length());
            sb.append(valueOf);
            sb.append("=");
            sb.append(valueOf2);
            return sb.toString();
        }

        public final Object setValue(Object obj) {
            throw new UnsupportedOperationException();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
            r0 = r2.val;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r3 = (java.util.Map.Entry) r3;
            r0 = r3.getKey();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
            r3 = r3.getValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
            r1 = r2.key;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r3) {
            /*
                r2 = this;
                boolean r0 = r3 instanceof java.util.Map.Entry
                if (r0 == 0) goto L_0x0028
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r0 = r3.getKey()
                if (r0 == 0) goto L_0x0028
                java.lang.Object r3 = r3.getValue()
                if (r3 == 0) goto L_0x0028
                java.lang.Object r1 = r2.key
                if (r0 == r1) goto L_0x001c
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0028
            L_0x001c:
                java.lang.Object r0 = r2.val
                if (r3 == r0) goto L_0x0026
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0028
            L_0x0026:
                r3 = 1
                goto L_0x0029
            L_0x0028:
                r3 = 0
            L_0x0029:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.Node.equals(java.lang.Object):boolean");
        }

        /* access modifiers changed from: package-private */
        public Node find(int i, Object obj) {
            Object obj2;
            if (obj == null) {
                return null;
            }
            Node node = this;
            do {
                if (node.hash == i && ((obj2 = node.key) == obj || (obj2 != null && obj.equals(obj2)))) {
                    return node;
                }
                node = node.next;
            } while (node != null);
            return null;
        }
    }

    static Class comparableClassFor(Object obj) {
        Type[] actualTypeArguments;
        if (!(obj instanceof Comparable)) {
            return null;
        }
        Class<?> cls = obj.getClass();
        if (cls == String.class) {
            return cls;
        }
        Type[] genericInterfaces = cls.getGenericInterfaces();
        if (genericInterfaces == null) {
            return null;
        }
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                if (parameterizedType.getRawType() == Comparable.class && (actualTypeArguments = parameterizedType.getActualTypeArguments()) != null && actualTypeArguments.length == 1 && actualTypeArguments[0] == cls) {
                    return cls;
                }
            }
        }
        return null;
    }

    static int compareComparables(Class cls, Object obj, Object obj2) {
        if (obj2 == null || obj2.getClass() != cls) {
            return 0;
        }
        return ((Comparable) obj).compareTo(obj2);
    }

    static final Node tabAt(Node[] nodeArr, int i) {
        return (Node) f16484U.getObjectVolatile(nodeArr, (((long) i) << ASHIFT) + ABASE);
    }

    static final boolean casTabAt(Node[] nodeArr, int i, Node node, Node node2) {
        return f16484U.compareAndSwapObject(nodeArr, ABASE + (((long) i) << ASHIFT), node, node2);
    }

    static final void setTabAt(Node[] nodeArr, int i, Node node) {
        f16484U.putObjectVolatile(nodeArr, (((long) i) << ASHIFT) + ABASE, node);
    }

    public ConcurrentHashMap() {
    }

    public ConcurrentHashMap(int i) {
        int i2;
        if (i >= 0) {
            if (i >= 536870912) {
                i2 = 1073741824;
            } else {
                i2 = tableSizeFor(i + (i >>> 1) + 1);
            }
            this.sizeCtl = i2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public ConcurrentHashMap(int i, float f, int i2) {
        int i3;
        if (f <= 0.0f || i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        }
        double d = (double) (((float) ((long) (i < i2 ? i2 : i))) / f);
        Double.isNaN(d);
        long j = (long) (d + 1.0d);
        if (j >= 1073741824) {
            i3 = 1073741824;
        } else {
            i3 = tableSizeFor((int) j);
        }
        this.sizeCtl = i3;
    }

    public int size() {
        long sumCount = sumCount();
        if (sumCount < 0) {
            return 0;
        }
        if (sumCount > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) sumCount;
    }

    public boolean isEmpty() {
        return sumCount() <= 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004d, code lost:
        return r1.val;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object get(java.lang.Object r5) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            int r0 = spread(r0)
            j$.util.concurrent.ConcurrentHashMap$Node[] r1 = r4.table
            r2 = 0
            if (r1 == 0) goto L_0x004e
            int r3 = r1.length
            if (r3 <= 0) goto L_0x004e
            int r3 = r3 + -1
            r3 = r3 & r0
            j$.util.concurrent.ConcurrentHashMap$Node r1 = tabAt(r1, r3)
            if (r1 == 0) goto L_0x004e
            int r3 = r1.hash
            if (r3 != r0) goto L_0x002c
            java.lang.Object r3 = r1.key
            if (r3 == r5) goto L_0x0029
            if (r3 == 0) goto L_0x0037
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x0037
        L_0x0029:
            java.lang.Object r5 = r1.val
            return r5
        L_0x002c:
            if (r3 >= 0) goto L_0x0037
            j$.util.concurrent.ConcurrentHashMap$Node r5 = r1.find(r0, r5)
            if (r5 == 0) goto L_0x0036
            java.lang.Object r2 = r5.val
        L_0x0036:
            return r2
        L_0x0037:
            j$.util.concurrent.ConcurrentHashMap$Node r1 = r1.next
            if (r1 == 0) goto L_0x004e
            int r3 = r1.hash
            if (r3 != r0) goto L_0x0037
            java.lang.Object r3 = r1.key
            if (r3 == r5) goto L_0x004b
            if (r3 == 0) goto L_0x0037
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x0037
        L_0x004b:
            java.lang.Object r5 = r1.val
            return r5
        L_0x004e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.get(java.lang.Object):java.lang.Object");
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(Object obj) {
        if (obj != null) {
            Node[] nodeArr = this.table;
            if (nodeArr != null) {
                Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
                while (true) {
                    Node advance = traverser.advance();
                    if (advance == null) {
                        break;
                    }
                    Object obj2 = advance.val;
                    if (obj2 == obj) {
                        return true;
                    }
                    if (obj2 != null && obj.equals(obj2)) {
                        return true;
                    }
                }
            }
            return false;
        }
        throw null;
    }

    public Object put(Object obj, Object obj2) {
        return putVal(obj, obj2, false);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0052, code lost:
        r7 = r6.val;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0054, code lost:
        if (r11 != false) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0056, code lost:
        r6.val = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x008c, code lost:
        addCount(1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0091, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object putVal(java.lang.Object r9, java.lang.Object r10, boolean r11) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x009b
            if (r10 == 0) goto L_0x009b
            int r1 = r9.hashCode()
            int r1 = spread(r1)
            r2 = 0
            j$.util.concurrent.ConcurrentHashMap$Node[] r3 = r8.table
        L_0x0010:
            if (r3 == 0) goto L_0x0095
            int r4 = r3.length
            if (r4 != 0) goto L_0x0017
            goto L_0x0095
        L_0x0017:
            int r4 = r4 + -1
            r4 = r4 & r1
            j$.util.concurrent.ConcurrentHashMap$Node r5 = tabAt(r3, r4)
            if (r5 != 0) goto L_0x002d
            j$.util.concurrent.ConcurrentHashMap$Node r5 = new j$.util.concurrent.ConcurrentHashMap$Node
            r5.<init>(r1, r9, r10, r0)
            boolean r4 = casTabAt(r3, r4, r0, r5)
            if (r4 == 0) goto L_0x0010
            goto L_0x008c
        L_0x002d:
            int r6 = r5.hash
            r7 = -1
            if (r6 != r7) goto L_0x0037
            j$.util.concurrent.ConcurrentHashMap$Node[] r3 = r8.helpTransfer(r3, r5)
            goto L_0x0010
        L_0x0037:
            monitor-enter(r5)
            j$.util.concurrent.ConcurrentHashMap$Node r7 = tabAt(r3, r4)     // Catch:{ all -> 0x0092 }
            if (r7 != r5) goto L_0x007e
            if (r6 < 0) goto L_0x0069
            r2 = 1
            r6 = r5
        L_0x0042:
            int r7 = r6.hash     // Catch:{ all -> 0x0092 }
            if (r7 != r1) goto L_0x0059
            java.lang.Object r7 = r6.key     // Catch:{ all -> 0x0092 }
            if (r7 == r9) goto L_0x0052
            if (r7 == 0) goto L_0x0059
            boolean r7 = r9.equals(r7)     // Catch:{ all -> 0x0092 }
            if (r7 == 0) goto L_0x0059
        L_0x0052:
            java.lang.Object r7 = r6.val     // Catch:{ all -> 0x0092 }
            if (r11 != 0) goto L_0x007f
            r6.val = r10     // Catch:{ all -> 0x0092 }
            goto L_0x007f
        L_0x0059:
            j$.util.concurrent.ConcurrentHashMap$Node r7 = r6.next     // Catch:{ all -> 0x0092 }
            if (r7 != 0) goto L_0x0065
            j$.util.concurrent.ConcurrentHashMap$Node r7 = new j$.util.concurrent.ConcurrentHashMap$Node     // Catch:{ all -> 0x0092 }
            r7.<init>(r1, r9, r10, r0)     // Catch:{ all -> 0x0092 }
            r6.next = r7     // Catch:{ all -> 0x0092 }
            goto L_0x007e
        L_0x0065:
            int r2 = r2 + 1
            r6 = r7
            goto L_0x0042
        L_0x0069:
            boolean r6 = r5 instanceof p003j$.util.concurrent.ConcurrentHashMap.TreeBin     // Catch:{ all -> 0x0092 }
            if (r6 == 0) goto L_0x007e
            r2 = 2
            r6 = r5
            j$.util.concurrent.ConcurrentHashMap$TreeBin r6 = (p003j$.util.concurrent.ConcurrentHashMap.TreeBin) r6     // Catch:{ all -> 0x0092 }
            j$.util.concurrent.ConcurrentHashMap$TreeNode r6 = r6.putTreeVal(r1, r9, r10)     // Catch:{ all -> 0x0092 }
            if (r6 == 0) goto L_0x007e
            java.lang.Object r7 = r6.val     // Catch:{ all -> 0x0092 }
            if (r11 != 0) goto L_0x007f
            r6.val = r10     // Catch:{ all -> 0x0092 }
            goto L_0x007f
        L_0x007e:
            r7 = r0
        L_0x007f:
            monitor-exit(r5)     // Catch:{ all -> 0x0092 }
            if (r2 == 0) goto L_0x0010
            r9 = 8
            if (r2 < r9) goto L_0x0089
            r8.treeifyBin(r3, r4)
        L_0x0089:
            if (r7 == 0) goto L_0x008c
            return r7
        L_0x008c:
            r9 = 1
            r8.addCount(r9, r2)
            return r0
        L_0x0092:
            r9 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0092 }
            throw r9
        L_0x0095:
            j$.util.concurrent.ConcurrentHashMap$Node[] r3 = r8.initTable()
            goto L_0x0010
        L_0x009b:
            goto L_0x009d
        L_0x009c:
            throw r0
        L_0x009d:
            goto L_0x009c
        */
        throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.putVal(java.lang.Object, java.lang.Object, boolean):java.lang.Object");
    }

    public void putAll(java.util.Map map) {
        tryPresize(map.size());
        for (Map.Entry entry : map.entrySet()) {
            putVal(entry.getKey(), entry.getValue(), false);
        }
    }

    public Object remove(Object obj) {
        return replaceNode(obj, (Object) null, (Object) null);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00b3, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object replaceNode(java.lang.Object r13, java.lang.Object r14, java.lang.Object r15) {
        /*
            r12 = this;
            int r0 = r13.hashCode()
            int r0 = spread(r0)
            j$.util.concurrent.ConcurrentHashMap$Node[] r1 = r12.table
        L_0x000a:
            r2 = 0
            if (r1 == 0) goto L_0x00b3
            int r3 = r1.length
            if (r3 == 0) goto L_0x00b3
            int r3 = r3 + -1
            r3 = r3 & r0
            j$.util.concurrent.ConcurrentHashMap$Node r4 = tabAt(r1, r3)
            if (r4 != 0) goto L_0x001b
            goto L_0x00b3
        L_0x001b:
            int r5 = r4.hash
            r6 = -1
            if (r5 != r6) goto L_0x0025
            j$.util.concurrent.ConcurrentHashMap$Node[] r1 = r12.helpTransfer(r1, r4)
            goto L_0x000a
        L_0x0025:
            r7 = 0
            monitor-enter(r4)
            j$.util.concurrent.ConcurrentHashMap$Node r8 = tabAt(r1, r3)     // Catch:{ all -> 0x00b0 }
            r9 = 1
            if (r8 != r4) goto L_0x00a2
            if (r5 < 0) goto L_0x006b
            r7 = r2
            r5 = r4
        L_0x0032:
            int r8 = r5.hash     // Catch:{ all -> 0x00b0 }
            if (r8 != r0) goto L_0x0062
            java.lang.Object r8 = r5.key     // Catch:{ all -> 0x00b0 }
            if (r8 == r13) goto L_0x0042
            if (r8 == 0) goto L_0x0062
            boolean r8 = r13.equals(r8)     // Catch:{ all -> 0x00b0 }
            if (r8 == 0) goto L_0x0062
        L_0x0042:
            java.lang.Object r8 = r5.val     // Catch:{ all -> 0x00b0 }
            if (r15 == 0) goto L_0x0050
            if (r15 == r8) goto L_0x0050
            if (r8 == 0) goto L_0x009f
            boolean r10 = r15.equals(r8)     // Catch:{ all -> 0x00b0 }
            if (r10 == 0) goto L_0x009f
        L_0x0050:
            if (r14 == 0) goto L_0x0055
            r5.val = r14     // Catch:{ all -> 0x00b0 }
            goto L_0x00a0
        L_0x0055:
            if (r7 == 0) goto L_0x005c
            j$.util.concurrent.ConcurrentHashMap$Node r3 = r5.next     // Catch:{ all -> 0x00b0 }
            r7.next = r3     // Catch:{ all -> 0x00b0 }
            goto L_0x00a0
        L_0x005c:
            j$.util.concurrent.ConcurrentHashMap$Node r5 = r5.next     // Catch:{ all -> 0x00b0 }
            setTabAt(r1, r3, r5)     // Catch:{ all -> 0x00b0 }
            goto L_0x00a0
        L_0x0062:
            j$.util.concurrent.ConcurrentHashMap$Node r7 = r5.next     // Catch:{ all -> 0x00b0 }
            if (r7 != 0) goto L_0x0067
            goto L_0x009f
        L_0x0067:
            r11 = r7
            r7 = r5
            r5 = r11
            goto L_0x0032
        L_0x006b:
            boolean r5 = r4 instanceof p003j$.util.concurrent.ConcurrentHashMap.TreeBin     // Catch:{ all -> 0x00b0 }
            if (r5 == 0) goto L_0x00a2
            r5 = r4
            j$.util.concurrent.ConcurrentHashMap$TreeBin r5 = (p003j$.util.concurrent.ConcurrentHashMap.TreeBin) r5     // Catch:{ all -> 0x00b0 }
            j$.util.concurrent.ConcurrentHashMap$TreeNode r7 = r5.root     // Catch:{ all -> 0x00b0 }
            if (r7 == 0) goto L_0x009f
            j$.util.concurrent.ConcurrentHashMap$TreeNode r7 = r7.findTreeNode(r0, r13, r2)     // Catch:{ all -> 0x00b0 }
            if (r7 == 0) goto L_0x009f
            java.lang.Object r8 = r7.val     // Catch:{ all -> 0x00b0 }
            if (r15 == 0) goto L_0x008a
            if (r15 == r8) goto L_0x008a
            if (r8 == 0) goto L_0x009f
            boolean r10 = r15.equals(r8)     // Catch:{ all -> 0x00b0 }
            if (r10 == 0) goto L_0x009f
        L_0x008a:
            if (r14 == 0) goto L_0x008f
            r7.val = r14     // Catch:{ all -> 0x00b0 }
            goto L_0x00a0
        L_0x008f:
            boolean r7 = r5.removeTreeNode(r7)     // Catch:{ all -> 0x00b0 }
            if (r7 == 0) goto L_0x00a0
            j$.util.concurrent.ConcurrentHashMap$TreeNode r5 = r5.first     // Catch:{ all -> 0x00b0 }
            j$.util.concurrent.ConcurrentHashMap$Node r5 = untreeify(r5)     // Catch:{ all -> 0x00b0 }
            setTabAt(r1, r3, r5)     // Catch:{ all -> 0x00b0 }
            goto L_0x00a0
        L_0x009f:
            r8 = r2
        L_0x00a0:
            r7 = 1
            goto L_0x00a3
        L_0x00a2:
            r8 = r2
        L_0x00a3:
            monitor-exit(r4)     // Catch:{ all -> 0x00b0 }
            if (r7 == 0) goto L_0x000a
            if (r8 == 0) goto L_0x00b3
            if (r14 != 0) goto L_0x00af
            r13 = -1
            r12.addCount(r13, r6)
        L_0x00af:
            return r8
        L_0x00b0:
            r13 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00b0 }
            throw r13
        L_0x00b3:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.replaceNode(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public void clear() {
        Node node;
        Node[] nodeArr = this.table;
        long j = 0;
        loop0:
        while (true) {
            int i = 0;
            while (nodeArr != null && i < nodeArr.length) {
                Node tabAt = tabAt(nodeArr, i);
                if (tabAt == null) {
                    i++;
                } else {
                    int i2 = tabAt.hash;
                    if (i2 == -1) {
                        nodeArr = helpTransfer(nodeArr, tabAt);
                    } else {
                        synchronized (tabAt) {
                            if (tabAt(nodeArr, i) == tabAt) {
                                if (i2 >= 0) {
                                    node = tabAt;
                                } else {
                                    node = tabAt instanceof TreeBin ? ((TreeBin) tabAt).first : null;
                                }
                                while (node != null) {
                                    j--;
                                    node = node.next;
                                }
                                setTabAt(nodeArr, i, (Node) null);
                                i++;
                            }
                        }
                    }
                }
            }
        }
        if (j != 0) {
            addCount(j, -1);
        }
    }

    public Set keySet() {
        KeySetView keySetView = this.keySet;
        if (keySetView != null) {
            return keySetView;
        }
        KeySetView keySetView2 = new KeySetView(this, (Object) null);
        this.keySet = keySetView2;
        return keySetView2;
    }

    public Collection values() {
        ValuesView valuesView = this.values;
        if (valuesView != null) {
            return valuesView;
        }
        ValuesView valuesView2 = new ValuesView(this);
        this.values = valuesView2;
        return valuesView2;
    }

    public Set entrySet() {
        EntrySetView entrySetView = this.entrySet;
        if (entrySetView != null) {
            return entrySetView;
        }
        EntrySetView entrySetView2 = new EntrySetView(this);
        this.entrySet = entrySetView2;
        return entrySetView2;
    }

    public int hashCode() {
        Node[] nodeArr = this.table;
        int i = 0;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                i += advance.val.hashCode() ^ advance.key.hashCode();
            }
        }
        return i;
    }

    public String toString() {
        Node[] nodeArr = this.table;
        int length = nodeArr == null ? 0 : nodeArr.length;
        Traverser traverser = new Traverser(nodeArr, length, 0, length);
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        Node advance = traverser.advance();
        if (advance != null) {
            while (true) {
                Object obj = advance.key;
                Object obj2 = advance.val;
                if (obj == this) {
                    obj = "(this Map)";
                }
                sb.append(obj);
                sb.append('=');
                if (obj2 == this) {
                    obj2 = "(this Map)";
                }
                sb.append(obj2);
                advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                sb.append(',');
                sb.append(' ');
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        Object value;
        Object obj2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof java.util.Map)) {
            return false;
        }
        java.util.Map map = (java.util.Map) obj;
        Node[] nodeArr = this.table;
        int length = nodeArr == null ? 0 : nodeArr.length;
        Traverser traverser = new Traverser(nodeArr, length, 0, length);
        while (true) {
            Node advance = traverser.advance();
            if (advance != null) {
                Object obj3 = advance.val;
                Object obj4 = map.get(advance.key);
                if (obj4 == null || (obj4 != obj3 && !obj4.equals(obj3))) {
                    return false;
                }
            } else {
                for (Map.Entry entry : map.entrySet()) {
                    Object key = entry.getKey();
                    if (key == null || (value = entry.getValue()) == null || (obj2 = get(key)) == null || (value != obj2 && !value.equals(obj2))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$Segment */
    class Segment extends ReentrantLock implements Serializable {
        private static final long serialVersionUID = 2249069246763182397L;

        Segment(float f) {
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        int i = 1;
        int i2 = 0;
        while (i < 16) {
            i2++;
            i <<= 1;
        }
        int i3 = 32 - i2;
        int i4 = i - 1;
        Segment[] segmentArr = new Segment[16];
        for (int i5 = 0; i5 < 16; i5++) {
            segmentArr[i5] = new Segment(0.75f);
        }
        objectOutputStream.putFields().put("segments", segmentArr);
        objectOutputStream.putFields().put("segmentShift", i3);
        objectOutputStream.putFields().put("segmentMask", i4);
        objectOutputStream.writeFields();
        Node[] nodeArr = this.table;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                objectOutputStream.writeObject(advance.key);
                objectOutputStream.writeObject(advance.val);
            }
        }
        objectOutputStream.writeObject((Object) null);
        objectOutputStream.writeObject((Object) null);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        long j;
        int i;
        boolean z;
        Object obj;
        this.sizeCtl = -1;
        objectInputStream.defaultReadObject();
        long j2 = 0;
        long j3 = 0;
        Node node = null;
        while (true) {
            Object readObject = objectInputStream.readObject();
            Object readObject2 = objectInputStream.readObject();
            j = 1;
            if (readObject != null && readObject2 != null) {
                j3++;
                node = new Node(spread(readObject.hashCode()), readObject, readObject2, node);
            }
        }
        if (j3 == 0) {
            this.sizeCtl = 0;
            return;
        }
        if (j3 >= 536870912) {
            i = 1073741824;
        } else {
            int i2 = (int) j3;
            i = tableSizeFor(i2 + (i2 >>> 1) + 1);
        }
        Node[] nodeArr = new Node[i];
        int i3 = i - 1;
        while (node != null) {
            Node node2 = node.next;
            int i4 = node.hash;
            int i5 = i4 & i3;
            Node tabAt = tabAt(nodeArr, i5);
            if (tabAt == null) {
                z = true;
            } else {
                Object obj2 = node.key;
                if (tabAt.hash >= 0) {
                    Node node3 = tabAt;
                    int i6 = 0;
                    while (true) {
                        if (node3 == null) {
                            z = true;
                            break;
                        } else if (node3.hash != i4 || ((obj = node3.key) != obj2 && (obj == null || !obj2.equals(obj)))) {
                            i6++;
                            node3 = node3.next;
                        }
                    }
                    z = false;
                    if (z && i6 >= 8) {
                        j2++;
                        node.next = tabAt;
                        Node node4 = node;
                        TreeNode treeNode = null;
                        TreeNode treeNode2 = null;
                        while (node4 != null) {
                            long j4 = j2;
                            TreeNode treeNode3 = new TreeNode(node4.hash, node4.key, node4.val, (Node) null, (TreeNode) null);
                            treeNode3.prev = treeNode2;
                            if (treeNode2 == null) {
                                treeNode = treeNode3;
                            } else {
                                treeNode2.next = treeNode3;
                            }
                            node4 = node4.next;
                            treeNode2 = treeNode3;
                            j2 = j4;
                        }
                        long j5 = j2;
                        setTabAt(nodeArr, i5, new TreeBin(treeNode));
                    }
                } else if (((TreeBin) tabAt).putTreeVal(i4, obj2, node.val) == null) {
                    j2 += j;
                }
                z = false;
            }
            if (z) {
                j2++;
                node.next = tabAt;
                setTabAt(nodeArr, i5, node);
            }
            j = 1;
            node = node2;
        }
        this.table = nodeArr;
        this.sizeCtl = i - (i >>> 2);
        this.baseCount = j2;
    }

    public Object putIfAbsent(Object obj, Object obj2) {
        return putVal(obj, obj2, true);
    }

    public boolean remove(Object obj, Object obj2) {
        if (obj != null) {
            return (obj2 == null || replaceNode(obj, (Object) null, obj2) == null) ? false : true;
        }
        throw null;
    }

    public boolean replace(Object obj, Object obj2, Object obj3) {
        if (obj != null && obj2 != null && obj3 != null) {
            return replaceNode(obj, obj3, obj2) != null;
        }
        throw null;
    }

    public Object replace(Object obj, Object obj2) {
        if (obj != null && obj2 != null) {
            return replaceNode(obj, obj2, (Object) null);
        }
        throw null;
    }

    public Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 == null ? obj2 : obj3;
    }

    public long mappingCount() {
        long sumCount = sumCount();
        if (sumCount < 0) {
            return 0;
        }
        return sumCount;
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$ForwardingNode */
    final class ForwardingNode extends Node {
        final Node[] nextTable;

        ForwardingNode(Node[] nodeArr) {
            super(-1, (Object) null, (Object) null, (Node) null);
            this.nextTable = nodeArr;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0029, code lost:
            if ((r0 instanceof p003j$.util.concurrent.ConcurrentHashMap.ForwardingNode) == false) goto L_0x0030;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x002b, code lost:
            r0 = ((p003j$.util.concurrent.ConcurrentHashMap.ForwardingNode) r0).nextTable;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0034, code lost:
            return r0.find(r5, r6);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public p003j$.util.concurrent.ConcurrentHashMap.Node find(int r5, java.lang.Object r6) {
            /*
                r4 = this;
                j$.util.concurrent.ConcurrentHashMap$Node[] r0 = r4.nextTable
            L_0x0002:
                r1 = 0
                if (r6 == 0) goto L_0x0039
                if (r0 == 0) goto L_0x0039
                int r2 = r0.length
                if (r2 == 0) goto L_0x0039
                int r2 = r2 + -1
                r2 = r2 & r5
                j$.util.concurrent.ConcurrentHashMap$Node r0 = p003j$.util.concurrent.ConcurrentHashMap.tabAt(r0, r2)
                if (r0 != 0) goto L_0x0014
                goto L_0x0039
            L_0x0014:
                int r2 = r0.hash
                if (r2 != r5) goto L_0x0025
                java.lang.Object r3 = r0.key
                if (r3 == r6) goto L_0x0024
                if (r3 == 0) goto L_0x0025
                boolean r3 = r6.equals(r3)
                if (r3 == 0) goto L_0x0025
            L_0x0024:
                return r0
            L_0x0025:
                if (r2 >= 0) goto L_0x0035
                boolean r1 = r0 instanceof p003j$.util.concurrent.ConcurrentHashMap.ForwardingNode
                if (r1 == 0) goto L_0x0030
                j$.util.concurrent.ConcurrentHashMap$ForwardingNode r0 = (p003j$.util.concurrent.ConcurrentHashMap.ForwardingNode) r0
                j$.util.concurrent.ConcurrentHashMap$Node[] r0 = r0.nextTable
                goto L_0x0002
            L_0x0030:
                j$.util.concurrent.ConcurrentHashMap$Node r5 = r0.find(r5, r6)
                return r5
            L_0x0035:
                j$.util.concurrent.ConcurrentHashMap$Node r0 = r0.next
                if (r0 != 0) goto L_0x0014
            L_0x0039:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.ForwardingNode.find(int, java.lang.Object):j$.util.concurrent.ConcurrentHashMap$Node");
        }
    }

    static final int resizeStamp(int i) {
        return Integer.numberOfLeadingZeros(i) | (1 << (RESIZE_STAMP_BITS - 1));
    }

    /* JADX INFO: finally extract failed */
    private final Node[] initTable() {
        while (true) {
            Node[] nodeArr = this.table;
            if (nodeArr != null && nodeArr.length != 0) {
                return nodeArr;
            }
            int i = this.sizeCtl;
            if (i < 0) {
                Thread.yield();
            } else {
                if (f16484U.compareAndSwapInt(this, SIZECTL, i, -1)) {
                    try {
                        Node[] nodeArr2 = this.table;
                        if (nodeArr2 == null || nodeArr2.length == 0) {
                            int i2 = i > 0 ? i : 16;
                            Node[] nodeArr3 = new Node[i2];
                            this.table = nodeArr3;
                            i = i2 - (i2 >>> 2);
                            nodeArr2 = nodeArr3;
                        }
                        this.sizeCtl = i;
                        return nodeArr2;
                    } catch (Throwable th) {
                        this.sizeCtl = i;
                        throw th;
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0012, code lost:
        if (r1.compareAndSwapLong(r11, r3, r5, r9) == false) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void addCount(long r12, int r14) {
        /*
            r11 = this;
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r0 = r11.counterCells
            if (r0 != 0) goto L_0x0014
            sun.misc.Unsafe r1 = f16484U
            long r3 = BASECOUNT
            long r5 = r11.baseCount
            long r9 = r5 + r12
            r2 = r11
            r7 = r9
            boolean r1 = r1.compareAndSwapLong(r2, r3, r5, r7)
            if (r1 != 0) goto L_0x003b
        L_0x0014:
            r1 = 1
            if (r0 == 0) goto L_0x0096
            int r2 = r0.length
            int r2 = r2 - r1
            if (r2 < 0) goto L_0x0096
            int r3 = p003j$.util.concurrent.ThreadLocalRandom.getProbe()
            r2 = r2 & r3
            r4 = r0[r2]
            if (r4 == 0) goto L_0x0096
            sun.misc.Unsafe r3 = f16484U
            long r5 = CELLVALUE
            long r7 = r4.value
            long r9 = r7 + r12
            boolean r0 = r3.compareAndSwapLong(r4, r5, r7, r9)
            if (r0 != 0) goto L_0x0034
            r1 = r0
            goto L_0x0096
        L_0x0034:
            if (r14 > r1) goto L_0x0037
            return
        L_0x0037:
            long r9 = r11.sumCount()
        L_0x003b:
            if (r14 < 0) goto L_0x0095
        L_0x003d:
            int r4 = r11.sizeCtl
            long r12 = (long) r4
            int r14 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r14 < 0) goto L_0x0095
            j$.util.concurrent.ConcurrentHashMap$Node[] r12 = r11.table
            if (r12 == 0) goto L_0x0095
            int r13 = r12.length
            r14 = 1073741824(0x40000000, float:2.0)
            if (r13 >= r14) goto L_0x0095
            int r13 = resizeStamp(r13)
            if (r4 >= 0) goto L_0x007c
            int r14 = RESIZE_STAMP_SHIFT
            int r14 = r4 >>> r14
            if (r14 != r13) goto L_0x0095
            int r14 = r13 + 1
            if (r4 == r14) goto L_0x0095
            int r14 = MAX_RESIZERS
            int r13 = r13 + r14
            if (r4 == r13) goto L_0x0095
            j$.util.concurrent.ConcurrentHashMap$Node[] r13 = r11.nextTable
            if (r13 == 0) goto L_0x0095
            int r14 = r11.transferIndex
            if (r14 > 0) goto L_0x006b
            goto L_0x0095
        L_0x006b:
            sun.misc.Unsafe r0 = f16484U
            long r2 = SIZECTL
            int r5 = r4 + 1
            r1 = r11
            boolean r14 = r0.compareAndSwapInt(r1, r2, r4, r5)
            if (r14 == 0) goto L_0x0090
            r11.transfer(r12, r13)
            goto L_0x0090
        L_0x007c:
            sun.misc.Unsafe r0 = f16484U
            long r2 = SIZECTL
            int r14 = RESIZE_STAMP_SHIFT
            int r13 = r13 << r14
            int r5 = r13 + 2
            r1 = r11
            boolean r13 = r0.compareAndSwapInt(r1, r2, r4, r5)
            if (r13 == 0) goto L_0x0090
            r13 = 0
            r11.transfer(r12, r13)
        L_0x0090:
            long r9 = r11.sumCount()
            goto L_0x003d
        L_0x0095:
            return
        L_0x0096:
            r11.fullAddCount(r12, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.addCount(long, int):void");
    }

    /* access modifiers changed from: package-private */
    public final Node[] helpTransfer(Node[] nodeArr, Node node) {
        Node[] nodeArr2;
        int i;
        if (nodeArr == null || !(node instanceof ForwardingNode) || (nodeArr2 = ((ForwardingNode) node).nextTable) == null) {
            return this.table;
        }
        int resizeStamp = resizeStamp(nodeArr.length);
        while (true) {
            if (nodeArr2 != this.nextTable || this.table != nodeArr || (i = this.sizeCtl) >= 0 || (i >>> RESIZE_STAMP_SHIFT) != resizeStamp || i == resizeStamp + 1 || i == MAX_RESIZERS + resizeStamp || this.transferIndex <= 0) {
                break;
            }
            if (f16484U.compareAndSwapInt(this, SIZECTL, i, i + 1)) {
                transfer(nodeArr, nodeArr2);
                break;
            }
        }
        return nodeArr2;
    }

    private final void tryPresize(int i) {
        int length;
        Node[] nodeArr;
        int tableSizeFor = i >= 536870912 ? 1073741824 : tableSizeFor(i + (i >>> 1) + 1);
        while (true) {
            int i2 = this.sizeCtl;
            if (i2 >= 0) {
                Node[] nodeArr2 = this.table;
                if (nodeArr2 == null || (length = nodeArr2.length) == 0) {
                    int i3 = i2 > tableSizeFor ? i2 : tableSizeFor;
                    if (f16484U.compareAndSwapInt(this, SIZECTL, i2, -1)) {
                        try {
                            if (this.table == nodeArr2) {
                                this.table = new Node[i3];
                                i2 = i3 - (i3 >>> 2);
                            }
                        } finally {
                            this.sizeCtl = i2;
                        }
                    }
                } else if (tableSizeFor > i2 && length < 1073741824) {
                    if (nodeArr2 == this.table) {
                        int resizeStamp = resizeStamp(length);
                        if (i2 >= 0) {
                            if (f16484U.compareAndSwapInt(this, SIZECTL, i2, (resizeStamp << RESIZE_STAMP_SHIFT) + 2)) {
                                transfer(nodeArr2, (Node[]) null);
                            }
                        } else if ((i2 >>> RESIZE_STAMP_SHIFT) == resizeStamp && i2 != resizeStamp + 1 && i2 != resizeStamp + MAX_RESIZERS && (nodeArr = this.nextTable) != null && this.transferIndex > 0) {
                            if (f16484U.compareAndSwapInt(this, SIZECTL, i2, i2 + 1)) {
                                transfer(nodeArr2, nodeArr);
                            }
                        } else {
                            return;
                        }
                    } else {
                        continue;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v11, resolved type: j$.util.concurrent.ConcurrentHashMap$Node} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v12, resolved type: j$.util.concurrent.ConcurrentHashMap$Node} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: j$.util.concurrent.ConcurrentHashMap$TreeNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v13, resolved type: j$.util.concurrent.ConcurrentHashMap$Node} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v14, resolved type: j$.util.concurrent.ConcurrentHashMap$Node} */
    /* JADX WARNING: type inference failed for: r12v12, types: [j$.util.concurrent.ConcurrentHashMap$Node] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void transfer(p003j$.util.concurrent.ConcurrentHashMap.Node[] r30, p003j$.util.concurrent.ConcurrentHashMap.Node[] r31) {
        /*
            r29 = this;
            r7 = r29
            r0 = r30
            int r8 = r0.length
            int r1 = NCPU
            r9 = 1
            if (r1 <= r9) goto L_0x000e
            int r2 = r8 >>> 3
            int r2 = r2 / r1
            goto L_0x000f
        L_0x000e:
            r2 = r8
        L_0x000f:
            r1 = 16
            if (r2 >= r1) goto L_0x0016
            r10 = 16
            goto L_0x0017
        L_0x0016:
            r10 = r2
        L_0x0017:
            if (r31 != 0) goto L_0x0029
            int r1 = r8 << 1
            j$.util.concurrent.ConcurrentHashMap$Node[] r1 = new p003j$.util.concurrent.ConcurrentHashMap.Node[r1]     // Catch:{ all -> 0x0023 }
            r7.nextTable = r1
            r7.transferIndex = r8
            r11 = r1
            goto L_0x002b
        L_0x0023:
            r0 = 2147483647(0x7fffffff, float:NaN)
            r7.sizeCtl = r0
            return
        L_0x0029:
            r11 = r31
        L_0x002b:
            int r12 = r11.length
            j$.util.concurrent.ConcurrentHashMap$ForwardingNode r13 = new j$.util.concurrent.ConcurrentHashMap$ForwardingNode
            r13.<init>(r11)
            r5 = 0
            r6 = 0
            r15 = 1
            r16 = 0
        L_0x0036:
            r1 = -1
            if (r15 == 0) goto L_0x007c
            int r6 = r6 + -1
            if (r6 >= r5) goto L_0x0072
            if (r16 == 0) goto L_0x0040
            goto L_0x0072
        L_0x0040:
            int r3 = r7.transferIndex
            if (r3 > 0) goto L_0x0046
            r6 = -1
            goto L_0x007a
        L_0x0046:
            sun.misc.Unsafe r1 = f16484U
            long r17 = TRANSFERINDEX
            if (r3 <= r10) goto L_0x0051
            int r2 = r3 - r10
            r19 = r2
            goto L_0x0053
        L_0x0051:
            r19 = 0
        L_0x0053:
            r2 = r29
            r20 = r3
            r3 = r17
            r17 = r5
            r5 = r20
            r18 = r6
            r6 = r19
            boolean r1 = r1.compareAndSwapInt(r2, r3, r5, r6)
            if (r1 == 0) goto L_0x006d
            int r3 = r20 + -1
            r6 = r3
            r5 = r19
            goto L_0x007a
        L_0x006d:
            r5 = r17
            r6 = r18
            goto L_0x0036
        L_0x0072:
            r17 = r5
            r18 = r6
            r5 = r17
            r6 = r18
        L_0x007a:
            r15 = 0
            goto L_0x0036
        L_0x007c:
            r17 = r5
            r2 = 0
            if (r6 < 0) goto L_0x019d
            if (r6 >= r8) goto L_0x019d
            int r3 = r6 + r8
            if (r3 < r12) goto L_0x0089
            goto L_0x019d
        L_0x0089:
            j$.util.concurrent.ConcurrentHashMap$Node r4 = tabAt(r0, r6)
            if (r4 != 0) goto L_0x009d
            boolean r1 = casTabAt(r0, r6, r2, r13)
            r15 = r1
            r9 = r7
            r19 = r10
            r20 = r12
            r7 = r13
        L_0x009a:
            r10 = 1
            goto L_0x01db
        L_0x009d:
            int r5 = r4.hash
            if (r5 != r1) goto L_0x00ab
            r9 = r7
            r19 = r10
            r20 = r12
            r7 = r13
            r10 = 1
            r15 = 1
            goto L_0x01db
        L_0x00ab:
            monitor-enter(r4)
            j$.util.concurrent.ConcurrentHashMap$Node r1 = tabAt(r0, r6)     // Catch:{ all -> 0x019a }
            if (r1 != r4) goto L_0x0190
            if (r5 < 0) goto L_0x0104
            r1 = r5 & r8
            j$.util.concurrent.ConcurrentHashMap$Node r5 = r4.next     // Catch:{ all -> 0x019a }
            r15 = r4
        L_0x00b9:
            if (r5 == 0) goto L_0x00c5
            int r14 = r5.hash     // Catch:{ all -> 0x019a }
            r14 = r14 & r8
            if (r14 == r1) goto L_0x00c2
            r15 = r5
            r1 = r14
        L_0x00c2:
            j$.util.concurrent.ConcurrentHashMap$Node r5 = r5.next     // Catch:{ all -> 0x019a }
            goto L_0x00b9
        L_0x00c5:
            if (r1 != 0) goto L_0x00ca
            r1 = r2
            r2 = r15
            goto L_0x00cb
        L_0x00ca:
            r1 = r15
        L_0x00cb:
            r5 = r4
        L_0x00cc:
            if (r5 == r15) goto L_0x00f3
            int r14 = r5.hash     // Catch:{ all -> 0x019a }
            java.lang.Object r9 = r5.key     // Catch:{ all -> 0x019a }
            r19 = r10
            java.lang.Object r10 = r5.val     // Catch:{ all -> 0x019a }
            r20 = r14 & r8
            if (r20 != 0) goto L_0x00e3
            r20 = r12
            j$.util.concurrent.ConcurrentHashMap$Node r12 = new j$.util.concurrent.ConcurrentHashMap$Node     // Catch:{ all -> 0x019a }
            r12.<init>(r14, r9, r10, r2)     // Catch:{ all -> 0x019a }
            r2 = r12
            goto L_0x00eb
        L_0x00e3:
            r20 = r12
            j$.util.concurrent.ConcurrentHashMap$Node r12 = new j$.util.concurrent.ConcurrentHashMap$Node     // Catch:{ all -> 0x019a }
            r12.<init>(r14, r9, r10, r1)     // Catch:{ all -> 0x019a }
            r1 = r12
        L_0x00eb:
            j$.util.concurrent.ConcurrentHashMap$Node r5 = r5.next     // Catch:{ all -> 0x019a }
            r10 = r19
            r12 = r20
            r9 = 1
            goto L_0x00cc
        L_0x00f3:
            r19 = r10
            r20 = r12
            setTabAt(r11, r6, r2)     // Catch:{ all -> 0x019a }
            setTabAt(r11, r3, r1)     // Catch:{ all -> 0x019a }
            setTabAt(r0, r6, r13)     // Catch:{ all -> 0x019a }
            r7 = r13
        L_0x0101:
            r15 = 1
            goto L_0x0195
        L_0x0104:
            r19 = r10
            r20 = r12
            boolean r1 = r4 instanceof p003j$.util.concurrent.ConcurrentHashMap.TreeBin     // Catch:{ all -> 0x019a }
            if (r1 == 0) goto L_0x0194
            r1 = r4
            j$.util.concurrent.ConcurrentHashMap$TreeBin r1 = (p003j$.util.concurrent.ConcurrentHashMap.TreeBin) r1     // Catch:{ all -> 0x019a }
            j$.util.concurrent.ConcurrentHashMap$TreeNode r5 = r1.first     // Catch:{ all -> 0x019a }
            r9 = r2
            r10 = r9
            r12 = r5
            r14 = 0
            r15 = 0
            r5 = r10
        L_0x0117:
            if (r12 == 0) goto L_0x015a
            r27 = r1
            int r1 = r12.hash     // Catch:{ all -> 0x019a }
            j$.util.concurrent.ConcurrentHashMap$TreeNode r7 = new j$.util.concurrent.ConcurrentHashMap$TreeNode     // Catch:{ all -> 0x019a }
            java.lang.Object r0 = r12.key     // Catch:{ all -> 0x019a }
            r28 = r13
            java.lang.Object r13 = r12.val     // Catch:{ all -> 0x019a }
            r25 = 0
            r26 = 0
            r21 = r7
            r22 = r1
            r23 = r0
            r24 = r13
            r21.<init>(r22, r23, r24, r25, r26)     // Catch:{ all -> 0x019a }
            r0 = r1 & r8
            if (r0 != 0) goto L_0x0144
            r7.prev = r10     // Catch:{ all -> 0x019a }
            if (r10 != 0) goto L_0x013e
            r2 = r7
            goto L_0x0140
        L_0x013e:
            r10.next = r7     // Catch:{ all -> 0x019a }
        L_0x0140:
            int r14 = r14 + 1
            r10 = r7
            goto L_0x014f
        L_0x0144:
            r7.prev = r9     // Catch:{ all -> 0x019a }
            if (r9 != 0) goto L_0x014a
            r5 = r7
            goto L_0x014c
        L_0x014a:
            r9.next = r7     // Catch:{ all -> 0x019a }
        L_0x014c:
            int r15 = r15 + 1
            r9 = r7
        L_0x014f:
            j$.util.concurrent.ConcurrentHashMap$Node r12 = r12.next     // Catch:{ all -> 0x019a }
            r7 = r29
            r0 = r30
            r1 = r27
            r13 = r28
            goto L_0x0117
        L_0x015a:
            r27 = r1
            r28 = r13
            r0 = 6
            if (r14 > r0) goto L_0x0166
            j$.util.concurrent.ConcurrentHashMap$Node r1 = untreeify(r2)     // Catch:{ all -> 0x019a }
            goto L_0x0170
        L_0x0166:
            if (r15 == 0) goto L_0x016e
            j$.util.concurrent.ConcurrentHashMap$TreeBin r1 = new j$.util.concurrent.ConcurrentHashMap$TreeBin     // Catch:{ all -> 0x019a }
            r1.<init>(r2)     // Catch:{ all -> 0x019a }
            goto L_0x0170
        L_0x016e:
            r1 = r27
        L_0x0170:
            if (r15 > r0) goto L_0x0177
            j$.util.concurrent.ConcurrentHashMap$Node r0 = untreeify(r5)     // Catch:{ all -> 0x019a }
            goto L_0x0181
        L_0x0177:
            if (r14 == 0) goto L_0x017f
            j$.util.concurrent.ConcurrentHashMap$TreeBin r0 = new j$.util.concurrent.ConcurrentHashMap$TreeBin     // Catch:{ all -> 0x019a }
            r0.<init>(r5)     // Catch:{ all -> 0x019a }
            goto L_0x0181
        L_0x017f:
            r0 = r27
        L_0x0181:
            setTabAt(r11, r6, r1)     // Catch:{ all -> 0x019a }
            setTabAt(r11, r3, r0)     // Catch:{ all -> 0x019a }
            r0 = r30
            r7 = r28
            setTabAt(r0, r6, r7)     // Catch:{ all -> 0x019a }
            goto L_0x0101
        L_0x0190:
            r19 = r10
            r20 = r12
        L_0x0194:
            r7 = r13
        L_0x0195:
            monitor-exit(r4)     // Catch:{ all -> 0x019a }
            r9 = r29
            goto L_0x009a
        L_0x019a:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x019a }
            throw r0
        L_0x019d:
            r19 = r10
            r20 = r12
            r7 = r13
            if (r16 == 0) goto L_0x01b3
            r9 = r29
            r9.nextTable = r2
            r9.table = r11
            int r0 = r8 << 1
            r10 = 1
            int r1 = r8 >>> 1
            int r0 = r0 - r1
            r9.sizeCtl = r0
            return
        L_0x01b3:
            r9 = r29
            r10 = 1
            sun.misc.Unsafe r1 = f16484U
            long r3 = SIZECTL
            int r12 = r9.sizeCtl
            int r13 = r12 + -1
            r2 = r29
            r5 = r12
            r14 = r6
            r6 = r13
            boolean r1 = r1.compareAndSwapInt(r2, r3, r5, r6)
            if (r1 == 0) goto L_0x01da
            int r12 = r12 + -2
            int r1 = resizeStamp(r8)
            int r2 = RESIZE_STAMP_SHIFT
            int r1 = r1 << r2
            if (r12 == r1) goto L_0x01d5
            return
        L_0x01d5:
            r6 = r8
            r15 = 1
            r16 = 1
            goto L_0x01db
        L_0x01da:
            r6 = r14
        L_0x01db:
            r13 = r7
            r7 = r9
            r5 = r17
            r10 = r19
            r12 = r20
            r9 = 1
            goto L_0x0036
        */
        throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.transfer(j$.util.concurrent.ConcurrentHashMap$Node[], j$.util.concurrent.ConcurrentHashMap$Node[]):void");
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$CounterCell */
    final class CounterCell {
        volatile long value;

        CounterCell(long j) {
            this.value = j;
        }
    }

    /* access modifiers changed from: package-private */
    public final long sumCount() {
        CounterCell[] counterCellArr = this.counterCells;
        long j = this.baseCount;
        if (counterCellArr != null) {
            for (CounterCell counterCell : counterCellArr) {
                if (counterCell != null) {
                    j += counterCell.value;
                }
            }
        }
        return j;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009c, code lost:
        if (r9.counterCells != r7) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x009e, code lost:
        r1 = new p003j$.util.concurrent.ConcurrentHashMap.CounterCell[(r8 << 1)];
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a3, code lost:
        if (r2 >= r8) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a5, code lost:
        r1[r2] = r7[r2];
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ac, code lost:
        r9.counterCells = r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0101 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x001b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void fullAddCount(long r25, boolean r27) {
        /*
            r24 = this;
            r9 = r24
            r10 = r25
            int r0 = p003j$.util.concurrent.ThreadLocalRandom.getProbe()
            r12 = 1
            if (r0 != 0) goto L_0x0015
            p003j$.util.concurrent.ThreadLocalRandom.localInit()
            int r0 = p003j$.util.concurrent.ThreadLocalRandom.getProbe()
            r1 = r0
            r0 = 1
            goto L_0x0018
        L_0x0015:
            r1 = r0
            r0 = r27
        L_0x0018:
            r13 = 0
            r14 = r1
        L_0x001a:
            r15 = 0
        L_0x001b:
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r7 = r9.counterCells
            if (r7 == 0) goto L_0x00bd
            int r8 = r7.length
            if (r8 <= 0) goto L_0x00bd
            int r1 = r8 + -1
            r1 = r1 & r14
            r1 = r7[r1]
            if (r1 != 0) goto L_0x0063
            int r1 = r9.cellsBusy
            if (r1 != 0) goto L_0x0061
            j$.util.concurrent.ConcurrentHashMap$CounterCell r7 = new j$.util.concurrent.ConcurrentHashMap$CounterCell
            r7.<init>(r10)
            int r1 = r9.cellsBusy
            if (r1 != 0) goto L_0x0061
            sun.misc.Unsafe r1 = f16484U
            long r3 = CELLSBUSY
            r5 = 0
            r6 = 1
            r2 = r24
            boolean r1 = r1.compareAndSwapInt(r2, r3, r5, r6)
            if (r1 == 0) goto L_0x0061
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = r9.counterCells     // Catch:{ all -> 0x005d }
            if (r1 == 0) goto L_0x0056
            int r2 = r1.length     // Catch:{ all -> 0x005d }
            if (r2 <= 0) goto L_0x0056
            int r2 = r2 + -1
            r2 = r2 & r14
            r3 = r1[r2]     // Catch:{ all -> 0x005d }
            if (r3 != 0) goto L_0x0056
            r1[r2] = r7     // Catch:{ all -> 0x005d }
            r1 = 1
            goto L_0x0057
        L_0x0056:
            r1 = 0
        L_0x0057:
            r9.cellsBusy = r13
            if (r1 == 0) goto L_0x001b
            goto L_0x0101
        L_0x005d:
            r0 = move-exception
            r9.cellsBusy = r13
            throw r0
        L_0x0061:
            r15 = 0
            goto L_0x00b6
        L_0x0063:
            if (r0 != 0) goto L_0x0067
            r0 = 1
            goto L_0x00b6
        L_0x0067:
            sun.misc.Unsafe r16 = f16484U
            long r18 = CELLVALUE
            long r2 = r1.value
            long r22 = r2 + r10
            r17 = r1
            r20 = r2
            boolean r1 = r16.compareAndSwapLong(r17, r18, r20, r22)
            if (r1 == 0) goto L_0x007b
            goto L_0x0101
        L_0x007b:
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = r9.counterCells
            if (r1 != r7) goto L_0x0061
            int r1 = NCPU
            if (r8 < r1) goto L_0x0084
            goto L_0x0061
        L_0x0084:
            if (r15 != 0) goto L_0x0088
            r15 = 1
            goto L_0x00b6
        L_0x0088:
            int r1 = r9.cellsBusy
            if (r1 != 0) goto L_0x00b6
            sun.misc.Unsafe r1 = f16484U
            long r3 = CELLSBUSY
            r5 = 0
            r6 = 1
            r2 = r24
            boolean r1 = r1.compareAndSwapInt(r2, r3, r5, r6)
            if (r1 == 0) goto L_0x00b6
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = r9.counterCells     // Catch:{ all -> 0x00b2 }
            if (r1 != r7) goto L_0x00ae
            int r1 = r8 << 1
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = new p003j$.util.concurrent.ConcurrentHashMap.CounterCell[r1]     // Catch:{ all -> 0x00b2 }
            r2 = 0
        L_0x00a3:
            if (r2 >= r8) goto L_0x00ac
            r3 = r7[r2]     // Catch:{ all -> 0x00b2 }
            r1[r2] = r3     // Catch:{ all -> 0x00b2 }
            int r2 = r2 + 1
            goto L_0x00a3
        L_0x00ac:
            r9.counterCells = r1     // Catch:{ all -> 0x00b2 }
        L_0x00ae:
            r9.cellsBusy = r13
            goto L_0x001a
        L_0x00b2:
            r0 = move-exception
            r9.cellsBusy = r13
            throw r0
        L_0x00b6:
            int r1 = p003j$.util.concurrent.ThreadLocalRandom.advanceProbe(r14)
            r14 = r1
            goto L_0x001b
        L_0x00bd:
            int r1 = r9.cellsBusy
            if (r1 != 0) goto L_0x00f1
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = r9.counterCells
            if (r1 != r7) goto L_0x00f1
            sun.misc.Unsafe r1 = f16484U
            long r3 = CELLSBUSY
            r5 = 0
            r6 = 1
            r2 = r24
            boolean r1 = r1.compareAndSwapInt(r2, r3, r5, r6)
            if (r1 == 0) goto L_0x00f1
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = r9.counterCells     // Catch:{ all -> 0x00ed }
            if (r1 != r7) goto L_0x00e7
            r1 = 2
            j$.util.concurrent.ConcurrentHashMap$CounterCell[] r1 = new p003j$.util.concurrent.ConcurrentHashMap.CounterCell[r1]     // Catch:{ all -> 0x00ed }
            r2 = r14 & 1
            j$.util.concurrent.ConcurrentHashMap$CounterCell r3 = new j$.util.concurrent.ConcurrentHashMap$CounterCell     // Catch:{ all -> 0x00ed }
            r3.<init>(r10)     // Catch:{ all -> 0x00ed }
            r1[r2] = r3     // Catch:{ all -> 0x00ed }
            r9.counterCells = r1     // Catch:{ all -> 0x00ed }
            r1 = 1
            goto L_0x00e8
        L_0x00e7:
            r1 = 0
        L_0x00e8:
            r9.cellsBusy = r13
            if (r1 == 0) goto L_0x001b
            goto L_0x0101
        L_0x00ed:
            r0 = move-exception
            r9.cellsBusy = r13
            throw r0
        L_0x00f1:
            sun.misc.Unsafe r1 = f16484U
            long r3 = BASECOUNT
            long r5 = r9.baseCount
            long r7 = r5 + r10
            r2 = r24
            boolean r1 = r1.compareAndSwapLong(r2, r3, r5, r7)
            if (r1 == 0) goto L_0x001b
        L_0x0101:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.fullAddCount(long, boolean):void");
    }

    private final void treeifyBin(Node[] nodeArr, int i) {
        if (nodeArr != null) {
            int length = nodeArr.length;
            if (length < 64) {
                tryPresize(length << 1);
                return;
            }
            Node tabAt = tabAt(nodeArr, i);
            if (tabAt != null && tabAt.hash >= 0) {
                synchronized (tabAt) {
                    if (tabAt(nodeArr, i) == tabAt) {
                        TreeNode treeNode = null;
                        Node node = tabAt;
                        TreeNode treeNode2 = null;
                        while (node != null) {
                            TreeNode treeNode3 = new TreeNode(node.hash, node.key, node.val, (Node) null, (TreeNode) null);
                            treeNode3.prev = treeNode2;
                            if (treeNode2 == null) {
                                treeNode = treeNode3;
                            } else {
                                treeNode2.next = treeNode3;
                            }
                            node = node.next;
                            treeNode2 = treeNode3;
                        }
                        setTabAt(nodeArr, i, new TreeBin(treeNode));
                    }
                }
            }
        }
    }

    static Node untreeify(Node node) {
        Node node2 = null;
        Node node3 = null;
        while (node != null) {
            Node node4 = new Node(node.hash, node.key, node.val, (Node) null);
            if (node3 == null) {
                node2 = node4;
            } else {
                node3.next = node4;
            }
            node = node.next;
            node3 = node4;
        }
        return node2;
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$TreeNode */
    final class TreeNode extends Node {
        TreeNode left;
        TreeNode parent;
        TreeNode prev;
        boolean red;
        TreeNode right;

        TreeNode(int i, Object obj, Object obj2, Node node, TreeNode treeNode) {
            super(i, obj, obj2, node);
            this.parent = treeNode;
        }

        /* access modifiers changed from: package-private */
        public Node find(int i, Object obj) {
            return findTreeNode(i, obj, (Class) null);
        }

        /* access modifiers changed from: package-private */
        public final TreeNode findTreeNode(int i, Object obj, Class cls) {
            int compareComparables;
            if (obj == null) {
                return null;
            }
            TreeNode treeNode = this;
            do {
                TreeNode treeNode2 = treeNode.left;
                TreeNode treeNode3 = treeNode.right;
                int i2 = treeNode.hash;
                if (i2 <= i) {
                    if (i2 >= i) {
                        Object obj2 = treeNode.key;
                        if (obj2 == obj || (obj2 != null && obj.equals(obj2))) {
                            return treeNode;
                        }
                        if (treeNode2 != null) {
                            if (treeNode3 != null) {
                                if ((cls == null && (cls = ConcurrentHashMap.comparableClassFor(obj)) == null) || (compareComparables = ConcurrentHashMap.compareComparables(cls, obj, obj2)) == 0) {
                                    TreeNode findTreeNode = treeNode3.findTreeNode(i, obj, cls);
                                    if (findTreeNode != null) {
                                        return findTreeNode;
                                    }
                                } else if (compareComparables >= 0) {
                                    treeNode2 = treeNode3;
                                }
                            }
                        }
                    }
                    treeNode = treeNode3;
                    continue;
                }
                treeNode = treeNode2;
                continue;
            } while (treeNode != null);
            return null;
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$TreeBin */
    final class TreeBin extends Node {
        private static final long LOCKSTATE;

        /* renamed from: U */
        private static final Unsafe f16485U;
        volatile TreeNode first;
        volatile int lockState;
        TreeNode root;
        volatile Thread waiter;

        static {
            Class<ConcurrentHashMap> cls = ConcurrentHashMap.class;
            try {
                Unsafe unsafe = DesugarUnsafe.getUnsafe();
                f16485U = unsafe;
                LOCKSTATE = unsafe.objectFieldOffset(TreeBin.class.getDeclaredField("lockState"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        static int tieBreakOrder(Object obj, Object obj2) {
            int compareTo;
            if (obj == null || obj2 == null || (compareTo = obj.getClass().getName().compareTo(obj2.getClass().getName())) == 0) {
                return System.identityHashCode(obj) <= System.identityHashCode(obj2) ? -1 : 1;
            }
            return compareTo;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
            r6 = p003j$.util.concurrent.ConcurrentHashMap.comparableClassFor(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
            r8 = p003j$.util.concurrent.ConcurrentHashMap.compareComparables(r6, r3, r7);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        TreeBin(p003j$.util.concurrent.ConcurrentHashMap.TreeNode r10) {
            /*
                r9 = this;
                r0 = -2
                r1 = 0
                r9.<init>(r0, r1, r1, r1)
                r9.first = r10
                r0 = r1
            L_0x0008:
                if (r10 == 0) goto L_0x005c
                j$.util.concurrent.ConcurrentHashMap$Node r2 = r10.next
                j$.util.concurrent.ConcurrentHashMap$TreeNode r2 = (p003j$.util.concurrent.ConcurrentHashMap.TreeNode) r2
                r10.right = r1
                r10.left = r1
                if (r0 != 0) goto L_0x001b
                r10.parent = r1
                r0 = 0
                r10.red = r0
            L_0x0019:
                r0 = r10
                goto L_0x0058
            L_0x001b:
                java.lang.Object r3 = r10.key
                int r4 = r10.hash
                r5 = r0
                r6 = r1
            L_0x0021:
                java.lang.Object r7 = r5.key
                int r8 = r5.hash
                if (r8 <= r4) goto L_0x0029
                r7 = -1
                goto L_0x0041
            L_0x0029:
                if (r8 >= r4) goto L_0x002d
                r7 = 1
                goto L_0x0041
            L_0x002d:
                if (r6 != 0) goto L_0x0035
                java.lang.Class r6 = p003j$.util.concurrent.ConcurrentHashMap.comparableClassFor(r3)
                if (r6 == 0) goto L_0x003b
            L_0x0035:
                int r8 = p003j$.util.concurrent.ConcurrentHashMap.compareComparables(r6, r3, r7)
                if (r8 != 0) goto L_0x0040
            L_0x003b:
                int r7 = tieBreakOrder(r3, r7)
                goto L_0x0041
            L_0x0040:
                r7 = r8
            L_0x0041:
                if (r7 > 0) goto L_0x0046
                j$.util.concurrent.ConcurrentHashMap$TreeNode r8 = r5.left
                goto L_0x0048
            L_0x0046:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r8 = r5.right
            L_0x0048:
                if (r8 != 0) goto L_0x005a
                r10.parent = r5
                if (r7 > 0) goto L_0x0051
                r5.left = r10
                goto L_0x0053
            L_0x0051:
                r5.right = r10
            L_0x0053:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r10 = balanceInsertion(r0, r10)
                goto L_0x0019
            L_0x0058:
                r10 = r2
                goto L_0x0008
            L_0x005a:
                r5 = r8
                goto L_0x0021
            L_0x005c:
                r9.root = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.TreeBin.<init>(j$.util.concurrent.ConcurrentHashMap$TreeNode):void");
        }

        private final void lockRoot() {
            if (!f16485U.compareAndSwapInt(this, LOCKSTATE, 0, 1)) {
                contendedLock();
            }
        }

        private final void unlockRoot() {
            this.lockState = 0;
        }

        private final void contendedLock() {
            boolean z = false;
            while (true) {
                int i = this.lockState;
                if ((i & -3) == 0) {
                    if (f16485U.compareAndSwapInt(this, LOCKSTATE, i, 1)) {
                        break;
                    }
                } else if ((i & 2) == 0) {
                    if (f16485U.compareAndSwapInt(this, LOCKSTATE, i, i | 2)) {
                        z = true;
                        this.waiter = Thread.currentThread();
                    }
                } else if (z) {
                    LockSupport.park(this);
                }
            }
            if (z) {
                this.waiter = null;
            }
        }

        /* access modifiers changed from: package-private */
        public final Node find(int i, Object obj) {
            Thread thread;
            Object obj2;
            TreeNode treeNode = null;
            if (obj != null) {
                Node node = this.first;
                while (node != null) {
                    int i2 = this.lockState;
                    if ((i2 & 3) == 0) {
                        if (f16485U.compareAndSwapInt(this, LOCKSTATE, i2, i2 + 4)) {
                            try {
                                TreeNode treeNode2 = this.root;
                                if (treeNode2 != null) {
                                    treeNode = treeNode2.findTreeNode(i, obj, (Class) null);
                                }
                                return treeNode;
                            } finally {
                                if (DesugarUnsafe.getAndAddInt(f16485U, this, LOCKSTATE, -4) == 6 && (thread = this.waiter) != null) {
                                    LockSupport.unpark(thread);
                                }
                            }
                        }
                    } else if (node.hash == i && ((obj2 = node.key) == obj || (obj2 != null && obj.equals(obj2)))) {
                        return node;
                    } else {
                        node = node.next;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0056, code lost:
            return r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0097, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final p003j$.util.concurrent.ConcurrentHashMap.TreeNode putTreeVal(int r13, java.lang.Object r14, java.lang.Object r15) {
            /*
                r12 = this;
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = r12.root
                r1 = 0
                r2 = 0
                r3 = r1
            L_0x0005:
                if (r0 != 0) goto L_0x0018
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = new j$.util.concurrent.ConcurrentHashMap$TreeNode
                r8 = 0
                r9 = 0
                r4 = r0
                r5 = r13
                r6 = r14
                r7 = r15
                r4.<init>(r5, r6, r7, r8, r9)
                r12.root = r0
                r12.first = r0
                goto L_0x0097
            L_0x0018:
                int r4 = r0.hash
                r9 = 1
                if (r4 <= r13) goto L_0x0020
                r4 = -1
                r10 = -1
                goto L_0x005f
            L_0x0020:
                if (r4 >= r13) goto L_0x0024
                r10 = 1
                goto L_0x005f
            L_0x0024:
                java.lang.Object r4 = r0.key
                if (r4 == r14) goto L_0x00a0
                if (r4 == 0) goto L_0x0032
                boolean r5 = r14.equals(r4)
                if (r5 == 0) goto L_0x0032
                goto L_0x00a0
            L_0x0032:
                if (r3 != 0) goto L_0x003a
                java.lang.Class r3 = p003j$.util.concurrent.ConcurrentHashMap.comparableClassFor(r14)
                if (r3 == 0) goto L_0x0040
            L_0x003a:
                int r5 = p003j$.util.concurrent.ConcurrentHashMap.compareComparables(r3, r14, r4)
                if (r5 != 0) goto L_0x005e
            L_0x0040:
                if (r2 != 0) goto L_0x0058
                j$.util.concurrent.ConcurrentHashMap$TreeNode r2 = r0.left
                if (r2 == 0) goto L_0x004c
                j$.util.concurrent.ConcurrentHashMap$TreeNode r2 = r2.findTreeNode(r13, r14, r3)
                if (r2 != 0) goto L_0x0056
            L_0x004c:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r2 = r0.right
                if (r2 == 0) goto L_0x0057
                j$.util.concurrent.ConcurrentHashMap$TreeNode r2 = r2.findTreeNode(r13, r14, r3)
                if (r2 == 0) goto L_0x0057
            L_0x0056:
                return r2
            L_0x0057:
                r2 = 1
            L_0x0058:
                int r4 = tieBreakOrder(r14, r4)
                r10 = r4
                goto L_0x005f
            L_0x005e:
                r10 = r5
            L_0x005f:
                if (r10 > 0) goto L_0x0064
                j$.util.concurrent.ConcurrentHashMap$TreeNode r4 = r0.left
                goto L_0x0066
            L_0x0064:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r4 = r0.right
            L_0x0066:
                if (r4 != 0) goto L_0x009d
                j$.util.concurrent.ConcurrentHashMap$TreeNode r2 = r12.first
                j$.util.concurrent.ConcurrentHashMap$TreeNode r11 = new j$.util.concurrent.ConcurrentHashMap$TreeNode
                r3 = r11
                r4 = r13
                r5 = r14
                r6 = r15
                r7 = r2
                r8 = r0
                r3.<init>(r4, r5, r6, r7, r8)
                r12.first = r11
                if (r2 == 0) goto L_0x007b
                r2.prev = r11
            L_0x007b:
                if (r10 > 0) goto L_0x0080
                r0.left = r11
                goto L_0x0082
            L_0x0080:
                r0.right = r11
            L_0x0082:
                boolean r13 = r0.red
                if (r13 != 0) goto L_0x0089
                r11.red = r9
                goto L_0x0097
            L_0x0089:
                r12.lockRoot()
                j$.util.concurrent.ConcurrentHashMap$TreeNode r13 = r12.root     // Catch:{ all -> 0x0098 }
                j$.util.concurrent.ConcurrentHashMap$TreeNode r13 = balanceInsertion(r13, r11)     // Catch:{ all -> 0x0098 }
                r12.root = r13     // Catch:{ all -> 0x0098 }
                r12.unlockRoot()
            L_0x0097:
                return r1
            L_0x0098:
                r13 = move-exception
                r12.unlockRoot()
                throw r13
            L_0x009d:
                r0 = r4
                goto L_0x0005
            L_0x00a0:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.TreeBin.putTreeVal(int, java.lang.Object, java.lang.Object):j$.util.concurrent.ConcurrentHashMap$TreeNode");
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:60:0x0091 A[Catch:{ all -> 0x00cd }] */
        /* JADX WARNING: Removed duplicated region for block: B:70:0x00ac A[Catch:{ all -> 0x00cd }] */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x00ad A[Catch:{ all -> 0x00cd }] */
        /* JADX WARNING: Removed duplicated region for block: B:78:0x00bd A[Catch:{ all -> 0x00cd }] */
        /* JADX WARNING: Removed duplicated region for block: B:79:0x00c0 A[Catch:{ all -> 0x00cd }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean removeTreeNode(p003j$.util.concurrent.ConcurrentHashMap.TreeNode r10) {
            /*
                r9 = this;
                j$.util.concurrent.ConcurrentHashMap$Node r0 = r10.next
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = (p003j$.util.concurrent.ConcurrentHashMap.TreeNode) r0
                j$.util.concurrent.ConcurrentHashMap$TreeNode r1 = r10.prev
                if (r1 != 0) goto L_0x000b
                r9.first = r0
                goto L_0x000d
            L_0x000b:
                r1.next = r0
            L_0x000d:
                if (r0 == 0) goto L_0x0011
                r0.prev = r1
            L_0x0011:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = r9.first
                r1 = 1
                r2 = 0
                if (r0 != 0) goto L_0x001a
                r9.root = r2
                return r1
            L_0x001a:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = r9.root
                if (r0 == 0) goto L_0x00d2
                j$.util.concurrent.ConcurrentHashMap$TreeNode r3 = r0.right
                if (r3 == 0) goto L_0x00d2
                j$.util.concurrent.ConcurrentHashMap$TreeNode r3 = r0.left
                if (r3 == 0) goto L_0x00d2
                j$.util.concurrent.ConcurrentHashMap$TreeNode r3 = r3.left
                if (r3 != 0) goto L_0x002c
                goto L_0x00d2
            L_0x002c:
                r9.lockRoot()
                j$.util.concurrent.ConcurrentHashMap$TreeNode r1 = r10.left     // Catch:{ all -> 0x00cd }
                j$.util.concurrent.ConcurrentHashMap$TreeNode r3 = r10.right     // Catch:{ all -> 0x00cd }
                if (r1 == 0) goto L_0x0087
                if (r3 == 0) goto L_0x0087
                r4 = r3
            L_0x0038:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r5 = r4.left     // Catch:{ all -> 0x00cd }
                if (r5 == 0) goto L_0x003e
                r4 = r5
                goto L_0x0038
            L_0x003e:
                boolean r5 = r4.red     // Catch:{ all -> 0x00cd }
                boolean r6 = r10.red     // Catch:{ all -> 0x00cd }
                r4.red = r6     // Catch:{ all -> 0x00cd }
                r10.red = r5     // Catch:{ all -> 0x00cd }
                j$.util.concurrent.ConcurrentHashMap$TreeNode r5 = r4.right     // Catch:{ all -> 0x00cd }
                j$.util.concurrent.ConcurrentHashMap$TreeNode r6 = r10.parent     // Catch:{ all -> 0x00cd }
                if (r4 != r3) goto L_0x0051
                r10.parent = r4     // Catch:{ all -> 0x00cd }
                r4.right = r10     // Catch:{ all -> 0x00cd }
                goto L_0x0066
            L_0x0051:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r7 = r4.parent     // Catch:{ all -> 0x00cd }
                r10.parent = r7     // Catch:{ all -> 0x00cd }
                if (r7 == 0) goto L_0x0060
                j$.util.concurrent.ConcurrentHashMap$TreeNode r8 = r7.left     // Catch:{ all -> 0x00cd }
                if (r4 != r8) goto L_0x005e
                r7.left = r10     // Catch:{ all -> 0x00cd }
                goto L_0x0060
            L_0x005e:
                r7.right = r10     // Catch:{ all -> 0x00cd }
            L_0x0060:
                r4.right = r3     // Catch:{ all -> 0x00cd }
                if (r3 == 0) goto L_0x0066
                r3.parent = r4     // Catch:{ all -> 0x00cd }
            L_0x0066:
                r10.left = r2     // Catch:{ all -> 0x00cd }
                r10.right = r5     // Catch:{ all -> 0x00cd }
                if (r5 == 0) goto L_0x006e
                r5.parent = r10     // Catch:{ all -> 0x00cd }
            L_0x006e:
                r4.left = r1     // Catch:{ all -> 0x00cd }
                if (r1 == 0) goto L_0x0074
                r1.parent = r4     // Catch:{ all -> 0x00cd }
            L_0x0074:
                r4.parent = r6     // Catch:{ all -> 0x00cd }
                if (r6 != 0) goto L_0x007a
                r0 = r4
                goto L_0x0083
            L_0x007a:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r1 = r6.left     // Catch:{ all -> 0x00cd }
                if (r10 != r1) goto L_0x0081
                r6.left = r4     // Catch:{ all -> 0x00cd }
                goto L_0x0083
            L_0x0081:
                r6.right = r4     // Catch:{ all -> 0x00cd }
            L_0x0083:
                if (r5 == 0) goto L_0x008e
                r1 = r5
                goto L_0x008f
            L_0x0087:
                if (r1 == 0) goto L_0x008a
                goto L_0x008f
            L_0x008a:
                if (r3 == 0) goto L_0x008e
                r1 = r3
                goto L_0x008f
            L_0x008e:
                r1 = r10
            L_0x008f:
                if (r1 == r10) goto L_0x00a8
                j$.util.concurrent.ConcurrentHashMap$TreeNode r3 = r10.parent     // Catch:{ all -> 0x00cd }
                r1.parent = r3     // Catch:{ all -> 0x00cd }
                if (r3 != 0) goto L_0x0099
                r0 = r1
                goto L_0x00a2
            L_0x0099:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r4 = r3.left     // Catch:{ all -> 0x00cd }
                if (r10 != r4) goto L_0x00a0
                r3.left = r1     // Catch:{ all -> 0x00cd }
                goto L_0x00a2
            L_0x00a0:
                r3.right = r1     // Catch:{ all -> 0x00cd }
            L_0x00a2:
                r10.parent = r2     // Catch:{ all -> 0x00cd }
                r10.right = r2     // Catch:{ all -> 0x00cd }
                r10.left = r2     // Catch:{ all -> 0x00cd }
            L_0x00a8:
                boolean r3 = r10.red     // Catch:{ all -> 0x00cd }
                if (r3 == 0) goto L_0x00ad
                goto L_0x00b1
            L_0x00ad:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = balanceDeletion(r0, r1)     // Catch:{ all -> 0x00cd }
            L_0x00b1:
                r9.root = r0     // Catch:{ all -> 0x00cd }
                if (r10 != r1) goto L_0x00c8
                j$.util.concurrent.ConcurrentHashMap$TreeNode r0 = r10.parent     // Catch:{ all -> 0x00cd }
                if (r0 == 0) goto L_0x00c8
                j$.util.concurrent.ConcurrentHashMap$TreeNode r1 = r0.left     // Catch:{ all -> 0x00cd }
                if (r10 != r1) goto L_0x00c0
                r0.left = r2     // Catch:{ all -> 0x00cd }
                goto L_0x00c6
            L_0x00c0:
                j$.util.concurrent.ConcurrentHashMap$TreeNode r1 = r0.right     // Catch:{ all -> 0x00cd }
                if (r10 != r1) goto L_0x00c6
                r0.right = r2     // Catch:{ all -> 0x00cd }
            L_0x00c6:
                r10.parent = r2     // Catch:{ all -> 0x00cd }
            L_0x00c8:
                r9.unlockRoot()
                r10 = 0
                return r10
            L_0x00cd:
                r10 = move-exception
                r9.unlockRoot()
                throw r10
            L_0x00d2:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.TreeBin.removeTreeNode(j$.util.concurrent.ConcurrentHashMap$TreeNode):boolean");
        }

        static TreeNode rotateLeft(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            if (!(treeNode2 == null || (treeNode3 = treeNode2.right) == null)) {
                TreeNode treeNode4 = treeNode3.left;
                treeNode2.right = treeNode4;
                if (treeNode4 != null) {
                    treeNode4.parent = treeNode2;
                }
                TreeNode treeNode5 = treeNode2.parent;
                treeNode3.parent = treeNode5;
                if (treeNode5 == null) {
                    treeNode3.red = false;
                    treeNode = treeNode3;
                } else if (treeNode5.left == treeNode2) {
                    treeNode5.left = treeNode3;
                } else {
                    treeNode5.right = treeNode3;
                }
                treeNode3.left = treeNode2;
                treeNode2.parent = treeNode3;
            }
            return treeNode;
        }

        static TreeNode rotateRight(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            if (!(treeNode2 == null || (treeNode3 = treeNode2.left) == null)) {
                TreeNode treeNode4 = treeNode3.right;
                treeNode2.left = treeNode4;
                if (treeNode4 != null) {
                    treeNode4.parent = treeNode2;
                }
                TreeNode treeNode5 = treeNode2.parent;
                treeNode3.parent = treeNode5;
                if (treeNode5 == null) {
                    treeNode3.red = false;
                    treeNode = treeNode3;
                } else if (treeNode5.right == treeNode2) {
                    treeNode5.right = treeNode3;
                } else {
                    treeNode5.left = treeNode3;
                }
                treeNode3.right = treeNode2;
                treeNode2.parent = treeNode3;
            }
            return treeNode;
        }

        static TreeNode balanceInsertion(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            treeNode2.red = true;
            while (true) {
                TreeNode treeNode4 = treeNode2.parent;
                if (treeNode4 == null) {
                    treeNode2.red = false;
                    return treeNode2;
                } else if (!treeNode4.red || (treeNode3 = treeNode4.parent) == null) {
                    return treeNode;
                } else {
                    TreeNode treeNode5 = treeNode3.left;
                    if (treeNode4 == treeNode5) {
                        TreeNode treeNode6 = treeNode3.right;
                        if (treeNode6 == null || !treeNode6.red) {
                            if (treeNode2 == treeNode4.right) {
                                treeNode = rotateLeft(treeNode, treeNode4);
                                TreeNode treeNode7 = treeNode4.parent;
                                treeNode3 = treeNode7 == null ? null : treeNode7.parent;
                                TreeNode treeNode8 = treeNode4;
                                treeNode4 = treeNode7;
                                treeNode2 = treeNode8;
                            }
                            if (treeNode4 != null) {
                                treeNode4.red = false;
                                if (treeNode3 != null) {
                                    treeNode3.red = true;
                                    treeNode = rotateRight(treeNode, treeNode3);
                                }
                            }
                        } else {
                            treeNode6.red = false;
                            treeNode4.red = false;
                            treeNode3.red = true;
                        }
                    } else if (treeNode5 == null || !treeNode5.red) {
                        if (treeNode2 == treeNode4.left) {
                            treeNode = rotateRight(treeNode, treeNode4);
                            TreeNode treeNode9 = treeNode4.parent;
                            treeNode3 = treeNode9 == null ? null : treeNode9.parent;
                            TreeNode treeNode10 = treeNode4;
                            treeNode4 = treeNode9;
                            treeNode2 = treeNode10;
                        }
                        if (treeNode4 != null) {
                            treeNode4.red = false;
                            if (treeNode3 != null) {
                                treeNode3.red = true;
                                treeNode = rotateLeft(treeNode, treeNode3);
                            }
                        }
                    } else {
                        treeNode5.red = false;
                        treeNode4.red = false;
                        treeNode3.red = true;
                    }
                    treeNode2 = treeNode3;
                }
            }
            return treeNode;
        }

        static TreeNode balanceDeletion(TreeNode treeNode, TreeNode treeNode2) {
            boolean z;
            boolean z2;
            while (treeNode2 != null && treeNode2 != treeNode) {
                TreeNode treeNode3 = treeNode2.parent;
                if (treeNode3 == null) {
                    treeNode2.red = false;
                    return treeNode2;
                } else if (treeNode2.red) {
                    treeNode2.red = false;
                    return treeNode;
                } else {
                    TreeNode treeNode4 = treeNode3.left;
                    TreeNode treeNode5 = null;
                    if (treeNode4 == treeNode2) {
                        TreeNode treeNode6 = treeNode3.right;
                        if (treeNode6 != null && treeNode6.red) {
                            treeNode6.red = false;
                            treeNode3.red = true;
                            treeNode = rotateLeft(treeNode, treeNode3);
                            treeNode3 = treeNode2.parent;
                            treeNode6 = treeNode3 == null ? null : treeNode3.right;
                        }
                        if (treeNode6 != null) {
                            TreeNode treeNode7 = treeNode6.left;
                            TreeNode treeNode8 = treeNode6.right;
                            if ((treeNode8 == null || !treeNode8.red) && (treeNode7 == null || !treeNode7.red)) {
                                treeNode6.red = true;
                            } else {
                                if (treeNode8 == null || !treeNode8.red) {
                                    if (treeNode7 != null) {
                                        treeNode7.red = false;
                                    }
                                    treeNode6.red = true;
                                    treeNode = rotateRight(treeNode, treeNode6);
                                    treeNode3 = treeNode2.parent;
                                    if (treeNode3 != null) {
                                        treeNode5 = treeNode3.right;
                                    }
                                    treeNode6 = treeNode5;
                                }
                                if (treeNode6 != null) {
                                    if (treeNode3 == null) {
                                        z2 = false;
                                    } else {
                                        z2 = treeNode3.red;
                                    }
                                    treeNode6.red = z2;
                                    TreeNode treeNode9 = treeNode6.right;
                                    if (treeNode9 != null) {
                                        treeNode9.red = false;
                                    }
                                }
                                if (treeNode3 != null) {
                                    treeNode3.red = false;
                                    treeNode = rotateLeft(treeNode, treeNode3);
                                }
                            }
                        }
                        treeNode2 = treeNode3;
                    } else {
                        if (treeNode4 != null && treeNode4.red) {
                            treeNode4.red = false;
                            treeNode3.red = true;
                            treeNode = rotateRight(treeNode, treeNode3);
                            treeNode3 = treeNode2.parent;
                            treeNode4 = treeNode3 == null ? null : treeNode3.left;
                        }
                        if (treeNode4 != null) {
                            TreeNode treeNode10 = treeNode4.left;
                            TreeNode treeNode11 = treeNode4.right;
                            if ((treeNode10 == null || !treeNode10.red) && (treeNode11 == null || !treeNode11.red)) {
                                treeNode4.red = true;
                            } else {
                                if (treeNode10 == null || !treeNode10.red) {
                                    if (treeNode11 != null) {
                                        treeNode11.red = false;
                                    }
                                    treeNode4.red = true;
                                    treeNode = rotateLeft(treeNode, treeNode4);
                                    treeNode3 = treeNode2.parent;
                                    if (treeNode3 != null) {
                                        treeNode5 = treeNode3.left;
                                    }
                                    treeNode4 = treeNode5;
                                }
                                if (treeNode4 != null) {
                                    if (treeNode3 == null) {
                                        z = false;
                                    } else {
                                        z = treeNode3.red;
                                    }
                                    treeNode4.red = z;
                                    TreeNode treeNode12 = treeNode4.left;
                                    if (treeNode12 != null) {
                                        treeNode12.red = false;
                                    }
                                }
                                if (treeNode3 != null) {
                                    treeNode3.red = false;
                                    treeNode = rotateRight(treeNode, treeNode3);
                                }
                            }
                        }
                        treeNode2 = treeNode3;
                    }
                    treeNode2 = treeNode;
                }
            }
            return treeNode;
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$TableStack */
    final class TableStack {
        int index;
        int length;
        TableStack next;
        Node[] tab;

        TableStack() {
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$Traverser */
    class Traverser {
        int baseIndex;
        int baseLimit;
        final int baseSize;
        int index;
        Node next = null;
        TableStack spare;
        TableStack stack;
        Node[] tab;

        Traverser(Node[] nodeArr, int i, int i2, int i3) {
            this.tab = nodeArr;
            this.baseSize = i;
            this.index = i2;
            this.baseIndex = i2;
            this.baseLimit = i3;
        }

        /* access modifiers changed from: package-private */
        public final Node advance() {
            Node node;
            Node[] nodeArr;
            int length;
            int i;
            Node node2 = this.next;
            if (node2 != null) {
                node2 = node2.next;
            }
            while (node == null) {
                if (this.baseIndex >= this.baseLimit || (nodeArr = this.tab) == null || (length = nodeArr.length) <= (i = this.index) || i < 0) {
                    this.next = null;
                    return null;
                }
                Node tabAt = ConcurrentHashMap.tabAt(nodeArr, i);
                if (tabAt == null || tabAt.hash >= 0) {
                    node = tabAt;
                } else if (tabAt instanceof ForwardingNode) {
                    this.tab = ((ForwardingNode) tabAt).nextTable;
                    pushState(nodeArr, i, length);
                    node = null;
                } else {
                    node = tabAt instanceof TreeBin ? ((TreeBin) tabAt).first : null;
                }
                if (this.stack != null) {
                    recoverState(length);
                } else {
                    int i2 = i + this.baseSize;
                    this.index = i2;
                    if (i2 >= length) {
                        int i3 = this.baseIndex + 1;
                        this.baseIndex = i3;
                        this.index = i3;
                    }
                }
            }
            this.next = node;
            return node;
        }

        private void pushState(Node[] nodeArr, int i, int i2) {
            TableStack tableStack = this.spare;
            if (tableStack != null) {
                this.spare = tableStack.next;
            } else {
                tableStack = new TableStack();
            }
            tableStack.tab = nodeArr;
            tableStack.length = i2;
            tableStack.index = i;
            tableStack.next = this.stack;
            this.stack = tableStack;
        }

        private void recoverState(int i) {
            TableStack tableStack;
            while (true) {
                tableStack = this.stack;
                if (tableStack == null) {
                    break;
                }
                int i2 = this.index;
                int i3 = tableStack.length;
                int i4 = i2 + i3;
                this.index = i4;
                if (i4 < i) {
                    break;
                }
                this.index = tableStack.index;
                this.tab = tableStack.tab;
                tableStack.tab = null;
                TableStack tableStack2 = tableStack.next;
                tableStack.next = this.spare;
                this.stack = tableStack2;
                this.spare = tableStack;
                i = i3;
            }
            if (tableStack == null) {
                int i5 = this.index + this.baseSize;
                this.index = i5;
                if (i5 >= i) {
                    int i6 = this.baseIndex + 1;
                    this.baseIndex = i6;
                    this.index = i6;
                }
            }
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$BaseIterator */
    abstract class BaseIterator extends Traverser {
        Node lastReturned;
        final ConcurrentHashMap map;

        BaseIterator(Node[] nodeArr, int i, int i2, int i3, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3);
            this.map = concurrentHashMap;
            advance();
        }

        public final boolean hasNext() {
            return this.next != null;
        }

        public final boolean hasMoreElements() {
            return this.next != null;
        }

        public final void remove() {
            Node node = this.lastReturned;
            if (node != null) {
                this.lastReturned = null;
                this.map.replaceNode(node.key, (Object) null, (Object) null);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$KeyIterator */
    final class KeyIterator extends BaseIterator implements Iterator, Enumeration, p003j$.util.Iterator {
        public void forEachRemaining(Consumer consumer) {
            Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
        }

        KeyIterator(Node[] nodeArr, int i, int i2, int i3, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3, concurrentHashMap);
        }

        public final Object next() {
            Node node = this.next;
            if (node != null) {
                Object obj = node.key;
                this.lastReturned = node;
                advance();
                return obj;
            }
            throw new NoSuchElementException();
        }

        public final Object nextElement() {
            return next();
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$ValueIterator */
    final class ValueIterator extends BaseIterator implements Iterator, Enumeration, p003j$.util.Iterator {
        public void forEachRemaining(Consumer consumer) {
            Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
        }

        ValueIterator(Node[] nodeArr, int i, int i2, int i3, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3, concurrentHashMap);
        }

        public final Object next() {
            Node node = this.next;
            if (node != null) {
                Object obj = node.val;
                this.lastReturned = node;
                advance();
                return obj;
            }
            throw new NoSuchElementException();
        }

        public final Object nextElement() {
            return next();
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$EntryIterator */
    final class EntryIterator extends BaseIterator implements Iterator, p003j$.util.Iterator {
        public void forEachRemaining(Consumer consumer) {
            Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
        }

        EntryIterator(Node[] nodeArr, int i, int i2, int i3, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3, concurrentHashMap);
        }

        public final Map.Entry next() {
            Node node = this.next;
            if (node != null) {
                Object obj = node.key;
                Object obj2 = node.val;
                this.lastReturned = node;
                advance();
                return new MapEntry(obj, obj2, this.map);
            }
            throw new NoSuchElementException();
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$MapEntry */
    final class MapEntry implements Map.Entry, Map.Entry {
        final Object key;
        final ConcurrentHashMap map;
        Object val;

        MapEntry(Object obj, Object obj2, ConcurrentHashMap concurrentHashMap) {
            this.key = obj;
            this.val = obj2;
            this.map = concurrentHashMap;
        }

        public Object getKey() {
            return this.key;
        }

        public Object getValue() {
            return this.val;
        }

        public int hashCode() {
            return this.key.hashCode() ^ this.val.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(this.key);
            String valueOf2 = String.valueOf(this.val);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length());
            sb.append(valueOf);
            sb.append("=");
            sb.append(valueOf2);
            return sb.toString();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
            r0 = r2.val;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r3 = (java.util.Map.Entry) r3;
            r0 = r3.getKey();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
            r3 = r3.getValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
            r1 = r2.key;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r3) {
            /*
                r2 = this;
                boolean r0 = r3 instanceof java.util.Map.Entry
                if (r0 == 0) goto L_0x0028
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r0 = r3.getKey()
                if (r0 == 0) goto L_0x0028
                java.lang.Object r3 = r3.getValue()
                if (r3 == 0) goto L_0x0028
                java.lang.Object r1 = r2.key
                if (r0 == r1) goto L_0x001c
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0028
            L_0x001c:
                java.lang.Object r0 = r2.val
                if (r3 == r0) goto L_0x0026
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0028
            L_0x0026:
                r3 = 1
                goto L_0x0029
            L_0x0028:
                r3 = 0
            L_0x0029:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.MapEntry.equals(java.lang.Object):boolean");
        }

        public Object setValue(Object obj) {
            if (obj != null) {
                Object obj2 = this.val;
                this.val = obj;
                this.map.put(this.key, obj);
                return obj2;
            }
            throw null;
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$KeySpliterator */
    final class KeySpliterator extends Traverser implements Spliterator {
        long est;

        public int characteristics() {
            return 4353;
        }

        public Comparator getComparator() {
            Spliterator$$CC.getComparator$$dflt$$(this);
            throw null;
        }

        public long getExactSizeIfKnown() {
            return Spliterator$$CC.getExactSizeIfKnown$$dflt$$(this);
        }

        KeySpliterator(Node[] nodeArr, int i, int i2, int i3, long j) {
            super(nodeArr, i, i2, i3);
            this.est = j;
        }

        public Spliterator trySplit() {
            int i = this.baseIndex;
            int i2 = this.baseLimit;
            int i3 = (i + i2) >>> 1;
            if (i3 <= i) {
                return null;
            }
            Node[] nodeArr = this.tab;
            int i4 = this.baseSize;
            this.baseLimit = i3;
            long j = this.est >>> 1;
            this.est = j;
            return new KeySpliterator(nodeArr, i4, i3, i2, j);
        }

        public void forEachRemaining(Consumer consumer) {
            if (consumer != null) {
                while (true) {
                    Node advance = advance();
                    if (advance != null) {
                        consumer.accept(advance.key);
                    } else {
                        return;
                    }
                }
            } else {
                throw null;
            }
        }

        public boolean tryAdvance(Consumer consumer) {
            if (consumer != null) {
                Node advance = advance();
                if (advance == null) {
                    return false;
                }
                consumer.accept(advance.key);
                return true;
            }
            throw null;
        }

        public long estimateSize() {
            return this.est;
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$ValueSpliterator */
    final class ValueSpliterator extends Traverser implements Spliterator {
        long est;

        public int characteristics() {
            return 4352;
        }

        public Comparator getComparator() {
            Spliterator$$CC.getComparator$$dflt$$(this);
            throw null;
        }

        public long getExactSizeIfKnown() {
            return Spliterator$$CC.getExactSizeIfKnown$$dflt$$(this);
        }

        ValueSpliterator(Node[] nodeArr, int i, int i2, int i3, long j) {
            super(nodeArr, i, i2, i3);
            this.est = j;
        }

        public Spliterator trySplit() {
            int i = this.baseIndex;
            int i2 = this.baseLimit;
            int i3 = (i + i2) >>> 1;
            if (i3 <= i) {
                return null;
            }
            Node[] nodeArr = this.tab;
            int i4 = this.baseSize;
            this.baseLimit = i3;
            long j = this.est >>> 1;
            this.est = j;
            return new ValueSpliterator(nodeArr, i4, i3, i2, j);
        }

        public void forEachRemaining(Consumer consumer) {
            if (consumer != null) {
                while (true) {
                    Node advance = advance();
                    if (advance != null) {
                        consumer.accept(advance.val);
                    } else {
                        return;
                    }
                }
            } else {
                throw null;
            }
        }

        public boolean tryAdvance(Consumer consumer) {
            if (consumer != null) {
                Node advance = advance();
                if (advance == null) {
                    return false;
                }
                consumer.accept(advance.val);
                return true;
            }
            throw null;
        }

        public long estimateSize() {
            return this.est;
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$EntrySpliterator */
    final class EntrySpliterator extends Traverser implements Spliterator {
        long est;
        final ConcurrentHashMap map;

        public int characteristics() {
            return 4353;
        }

        public Comparator getComparator() {
            Spliterator$$CC.getComparator$$dflt$$(this);
            throw null;
        }

        public long getExactSizeIfKnown() {
            return Spliterator$$CC.getExactSizeIfKnown$$dflt$$(this);
        }

        EntrySpliterator(Node[] nodeArr, int i, int i2, int i3, long j, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3);
            this.map = concurrentHashMap;
            this.est = j;
        }

        public Spliterator trySplit() {
            int i = this.baseIndex;
            int i2 = this.baseLimit;
            int i3 = (i + i2) >>> 1;
            if (i3 <= i) {
                return null;
            }
            Node[] nodeArr = this.tab;
            int i4 = this.baseSize;
            this.baseLimit = i3;
            long j = this.est >>> 1;
            this.est = j;
            return new EntrySpliterator(nodeArr, i4, i3, i2, j, this.map);
        }

        public void forEachRemaining(Consumer consumer) {
            if (consumer != null) {
                while (true) {
                    Node advance = advance();
                    if (advance != null) {
                        consumer.accept(new MapEntry(advance.key, advance.val, this.map));
                    } else {
                        return;
                    }
                }
            } else {
                throw null;
            }
        }

        public boolean tryAdvance(Consumer consumer) {
            if (consumer != null) {
                Node advance = advance();
                if (advance == null) {
                    return false;
                }
                consumer.accept(new MapEntry(advance.key, advance.val, this.map));
                return true;
            }
            throw null;
        }

        public long estimateSize() {
            return this.est;
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$CollectionView */
    abstract class CollectionView implements Collection, Serializable, p003j$.util.Collection {
        private static final long serialVersionUID = 7249069246763182397L;
        final ConcurrentHashMap map;

        public abstract boolean contains(Object obj);

        public abstract Iterator iterator();

        CollectionView(ConcurrentHashMap concurrentHashMap) {
            this.map = concurrentHashMap;
        }

        public final void clear() {
            this.map.clear();
        }

        public final int size() {
            return this.map.size();
        }

        public final boolean isEmpty() {
            return this.map.isEmpty();
        }

        public final Object[] toArray() {
            long mappingCount = this.map.mappingCount();
            if (mappingCount <= 2147483639) {
                int i = (int) mappingCount;
                Object[] objArr = new Object[i];
                int i2 = 0;
                Iterator it = iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (i2 == i) {
                        int i3 = 2147483639;
                        if (i < 2147483639) {
                            if (i < 1073741819) {
                                i3 = (i >>> 1) + 1 + i;
                            }
                            objArr = Arrays.copyOf(objArr, i3);
                            i = i3;
                        } else {
                            throw new OutOfMemoryError("Required array size too large");
                        }
                    }
                    objArr[i2] = next;
                    i2++;
                }
                if (i2 == i) {
                    return objArr;
                }
                return Arrays.copyOf(objArr, i2);
            }
            throw new OutOfMemoryError("Required array size too large");
        }

        public final Object[] toArray(Object[] objArr) {
            Object[] objArr2;
            long mappingCount = this.map.mappingCount();
            if (mappingCount <= 2147483639) {
                int i = (int) mappingCount;
                if (objArr.length >= i) {
                    objArr2 = objArr;
                } else {
                    objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
                }
                int length = objArr2.length;
                int i2 = 0;
                Iterator it = iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (i2 == length) {
                        int i3 = 2147483639;
                        if (length < 2147483639) {
                            if (length < 1073741819) {
                                i3 = (length >>> 1) + 1 + length;
                            }
                            objArr2 = Arrays.copyOf(objArr2, i3);
                            length = i3;
                        } else {
                            throw new OutOfMemoryError("Required array size too large");
                        }
                    }
                    objArr2[i2] = next;
                    i2++;
                }
                if (objArr == objArr2 && i2 < length) {
                    objArr2[i2] = null;
                    return objArr2;
                } else if (i2 == length) {
                    return objArr2;
                } else {
                    return Arrays.copyOf(objArr2, i2);
                }
            } else {
                throw new OutOfMemoryError("Required array size too large");
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            Iterator it = iterator();
            if (it.hasNext()) {
                while (true) {
                    Object next = it.next();
                    if (next == this) {
                        next = "(this Collection)";
                    }
                    sb.append(next);
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append(',');
                    sb.append(' ');
                }
            }
            sb.append(']');
            return sb.toString();
        }

        /* JADX WARNING: Removed duplicated region for block: B:4:0x000c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean containsAll(java.util.Collection r2) {
            /*
                r1 = this;
                if (r2 == r1) goto L_0x001a
                java.util.Iterator r2 = r2.iterator()
            L_0x0006:
                boolean r0 = r2.hasNext()
                if (r0 == 0) goto L_0x001a
                java.lang.Object r0 = r2.next()
                if (r0 == 0) goto L_0x0018
                boolean r0 = r1.contains(r0)
                if (r0 != 0) goto L_0x0006
            L_0x0018:
                r2 = 0
                return r2
            L_0x001a:
                r2 = 1
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.CollectionView.containsAll(java.util.Collection):boolean");
        }

        public final boolean removeAll(Collection collection) {
            if (collection != null) {
                boolean z = false;
                Iterator it = iterator();
                while (it.hasNext()) {
                    if (collection.contains(it.next())) {
                        it.remove();
                        z = true;
                    }
                }
                return z;
            }
            throw null;
        }

        public final boolean retainAll(Collection collection) {
            if (collection != null) {
                boolean z = false;
                Iterator it = iterator();
                while (it.hasNext()) {
                    if (!collection.contains(it.next())) {
                        it.remove();
                        z = true;
                    }
                }
                return z;
            }
            throw null;
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$KeySetView */
    public class KeySetView extends CollectionView implements Set, Serializable, p003j$.util.Set {
        private static final long serialVersionUID = 7249069246763182397L;
        private final Object value;

        KeySetView(ConcurrentHashMap concurrentHashMap, Object obj) {
            super(concurrentHashMap);
            this.value = obj;
        }

        public boolean contains(Object obj) {
            return this.map.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return this.map.remove(obj) != null;
        }

        public Iterator iterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new KeyIterator(nodeArr, length, 0, length, concurrentHashMap);
        }

        public boolean add(Object obj) {
            Object obj2 = this.value;
            if (obj2 != null) {
                return this.map.putVal(obj, obj2, true) == null;
            }
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection collection) {
            Object obj = this.value;
            if (obj != null) {
                boolean z = false;
                for (Object putVal : collection) {
                    if (this.map.putVal(putVal, obj, true) == null) {
                        z = true;
                    }
                }
                return z;
            }
            throw new UnsupportedOperationException();
        }

        public int hashCode() {
            Iterator it = iterator();
            int i = 0;
            while (it.hasNext()) {
                i += it.next().hashCode();
            }
            return i;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r2 = (java.util.Set) r2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r2) {
            /*
                r1 = this;
                boolean r0 = r2 instanceof java.util.Set
                if (r0 == 0) goto L_0x0016
                java.util.Set r2 = (java.util.Set) r2
                if (r2 == r1) goto L_0x0014
                boolean r0 = r1.containsAll(r2)
                if (r0 == 0) goto L_0x0016
                boolean r2 = r2.containsAll(r1)
                if (r2 == 0) goto L_0x0016
            L_0x0014:
                r2 = 1
                goto L_0x0017
            L_0x0016:
                r2 = 0
            L_0x0017:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.KeySetView.equals(java.lang.Object):boolean");
        }

        public Spliterator spliterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            long sumCount = concurrentHashMap.sumCount();
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            long j = 0;
            if (sumCount >= 0) {
                j = sumCount;
            }
            return new KeySpliterator(nodeArr, length, 0, length, j);
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$ValuesView */
    final class ValuesView extends CollectionView implements Collection, Serializable, p003j$.util.Collection {
        private static final long serialVersionUID = 2249069246763182397L;

        ValuesView(ConcurrentHashMap concurrentHashMap) {
            super(concurrentHashMap);
        }

        public final boolean contains(Object obj) {
            return this.map.containsValue(obj);
        }

        public final boolean remove(Object obj) {
            if (obj == null) {
                return false;
            }
            Iterator it = iterator();
            while (it.hasNext()) {
                if (obj.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        public final Iterator iterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new ValueIterator(nodeArr, length, 0, length, concurrentHashMap);
        }

        public final boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        public final boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        public Spliterator spliterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            long sumCount = concurrentHashMap.sumCount();
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            long j = 0;
            if (sumCount >= 0) {
                j = sumCount;
            }
            return new ValueSpliterator(nodeArr, length, 0, length, j);
        }
    }

    /* renamed from: j$.util.concurrent.ConcurrentHashMap$EntrySetView */
    final class EntrySetView extends CollectionView implements Set, Serializable, p003j$.util.Set {
        private static final long serialVersionUID = 2249069246763182397L;

        EntrySetView(ConcurrentHashMap concurrentHashMap) {
            super(concurrentHashMap);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
            r0 = r2.map.get((r0 = r3.getKey()));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
            r3 = (r3 = (java.util.Map.Entry) r3).getValue();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean contains(java.lang.Object r3) {
            /*
                r2 = this;
                boolean r0 = r3 instanceof java.util.Map.Entry
                if (r0 == 0) goto L_0x0024
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r0 = r3.getKey()
                if (r0 == 0) goto L_0x0024
                j$.util.concurrent.ConcurrentHashMap r1 = r2.map
                java.lang.Object r0 = r1.get(r0)
                if (r0 == 0) goto L_0x0024
                java.lang.Object r3 = r3.getValue()
                if (r3 == 0) goto L_0x0024
                if (r3 == r0) goto L_0x0022
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0024
            L_0x0022:
                r3 = 1
                goto L_0x0025
            L_0x0024:
                r3 = 0
            L_0x0025:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.EntrySetView.contains(java.lang.Object):boolean");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r3 = (java.util.Map.Entry) r3;
            r0 = r3.getKey();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
            r3 = r3.getValue();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean remove(java.lang.Object r3) {
            /*
                r2 = this;
                boolean r0 = r3 instanceof java.util.Map.Entry
                if (r0 == 0) goto L_0x001c
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r0 = r3.getKey()
                if (r0 == 0) goto L_0x001c
                java.lang.Object r3 = r3.getValue()
                if (r3 == 0) goto L_0x001c
                j$.util.concurrent.ConcurrentHashMap r1 = r2.map
                boolean r3 = r1.remove(r0, r3)
                if (r3 == 0) goto L_0x001c
                r3 = 1
                goto L_0x001d
            L_0x001c:
                r3 = 0
            L_0x001d:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.EntrySetView.remove(java.lang.Object):boolean");
        }

        public Iterator iterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new EntryIterator(nodeArr, length, 0, length, concurrentHashMap);
        }

        public boolean add(Map.Entry entry) {
            return this.map.putVal(entry.getKey(), entry.getValue(), false) == null;
        }

        public boolean addAll(Collection collection) {
            Iterator it = collection.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (add((Map.Entry) it.next())) {
                    z = true;
                }
            }
            return z;
        }

        public final int hashCode() {
            Node[] nodeArr = this.map.table;
            int i = 0;
            if (nodeArr != null) {
                Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
                while (true) {
                    Node advance = traverser.advance();
                    if (advance == null) {
                        break;
                    }
                    i += advance.hashCode();
                }
            }
            return i;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r2 = (java.util.Set) r2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r2) {
            /*
                r1 = this;
                boolean r0 = r2 instanceof java.util.Set
                if (r0 == 0) goto L_0x0016
                java.util.Set r2 = (java.util.Set) r2
                if (r2 == r1) goto L_0x0014
                boolean r0 = r1.containsAll(r2)
                if (r0 == 0) goto L_0x0016
                boolean r2 = r2.containsAll(r1)
                if (r2 == 0) goto L_0x0016
            L_0x0014:
                r2 = 1
                goto L_0x0017
            L_0x0016:
                r2 = 0
            L_0x0017:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: p003j$.util.concurrent.ConcurrentHashMap.EntrySetView.equals(java.lang.Object):boolean");
        }

        public Spliterator spliterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            long sumCount = concurrentHashMap.sumCount();
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            long j = 0;
            if (sumCount >= 0) {
                j = sumCount;
            }
            return new EntrySpliterator(nodeArr, length, 0, length, j, concurrentHashMap);
        }
    }
}
