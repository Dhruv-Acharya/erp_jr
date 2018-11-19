package com.jalaramrakhi.erpjr.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Barcode Item Detail Information Missing!")
public class BarcodeItemDetailsMissingInformationException extends RuntimeException {
}
