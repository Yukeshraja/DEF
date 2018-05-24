import React, { Component, PropTypes } from "react";
import { Route } from "react-router-dom";
import { Header, Footer } from "./layout1";
import Table from "../components/table";
import Field from "../components/field";
import ThemeGeneratorNew from "../components/handlebars/ThemeGeneratorNew";
import RemitterUI from "../codegenerated/remitter_kb";
import Beneficiary from "../codegenerated/beneficiary";
import ThemeConfig from "../components/handlebars/themeConfig.json";
import Handlebars from "handlebars";
import ThemeTemplate from "../components/handlebars/theme.handlebars";
import AppBar from "material-ui/AppBar";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import Altiplatform from "../components/handlebars/Altiplatform";
import Altiplatformport from "../components/handlebars/Altiplatformport";
import {
  cyan500,
  cyan700,
  pinkA200,
  grey900,
  grey100,
  grey300,
  grey400,
  grey500,
  white,
  darkBlack,
  fullBlack,
  indigo500
} from "material-ui/styles/colors";

const muiTheme = getMuiTheme({
  appBar: {
    height: 65
  }
});

class Layout14 extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    console.log(Altiplatformport);
    return (
      <MuiThemeProvider muiTheme={getMuiTheme(muiTheme)}>
        <div>
          <Route
            path="/"
            exact={true}
            render={() =>
              <div>
                <AppBar
                  title="Altimetik - Platform portal"
                  iconClassNameLeft="logo"
                  className="logoNew"
                  style={{ backgroundColor: "black" }}
                />
                <Altiplatform name="Durai" />
              </div>}
          />
          <Route path="/:id" component={Altiplatformport} />
        </div>
      </MuiThemeProvider>
    );
  }
}

export default Layout14;
