import { WebPlugin } from '@capacitor/core';

import type { AdsKitPlugin } from './definitions';

export class AdsKitWeb extends WebPlugin implements AdsKitPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
