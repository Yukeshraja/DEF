import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import Login from "./Login"
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import "./static/css/common.css";
import "./static/css/reset.css";
import "./index.css";
import darkBaseTheme from 'material-ui/styles/baseThemes/darkBaseTheme';
import { Provider } from "react-redux";
import { HashRouter as Router } from "react-router-dom";
import reducers from "./reducers";
import thunk from "redux-thunk";
import { createStore, applyMiddleware } from "redux";
import { createLogger} from "redux-logger";
import Immutable from 'immutable';
import DevLog from './DEF/tabs/index';
import UserForm from './DEF/tabs/index' ;
import GetJob from './DEF/tabs/getJob';
import FetchCredentials from './DEF/tabs/credmngt';
import Admin from './DEF/tabs/admin';
import SCM from './DEF/tabs/admin';
import CICD from './DEF/tabs/admin';
import Deployment from './DEF/tabs/admin';
import Logo1 from './DEF/header/index';
import {blueGrey500
} from 'material-ui/styles/colors';
import {
  cyan400
} from 'material-ui/styles/colors';
import muiConfig from './themeConfig';
//import DevLog from './DEF/tabs/index';
import { Route } from "react-router-dom";
import LandingPage from './DEF/header/LandingPage';

const logger = createLogger({
  stateTransformer: (state) => {
    let newState = {};

    for (var i of Object.keys(state)) {
      if (Immutable.Iterable.isIterable(state[i])) {
        newState[i] = state[i].toJS();
      } else {
        newState[i] = state[i];
      }
    };

    return newState;
  }
});




const muiTheme = getMuiTheme({
  palette: {
    textColor: blueGrey500
,
  },
  appBar: {
    height: 70,
  },
});


const store = createStore(reducers, applyMiddleware(thunk, logger));

ReactDOM.render(
  <Provider store={store}>
    <Router  basename="/DEF">
    <div>
      <MuiThemeProvider muiTheme={muiTheme}>
      
        <div>

            <App />

            <div className="content">
                    
                   
                   <Route exact path="/" component={LandingPage} />

                    <Route path="/view/admin" 
                      render={() => {
                        return (
                          <Admin />
                        );
                      }}
                    />
                    
                    <Route path="/view/updateJob/:jobParamValue" 
                      render={() => {
                        return (
                          <UserForm />
                        );
                      }}
                    />

                    <Route path="/view/developer" 
                      render={() => {
                        return (
                          <UserForm />
                        );
                      }}
                    />                    

                    <Route path="/view/getjobs" 
                      render={() => {
                        return (
                          <GetJob />
                        );
                      }}
                    />
                    <Route path="/view/config"
                      render={() => {
                        return (
                          <UserForm />
                        );
                      }}
                    />
                    <Route path="/view/landing"
                      render={() => {
                        return (
                          <LandingPage />
                        );
                      }}
                    />
                    <Route path="/view/credmgmt" render={ () => {
                        return (
                          <FetchCredentials />
                        );
                    }} />
              </div>
        </div>
      </MuiThemeProvider>
      </div>
    </Router>
  </Provider>,
  document.getElementById("root")
);
