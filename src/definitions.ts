export interface AdsKitPlugin {
  showBanner(options: { bannerId: string,settings:{postion:"top"|"bottom"}}): Promise<void>;
  hideBanner(): Promise<void>;
  showInterstitial(options: { interstitialId: string }): Promise<void>;
  //
}
