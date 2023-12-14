import { Component } from '@angular/core';

@Component({
  selector: 'app-servers',
 // selector: '.app-servers',
  templateUrl: './servers.component.html',
  styleUrl: './servers.component.css'
})
export class ServersComponent {

    allowNewServer = false;
    serverCreationStatus = "No server was created...";
    serverName = "TestServer";
    serverCreated = false;
    servers = ['testserver','tesetserver 2'];

    constructor(){

      setTimeout(() => {this.allowNewServer=true
      },2000);
    }

    onCreateServer(){
      this.serverCreated = true;
      this.servers.push(this.serverName);
      this.serverCreationStatus = "Server was created...." + this.serverName;
    }
    onUpdateServerName(event : any){
      console.log(event)
      this.serverName = (<HTMLInputElement>event.target).value;
    }
}
