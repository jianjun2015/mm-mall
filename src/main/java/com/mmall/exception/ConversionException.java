
package com.mmall.exception;


import java.text.MessageFormat;

/**
 * 意味着或{@link}
 * 抛出了异常
 *
 * @author wjj
 * @since 1.0
 */
public class ConversionException extends RuntimeException {

    private static final long serialVersionUID = -5569636259861281518L;

    public ConversionException() {
        super();
    }

    public ConversionException(Throwable caught) {
        super(caught);
    }

    public ConversionException(String pattern, Object... arguments) {
        super(MessageFormat.format(pattern, arguments));
    }

    public ConversionException(Throwable caught, String pattern, Object... arguments) {
        super(MessageFormat.format(pattern, arguments), caught);
    }
}
