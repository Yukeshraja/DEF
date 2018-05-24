import React, { Component } from "react";
import MaterialCheckbox, { MaterialCheckboxWithData } from "../checkbox/materialcheckbox";
import Checkbox from "../checkbox";
import DropDown, { DropDownWithData } from "../dropdown";
import MaterialRadio, { RadioWithData } from "../radio/materialradiobutton";
import Radio from "../radio";
import TextArea from "../textarea";
import TextField from "../textfield";
import MaterialTextField from "../textfield";
import R from "ramda";
import "./field.css";

const validateField = (re, value) => re.test(value);
const nameToLabel = str => str.replace("_", " ").toUpperCase();

class Field extends Component {
  constructor(props) {
    super(props);
    this.renderComponent = this.renderComponent.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.state = { file_error: false };
  }

  handleChange(e, type) {
    const { name, value } = e.target;
    let error = false;
    if (type === "integer") {
      error = validateField(/[A-Za-z]/gi, value);
    } else if (type === "string") {
      error = validateField(/[0-9]/gi, value);
    } else {
      error = false;
    }
    console.log(error, "error_input", name);
    this.setState({ [name]: { value, error } });
  }
  renderComponent(item, index) {
    switch (item.fieldType) {
      case "textBox":
        return (
          <TextField
            key={index}
            {...item}
            onChange={event => this.handleChange(event, item.type)}
            value={this.state[item.name] ? this.state[item.name].value : ""}
            error={this.state[item.name] ? this.state[item.name].error : false}
            labelText={nameToLabel(item.name)}
          />
        );
      case "checkbox":
        return this.getComponent(item, "options", Checkbox);
      case "radio":
        return this.getComponent(item, "values", Radio);
      case "blob":
        return <TextArea key={index} {...item} />;
      case "dropdown":
        return <DropDown key={index} {...item} />;
      default:
        return null;
    }
  }
  render() {
    const { match = { params: {} } } = this.props;
    const { templateName = "test1" } = match.params;
    console.log(templateName, "templateName");
    if (this.props.fieldData) {
      console.log("fieldData", this.props.fieldData);
      return (
        <div
          className={this.props.className}
          style={{ display: "inline-block" }}
        >
          {this.props.fieldData.map((item, index) =>
            this.renderComponent(item, index)
          )}
        </div>
      );
    }
    // conditional IO should be avoided ...
    try {
      const selectedData = require(`./${templateName}`);
      return (
        <div
          className={this.props.className}
          style={{ display: "inline-block" }}
        >
          {selectedData.map((item, index) => this.renderComponent(item, index))}
        </div>
      );
    } catch (e) {
      // console.error(e);
      return <div style={{ color: "red" }}>Error check if the file exists</div>;
    }
  }
}
export class FieldUI extends Component {
  constructor(props) {
    super(props);
    this.renderComponent = this.renderComponent.bind(this);
    this.getComponent = this.getComponent.bind(this);
    this.state = {};
  }
  getComponent(itemData, props, UIComponent) {
    const { serviceUrl } = itemData;
    if (R.isEmpty(itemData.enum) || R.isNil(itemData.enum)) {
      switch (itemData.fieldType) {
        case "dropdown":
          return <DropDownWithData url={serviceUrl} {...itemData} {...props} />;
        case "checkbox":
          return (
            <MaterialCheckboxWithData
              url={serviceUrl}
              {...itemData}
              {...props}
            />
          );
        case "radio":
          return <RadioWithData url={serviceUrl} {...itemData} {...props} />;
        default:
          return null;
      }
    } else {
      return <UIComponent {...itemData} options={itemData.enum} {...props} />;
    }
  }
  renderComponent(item, index) {
    switch (item.fieldType) {
      case "textBox":
        return (
          <MaterialTextField
            key={index}
            name={item.name}
            formName={this.props.formName}
            errorText={this.props.errorText}
            defaultValue={this.props.defaultValue}
            path={this.props.path}
            style={{ width: "100%" }}
            floatingLabelText={item.labelName.toUpperCase()}
          />
        );
      case "checkbox":
        return this.getComponent(item, this.props, MaterialCheckbox);
      case "radio":
        return this.getComponent(item, this.props, MaterialRadio);
      case "blob":
        return (
          <MaterialTextField
            key={index}
            name={item.name}
            floatingLabelText={item.labelName.toUpperCase()}
            multiLine={true}
          />
        );
      case "dropdown":
        return this.getComponent(item, this.props, DropDown);
      default:
        return null;
    }
  }
  render() {
    const { response } = this.props;
    const { styles } = response;
    const finalStyle = Object.assign({}, { marginLeft : "10px"} ,styles);
    return (
      <div
        className={this.props.className}
        style={finalStyle}
      >
        {this.renderComponent(response, 0)}
      </div>
    );
  }
}

export default Field;
