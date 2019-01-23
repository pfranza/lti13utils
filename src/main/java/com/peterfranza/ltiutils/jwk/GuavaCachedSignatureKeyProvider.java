package com.peterfranza.ltiutils.jwk;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class GuavaCachedSignatureKeyProvider implements SignatureKeyProvider {

	private static final String NULL_KID_KEY = "null-kid";

	private SignatureKeyProvider delegate;

	private final Cache<String, SignatureKey> cache;

	public GuavaCachedSignatureKeyProvider(SignatureKeyProvider provider) {
		this(provider, 5, 10, TimeUnit.HOURS);
	}

	public GuavaCachedSignatureKeyProvider(SignatureKeyProvider delegate, long size, long expiresIn,
			TimeUnit expiresUnit) {
		this.delegate = delegate;
		this.cache = CacheBuilder.newBuilder().maximumSize(size).expireAfterWrite(expiresIn, expiresUnit).build();
	}

	@Override
	public Optional<SignatureKey> getById(String keyId) {
		try {
			String cacheKey = keyId == null ? NULL_KID_KEY : keyId;
			return Optional.ofNullable(cache.get(cacheKey, new Callable<SignatureKey>() {
				@Override
				public SignatureKey call() throws Exception {
					return delegate.getById(keyId).orElseThrow(RuntimeException::new);
				}
			}));
		} catch (ExecutionException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<SignatureKey> getFirst(Function<SignatureKey, Boolean> filter) {
		Optional<SignatureKey> sig = cache.asMap().values().stream().filter(key -> {
			return filter.apply(key);
		}).findFirst();

		if (sig.isPresent())
			return sig;

		sig = delegate.getFirst();
		if (sig.isPresent()) {
			cache.put(sig.get().getKeyPairIdentifier(), sig.get());
			return sig;
		}

		return Optional.empty();
	}

}
