package com.herd.API.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CowService {

    private final CowRepository cowRepository;

    @Autowired
    public CowService(CowRepository cowRepository) {
        this.cowRepository = cowRepository;
    }

    @GetMapping
    public String getCows() {
        List<Cow> findAllCow = cowRepository.findAll();
        String returnString = "[\n";
        for (Cow cow : findAllCow) {
            returnString += cow.toString();
        }
        returnString += "]";
        returnString = returnString.replaceAll("\\n", "<br>");
        return returnString;
        //return cowRepository.findAll();
    }

    public void addNewCow(Cow cow) {
        Optional<Cow> cowNumberOptional = cowRepository.findCowByCowNumber(cow.getCowNumber());
        Optional<Cow> collarIdOptional = cowRepository.findCowByCollarId(cow.getCollarId());
        String collarStatus = cow.getCollarStatus();
        if (cowNumberOptional.isPresent() && collarIdOptional.isPresent()) {
            throw new IllegalStateException("Collar Id and Cow Number are both taken!");
        } else if (cowNumberOptional.isPresent()) {
            throw new IllegalStateException("Cow Number taken!");
        } else if (collarIdOptional.isPresent()) {
            throw new IllegalStateException("Collar Id taken!");
        } else if (collarStatus.equals("Healthy") || collarStatus.equals("Broken")) {
            cowRepository.save(cow);
        } else {
            throw new IllegalStateException("Collar Status is invalid!");
        }
    }

    public void deleteCow(String cowId) {
        boolean exists = cowRepository.existsById(cowId);
        if (!exists) {
            throw new IllegalStateException("Cow: " + cowId + " does not exist in Database.");
        } else {
            cowRepository.deleteById(cowId);
        }
    }

    @Transactional
    public String updateCow(String cowId, Long collarId, Long cowNumber, String collarStatus) {
        Cow cow = cowRepository.findById(cowId).orElseThrow(() -> new IllegalStateException("Cow with id " + cowId + " not in Database!"));
        if (collarId == null) {} else {
            if (collarId > 0 && !Objects.equals(cow.getCollarId(), Math.toIntExact(collarId))) {
                Optional<Cow> collarIdOptional = cowRepository.findCowByCollarId(Math.toIntExact(collarId));
                if (collarIdOptional.isPresent()) {
                    throw new IllegalStateException("Collar Id is already taken!");
                } else {
                    cow.setCollarId(Math.toIntExact(collarId));
                }
            }
        }
        if (cowNumber == null) {} else {
            if (cowNumber > 0 && !Objects.equals(cow.getCowNumber(), Math.toIntExact(cowNumber))) {
                Optional<Cow> cowNumberOptional = cowRepository.findCowByCowNumber(Math.toIntExact(cowNumber));
                if (cowNumberOptional.isPresent()) {
                    throw new IllegalStateException("Cow Number is already taken!");
                } else {
                    cow.setCowNumber(Math.toIntExact(cowNumber));
                }
            }
        }
        if (collarStatus != null && (collarStatus.equals("Healthy") || collarStatus.equals("Broken"))) {
            cow.setCollarStatus(collarStatus);
        }
        return cow.responseString();
    }
}
