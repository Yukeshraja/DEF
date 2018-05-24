import React, { PropTypes } from "react";
import { Link } from "react-router-dom";
import { appendClassName } from '../../utils';

import "./menu.css";

const Menu = props => {
  const { menuItems, customstyles } = props;
  return (
    <div className={appendClassName('menu',props.className)}style={customstyles}>
      <h2 className="menu_heading">{props.menuHeading}</h2>
      <ul className="menu_items">
        {menuItems.map((item, index) => (
          <li key={index}><Link to={item.link}>{item.anchorText}</Link></li>
        ))}
      </ul>
    </div>
  );
};

Menu.defaultProps = {
  menuHeading: "Alti Menu",
  menuItems: [
    { link: "#", anchorText: "Basic" },
    { link: "#", anchorText: "Advanced" },
    { link: "#", anchorText: "Tutorial" },
    { link: "#", anchorText: "Examples" },
    { link: "#", anchorText: "Demo" }
  ]
};

Menu.propTypes = {
  menuHeading: PropTypes.string,
  menuItems: PropTypes.arrayOf(
    PropTypes.shape({
      link: PropTypes.string.isRequired,
      anchorText: PropTypes.string.isRequired
    })
  ).isRequired
};

export default Menu;
