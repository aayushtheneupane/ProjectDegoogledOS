package com.google.protobuf;

import com.google.protobuf.FieldSet.FieldDescriptorLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.LazyField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class FieldSet<FieldDescriptorType extends FieldDescriptorLite<FieldDescriptorType>> {
    private final SmallSortedMap<FieldDescriptorType, Object> fields = SmallSortedMap.newFieldMap(16);
    private boolean hasLazyField = false;
    private boolean isImmutable;

    public interface FieldDescriptorLite<T extends FieldDescriptorLite<T>> extends Comparable<T> {
    }

    static {
        new FieldSet(true);
    }

    private FieldSet() {
    }

    private Object cloneIfMutable(Object obj) {
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private void mergeFromField(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof LazyField) {
            value = ((LazyField) value).getValue();
        }
        GeneratedMessageLite.ExtensionDescriptor extensionDescriptor = (GeneratedMessageLite.ExtensionDescriptor) fieldDescriptorLite;
        if (extensionDescriptor.isRepeated) {
            Object obj = this.fields.get(fieldDescriptorLite);
            if (obj instanceof LazyField) {
                obj = ((LazyField) obj).getValue();
            }
            if (obj == null) {
                obj = new ArrayList();
            }
            for (Object cloneIfMutable : (List) value) {
                ((List) obj).add(cloneIfMutable(cloneIfMutable));
            }
            this.fields.put(fieldDescriptorLite, obj);
        } else if (extensionDescriptor.type.getJavaType() == WireFormat$JavaType.MESSAGE) {
            Object obj2 = this.fields.get(fieldDescriptorLite);
            if (obj2 instanceof LazyField) {
                obj2 = ((LazyField) obj2).getValue();
            }
            if (obj2 == null) {
                this.fields.put(fieldDescriptorLite, cloneIfMutable(value));
                return;
            }
            GeneratedMessageLite.Builder builder = ((GeneratedMessageLite) obj2).toBuilder();
            builder.mergeFrom((GeneratedMessageLite) value);
            this.fields.put(fieldDescriptorLite, builder.build());
        } else {
            this.fields.put(fieldDescriptorLite, cloneIfMutable(value));
        }
    }

    public static <T extends FieldDescriptorLite<T>> FieldSet<T> newFieldSet() {
        return new FieldSet<>();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        if ((r3 instanceof com.google.protobuf.Internal.EnumLite) == false) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0016, code lost:
        if ((r3 instanceof com.google.protobuf.LazyField) == false) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
        r1 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void verifyType(com.google.protobuf.WireFormat$FieldType r2, java.lang.Object r3) {
        /*
            if (r3 == 0) goto L_0x0048
            com.google.protobuf.WireFormat$JavaType r2 = r2.getJavaType()
            int r2 = r2.ordinal()
            r0 = 1
            r1 = 0
            switch(r2) {
                case 0: goto L_0x003b;
                case 1: goto L_0x0038;
                case 2: goto L_0x0035;
                case 3: goto L_0x0032;
                case 4: goto L_0x002f;
                case 5: goto L_0x002c;
                case 6: goto L_0x0023;
                case 7: goto L_0x001a;
                case 8: goto L_0x0010;
                default: goto L_0x000f;
            }
        L_0x000f:
            goto L_0x003d
        L_0x0010:
            boolean r2 = r3 instanceof com.google.protobuf.MessageLite
            if (r2 != 0) goto L_0x0018
            boolean r2 = r3 instanceof com.google.protobuf.LazyField
            if (r2 == 0) goto L_0x003d
        L_0x0018:
            r1 = r0
            goto L_0x003d
        L_0x001a:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0018
            boolean r2 = r3 instanceof com.google.protobuf.Internal.EnumLite
            if (r2 == 0) goto L_0x003d
            goto L_0x0018
        L_0x0023:
            boolean r2 = r3 instanceof com.google.protobuf.ByteString
            if (r2 != 0) goto L_0x0018
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L_0x003d
            goto L_0x0018
        L_0x002c:
            boolean r1 = r3 instanceof java.lang.String
            goto L_0x003d
        L_0x002f:
            boolean r1 = r3 instanceof java.lang.Boolean
            goto L_0x003d
        L_0x0032:
            boolean r1 = r3 instanceof java.lang.Double
            goto L_0x003d
        L_0x0035:
            boolean r1 = r3 instanceof java.lang.Float
            goto L_0x003d
        L_0x0038:
            boolean r1 = r3 instanceof java.lang.Long
            goto L_0x003d
        L_0x003b:
            boolean r1 = r3 instanceof java.lang.Integer
        L_0x003d:
            if (r1 == 0) goto L_0x0040
            return
        L_0x0040:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        L_0x0048:
            java.lang.NullPointerException r2 = new java.lang.NullPointerException
            r2.<init>()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.FieldSet.verifyType(com.google.protobuf.WireFormat$FieldType, java.lang.Object):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FieldSet)) {
            return false;
        }
        SmallSortedMap<FieldDescriptorType, Object> smallSortedMap = ((FieldSet) obj).fields;
        return smallSortedMap.equals(smallSortedMap);
    }

    public int hashCode() {
        return this.fields.hashCode();
    }

    public boolean isImmutable() {
        return this.isImmutable;
    }

    public Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        if (this.hasLazyField) {
            return new LazyField.LazyIterator(this.fields.entrySet().iterator());
        }
        return this.fields.entrySet().iterator();
    }

    public void makeImmutable() {
        if (!this.isImmutable) {
            this.fields.makeImmutable();
            this.isImmutable = true;
        }
    }

    public void mergeFrom(FieldSet<FieldDescriptorType> fieldSet) {
        for (int i = 0; i < fieldSet.fields.getNumArrayEntries(); i++) {
            mergeFromField(fieldSet.fields.getArrayEntryAt(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> mergeFromField : fieldSet.fields.getOverflowEntries()) {
            mergeFromField(mergeFromField);
        }
    }

    public void setField(FieldDescriptorType fielddescriptortype, Object obj) {
        GeneratedMessageLite.ExtensionDescriptor extensionDescriptor = (GeneratedMessageLite.ExtensionDescriptor) fielddescriptortype;
        if (!extensionDescriptor.isRepeated) {
            verifyType(extensionDescriptor.type, obj);
        } else if (obj instanceof List) {
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.addAll((List) obj);
            for (Object verifyType : arrayList) {
                verifyType(extensionDescriptor.type, verifyType);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof LazyField) {
            this.hasLazyField = true;
        }
        this.fields.put(fielddescriptortype, obj);
    }

    public FieldSet<FieldDescriptorType> clone() {
        FieldSet<FieldDescriptorType> newFieldSet = newFieldSet();
        for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
            Map.Entry<FieldDescriptorType, Object> arrayEntryAt = this.fields.getArrayEntryAt(i);
            newFieldSet.setField((FieldDescriptorLite) arrayEntryAt.getKey(), arrayEntryAt.getValue());
        }
        for (Map.Entry next : this.fields.getOverflowEntries()) {
            newFieldSet.setField((FieldDescriptorLite) next.getKey(), next.getValue());
        }
        newFieldSet.hasLazyField = this.hasLazyField;
        return newFieldSet;
    }

    private FieldSet(boolean z) {
        makeImmutable();
    }
}
