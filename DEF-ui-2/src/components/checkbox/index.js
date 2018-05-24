import React, { Component, PropTypes } from "react";

class Checkbox extends Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.state = {};
  }
  handleChange(event){
          const { name , checked } = event.target;
          this.setState({ [name] : checked });
  }
  render() {
    const { options } = this.props;
    return (
      <div>
        {options.map((option, index) => {
          const checkValue = this.state[option.name]
            ? this.state[option.name]
            : false;
          return (
            <label key={index}>
              <input type="checkbox" name={option.name} checked={checkValue} onChange={this.handleChange} title="hello" />
              {option.labelText}
            </label>
          );
        })}
      </div>
    );
  }
}

Checkbox.defaultProps = {
  options: [{ name: "test", labelText: "Test" }]
};

Checkbox.propTypes = {
  options: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string.isRequired,
      labelText: PropTypes.string
    })
  )
};

export default Checkbox;
