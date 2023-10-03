package com.google.android.material.shape;

import com.android.contacts.ContactPhotoManager;

public class MaterialShapeUtils {
    static CornerTreatment createCornerTreatment(int i, int i2) {
        if (i == 0) {
            return new RoundedCornerTreatment((float) i2);
        }
        if (i != 1) {
            return createDefaultCornerTreatment();
        }
        return new CutCornerTreatment((float) i2);
    }

    static CornerTreatment createDefaultCornerTreatment() {
        return new RoundedCornerTreatment(ContactPhotoManager.OFFSET_DEFAULT);
    }

    static EdgeTreatment createDefaultEdgeTreatment() {
        return new EdgeTreatment();
    }
}
