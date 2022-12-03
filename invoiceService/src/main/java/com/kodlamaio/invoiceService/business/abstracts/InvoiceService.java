package com.kodlamaio.invoiceService.business.abstracts;

import com.kodlamaio.common.event.RentalCreateInvoice;
import com.kodlamaio.invoiceService.business.responses.create.CreateInvoiceResponses;

public interface InvoiceService {

	CreateInvoiceResponses add(RentalCreateInvoice createInvoiceRequest);
}
