package com.hazelblast.client.smarter;

import com.hazelblast.client.annotations.LoadBalanced;
import com.hazelblast.client.router.NoOpRouter;
import com.hazelblast.client.router.Router;
import com.hazelcast.core.HazelcastInstance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.String.format;

public class LoadBalancedMethodHandler extends RoutedMethodHandler {

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return LoadBalanced.class;
    }

    public ProxyMethod analyze(Method method) {
        LoadBalanced annotation = method.getAnnotation(LoadBalanced.class);

        long timeoutMs;
        boolean interruptOnTimeout;
        int partitionKeyIndex = -1;
        Method partitionKeyProperty = null;
        Router loadBalancer = null;
        timeoutMs = annotation.timeoutMs();
        interruptOnTimeout = annotation.interruptOnTimeout();

        Class<? extends Router> loadBalancerClass = annotation.loadBalancer();
        if (!loadBalancerClass.equals(NoOpRouter.class)) {
            try {
                Constructor<? extends Router> constructor = loadBalancerClass.getConstructor(HazelcastInstance.class);
                loadBalancer = constructor.newInstance(hazelcastInstance);
            } catch (InstantiationException e) {
                throw new IllegalArgumentException(format("Failed to instantiate Router class '%s'", loadBalancerClass.getName()), e);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException(format("Failed to instantiate Router class '%s'", loadBalancerClass.getName()), e);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException(format("Failed to instantiate Router class '%s'", loadBalancerClass.getName()), e);
            } catch (InvocationTargetException e) {
                throw new IllegalArgumentException(format("Failed to instantiate Router class '%s'", loadBalancerClass.getName()), e);
            }
        }

        return new ProxyMethod(method, partitionKeyIndex, partitionKeyProperty, timeoutMs, interruptOnTimeout, loadBalancer, this);
    }
}