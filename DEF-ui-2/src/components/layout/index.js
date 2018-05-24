import React, { Component } from "react";
import AppBar from "material-ui/AppBar";
import Paper from "material-ui/Paper";
import Menu from "../menu/materialMenu";
import Footer from "../footer";
import SvgIcon from "material-ui/SvgIcon";

const AngularIcon = props => (
  <SvgIcon {...props}>
    <path d="M9.4 16.6L4.8 12l4.6-4.6L8 6l-6 6 6 6 1.4-1.4zm5.2 0l4.6-4.6-4.6-4.6L16 6l6 6-6 6-1.4-1.4z" />
  </SvgIcon>
);

class Layout extends Component {
  constructor(props) {
    super(props);
    this.state = { expand: true };
  }
  render() {
    const { menuItems, menuType } = this.props;
    const isExpandable = menuType === "menucollapser";
    const unexpandedStyle = {
      menuDiv: { width: "4%", display: "inline-block", verticalAlign:'top' },
      contentDiv: { width: "94%", display: "inline-block", paddingLeft: "1%",verticalAlign:'top' }
    };
    const expandedStyle = {
      menuDiv: { width: "25%", display: "inline-block" , verticalAlign: 'top'},
      contentDiv: { width: "75%", display: "inline-block", paddingLeft: "1%", verticalAlign : 'top' }
    };
    const { appTitle } = this.props;
    return (
      <div style={{ height: "100%" }}>
        <AppBar
          title={appTitle || "Altimetrik"}
          iconClassNameLeft="logo"
          className="logo"
        />
        <div style={this.state.expand ? expandedStyle.menuDiv : unexpandedStyle.menuDiv }>
          <Paper
            style={{ height: "100vh", marginTop: "10px", overflow: "hidden" }}
          >
            {isExpandable
              ? <AngularIcon
                  style={{ cursor: "pointer" }}
                  onClick={() => this.setState({ expand: !this.state.expand })}
                />
              : null}
            <Menu
              style={{ textAlign: "left" }}
              menuItems={menuItems}
              menuType={menuType}
            />
          </Paper>
        </div>
        <div
          style={this.state.expand ?  expandedStyle.contentDiv : unexpandedStyle.contentDiv }
        >
          {this.props.children}
        </div>
        <Footer />
      </div>
    );
  }
}

export default Layout;
