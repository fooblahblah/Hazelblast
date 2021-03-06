package com.hazelblast.client.exceptions;

/**
 * A {@link RemotingException} thrown when a timeout happens when calling a remote method.
 *
 * @author Peter Veentjer.
 */
public class DistributedMethodTimeoutException extends RemotingException {
    static final long serialVersionUID = 1;

    /**
     * Constructs a RemoteMethodTimeoutException.
     *
     * @param message the message.
     * @param cause   the cause of the exception.
     */
    public DistributedMethodTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
