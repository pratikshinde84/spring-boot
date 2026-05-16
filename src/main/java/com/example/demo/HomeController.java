package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final StudentRepository studentRepository;

    public HomeController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute Student student, Model model) {
        studentRepository.save(student);
        model.addAttribute("successMessage", "Student details saved successfully.");
        model.addAttribute("student", new Student());
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }
}