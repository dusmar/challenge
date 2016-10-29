import React, { Component } from 'react';
import {connect} from 'react-redux'
import { getValue, setValue } from '~/lib/utils.js'


//private redux action
const setFieldValueAction = (expression, value) => {
	return {
    	type: '@@form-fields/SET_VALUE',
    	payload: {expression, value}
	}
};

//public reducer
export const fieldsReducer = (state = {}, action) => {
  	switch (action.type) {
	case '@@form-fields/SET_VALUE':
        const { expression, value } = action.payload;
		setValue(action.getState(), expression, value);
	default:
		return state
  	}
};

//redux middleware - add state accessor to the action
export const fieldsReduxMiddleware = store => next => action => {
    action.type == '@@form-fields/SET_VALUE' ? next({ ...action, getState: store.getState }) : next(action)
}


export const fields = (WrappedComponent, options) => {
	const {path = '', fields = []} = options;
		  
    const Injected = class extends Component {

    	constructor(props) {
    		super(props);
    		this.createFields = this.createFields.bind(this);
    		this.setFieldValue = this.setFieldValue.bind(this);
    	}
    	
    	createFields() {
    		const fs = {};
    		for (let field of fields) {
    			const expression = path + '.' + field;
    			fs[field] = {
    				name: field,
    				onBlur: (e) => {
    					const value = e.target.value;
    					this.setFieldValue(expression, value);
    				},

    				onKeyDown: (e) => {
    					if (e.keyCode == 13) {
	   						const value = e.target.value;
    						this.setFieldValue(expression, value);
    					}
    				}
    			};
    		}
    		
    		this.fields = fs;
    	}

    	setFieldValue(expression, value) {
    		const { setFieldValueAction, state } = this.props;
            const val = getValue(state, expression);
            if (value != val) {
                setFieldValueAction(expression, value);
            }
    	}

    	
    	componentWillMount() {
            this.createFields();
    	}

    	
        render() {
        	const { state } = this.props;
        	if (this.fields) {
        		for (let field in this.fields) {
        			const f = this.fields[field];
        			f.defaultValue = getValue(state, path + '.' + field);
        		}
        	}
        	return <WrappedComponent {...this.props} fields={this.fields} />
        }
    };

    return connect(state => ({state}), { setFieldValueAction })(Injected);
};

