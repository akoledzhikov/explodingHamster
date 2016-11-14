package org.hamster.web.rest;


import org.hamster.model.runtime.ContainerInstance;
import org.hamster.service.ContainterInstanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/containers")
public class ContainerInstanceRestController
{
    @Autowired
    private ContainterInstanceServiceImpl containerService;


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Iterable<ContainerInstance> findAll()
    {
        return containerService.findAll();
    }
}
