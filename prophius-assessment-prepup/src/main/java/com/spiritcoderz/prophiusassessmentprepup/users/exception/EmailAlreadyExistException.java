package com.spiritcoderz.prophiusassessmentprepup.users.exception;

import org.springframework.stereotype.Component;

@Component
public class EmailAlreadyExistException extends Exception {
    @java.io.Serial
    private static final long serialVersionUID = 2796477558314496007L;

    public EmailAlreadyExistException() {
        super();
    }

    public EmailAlreadyExistException(String s) {
        super(s);
    }
}
