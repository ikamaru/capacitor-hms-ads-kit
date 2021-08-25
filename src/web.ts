import { WebPlugin } from '@capacitor/core';

import type { AdsKitPlugin } from './definitions';

export class AdsKitWeb extends WebPlugin implements AdsKitPlugin {
  showBanner(_options: { bannerId: string }): Promise<void> {
    throw new Error('Method not implemented.');
  }
  hideBanner(): Promise<void> {
    throw new Error('Method not implemented.');
  }
  showInterstitial(_options: { interstitialId: string; }): Promise<void> {
    throw new Error('Method not implemented.');
  }
  
}
