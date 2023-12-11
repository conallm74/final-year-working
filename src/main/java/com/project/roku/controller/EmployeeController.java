package com.project.roku.controller;

import com.project.roku.entity.Employee;
import com.project.roku.services.employee_services.EmployeeRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeRepoService employeeService;

    // make consructors for injectin

    // public EmployeeController(){}
    @Autowired
    public EmployeeController(EmployeeRepoService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }

    // get the employees from the database
    @GetMapping("/employeeList")
    public String listEmployees(Model theModel) {
        List<Employee> theEmployees = employeeService.findAll();

        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        // create the model attribute to bind the form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);
        // Thymeleaf template will acceess this data (employee) for binding the form data
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        // save the employee
        employeeService.save(theEmployee);
        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }


    // controller code to update the employee info
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){
        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // set the employee in the model to prepopulate the model/form
        theModel.addAttribute("employee", theEmployee);
        // send over to our form

        return "employees/employee-form";
    }
    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int theId){
        // delete the employee data
        employeeService.deleteById(theId);
        // re-direct to the employee/list
        return "redirect:/employees/list";
    }
}










