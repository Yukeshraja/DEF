//require('dotenv').config({silent: true});
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
//import { ValidatorForm, TextValidator} from 'react-material-ui-form-validator';
import DropDownMenu from 'material-ui/DropDownMenu';
import { Card, CardActions, CardHeader, CardMedia, CardTitle, CardText } from 'material-ui/Card';
import Paper from 'material-ui/Paper';
import {
  post,
  handleResponseError,
  convertJSON,
  Loader,
  capitalize
} from "../../utils";
import config from '../../templates/def_config.json';
import { Route } from "react-router-dom";
import Snackbar from 'material-ui/Snackbar';
import SCM from './scm';
import CICD from './CICD';
import Deployment from './Deployment';

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


const MenuData = {

  "styles": {
    "style": "Menu"
  },
  "menuType": "menucollapser",
  "position": "left-aligned",
  "data": {
    "menuHeading": "Alti Menu",
    "menuItems": [{ "link": "/view/remitter", "anchorText": "Remitter", "icon": "ContentInbox", "url": "http://192.168.91.51:8012/api/remitter" }, { "link": "/view/transactioninfo", "anchorText": "Transaction Info", "icon": "ActionGrade", "url": "http://192.168.91.51:8012/api/transactioninfo" }, { "link": "/view/beneficiary", "anchorText": "Beneficiary", "icon": "ContentSend", "url": "http://192.168.91.51:8012/api/beneficiary" }]
  },
  "Template": {
    "name": ""
  }
}

//const { MenuContext: { data: { menuItems } } } = this.MenuData.data.menuItems;

const menuItems = [{ "link": "/view/admin/scm", "anchorText": "Add SCM", "icon": "", "url": "" }, { "link": "/view/admin/cicd", "anchorText": "CICD", "icon": "", "url": "" }, { "link": "/view/admin/deployment", "anchorText": "Deployment", "icon": "", "url": "" }];
const style = {
  height: 200,
  width: 800,
  margin: 10,
  textAlign: 'left',
  display: 'block',
};
const menu_style = {
  display: 'inline-block',
  margin: '1px 8px 4px 0',
};


const buttonstyle = {
  margin: 12,
};

const scmvalid = false;

const scmAdd = false;


class AdminMenu extends React.Component {

  state = {
    userName: '',
    projectName: '',
    selectValue: ''
  }

  handleChange = (event, index, value) => this.setState({ selectValue: value });




  renderRoutes(expand) {
    //const { MenuContext: { data: { menuItems } } } = this.props;
    const routes = menuItems.map(({ url, link }, index) => {
      return (
        <Route
          path=""
          key={index}
          render={({ match: { params } }) => {
            return (
              <div></div>
            );
          }}
        />
      );
    });


    return routes.concat([
      <Route
        path="/view/admin/scm"
        key={routes.length}
        render={() => {
          return (
            <div><SCM /></div>
          );
        }}
      />,
      <Route
        path="/view/admin/cicd"
        key="cicd"
        render={() => {
          return (
            <div><CICD /></div>
          );
        }}

      />,
      <Route
        path="/view/admin/deployment"
        key="deployment"
        render={() => {
          return (
            <div><Deployment /></div>
          );
        }}
      />
    ]);
  }


  render() {
    return (<div>
      <div
        style={{
          display: "inline-block",
          width: "25%",
          verticalAlign: "top"
        }}
      >
        <Paper style={{ height: "100vh", overflow: "hidden" }}
        >
          <Menu1 style={{ textAlign: "left" }} menuItems={menuItems} />
        </Paper>
      </div>
      <div
        style={{
          display: "inline-block",
          width: "74%",
          marginLeft: "1%",
          verticalAlign: "top"
        }}
      >
        {this.renderRoutes(true)}
      </div>

    </div>);
  }
}


export default class Admin extends React.Component {

  state = {
    stepIndex: null,
    disable: true,
    visited: [],
    value: 1,
    SCM_Name: 1,
    SCM_gitlink: "",
    SCM_AuthType: 1,
    SCM_Creds: "",
    SCM_repo: "",
    CI_type: 1,
    CI_Link: "",
    CI_uptype: 1,
    CI_Arty: "",
    CI_rele: "",
    CI_snap: "",
    DeployData: "",
    SCM: {
      "Name": 1,
      "Link": "",
      "repoBranch": 1,
      "authType": 1,
      "CredentialID": ""
    },
    buildtoolval: 1,
    scmone: 1,
    scmtwo: 1,
    scmthree: "",
    cicdone: 1,
    cicdtwo: 2,
    cicdthree: "",
    dockerone: 1,
    dockertwo: 2,
    dockerthree: "",
    scnfour: 1,
    scmfive: 1,
    scmAdd: true,
  };



  componentWillMount() {
    const { stepIndex, visited } = this.state;
    this.setState({ visited: visited.concat(stepIndex) });
  }

  componentWillUpdate(nextProps, nextState) {
    const { stepIndex, visited } = nextState;
    if (visited.indexOf(stepIndex) === -1) {
      this.setState({ visited: visited.concat(stepIndex) });
    }
  }

  handleNext = () => {
    const { stepIndex } = this.state;
    if (stepIndex < 3) {
      this.setState({ stepIndex: stepIndex + 1 });
    }
  };

  // handleChangebuild = (event, index, value) => this.setState({value});

  // handleChangebuildtool = (event, index, buildtoolval) => this.setState({buildtoolval});


  handlePrev = () => {
    const { stepIndex } = this.state;
    if (stepIndex > 0) {
      this.setState({ stepIndex: stepIndex - 1 });
    }
  };

  handleChange = (event, index, values) => this.setState({ values });

  handleChangebuild = (event, index, value) => this.setState({ SCM_Name: value });

  handleChangeAuth = (event, index, value) => this.setState({ SCM_AuthType: value });

  handleChangebuildtype = (event, index, value) => this.setState({ scmtwo: value });

  handleChangeAuthType = (event, index, value) => this.setState({ CI_uptype: value });

  handleChangeCIType = (event, index, value) => this.setState({ CI_type: value });


  render() {
    const { stepIndex, visited } = this.state;
    const styles = getStyles();
    return (

      <div>
        <AdminMenu />
      </div>

    );
  }
}
