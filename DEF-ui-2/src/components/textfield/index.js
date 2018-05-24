import React, { PropTypes } from "react";
import { classNames } from "../../utils";
import TextField from "material-ui/TextField";
import { connect } from "react-redux";
import { compose } from "redux";
import { handleFormField } from "../../actions";
import { fieldMap } from "../HOC/fieldMap";
import { camelCase } from "../../utils";

// Styles
import "./textfield.css";

const InputField = props => {
  return (
    <div className="input_field">
      <label>
        {props.labelText}
        <input
          type="text"
          className={classNames({ textfield: true, error_input: props.error })}
          onChange={props.onChange}
          name={props.name}
          value={props.value}
        />
      </label>
    </div>
  );
};

InputField.propTypes = {
  onChange: PropTypes.func,
  error: PropTypes.bool,
  name: PropTypes.string.isRequired,
  labelText: PropTypes.string
};

const ModifiedField = props => {
  const {
    handleChange,
    value,
    defaultValue,
    formName,
    handleFormField,
    path,
    style,
    ...baseProps
  } = props;
  return (
    <TextField
      onChange={props.handleChange}
      value={props.value}
      style={style}
      {...baseProps}
    />
  );
};

const mapStateToProps = (state, ownProps) => {
  const { path , name } = ownProps;
  const finalPath = path.concat(camelCase(name));
  return {
    value: state.form.getIn(finalPath, ownProps.value)
  };
};

export default compose(
  connect(mapStateToProps, { handleFormField }),
  fieldMap("textField", "")
)(ModifiedField);
