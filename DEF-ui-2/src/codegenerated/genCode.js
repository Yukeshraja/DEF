import React from 'react';
import { FieldGroupUI } from '../components/field/fieldset';
import { fetchData } from '../components/HOC/fetchData';

const Transactioninfo = (props) => (<FieldGroupUI fieldSetData={{ fieldSet : props.fieldSetData.fieldSet}} recurse={false} path={['transactioninfo']} />);
const Pmtinf = (props) => (<FieldGroupUI fieldSetData={{ fieldSet : props.fieldSetData.fieldSet.fieldSet[0]}} path={['transactioninfo','pmtinf']} recurse={false}/>);
const Transactiondetails = (props) => (<FieldGroupUI fieldSetData={{ fieldSet : props.fieldSetData.fieldSet.fieldSet[0].fieldSet[0]}} path={['transactioninfo','pmtinf','transactiondetails']} recurse={false}/>);

const Main = (props) => (
        <div>
             <Transactioninfo fieldSetData={props.fieldSetData}/>
             <Pmtinf fieldSetData={props.fieldSetData}/>
             <Transactiondetails fieldSetData={props.fieldSetData}/>
        </div>
)

const Enhance =  fetchData("POST","fieldSetData")(Main);

const Final = () => <Enhance url="http://192.168.94.119:2233/swagger/getFieldSet" data={{ api : "transactioninfo" , swagger: "oneplatform"}} />;

export default Final;
