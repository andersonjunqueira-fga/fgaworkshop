package br.com.neotech.fgaworkshop.interfaces.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class IndexRest {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> ok() {
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

}

