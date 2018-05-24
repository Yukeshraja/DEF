import React, { Component } from "react";
import { wrapComponentName } from "../../utils";
import R from "ramda";

export const layoutConfig = fileName => BaseComponent => {
  class LayoutConfig extends Component {
    render() {
      if (this.props.hasOwnProperty(fileName)) {
        const layoutConfig = R.prop(fileName, this.props);
        const newProps = Object.assign({}, this.props, layoutConfig);
        return <BaseComponent {...newProps} />;
      }
      return <BaseComponent {...this.props} />;
    }
  }

  LayoutConfig.displayName = wrapComponentName(BaseComponent, "withConfig");
  return LayoutConfig;
};
