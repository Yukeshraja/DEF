import React from 'react';
import { storiesOf, action, linkTo } from '@kadira/storybook';
import { MemoryRouter } from 'react-router-dom';
import '../../static/css/reset.css';
import  Slider  from '../slider';
import Radio from '../radio';
import TextField from '../textfield';
import TextArea from '../textarea';
import DropDown from '../dropdown';
import Table from '../table';
import Checkbox from '../checkbox';
import Header from '../header';
import Footer from '../footer';
import Menu from '../menu';


storiesOf('Slider', module)
  .add('with default', () => (
    <Slider />
  ));

storiesOf('Radio',module)
    .add('with default', () => {
          return <Radio />
    })
    .add('with name and values props', () => {
          return <Radio name="test" values={[{value: "value1", labelText:'Test1'},{ value :"value2", labelText : 'Test2'}]} />
    })
storiesOf('TextField',module)
    .add('with default', () => (
          <TextField name="test" onChange={action('change')}/>
    ))
    .add('with error', () => (
          <TextField name="test" onChange={action('change')} error={true}/>
    ))
    .add('with label', () => (
          <TextField name="test" onChange={action('change')} labelText="First Name" placeholder="Enter first name.." />
    ))
storiesOf('TextArea', module)
      .add('with default', () => (
          <TextArea name="sample" onChange={action('change')} style={{height : '200px'}}/>
      ))
storiesOf('Table',module)
      .add('with default', () => (
            <Table rowData={[ {name : 'Varenya' , company : 'Altimetrik'} , { name : 'John' , company : 'Doe'}]} />
      ))
storiesOf('Checkbox',module)
      .add('with default', () => (
            <Checkbox options={[ {name : 'Varenya' , labelText: 'Varenya'} , { name : 'John' , labelText : 'John'}]} />
      ))
storiesOf('Header', module)
      .add('with default', () => (
            <Header />
      ))
storiesOf('Footer',module)
      .add('with default',() => (
          <Footer />
      ));
storiesOf('Menu',module)
      .add('with default', () => (
        <MemoryRouter>
          <Menu />
        </MemoryRouter>
      ))
