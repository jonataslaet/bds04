package com.devsuperior.bds04.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private EventRepository eventRepository;

	@Transactional(readOnly = true)
	public Page<EventDTO> findAllPaged(Pageable pageable) {
		Page<Event> eventsPaged = eventRepository.findAll(pageable);
		return eventsPaged.map(c -> new EventDTO(c));
	}
	
	public EventDTO insert(EventDTO eventDTO) {
		Event event = new Event();
		copyDtoToEntity(eventDTO, event);
		event = eventRepository.save(event);
		return new EventDTO(event);
	}

	private void copyDtoToEntity(EventDTO eventDTO, Event event) {
		
		Optional<City> cityOptional = cityRepository.findById(eventDTO.getCityId());
		
		event.setCity(cityOptional.get());
		event.setDate(eventDTO.getDate());
		event.setId(eventDTO.getId());
		event.setName(eventDTO.getName());
		event.setUrl(eventDTO.getUrl());
	}


}
