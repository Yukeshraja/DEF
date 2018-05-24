import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-scm',
  templateUrl: './scm.component.html',
  styleUrls: ['./scm.component.css']
})
export class ScmComponent implements OnInit {
  rep = {};
  ci = {};
  Github = false;
  GitLab = false;
  Repository = false;
 git(){
   this.Github = true;
   this.GitLab = false;
 }
 gitLab(){
   this.Github = false;
   this.GitLab = true;
 }
 GetRep(){
  this.Repository = true;
 }
  constructor() { }

  ngOnInit() {
  }

}
