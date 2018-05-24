import {Component, OnInit} from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Http, Response, Headers, RequestOptions, URLSearchParams }
from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import { Router }    from '@angular/router';
import { environment } from '../../environments/environment';
import { DataserviceService } from '../dataservice.service';




@Component({
  selector: 'app-new-project',
  templateUrl: './new-project.component.html',
  styleUrls: ['./new-project.component.css']
})
export class NewProjectComponent implements OnInit {
  heroesUrl = "/api";
  extractData: string;
<<<<<<< HEAD
=======
   message:string;//for service cheking
>>>>>>> d8ac57b3e85b42bde5445682e5e0729a45f77d50
  validName = false;
 // nameValidation  = false;
 fieldval = false;
 githublink = false;
 userNamevalid = false;
 pwdvalid = false;
 form;
 leastone  = false;
<<<<<<< HEAD
  
=======
 upCredUsers = [];
 upsCredUsers = [];
 upssCredUsers =[];
 userNames = [];
 userNamesPM = []
 userNamesCD = [];

  usernameJenkin = [];
  passwordJenkin = [];
  validData :any;
>>>>>>> d8ac57b3e85b42bde5445682e5e0729a45f77d50

  Newproject = {
    "Name": "",
    "TeamMembers": [],
    "Buildpacks": {
      "Name": "java",
      "Buildtool": "Maven",
      "PomXml": "pom.xml"
    },
    "BuildFaces": {
      "Clean": false,
      "Compile": false,
      "Build": false,
      "validate": false,
      "package": false,
      "install": false,
      "test": false,
      "deploy": false,
      "docker": false,
      "dockerBuild": false,
      "appPortNumber": "",
      "dockerPortNumber": "",
      "dockerFilePath": "",
      "dockerServer": "",
      "dockerPort": "",
      "dockerImageName": "",
      "extraArgs": " -DskipTests "
    },
    "SCM": {
      "Name": "Github",
      "Link": "",
      "Credentials" : "",
      "Username": "",
      "Password": "",
      "repoBranch": "",
      "authType": "",
      "CredentialID" :""
    },
    "ConnectTo": "Jenkins",
    "Jenkins": {
      "CredentialID":"",
      "JenkinsURL": "",
      "JenkinsUsername": "",
      "JenkinsPassword": "",
      "ArtifactoryUrl": "",
      "TargetReleaseSynatax": "",
      "TargetSnapshotSyntax": "",
      "authType": ""
    },
    "Concourse": {
      "ConcourseURL": "",
      "ConcourseUsername": "",
      "ConcoursePassword": ""
    },
    "Pm": {
      "Name": "",
      "Link": "",
      "Username": "",
      "Password": "",
      "CredentialID": "",
      "authType": ""
    },
   "CD": {
      "deployTo" : "",
      "deploymentServer" : "",
      "CredentialID" : "",
      "appTargetPortNumber": "",
      "dockerExposedPortNumber": "",
      "dockerImgRegistryPort": "",
      "dockerImgRegistryServer": "",
      "bash": "",
      "authType":""
    }
  };


  dropdownList = [];
  credentialsList = [];

  dropdownSettings = {};
  credentialsDropdown ={};

  constructor(private http: Http, private router: Router,private data: DataserviceService) { 
    this.getCredntials()
  }

  myMethod(data: any) {
	console.log(data);
    var headers = new Headers();
	//this.data.BuildFaces.dockerImageName = this.Newproject.BuildFaces.dockerImageName;
    headers.append('Content-Type', 'application/json');

   /* var sdata = {
    "name":"proname",
    "data":"prodata",
    "build":{
     "buildtool":"maven",
     "buildlink":"link",
     "buildtype":"type"
     }
    };*/

    this.validData = data;
  //  localStorage.setItem('projectData', this.validData);

    this.data.setData(this.validData);

    //JSON.stringify(this.basicdata);

   // window.localStorage['storageName'] = JSON.stringify(data);

    this.http
      .post(environment.apiservices+ '/demo/createJob',
      data, {
        headers: headers
      })
      .subscribe(data => {
        this.router.navigate(['Success']);
      }, error => {
        //  console.log(JSON.stringify(error.json()));
        console.log("error");
        this.router.navigate(['Failure']);
      });
    // console.log(data);
  }
<<<<<<< HEAD
=======
  getCredntials() {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    
    this.http.get(environment.apiservices+ '/demo/getAllCredentials')
      .map(
        (response) => response.json()
      )
     .subscribe(
       (data) => {
       this.credentialsList = data['result'];
      });
        
  }  

  doSomething(data){
    data.map(function(key, data) {
          this.credentialsList.push({"id" : key , "itemName:" : data['credentialName'] })
    })

    //alert(this.credentialsList); 
  }
>>>>>>> d8ac57b3e85b42bde5445682e5e0729a45f77d50


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
    this.credentialsDropdown = {
      singleSelection: true,
      text: "Select Auth type",
      enableSearchFilter: true,
      classes: "myclass custom-class"
    };

  }

<<<<<<< HEAD
  nameValidation() {
   // this.clickMessage = 'You are my hero!';
  // alert("hello"+this.Newproject.Name);
   if(this.Newproject.Name === ''){
   this.validName = true;
   }
   this.fieldval = true;

  }

  onDockerSelect(){
  if(this.Newproject.BuildFaces.Clean || this.Newproject.BuildFaces.Compile || this.Newproject.BuildFaces.Build || this.Newproject.BuildFaces.validate || this.Newproject.BuildFaces.package || this.Newproject.BuildFaces.install || this.Newproject.BuildFaces.test || this.Newproject.BuildFaces.deploy === true){
      this.leastone = true;
      
  }else{

   this.leastone = false;

  }
 // console.log("value is..."+this.leastone);
 // console.log("value is..."+this.Newproject.BuildFaces.Clean);
  }

  gitHubValid(){
      if(this.Newproject.SCM.Link === ''){
      this.githublink = true;
      } 
  }

  usernameValidation(){
      if(this.Newproject.SCM.Username === ''){
      this.userNamevalid = true;
      }
  }

  validPwd(){
    if(this.Newproject.SCM.Password === ''){
    this.pwdvalid = true;
    }
  }


 /* any("#tabs").tabs({
        select: function(event, ui) {
            alert('validating tab ' + ui.index);

            var valid = false;

            // do your validating here...
            // determine validity

            // If the form isn't valid, prevent the tab from changing
            if(!valid)
            {
                // prevent further action
                event.preventDefault();
            }
            else
            {
                // valid so we'll allow user to change tab
                alert('valid');
            }
        }        
});*/



  checkhere(event) {
  //alert("checking here.."+e);
  //event.preventDefault();
   //e.stopPropagation();
  // this.fieldval
   return ;
  }

  checkProject() {
 // alert("check one..");
  return false;
  }

=======
 

 /* onDockerSelect(){
        if(this.Newproject.BuildFaces.Clean || this.Newproject.BuildFaces.Compile || this.Newproject.BuildFaces.Build || this.Newproject.BuildFaces.validate || this.Newproject.BuildFaces.package || this.Newproject.BuildFaces.install || this.Newproject.BuildFaces.test || this.Newproject.BuildFaces.deploy === true){
            this.leastone = true;
            
        }else{

         this.leastone = false;

        }
  }*/


onChangeCD(){
   this.userNamesCD = [];
    if(this.Newproject.CD.authType === 'up'){
    
        for(let result of this.credentialsList){
        if(result.type === 'up'){

              this.userNamesCD.push(result.credentialName);

            }
          
          }

      }
      if(this.Newproject.CD.authType === 'ups'){
    
        for(let result of this.credentialsList){
        if(result.type === 'ups'){

              this.userNamesCD.push(result.credentialName);

            }
          
          }

      }
      if(this.Newproject.CD.authType === 'upss'){
    
        for(let result of this.credentialsList){
        if(result.type === 'upss'){

              this.userNamesCD.push(result.credentialName);

            }
          
          }

      }

}

  onChangePM(){

     this.userNamesPM = [];
     if(this.Newproject.Pm.authType === 'up'){
    
        for(let result of this.credentialsList){
        if(result.type === 'up'){

              this.userNamesPM.push(result.credentialName);

            }
          
          }

      }
      if(this.Newproject.Pm.authType === 'ups'){
    
        for(let result of this.credentialsList){
        if(result.type === 'ups'){

              this.userNamesPM.push(result.credentialName);

            }
          
          }

      }
      if(this.Newproject.Pm.authType === 'upss'){
    
        for(let result of this.credentialsList){
        if(result.type === 'upss'){

              this.userNamesPM.push(result.credentialName);

            }
          
          }

      }
  }

  onChangeCICD(){
     this.usernameJenkin = [];
    // this.passwordJenkin = [];

     if(this.Newproject.Jenkins.authType === 'up'){
    
        for(let result of this.credentialsList){
        if(result.type === 'up'){

              this.usernameJenkin.push(result.credentialName);

            }
          
          }

      }
      if(this.Newproject.Jenkins.authType === 'ups'){

        for(let result of this.credentialsList){
        if(result.type === 'ups'){

          this.usernameJenkin.push(result.credentialName);

          }
      
       }

      }
      if(this.Newproject.Jenkins.authType === 'upss'){

         for(let result of this.credentialsList){
         if(result.type === 'upss'){

            this.usernameJenkin.push(result.credentialName);

          }
        
        }

      }
     
  }

  onChange(){
      
      this.userNames = [];
      /*if(this.Newproject.ConnectTo === 'jenkins'){
        this.usernameJenkin = [];
        this.passwordJenkin = [];
      }*/
      if(this.Newproject.SCM.authType === 'up'){
    
        for(let result of this.credentialsList){
        if(result.type === 'up'){

              this.userNames.push(result.credentialName);

            }
          
          }

      }
      if(this.Newproject.SCM.authType === 'ups'){

        for(let result of this.credentialsList){
        if(result.type === 'ups'){

          this.userNames.push(result.credentialName);

          }
      
       }

      }
      if(this.Newproject.SCM.authType === 'upss'){

         for(let result of this.credentialsList){
         if(result.type === 'upss'){

            this.userNames.push(result.credentialName);

          }
        
        }

      }
     

  }


>>>>>>> d8ac57b3e85b42bde5445682e5e0729a45f77d50
  // passwordManagement = {
  //   Scm:{
  //     GitHub:"GITHUBALTI1",
  //     GitLab:"GITLABALTI1",
  //     SVN:""
  //   },
  //   Cloud:{
  //     AWS:"AWSALTI1",
  //     PCF:"PCFALTI1",
  //     GoogleCloud:"GCALTI1",
  //     Azure:"AZALTI1",
  //     DigitalOcean:"DOSALTI1"
  //   },
  //   CI:{
  //     Jenkins:"JENALTI1",
  //     Concoures:"CONALTI1"
  //   },
  //   PM:{
  //     Rally:"RAALTI001",
  //     PivotalTracker:"PATLTI001"
  //   }
  // }
}

