import React, { Component, PropTypes } from "react";
import Handlebars from "handlebars";

const source = `<p>Hello, my name is {{name}}. I work for {{employer}}. I have {{kids.length}} kids:</p> <ul>{{#kids}}<li>{{name}} is {{age}}</li>{{/kids}}</ul>`;

const template = Handlebars.compile(source);

class StarshipEnterprise extends Component {
  constructor(props) {
    super(props);
    this.state = { options: [], open: true };
  }

  componentDidMount() {
    const { serviceUrl } = this.props;
    this.setState({ isFetching: true });

    var component = this;
    //alert(template( this.props ) );

    component.setState({
      options: template(this.props)
    });

    //console.log(this.state.options)
    //alert(this.state.options)
  }

  static propTypes = {
    name: PropTypes.string,
    employer: PropTypes.string,
    kids: PropTypes.arrayOf(PropTypes.object)
  };

  static defaultProps = {
    name: "Data",
    employer: "United Federation of Planets",
    kids: [
      {
        name: "Lal",
        age: "2"
      }
    ]
  };

  render() {
    return (
      <div className="container">
        {this.state.options}
      </div>
    );
  }
}

export default StarshipEnterprise;
