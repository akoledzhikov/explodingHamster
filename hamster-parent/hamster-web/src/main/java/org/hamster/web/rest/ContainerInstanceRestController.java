package org.hamster.web.rest;


import java.util.ArrayList;
import java.util.List;

import org.hamster.model.runtime.ContainerInstance;
import org.hamster.service.ContainterInstanceServiceImpl;
import org.hamster.web.dto.ContainerInstanceDTO;
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
@RequestMapping("api/v1/containers")
public class ContainerInstanceRestController
{
    @Autowired
    private ContainterInstanceServiceImpl containerService;


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ContainerInstanceDTO>> findAll()
    {
        Iterable<ContainerInstance> containers = containerService.findAll();
        List<ContainerInstanceDTO> result = new ArrayList<>();
        for (ContainerInstance ci : containers)
        {
            result.add(new ContainerInstanceDTO(ci));
        }
        
        return new ResponseEntity<Iterable<ContainerInstanceDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/findSome", method = RequestMethod.POST)
    public ResponseEntity<Iterable<ContainerInstanceDTO>> findSome(@RequestBody MultiIdBasedRequest request)
    {
        Iterable<ContainerInstance> containers = containerService.findAll(request.getIds());
        List<ContainerInstanceDTO> result = new ArrayList<>();
        for (ContainerInstance ci : containers)
        {
            result.add(new ContainerInstanceDTO(ci));
        }
        return new ResponseEntity<Iterable<ContainerInstanceDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/findOne", method = RequestMethod.POST)
    public ResponseEntity<ContainerInstanceDTO> findOne(@RequestBody SingleIdBasedRequest request)
    {
        ContainerInstance result = containerService.findOne(request.getId());
        return new ResponseEntity<ContainerInstanceDTO>(new ContainerInstanceDTO(result), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ContainerInstanceDTO> save(@RequestBody ContainerInstanceDTO request)
    {
        // TODO actual saving
        return new ResponseEntity<ContainerInstanceDTO>(request, HttpStatus.OK);
    }
}
