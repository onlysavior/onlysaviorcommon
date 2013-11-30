package onlysaviorcommon.util;

import onlysaviorcommon.exception.UnexpectedFailureException;
import onlysaviorcommon.exception.UnreachableCodeException;

/**
 * assert fast fail
 */
public class Assert {
    public static <T> T assertNotNull(T object) {
        return assertNotNull(object, null, null, (Object[])null);
    }

    public static <T> T assertNotNull(T object, String message, Object... args) {
        return assertNotNull(object, null, message, args);
    }

    public static <T> T assertNotNull(T object, ExceptionType exceptionType, String message, Object... args) {
        if (object == null) {
            if (exceptionType == null) {
                exceptionType = ExceptionType.ILLEGAL_ARGUMENT;
            }

            throw exceptionType.newInstance(getMessage(message, args,
                    "[Assertion failed] - the argument is required; it must not be null"));
        }

        return object;
    }

    public static <T> T assertNull(T object, ExceptionType exceptionType, String message, Object... args) {
        if(object != null) {
            if(exceptionType == null) {
                exceptionType = ExceptionType.ILLEGAL_ARGUMENT;
            }

            throw exceptionType.newInstance(getMessage(message, args,
                    "[Assertion failed] - the object argument must be null"));
        }

        return object;
    }

    public static <T> T assertNull(T object) {
        return assertNull(object, null, null, (Object[])null);
    }

    public static <T> T assertNull(T object, String message, Object... args) {
        return assertNull(object, null, message, args);
    }

    public static void assertTrue(boolean eval, ExceptionType exceptionType, String message, Object... args) {
        if(!eval) {
            if(exceptionType == null) {
                exceptionType = ExceptionType.ILLEGAL_ARGUMENT;
            }

            throw exceptionType.newInstance(getMessage(message, args,
                    "[Assertion failed] - the expression must be true"));
        }
    }

    public static void assertTure(boolean eval) {
        assertTrue(eval, null, null, (Object[])null);
    }

    public static void assertTrue(boolean eval, String message, Object... args) {
        assertTrue(eval, null, message, args);
    }

    public static void unreachableCode(String message, Object... args) {
        ExceptionType.UNREACHABLE_CODE.newInstance(getMessage(message,args,
                "[Assertion failed] - the code is expected as unreachable"));
    }

    public static void unreachableCode() {
        unreachableCode(null, (Object[])null);
    }

    public static void unexpectedException(Throwable e, String message, Object... args) {
        RuntimeException exception = ExceptionType.UNEXPECTED_FAILURE.newInstance(getMessage(message,
                args,"[Assertion failed] - unexpected exception is thrown"));
        exception.initCause(e);
        throw exception;
    }

    public static void unexpectedException(Throwable e) {
        unexpectedException(e, null, (Object[])null);
    }

    public static void fail(String message, Object... args) {
        throw ExceptionType.UNEXPECTED_FAILURE.newInstance(getMessage(message,
                args, "[Assertion failed] - unexpected failure"));
    }

    public static void fail() {
        fail(null, (Object[])null);
    }

    public static void unsupportedOperation(String message, Object... args) {
        throw ExceptionType.UNSUPPORTED_OPERATION.newInstance(getMessage(message,
                args, "[Assertion failed] - unsupported operation or unimplemented function"));
    }

    public static void unsupportedOperation() {
        unsupportedOperation(null, (Object[])null);
    }

    private static String getMessage(String message, Object[] args, String defaultMessage) {
        if (message == null) {
            message = defaultMessage;
        }

        if (args == null || args.length == 0) {
            return message;
        }

        return String.format(message, args);
    }

    public static enum ExceptionType {
        ILLEGAL_ARGUMENT {
            @Override
            RuntimeException newInstance(String message) {
                return new IllegalArgumentException(message);
            }
        },

        ILLEGAL_STATE {
            @Override
            RuntimeException newInstance(String message) {
                return new IllegalStateException(message);
            }
        },

        NULL_POINT {
            @Override
            RuntimeException newInstance(String message) {
                return new NullPointerException(message);
            }
        },

        UNREACHABLE_CODE {
            @Override
            RuntimeException newInstance(String message) {
                return new UnreachableCodeException(message);
            }
        },

        UNEXPECTED_FAILURE {
            @Override
            RuntimeException newInstance(String message) {
                return new UnexpectedFailureException(message);
            }
        },

        UNSUPPORTED_OPERATION {
            @Override
            RuntimeException newInstance(String message) {
                return new UnsupportedOperationException(message);
            }
        };

        abstract RuntimeException newInstance(String message);
    }
}
