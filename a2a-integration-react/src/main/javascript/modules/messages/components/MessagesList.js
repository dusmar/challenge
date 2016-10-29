import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Route, browserHistory } from 'react-router';
import { defineMessages, injectIntl } from 'react-intl';
import ReactList from 'react-list'

import { fetchMessages } from '../messagesActions.js'
import MessagesFilter from './MessagesFilter.js'

import List from '~/lib/list/List';

const messages = defineMessages({
	"title": {
		"id": "messages.title",
		"defaultMessage": "Messages"
	}
});


require('~/styles/tables.scss')

//TODO refactor this & List cmponents
class MessagesList extends List {

	constructor(props) {
		super(props);
	}


	componentWillMount() {
		const { status } = this.props.list;
		if (!status) {
			this.props.fetchMessages();
		}
	}

	handleClick(e, item) {
		e.preventDefault();
		if (item) {
			browserHistory.push('/messages/' + item.id);
		}
	}

	async loadList(page) {
		const { fetchMessages, list } = this.props;
		const { filter, paging } = list;
		await fetchMessages(filter, {...paging, page});
	}

	renderTitle() {
		return <h1>{ this.props.intl.formatMessage(messages.title)}</h1>
	}

	renderFilter() {
		const { list = {} } = this.props;
		return <MessagesFilter status={list.status} onSubmit={this.loadList}/>
	}

	renderHeader() {
		const { intl } = this.props;
		return (
			<div className="row tableHeader">
				<div className="col-md-2 col-sm-6 tableCell">
					Id
				</div>
				<div className="col-md-2 col-sm-6 tableCell">
					Status
				</div>
				<div className="col-md-5 col-sm-12 tableCell">
					Created By
				</div>
				<div className="col-md-1 col-sm-6 tableCell">
					Creator
				</div>
				<div className="col-md-2 col-sm-6 tableCell">
					Last Attempt
				</div>
			</div>
		)
	}

	renderItem(idx) {
		const { intl } = this.props;
		const { data = [] } = this.props.list;
		const item = data[idx];
		return (
			<div key={idx} className="row tableRow" onClick={(e) => this.handleClick(e, item)}>
				<div className="col-md-2 col-sm-6 tableCell">
					{item.id}
				</div>
				<div className="col-md-2 col-sm-6 tableCell">
					{item.status}
				</div>
				<div className="col-md-5 col-sm-12 tableCell">
					{item.createdBy} 
				</div>
				<div className="col-md-1 col-sm-6 tableCell">
					{item.creatorType}
				</div>
				<div className="col-md-2 col-sm-6 tableCell">
					{item.lastAttempt && intl.formatDate(new Date(item.lastAttempt))}
				</div>
			</div>)
	}

}

//inject i18n
MessagesList = injectIntl(MessagesList);

export default connect(state => ({
	list: state.messages.list
}), {fetchMessages})(MessagesList);

