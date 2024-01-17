package org.example.pubsub.pseudorandom;

import java.util.concurrent.atomic.AtomicLong;

public class LockFreePseudoRandom{
    private AtomicLong seed;
    private static final long multiplier = 0x5DEECE66DL;
    private static final long addend = 0xBL;
    private static final long mask = (1L << 48) - 1;

    public LockFreePseudoRandom(Long seed) {
        this.seed = new AtomicLong(seed);
    }

    public LockFreePseudoRandom() {
        this(System.currentTimeMillis());
    }

    public int nextInt (int n) {
        return next(31) % n;
    }

    protected int next(int bits) {
        long oldseed, nextseed;
        do {
            oldseed = seed.get();
            nextseed = (oldseed * multiplier + addend) & mask;
        } while (!seed.compareAndSet(oldseed, nextseed));
        return (int)(nextseed >>> (48 - bits));
    }
}
