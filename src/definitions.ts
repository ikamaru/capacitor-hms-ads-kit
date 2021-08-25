export interface AdsKitPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
