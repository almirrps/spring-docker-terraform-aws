package com.almirrps.contactbook.service;

import com.almirrps.contactbook.entity.Contact;
import com.almirrps.contactbook.repository.ContactbookRepository;
import com.almirrps.contactbook.service.client.ViaCepClient;
import com.almirrps.contactbook.service.client.response.AddressResponse;
import com.almirrps.contactbook.service.interfaces.ContactbookService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class ContactbookServiceImpl implements ContactbookService {

    private final ContactbookRepository repository;
    private final ViaCepClient viaCepClient;

    @Override
    public Contact save(Contact contact) {
        log.info("M save, contact={}, NEW", contact);
        try {
            AddressResponse address = viaCepClient.getAddress(contact.getCep());
            contact.setAddress(address);
            log.info("M save, contact={}, SAVING", contact);
            return repository.save(contact);
        } catch (Exception ex) {
            log.info("Cannot find the address with cep={}, ERROR.", contact.getCep());

            throw new IllegalArgumentException("Cannot find the address with cep: " + contact.getCep());
        }
    }

    @Override
    public Optional<Contact> getByName(String name) {
        log.info("M getByName, name={}, NEW", name);
        return repository.findByName(name);
    }

}