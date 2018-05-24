import React from 'react';
import { FieldGroupUI } from '../components/field/fieldset';
import fieldSetData from './fieldSet.json';

const Transactioninfo = () => (<FieldGroupUI fieldSetData={fieldSetData} recurse={false} />);

const PayMtInfo = () => (<FieldGroupUI fieldSetData={{ fieldSet : fieldSetData.fieldSet.fieldSet[0]}} recurse={false} />);

const TransactionDetails = () => (<FieldGroupUI fieldSetData={{ fieldSet : fieldSetData.fieldSet.fieldSet[0].fieldSet[0]}} recurse={false} />);

const Main = (props) => (
      <div>
        <Transactioninfo />
        <PayMtInfo />
        <TransactionDetails />
      </div>
);

export default Main;
