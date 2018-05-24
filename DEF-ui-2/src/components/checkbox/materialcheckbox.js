import React, { Component, PropTypes } from "react";
import Checkbox from "material-ui/Checkbox";
import { connect } from "react-redux";
import { fetchData } from "../HOC/fetchData";
import { fieldMap } from "../HOC/fieldMap";
import { compose } from "redux";
import { handleFormField, handleCheckBox } from "../../actions";
import { camelCase } from "../../utils";
import { fromJS } from "immutable";

class MaterialCheckbox extends Component {
  render() {
    const { options, handleChange, checkBoxObj, style } = this.props;
    return (
      <div>
        {options.map((option, index) => {
          return (
            <Checkbox
              label={option.labelText}
              key={index}
              style={style}
              name={option.labelText}
              checked={checkBoxObj.get(option.labelText, false)}
              onCheck={handleChange}
              style={{ marginBottom: "16px" }}
            />
          );
        })}
      </div>
    );
  }
}

MaterialCheckbox.defaultProps = {
  options: [{ valueText: "test", labelText: "Test" }]
};

MaterialCheckbox.propTypes = {
  options: PropTypes.arrayOf(
    PropTypes.shape({
      valueText: PropTypes.oneOfType([PropTypes.string,PropTypes.number]).isRequired,
      labelText: PropTypes.string
    })
  )
};

const mapStateToProps = (state, ownProps) => {
  const { path , name } = ownProps;
  const finalPath = path.concat(camelCase(name));
  return {
     checkBoxObj: state.form.getIn(finalPath,fromJS({}))
  }
};

export const MaterialCheckboxWithData = compose(
  connect(mapStateToProps, { handleFormField, handleCheckBox }),
  fieldMap("checkbox", ""),
  fetchData("GET", "options")
)(MaterialCheckbox);

export default compose(
  connect(mapStateToProps, { handleFormField, handleCheckBox }),
  fieldMap("checkbox", "")
)(MaterialCheckbox);
