package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;
import static org.launchcode.techjobs.mvc.models.JobData.findAll;
import static org.launchcode.techjobs.mvc.models.JobData.findByColumnAndValue;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search") //everything in the search controller is going to map to local host 8080/search
public class SearchController {

    @GetMapping(value = "")//This makes sure it maps 8080/search only
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search"; //"search" is a html address, all the return take it to a html address
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
@PostMapping(value = "results")
public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
    ArrayList<Job> jobs = new ArrayList<>();
    if(searchTerm.toLowerCase() == "all" || searchTerm == ""){// if the user puts all or if its blank it will populate with all job data
        jobs = JobData.findAll();


    }else {
        jobs = JobData.findByColumnAndValue(searchType, searchTerm);
    }
    model.addAttribute("columns", columnChoices);// this pass it into the view
    model.addAttribute("jobs",jobs);


        return "search";

    }
 }
