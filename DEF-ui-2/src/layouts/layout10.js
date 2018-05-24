import React from "react";
import { Route, Link } from "react-router-dom";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import Header  from "../components/header";
import "../static/css/all.css";
import Paper from "material-ui/Paper";
import {Tabs, Tab} from 'material-ui/Tabs';
import Footer from "../components/footer";
import TabsExampleControlled from "../components/tabs"; 

const muiTheme = getMuiTheme({
  appBar: {
    height: 70
  }
});

const styles = {
  headline: {
    fontSize: 24,
    paddingTop: 16,
    marginBottom: 12,
    fontWeight: 400,
  },
};

const leftContainer = {
  height: 480,
  width: 940,
  margin: 10,
  float: 'left',
};

const rightContainer = {
  height: 480,
  width: 325,
  margin: 10,
  float: 'left',
};

const Main = props => {
  return (
    <MuiThemeProvider muiTheme={getMuiTheme(muiTheme)}>
      <div style={{ height: "100%" , width: "100%"}}>
        <Header className="header-bg logo" appName="Altimetrik"/>
            <div>

                <div id="a" style={{width: "75%", height: "100%", float:"left"}}>
                  <Paper style={leftContainer} zDepth={1}>
                    <div className="block">

                      <TabsExampleControlled />

                    </div>
                  </Paper>
                </div>

                <div id="b" style={{width: "25%", height: "100%", float:"right"}}>
                  <Paper style={rightContainer} zDepth={1}>
                    <div className="block">
                      <div className="row title pad-8-12">Vikas Sharma</div>
                      <div className="row pad-15-12">
                          <span className="row pad-8-12 sub-title align-left">Account Balance (&#8377;)</span>
                          <span className="row pad-8-12 sub-value align-left">2,57,747</span>
                      </div>
                      <div className="row pad-15-12">
                          <span className="row pad-8-12 sub-title align-left">Total Commission (&#8377;)</span>
                          <span className="row pad-8-12 sub-value align-left">32,114</span>
                      </div>
                      <div className="row pad-15-12">
                          <span className="row pad-8-12 sub-title align-left">Total Transactions (&#8377;)</span>
                          <span className="row pad-8-12 sub-value align-left">6,21,000</span>
                      </div>
                      <div className="row pad-15-12">
                          <span className="row pad-8-12 sub-title align-left">Total Transactions Count / day</span>
                          <span className="row pad-8-12 sub-value align-left">133</span>
                      </div>
                    </div>
                  </Paper>
                </div>


            </div>
        <Footer />
      </div>
    </MuiThemeProvider>
  );
};

export default Main;
