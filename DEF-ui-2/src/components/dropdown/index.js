import React, { Component, PropTypes } from "react";
import SelectField from "material-ui/SelectField";
import MenuItem from "material-ui/MenuItem";
import { handleFormField } from "../../actions";
import { fetchData } from "../HOC/fetchData";
import { fieldMap } from "../HOC/fieldMap";
import { compose } from "redux";
import { connect } from "react-redux";
import { camelCase } from "../../utils";

/*
    The expected response from api is this:
    [
      {value : "any text", labelText : "any text"},
      {value : "any text", labelText : "any text"}
  ]

*/
class DropDown extends Component {
  render() {
    const { name, options, dropDownValue, handleChange, style } = this.props;
    const [{ valueText }, ...rest] = options;
    return (
      <SelectField
        autoWidth={true}
        fullWidth={true}
        style={style}
        onChange={handleChange}
        floatingLabelText={name.toUpperCase()}
        name={name}
        value={dropDownValue || valueText}
      >
        {options.map((option, index) => (
          <MenuItem
            primaryText={option.labelText}
            key={index}
            value={option.valueText}
          />
        ))}
      </SelectField>
    );
  }
}
DropDown.propTypes = {
  name: PropTypes.string.isRequired,
  options: PropTypes.array.isRequired
};

const mapStateToProps = (state, ownProps) => {
  const { path , name }= ownProps;
  const finalPath = path.concat(camelCase(name));
  return {
    dropDownValue: state.form.getIn(finalPath,'')
  };
};

export const DropDownWithData = compose(
  connect(mapStateToProps, { handleFormField }),
  fieldMap("dropDown", ""),
  fetchData("GET", "options")
)(DropDown);

export default compose(
  connect(mapStateToProps, { handleFormField }),
  fieldMap("dropDown", "")
)(DropDown);
