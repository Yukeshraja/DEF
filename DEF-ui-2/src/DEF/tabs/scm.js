import React from 'react';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import Menu from 'material-ui/Menu';
import Popup from 'react-popup';
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

class SCM extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      stepIndex: null,
      disable: true,
      visited: [],
      value: 1,
      SCMid: 1,
      SCM_gitlink: "",
      SCM_AuthType: 1,
      SCM_Creds: "",
      SCM_DYNACRED: "",
      SCM_ID: "",
      SCM_Name: 1,
      SCM_repo: "",
      creddata: [],
      credData11: [],
      errorData: "",
      showSnackBarSuccess: false,
      showSnackBarFailure: false,
      errorText:'',
      value1:props.value
    };
    this.fetchCreds = this.fetchCreds.bind(this);
    this.handleAuth = this.handleAuth.bind(this);
    this.validateSCMdtls = this.validateSCMdtls.bind(this);
    this.addSCMdtls = this.addSCMdtls.bind(this);
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

  handleTouchTapFailure = (response) => {
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

  validateSCMdtls = () => {

    const valid = 'validateScm'
    if(this.state.SCM_Name != "" && this.state.SCM_ID != "" && this.state.SCM_AuthType != "" && this.state.SCM_gitlink != "" && this.state.SCM_DYNACRED != ""){

      this.setState({validData:true})

          //alert("url is...111"+process.env);
    //alert("url is..."+process.env.API_URLS);
    //alert("url is..."+process.env.REACT_APP_API_URLS);
    var url = process.env.REACT_APP_API_URLS + '/' + valid;
    fetch(url, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        "Name": this.state.SCM_Name,
        "Link": this.state.SCM_gitlink,
        "ScmId": this.state.SCM_ID,
        "authType": this.state.SCM_AuthType,
        "CredentialID": this.state.SCM_DYNACRED
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
       this.setState({validData:false})
    }

  }

  addSCMdtls = () => {

    const createSCM = 'createScm'
    //alert("url is...111"+process.env);
    //alert("url is..."+process.env.API_URLS);
    //alert("url is..."+process.env.REACT_APP_API_URLS);
    var url = process.env.REACT_APP_API_URLS + '/' + createSCM;
    fetch(url, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        "Name": this.state.SCM_Name,
        "Link": this.state.SCM_gitlink,
        "ScmId": this.state.SCM_ID,
        "authType": this.state.SCM_AuthType,
        "CredentialID": this.state.SCM_DYNACRED
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

  handleChangebuild = (event, index, value) => this.setState({ SCM_Name: value });

  handleChangeAuth = (event, index, value) => this.setState({ SCM_AuthType: value });

  handleCredChange = (event, index, value) => this.setState({ SCM_DYNACRED: value });

 
//SCM_ID
  
  checkSCM_ID() { 

        var error1_check; 

     
        var abc = this.state.SCM_ID;
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

    const aie = this.state.SCM_AuthType;

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

       <div>
        <TextField value={this.state.SCM_ID} 
            onChange={(e, newValue) => this.setState({ SCM_ID: newValue })}
            onBlur ={this.checkSCM_ID()}
             
            style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} 
            floatingLabelText="scm id" />
         {!this.state.checkOne ? (
        <p style={{color:'red', marginLeft:20}}>Enter the valid SCM ID</p>
      ) : (
        <p></p>
      )}

         </div>
        
         <br />
        <DropDownMenu value={this.state.SCM_Name} onChange={this.handleChangebuild} style={{ marginTop: 0, width: '100%', maxWidth: 750 }}>
          <MenuItem value={1} disabled primaryText="Select Name" />
                <MenuItem value={"github"} primaryText="Github" />
                <MenuItem value={"gitlab"} disabled primaryText="Gitlab" />
                <MenuItem value={"svn"} disabled primaryText="SVN" />
        </DropDownMenu>
        <br />
        <TextField value={this.state.SCM_gitlink} onChange={(e, newValue) => this.setState({ SCM_gitlink: newValue })} style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 700 }} floatingLabelText="Github Link" />
         <p style={{color:this.state.SCM_gitlink  === '' ? 'red': 'green', marginLeft:20}}></p>
         {this.state.SCM_gitlink  === '' ? (
        <p style={{color:'red', marginLeft:20}}>Enter the Github link value</p>
      ) : (
        <p></p>
        )}
        <br />

        <DropDownMenu value={this.state.SCM_AuthType} onChange={this.handleChangeAuth} style={{ marginTop: 0, width: '100%', maxWidth: 750 }}>
          <MenuItem value={1} disabled primaryText="Select Type" />
          <MenuItem value={"up"} primaryText="Hostname with Username & Password" />
          <MenuItem value={"ups"} primaryText="Host with SSH Key" />
          <MenuItem value={"upss"} primaryText="SecretText" />
        </DropDownMenu>
        <br />
        <SelectField style={{ marginTop: 0, marginLeft: 20, width: '100%', maxWidth: 710 }}
          name="formField"
          floatingLabelText="Credentials"
          value={this.state.SCM_DYNACRED}
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
         {this.state.validData  === false ? (
        <p style={{color:'red', marginLeft:20}}>Fill All the Fields</p>
      ) : (
        <p></p>
        )}

        <div>
          <RaisedButton label="Validate" primary={true} style={buttonstyle} disabled={this.scmvalid} onClick={this.validateSCMdtls} />
          <RaisedButton label="Add" primary={true} style={buttonstyle} disabled={this.state.scmAdd} onClick={this.addSCMdtls} /></div>
      </Card>

    </div>);
  }
}


export default SCM;