import { Component, OnInit } from '@angular/core';

@Component({
  //by element
   selector: 'app-server',
  //by attribute
 // selector: '[app-server]',
  //by class
  //selector: '.app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.css']
})
export class ServerComponent implements OnInit {

  serverId: number = 20;
  serverStatus : string = "offline";

  constructor() { 
    this.serverStatus = Math.random() > 0.5 ? "online" : "offline";
  }

  getServerStatus(){
    return this.serverStatus;
  }
  ngOnInit() {
  }

  getColor(){
    return this.serverStatus === 'online'? 'green' : 'red';
  }
}
