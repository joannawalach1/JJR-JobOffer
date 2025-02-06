package pl.juniorjavaready.domain.offer;

import java.util.concurrent.atomic.AtomicInteger;

public class JobOfferIdGenerator {
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static int getNextId() {
        return counter.incrementAndGet();
    }
}