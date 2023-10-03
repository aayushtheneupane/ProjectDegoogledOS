package androidx.appcompat.mms;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import androidx.appcompat.mms.CarrierConfigXmlParser;
import com.android.messaging.R;

class DefaultCarrierConfigValuesLoader implements CarrierConfigValuesLoader {
    public static final String KEY_TYPE_BOOL = "bool";
    public static final String KEY_TYPE_INT = "int";
    public static final String KEY_TYPE_STRING = "string";
    private final Context mContext;
    private final SparseArray mValuesCache = new SparseArray();

    DefaultCarrierConfigValuesLoader(Context context) {
        this.mContext = context;
    }

    private void loadFromResources(int i, final Bundle bundle) {
        XmlResourceParser xmlResourceParser = null;
        try {
            xmlResourceParser = Utils.getSubDepContext(this.mContext, i).getResources().getXml(R.xml.mms_config);
            new CarrierConfigXmlParser(xmlResourceParser, new CarrierConfigXmlParser.KeyValueProcessor() {
                public void process(String str, String str2, String str3) {
                    try {
                        if (DefaultCarrierConfigValuesLoader.KEY_TYPE_INT.equals(str)) {
                            bundle.putInt(str2, Integer.parseInt(str3));
                        } else if (DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL.equals(str)) {
                            bundle.putBoolean(str2, Boolean.parseBoolean(str3));
                        } else if (DefaultCarrierConfigValuesLoader.KEY_TYPE_STRING.equals(str)) {
                            bundle.putString(str2, str3);
                        }
                    } catch (NumberFormatException unused) {
                        Log.w("MmsLib", "Load carrier value from resources: invalid " + str2 + "," + str3 + "," + str);
                    }
                }
            }).parse();
            if (xmlResourceParser == null) {
                return;
            }
        } catch (Resources.NotFoundException unused) {
            Log.w("MmsLib", "Can not get mms_config.xml");
            if (xmlResourceParser == null) {
                return;
            }
        } catch (Throwable th) {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw th;
        }
        xmlResourceParser.close();
    }

    private static void loadFromSystem(int i, Bundle bundle) {
        try {
            Bundle carrierConfigValues = Utils.getSmsManager(i).getCarrierConfigValues();
            if (carrierConfigValues != null) {
                bundle.putAll(carrierConfigValues);
            }
        } catch (Exception e) {
            Log.w("MmsLib", "Calling system getCarrierConfigValues exception", e);
        }
    }

    private void loadLocked(int i, Bundle bundle) {
        loadFromResources(i, bundle);
        int i2 = Build.VERSION.SDK_INT;
        loadFromSystem(i, bundle);
    }

    public Bundle get(int i) {
        Bundle bundle;
        boolean z;
        int effectiveSubscriptionId = Utils.getEffectiveSubscriptionId(i);
        synchronized (this) {
            bundle = (Bundle) this.mValuesCache.get(effectiveSubscriptionId);
            if (bundle == null) {
                bundle = new Bundle();
                this.mValuesCache.put(effectiveSubscriptionId, bundle);
                loadLocked(effectiveSubscriptionId, bundle);
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            Log.i("MmsLib", "Carrier configs loaded: " + bundle);
        }
        return bundle;
    }
}
