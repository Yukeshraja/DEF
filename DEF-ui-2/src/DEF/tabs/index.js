import React from 'react';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
//import { ValidatorForm, TextValidator} from 'react-material-ui-form-validator';
import {
  Step,
  Stepper,
  StepButton,
} from 'material-ui/Stepper';
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import ExpandTransition from 'material-ui/internal/ExpandTransition';
//import { ValidatorForm, TextValidator} from 'react-material-ui-form-validator';
import DropDownMenu from 'material-ui/DropDownMenu';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
//import TextField from 'material-ui/TextField';
import Checkbox from 'material-ui/Checkbox';
import Divider from 'material-ui/Divider';
import AppBar from 'material-ui/AppBar';
import Chip from 'material-ui/Chip';
import IconButton from 'material-ui/IconButton';
import NavigationClose from 'material-ui/svg-icons/navigation/close';

const names = [
  'Oliver Hansen',
  'Van Henry',
  'April Tucker',
  'Ralph Hubbard',
  'Omar Alexander',
  'Carlos Abbott',
  'Miriam Wagner',
  'Bradley Wilkerson',
  'Virginia Andrews',
  'Kelly Snyder',
];

const styles = {
  headline: {
    fontSize: 24,
    paddingTop: 16,
    marginBottom: 12,
    fontWeight: 1500,
  },
};

const getStyles = () => {
  return {
    root: {
      width: '100%',
      maxWidth: 1300,
      margin: 'auto',
    },
    content: {
      margin: '0 16px',
    },
    actions: {
      marginTop: 12,
    },
    backButton: {
      marginRight: 12,
    },
  };
};


//export default class TaB extends React.Component {

   //class HorizontalLinearStepper extends React.Component {

    //const Name = "";



class UserForm extends React.Component {

state = {
  userName:'',
  projectName:'',
  selectValue:''
}

handleChange = (event, index, value) => this.setState({selectValue: value});

render(){
return(<div>
      <Card>
    
    <CardTitle title="User Form" />
    <CardText>
    <strong>Enter the UserName and ProjectName</strong>
    </CardText>
    
   <SelectField
          floatingLabelText="Select Type"
          value={this.state.selectValue}
          onChange={this.handleChange}
          style={{marginTop: 0,marginLeft:10, width:'100%',maxWidth:700,margin:'auto'}}
        >
          <MenuItem value={1} primaryText="LDAP" />
          <MenuItem value={2} primaryText="eloud" />
        </SelectField>
   <br/>
    <TextField value={this.state.userName}   onChange={(e, newValue) => this.setState({ userName: newValue})}   hintText="Enter UserName" style={{marginTop: 0, marginLeft: 10, width:'100%',maxWidth:700,margin:'auto'}} />
    <TextField  value={this.state.projectName}   onChange={(e, newValue) => this.setState({ projectName: newValue})}    hintText="Enter Project Name" style={{marginTop: 0, marginLeft: 10, width:'100%',maxWidth:700,margin:'auto'}} />
    <CardActions>
      <FlatButton label="update" />
      <FlatButton label="cancel" />
    </CardActions>
  </Card>
  </div>);
  }
}

export default class DevLog extends React.Component  {
  

state = {
        stepIndex: null,
        disable: true,
        visited: [],
        value:1,
        scmone:"1",
        scm_DYNACRED:"",
        values: [],
        scmdata:[],
        cidata:[],
        cddata:[],
        creddata:[],
        credData11:[],
        citype: "",
        status: "",
        createorUpdate : "",
        "Name": "",
        "TeamMembers": [],
        "Buildpacks": {
          "Name": "",
          "Buildtool": "",
          "PomXml": ""
        },
        "BuildFaces": {
          "Clean": true,
          "Compile": false,
          "Build": false,
          "validate": false,
          "package": false,
          "install": true,
          "test": false,
          "deploy": false,
          "docker": true,
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
          "ScmId" : "",
          "Name": "",
          "Link": "",
          "Credentials" : "",
          "Username": "",
          "Password": "",
          "repoBranch": "",
          "authType": "",
          "CredentialID" :""
        },
        "ConnectTo": "",
        "Jenkins": {
          "ciName" : "",
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
          "cdName" : "",
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

  static contextTypes = { 
    router: React.PropTypes.object 
  } 

  constructor(props) {
    super(props); 
      this.handleChangecitool = this.handleChangecitool.bind(this);
  }

  componentWillMount() {
    const {stepIndex, visited} = this.state;
    this.setState({visited: visited.concat(stepIndex) });
    const creds =  this.fetchData();
    //alert('Hi')
    console.log(this.props);
    //alert('Hi' + this.props.params.jobParamValue)
    // this.handleChangecitool();  
  }

  componentWillUpdate(nextProps, nextState) {
    const {stepIndex, visited} = nextState;
    if (visited.indexOf(stepIndex) === -1) {
      this.setState({visited: visited.concat(stepIndex)});
    }
  }


  fetchData = () =>{
        this.fetchSCM();
        this.fetchOneJobName();
        this.handleChangecitool();
        this.handleChangecdtool();
  }

  fetchSCM  = () =>{
        const getscm = 'getAllScm'
    //alert("url is...111"+process.env);
    //alert("url is..."+process.env.API_URLS);
    //alert("url is..."+process.env.REACT_APP_API_URLS);


        var url = process.env.REACT_APP_API_URLS+'/'+getscm;
        fetch(url, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
         .then( (response) => {
            return response.json()
         })
         .then( (json) => {
          this.setState({
            scmdata : json.result
          })
            console.log('parsed json', json)
         })
         .catch( (ex) => {
            console.log('parsing failed', ex)
         })

  }

  fetchOneJobName  = () => {
      const { match = { params: {} } } = this.context.router.route;
      const { jobParamValue } = match.params;

      if (jobParamValue !== undefined) {
          this.setState({createorUpdate : "update", stepIndex : 0});
          var url = process.env.REACT_APP_API_URLS+'/getOneJob/'+jobParamValue;

          fetch(url, {
            headers: {
              'Content-Type': 'application/json'
            }
          })
           .then( (response) => {
              return response.json()
           })
           .then( (json) => {
              var result0 = json.result[0];
              this.setState({
                status : result0.status,
                Buildpacks : result0.Buildpacks,
                BuildFaces : result0.BuildFaces,
                Name : result0.Name,
                SCM : result0.SCM,
                ConnectTo : result0.ConnectTo,
                Jenkins : result0.Jenkins,
                Concourse : result0.Concourse,
                CD : result0.CD,
                Pm : result0.Pm
              })
              console.log('parsed json', json)
           })
           .catch( (ex) => {
              console.log('parsing failed', ex)
           })
      } else {
            this.setState({createorUpdate : "create", stepIndex : 0});
      }
      
  }

  handleNext = () => {
    const {stepIndex} = this.state;
    if (stepIndex < 9) {
      this.setState({stepIndex: stepIndex + 1});
    }
    if (stepIndex === 4) {
      this.onClickbuildFace();
    }
  };



  handleChangebuild = (event, index, newValue) => 
  {
        if (this.state.Buildpacks == null) {
          this.state.Buildpacks = {}
        }
        var abc = this.state.Buildpacks;
        abc.Name = newValue;
        this.setState({Buildpacks: abc})
  }

  handleChangebuildtool = (event, index, newValue) => 
  {
        if (this.state.Buildpacks == null) {
          this.state.Buildpacks = {}
        }
        var abc = this.state.Buildpacks;
        abc.Buildtool = newValue;
        this.setState({Buildpacks: abc})
  }

  handleChangescmtool = (event, index, newValue) => 
  {
        if (this.state.SCM == null) {
          this.state.SCM = {}
        }
        var abc = this.state.SCM;
        abc.ScmId = newValue;
        this.setState({SCM: abc})  

  }

  handleChangescmtype = (event, index, newValue) => 
  {
        if (this.state.SCM == null) {
          this.state.SCM = {}
        }
        var abc = this.state.SCM;
        abc.Name = newValue;
        this.setState({SCM: abc})  

        var url = process.env.REACT_APP_API_URLS+'/getAllScm';
        fetch(url, {
                  headers: {
                    'Content-Type': 'application/json'
                  }
              }) 
        .then((response) => response.json()) 
        .then((responseJson) => { 
              //alert(JSON.stringify(responseJson.result)); 
              this.setState({
                scmdata : responseJson.result
              })
              return responseJson.result; }) 
        .catch((error) => { console.error(error); });
  }

  onClickbuildFace() {  

        if (this.state.BuildFaces == null) {
          this.state.BuildFaces = {}
        }
        var abc = this.state.BuildFaces;
        var imageName = (this.state.Name !== null) ? this.state.Name.toLowerCase() : '';
        abc.dockerImageName = imageName.replace(/[^a-zA-Z0-9]/g, "")
        this.setState({BuildFaces: abc})   
  }


  handleChangecitool = () => 
  {
        //alert('Hello')
        if (this.state.Jenkins == null) {
          this.state.Jenkins = {}
        }
        // this.setState({ConnectTo: newValue})  

        var url = process.env.REACT_APP_API_URLS+'/getAllCi';
        fetch(url, {
                  headers: {
                    'Content-Type': 'application/json'
                  }
              }) 
        .then((response) => response.json()) 
        .then((responseJson) => { 
              //alert(JSON.stringify(responseJson.result)); 
              this.setState({
                cidata : responseJson.result
              })
              return responseJson.result; }) 
        .catch((error) => { console.error(error); });
  }

  handleChangeciid = (event, index, newValue) => 
  {
        if (this.state.Jenkins == null) {
          this.state.Jenkins = {} 
        }

        this.setState({ConnectTo: newValue})  

        // var abc = this.state.Jenkins;
        // abc.ciName = newValue;
        // this.setState({Jenkins: abc})    
  }

  handleChangeciType = (event, index, newValue) => 
  {
        if (this.state.Jenkins == null) {
          this.state.Jenkins = {} 
        }

        this.setState({citype : newValue})  

        // var abc = this.state.Jenkins;
        // abc.ciName = newValue;
        // this.setState({Jenkins: abc})    
  }  

  handleChangerepoBranch = (event, newValue) => 
  {
        if (this.state.SCM == null) {
          this.state.SCM = {} 
        }

        var abc = this.state.SCM;
        abc.repoBranch = newValue;
        this.setState({SCM: abc})       
  }

  handleChangecdtool = (event, index, newValue) => 
  {
        if (this.state.CD == null) {
          this.state.CD = {}
        }
        var abc = this.state.CD;
        abc.deployTo = newValue;
        this.setState({CD: abc}) 

        var url = process.env.REACT_APP_API_URLS+'/getAllCd';
        fetch(url, {
                  headers: {
                    'Content-Type': 'application/json'
                  }
              }) 
        .then((response) => response.json()) 
        .then((responseJson) => { 
              //alert(JSON.stringify(responseJson.result)); 
              this.setState({
                cddata : responseJson.result
              })
              return responseJson.result; }) 
        .catch((error) => { console.error(error); });
  }

  handleChangecdid = (event, index, newValue) => 
  {
        if (this.state.CD == null) {
          this.state.CD = {}
        }
        var abc = this.state.CD;
        abc.cdName = newValue;
        this.setState({CD: abc})    
  }


  handleCleanCheck  = (event, index, newValue) => {
        if (this.state.BuildFaces == null) {
          this.state.BuildFaces = {}
        }                      
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
        //alert(value + ":" + name + ":"+ newValue)
        var abc = this.state.BuildFaces;
        abc[name] = value;
        //alert(JSON.stringify(abc))
        this.setState({BuildFaces: abc})
  }     


setDockerImageName = (event) =>{
        if (this.state.BuildFaces == null) {
          this.state.BuildFaces = {}
        }
        var abc = this.state.BuildFaces;
        abc.dockerImageName = event.target.value;
        this.setState({BuildFaces: abc})   
}

setDockerFilePath = (event) =>{
        if (this.state.BuildFaces == null) {
          this.state.BuildFaces = {}
        }
        var abc = this.state.BuildFaces;
        abc.dockerFilePath = event.target.value;
        this.setState({BuildFaces: abc})   
}
          
handleCreateJob = (event) => {
  var url = process.env.REACT_APP_API_URLS+'/createJob';
  fetch(url, {
                  headers: {
                    'Content-Type': 'application/json'
                  },
                  method: 'post',
                  body: JSON.stringify(this.state)
              }) 
        .then((response) => response.json()) 
        .then((responseJson) => { 
              //alert(JSON.stringify(responseJson.result)); 
              this.setState({
                status : responseJson.status
              })
              return responseJson.result; }) 
        .catch((error) => { console.error(error); });

  this.refs.nameInputField.focus();
}

handleEditJob = (event) => {
  var url = process.env.REACT_APP_API_URLS+`/updateJob/${this.state.Name}`;
  fetch(url, {
                  headers: {
                    'Content-Type': 'application/json'
                  },
                  method: 'PUT',
                  body: JSON.stringify(this.state)
              }) 
        .then((response) => response.json()) 
        .then((responseJson) => { 
              //alert(JSON.stringify(responseJson.result)); 
              this.setState({
                status : responseJson.status
              })
              return responseJson.result; }) 
        .catch((error) => { console.error(error); });

  this.refs.nameInputField.focus();
}

  handleClose = () => {
      this.setState({status: null});
  };

  handlePrev = () => {
    const {stepIndex} = this.state;
    if (stepIndex > 0) {
      this.setState({stepIndex: stepIndex - 1});
    }
  };

handleChange = (event, index, values) => this.setState({values});

  menuItems(values) {
    return names.map((name) => (
      <MenuItem
        key={name}
        insetChildren={true}
        checked={values && values.indexOf(name) > -1}
        value={name}
        primaryText={name}
      />
    ));
  }
 
 handleChangeSCM = (event, index, value) => this.setState({scmone:value});

 
 handleScmData = () =>{

  const aie = this.state.scmone;

  const data555 =[];

  { 
    this.state.scmdata.map((obj) => {
                                    if (obj.type === aie) {

                                      data555.push(obj.credentialName)
                                       
                                    }
                                  })}

    this.setState({credData11:data555})
}



 
  getStepContent(stepIndex) {
    //const {Name} = this.state.Name;
    const {values} = this.state;
    //alert(JSON.stringify(this.state.Buildpacks))
    var Buildpacks_Name = this.state.Buildpacks == null ? "" : this.state.Buildpacks.Name;
    var Buildpacks_Buildtool = this.state.Buildpacks == null ? "" : this.state.Buildpacks.Buildtool;
    var Buildpacks_PomXml = this.state.Buildpacks == null ? "" : this.state.Buildpacks.PomXml;
    var scm_DYNACRED = this.state.SCM == null ? "" : this.state.SCM.ScmId;
    var scm_Name = this.state.SCM == null ? "" : this.state.SCM.Name;
    var scm_repoBranch = this.state.SCM == null ? "" : this.state.SCM.repoBranch;
    var ci_Name = this.state.ConnectTo;
    var Jenkins_ciName = this.state.Jenkins == null ? "" : this.state.Jenkins.ciName;
    var ci_type = this.state.citype == null ? "" : this.state.citype;
    var cd_type = this.state.CD == null ? "" : this.state.CD.deployTo;
    var cd_id = this.state.CD == null ? "" : this.state.CD.cdName;
    var BuildFaces_Clean = this.state.BuildFaces == null ? "" : this.state.BuildFaces.Clean;
    var BuildFaces_Compile = this.state.BuildFaces == null ? "" : this.state.BuildFaces.Compile;
    var BuildFaces_Install = this.state.BuildFaces == null ? "" : this.state.BuildFaces.install;
    var BuildFaces_Test = this.state.BuildFaces == null ? "" : this.state.BuildFaces.test;
    var BuildFaces_Deploy = this.state.BuildFaces == null ? "" : this.state.BuildFaces.deploy;
    var BuildFaces_Docker = this.state.BuildFaces == null ? "" : this.state.BuildFaces.docker;
    var BuildFaces_Package = this.state.BuildFaces == null ? "" : this.state.BuildFaces.package;
    var BuildFaces_dockerImageName = this.state.BuildFaces == null ? "" : this.state.BuildFaces.dockerImageName;
    var BuildFaces_dockerFilePath = this.state.BuildFaces == null ? "" : this.state.BuildFaces.dockerFilePath;
    var status_value = null;
    status_value = this.state.status != null && this.state.status.trim != '' ?  
                        <AppBar
                          title={<span style={styles.title}>Result</span>}
                          iconElementLeft={<IconButton><NavigationClose onClick={this.handleClose}/></IconButton>}
                          iconElementRight={<CardText>{this.state.status}</CardText>} >  
                              <TextField type="hidden"  ref='nameInputField' />
                        </AppBar>  : null 
    var createorUpdateButton = this.state.createorUpdate != null && this.state.createorUpdate == 'create' ?  
    
                      <CardActions>  
                            <RaisedButton
                                label="Create Job"
                                primary={true}
                                onClick={this.handleCreateJob}
                            />
                      </CardActions>    

                      : <CardActions>  
                            <RaisedButton
                                label="Update Job"
                                primary={true}
                                onClick={this.handleEditJob}
                            />
                      </CardActions>                        


    const style = {
      card : {width : "20%", height : "40%", display: 'inline-block', margin : '2%'},
      title: {
        cursor: 'pointer',
      },
      centerPlaced : {textAlign : "center" },
      width700 : {width:'100%', maxWidth:700},
      chip: {marginTop: 0, padding: '7', width:'100%',maxWidth:700,margin:'auto' , overflowWrap: 'break-word'},
    }

    switch (stepIndex) {
      case 0:
        return  (


          <div style={style.centerPlaced}>
              
                <TextField  value={this.state.Name}   
                    onChange={(e, newValue) => this.setState({ Name: newValue})} 
                    style={{marginTop: 0, width:'100%',maxWidth:700,margin:'auto'}} 
                    floatingLabelText="Enter the Project Name"/>
              
          </div>

          );
      case 1, 2:
        return (

        <div  style={style.centerPlaced}> 

              <SelectField value={Buildpacks_Name} 
                          onChange={this.handleChangebuild} 
                          style={style.width700} 
                          floatingLabelText="Select Build type ">
                    <MenuItem value="Java" primaryText="Java" />
                    <MenuItem value="Nodejs" primaryText="Nodejs" disabled/>
                    <MenuItem value="PHP" primaryText="PHP" disabled/>
                    <MenuItem value="Static File" primaryText="Static File" disabled/>
              </SelectField>
              <br/>
              <SelectField value={Buildpacks_Buildtool} 
                            onChange={this.handleChangebuildtool} 
                            style={style.width700} 
                            floatingLabelText="Select Build Tool ">
                    <MenuItem value="Maven" primaryText="Maven" />
                    <MenuItem value="Gradle" primaryText="Gradle" disabled/>
              </SelectField>
              <br/>
              <TextField  value={Buildpacks_PomXml}   
                    onChange={(e, newValue) => {
                      var abc = this.state.Buildpacks;
                      abc.PomXml = newValue;
                      this.setState({Buildpacks: abc})
                    }}
                    style={style.width700} 
                    floatingLabelText="Build Path" />

 
        </div>


              );
      case 3:
        return (
          <div  style={style.centerPlaced}>
            <SelectField value={scm_Name} 
                    onChange={this.handleChangescmtype}
                    style={style.width700} 
                    floatingLabelText="Select SCM type ">
                <MenuItem value={"github"} primaryText="Github" />
                <MenuItem value={"gitlab"} disabled primaryText="Gitlab" />
                <MenuItem value={"svn"} disabled primaryText="SVN" />
            </SelectField>
          <br/>
              <SelectField  style={style.width700}
                                name="formField" 
                                value={scm_DYNACRED} 
                                onChange={this.handleChangescmtool} 
                                floatingLabelText="Select SCM repository Id ">
                                {this.state.scmdata.map((obj) => {
                                    if (obj) {
                                        return ( <MenuItem 
                                                    value={obj.ScmId}
                                                    key={obj.ScmId}  
                                                    primaryText={obj.ScmId}  />)
                                    }})}
              </SelectField>

              <TextField  value={scm_repoBranch}   
                  onChange={this.handleChangerepoBranch} 
                  style={style.width700} 
                  floatingLabelText="Enter repo branch" />
          </div>
          );
      case 4:
        return  (
            <div style={style.centerPlaced}>

                          <Chip style={style.chip}>
                            <Checkbox
                              label="Clean"
                              name="Clean"
                              checked={BuildFaces_Clean}
                              onCheck={this.handleCleanCheck}
                              style={styles.checkbox}
                            />
                          </Chip>
                      <br />
                          <Chip style={style.chip}>
                            <Checkbox
                              label="Compile"
                              name="Compile"
                              checked={BuildFaces_Compile}
                              onCheck={this.handleCleanCheck}
                              style={styles.checkbox}
                            />
                          </Chip>
                      <br />
                          <Chip style={style.chip}>    
                            <Checkbox
                              label="Package"
                              name="package"
                              checked={BuildFaces_Package}
                              onCheck={this.handleCleanCheck}
                              style={styles.checkbox}
                            />
                          </Chip>
                      <br />
                          <Chip style={style.chip}>
                            <Checkbox
                              label="Install"
                              name="install"
                              checked={BuildFaces_Install}
                              onCheck={this.handleCleanCheck}
                              style={styles.checkbox}
                            />
                          </Chip>
                      <br />
                          <Chip style={style.chip}>
                            <Checkbox
                              label="Test"
                              name="test"
                              checked={BuildFaces_Test}
                              onCheck={this.handleCleanCheck}
                              style={styles.checkbox}
                            />
                          </Chip>
                      <br />
                          <Chip style={style.chip}>
                            <Checkbox
                              label="Deploy"
                              name="deploy"
                              checked={BuildFaces_Deploy}
                              onCheck={this.handleCleanCheck}
                              style={styles.checkbox}
                            />
                          </Chip>
                      <br />
                          <Chip style={style.chip}>
                            <Checkbox
                              label="Docker"
                              name="docker"
                              checked={BuildFaces_Docker}
                              onCheck={this.handleCleanCheck}
                              style={styles.checkbox}
                            />    
                          </Chip>
                      <br />

                 {(() => {
                        //alert(this.state.BuildFaces.docker)
                        if (this.state.BuildFaces.docker) {
                            return(<div>
                              <p style={{color:this.state.BuildFaces.dockerImageName === '' ? 'red': 'green'}}>Enter the Docker Image Name</p>
                                  <TextField  value={BuildFaces_dockerImageName}   
                                  onChange={this.setDockerImageName} 
                                  style={{marginTop: 0, width:'100%',maxWidth:700,margin:'auto'}} />



                              <p style={{color:this.state.BuildFaces.dockerFilePath === '' ? 'red': 'green'}}>Enter the Docker File Path</p>       
                                  <TextField  value={BuildFaces_dockerFilePath}   
                                  onChange={this.setDockerFilePath} 
                                  style={{marginTop: 0, width:'100%',maxWidth:700,margin:'auto'}} />
                          </div>) 
                        }

                    })()}

            </div>);
      case 5:
        return  (

                  <div style={style.centerPlaced}>

                      <div>

                          <SelectField value={ci_type}
                                style={style.width700}
                                floatingLabelText="Select CI type "  
                                value={"Jenkins"} 
                                onChange={this.handleChangeciType}>
                              <MenuItem value={"Jenkins"} primaryText="Jenkins" />
                          </SelectField>
                          <br/>
                          <SelectField  style={{ width:'100%',maxWidth:700}}
                                            name="formField" 
                                            value={ci_Name} 
                                            onChange={this.handleChangeciid} 
                                            floatingLabelText="Select CI tool ">
                                            {this.state.cidata.map((obj) => {
                                                if (obj) {
                                                    return ( <MenuItem 
                                                                value={obj.ciName}
                                                                key={obj.ciName}  
                                                                primaryText={obj.ciName}  />)
                                                }})}
                          </SelectField>
                      </div>

                  </div>
                );
      case 6, 7:
        return  (

          <div style={style.centerPlaced}>
              
                <SelectField value={cd_type} 
                      onChange={this.handleChangecdtool}
                      style={{ width:'100%', maxWidth:700 }} 
                      floatingLabelText="Select CD Type">
                  <MenuItem value={"docker"} primaryText="Docker" />
                </SelectField>
                <br/>
                <SelectField  style={{ width:'100%', maxWidth:700}}
                                  value={cd_id} 
                                  onChange={this.handleChangecdid} 
                                  floatingLabelText="Select CD tool">
                                  {this.state.cddata.map((obj) => {
                                      if (obj) {
                                          return ( <MenuItem 
                                                      value={obj.cdName}
                                                      key={obj.cdName}  
                                                      primaryText={obj.cdName}  />)
                                      }})}
                </SelectField>
          </div> 
          );
      case 8:
        return  (

            <div>
                 
                       
                  <Card>
                    <CardTitle title= {JSON.stringify(this.state.Name, null, 2) }/>
                    <div>
                      <Card style={style.card}>
                          <CardText >
                              {JSON.stringify(this.state.SCM, null, 2) }
                          </CardText>
                      </Card>   
                      <Card style={style.card}>
                          <CardText >
                              {JSON.stringify(this.state.Jenkins, null, 2) }
                          </CardText>
                      </Card>   
                      <Card style={style.card}>
                          <CardText>
                              {JSON.stringify(this.state.CD, null, 2) }
                          </CardText>
                      </Card>   
                      <Card style={style.card}>
                          <CardText>
                              {JSON.stringify(this.state.BuildFaces, null, 2) }
                          </CardText>
                      </Card>   
                      <Card style={style.card}>
                          <CardText>
                              {JSON.stringify(this.state.Buildpacks, null, 2) }
                          </CardText>
                      </Card>
                    </div>   

                    {createorUpdateButton}

                  </Card>    

                  {status_value}

          </div>);
      default:
        return 'Click a step to get started.';
    }
  }

  render() {
    console.log(this.props);
    
    const { match = { params: {} } } = this.context.router.route;
    const { jobParamValue } = match.params;

    const {stepIndex, visited} = this.state;
    const styles = getStyles();
    if(true){

       return (
       
      <div style={styles.root}>
        
        <Stepper linear={false}>
          <Step completed={visited.indexOf(0) !== -1} active={stepIndex === 0}>
            <StepButton onClick={() => this.setState({stepIndex: 0})}>
             Project Name
            </StepButton>
          </Step>
          <Step completed={visited.indexOf(2) !== -1} active={stepIndex === 2}>
            <StepButton onClick={() => this.setState({stepIndex: 2})}>
             Build Packs
            </StepButton>
          </Step>
          <Step completed={visited.indexOf(3) !== -1} active={stepIndex === 3}>
            <StepButton onClick={() => this.setState({stepIndex: 3})}>
            Source Code Management 
            </StepButton>
          </Step>
          <Step completed={visited.indexOf(4) !== -1} active={stepIndex === 4}>
            <StepButton onClick={() => {this.onClickbuildFace();this.setState({stepIndex: 4})}}>
            Build Command 
            </StepButton>
          </Step>
          <Step completed={visited.indexOf(5) !== -1} active={stepIndex === 5}>
            <StepButton onClick={() => {this.handleChangecitool();this.setState({stepIndex: 5})}}>
            CI/CD
            </StepButton>
          </Step>
          <Step completed={visited.indexOf(7) !== -1} active={stepIndex === 7}>
            <StepButton onClick={() => 
                    { 
                      //alert(JSON.stringify(this.state))
                      this.setState({stepIndex: 7})
                    }
            }>
            Deployment 
            </StepButton>
          </Step>
          <Step completed={visited.indexOf(8) !== -1} active={stepIndex === 8}>
            <StepButton onClick={() => this.setState({stepIndex: 8})}>
            Summary
            </StepButton>
          </Step>
        </Stepper>

        


        <div style={styles.content}>
          <p>{this.getStepContent(stepIndex)}</p>
          {stepIndex !== null && stepIndex !== 8  &&(
            <div style={styles.actions}>
              <FlatButton
                label="Back"
                disabled={stepIndex === 0}
                onClick={this.handlePrev}
                style={styles.backButton}
              />
              <RaisedButton
                label="Next"
                primary={true}
                onClick={this.handleNext}
              />
            </div>
          )}
        </div>


      </div>
    );

    } else{

      return (

      <div>Hello Admin</div>
      );

    }

   
  }
}