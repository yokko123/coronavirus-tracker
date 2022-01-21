package io.covidtracker.coronavirustracker.controllers;

import io.covidtracker.coronavirustracker.Services.CoronaVirusDataService;
import io.covidtracker.coronavirustracker.models.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;
    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats= coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getTotalCasesToday()).sum();
        model.addAttribute("locationstats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        return "home";
    }
}
