import { Component, OnInit } from '@angular/core';
import 'rxjs/add/operator/map';import { Http, Response, Headers, RequestOptions, URLSearchParams }
from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Router }    from '@angular/router';
import { SuccessComponent } from './../success/success.component';
import { DataserviceService } from '../dataservice.service';

@Component({
  selector: 'app-get-job',
  templateUrl: './get-job.component.html',
  styleUrls: ['./get-job.component.css']
})
export class GetJobComponent implements OnInit {
  userNames = [];
  userNamesPM = []
  
  usernameJenkin = [];
  passwordJenkin = [];
  validData :any;
  Newproject = {
    "Name": "gatewayservice",
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
      "dockerServer": "",
      "dockerPort": "",
      "dockerImageName": "",
    "appPortNumber": "",
    "dockerPortNumber": "",
      "extraArgs": ""
    },
    "SCM": {
      "Name": "",
      "Link": "",
      "Username": "",
      "Password": "",
      "repoBranch": "",
      "authType": "",
      "CredentialID" :""
    },
    "ConnectTo": "",
    "Jenkins": {
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
      "bash": ""
    }
  };

  credentialsList = [];
  Edit = false;
  Data : string;
  JobNam = '';
  constructor(private http: Http, private router: Router, private data: DataserviceService) {
    this.getCredntials()
  }
  ngOnInit() {
    this.http.get(environment.apiservices + '/demo/getAllJob').map(
      (response) => response.json()
    )
   .subscribe(
     (data) => {
      // Read the result field from the JSON response.
      this.Data = data.result;
    }
   )
  }

  getCredntials() {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    
    this.http.get(environment.apiservices + '/demo/getAllCredentials')
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

  GetJUpdat(data){
  this.Edit = true;
    this.JobNam = data;
    // this.router.navigate(['update']);
    this.http.get(environment.apiservices + '/demo/getOneJob/'+this.JobNam).map(
      (response) => response.json()
    )
   .subscribe(
     (data) => {
      // Read the result field from the JSON response.
      // alert(JSON.stringify(data.result[0]))
      this.Newproject = data.result[0];
      }
   )
  }
  UpdateProject(data : any, jobName){
    
    this.validData = data;
    this.data.setData(this.validData);
    
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');

    this.http
      .put(environment.apiservices + '/demo/updateJob/'+jobName,
      data, {
        headers: headers
      })
      .subscribe(data => {
        //alert('ok');
        this.router.navigate(['Success']);
      }, error => {
        console.log("error");
        this.router.navigate(['Failure']);
      });
  }
  cancel(){
    this.Edit = false;
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


}
