package com.android.dialer.feedback;

import com.android.incallui.call.CallList;

public abstract class FeedbackComponent {

    public interface HasComponent {
    }

    public abstract CallList.Listener getCallFeedbackListener();
}
