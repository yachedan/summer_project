package com.summer.project.controller.ui;

import com.summer.project.model.*;
import com.summer.project.service.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
    @author user
    @project summer_project
    @class JournalUIController
    @version 1.0.0
    @since 8/28/2021 - 11.42
*/
@Service
@RequestMapping("ui/journal")
public class JournalUIController {
    private final JournalService journalService;
    private final StudentService studentService;
    private final ConductService conductService;
    private final ScheduleService scheduleService;
    private final GroupService groupService;

    public JournalUIController(JournalService journalService, StudentService studentService,
                               ConductService conductService, ScheduleService scheduleService,
                               GroupService groupService){
        this.journalService = journalService;
        this.studentService = studentService;
        this.conductService = conductService;
        this.scheduleService = scheduleService;
        this.groupService = groupService;
    }

    /*@GetMapping("/get/all")
    public String getEntries(HttpServletRequest request, Model model){
        int page = 0;
        int size = 10;
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page"))-1;
        }
        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("entries",journalService.getAll(PageRequest.of(page,size)));
        return "get-journal";
    }*/
    @GetMapping("/get/all")
    public String getEntries(HttpServletRequest request, Model model){
        model.addAttribute("groups", groupService.getAll());
        return "get-journal";
    }
    @GetMapping("/get/{groupId}")
    public String getEntriesByGroup(@PathVariable("groupId")Long groupId, HttpServletRequest request, Model model){
        List<Schedule> entries = scheduleService.getByGroup(groupId);
        Group group = entries.get(0).getGroup();
        Schedule[][] array = new Schedule[9][5];
        for(int i=0; i<9; i++)
            for(int j=0; j<5; j++)
                array[i][j] = new Schedule(null, group, new Teaching(null, new Subject(null, "-",null,null)
                        ,new Teacher(null, "-",null,null,null,null, null),
                        null,null),j,i,null,null,null);
        for(Schedule entry : entries){
            array[entry.getTime()][entry.getDay()] = entry;
        }
        for(int i = 0; i < 9; i++){
            model.addAttribute("row"+i, array[i]);
        }
        return "get-journal-group";
    }
    @GetMapping("/table/{groupId}/{time}/{day}/{id}")
    public String showTable(@PathVariable("id") Long id,@PathVariable("groupId") Long groupId,@PathVariable("time")
            int time, @PathVariable("day") int day, Journal journal,HttpServletRequest request,
                            ListWrapper list, Model model) {

        List<Student> students = studentService.getAllByGroup(groupId);


        List<Conduct> conductList = conductService.getAllBySchedule(id);
        for(Conduct conduct : conductList) {
            for (Student student : students) {
                var temp = journalService.getByStudentAndDate(student.getId(), conduct.getDate());
                if (temp != null) {
                    if (!temp.getConduct().isConducted())
                        journalService.delete(temp.getId());
                    continue;
                }

                Journal entry = new Journal(null, student, conduct, null, null);
                journalService.create(entry);
            }
        }

        int page = 0;
        int size = 5*students.size();
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page"))-1;
        }
        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("entries", journalService.getAllBySchedule(id,PageRequest.of(page,size)));
        List<Journal> entries = journalService.getAllBySchedule(id,PageRequest.of(page,size)).getContent();

        Journal[][] array = new Journal[students.size()][];
        boolean f = true;
        for(int i = 0; i < students.size(); i++){
            int finalI = i;
            List<Journal> entriesFiltered = entries.stream()
                    .filter(entry -> entry.getStudent() == students.get(finalI))
                    .sorted(Comparator.comparing(o -> o.getConduct().getDate()))
                    .collect(Collectors.toList());
            array[i] = new Journal[entriesFiltered.size()];
            for(int j = 0; j < entriesFiltered.size(); j++){
                array[i][j] = entriesFiltered.get(j);
            }
            if (f) {
                List<LocalDate> dates = entriesFiltered.stream()
                        .map(el->el.getConduct().getDate()).distinct().collect(Collectors.toList());
                model.addAttribute("dates",dates);
                f = false;
            }
        }
        /*for(Student student : students){
            list.setTempJournalList(entries.stream()
                    .filter(entry->entry.getStudent() == student).collect(Collectors.toList()));
            for(int i = 0; i < list.getTempJournalList().size(); i++){
                model.addAttribute("entry"+list.getTempJournalList().get(i).getStudent().getId()+i, list
                        .getTempJournalList().get(i));
            }
            if (f) {
                List<LocalDate> dates = list.getTempJournalList().stream()
                        .map(Journal::getDate).distinct().collect(Collectors.toList());
                model.addAttribute("dates",dates);
                f = false;
            }
            model.addAttribute("row"+student.getId(),list);
        }

        for(Journal entry : entries){
            model.addAttribute("journal"+entry.getStudent(),entry);
        }*/
        list.setTempJournalArray(array);
        model.addAttribute("list",list);
        model.addAttribute("students", students);
        model.addAttribute(id);
        model.addAttribute(groupId);
        model.addAttribute(day);
        model.addAttribute(time);
        model.addAttribute("journal", journal);
        return "table-journal";
    }
    /*@RequestMapping("/table/{groupId}/{time}/{day}/{id}/add")
    public String addColumn(@PathVariable("id") Long id, @PathVariable("groupId") Long groupId, @PathVariable("time")
            int time, @PathVariable("day") int day, Journal journal,BindingResult bindingResult, Model model) {

        List<Student> students = studentService.getAllByGroup(groupId);
        for(Student student : students){
            if(journalService.getByStudentAndDate(student.getId(), journal.getDate()) != null)
                continue;
            Journal entry = new Journal(false,journal.getDate(),student,scheduleService.getById(id),
                    null,null);
            journalService.create(entry);
        }

        return "redirect:/ui/journal/table/"+groupId+"/"+time+"/"+day+"/"+id;
    }*/
    @RequestMapping("/table/{groupId}/{time}/{day}/{id}/change")
    public String changeAttended(@PathVariable("id") Long id, @PathVariable("groupId") Long groupId, @PathVariable("time")
            int time, @PathVariable("day") int day, ListWrapper list,BindingResult bindingResult, Model model) {

        var array = list.getTempJournalArray();
        for(Journal[] row : array)
            for(Journal journal : row){
                String value = journal.getValue();
                if(value.equals("0") || !value.chars().allMatch(Character::isDigit))
                    value = "н";
                journal = journalService.getById(journal.getId());
                journal.setValue(value);
                journalService.update(journal);
            }

        return "redirect:/ui/journal/table/"+groupId+"/"+time+"/"+day+"/"+id;
    }
   /* @RequestMapping("/table/{groupId}/{time}/{day}/{id}/delete/{date}")
    public String deleteDate(@PathVariable("id") Long id, @PathVariable("groupId") Long groupId, @PathVariable("time")
            int time, @PathVariable("day") int day,
                             @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                             Model model) {

        journalService.deleteAllByGroupAndDate(groupId, date);
        return "redirect:/ui/journal/table/"+groupId+"/"+time+"/"+day+"/"+id;
    }*/

}
