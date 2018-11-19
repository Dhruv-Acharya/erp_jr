package com.jalaramrakhi.erpjr.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Account Information Missing!")
public class AccountMissingInformationException extends RuntimeException {
}
