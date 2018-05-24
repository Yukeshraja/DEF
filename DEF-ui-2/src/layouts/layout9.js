import React, { Component }  from "react";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import MaterialTable from "../components/table/materialtable";
import AppBar from "material-ui/AppBar";
import { Link, Route } from "react-router-dom";
import Paper from "material-ui/Paper";
import { fetchCompdata } from "../components/HOC/fetchData";
import Menu from "../components/menu/materialMenu";
import Footer from "../components/footer";
import ThemeGenerator from "../components/handlebars/themeGenerator";
import HeaderNew from "../components/header/headernew.js";
import {
  lime500, cyan700, cyan500, amber400, orangeA400,
  pinkA200, blue700, blue500, blue300, 
  grey100, grey300, grey400, grey500,
  white, darkBlack, fullBlack,indigo500
} from 'material-ui/styles/colors';
import {fade} from 'material-ui/utils/colorManipulator';
import ConfigDetails from '../components/config/configdetails.js';

const muiTheme = getMuiTheme({
  appBar: {
    height: 70
  },
  palette: {
    primary1Color: amber400,
    primary2Color: cyan700,
    primary3Color: grey400,
    accent1Color: pinkA200,
    accent2Color: grey100,
    accent3Color: grey500,
    textColor: darkBlack,
    alternateTextColor: white,
    canvasColor: orangeA400,
    borderColor: grey300,
    disabledColor: fade(darkBlack, 0.3),
    pickerHeaderColor: cyan500,
    clockCircleColor: fade(darkBlack, 0.07),
    shadowColor: fullBlack,
  },
});

class Main extends Component {
  constructor(props) {
    super(props);
    this.handleExpand = this.handleExpand.bind(this);
    this.renderRoutes = this.renderRoutes.bind(this);
    this.state = { expand: true };
  }
  handleExpand() {
    this.setState((state, props) => {
      return { expand: !state.expand };
    });
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
                width={expand ? 1072 : 1288}
              />
            );
          }}
        />
      );
    });

    return routes.concat(
      <Route
        path="/"
        key={routes.length}
        render={() => {
          return (
            <MaterialTable
              url={menuItems[0].url}
              keyName="tableData"
              fieldSetURL={this.props.fieldSetURL}
              width={expand ? 1072 : 1288}
            />
          );
        }}
        exact={true}
      />,      
      <Route
        path="/view/config"
        render={() => {
          return (
            <ConfigDetails {...this.props}/>
          );
        }}
      />
    );
  }

  render() {
    const { expand } = this.state;
    const { MenuContext: { data: { menuItems } } } = this.props;
    return (
      <MuiThemeProvider muiTheme={getMuiTheme(muiTheme)}>
        <div style={{ height: "100%" }}>
          <div>
            <HeaderNew {...this.props}/>
          </div>
          <div
            style={
              expand
                ? {
                    float: "left",
                    display: "inline-block",
                    width: "15%",
                    verticalAlign: "top"
                  }
                : {
                    display: "inline-block",
                    width: "4%",
                    verticalAlign: "top"
                  }
            }
          >
                <Paper  style={{ height: "100vh", overflow: "hidden" }}>
                    <Menu style={{textAlign: 'left'}}  menuItems={menuItems}/>
                </Paper>
          </div>
          <div
            style={
              expand
                ? {
                    float: "left",
                    display: "inline-block",
                    width: "83%",
                    marginLeft: "1%",
                    verticalAlign: "top"
                  }
                : {
                    display: "inline-block",
                    width: "94%",
                    marginLeft: "1%",
                    verticalAlign: "top"
                  }
            }
          >
            {this.renderRoutes(expand)}



          </div>



          <Footer />
        </div>
      </MuiThemeProvider>
    );
    
  }
}

export default Main;
