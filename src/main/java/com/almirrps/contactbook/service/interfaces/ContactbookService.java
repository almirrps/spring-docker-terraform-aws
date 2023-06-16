package com.almirrps.contactbook.service.interfaces;

import com.almirrps.contactbook.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactbookService {

    Contact save(Contact contact);

    List<Contact> getAll();

    Optional<Contact> getByName(String name);

}