package com.android.tools.p006r8;

import com.android.contacts.common.model.account.AccountType;
import com.android.contacts.common.model.dataitem.DataKind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.android.tools.r8.GeneratedOutlineSupport */
/* compiled from: outline */
public class GeneratedOutlineSupport {
    public static float outline0(float f, float f2, float f3, float f4) {
        return ((f - f2) * f3) + f4;
    }

    public static int outline1(Object obj, int i) {
        return String.valueOf(obj).length() + i;
    }

    public static String outline10(String str, boolean z) {
        return str + z;
    }

    public static String outline11(StringBuilder sb, Object obj, String str) {
        sb.append(obj);
        sb.append(str);
        return sb.toString();
    }

    public static String outline12(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static StringBuilder outline13(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder outline14(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static ArrayList outline15(int i, Map map, Object obj, int i2, Object obj2) {
        map.put(Integer.valueOf(i), obj);
        ArrayList arrayList = new ArrayList(i2);
        arrayList.add(obj2);
        return arrayList;
    }

    public static ArrayList outline16(ArrayList arrayList, Object obj, int i, Map map, Object obj2, int i2) {
        arrayList.add(obj);
        map.put(Integer.valueOf(i), obj2);
        return new ArrayList(i2);
    }

    public static void outline17(String str, int i, int i2, List list) {
        list.add(new AccountType.EditField(str, i, i2));
    }

    public static void outline18(HashSet hashSet, Object obj, Object obj2, Object obj3, Object obj4) {
        hashSet.add(obj);
        hashSet.add(obj2);
        hashSet.add(obj3);
        hashSet.add(obj4);
    }

    public static void outline19(Set set, Object obj, Object obj2, Object obj3, Object obj4) {
        set.add(obj);
        set.add(obj2);
        set.add(obj3);
        set.add(obj4);
    }

    public static DataKind outline2(String str, int i, int i2, boolean z, AccountType accountType) {
        DataKind dataKind = new DataKind(str, i, i2, z);
        accountType.addKind(dataKind);
        return dataKind;
    }

    public static Character outline3(char c, HashMap hashMap, Object obj, char c2) {
        hashMap.put(obj, Character.valueOf(c));
        return Character.valueOf(c2);
    }

    public static String outline4(int i, String str, String str2) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String outline5(String str, int i) {
        return str + i;
    }

    public static String outline6(String str, Object obj) {
        return str + obj;
    }

    public static String outline7(String str, Object obj, String str2) {
        return str + obj + str2;
    }

    public static String outline8(String str, String str2) {
        return str + str2;
    }

    public static String outline9(String str, String str2, String str3) {
        return str + str2 + str3;
    }
}
