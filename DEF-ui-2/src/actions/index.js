// export your actions from here
import { get, convertJSON, handleResponseError, post } from "../utils";
import {
  FETCH_STATUS,
  SET_DATA,
  SET_ERROR,
  HANDLE_FORM_FIELD,
  HANDLE_CHECKBOX,
  CLEAR_FORM,
  SET_INITIAL_VALUE
} from "./types";

export const statusUpdate = (forState, payload) => ({
  type: FETCH_STATUS,
  forState,
  payload,
  key: "isFetching"
});

export const setInitialValues = (formName,formData) => ({
          type : SET_INITIAL_VALUE,
          formData,
          formName
})

export const clearForm = (formName) => ({
        type : CLEAR_FORM,
        formName
})

export const setData = (forState, payload) => ({
  type: SET_DATA,
  forState,
  payload,
  key: "responseData"
});

export const setError = (forState, payload) => ({
  type: SET_ERROR,
  forState,
  payload,
  key: "error"
});

export const postData = (forState, url, postData) => dispatch => {
  dispatch(statusUpdate(forState, true));
  post(url, postData)
    .then(handleResponseError)
    .then(convertJSON)
    .then(json => {
      dispatch(setData(forState, json));
      dispatch(statusUpdate(forState, false));
      dispatch(setError(forState, ""));
    })
    .catch(error => {
      console.error(error, "error fetching..");
      dispatch(statusUpdate(forState, false));
      dispatch(setError(forState, error));
    });
};

export const handleFormField = (path, fieldValue) => ({
  type: HANDLE_FORM_FIELD,
  path,
  fieldValue
});


export const handleCheckBox = (
  formName,
  checkBoxName,
  fieldName,
  fieldValue
) => ({
  type: HANDLE_CHECKBOX,
  formName,
  checkBoxName,
  fieldName,
  fieldValue
});

export const fetchData = (forState, url) => dispatch => {
  dispatch(statusUpdate(forState, true));
  get(url)
    .then(handleResponseError)
    .then(convertJSON)
    .then(json => {
      dispatch(setData(forState, json));
      dispatch(statusUpdate(forState, false));
      dispatch(setError(forState, ""));
    })
    .catch(error => {
      console.error(error, "error fetching..");
      dispatch(statusUpdate(forState, false));
      dispatch(setError(forState, error));
    });
};
