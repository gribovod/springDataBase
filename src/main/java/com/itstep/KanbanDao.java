/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itstep;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Liona
 */
@Transactional
public interface KanbanDao extends CrudRepository<KanbanModel, Long>{
    public KanbanModel findById(long id);
    public KanbanModel findByName(String name);
}