import React, { Component, PropTypes } from "react";
import "./radio.css";

class Radio extends Component {
  constructor(props) {
    super(props);
    this.state = {};
    this.handleChange = this.handleChange.bind(this);
  }
  handleChange(event) {
    const { name, value } = event.target;
    this.setState({ [name]: value }, () => {
      if (this.props.changeCallback) this.props.changeCallback(this.state);
    });
  }
  render() {
    const { name, values } = this.props;
    return (
      <div className="radio_group">
        {values.map((valueItem, index) => (
          <label key={index}>
            {" "}
            <input
              type="radio"
              name={name}
              value={valueItem.value}
              onChange={this.handleChange}
              checked={this.state[name] === valueItem.value}
            />
            {valueItem.labelText}
            {" "}
          </label>
        ))}
      </div>
    );
  }
}

Radio.defaultProps = {
  values: [{ labelText: 'Sample', value : 'test'}],
  name: "sample"
};
Radio.propTypes = {
  values: PropTypes.arrayOf(
    PropTypes.shape({
      labelText: PropTypes.string.isRequired,
      value: PropTypes.string.isRequired
    })
  ).isRequired,
  name: PropTypes.string.isRequired
};

export default Radio;
