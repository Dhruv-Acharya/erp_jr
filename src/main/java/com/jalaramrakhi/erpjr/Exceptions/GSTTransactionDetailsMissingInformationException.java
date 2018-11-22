package com.jalaramrakhi.erpjr.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "GST Transaction Details Information Missing!")
public class GSTTransactionDetailsMissingInformationException extends RuntimeException {
}
