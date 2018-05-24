import React, { Component, PropTypes } from "react";
import ThemeConfig from "./altiplatform.json";
import { readAltiPlatformData } from "./theme";
import { Link } from "react-router-dom";

class Altiplatform extends React.Component {
  // constructor(props) {
  //   super(props);
  //   // this.handleClick = this.handleClick.bind(this);
  //   // this.state = {
  //   //   description: readAltiPlatformData(ThemeConfig)
  //   // };
  // }
  //
  render() {
    const { field } = ThemeConfig;
    // return <div dangerouslySetInnerHTML={{ __html: this.state.description }} />
    return (
      <div>
        {field.map((item, index) => <AltiCard {...item} />)}
      </div>
    );
  }
}

const AltiCard = props => {
  return (
    <div className="mdl-card mdl-shadow--2dp demo-card-square">
      <div
        className="mdl-card__title mdl-card--expand"
        style={{ backgroundImage: `url(${props.image})` }}
      >
        <h2 className="mdl-card__title-text">{props.name}</h2>
      </div>
      <div className="mdl-card__supporting-text">
        {props.shortdesc}
      </div>
      <div className="mdl-card__actions mdl-card--border">
        <Link
          to={`/${props.id}`}
          className="mdl-button mdl-button--accent mdl-js-button mdl-js-ripple-effect"
        >
          More..
        </Link>
      </div>
    </div>
  );
};

export default Altiplatform;
