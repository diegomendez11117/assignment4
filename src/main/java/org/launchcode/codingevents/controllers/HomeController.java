package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Employer;
import org.launchcode.codingevents.models.Job;
import org.launchcode.codingevents.models.Skill;
import org.launchcode.codingevents.models.data.SkillRepository;
import org.launchcode.codingevents.models.data.EmployerRepository;
import org.launchcode.codingevents.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers",employerRepository.findAll());
        model.addAttribute("skills",skillRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");

            return "add";
        }

        Employer employer = employerRepository.findById(employerId).orElse(new Employer());
        newJob.setEmployer(employer);
        List<Skill> aSkill = (List<Skill>)skillRepository.findAllById(skills);
        newJob.setSkills(aSkill);
        jobRepository.save(newJob);

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Job job = jobRepository.findById(jobId).orElse(new Job());
        model.addAttribute("job", job);
        return "view";
    }

    @Autowired
    public EmployerRepository employerRepository;

    @Autowired
    public SkillRepository skillRepository;

    @Autowired
    public JobRepository jobRepository;


}
