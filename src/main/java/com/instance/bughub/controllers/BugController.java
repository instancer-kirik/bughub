package com.instance.bughub.controllers;



import com.instance.bughub.DTO.CommentDTO;
import com.instance.bughub.DTO.CommentDisplayDTO;
import com.instance.bughub.entities.Bug;
import com.instance.bughub.entities.Comment;
import com.instance.bughub.entities.User;
import com.instance.bughub.repositories.BugRepo;
import com.instance.bughub.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bugs")
public class BugController {
    @Autowired
    BugRepo bugRepo;
    @Autowired
    UserRepo userRepo;
   // @Autowired TeacherRepo teacherRepo;
    @GetMapping("/allBugs")
    public ResponseEntity<List<Bug>> getBugs(){
        List<Bug> bugs =bugRepo.findAll();
        return new ResponseEntity<>(bugs, HttpStatus.OK);
    }
    @GetMapping("/{bugId}")
    public ResponseEntity<Bug> getBug(@PathVariable int bugId){
        Bug bug = bugRepo.getReferenceById(bugId);
        return new ResponseEntity<>(bug, HttpStatus.OK);
    }
    @PostMapping("/addBug")
    public ResponseEntity<Bug> createBug(@RequestBody Bug bug){
        Bug bug1 = bugRepo.save(bug);
        return new ResponseEntity<>(bug1,HttpStatus.CREATED);
    }
    @PutMapping("/{bugId}/users/{userId}")
    public ResponseEntity<Bug> accountAfflictUserToBug(@PathVariable int bugId,@PathVariable int userId){
        Bug bug = bugRepo.findById(bugId).get();
        User user = userRepo.findById(userId).get();
        bug.afflictUser(user);
        Bug bug1 = bugRepo.save(bug);
        return new ResponseEntity<>(bug1,HttpStatus.ACCEPTED);
//user.setBugs(user.getBugs().add(bug));
    }
    @DeleteMapping("/delete/{bugId}")
    ResponseEntity<?> deleteBug(@PathVariable int bugId){

        bugRepo.deleteById(bugId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/{bugId}/comments")
    public ResponseEntity<List<CommentDisplayDTO>> getCommentsForBug(@PathVariable Integer bugId) {
        Bug bug = bugRepo.findById(bugId)
                .orElseThrow(() -> new RuntimeException("Bug not found"));

        List<CommentDisplayDTO> commentDTOs = bug.getComments().stream()
                .map(this::convertToDisplayDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(commentDTOs);
    }

    private CommentDisplayDTO convertToDisplayDTO(Comment comment) {
        CommentDisplayDTO dto = new CommentDisplayDTO();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setUsername(comment.getUser().getUsername()); // Assumes getUser() returns the User object
        dto.setCreatedAt(comment.getCreatedAt()); // Assumes there is a createdAt field in Comment

        return dto;
    }


//    @PutMapping("/{bugId}/teachers/{teacherId}")
//    Bug assignTeacherToBug(@PathVariable int bugId, @PathVariable int teacherId){
//        Bug bug = bugRepo.findById(bugId).get();
//        Teacher teacher = teacherRepo.findById(teacherId).get();
//        bug.assignTeacher(teacher);
//        return bugRepo.save(bug);
//    }
}
