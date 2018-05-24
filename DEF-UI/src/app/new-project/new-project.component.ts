import {Component, OnInit} from '@angular/core';
@Component({
  selector: 'app-new-project',
  templateUrl: './new-project.component.html',
  styleUrls: ['./new-project.component.css']
})
export class NewProjectComponent implements OnInit {
  Newproject = {
    Name: '',
    TeamMembers : [],
    Buildpacks : {
    Name  : '',
    Buildtool:'',
    Packaging:'',
    Version:''
  },
  SCM:{
    Name:'',
    Link:'',
    Username:'',
    Password:''
  },
  CI:{
    Name:'',
    Link:'',
    Username:'',
    Password:''
  },
  Pm:{
    Name:'',
    Link:'',
    Username:'',
    Password:''
  },
  CD:{
    Platform:'',
    Name:'',
    Link:'',
    Username:'',
    Password:''
  }
};
  dropdownList = [];
  dropdownSettings = {};
  constructor() {
  }
  ngOnInit() {
    this.dropdownList = [
      { "id": 1, "itemName": "Kalanithi" },
      { "id": 2, "itemName": "Shyam" },
      { "id": 3, "itemName": "Sathya" },
      { "id": 4, "itemName": "Suresh" },
      { "id": 5, "itemName": "Naveen" },
      { "id": 6, "itemName": "Chandra" },
      { "id": 7, "itemName": "Arun" },
      { "id": 8, "itemName": "Rangan" },
      { "id": 9, "itemName": "Nithya" },
      { "id": 10, "itemName": "Sengathir" }
    ];
    this.dropdownSettings = {
      singleSelection: false,
      text: "Select Team Members",
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      enableSearchFilter: true,
      classes: "myclass custom-class"
    };
  }
}
