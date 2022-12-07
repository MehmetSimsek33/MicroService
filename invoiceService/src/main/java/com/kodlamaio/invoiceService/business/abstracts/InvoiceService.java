package com.kodlamaio.invoiceService.business.abstracts;

import com.kodlamaio.common.event.PaymentCreatedEvent;
import com.kodlamaio.common.event.RentalCreateInvoice;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.invoiceService.business.responses.create.CreateInvoiceResponses;
import com.kodlamaio.invoiceService.entities.Invoice;

public interface InvoiceService {

	 DataResult<Invoice> add(PaymentCreatedEvent createInvoiceRequest);
}
