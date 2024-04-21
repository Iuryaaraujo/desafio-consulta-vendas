package com.devsuperior.dsmeta.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true) //Operação usada para leitura
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true) // readOnly somente de leitura
	public Page<SaleMinDTO> getReport(Pageable pageable) {
		Page<Sale> result = repository.findAll(pageable);
		return result.map(x -> new SaleMinDTO(x));
	}

	@Transactional(readOnly = true) 
	public Page<SaleMinDTO> getSummary(Pageable pageable) {
		Page<Sale> result = repository.findAll(pageable);
		return result.map(x -> new SaleMinDTO(x));
	}
}
