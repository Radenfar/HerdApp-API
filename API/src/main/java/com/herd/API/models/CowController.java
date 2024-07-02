package com.herd.API.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cows")
public class CowController {

    private final CowService cowService;

    @Autowired
    public CowController(CowService cowService) {
        this.cowService = cowService;
    }

    @GetMapping
    public String getCows() {
        return cowService.getCows();
    }

    @PostMapping
    public String registerNewCow(@RequestBody Cow cow) {
        cowService.addNewCow(cow);
        return cow.responseString();
    }

    @DeleteMapping(path = {"cowId"})
    public void deleteCow(@PathVariable("cowId") String cowId) {
        cowService.deleteCow(cowId);
    }

    @PutMapping(path="{cowId}")
    public String updateCow(
            @PathVariable("cowId") String cowId,
            @RequestParam(required = false) Long collarId,
            @RequestParam(required = false) Long cowNumber,
            @RequestParam(required = false) String collarStatus) {
        String cowString = cowService.updateCow(cowId, collarId, cowNumber, collarStatus);
        return cowString;
    }
}
