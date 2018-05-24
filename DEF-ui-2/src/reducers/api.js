import { FETCH_STATUS, SET_ERROR, SET_DATA } from "../actions/types";

const updateState = (
  state = { isFetching: false, responseData: [], error: "" },
  action
) => {
  switch (action.type) {
    case FETCH_STATUS:
    case SET_ERROR:
    case SET_DATA:
      return { ...state, [action.key]: action.payload };
    default:
      return state;
  }
};

export const api = (
  state = { tableData: { isFetching: false, responseData: [], error: "" } },
  action
) => {
  switch (action.type) {
    case FETCH_STATUS:
    case SET_ERROR:
    case SET_DATA:
      return Object.assign({}, state, {
        [action.forState]: updateState(state[action.forState], action)
      });
    default:
      return state;
  }
};
