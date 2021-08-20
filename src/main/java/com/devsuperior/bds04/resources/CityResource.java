package com.devsuperior.bds04.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.services.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityResource {

	@Autowired
	private CityService cityService;

	@GetMapping
	ResponseEntity<List<CityDTO>> findAll() {
		List<CityDTO> citiesDTO = cityService.findAll();

		return ResponseEntity.ok().body(citiesDTO);
	}

	@PostMapping
	ResponseEntity<CityDTO> insert(@RequestBody @Valid CityDTO cityDTO) {
		CityDTO createdCityDTO = cityService.insert(cityDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCityDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(createdCityDTO);
	}
}
