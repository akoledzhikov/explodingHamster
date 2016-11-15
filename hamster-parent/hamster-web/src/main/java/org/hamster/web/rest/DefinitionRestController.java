package org.hamster.web.rest;


import java.util.ArrayList;
import java.util.List;

import org.hamster.model.def.Definition;
import org.hamster.service.DefinitionServiceImpl;
import org.hamster.web.dto.DefinitionDTO;
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
@RequestMapping("api/v1/definitions")
public class DefinitionRestController
{
    @Autowired
    private DefinitionServiceImpl ds;


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Iterable<DefinitionDTO>> findAll()
    {
        Iterable<Definition> defs = ds.findAll();
        List<DefinitionDTO> result = new ArrayList<>();
        for (Definition d : defs)
        {
            result.add(new DefinitionDTO(d));
        }

        return new ResponseEntity<Iterable<DefinitionDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/findSome", method = RequestMethod.POST)
    public ResponseEntity<Iterable<DefinitionDTO>> findSome(@RequestBody MultiIdBasedRequest request)
    {
        Iterable<Definition> defs = ds.findAll(request.getIds());
        List<DefinitionDTO> result = new ArrayList<>();
        for (Definition d : defs)
        {
            result.add(new DefinitionDTO(d));
        }
        return new ResponseEntity<Iterable<DefinitionDTO>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/findOne", method = RequestMethod.POST)
    public ResponseEntity<DefinitionDTO> findOne(@RequestBody SingleIdBasedRequest request)
    {
        Definition result = ds.findOne(request.getId());
        return new ResponseEntity<DefinitionDTO>(new DefinitionDTO(result), HttpStatus.OK);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<DefinitionDTO> save(@RequestBody DefinitionDTO request)
    {
        // TODO actual saving
        return new ResponseEntity<DefinitionDTO>(request, HttpStatus.OK);
    }
}
