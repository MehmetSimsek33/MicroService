package com.kodlamaio.invoiceService.business.abstracts;

import com.kodlamaio.common.event.PaymentCreatedEvent;
import com.kodlamaio.common.event.RentalCreateInvoice;
import com.kodlamaio.invoiceService.business.responses.create.CreateInvoiceResponses;
import com.kodlamaio.invoiceService.entities.Invoice;

public interface InvoiceService {

	Invoice add(PaymentCreatedEvent createInvoiceRequest);
}
