package org.itstep;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vadim
 */

@RestController
public class KanbanController {
    private ArrayList<Kanban> kanbanList = new ArrayList<Kanban>();
    
    @RequestMapping("/kanban/new/{text}")
    public Kanban newKanban (@PathVariable("text") String text){
        Kanban kanban = new Kanban(text);
        kanbanList.add(kanban);
        return kanban;
    }
    @RequestMapping("/kanban/new/{text}/{name}")
    public Kanban newKanban (@PathVariable("name") String name, @PathVariable("text") String text){
        Kanban kanban = new Kanban(name, text);
        kanbanList.add(kanban);
        return kanban;
    }
    
    @RequestMapping("/kanban/new/{text}/{name}/{uId}")
    public Kanban newKanban (@PathVariable("name") String name, @PathVariable("text") String text, @PathVariable("uId") long uId){
        Kanban kanban = new Kanban(name, text, getUserById(uId));
        kanbanList.add(kanban);
        getUserById(uId).addTask(kanban.getId());
        return kanban;
    }
    
    @RequestMapping("/kanban")
    public ArrayList getKanbanList (){
        for(int i = 0; i < this.kanbanList.size(); i++)
            this.checkingTime( this.kanbanList.get(i) );
        return kanbanList;
    }
    @RequestMapping("/kanban/chstate/{id}/{state}")
    public Kanban changeState(@PathVariable("id") int id, @PathVariable("state") int state){
        /*
        1 - PENDING, 
        2 - DONE
        3 - UNDONE
        */
        Kanban kanban = kanbanList.get(id);
        if (state == 1) {
            kanban.setState(State.PENDING);
        }else{
            if (state == 2) {
                kanban.setState(State.DONE);
            }else{
                kanban.setState(State.UNDONE);
            }
        }
        return this.kanbanList.get(id);
    }
    @RequestMapping("/kanban/remove/{id}")
    public Kanban removeKanban(@PathVariable("id") int id){
        Kanban kanban = kanbanList.get(id);
        kanban.setDeleteMark(true);
        rebuildKanbanList();
        return kanban;
    }
    
    private void rebuildKanbanList(){
        ArrayList<Kanban> newList = new ArrayList<>();
        for(Kanban kanban : kanbanList){
            if(!kanban.getDeleteMark()){
                newList.add(kanban);
            }
        }
        kanbanList = newList;
    };
    
    ///DATE TIME FEATURE
    
    @RequestMapping("/kanban/new/{name}/{text}/{uId}/{dateTimeEnd}")
    public Kanban newKanban(@PathVariable("name") String name, @PathVariable("text") String text, @PathVariable("uId") long uId, @PathVariable("dateTimeEnd") String dateTimeEnd){
        Kanban kanban = new Kanban(name, text, getUserById(uId), dateTimeEnd);
        kanbanList.add(kanban);
        return kanban;
    }
    
    public void checkingTime(Kanban kanban){
        if( this.kanbanList.get( this.kanbanList.indexOf(kanban) ).getDateTimeEnd().isBefore( LocalDateTime.now() ) )
            this.kanbanList.get( this.kanbanList.indexOf(kanban) ).setState(State.UNDONE);
    }
    
    
    
    
     ///UserController
    
    ArrayList<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();
    
    @RequestMapping("/kanban/newuser/{name}")
    public ArrayList createUser(@PathVariable("name") String name){
        users.add(new User(counter.incrementAndGet(), name));
        return users;
    }
    
    @RequestMapping("/kanban/user/{id}")
    public ArrayList getTasksOfUser(@PathVariable("id") int id){
        ArrayList<Kanban> userTasks = new ArrayList<>();
        getUserById(id).getTasks().stream().forEach((_item) -> {
            userTasks.add(kanbanList.get(id));
        });
        return userTasks;
    }
    
    public User getUserById(long id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return new User("NoName");
    }
    
}
