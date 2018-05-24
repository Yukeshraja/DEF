import React from 'react';
import { classNames } from '../../utils';

//Styles
import './textarea.css';

const TextArea = (props) => {
        return (
          <div className="input_textarea">
              <textarea className={classNames({ textbox : true , error_input : props.error})} {...props} />
          </div>
        )
}

export default TextArea;
