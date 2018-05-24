import React from "react";
import "./header.css";
//import logo from "../../logo.svg";
import { appendClassName } from '../../utils';

const Header = props => {
  return (
    <header className={appendClassName('container group',props.className)} style={props.customstyles}>
      <a href={props.appUrl} className="app">
        <img className="app-logo" src={props.imgUrl} />
        {props.appName}
      </a>
      <span>welcome</span>
    </header>
  );
};

Header.defaultProps = {
  appName: "App Name",
  appUrl : '#'
};

export default Header;
