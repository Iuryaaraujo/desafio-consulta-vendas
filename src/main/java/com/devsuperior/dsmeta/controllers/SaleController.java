package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(
			@RequestParam(value = "minDate"  , defaultValue = "") String dateMin,
			@RequestParam(value = "maxDate"  , defaultValue = "") String dateMax,
			@RequestParam(value = "name"     , defaultValue = "") String name,
			Pageable pageable) {

		Page<SaleMinDTO> sales = service.findReport(dateMin, dateMax, name, pageable);

		return ResponseEntity.ok().body(sales);

	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SellerMinDTO>> getSummary(
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate,
			Pageable pageable) {

		Page<SellerMinDTO> result = service.findSumary(minDate, maxDate, pageable);

		return ResponseEntity.ok().body(result);
	}
}