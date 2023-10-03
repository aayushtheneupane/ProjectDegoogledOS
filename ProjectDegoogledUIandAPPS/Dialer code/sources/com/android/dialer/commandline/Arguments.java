package com.android.dialer.commandline;

import com.android.dialer.commandline.Command;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.auto.value.AutoValue;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.PeekingIterator;

@AutoValue
public abstract class Arguments {
    public static final Arguments EMPTY = new AutoValue_Arguments(ImmutableMap.m82of(), ImmutableList.m74of());

    public static Arguments parse(String[] strArr) throws Command.IllegalCommandLineArgumentException {
        ImmutableMap immutableMap;
        String str;
        if (strArr == null) {
            return EMPTY;
        }
        PeekingIterator peekingIterator = Collections2.peekingIterator(Collections2.forArray(strArr));
        ImmutableMap.Builder builder = ImmutableMap.builder();
        if (peekingIterator.hasNext()) {
            if (((String) peekingIterator.peek()).startsWith("--")) {
                while (true) {
                    if (!peekingIterator.hasNext()) {
                        immutableMap = builder.build();
                        break;
                    }
                    String str2 = (String) peekingIterator.peek();
                    if (str2.equals("--")) {
                        peekingIterator.next();
                        immutableMap = builder.build();
                        break;
                    } else if (str2.startsWith("--")) {
                        String substring = ((String) peekingIterator.next()).substring(2);
                        if (peekingIterator.hasNext() && !((String) peekingIterator.peek()).startsWith("--")) {
                            str = (String) peekingIterator.next();
                        } else if (substring.contains("=")) {
                            String[] split = substring.split("=", 2);
                            String str3 = split[0];
                            str = split[1];
                            substring = str3;
                        } else if (substring.startsWith("no")) {
                            substring = substring.substring(2);
                            str = "false";
                        } else {
                            str = "true";
                        }
                        builder.put(substring, str);
                    } else {
                        throw new Command.IllegalCommandLineArgumentException("flag or '--' expected");
                    }
                }
            } else {
                immutableMap = builder.build();
            }
        } else {
            immutableMap = builder.build();
        }
        ImmutableList.Builder builder2 = ImmutableList.builder();
        builder2.addAll(peekingIterator);
        return new AutoValue_Arguments(immutableMap, builder2.build());
    }

    public Boolean getBoolean(String str, boolean z) throws Command.IllegalCommandLineArgumentException {
        if (!getFlags().containsKey(str)) {
            return Boolean.valueOf(z);
        }
        String str2 = getFlags().get(str);
        char c = 65535;
        int hashCode = str2.hashCode();
        if (hashCode != 3569038) {
            if (hashCode == 97196323 && str2.equals("false")) {
                c = 1;
            }
        } else if (str2.equals("true")) {
            c = 0;
        }
        if (c == 0) {
            return true;
        }
        if (c == 1) {
            return false;
        }
        throw new Command.IllegalCommandLineArgumentException(GeneratedOutlineSupport.outline8("boolean value expected for ", str));
    }

    public abstract ImmutableMap<String, String> getFlags();

    public abstract ImmutableList<String> getPositionals();
}
