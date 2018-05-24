import {
  HANDLE_FORM_FIELD,
  HANDLE_CHECKBOX,
  SET_INITIAL_VALUE,
  CLEAR_FORM
} from "../actions/types";
import {camelCase} from "../utils"
import { fromJS } from "immutable";
const form = (state = fromJS({}), action) => {
  switch (action.type) {
    case HANDLE_FORM_FIELD:
      return state.setIn(
        action.path,
        action.fieldValue
      );
    case HANDLE_CHECKBOX:
      return state.setIn(
        [action.formName, camelCase(action.checkBoxName), action.fieldName],
        action.fieldValue
      );
    case SET_INITIAL_VALUE:
      return state.merge(fromJS({ [action.formName]: action.formData }));
    case CLEAR_FORM:
      return state.set(action.formName, fromJS({}));
    default:
      return state;
  }
};

export default form;
