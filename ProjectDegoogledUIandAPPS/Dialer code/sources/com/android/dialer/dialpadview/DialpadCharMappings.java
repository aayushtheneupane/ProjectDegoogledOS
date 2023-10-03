package com.android.dialer.dialpadview;

import android.content.Context;
import android.support.p000v4.util.SimpleArrayMap;
import android.support.p002v7.appcompat.R$style;
import com.android.dialer.common.Assert;

public class DialpadCharMappings {
    private static final SimpleArrayMap<String, SimpleArrayMap<Character, Character>> CHAR_TO_KEY_MAPS = new SimpleArrayMap<>();
    private static final SimpleArrayMap<String, String[]> KEY_TO_CHAR_MAPS = new SimpleArrayMap<>();

    private static class Bul {
        /* access modifiers changed from: private */
        public static final SimpleArrayMap<Character, Character> CHAR_TO_KEY = DialpadCharMappings.access$000(KEY_TO_CHARS);
        /* access modifiers changed from: private */
        public static final String[] KEY_TO_CHARS = {"", "", "АБВГ", "ДЕЖЗ", "ИЙКЛ", "МНО", "ПРС", "ТУФХ", "ЦЧШЩ", "ЪЬЮЯ", "", ""};
    }

    private static class Latin {
        /* access modifiers changed from: private */
        public static final SimpleArrayMap<Character, Character> CHAR_TO_KEY = DialpadCharMappings.access$000(KEY_TO_CHARS);
        /* access modifiers changed from: private */
        public static final String[] KEY_TO_CHARS = {"+", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ", "", ""};
    }

    private static class Rus {
        /* access modifiers changed from: private */
        public static final SimpleArrayMap<Character, Character> CHAR_TO_KEY = DialpadCharMappings.access$000(KEY_TO_CHARS);
        /* access modifiers changed from: private */
        public static final String[] KEY_TO_CHARS = {"", "", "АБВГ", "ДЕЁЖЗ", "ИЙКЛ", "МНОП", "РСТУ", "ФХЦЧ", "ШЩЪЫ", "ЬЭЮЯ", "", ""};
    }

    private static class Ukr {
        /* access modifiers changed from: private */
        public static final SimpleArrayMap<Character, Character> CHAR_TO_KEY = DialpadCharMappings.access$000(KEY_TO_CHARS);
        /* access modifiers changed from: private */
        public static final String[] KEY_TO_CHARS = {"", "", "АБВГҐ", "ДЕЄЖЗ", "ИІЇЙКЛ", "МНОП", "РСТУ", "ФХЦЧ", "ШЩ", "ЬЮЯ", "", ""};
    }

    static {
        CHAR_TO_KEY_MAPS.put("bul", Bul.CHAR_TO_KEY);
        CHAR_TO_KEY_MAPS.put("rus", Rus.CHAR_TO_KEY);
        CHAR_TO_KEY_MAPS.put("ukr", Ukr.CHAR_TO_KEY);
        KEY_TO_CHAR_MAPS.put("bul", Bul.KEY_TO_CHARS);
        KEY_TO_CHAR_MAPS.put("rus", Rus.KEY_TO_CHARS);
        KEY_TO_CHAR_MAPS.put("ukr", Ukr.KEY_TO_CHARS);
    }

    static /* synthetic */ SimpleArrayMap access$000(String[] strArr) {
        Assert.checkArgument(strArr.length == 12);
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            for (int i2 = 0; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (Character.isAlphabetic(charAt)) {
                    Character valueOf = Character.valueOf(Character.toLowerCase(charAt));
                    Assert.checkArgument(i >= 0 && i <= 11);
                    simpleArrayMap.put(valueOf, Character.valueOf(i != 10 ? i != 11 ? (char) (i + 48) : '#' : '*'));
                }
            }
            i++;
        }
        return simpleArrayMap;
    }

    public static SimpleArrayMap<Character, Character> getCharToKeyMap(Context context) {
        return CHAR_TO_KEY_MAPS.get(R$style.getLocale(context).getISO3Language());
    }

    public static SimpleArrayMap<Character, Character> getDefaultCharToKeyMap() {
        return Latin.CHAR_TO_KEY;
    }

    public static String[] getDefaultKeyToCharsMap() {
        return Latin.KEY_TO_CHARS;
    }

    static String[] getKeyToCharsMap(Context context) {
        return KEY_TO_CHAR_MAPS.get(R$style.getLocale(context).getISO3Language());
    }

    public static SimpleArrayMap<Character, Character> getCharToKeyMap(String str) {
        SimpleArrayMap<Character, Character> simpleArrayMap = CHAR_TO_KEY_MAPS.get(str);
        Assert.isNotNull(simpleArrayMap, "No character mappings can be found for language code '%s'", str);
        return simpleArrayMap;
    }
}
