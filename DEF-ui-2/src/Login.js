import React, { Component } from 'react';
import { Card, CardTitle } from "material-ui/Card";
//import TemplateRender from './templates/templateRenderer';
//import React from 'react';
import {cyan500} from 'material-ui/styles/colors';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import AppBar from 'material-ui/AppBar';
//import {Router, Route} from 'react-router';
import { HashRouter as Router } from "react-router-dom";
import App from "./App";
import TextField from 'material-ui/TextField';
import  RaisedButton from 'material-ui/RaisedButton';
import ReactDOM from "react-dom";


import './App.css';


class Login extends Component {

getToApp(){
	//alert("get into App");


	
    
	
}

  render() {

  	const muiTheme = getMuiTheme({
  palette: {
    textColor: cyan500,
  },
  appBar: {
    height: 50,
  },
});

// MuiThemeProvider takes the theme as a property and passed it down the hierarchy.
const Main = () => (
  <MuiThemeProvider muiTheme={muiTheme}>
    <AppBar title="My AppBar" />
  </MuiThemeProvider>
);
    return (
			<div style ={{witdh:'100px'}}>
			<Main />
				    <div className="App">
					       <Card>
                          <CardTitle title="Login " style={{ textTransform : 'capitalize', textAlign : "center"}} />

                      </Card>
                      <Card>
							<TextField
							hintText="User name"
							/><br />
							<br />
							<TextField
							hintText="password."
							/><br />

                      </Card>
                       <RaisedButton label="Primary" primary={true} style= {{margin: 12, color:'blue'}} onClick = {this.getToApp}/>
				  	</div>
		    </div>
    );
  }
}

export default Login;
