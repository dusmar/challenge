import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Route } from 'react-router';
import { defineMessages, injectIntl } from 'react-intl';
import { fields } from '~/lib/form/form-fields';

import { Button } from '~/lib/status/status'


const messages = defineMessages({
	"search": {
		"id": "filter.search",
		"defaultMessage": "Search"
	},
	"searchPlaceholder": {
		"id": "messages.searchPlaceholder",
		"defaultMessage": "Filter messages"
	}
});

require('~/styles/filter.scss')
class MessagesFilter extends Component {

	constructor(props) {
		super(props);
		this.onSubmit = this.onSubmit.bind(this);
	}

	async onSubmit(e) {
		e.preventDefault();
		const {onSubmit} = this.props;
		if (typeof onSubmit == "function") {
			onSubmit();
		}
	}

	render() {
		const { intl, status, fields } = this.props;
		return (
			<form onSubmit={this.onSubmit} disabled={status == 'pending'}>
				<div className="filter">
					<input 
						type="text" 
						autoComplete="off"
						placeholder={intl.formatMessage(messages.searchPlaceholder)} 
						disabled={ status == 'pending' }
						{...fields.query}
						/>
					<Button className="filterButton" status={status}>{intl.formatMessage(messages.search)}</Button>
				</div>
			</form>
			)
	}
}

//TODO add required props
MessagesFilter = injectIntl(MessagesFilter);

MessagesFilter = fields(MessagesFilter, {
	path: 'messages.list.filter',
	fields: ['query']
});

export default MessagesFilter;


