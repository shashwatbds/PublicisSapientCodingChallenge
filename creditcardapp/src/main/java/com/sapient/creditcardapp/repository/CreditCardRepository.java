package com.sapient.creditcardapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sapient.creditcardapp.model.CreditCard;

@RepositoryRestResource
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
