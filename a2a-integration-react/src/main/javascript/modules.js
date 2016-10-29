import React, { Component } from 'react'
import { Route } from 'react-router'
import { defineMessages } from 'react-intl';
import { menuReducer } from './lib/menu/menu'

import Layout from './layout.js'

import home from './modules/home/home.js'
import messages from './modules/messages/messages.js'
import blocks from './modules/blocks/blocks.js'

import { fetchMessages } from './modules/messages/messagesActions'

import config from '~/config'

//available modules
const modules = {home, messages, blocks};

//create application routes
function initRoutes(routeCreators) {
	const {home, messages, blocks} = routeCreators;

	return function(store, stateFnct) {
		return (
			<Route>
				<Route component={Layout}>
					{home('/', store, stateFnct)}
					{messages('/messages', store, stateFnct)}
					{blocks('/blocks', store, stateFnct)}
				</Route>
			</Route>
		)
	}
}

//create meu items
function initMenu(reducers, state, store) {
	reducers.menu = menuReducer;
	const menu = {
		items: [
			{
				id: 'home', 
				message: {id: 'home.menu', defaultMessage: 'Home'},
				route: '/'
			},
			{
				id: 'messages', 
				message: {id: 'messages.menu', defaultMessage: 'Messages'},
				route: '/messages',
				pattern: '/messages.*',
				action: () => (fetchMessages())
			},
			{
				id: 'blocks', 
				message: {id: 'blocks.menu', defaultMessage: 'Blocks'},
				route: '/blocks',
				pattern: '/blocks.*'
			}
		],
		active: 'home'
	}
	state.menu = menu;
	return menu;
}

/* **** Modules initializer **** */
/* Usually, you DO NOT WANT to modify this initializer */
export default function() {
	const labels = {};
	const state = {};
	const reducers = {};
	const routeCreators = {};
	const locale = config.get(config.LOCALE);

	for (let name in modules) {
		const init = modules[name];
		if (!(typeof init == "function")) {
			continue;
		}

		const module = init(locale);
		if (module.labels) {
			Object.assign(labels, module.labels);
		}

		if (module.state) {
			state[name] = module.state;
		}

		if (module.reducer) {
			reducers[name] = module.reducer;
		}

		if (typeof module.createRoutes == "function") {
			routeCreators[name] = module.createRoutes;
		}
	}

	//application menu
	initMenu(reducers, state);

	return {
		labels,
		reducers,
		createRoutes: initRoutes(routeCreators),
		state
	}
}