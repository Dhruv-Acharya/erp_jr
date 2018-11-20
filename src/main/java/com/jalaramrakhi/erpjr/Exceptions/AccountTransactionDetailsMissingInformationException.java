package com.jalaramrakhi.erpjr.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Account Transaction Detail Information Missing!")
public class AccountTransactionDetailsMissingInformationException extends RuntimeException {
}
