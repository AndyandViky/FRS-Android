package com.example.yanglin.arcface.utils;

import android.graphics.Rect;
import android.util.Log;

import com.arcsoft.facedetection.AFD_FSDKFace;
import com.arcsoft.facerecognition.AFR_FSDKEngine;
import com.arcsoft.facerecognition.AFR_FSDKError;
import com.arcsoft.facerecognition.AFR_FSDKFace;
import com.arcsoft.facerecognition.AFR_FSDKMatching;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanglin on 18-4-9.
 */

public class AfrFace {
    public static String appid = "GbiFvjqzJQPLGvjFAXjAZ2D4vZLnqA8exPYyZHQCTHaq";
    public static String fr_key = "3NC7uc9Qi4QJCQdmVuQ7tphRN4RnzZzmWnFcy2epUjMX";

    AFR_FSDKEngine mFREngine;
    public AFR_FSDKFace getFeature(byte[] data, int width, int height, AFD_FSDKFace face1) {
        mFREngine = new AFR_FSDKEngine();
        AFR_FSDKFace face = new AFR_FSDKFace();
        mFREngine.AFR_FSDK_InitialEngine(appid, fr_key);

        mFREngine.AFR_FSDK_ExtractFRFeature(data, width, height, AFR_FSDKEngine.CP_PAF_NV21, new Rect(210, 170, 470, 440), AFR_FSDKEngine.AFR_FOC_0, face);
        return face;
    }

    public void destroy() {
        if (mFREngine != null) {
            mFREngine.AFR_FSDK_UninitialEngine();
        }

    }
}
