package edu.monash.merc.eddy.modc.common.exception;

/**
 * Created by simonyu on 14/08/2014.
 */
public class MException extends RuntimeException {
    /**
     * Default constructor.
     */
    public MException() {
        super();
    }

    /**
     * Constructor taking error message.
     *
     * @param message
     *            error message to set.
     */
    public MException(final String message) {
        super(message);
    }

    /**
     * Constructor taking throwable object.
     *
     * @param rootCause
     *            Throwable object to set.
     */
    public MException(final Throwable rootCause) {
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
    public MException(final String message, final Throwable rootCause) {
        super(message, rootCause);
    }
}

