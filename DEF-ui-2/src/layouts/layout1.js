import React from "react";
import { Route, Link } from "react-router-dom";
import Field from "../components/field";
export const Layout1 = props => {
  const { MenuContext } = props;
  return (
    <div>
      <Header
        {...props}
        customstyles={{
          height: "100px",
          border: "1px solid red",
          backgroundColor: "red"
        }}
      />
      <div className="content">
        <Menu {...MenuContext} />
        <Route path="/" component={Field} exact={true} />
        <Route path="/view/:templateName" component={Field} />
        <div style={{ width: "50%", margin: "0px auto" }} />
      </div>
      <Footer
        {...props}
        customstyles={{ height: "30px", backgroundColor: "blue" }}
      />
    </div>
  );
};

export const Header = props => {
  const { Header } = props;
  const { styles, Content } = Header;
  return (
    <header className={props.className} style={props.customstyles}>
      {props.headerContent}
    </header>
  );
};

const Menu = props => {
  const { data, position } = props;
  const { header, menuItems } = data;
  let positionStyle = { position: "absolute" };
  if (position === "right-aligned") {
    positionStyle = { ...positionStyle, right: "0px" };
  } else if (position === "left-aligned") {
    positionStyle = { ...positionStyle, left: "0px" , textAlign: 'left'};
  } else {
    positionStyle = { ...positionStyle, left: "0px" };
  }

  return (
    <div
      style={Object.assign(
        { display: "inline-block", width: "25%" },
        positionStyle
      )}
    >
      <h1>{header}</h1>
      <ul style={{ "listStyle": "none" }}>
        {menuItems.map((item, index) => (
          <li key={index}><Link to={item.link}>{item.anchorText}</Link></li>
        ))}
      </ul>
    </div>
  );
};

export const Footer = props => {
  const { Footer } = props;
  const { styles, Content } = Footer;
  return (
    <footer
      className={styles.style}
      style={Object.assign(
        { position: "fixed", bottom: "0px", width: "100%" },
        props.customstyles
      )}
    >
      Copyright (c) 2017-2018 Altimetrik, Pvt Ltd
    </footer>
  );
};
