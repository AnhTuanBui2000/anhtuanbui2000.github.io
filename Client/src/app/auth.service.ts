import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import {User} from '../app/models/User';
import {Friends} from '../app/models/Friends';
import {Message} from '../app/models/Message';
import {GroupChat} from '../app/models/GroupChat';
import {Observable} from 'rxjs';
@Injectable({
 providedIn: 'root'
})
export class AuthenticationService {
 BASE_PATH= 'http://localhost:8080'
 public ID_SESSION = 'id_session'
 public id!: string;
 public username!:       String;
 public password!:       String;
 public name!:string;
 constructor(private http: HttpClient) {
 }
 login(username: string, password: string) {
 var params = new HttpParams()
 .set('username',username)
 .set('password', password);
 return this.http.post<Response>(this.BASE_PATH+"/auth/login",params,{
observe: 'response' });
 }

 getUserLongin(user:string){
return this.http.get<User>(this.BASE_PATH+"/auth/login/"+user).pipe();
 }
 register(user: String, pass: String,name:String){
 return this.http.post<Response>(this.BASE_PATH+"/auth/register",{username:user,password:pass,
                                 name:name},{ observe: 'response' });
 }
 createBasicAuthToken() {
 console.log(this.username + ":" + this.password);
 return 'Basic ' + window.btoa(this.username + ":" + this.password)
 } 
 registerSuccessfulLogin(id:any) {
 sessionStorage.setItem(this.ID_SESSION, id)
 }
 logout() {
 sessionStorage.removeItem(this.ID_SESSION);
 this.id='';
 this.username = "";
 this.password = "";
 }
 isUserLoggedIn() {
 let user = sessionStorage.getItem(this.ID_SESSION)
 if (user === null) return false
 return true
 }
 getLoggedInUserName() {
 let user = sessionStorage.getItem(this.ID_SESSION)
 if (user === null) return ''
 return user
 }
 //ProfileComponent
 getProfile (){
     console.log("Get profile :"+this.id);
     return this.http.get<User>(this.BASE_PATH+"/profile/"+this.id).pipe();
 }
 getListFriends():Observable<Array<Friends>>{
     console.log("Get list friends :"+this.id);
     return this.http.get<Array<Friends>>(this.BASE_PATH+"/friends/"+this.id).pipe();
 }
 findUser(id:string):Observable<Array<User>>{
    console.log("Find User by id:"+id);
    return this.http.get<Array<User>>(this.BASE_PATH+"/find/"+id).pipe();
 }
 deleteFriend(id_me:any,id_friend:any){
    console.log("Delete user id:"+id_friend);
    return this.http.delete<Response>(this.BASE_PATH+"/delete/" +id_me+"/"+id_friend).pipe();
 }
 addFriend(id_friend:string,name:string,id_me:string,status_friend:any){
    return this.http.post<Response>(this.BASE_PATH+"/add/friend",{id_friend:id_friend,name:name,
        id_me:id_me,status_friend:status_friend},{ observe: 'response' });
}

 updateNameUser(name:string){
    return this.http.post<Response>(this.BASE_PATH+"/update/"+this.id,{id:this.id,name:name,username:this.username,
        password:this.password},{ observe: 'response' });
 }
 updatePassword(password:string){
   return this.http.post<Response>(this.BASE_PATH+"/update/"+this.id,{id:this.id,name:this.name,username:this.username,
       password:password},{ observe: 'response' });
}
 //--------------
 //ChatComponent
 addMessage(sender: string , content:string,receiver:string){
    return this.http.post<Response>(this.BASE_PATH+"/add/message",{
       sender:sender,
       content:content,
       receiver:receiver},{observe: 'response'});
 }
 getListMessage(id:string){
    return this.http.get<Array<Message>>(this.BASE_PATH+"/listmessage/"+id).pipe();
 }
 updateStatusTrue(id:string){
   return this.http.put<Response>(this.BASE_PATH+"/update/statustrue/"+id,{ observe: 'response' });
 }
 updateStatusFalse(id:string){
   return this.http.put<Response>(this.BASE_PATH+"/update/statusfalse/"+id,{ observe: 'response' });
 }
 //------------------------
//GroupChatComponent
displayGroupChat():Observable<Array<GroupChat>>{
   console.log("Get list friends :"+this.id);
   return this.http.get<Array<GroupChat>>(this.BASE_PATH+"/getgroupchat/"+this.id).pipe();
}
displayUserInGroupChat(idGroup:string):Observable<Array<GroupChat>>{
   console.log("Get list User in GroupChat "+idGroup);
   return this.http.get<Array<GroupChat>>(this.BASE_PATH+"/getusergroupchat/"+idGroup).pipe();
}
getListMessageGroup(id:string):Observable<Array<Message>>{
   console.log("Get list Message in Group ");
   return this.http.get<Array<Message>>(this.BASE_PATH+"/listmessagegroup/"+id).pipe();
}
addGroupChat(idUser:string,nameUser:string,nameGroup:string){
   return this.http.post<Response>(this.BASE_PATH+"/add/groupchat",{
      id_user:idUser,
      nameuser:nameUser,
      namegroup:nameGroup
      },{observe: 'response'});
}
addUserGroupChat(idUser:string,idGroup:string,nameGroup:string,nameUser:string){
   return this.http.post<Response>(this.BASE_PATH+"/add/user/groupchat",{
      id_user:idUser,
      namegroup:nameGroup,
      nameuser:nameUser,
      id_groupchat:idGroup},{observe: 'response'});
}
getListFriendsNotInGroup(id_me:string,id_group:string):Observable<Array<Friends>>{
   console.log("Get list friends not in group :"+id_group);
     return this.http.get<Array<Friends>>(this.BASE_PATH+"/findfriend/"+id_me+"/"+id_group).pipe();
}


//-------------------------
} 