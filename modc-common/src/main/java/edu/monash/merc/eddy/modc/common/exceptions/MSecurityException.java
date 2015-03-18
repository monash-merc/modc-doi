package edu.monash.merc.eddy.modc.common.exceptions;

/**
 * Created by simonyu on 14/08/2014.
 */
public class MSecurityException extends RuntimeException {
    /**
     * Default constructor.
     */
    public MSecurityException() {
        super();
    }

    /**
     * Constructor taking error message.
     *
     * @param message
     *            error message to set.
     */
    public MSecurityException(final String message) {
        super(message);
    }

    /**
     * Constructor taking throwable object.
     *
     * @param rootCause
     *            Throwable object to set.
     */
    public MSecurityException(final Throwable rootCause) {
        super(rootCause);
    }

    /**
     * Constructor taking throwable object and error message.
     *
     * @param message
     *            error message to set.
     * @param rootCause
     *            Throwable object to set.
     */
    public MSecurityException(final String message, final Throwable rootCause) {
        super(message, rootCause);
    }
}

