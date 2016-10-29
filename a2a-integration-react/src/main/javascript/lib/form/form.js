import React, { Component } from 'react';
import {FormattedMessage, injectIntl, intlShape} from 'react-intl';

require('./Form.scss');
require('../styles/alert.scss');

export const injectFormUtils = (WrappedComponent, options) => {
	const {path = '', fields = [], getInitialState, validators = [], errorPrefix = ''} = options;
		  
    const Injected = class extends Component {

    	constructor(props) {
    		super(props);

            console.log('utils props', props);

    		this.createFields = this.createFields.bind(this);
    		this.evaluatePath = this.evaluatePath.bind(this);
    		this.onSubmit = this.onSubmit.bind(this);
    		this.handleErrors = this.handleErrors.bind(this);
    	}
    	
    	createFields() {
    		const fs = {};
    		for (let field of fields) {
    			fs[field] = {
    				name: field,
    				onChange: (e) => {
    					e.preventDefault();
    					const val = e.target.value;
    					const data = this.evaluatePath(path);
    					data[field] = val;
    					this.forceUpdate();
    				}
    			};
    		}
    		
    		this.fields = fs;
    	}

    	evaluatePath(path) {
    		let obj = this.props[path] || {};
    		return obj;
    	}
    	
    	handleErrors(errors = []) {
    		//clear errors
    		this.errors = {$all: []};
    		if (errors.length) {
	    		//check errors
    			let i = 0;
	            for (let e of errors) {
	            	e.key = i++;
	                if (e.field) {
	                    let error = this.errors[e.field];
	                    if (!error) {
	                        error = {
	                            errors: []
	                        };
	                        this.errors[e.field] = error;
	                    }
	                    error.errors.push(e);
	                }
	                
	                this.errors.$all.push(e);
	            }

	            this.forceUpdate();
	            return false;
    		}
    		return true;
    	}
    	
    	onSubmit(e) {
            e.preventDefault();
            //collect values 
            const data = this.evaluatePath(path);
            
            //validate object
            let errors = [];
            if (validators.length) {
                for (let v of validators) {
                    const errs = v(data);
                    if (errs) {
                        errors = errors.concat(errs);
                    }
                }
            }

            if (!this.handleErrors(errors)) {
            	return;
            }
            
            //validation passed, submit data
            if (typeof this.props.onSubmit === "function") {
                try {
                    this.props.onSubmit(data);
                } catch (ex) {
                    //TODO handle exception
                    console.log(ex);
                    this.handleErrors([translateException(ex)]);
                }
            }
        }

    	
    	componentWillMount() {
            this.createFields();
    	    this.handleErrors();

            this.submit = {
                onSubmit: (e) => {
                	this.onSubmit(e);
                }
            };
    	}
    	
        render() {
        	if (this.fields) {
        		for (let field in this.fields) {
        			const f = this.fields[field];
        			f.value = this.evaluatePath(path)[field];
        		}
        	}
        	return <WrappedComponent {...this.props} fields={this.fields} submit={this.submit} errors={this.errors}/>
        }
    };

    return injectIntl(Injected);
};



export const FormField = function(props) {
    //check errors for this fields
    const {fieldName, errors, label, showLabels} = props;
    const hasErrors = errors && errors.length > 0;
    
    let className = "form-group";
    if (hasErrors) {
        className += " error";
    }
    
    return (
        <div className={className}>
            {showLabels && label && <div className="form-label">{label}</div>}
            {props.children}
            {hasErrors && errors.map(msg => {
            	const values = {field: msg.field};
                return <div key={msg.key}><b>{msg.field}</b> <FormattedMessage id={msg.label} values={values}/> </div>
            	}
            )}
        </div>
    );
}


/**
 * TODO comment
 */
export const FormFeedback = function(props) {
    const {errors} = props;
    if (!errors || !errors.$all) {
        return <div/>;
    }
    return (
        <div>{errors && errors.$all.length > 0 && <div className="form-group">
        	<div className="alert alert-danger">
	            <ul className="errors">
	            {errors.$all.map(msg => {
	            	const values = {field: msg.field};
	                return <li key={msg.key}><b>{msg.field}</b> <FormattedMessage id={msg.label} values={values}/> </li>
	            	}
	            )}
	            </ul>
	        </div>
        </div>}</div>

    );
}


/**
 * TODO comment
 */
export const FormButtonBar = function(props) {
    return (
        <div className="form-button-bar">
            {props.children}
        </div>
    );
}


export const translateException = function(ex) {
    console.log("Form.translateException(ex)", ex);
    return {key: '1', description: 'Unexpected error!'};
}