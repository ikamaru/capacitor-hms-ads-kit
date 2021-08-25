package com.ikamaru.capacitor.hms.ads.kit;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.InterstitialAd;
import com.huawei.hms.ads.banner.BannerView;

@CapacitorPlugin(name = "AdsKit")
public class AdsKitPlugin extends Plugin {

    private static final String TAG = "AdsKitPlugin";
    //private AdsKit implementation = new AdsKit();
    BannerView bannerView;
    LinearLayout mAdViewLayout;
    ViewGroup mViewGroup=null;
    ViewGroup mIonicView=null;

    boolean bannerLoaded=false;

    private InterstitialAd interstitialAd;

    @Override
    public void load() {
        super.load();
        // Initialize the HUAWEI Ads SDK.
        HwAds.init(bridge.getContext());
    }

    @PluginMethod
    public void showBanner(PluginCall call) {
        if(bannerLoaded) {
            call.reject("already loaded");
            return;
        }
        bridge.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String bannerId="testw6vs28auh3";
                if(call.getData().has("bannerId") && !call.getData().getString("bannerId").isEmpty()){
                    bannerId=call.getData().getString("bannerId");//
                }
                String position="top";
                if(call.getData().has("settings")){
                    position=call.getData().getJSObject("settings").getString("position","top");
                }
                //Log.i(TAG, "bannerId: "+bannerId);
                //prepare the banner
                bannerView = new BannerView(bridge.getContext());
                bannerView.setAdId(bannerId);
                if(mViewGroup==null) {
                    //get the ionic view
                    mViewGroup = bridge.getActivity().findViewById(android.R.id.content);
                    mIonicView = (ViewGroup) mViewGroup.getChildAt(0);
                }
                if(mAdViewLayout!=null){//if the layout already there => just clear it
                    mAdViewLayout.removeAllViews();
                }else{//else=> prepare it and clear the mViewGroup
                    mAdViewLayout = new LinearLayout(bridge.getContext());
                    mAdViewLayout.setLayoutParams(new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    mAdViewLayout.setOrientation(LinearLayout.VERTICAL);
                    //remove the ionic view from the screen
                    mViewGroup.removeAllViews();
                    //put the created layout inside the main view
                    mViewGroup.addView(mAdViewLayout);
                }
                //put in the layout both the bannerview and ionic view
                if(position!=null && position.equals("bottom")){//if position bottom -> in the layout put the ionic view in the top then the bannerview
                    mAdViewLayout.addView(mIonicView);
                    mAdViewLayout.addView(bannerView);
                }else{//else=>in the layout  put the bannerview in the top then the ionic view
                    mAdViewLayout.addView(bannerView);
                    mAdViewLayout.addView(mIonicView);
                }

                // Create an ad request to load an ad.
                AdParam adParam = new AdParam.Builder().build();
                bannerView.loadAd(adParam);
                bannerView.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // Called when an ad is loaded successfully.
                        Log.i(TAG, "onAdLoaded: ");
                        call.resolve();
                        bridge.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                /*if(!bannerLoaded){
                                    //if onAdLoaded decrease the ionic view to give space to the bannerview
                                    ViewGroup.LayoutParams params = mIonicView.getLayoutParams();
                                    params.height = mIonicView.getHeight()-175;
                                    mIonicView.requestLayout();
                                    bannerLoaded=true;
                                }*/

                            }
                        });
                    }
                    @Override
                    public void onAdFailed(int errorCode) {
                        // Called when an ad fails to be loaded.
                        Log.i(TAG, "onAdFailed: "+errorCode);
                        call.reject("onAdFailed: "+errorCode);
                        bridge.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                /*if(bannerLoaded){
                                    //if onAdLoaded decrease the ionic view to give space to the bannerview
                                    ViewGroup.LayoutParams params = mIonicView.getLayoutParams();
                                    params.height = mIonicView.getHeight()+175;
                                    mIonicView.requestLayout();
                                    bannerLoaded=false;
                                }*/
                            }
                        });
                    }
                    @Override
                    public void onAdOpened() {
                        // Called when an ad is opened.
                        Log.i(TAG, "onAdOpened: ");
                    }
                    @Override
                    public void onAdClicked() {
                        // Called when an ad is clicked.
                        Log.i(TAG, "onAdClicked: ");
                    }
                    @Override
                    public void onAdLeave() {
                        // Called when an ad leaves an app.
                        Log.i(TAG, "onAdLeave: ");
                    }
                    @Override
                    public void onAdClosed() {
                        // Called when an ad is closed.
                        Log.i(TAG, "onAdClosed: ");
                        bridge.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                /*if(bannerLoaded){
                                    //if onAdLoaded decrease the ionic view to give space to the bannerview
                                    ViewGroup.LayoutParams params = mIonicView.getLayoutParams();
                                    params.height = mIonicView.getHeight()+175;
                                    mIonicView.requestLayout();
                                    bannerLoaded=false;
                                }*/
                            }
                        });
                    }
                });
            }
        });
    }
    @PluginMethod
    public void hideBanner(PluginCall call) {

        bridge.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bannerView.destroy();
                mAdViewLayout.removeAllViews();
                mAdViewLayout.addView(mIonicView);
                call.resolve();
                /*if(bannerLoaded){
                    call.resolve();
                    //if onAdLoaded decrease the ionic view to give space to the bannerview
                    ViewGroup.LayoutParams params = mIonicView.getLayoutParams();
                    params.height = mIonicView.getHeight()+175;
                    mIonicView.requestLayout();
                    bannerLoaded=false;
                }else{
                    call.reject("already closed");
                }*/
            }
        });
    }
    @PluginMethod
    public void showInterstitial(PluginCall call) {
        String interstitialId="testb4znbuh3n2"; // "testb4znbuh3n2" is a dedicated test ad unit ID. Before releasing your app, replace the test ad unit ID with the formal one.
        if(call.getData().has("interstitialId") && !call.getData().getString("interstitialId").isEmpty()){
            interstitialId=call.getData().getString("interstitialId");
        }
        //Log.i(TAG, "interstitialId:"+interstitialId);
        interstitialAd = new InterstitialAd(bridge.getContext());
        interstitialAd.setAdId(interstitialId);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Called when an ad is loaded successfully.
                Log.i(TAG, "onAdLoaded: ");
                call.resolve();
            }
            @Override
            public void onAdFailed(int errorCode) {
                // Called when an ad fails to be loaded.
                Log.i(TAG, "onAdFailed: "+errorCode);
                call.reject("onAdFailed: "+errorCode);
            }
            @Override
            public void onAdClosed() {
                // Called when an ad is closed.
                Log.i(TAG, "onAdClosed: ");
            }
            @Override
            public void onAdClicked() {
                // Called when an ad is clicked.
                Log.i(TAG, "onAdClicked: ");
            }
            @Override
            public void onAdLeave() {
                // Called when an ad leaves an app.
                Log.i(TAG, "onAdLeave: ");
            }
            @Override
            public void onAdOpened() {
                // Called when an ad is opened.
                Log.i(TAG, "onAdOpened: ");
            }
            @Override
            public void onAdImpression() {
                // Called when an ad impression occurs.
                Log.i(TAG, "onAdImpression: ");
            }
        });
        // Load an interstitial ad.
        AdParam adParam = new AdParam.Builder().build();
        interstitialAd.loadAd(adParam);
        try {
            Thread.sleep(2000);
            // Display the ad.
            if (interstitialAd != null && interstitialAd.isLoaded()) {
                Log.i(TAG, "showInterstitial: Ad loaded");
                interstitialAd.show(bridge.getActivity());
            } else {
                call.reject("Ad not loaded");
                Log.i(TAG, "showInterstitial: Ad not loaded");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
