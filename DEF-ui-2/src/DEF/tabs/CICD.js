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

class CICD extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      stepIndex: null,
      disable: true,
      visited: [],
      value: 1,
      CI_Id : "",
      CI_type: 1,
      CI_Link: "",
      CI_uptype: 1,
      CI_Arty: "",
      CI_rele: "",
      CI_snap: "",
      DeployData: "",
      CI_DYNACRED: "",
      creddata: [],
      CI_Folder: "",
      credData11: [],
      showSnackBarSuccess: false,
      showSnackBarFailure: false
    };

    this.validateCIdtls = this.validateCIdtls.bind(this);
    this.addCIdtls = this.addCIdtls.bind(this);
    this.handleTouchTapFailure = this.handleTouchTapFailure.bind(this);
    this.handleTouchTapSuccess = this.handleTouchTapSuccess.bind(this);
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



  handleChangeAuthType = (event, index, value) => this.setState({ CI_uptype: value });

  handleChangeCIType = (event, index, value) => this.setState({ CI_type: value });

  handleCredChange = (event, index, value) => this.setState({ CI_DYNACRED: value });

  componentWillMount() {
    const creds = this.fetchCreds();
    console.log("creds are ..." + creds);
  }


ValidateIPaddress() 
{  
 if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(""))  
  {  
    return (true)  
  }  
alert("You have entered an invalid IP address!")  
return (false)  
} 



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


  validateCIdtls = () => {

    if(this.state.CI_Id != "" && this.state.CI_Link != "" && this.state.CI_type != "" && this.state.CI_uptype != "" && this.state.CI_Arty != "" ){
      this.setState({validData:true});

      const valid = 'validateCi'
    var url = process.env.REACT_APP_API_URLS + '/' + valid;
    fetch(url, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        "ciName": this.state.CI_Id,
        "ciURL": this.state.CI_Link,
        "ArtifactoryUrl": this.state.CI_Arty,
        "targetReleaseSynatax": this.state.CI_rele,
        "targetSnapshotSyntax": this.state.CI_snap,
        "CredentialID": this.state.CI_DYNACRED,
        "authType": this.state.CI_uptype,
        "ciType": this.state.CI_type,
        "ciFolder": this.state.CI_Folder,
      }),
    }).then((response) => response.json())
    .then(data => {
     
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


  addCIdtls = () => {


    const addCi = 'createCi'
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
        "ciName": this.state.CI_Id,
        "ciURL": this.state.CI_Link,
        "ArtifactoryUrl": this.state.CI_Arty,
        "targetReleaseSynatax": this.state.CI_rele,
        "targetSnapshotSyntax": this.state.CI_snap,
        "CredentialID": this.state.CI_DYNACRED,
        "authType": this.state.CI_uptype,
        "ciType": this.state.CI_type,
        "ciFolder": this.state.CI_Folder,
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

  checkCI_ID() { 

        var error1_check; 

     
        var abc = this.state.CI_Id;
        var error_check = (/[^a-zA-Z0-9_]/g) ? 'true' : 'false';
        if (/^[a-zA-Z0-9_]+$/.test(abc) ){

          error1_check = true;
          this.state.checkOne = error1_check;

          }else{

           error1_check = false;

           this.state.checkOne = error1_check;

          }
  }


  handleAuth = () => {

    const aie = this.state.CI_uptype;

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

        <TextField value={this.state.CI_Id} 
            onChange={(e, newValue) => this.setState({ CI_Id: newValue })}
             onBlur ={this.checkCI_ID()} 
            style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} 
            floatingLabelText="CI id" />
            {!this.state.checkOne ? (
            <p style={{color:'red', marginLeft:20}}>Enter the valid CI_ID</p>
            ) : (
            <p></p>
            )}

        <DropDownMenu value={this.state.CI_type} onChange={this.handleChangeCIType} style={{ marginTop: 0, width: '100%', maxWidth: 750 }}>
          <MenuItem value={1} disabled primaryText="Select Type" />
          <MenuItem value={"jenkins"} primaryText="Jenkins" />
        </DropDownMenu>
        <br />
        <TextField value={this.state.CI_Link} onChange={(e, newValue) => this.setState({ CI_Link: newValue })} style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} floatingLabelText="Link" />
          {!this.state.CI_Link ? (
            <p style={{color:'red', marginLeft:20}}>Enter the Link</p>
            ) : (
            <p></p>
            )}
        <br />
        <DropDownMenu value={this.state.CI_uptype} onChange={this.handleChangeAuthType} style={{ marginTop: 0, width: '100%', maxWidth: 750 }}>
          <MenuItem value={1} disabled primaryText="Select Type" />
          <MenuItem value={"up"} primaryText="Hostname with Username & Password" />
          <MenuItem value={"ups"} primaryText="Host with SSH Key" />
          <MenuItem value={"upss"} primaryText="SecretText" />
        </DropDownMenu>
        <SelectField style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 710 }}
          name="formField"
          floatingLabelText="Credentials"
          value={this.state.CI_DYNACRED}
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
        <TextField value={this.state.CI_Folder} onChange={(e, newValue) => this.setState({ CI_Folder: newValue })} style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} hintText="optional" floatingLabelText="CI Folder" />"(optional...)"
        <TextField required={true} value={this.state.CI_Arty} onChange={(e, newValue) => this.setState({ CI_Arty: newValue })} style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} floatingLabelText="Articraft URL" />
         {!this.state.CI_Arty ? (
            <p style={{color:'red', marginLeft:20}}>Enter the Articraft URL</p>
            ) : (
            <p></p>
            )}
        <br />
        <TextField value={this.state.CI_rele} onChange={(e, newValue) => this.setState({ CI_rele: newValue })} style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} floatingLabelText="target release syntax" />
         {!this.state.CI_rele ? (
            <p style={{color:'red', marginLeft:20}}>Enter the Target Release Syntax</p>
            ) : (
            <p></p>
            )}
        <TextField value={this.state.CI_snap} onChange={(e, newValue) => this.setState({ CI_snap: newValue })} style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} floatingLabelText="target snapshort syntax" />
        {!this.state.CI_snap ? (
            <p style={{color:'red', marginLeft:20}}>Enter the Target snapshort Syntax</p>
            ) : (
            <p></p>
            )}
        <br />
          {this.state.validData  === false ? (
        <p style={{color:'red', marginLeft:20}}>Fill All the Fields</p>
      ) : (
        <p></p>
        )}
        <div>
          <RaisedButton label="Validate" primary={true} style={buttonstyle} disabled={this.scmvalid} onClick={this.validateCIdtls} />
          <RaisedButton label="Add" primary={true} style={buttonstyle} disabled={this.state.scmAdd} onClick={this.addCIdtls} /></div>
      </Card>

    </div>);
  }
}


export default CICD;
