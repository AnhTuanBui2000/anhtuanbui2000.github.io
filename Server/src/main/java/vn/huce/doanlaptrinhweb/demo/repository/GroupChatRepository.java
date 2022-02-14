/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.huce.doanlaptrinhweb.demo.repository;


import java.util.ArrayList;
import java.util.Collection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.huce.doanlaptrinhweb.demo.model.GroupChat;

/**
 *
 * @author tuank
 */
public interface GroupChatRepository extends CrudRepository<GroupChat, Long> {
    @Query("select g from GroupChat g where id_groupchat=:id_groupchat")
    public Collection<GroupChat> getUserGroupChat(@Param("id_groupchat")String id_groupchat);
    @Query("select g from GroupChat g where id_groupchat=:id_groupchat")
    public ArrayList<GroupChat> getListUserGroupChat(@Param("id_groupchat")String id_groupchat);
    @Query("select g from GroupChat g where id_user=:id_user")
    public Collection<GroupChat> getGroupChat(@Param("id_user")Long id_user);
    @Query("select g from GroupChat g group by id_groupchat order by id_groupchat")
    public ArrayList<GroupChat> getGroupChatSize();
    
    @Query("select g from GroupChat g where id_user=:id_user")
    public ArrayList<GroupChat> findUser(@Param("id_user")Long id_user);
}
