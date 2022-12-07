package com.kodlamaio.invoiceService.business.concretes;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.event.PaymentCreatedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.common.utilities.results.DataResult;
import com.kodlamaio.common.utilities.results.SuccessDataResult;
import com.kodlamaio.invoiceService.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceService.business.responses.create.CreateInvoiceResponses;
import com.kodlamaio.invoiceService.dataAccess.InvoiceRepository;
import com.kodlamaio.invoiceService.entities.Invoice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
	//REFACTOR YAPILACAK
	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;

	@Override
	public  DataResult<Invoice> add(PaymentCreatedEvent createInvoiceRequest) {
	//	Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		Invoice invoice =new Invoice();
		invoice.setId(UUID.randomUUID().toString());
		invoice.setRentalId(createInvoiceRequest.getRentalId());
		this.invoiceRepository.save(invoice);
	CreateInvoiceResponses invoiceResponses = this.modelMapperService.forResponse().map(invoice,
				CreateInvoiceResponses.class);
		return new SuccessDataResult<Invoice>(invoice);
	}

}
