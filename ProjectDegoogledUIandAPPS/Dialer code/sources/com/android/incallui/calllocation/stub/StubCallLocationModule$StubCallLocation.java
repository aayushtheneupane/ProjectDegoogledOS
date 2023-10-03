package com.android.incallui.calllocation.stub;

import android.content.Context;
import com.android.incallui.calllocation.CallLocation;

class StubCallLocationModule$StubCallLocation implements CallLocation {
    StubCallLocationModule$StubCallLocation() {
    }

    public boolean canGetLocation(Context context) {
        return false;
    }

    public void close() {
    }
}
