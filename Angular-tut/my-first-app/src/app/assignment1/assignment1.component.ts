import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-assignment1',
  templateUrl: './assignment1.component.html',
  styleUrls: ['./assignment1.component.css']
})
export class Assignment1Component implements OnInit {

  userName = '';
  allowClear = false;
  constructor() { }

  ngOnInit() {
  }
  onUpdateUserName(event: Event) {
      this.allowClear = true;
  }

  onClearInput() {
      this.userName = '';
  }
}
