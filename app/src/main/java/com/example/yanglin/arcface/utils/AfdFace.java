package com.example.yanglin.arcface.utils;
import android.util.Log;
import com.arcsoft.facedetection.AFD_FSDKEngine;
import com.arcsoft.facedetection.AFD_FSDKError;
import com.arcsoft.facedetection.AFD_FSDKFace;

import java.util.ArrayList;
import java.util.List;


public class AfdFace {
    public static String appid = "GbiFvjqzJQPLGvjFAXjAZ2D4vZLnqA8exPYyZHQCTHaq";
    public static String fd_key = "3NC7uc9Qi4QJCQdmVuQ7tphJCfAZtjkrQjWtyzrzoVVc";

    AFD_FSDKEngine mFDEngine;
    public AFD_FSDKFace getFace(byte[] data, int width, int height) {
        mFDEngine = new AFD_FSDKEngine();

        // 用来存放检测到的人脸信息列表
        List<AFD_FSDKFace> result = new ArrayList<>();

        AFD_FSDKError err = mFDEngine.AFD_FSDK_InitialFaceEngine(appid,fd_key, AFD_FSDKEngine.AFD_OPF_0_HIGHER_EXT, 16, 5);
        Log.d("com.arcsoft", "AFD_FSDK_InitialFaceEngine = " + err.getCode());

        //输入的data数据为NV21格式（如Camera里NV21格式的preview数据），其中height不能为奇数，人脸检测返回结果保存在result。
        err = mFDEngine.AFD_FSDK_StillImageFaceDetection(data, width, height, AFD_FSDKEngine.CP_PAF_NV21, result);
        Log.d("com.arcsoft", "AFD_FSDK_StillImageFaceDetection =" + err.getCode());
        for (AFD_FSDKFace face : result) {
            Log.d("com.arcsoft", "Face:" + face.toString());
        }
        if (result.size()<=0 && result.size() > 1) {
            return null;
        }
        return result.get(0);
    }

    public void destroy() {
        if (mFDEngine != null) {
            mFDEngine.AFD_FSDK_UninitialFaceEngine();
        }

    }
}