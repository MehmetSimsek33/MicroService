package com.kodlamaio.invoiceService.business.concretes;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.event.RentalCreateInvoice;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.responses.create.CreateInvoiceResponses;
import com.kodlamaio.invoiceService.dataAccess.InvoiceRepository;
import com.kodlamaio.invoiceService.entities.Invoice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {

	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;

	@Override
	public CreateInvoiceResponses add(RentalCreateInvoice createInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		this.invoiceRepository.save(invoice);
		CreateInvoiceResponses invoiceResponses = this.modelMapperService.forRequest().map(invoice,
				CreateInvoiceResponses.class);
		return invoiceResponses;
	}

}
