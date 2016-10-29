import React, { Component } from 'react'
import { connect, dispatch } from 'react-redux'
import { Route, IndexRoute } from 'react-router'
import { loadLabels } from '~/lib/i18n/i18n'


import MessagesList from './components/MessagesList'
import MessageDetail from './components/MessageDetail'
import reducer from './messagesReducer'
import { fetchMessages, fetchMessage } from './messagesActions'


function createRoutes(rootPath, store, stateFnct) {
	const fetchDetail = function(cfg) {
		store.dispatch(fetchMessage(cfg.params.id));
	}

	return (
		<Route path={rootPath}>
			<IndexRoute component={ MessagesList } />
			<Route path=":id" component={ MessageDetail } onEnter={ fetchDetail }/>
		</Route>
    );
};


/**
 * Module initializer
 */
export default function(context = {}) {
	const {locale = 'en'} = context;
	const labels = loadLabels('messages', 'messages', locale);
	return {
		labels,
		reducer,
		createRoutes
	}
}

