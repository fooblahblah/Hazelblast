package com.hazelblast.client.router;

import com.hazelcast.core.Member;

import java.lang.reflect.Method;

/**
 * A 'placeholder' {@link Router} that indicates to the ProxyProvider that it should rely
 * on loadbalancing higher up, e.g. if something is configured on the Executor.
 *
 * @author Peter Veentjer.
 */
public final class NoOpRouter implements Router {

    public Member getNext(Method method, Object[] args) {
        throw new UnsupportedOperationException();
    }
}