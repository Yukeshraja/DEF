import React, { Component, PropTypes } from "react";
import { getDisplayName, camelCase } from "../../utils";
import R from "ramda";

export const fieldMap = (fieldType, defaultFieldProp) => BaseComponent => {
  class FieldMap extends Component {
    constructor(props) {
      super(props);
      this.handleChange = this.handleChange.bind(this);
    }
    handleChange() {
      const { handleFormField, name, path } = this.props;
      const handleField = R.curry(fieldValue =>
        handleFormField(path.concat(camelCase(name)), fieldValue)
      );
      switch (fieldType) {
        case "textField":
          return (event, newValue) => handleField(newValue);
        case "dropDown":
          return (event, index, newValue) => handleField(newValue);
        case "radio":
          return (event, newValue) => handleField(newValue);
        case "checkbox":
          return (event, isChecked) =>
            handleFormField(
              path.concat([camelCase(name), camelCase(event.target.name)]),
              isChecked
            );
        default:
          return event =>
            handleFormField(
              path.concat(camelCase(event.target.name)),
              event.target.value
            );
      }
    }
    render() {
      return (
        <BaseComponent {...this.props} handleChange={this.handleChange()} />
      );
    }
  }
  FieldMap.propTypes = {
    name: PropTypes.string,
    formName: PropTypes.string.isRequired,
    handleFormField: PropTypes.func,
    handleCheckBox: PropTypes.func
  };
  FieldMap.displayName = `FieldMap(${getDisplayName(BaseComponent)})`;
  return FieldMap;
};
