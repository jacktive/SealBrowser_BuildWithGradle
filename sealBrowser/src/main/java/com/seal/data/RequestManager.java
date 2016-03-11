package com.seal.data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.seal.ui.activities.SLAppication;
import com.seal.utils.FileUtils;

public class RequestManager {

	private static FileUtils fileUtils;
	private static RequestQueue mRequestQueue;

	public static void init(Context context) {
		fileUtils = new FileUtils();
		mRequestQueue = Volley.newRequestQueue(context, fileUtils.getCacheFile());
	}

	public static RequestQueue getRuquestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(SLAppication.getContext(), fileUtils.getCacheFile());
		}
		return mRequestQueue;
	}

	public static void addRequest(Request<?> request, Object tag) {
		if (tag != null) {
			request.setTag(tag);
		}
		mRequestQueue.add(request);
	}

	public static void cancelAll(Object tag) {
		mRequestQueue.cancelAll(tag);
	}
}
