package com.google.protobuf;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.List;

final class MessageLiteToString {
    private static final String camelCaseToSnakeCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }

    static String escapeBytes(ByteString byteString) {
        StringBuilder sb = new StringBuilder(byteString.size());
        for (int i = 0; i < byteString.size(); i++) {
            byte byteAt = byteString.byteAt(i);
            if (byteAt == 34) {
                sb.append("\\\"");
            } else if (byteAt == 39) {
                sb.append("\\'");
            } else if (byteAt != 92) {
                switch (byteAt) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (byteAt >= 32 && byteAt <= 126) {
                            sb.append((char) byteAt);
                            break;
                        } else {
                            sb.append('\\');
                            sb.append((char) (((byteAt >>> 6) & 3) + 48));
                            sb.append((char) (((byteAt >>> 3) & 7) + 48));
                            sb.append((char) ((byteAt & 7) + 48));
                            break;
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    static final void printField(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object printField : (List) obj) {
                printField(sb, i, str, printField);
            }
            return;
        }
        sb.append(10);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(escapeBytes(ByteString.copyFromUtf8((String) obj)));
            sb.append('\"');
        } else if (obj instanceof ByteString) {
            sb.append(": \"");
            sb.append(escapeBytes((ByteString) obj));
            sb.append('\"');
        } else if (obj instanceof GeneratedMessageLite) {
            sb.append(" {");
            reflectivePrintWithIndent((GeneratedMessageLite) obj, sb, i + 2);
            sb.append("\n");
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append("}");
        } else {
            sb.append(": ");
            sb.append(obj.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0160, code lost:
        if (((java.lang.Integer) r9).intValue() == 0) goto L_0x01b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0171, code lost:
        if (((java.lang.Float) r9).floatValue() == 0.0f) goto L_0x01b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0183, code lost:
        if (((java.lang.Double) r9).doubleValue() == 0.0d) goto L_0x01b5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void reflectivePrintWithIndent(com.google.protobuf.MessageLite r12, java.lang.StringBuilder r13, int r14) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.util.TreeSet r2 = new java.util.TreeSet
            r2.<init>()
            java.lang.Class r3 = r12.getClass()
            java.lang.reflect.Method[] r3 = r3.getDeclaredMethods()
            int r4 = r3.length
            r5 = 0
            r6 = r5
        L_0x001a:
            java.lang.String r7 = "get"
            if (r6 >= r4) goto L_0x0049
            r8 = r3[r6]
            java.lang.String r9 = r8.getName()
            r1.put(r9, r8)
            java.lang.Class[] r9 = r8.getParameterTypes()
            int r9 = r9.length
            if (r9 != 0) goto L_0x0046
            java.lang.String r9 = r8.getName()
            r0.put(r9, r8)
            java.lang.String r9 = r8.getName()
            boolean r7 = r9.startsWith(r7)
            if (r7 == 0) goto L_0x0046
            java.lang.String r7 = r8.getName()
            r2.add(r7)
        L_0x0046:
            int r6 = r6 + 1
            goto L_0x001a
        L_0x0049:
            java.util.Iterator r2 = r2.iterator()
        L_0x004d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x01d5
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = ""
            java.lang.String r3 = r3.replaceFirst(r7, r4)
            java.lang.String r6 = "List"
            boolean r6 = r3.endsWith(r6)
            r8 = 1
            if (r6 == 0) goto L_0x00b6
            java.lang.String r6 = "OrBuilderList"
            boolean r6 = r3.endsWith(r6)
            if (r6 != 0) goto L_0x00b6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r9 = r3.substring(r5, r8)
            java.lang.String r9 = r9.toLowerCase()
            r6.append(r9)
            int r9 = r3.length()
            int r9 = r9 + -4
            java.lang.String r9 = r3.substring(r8, r9)
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r7)
            r9.append(r3)
            java.lang.String r9 = r9.toString()
            java.lang.Object r9 = r0.get(r9)
            java.lang.reflect.Method r9 = (java.lang.reflect.Method) r9
            if (r9 == 0) goto L_0x00b6
            java.lang.String r3 = camelCaseToSnakeCase(r6)
            java.lang.Object[] r4 = new java.lang.Object[r5]
            java.lang.Object r4 = com.google.protobuf.GeneratedMessageLite.invokeOrDie(r9, r12, r4)
            printField(r13, r14, r3, r4)
            goto L_0x004d
        L_0x00b6:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r9 = "set"
            r6.append(r9)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            java.lang.Object r6 = r1.get(r6)
            java.lang.reflect.Method r6 = (java.lang.reflect.Method) r6
            if (r6 != 0) goto L_0x00d1
            goto L_0x004d
        L_0x00d1:
            java.lang.String r6 = "Bytes"
            boolean r6 = r3.endsWith(r6)
            if (r6 == 0) goto L_0x00f6
            java.lang.StringBuilder r6 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r7)
            int r9 = r3.length()
            int r9 = r9 + -5
            java.lang.String r9 = r3.substring(r5, r9)
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            boolean r6 = r0.containsKey(r6)
            if (r6 == 0) goto L_0x00f6
            goto L_0x004d
        L_0x00f6:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r9 = r3.substring(r5, r8)
            java.lang.String r9 = r9.toLowerCase()
            r6.append(r9)
            java.lang.String r9 = r3.substring(r8)
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r7)
            r9.append(r3)
            java.lang.String r9 = r9.toString()
            java.lang.Object r9 = r0.get(r9)
            java.lang.reflect.Method r9 = (java.lang.reflect.Method) r9
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "has"
            r10.append(r11)
            r10.append(r3)
            java.lang.String r3 = r10.toString()
            java.lang.Object r3 = r0.get(r3)
            java.lang.reflect.Method r3 = (java.lang.reflect.Method) r3
            if (r9 == 0) goto L_0x004d
            java.lang.Object[] r10 = new java.lang.Object[r5]
            java.lang.Object r9 = com.google.protobuf.GeneratedMessageLite.invokeOrDie(r9, r12, r10)
            if (r3 != 0) goto L_0x01be
            boolean r3 = r9 instanceof java.lang.Boolean
            if (r3 == 0) goto L_0x0155
            r3 = r9
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            r3 = r3 ^ r8
            goto L_0x01b8
        L_0x0155:
            boolean r3 = r9 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x0163
            r3 = r9
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            if (r3 != 0) goto L_0x01b7
            goto L_0x01b5
        L_0x0163:
            boolean r3 = r9 instanceof java.lang.Float
            if (r3 == 0) goto L_0x0174
            r3 = r9
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
            r4 = 0
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x01b7
            goto L_0x01b5
        L_0x0174:
            boolean r3 = r9 instanceof java.lang.Double
            if (r3 == 0) goto L_0x0186
            r3 = r9
            java.lang.Double r3 = (java.lang.Double) r3
            double r3 = r3.doubleValue()
            r10 = 0
            int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r3 != 0) goto L_0x01b7
            goto L_0x01b5
        L_0x0186:
            boolean r3 = r9 instanceof java.lang.String
            if (r3 == 0) goto L_0x018f
            boolean r3 = r9.equals(r4)
            goto L_0x01b8
        L_0x018f:
            boolean r3 = r9 instanceof com.google.protobuf.ByteString
            if (r3 == 0) goto L_0x019a
            com.google.protobuf.ByteString r3 = com.google.protobuf.ByteString.EMPTY
            boolean r3 = r9.equals(r3)
            goto L_0x01b8
        L_0x019a:
            boolean r3 = r9 instanceof com.google.protobuf.MessageLite
            if (r3 == 0) goto L_0x01a8
            r3 = r9
            com.google.protobuf.MessageLite r3 = (com.google.protobuf.MessageLite) r3
            com.google.protobuf.MessageLite r3 = r3.getDefaultInstanceForType()
            if (r9 != r3) goto L_0x01b7
            goto L_0x01b5
        L_0x01a8:
            boolean r3 = r9 instanceof java.lang.Enum
            if (r3 == 0) goto L_0x01b7
            r3 = r9
            java.lang.Enum r3 = (java.lang.Enum) r3
            int r3 = r3.ordinal()
            if (r3 != 0) goto L_0x01b7
        L_0x01b5:
            r3 = r8
            goto L_0x01b8
        L_0x01b7:
            r3 = r5
        L_0x01b8:
            if (r3 != 0) goto L_0x01bc
            r3 = r8
            goto L_0x01ca
        L_0x01bc:
            r3 = r5
            goto L_0x01ca
        L_0x01be:
            java.lang.Object[] r4 = new java.lang.Object[r5]
            java.lang.Object r3 = com.google.protobuf.GeneratedMessageLite.invokeOrDie(r3, r12, r4)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
        L_0x01ca:
            if (r3 == 0) goto L_0x004d
            java.lang.String r3 = camelCaseToSnakeCase(r6)
            printField(r13, r14, r3, r9)
            goto L_0x004d
        L_0x01d5:
            boolean r0 = r12 instanceof com.google.protobuf.GeneratedMessageLite.ExtendableMessage
            if (r0 == 0) goto L_0x0210
            r0 = r12
            com.google.protobuf.GeneratedMessageLite$ExtendableMessage r0 = (com.google.protobuf.GeneratedMessageLite.ExtendableMessage) r0
            com.google.protobuf.FieldSet<com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor> r0 = r0.extensions
            java.util.Iterator r0 = r0.iterator()
        L_0x01e2:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0210
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.String r2 = "["
            java.lang.StringBuilder r2 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r2)
            java.lang.Object r3 = r1.getKey()
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r3 = (com.google.protobuf.GeneratedMessageLite.ExtensionDescriptor) r3
            int r3 = r3.number
            r2.append(r3)
            java.lang.String r3 = "]"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.Object r1 = r1.getValue()
            printField(r13, r14, r2, r1)
            goto L_0x01e2
        L_0x0210:
            com.google.protobuf.GeneratedMessageLite r12 = (com.google.protobuf.GeneratedMessageLite) r12
            com.google.protobuf.UnknownFieldSetLite r12 = r12.unknownFields
            if (r12 == 0) goto L_0x0219
            r12.printWithIndent(r13, r14)
        L_0x0219:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageLiteToString.reflectivePrintWithIndent(com.google.protobuf.MessageLite, java.lang.StringBuilder, int):void");
    }

    static String toString(MessageLite messageLite, String str) {
        StringBuilder outline14 = GeneratedOutlineSupport.outline14("# ", str);
        reflectivePrintWithIndent(messageLite, outline14, 0);
        return outline14.toString();
    }
}
