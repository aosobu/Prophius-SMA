package com.spiritcoderz.prophiusassessmentprepup.users.exception;

import org.springframework.stereotype.Component;

@Component
public class CustomProphiusException extends RuntimeException{
    @java.io.Serial
    private static final long serialVersionUID = 2256477558398496007L;

    public CustomProphiusException() {
        super();
    }

    public CustomProphiusException(String s) {
        super(s);
    }
}
