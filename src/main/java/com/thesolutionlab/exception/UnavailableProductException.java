package com.thesolutionlab.exception;

public class UnavailableProductException extends RuntimeException {

    public UnavailableProductException( String reason )
    {
        super(reason);
    }
}
