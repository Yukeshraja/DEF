import React from 'react';
import "./style.css";
import plusSign from './Plus-26.png'
import minusSign from './Minus-26.png'

const styles = {
  active: {
    display: 'inherit'
  },
  inactive: {
    display: 'none'
  }
};

class Accordion extends React.Component {

  constructor() {
    super();
    this.state = {
      active : false,
      imgDP : <img src={plusSign} alt="" height="25px" width="25px"/>
    };
    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState({
      active : !this.state.active,
      imgDP:  (this.state.active ? <img src={plusSign} alt="" height="25px" width="25px"/>: <img src={minusSign} alt="" height="25px" width="25px"/>)      
    });


  }


  render() {
    const stateStyle = this.state.active ? styles.active : styles.inactive;


    return (
      
<section>
      <div class="collapsible-header"><i class="material-icons">filter_drama</i>{this.props.summary} </div>
      <div class="collapsible-body">
        <span>
              <pre>
                <div className="inlineText">{JSON.stringify(this.props.details, null, 1)}</div>
              </pre>
        </span>
      </div>
</section>    
  
    );
  }
}

Accordion.propTypes = {
  summary: React.PropTypes.string.isRequired,
  details: React.PropTypes.object.isRequired
};

export default Accordion;
