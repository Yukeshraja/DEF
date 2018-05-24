import React from "react";
import { Route, Link } from "react-router-dom";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import "../../static/css/all.css";
import Paper from "material-ui/Paper";
import {Tabs, Tab} from 'material-ui/Tabs';
import MaterialTable from "../table/materialtable";


export default class TabsExampleControlled extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      value: 'remitter',
    };
  }

  handleChange = (value) => {
    this.setState({
      value: value,
    });
  };

  render() {
    return (
      <Tabs
        value={this.state.value}
        onChange={this.handleChange}
      >
        <Tab label="Remitter" value="remitter">
          <div  className="row mar-20-16 vscrolling_container">
              <MaterialTable url={'http://192.168.94.119:5555/api/remitter'} keyName='tableData' apiName="remitter"/> 
          </div>
        </Tab>
        <Tab label="Beneficiary" value="beneficiary">
          <div  className="row mar-20-16 vscrolling_container">
              <MaterialTable url={'http://192.168.94.119:5555/api/beneficiary'} keyName='tableData' apiName="beneficiary"/>
          </div>
        </Tab>
      </Tabs>
    );
  }
}