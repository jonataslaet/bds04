package com.devsuperior.bds04.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> cities = cityRepository.findAllOrderByName();
		return cities.stream().map(c -> new CityDTO(c)).collect(Collectors.toList());
	}

	public CityDTO insert(CityDTO cityDTO) {
		City city = new City();
		copyDtoToEntity(cityDTO, city);
		city = cityRepository.save(city);
		return new CityDTO(city);
	}
	
	private void copyDtoToEntity(CityDTO cityDTO, City city) {
		city.setName(cityDTO.getName());
	}
}
