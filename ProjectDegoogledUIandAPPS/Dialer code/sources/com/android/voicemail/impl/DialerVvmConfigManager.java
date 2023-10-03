package com.android.voicemail.impl;

import android.content.Context;
import android.net.Uri;
import android.os.PersistableBundle;
import android.util.ArrayMap;
import com.android.dialer.R;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.AutoValue_CarrierIdentifierMatcher;
import com.android.voicemail.impl.utils.LoggerUtils;
import com.android.voicemail.impl.utils.XmlUtils$ReadMapCallback;
import com.google.common.collect.ComparisonChain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DialerVvmConfigManager {
    static final String KEY_MCCMNC = "mccmnc";
    private static Map<String, SortedSet<ConfigEntry>> cachedConfigs;
    private final Map<String, SortedSet<ConfigEntry>> configs;

    private static class ConfigEntry implements Comparable<ConfigEntry> {
        final PersistableBundle config;
        final CarrierIdentifierMatcher matcher;

        ConfigEntry(CarrierIdentifierMatcher carrierIdentifierMatcher, PersistableBundle persistableBundle) {
            this.matcher = carrierIdentifierMatcher;
            this.config = persistableBundle;
        }

        public int compareTo(Object obj) {
            ConfigEntry configEntry = (ConfigEntry) obj;
            ComparisonChain start = ComparisonChain.start();
            if (this.matcher.gid1().isPresent() && configEntry.matcher.gid1().isPresent()) {
                return start.compare((Comparable<?>) this.matcher.gid1().get(), (Comparable<?>) configEntry.matcher.gid1().get()).compare((Comparable<?>) this.matcher.mccMnc(), (Comparable<?>) configEntry.matcher.mccMnc()).result();
            }
            if (this.matcher.gid1().isPresent()) {
                return -1;
            }
            return configEntry.matcher.gid1().isPresent() ? 1 : 0;
        }
    }

    static class MyReadMapCallback implements XmlUtils$ReadMapCallback {
        MyReadMapCallback() {
        }

        public Object readThisUnknownObjectXml(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
            int next;
            if ("pbundle_as_map".equals(str)) {
                int depth = xmlPullParser.getDepth();
                String name = xmlPullParser.getName();
                String[] strArr = new String[1];
                do {
                    next = xmlPullParser.next();
                    if (next == 1 || (next == 3 && xmlPullParser.getDepth() >= depth)) {
                        return PersistableBundle.EMPTY;
                    }
                } while (next != 2);
                ArrayMap<String, ?> readThisArrayMapXml = LoggerUtils.readThisArrayMapXml(xmlPullParser, name, strArr, new MyReadMapCallback());
                PersistableBundle persistableBundle = new PersistableBundle();
                for (Map.Entry next2 : readThisArrayMapXml.entrySet()) {
                    Object value = next2.getValue();
                    if (value instanceof Integer) {
                        persistableBundle.putInt((String) next2.getKey(), ((Integer) value).intValue());
                    } else if (value instanceof Boolean) {
                        persistableBundle.putBoolean((String) next2.getKey(), ((Boolean) value).booleanValue());
                    } else if (value instanceof String) {
                        persistableBundle.putString((String) next2.getKey(), (String) value);
                    } else if (value instanceof String[]) {
                        persistableBundle.putStringArray((String) next2.getKey(), (String[]) value);
                    } else if (value instanceof PersistableBundle) {
                        persistableBundle.putPersistableBundle((String) next2.getKey(), (PersistableBundle) value);
                    }
                }
                return persistableBundle;
            }
            throw new XmlPullParserException(GeneratedOutlineSupport.outline8("Unknown tag=", str));
        }
    }

    public DialerVvmConfigManager(Context context) {
        if (cachedConfigs == null) {
            cachedConfigs = loadConfigs(context, context.getResources().getXml(R.xml.vvm_config));
        }
        this.configs = cachedConfigs;
    }

    private static Map<String, SortedSet<ConfigEntry>> loadConfigs(Context context, XmlPullParser xmlPullParser) {
        SortedSet sortedSet;
        ArrayMap arrayMap = new ArrayMap();
        try {
            Iterator it = readBundleList(xmlPullParser).iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof PersistableBundle) {
                    PersistableBundle persistableBundle = (PersistableBundle) next;
                    if (persistableBundle.containsKey("feature_flag_name")) {
                        if (!((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean(persistableBundle.getString("feature_flag_name"), false)) {
                        }
                    }
                    String[] stringArray = persistableBundle.getStringArray(KEY_MCCMNC);
                    if (stringArray != null) {
                        for (String parse : stringArray) {
                            Uri parse2 = Uri.parse(parse);
                            String path = parse2.getPath();
                            if (arrayMap.containsKey(path)) {
                                sortedSet = (SortedSet) arrayMap.get(path);
                            } else {
                                sortedSet = new TreeSet();
                                arrayMap.put(path, sortedSet);
                            }
                            AutoValue_CarrierIdentifierMatcher.Builder builder = new AutoValue_CarrierIdentifierMatcher.Builder();
                            builder.setMccMnc(path);
                            if (parse2.getQueryParameterNames().contains("gid1")) {
                                builder.setGid1(parse2.getQueryParameter("gid1"));
                            }
                            sortedSet.add(new ConfigEntry(builder.build(), persistableBundle));
                        }
                    } else {
                        throw new IllegalArgumentException("MCCMNC is null");
                    }
                } else {
                    throw new IllegalArgumentException("PersistableBundle expected, got " + next);
                }
            }
            return arrayMap;
        } catch (IOException | XmlPullParserException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList readBundleList(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        int next;
        int depth = xmlPullParser.getDepth();
        do {
            next = xmlPullParser.next();
            if (next == 1) {
                return null;
            }
            if (next == 3 && xmlPullParser.getDepth() >= depth) {
                return null;
            }
        } while (next != 2);
        xmlPullParser.next();
        return LoggerUtils.readThisListXml(xmlPullParser, xmlPullParser.getName(), new String[1], new MyReadMapCallback(), false);
    }

    public PersistableBundle getConfig(CarrierIdentifier carrierIdentifier) {
        if (!this.configs.containsKey(carrierIdentifier.mccMnc())) {
            return null;
        }
        for (ConfigEntry configEntry : this.configs.get(carrierIdentifier.mccMnc())) {
            CarrierIdentifierMatcher carrierIdentifierMatcher = configEntry.matcher;
            boolean z = false;
            if (carrierIdentifierMatcher.mccMnc().equals(carrierIdentifier.mccMnc()) && (!carrierIdentifierMatcher.gid1().isPresent() || carrierIdentifierMatcher.gid1().get().equalsIgnoreCase(carrierIdentifier.gid1()))) {
                z = true;
                continue;
            }
            if (z) {
                return configEntry.config;
            }
        }
        return null;
    }

    DialerVvmConfigManager(Context context, XmlPullParser xmlPullParser) {
        this.configs = loadConfigs(context, xmlPullParser);
    }
}
