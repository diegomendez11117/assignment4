package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Employer;
import org.launchcode.codingevents.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {


    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Employer");
            model.addAttribute(newEmployer);
            return "employers/add";
        }

        employerRepository.save(newEmployer);
        return "redirect:";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }

    @Autowired
    public EmployerRepository employerRepository;

    @GetMapping
    public String displayAllEmployers(Model model){
        model.addAttribute("title", "All Employers");
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }
}
