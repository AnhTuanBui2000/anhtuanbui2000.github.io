/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.huce.doanlaptrinhweb.demo.repository;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.huce.doanlaptrinhweb.demo.model.Message;

/**
 *
 * @author tuank
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
   @Query("select m from Message m where sender=:id or receiver=:id")
   public Collection<Message> getListMessage(@Param("id")String id);
   @Query("select m from Message m where receiver=:receiver")
   public Collection<Message> getListMessageGroup(@Param("receiver")String receiver);
   @Query("select m from Message m where receiver=:receiver")
   public ArrayList<Message> getMessageGroup(@Param("receiver")String receiver);
}
