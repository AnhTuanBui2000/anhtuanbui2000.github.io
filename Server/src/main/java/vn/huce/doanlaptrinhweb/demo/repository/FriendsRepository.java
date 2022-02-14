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
import vn.huce.doanlaptrinhweb.demo.model.Friends;

/**
 *
 * @author tuank
 */
public interface FriendsRepository extends CrudRepository<Friends, Long> {
    @Query("select f from Friends f where id_me=:id_me")
    public Collection<Friends> findListFriendsById(@Param("id_me")Long id_me);
    @Query("select f from Friends f where id_me=:id_me")
    public ArrayList<Friends> getListFriendsById(@Param("id_me")Long id_me);
    @Query("select f from Friends f where id_friend=:id_friend and id_me=:id_me")
    public Friends findFriend(@Param("id_friend") Long id_friend,@Param("id_me")Long id_me);
    @Query("select f from Friends f where id_friend=:id_friend")
    public ArrayList<Friends> findListFriendsByIdFriend(@Param("id_friend")Long id_friend);
  
}
