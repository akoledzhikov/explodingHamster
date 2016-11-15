package org.hamster.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.hamster.model.user.DefinitionAvailability;
import org.hamster.model.user.User;
import org.hamster.service.DefinitionAvailabilityServiceImpl;
import org.hamster.service.UserServiceImpl;
import org.hamster.web.dto.DefinitionAvailabilityDTO;
import org.hamster.web.dto.request.SingleIdBasedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/defAvailabilities")
public class DefinitionAvailabilityRestController
{
    @Autowired
    private DefinitionAvailabilityServiceImpl das;
    
    @Autowired
    private UserServiceImpl us;
    
    @RequestMapping(value = "/findByUser", method = RequestMethod.POST)
    public ResponseEntity<Iterable<DefinitionAvailabilityDTO>> findSome(@RequestBody SingleIdBasedRequest request)
    {
        User user = us.findOne(request.getId());
        Iterable<DefinitionAvailability> aval = das.findByUser(user);
        List<DefinitionAvailabilityDTO> result = new ArrayList<>();
        for (DefinitionAvailability da : aval)
        {
            result.add(new DefinitionAvailabilityDTO(da));
        }
        return new ResponseEntity<Iterable<DefinitionAvailabilityDTO>>(result, HttpStatus.OK);
    }
}
