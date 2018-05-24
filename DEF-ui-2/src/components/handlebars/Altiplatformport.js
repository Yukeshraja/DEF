import React, { Component, PropTypes } from "react";
import ThemeConfig from "./altiplatform.json";
import { Link } from 'react-router-dom';

class Altiplatformport extends React.Component {
  render() {
    const { match: { params: { id } } } = this.props;
    console.log(ThemeConfig);
    const currentItem = ThemeConfig.field.find(item => item.id === id);
    // return <div dangerouslySetInnerHTML={{ __html: this.state.description }} />
    return (
      <div className="hero-background">
        <div>
          <img className="strips" src="assets/images/strips.png" />
        </div>
        <div>
				<Link className="navbar-item" to="/">Back</Link>
          <div className="header-container header">
            <a className="navbar-brand logo" href="#">
              {" "}<img className="logo" src="assets/images/logo.svg" />{" "}
            </a>
            <a href="#email-form">
              <button className="header-btn"> Download FREE!</button>
            </a>
            <div className="header-right">
              <a className="navbar-item" href="#team">The Team</a>
              <a className="navbar-item" href="#pricing">Pricing</a>
              <a className="navbar-item" href="#features">Features</a>
            </div>
          </div>

          <div className="hero row">
            <div className="hero-right col-sm-6 col-sm-6">
              <h1 className="header-headline bold">
                {" "}{currentItem.name} <br />
              </h1>
              <h4 className="header-running-text light">
                {" "}{currentItem.shortdesc}
              </h4>
              <a href="#email-form">
                <button className="hero-btn"> Download FREE!</button>
              </a>
            </div>
            <div className="col-sm-6 col-sm-6 ipad">
              <img
                className="ipad-screen img-responsive"
                src={currentItem.screenshots[0].screenshot}
              />
            </div>
            <div><img className="mouse" src="assets/images/mouse.svg" /></div>
          </div>
        </div>
        <div id="features" className="features-section">
          <div className="features row">
            <h2 className="features-headline  strong ">FEATURES</h2>
          </div>
          {currentItem.features.map((item, index) =>
            <FeatureComp {...item} key={index} />
          )}
        </div>
      </div>
    );
  }
}

export const FeatureComp = props =>
  <div className="col-sm-4 features-section">
    <div className="feature-icon feature-display">
      <img alt="feature" className="feature-img" src={props.icon} />
    </div>
    <h5 className="feature-head-text feature-display">
      {" "}{props.heading}{" "}
    </h5>
    <p className="feature-subtext light feature-display">
      {props.shortdesc}
    </p>
  </div>;

export default Altiplatformport;
