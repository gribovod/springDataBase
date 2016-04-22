package com.itstep;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vadim
 */

@RestController
public class KanbanController {
    
    @Autowired
    private KanbanDao kanbanDao;
    private ArrayList<Kanban> kanbanList = new ArrayList<>();

    

    @RequestMapping("/kanban/new/{text}/{name}")
    public Kanban newKanban (@PathVariable("name") String name, @PathVariable("text") String text){
        Kanban kanban;
	try{
	    KanbanModel kModel = new KanbanModel(name, text);
            kanbanDao.save(kModel);
            kanban = new Kanban(kModel);
            kanbanList.add(kanban);
	}
	catch(Exception ex){
	    return null;
	}
        return kanban;
    }
    
    @RequestMapping("/kanban/new/{text}/{name}/{userName}")
    public Kanban newKanban (@PathVariable("name") String name, @PathVariable("text") String text, @PathVariable("userName") String userName){   
        Kanban kanban;
	try{
	    KanbanModel kModel = new KanbanModel(name, text, userName);
            kanbanDao.save(kModel);
            kanban = new Kanban(kModel);
            kanbanList.add(kanban);
	}
	catch(Exception ex){
	    return null;
	}
        return kanban;
    }
    
    @RequestMapping("/kanban")
    public ArrayList getKanbanList (){
        if(this.kanbanList.size() == 0){
            for(KanbanModel kModel : this.kanbanDao.findAll()){
                Kanban kanban = new Kanban(kModel);
                kanbanList.add(kanban);
            }
        }
        for(Kanban kanban : kanbanList){
            this.checkingTime(kanban);
        }
        return kanbanList;
    }
    @RequestMapping("/kanban/chstate/{id}/{state}")
    public Kanban changeState(@PathVariable("id") int id, @PathVariable("state") int state){
        Kanban kanban = getKanbanById(id);
        KanbanModel kModel = kanbanDao.findById(id);
        if (state == 1) {
            kanban.setState(State.PENDING);
            kModel.setStateStr("PENDING");
        }else{
            if (state == 2) {
                kanban.setState(State.DONE);
                kModel.setStateStr("DONE");
            }else{
                kanban.setState(State.UNDONE);
                kModel.setStateStr("UNDONE");
            }
        }
        kanbanDao.save(kModel);
        return kanban;
    }
    
    @RequestMapping("/kanban/remove/{id}")
    public ArrayList<Kanban> removeKanban(@PathVariable("id") int id){
        kanbanDao.delete(kanbanDao.findById(id));
        getKanbanById(id).setDeleteMark(true);
        rebuildKanbanList();
        return kanbanList;
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
    
    @RequestMapping("/kanban/new/{name}/{text}/{userName}/{dateTimeEnd}")
    public Kanban newKanban(@PathVariable("name") String name, @PathVariable("text") String text, @PathVariable("userName") String userName, @PathVariable("dateTimeEnd") String dateTimeEnd){
        Kanban kanban;
	try{
	    KanbanModel kModel = new KanbanModel(name, text, userName, dateTimeEnd);
            kanbanDao.save(kModel);
            kanban = new Kanban(kModel);
            kanbanList.add(kanban);
	}
	catch(Exception ex){
	    return null;
	}

        return kanban;
    }
    
    public void checkingTime(Kanban kanban){
        if( this.kanbanList.get( this.kanbanList.indexOf(kanban) ).getDateTimeEnd().isBefore( LocalDateTime.now() ) )
            this.kanbanList.get( this.kanbanList.indexOf(kanban) ).setState(State.UNDONE);
    }
    
    public Kanban getKanbanById(long id){
        for(Kanban k : kanbanList){
            if(k.getId() == id)
                return k;
        }
        return null;
    }
    

    
}
