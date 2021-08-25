import { registerPlugin } from '@capacitor/core';

import type { AdsKitPlugin } from './definitions';

const AdsKit = registerPlugin<AdsKitPlugin>('AdsKit', {
  web: () => import('./web').then(m => new m.AdsKitWeb()),
});

export * from './definitions';
export { AdsKit };
