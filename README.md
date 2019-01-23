# LTI 1.3 Parsing Utilities Library




Usage:
```java
//Load the platform JWKS public keys from a url
GuavaCachedSignatureKeyProvider platformKeys = new GuavaCachedSignatureKeyProvider(
		new URLJWKSKeyProvider(new URL("https://fancylms.org/.well-known/jwks.json")));

//Transform an HttpServletRequest into an LTI13Request
LTI13Request ltiRequest = LTI13JWSParser.convertOrThrow(platformKeys, request);
```
