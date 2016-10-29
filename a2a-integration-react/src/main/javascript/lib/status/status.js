import React, {Component} from 'react';

import { injectIntl } from 'react-intl';


export const Button = function(props) {
	const {status} = props;
	return <button {...props} disabled={status == 'pending'}>{ props.children }</button>
};


//type=inline/overlay/relative*
require('./status.scss');

export class Spinner extends Component {

	renderInlineSpinner() {
		return (
			<div className="inlineSpinner icon">&#xe806;</div>
		)
	}

	renderOverlaySpinner() {
		const { size = "normal" } = this.props;
		const className = "overlaySpinner-" + size;
		return (
			<div className={ className }><div>&#xe838;</div></div>
		)
	}

	renderRelativeSpinner() {
		const { size = "normal" } = this.props;
		const className = "relativeSpinner-" + size;
		return (
			<div className={ className }>&#xe838;</div>
		)
	}


	render() {
		const { status, type = "relative" } = this.props;
		if (status != 'pending') {
			return null;
		}

		switch (type) {
			case 'inline':
				return this.renderInlineSpinner();
			case 'overlay':
				return this.renderOverlaySpinner();
			default:
		}

		return this.renderRelativeSpinner();
	}
};


//TODO empty status
export class EmptyInfo extends Component {

	constructor(props) {
		super(props);
	}

	render() {
		const { empty } = this.props;
		if (!empty) {
			return null;
		}

		//TODO 
		return (
			<div className="empty-info">
				No records found
			</div>
		)
	}
};


//TODO error status
class ErrorInfoComponent extends Component {
	
	constructor(props) {
		super(props);
	}

	render() {
		const { error } = this.props;
		return undefined;
	}
}

export const ErrorInfo = injectIntl(ErrorInfoComponent);
