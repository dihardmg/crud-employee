package com.dihardmg.employee.crudemployee.controller;

import com.dihardmg.employee.crudemployee.dao.EmployeeDao;
import com.dihardmg.employee.crudemployee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

/**
 * @author : Otorus
 * @since : 12/23/17
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping("/list")
    public ModelMap daftarEmployee(@PageableDefault(size = 5)Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null){
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("daftarEmployee", employeeDao.findByNamaContainingIgnoreCase(value, pageable));
        }else {
            return new ModelMap().addAttribute("daftarEmployee", employeeDao.findAll(pageable));
        }
    }

    @RequestMapping("/hapus")
    public String hapus (@RequestParam("id") String id){
        employeeDao.delete(id);
        return "redirect:list";
    }

    @GetMapping("/form")
    public ModelMap tapilkanForm(@RequestParam(value = "id",required = false)Employee employee){
        if(employee == null){
            employee = new Employee();
        }
        return new ModelMap("employee", employee);
    }


    @PostMapping("/form")
    public String simpan(@ModelAttribute @Valid Employee employee, BindingResult errors, SessionStatus status){
        if (errors.hasErrors()){
            return "employee/form";
        }
        employeeDao.save(employee);
        status.setComplete();
        return "redirect:list";
    }

}
