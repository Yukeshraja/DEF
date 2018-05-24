import { combineReducers } from 'redux';
import { api } from './api';
import form from './form';

const reducers = combineReducers({
      api,
      form
});

export default reducers;
