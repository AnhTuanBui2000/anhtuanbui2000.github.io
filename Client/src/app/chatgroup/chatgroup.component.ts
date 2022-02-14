import { Component, OnInit,DoCheck } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { AuthenticationService } from '../auth.service'; 
import { NgForm } from '@angular/forms';
import { WebsocketService } from '../websocket.service';
import { Message } from '../models/Message';
import { GroupChat } from '../models/GroupChat';
import { Friends } from '../models/Friends';
@Component({
  selector: 'app-chatgroup',
  templateUrl: './chatgroup.component.html',
  styleUrls: ['./chatgroup.component.css']
})
export class ChatgroupComponent implements OnInit {

  constructor(public auth :AuthenticationService,public router:Router,public websocket:WebsocketService) { }

  ngOnInit(): void {
    this.getListMessageGroup(this.id_me);
    this.auth.updateStatusTrue(this.id_me).subscribe((response) => {
      console.log(response.status);
    });
    this.displayGroupChat();
    this.websocket.openWebSocket();
    
  }
  //khi component bị phá hủy
  ngOnDestroy(): void {
    this.websocket.closeWebSocket();
    this.auth.updateStatusFalse(this.id_me).subscribe((response) => {
      console.log(response.status);
    });
  }
  userGroupChat:any;
  friends:any;
  grchat:any;
  messages:any;
  id_me=this.auth.id;
  id_group:any;
  name=this.auth.name;
  namegroup:any;
  thongBao:any;
  //mở profile
  profile(){
    this.router.navigate(['/profile']);
  }
  //mở chat
  chat(){
    this.router.navigate(['/chat']);
  }
  login(){
    this.router.navigate(['/login']);
  }
  //hiển thị nhóm chat
  displayGroupChat(){
    this.auth.displayGroupChat().subscribe((data:Array<GroupChat>) =>this.grchat=data);
  }
  //hiển thị người trong nhóm chat
  displayUserInGroupChat(){
    this.auth.displayUserInGroupChat(this.id_group).subscribe((data:Array<GroupChat>)=>this.userGroupChat=data);
  }
  //hiển thị bạn bè chưa vào nhóm
  displayFriends(){
    this.auth.getListFriendsNotInGroup(this.id_me,this.id_group).subscribe((data:Array<Friends>)=>this.friends=data);
    
  }
  
  //gửi tin nhắn
  sendMessage(sendForm: NgForm){
    this.websocket.sendMessage(this.id_me,this.name+":"+sendForm.value.message,this.id_group);
    
    sendForm.controls.message.reset();
 }
 //Tạo nhóm chat mới
 addGroup(addForm:NgForm){
 this.auth.addGroupChat(this.id_me,this.name,addForm.value.addgroup).subscribe((response)=>{
  if(response.status==200){
    console.log('Tạo nhóm thành công');
    this.thongBao="Tạo nhóm thành công !";
    addForm.controls.addgroup.reset();
    this.displayGroupChat();
  }
  else this.thongBao="Tạo nhóm không thành công";
 });
 }
 //Click vào nhóm chat 
 selectGroup(id:string,name:string){
  this.id_group=id;
  this.namegroup=name;
  this.displayUserInGroupChat();
  var selectedGroup=document.getElementById('selectedGroup');
  //this.getListMessageGroup(id);
  selectedGroup!.innerText=name;
  this.friends=null;
}
//Hiển thị lịch sử tin nhắn
getListMessageGroup(id:string){
  this.auth.getListMessageGroup(id).subscribe((data:Array<Message>) =>this.messages=data);
}
//thêm thành viên
addUserGroupChat(id:string,name:string){
this.auth.addUserGroupChat(id,this.id_group,this.namegroup,name).subscribe((response)=>{
  if(response.status==200){
    console.log('Thêm thành viên thành công');
    this.displayFriends();
    this.displayUserInGroupChat();
  }
})
}

}
