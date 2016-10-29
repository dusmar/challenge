import React, { Component } from 'react'


require('./detail.scss')
class Field extends Component {

	render() {
		const { value, label, children, delimiter = ":" } = this.props;

		//do not display empty values
		if ((typeof value == "undefined") || value == undefined || value === null) {
			return null;
		}

		return (
			<div className="detail-row">
				<div className="detail-label">{ label }{ delimiter }</div>
				<div className="detail-value">{ children }</div>
			</div>
		)
	}

}

export default Field;