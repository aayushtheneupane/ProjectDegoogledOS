package android.support.transition;

import android.view.View;
import android.view.ViewGroup;
import com.android.dialer.R;

public class Scene {
    private Runnable mExitAction;
    private ViewGroup mSceneRoot;

    static Scene getCurrentScene(View view) {
        return (Scene) view.getTag(R.id.transition_current_scene);
    }

    public void exit() {
        Runnable runnable;
        if (((Scene) this.mSceneRoot.getTag(R.id.transition_current_scene)) == this && (runnable = this.mExitAction) != null) {
            runnable.run();
        }
    }
}
