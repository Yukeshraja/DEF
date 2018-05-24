import React from 'react';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import Menu from 'material-ui/Menu';
import Menu1 from "../../components/menu/materialMenu";
//import { ValidatorForm, TextValidator} from 'react-material-ui-form-validator';
import {
  Step,
  Stepper,
  StepButton,
} from 'material-ui/Stepper';
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import ExpandTransition from 'material-ui/internal/ExpandTransition';
import Snackbar from 'material-ui/Snackbar';
import DropDownMenu from 'material-ui/DropDownMenu';
import { Card, CardActions, CardHeader, CardMedia, CardTitle, CardText } from 'material-ui/Card';


class Deployment extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      stepIndex: null,
      disable: true,
      visited: [],
      cdName : "",
      DEP_deploy: 1,
      DEP_AuthType: 1,
      DEP_cred: 1,
      DEP_depserver: "",
      DEP_AppNumber: "",
      DEP_DockerExposedNumber: "",
      DEP_RegService: "",
      DEP_RegistryNumber: "",
      DeployData: "",
      creddata: [],
      credData11: [],
      DEP_DYNACRED: "",
      showSnackBarSuccess: false,
      showSnackBarFailure: false
    };
    this.validateCDdtls = this.validateCDdtls.bind(this);
    this.addCDdtls = this.addCDdtls.bind(this);
  }
  componentWillMount() {
    const creds = this.fetchCreds();
    console.log("creds are ..." + creds);
  }

  handleTouchTapSuccess = () => {
    this.setState({
      showSnackBarSuccess: true
    });
  };

  handleTouchTapFailure = () => {
    this.setState({
      showSnackBarFailure: true
    });
  };

  handleRequestClose = () => {
    this.setState({
      showSnackBarSuccess: false,
      showSnackBarFailure: false
    });
  };



  fetchCreds = () => {
    const getcred = 'getAllCredentials'
    //alert("url is...111"+process.env);
    //alert("url is..."+process.env.API_URLS);
    //alert("url is..."+process.env.REACT_APP_API_URLS);
    var url = process.env.REACT_APP_API_URLS + '/' + getcred;
    fetch(url, {
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then((response) => {
        return response.json()
      })
      .then((json) => {
        this.setState({
          creddata: json.result
        })
        console.log('parsed json', json)
      })
      .catch((ex) => {
        console.log('parsing failed', ex)
      })
  }


  validateCDdtls = () => {

    if(this.state.cdName != "" && this.state.DEP_deploy != "" && this.state.DEP_depserver != "" && this.state.DEP_DYNACRED != "" &&  this.state.DEP_AppNumber != "" && this.state.DEP_DockerExposedNumber != "" && this.state.DEP_RegistryNumber != "" &&  this.state.DEP_RegService != "" &&  this.state.DeployData != "" &&  this.state.DEP_AuthType != ""){
      this.setState({validData:true});
      const valid = 'validateCd'
    var url = process.env.REACT_APP_API_URLS + '/' + valid;
    fetch(url, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        "cdName": this.state.cdName,
        "deployTo": this.state.DEP_deploy,
        "deploymentServer": this.state.DEP_depserver,
        "CredentialID": this.state.DEP_DYNACRED,
        "appTargetPortNumber": this.state.DEP_AppNumber,
        "dockerExposedPortNumber": this.state.DEP_DockerExposedNumber,
        "dockerImgRegistryPort": this.state.DEP_RegistryNumber,
        "dockerImgRegistryServer": this.state.DEP_RegService,
        "bash": this.state.DeployData,
        "authType": this.state.DEP_AuthType,
      }),
    }).then((response) => response.json())
    .then(data => {
      // ChromeSamples.log('Created Gist:', data.html_url);
      // if (response.ok) {
      //   console.log("status is..." + data.status);
      // }
      console.log("data is..." + JSON.stringify(data));
      if (data.status === 'SUCCESS') {
        this.handleTouchTapSuccess();
      }
      else if (data.status === 'FAILURE') {
        this.handleTouchTapFailure();
      }
    })

    }else{
      this.setState({validData:false});

    }

    
  }


  addCDdtls = () => {


    const addCi = 'createCd'
    //alert("url is...111"+process.env);
    //alert("url is..."+process.env.API_URLS);
    //alert("url is..."+process.env.REACT_APP_API_URLS);
    var url = process.env.REACT_APP_API_URLS + '/' + addCi;
    fetch(url, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        "cdName" : this.state.cdName,
        "deployTo": this.state.DEP_deploy,
        "deploymentServer": this.state.DEP_depserver,
        "CredentialID": this.state.DEP_DYNACRED,
        "appTargetPortNumber": this.state.DEP_AppNumber,
        "dockerExposedPortNumber": this.state.DEP_DockerExposedNumber,
        "dockerImgRegistryPort": this.state.DEP_RegistryNumber,
        "dockerImgRegistryServer": this.state.DEP_RegService,
        "bash": this.state.DeployData,
        "authType": this.state.DEP_AuthType,
      }),
    }).then((response) => response.json())
    .then(data => {
      // ChromeSamples.log('Created Gist:', data.html_url);
      // if (response.ok) {
      //   console.log("status is..." + data.status);
      // }
      console.log("data is..." + JSON.stringify(data));
      if (data.status === 'SUCCESS') {
        this.handleTouchTapSuccess();
      }
      else if (data.status === 'FAILURE') {
        this.handleTouchTapFailure();
      }
    })
  }


checkDeploy() { 

        var error1_check; 

     
        var abc = this.state.cdName;
        //var error_check = (/[^a-zA-Z0-9_]/g) ? 'true' : 'false';
        if (/^[a-zA-Z0-9_]+$/.test(abc) ){

          error1_check = true;
          this.state.checkOne = error1_check;

          }else{

           error1_check = false;

           this.state.checkOne = error1_check;

          }
  }

  handleDeployType = (event, index, value) => this.setState({ DEP_deploy: value });

  handleChangeAuthType = (event, index, value) => this.setState({ DEP_AuthType: value });

  //handleChangeCred = (event, index, value) => this.setState({DEP_cred:value});

  handleCredChange = (event, index, value) => this.setState({ DEP_DYNACRED: value });

   validRegistryPort() { 

        var error1_check; 

     
        var abc = this.state.DEP_RegistryNumber;
       // var error_check = (/[^0-9]/g) ? 'true' : 'false';
        if (/^[0-9]{1,5}$/.test(abc) ){

          error1_check = true;
          this.state.checkThree = error1_check;

          }else{

           error1_check = false;

           this.state.checkThree = error1_check;

          }
  }

    validDeployServer(){

       var error1_check; 

     
        var abc = this.state.DEP_depserver;
       // var error_check = (/[^0-9]/g) ? 'true' : 'false';
         if (/^[a-zA-Z0-9:./]+$/.test(abc) ){

          error1_check = true;
          this.state.checkFour = error1_check;

          }else{

           error1_check = false;

           this.state.checkFour = error1_check;

          }


    }


    validRegistryServer(){

       var error1_check; 

     
        var abc = this.state.DEP_RegService;
       // var error_check = (/[^0-9]/g) ? 'true' : 'false';
         if (/^[a-zA-Z0-9:/.]+$/.test(abc) ){

          error1_check = true;
          this.state.checkFive = error1_check;

          }else{

           error1_check = false;

           this.state.checkFive = error1_check;

          }


    }
  

   validDockerPort() { 

        var error1_check; 

     
        var abc = this.state.DEP_DockerExposedNumber;
       // var error_check = (/[^0-9]/g) ? 'true' : 'false';
        if (/^[0-9]{1,5}$/.test(abc) ){

          error1_check = true;
          this.state.checkTwo = error1_check;

          }else{

           error1_check = false;

           this.state.checkTwo = error1_check;

          }
  }

   validAppPort() { 

        var error1_check; 

     
        var abc = this.state.DEP_AppNumber;
       // var error_check = (/[^0-9]/g) ? 'true' : 'false';
        if (/^[0-9]{1,5}$/.test(abc) ){

          error1_check = true;
          this.state.checkThree = error1_check;

          }else{

           error1_check = false;

           this.state.checkThree = error1_check;

          }
  }

  handleAuth = () => {

    const aie = this.state.DEP_AuthType;

    const data555 = [];

    {
      this.state.creddata.map((obj) => {
        if (obj.type === aie) {

          data555.push(obj.credentialName)

        }
      })
    }

    this.setState({ credData11: data555 })
  }


  render() {
    const buttonstyle = {
      margin: 12,
    };
    return (<div>
      <div>
        <Snackbar
          open={this.state.showSnackBarSuccess}
          message="SUCCESS!!"
          autoHideDuration={2000}
          onRequestClose={this.handleRequestClose}
        />
        <Snackbar
          open={this.state.showSnackBarFailure}
          message="FAILURE!!"
          autoHideDuration={2000}
          onRequestClose={this.handleRequestClose}
        />
      </div>
      <Card>

        <TextField value={this.state.cdName} 
            onChange={(e, newValue) => this.setState({ cdName: newValue })} 
              onBlur ={this.checkDeploy()} 
            style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} 
            floatingLabelText="CD id" />
         {!this.state.checkOne ? (
            <p style={{color:'red', marginLeft:20}}>Enter the valid CD_ID</p>
            ) : (
            <p></p>
            )}
        <br />
        <DropDownMenu value={this.state.DEP_deploy} onChange={this.handleDeployType} style={{ marginTop: 0, width: '100%', maxWidth: 750 }}>
          <MenuItem value={1} disabled primaryText="Select Type" />
          <MenuItem value={"docker"} primaryText="Docker" />
        </DropDownMenu>
        <DropDownMenu value={this.state.DEP_AuthType} onChange={this.handleChangeAuthType} 
                                                      style={{ marginTop: 0, width: '100%', maxWidth: 750 }}>
          <MenuItem value={1} disabled primaryText="Select Type" />
          <MenuItem value={"up"} primaryText="Hostname with Username & Password" />
          <MenuItem value={"ups"} primaryText="Host with SSH Key" />
          <MenuItem value={"upss"} primaryText="SecretText" />
        </DropDownMenu>
        <SelectField style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 710 }}
          name="formField"
          floatingLabelText="Credentials"
          value={this.state.DEP_DYNACRED}
          onClick={this.handleAuth}
          onChange={this.handleCredChange} >
          {this.state.credData11.map((obj) => {
            if (obj) {
              return (<MenuItem
                value={obj}
                key={this.state.credData11.indexOf(obj)}
                primaryText={obj} />)
            }
          })}
        </SelectField>
        <br />

        <TextField value={this.state.DEP_depserver} onChange={(e, newValue) => this.setState({ DEP_depserver: newValue })} onBlur ={this.validDeployServer()} style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} floatingLabelText="Deployment Server" />
        {!this.state.checkFour ? (
            <p style={{color:'red', marginLeft:20}}>Enter the a Valid Deployment Server</p>
            ) : (
            <p></p>
            )}
        <br />
        <br />
        <TextField value={this.state.DEP_AppNumber} onChange={(e, newValue) => this.setState({ DEP_AppNumber: newValue })} onBlur ={this.validAppPort()} style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} floatingLabelText="App Port Number" />
        
          {!this.state.checkThree ? (
            <p style={{color:'red', marginLeft:20}}>Enter the valid Registry Port</p>
            ) : (
            <p></p>
            )}
        <TextField value={this.state.DEP_DockerExposedNumber} onChange={(e, newValue) => this.setState({ DEP_DockerExposedNumber: newValue })} onBlur ={this.validDockerPort()} style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} floatingLabelText="Docker Exposed Port Number" />
      
         {!this.state.checkTwo ? (
            <p style={{color:'red', marginLeft:20}}>Enter the valid Docker Exposed  Port</p>
            ) : (
            <p></p>
            )}
        <TextField value={this.state.DEP_RegService} onChange={(e, newValue) => this.setState({ DEP_RegService: newValue })} onBlur ={this.validRegistryServer()} style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} floatingLabelText="Registry Server" />
       
         {!this.state.checkFive ? (
            <p style={{color:'red', marginLeft:20}}>Enter the a Valid Registy Server</p>
            ) : (
            <p></p>
            )}
        <TextField value={this.state.DEP_RegistryNumber} onChange={(e, newValue) => this.setState({ DEP_RegistryNumber: newValue })}  onBlur ={this.validRegistryPort()}  style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} floatingLabelText="Docker Registry Port Number" />
         <p style={{color:this.state.checkThree  === true ? 'green': 'red', marginLeft:20}}></p>
          {!this.state.DEP_RegistryNumber ? (
            <p style={{color:'red', marginLeft:20}}>Enter the valid Registry Port</p>
            ) : (
            <p></p>
            )}
        <br />
        <label style={{ marginTop: 0, marginLeft: 360, width: '100%', maxWidth: 700, fontWeight: "bold" }}>Bash</label>
        <br />

        <textarea value={this.state.DeployData} rows="3" onChange={(e, newValue) => this.setState({ DeployData: newValue })} placeholder="" style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }}></textarea>
        <br />
         {this.state.validData  === false ? (
        <p style={{color:'red', marginLeft:20}}>Fill All the Fields</p>
      ) : (
        <p></p>
        )}
        <div>
          <RaisedButton label="Validate" primary={true} style={buttonstyle} disabled={this.scmvalid} onClick={this.validateCDdtls} />
          <RaisedButton label="Add" primary={true} style={buttonstyle} disabled={this.state.scmAdd} onClick={this.addCDdtls} /></div>
      </Card>

    </div>);
  }
}




export default Deployment;