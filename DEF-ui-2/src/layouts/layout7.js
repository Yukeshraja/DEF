import React from "react";
import injectTapEventPlugin from "react-tap-event-plugin";
import { List, ListItem } from "material-ui/List";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import AppBar from "material-ui/AppBar";
import { Link, Route } from "react-router-dom";
import "../static/css/all.css";
import Paper from "material-ui/Paper";
import { FieldGroup } from "../components/field/fieldset";
import { fetchData } from "../components/HOC/fetchData";
import HeaderNew from "../components/header/headernew.js";
import MaterialTable from "../components/table/materialtable";
import Remitter from "../components/field/remitter";
import ConfigDetails from '../components/config/configdetails.js';
import Menu from "../components/menu/materialMenu";
import Footer from "../components/footer";

injectTapEventPlugin();

const muiTheme = getMuiTheme({
  appBar: {
    height: 70
  }
});

class Main extends React.Component  {

  constructor(props) {
    super(props);
  }

renderRoutes(expand) {
    const { MenuContext: { data: { menuItems } } } = this.props;
    const routes = menuItems.map(({ url, link }, index) => {
      return (
        <Route
          path={link}
          key={index}
          render={({ match: { params } }) => {
            return (
              <MaterialTable
                url={url}
                keyName="tableData"
                fieldSetURL={this.props.fieldSetURL}
                fileUploadAPI={this.props.fileUploadAPI}
                width={expand ? 1072 : 1288}
              />
            );
          }}
        />
      );
    });


    return routes.concat([
      <Route
        path="/"
        key={routes.length}
        render={() => {
          return (
            <MaterialTable
              url={menuItems[0].url}
              keyName="tableData"
              fieldSetURL={this.props.fieldSetURL}
              fileUploadAPI={this.props.fileUploadAPI}
              width={expand ? 1072 : 1288}
            />
          );
        }}
        exact={true}
      />,
      <Route
        path="/test"
        render={() => {
          return (
            <Remitter
            />
          );
        }}
        key={routes.length + 1}
      />,      
      <Route
        path="/view/config"
        render={() => {
          return (
            <ConfigDetails {...this.props}/>
          );
        }}
      />
    ]);
  }


  render () {
    const { MenuContext: { data: { menuItems } } } = this.props;
    return (
        <div style={{ height: "100%" }}>
          <div>
            <HeaderNew {...this.props}/>
          </div>
          <div
            style={{
              display: "inline-block",
              width: "25%",
              verticalAlign: "top"
            }}
          >
            <Paper style={{ height: "100vh", overflow: "hidden" }}
            >
              <Menu style={{ textAlign: "left" }} menuItems={menuItems} />
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

           <Footer />
        </div>
  )}
}

export default Main;
