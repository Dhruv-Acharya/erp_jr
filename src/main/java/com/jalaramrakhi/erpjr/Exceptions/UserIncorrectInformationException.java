package com.jalaramrakhi.erpjr.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Incorrect user information")
public class UserIncorrectInformationException extends RuntimeException {
}
