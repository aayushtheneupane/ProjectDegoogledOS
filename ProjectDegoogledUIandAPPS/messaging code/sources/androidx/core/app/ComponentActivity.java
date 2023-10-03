package androidx.core.app;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import androidx.core.view.KeyEventDispatcher;
import p000a.p005b.C0027n;

public class ComponentActivity extends Activity implements KeyEventDispatcher.Component {
    private C0027n mExtraDataMap = new C0027n();

    public class ExtraData {
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView != null) {
            KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent);
        }
        return KeyEventDispatcher.dispatchKeyEvent(this, decorView, this, keyEvent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView != null) {
            KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent);
        }
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    public ExtraData getExtraData(Class cls) {
        return (ExtraData) this.mExtraDataMap.get(cls);
    }

    public void putExtraData(ExtraData extraData) {
        this.mExtraDataMap.put(extraData.getClass(), extraData);
    }

    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
