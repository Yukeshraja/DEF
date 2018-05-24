import React from "react";
import './view.css';

const View = props => {
  return (
    <div className="view" style={props.customstyles}>
      {props.children || "View goes here"}
    </div>
  );
};

export default View;
