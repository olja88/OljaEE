package com.oljalatinovic.oljaee.exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
@ApplicationException(rollback = true)
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}