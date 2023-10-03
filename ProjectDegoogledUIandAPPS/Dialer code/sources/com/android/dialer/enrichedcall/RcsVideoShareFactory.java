package com.android.dialer.enrichedcall;

import com.android.incallui.videotech.VideoTech;

public interface RcsVideoShareFactory {
    VideoTech newRcsVideoShare(EnrichedCallManager enrichedCallManager, VideoTech.VideoTechListener videoTechListener, String str);
}
