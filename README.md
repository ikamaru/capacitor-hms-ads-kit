# capacitor-hms-ads-kit

Provides the Publisher Service for you to obtain high-quality ad content and quickly grow your business based on Huawei's device capabilities and integrated resources.

## Install

```bash
npm install capacitor-hms-ads-kit
npx cap sync
```

## API

<docgen-index>

* [`showBanner(...)`](#showbanner)
* [`hideBanner()`](#hidebanner)
* [`showInterstitial(...)`](#showinterstitial)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### showBanner(...)

```typescript
showBanner(options: { bannerId: string; settings: { postion: "top" | "bottom"; }; }) => any
```

| Param         | Type                                                                          |
| ------------- | ----------------------------------------------------------------------------- |
| **`options`** | <code>{ bannerId: string; settings: { postion: "top" \| "bottom"; }; }</code> |

**Returns:** <code>any</code>

--------------------


### hideBanner()

```typescript
hideBanner() => any
```

**Returns:** <code>any</code>

--------------------


### showInterstitial(...)

```typescript
showInterstitial(options: { interstitialId: string; }) => any
```

| Param         | Type                                     |
| ------------- | ---------------------------------------- |
| **`options`** | <code>{ interstitialId: string; }</code> |

**Returns:** <code>any</code>

--------------------

</docgen-api>
