import React, { Component } from "react";
import Menu from "../components/menu/materialMenu";
import MaterialTable from "../components/table/materialtable";
import AppBar from "material-ui/AppBar";
import { Link, Route } from "react-router-dom";
import { layoutConfig } from "../components/HOC/layoutConfig";
import Paper from "material-ui/Paper";
import SvgIcon from "material-ui/SvgIcon";
import Footer from "../components/footer";
import HeaderNew from "../components/header/headernew.js";
import TransactForm from "../codegenerated/generatedCode";
import Remitter from "../components/field/remitter";
import ThemeGeneratorNew from "../components/handlebars/ThemeGeneratorNew";
import ConfigDetails from '../components/config/configdetails.js';
import IconMenu from 'material-ui/IconMenu';
import MenuItem from 'material-ui/MenuItem';
import IconButton from 'material-ui/IconButton';
import MoreVertIcon from 'material-ui/svg-icons/navigation/more-vert';

const AngularIcon = props => (
  <SvgIcon {...props}>
    <path d="M9.4 16.6L4.8 12l4.6-4.6L8 6l-6 6 6 6 1.4-1.4zm5.2 0l4.6-4.6-4.6-4.6L16 6l6 6-6 6-1.4-1.4z" />
  </SvgIcon>
);

class Main extends Component {
  constructor(props) {
    super(props);
    this.handleExpand = this.handleExpand.bind(this);
    this.renderRoutes = this.renderRoutes.bind(this);
    this.state = { expand: false };
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

  render() {
    
    const { expand } = this.state;
    const { MenuContext: { data: { menuItems } } , Header : { appTitle }} = this.props;
    const { Project: { path } } = this.props;
    return (
      <div style={{ height: "100%" }}>

        <div>
          <HeaderNew {...this.props}/>
        </div>
      
          <div>

                  <div
                    style={
                      expand
                        ? {
                            display: "inline-block",
                            width: "20%",
                            verticalAlign: "top"
                          }
                        : {
                            display: "inline-block",
                            width: "4%",
                            verticalAlign: "top"
                          }
                    }
                  >
                    <Paper style={{ height: "100vh", overflow: "hidden" }}>
                      <AngularIcon
                        style={{ cursor: "pointer" }}
                        onClick={this.handleExpand}
                      />
                      <Menu style={{ textAlign: "left" }} menuItems={menuItems} />
                    </Paper>
                  </div>
                  <div
                    style={
                      expand
                        ? {
                            display: "inline-block",
                            width: "78%",
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

                    <div style={{ bottom: "10%", position: "fixed" }}>
                      <ThemeGeneratorNew {...this.props} />
                    </div>

                  </div>
                </div>
            <Footer />
          </div>

    );
  }
}

export default layoutConfig("layout8")(Main);
