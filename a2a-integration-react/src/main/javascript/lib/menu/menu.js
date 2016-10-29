import React, {Component} from 'react';
import { defineMessages, injectIntl } from 'react-intl';
import { connect } from 'react-redux';

import { Link, browserHistory } from 'react-router'

//styles
require('./menu.scss');

//menu action wrapper
const menuActionWrapper = (action) => (action);

//reducer
export const menuReducer = (state = {items: [], active: null}, action) => {
  	switch (action.type) {
	//react on route change to set active menu item
	case '@@router/LOCATION_CHANGE':
		let {items, active} = state;
		const {pathname} = action.payload;

		for (let item of items) {
			if (item.pattern) {
				const exp = new RegExp(item.pattern);
				if (exp.exec(pathname)) {
					active = item.id;
					break;
				}

			}
			if (item.route == pathname) {
				active = item.id;
				break;
			}
		}

		return {
			...state,
			active
		}

	default:
		return state
  }
}



class TopMenu extends Component {
	render() {
		const {items = [], active, intl, action} = this.props;
  		return (
  			<header className="top-menu-bar">
  				<div className="container">
  					<div className="top-menu-logo"/>
  					{items.map(item => {
	            		return <MenuItem key={item.id} item={item} active={item.id == active} intl={intl} action={action}/>
	            	})}
  					<div className="clearfix"/>
  				</div>
  			</header>
  			)
	}

};



class MenuItem extends Component {

	handleClick(e) {
		e.preventDefault();
		const { item, action } = this.props;
		browserHistory.push(item.route);

		if (action && typeof item.action == "function") {
			//execute JS action
			action(item.action());
		}
	}

	render() {
		const {item, active, intl} = this.props;
		const label = intl.formatMessage({id: item.message.id});
		const className = active ? "top-menu-item active" : "top-menu-item";
  		return (<div className={className}><a href="#" onClick={(event)=>this.handleClick(event)}><span>{label}</span></a></div>)
	}

};


/**
 * Main menu bar (TODO add mobile render function)
 */
class Menu extends Component {

	render() {
		return <TopMenu {...this.props.menu} intl={this.props.intl} action={ this.props.menuActionWrapper }/>
	}
}

Menu = injectIntl(Menu);

export default connect(state => ({
  menu: state.menu
}), { menuActionWrapper })(Menu);
