package com.android.systemui.havoc.carrierlabel;

import android.os.Environment;
import android.telephony.Rlog;
import android.util.Xml;
import com.android.internal.util.XmlUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SpnOverride {
    private HashMap<String, String> mCarrierSpnMap = new HashMap<>();

    public SpnOverride() {
        loadSpnOverrides();
    }

    public String getSpn(String str) {
        return this.mCarrierSpnMap.get(str);
    }

    private void loadSpnOverrides() {
        try {
            FileReader fileReader = new FileReader(new File(Environment.getRootDirectory(), "etc/spn-conf.xml"));
            try {
                XmlPullParser newPullParser = Xml.newPullParser();
                newPullParser.setInput(fileReader);
                XmlUtils.beginDocument(newPullParser, "spnOverrides");
                while (true) {
                    XmlUtils.nextElement(newPullParser);
                    if (!"spnOverride".equals(newPullParser.getName())) {
                        fileReader.close();
                        return;
                    }
                    this.mCarrierSpnMap.put(newPullParser.getAttributeValue((String) null, "numeric"), newPullParser.getAttributeValue((String) null, "spn"));
                }
            } catch (XmlPullParserException e) {
                Rlog.w("SpnOverride", "Exception in spn-conf parser " + e);
            } catch (IOException e2) {
                Rlog.w("SpnOverride", "Exception in spn-conf parser " + e2);
            }
        } catch (FileNotFoundException unused) {
            Rlog.w("SpnOverride", "Can not open " + Environment.getRootDirectory() + "/" + "etc/spn-conf.xml");
        }
    }
}
