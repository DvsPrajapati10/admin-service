package com.tcs.srs.adminservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusRouteService {

    @Autowired
    public BusRouteRepository busRouteRepository;

    public List<BusRoute> getAllBusRoute(){
        return  busRouteRepository.findAll();
    }

    public Optional<BusRoute> getBusRouteByBusNumber(Integer busNumber){
        return busRouteRepository.findById(busNumber);
    }

    public BusRoute createBusRoute(BusRoute busRoute){
        return  busRouteRepository.save(busRoute);
    }

    public int deleteBusRoute(Integer busNumber){
        return busRouteRepository.deleteRouteByBusNumber(busNumber);
    }
}
