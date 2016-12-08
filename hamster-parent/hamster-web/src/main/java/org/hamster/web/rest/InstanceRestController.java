package org.hamster.web.rest;


import java.util.ArrayList;
import java.util.List;

import org.hamster.model.runtime.Instance;
import org.hamster.service.InstanceServiceImpl;
import org.hamster.web.dto.InstanceDTO;
import org.hamster.web.dto.request.MultiIdBasedRequest;
import org.hamster.web.dto.request.SingleIdBasedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/instances")
public class InstanceRestController
{
    @Autowired
    private InstanceServiceImpl is;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Iterable<InstanceDTO>> findAll()
    {
        Iterable<Instance> instances = is.findAll();
        List<InstanceDTO> result = new ArrayList<>();
        for (Instance i : instances)
        {
            result.add(new InstanceDTO(i));
        }

        return new ResponseEntity<Iterable<InstanceDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/findSome", method = RequestMethod.POST)
    public ResponseEntity<Iterable<InstanceDTO>> findSome(@RequestBody MultiIdBasedRequest request)
    {
        Iterable<Instance> instances = is.findAll(request.getIds());
        List<InstanceDTO> result = new ArrayList<>();
        for (Instance i : instances)
        {
            result.add(new InstanceDTO(i));
        }
        return new ResponseEntity<Iterable<InstanceDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/findOne", method = RequestMethod.POST)
    public ResponseEntity<InstanceDTO> findOne(@RequestBody SingleIdBasedRequest request)
    {
        Instance result = is.findOne(request.getId());
        return new ResponseEntity<InstanceDTO>(new InstanceDTO(result), HttpStatus.OK);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<InstanceDTO> create(@RequestBody InstanceDTO request)
    {
        is.createNew(request.toEntity());
        return new ResponseEntity<InstanceDTO>(request, HttpStatus.OK);
    }

    
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public ResponseEntity<InstanceDTO> reject(@RequestBody InstanceDTO request)
    {
        is.reject(request.toEntity());
        return new ResponseEntity<InstanceDTO>(request, HttpStatus.OK);
    }
}
