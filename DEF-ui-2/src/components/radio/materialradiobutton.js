import React, { Component, PropTypes } from "react";
import { RadioButton, RadioButtonGroup } from "material-ui/RadioButton";
import { fetchData } from "../HOC/fetchData";
import { fieldMap } from "../HOC/fieldMap";
import { handleFormField } from "../../actions";
import { connect } from "react-redux";
import { compose } from "redux";
import { camelCase } from "../../utils";

class Radio extends Component {
  render() {
    const { name, options, handleChange, valueSelected, style } = this.props;
    return (
      <RadioButtonGroup
        name={name}
        onChange={handleChange}
        valueSelected={valueSelected}
      >
        {options.map((valueItem, index) => (
          <RadioButton
            value={valueItem.valueText}
            label={valueItem.labelText}
            key={index}
            style={Object.assign({}, style, { marginBottom: "16px" })}
          />
        ))}
      </RadioButtonGroup>
    );
  }
}

Radio.defaultProps = {
  options: [{ labelText: "Sample", valueText: "test" }],
  name: "sample"
};
Radio.propTypes = {
  options: PropTypes.arrayOf(
    PropTypes.shape({
      labelText: PropTypes.string.isRequired,
      valueText: PropTypes.string.isRequired
    })
  ).isRequired,
  name: PropTypes.string.isRequired
};

const mapStateToProps = (state, ownProps) => {
  const { path, name } = ownProps;
  const finalPath = path.concat(camelCase(name));
  return {
    valueSelected: state.form.getIn(finalPath, "")
  };
};

export const RadioWithData = compose(
  connect(mapStateToProps, { handleFormField }),
  fieldMap("radio", ""),
  fetchData("GET", "options")
)(Radio);

export default compose(
  connect(mapStateToProps, { handleFormField }),
  fieldMap("radio", "")
)(Radio);
