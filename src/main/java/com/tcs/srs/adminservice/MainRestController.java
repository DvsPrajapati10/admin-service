package com.tcs.srs.adminservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MainRestController {

	@Autowired
	public BusRouteService busRouteService;

	@Cacheable(value = "bus_route", key = "#bus_number")
	@GetMapping("/get/all/busroute")
	public ResponseEntity<List<BusRoute>> getAllBusRoute() {
		return new ResponseEntity<>(busRouteService.getAllBusRoute(), HttpStatus.OK);
	}

	@Cacheable(value = "bus_route", key = "#bus_number")
	@GetMapping("/get/busroute/{busNumber}")
	public ResponseEntity<Optional<BusRoute>> getBusRouteByBusNumber(@PathVariable Integer busNumber) {
		return new ResponseEntity<>(busRouteService.getBusRouteByBusNumber(busNumber), HttpStatus.OK);
	}

	@PostMapping("/createRoute")
	public ResponseEntity<BusRoute> createBusRoute(@RequestBody BusRoute busRoute) {
		return new ResponseEntity<>(busRouteService.createBusRoute(busRoute), HttpStatus.CREATED);
	}

	@PutMapping("/updateRoute/{busNumber}")
	public ResponseEntity<BusRoute> updateBusRoute(@PathVariable String busNumber, @RequestBody BusRoute busRoute) {
		if (busRouteService.getBusRouteByBusNumber(Integer.valueOf(busNumber)).isPresent()) {
			return new ResponseEntity<>(busRouteService.createBusRoute(busRoute), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteRoute/{busNumber}")
	public ResponseEntity<Integer> deleteRouteByBusNumber(@PathVariable String busNumber) {
		if (busRouteService.getBusRouteByBusNumber(Integer.valueOf(busNumber)).isPresent()) {
			return new ResponseEntity<>(busRouteService.deleteBusRoute(Integer.valueOf(busNumber)), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
