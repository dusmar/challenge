import React, {Component} from 'react';

import Menu from './lib/menu/menu'

require('~/styles/layout.scss')

export class Layout extends Component {
	
	/**
	 * Displays the "page"
	 */
	render() {
  		return (
  			<div className="main-content">
  				<Menu/>
  				<div className="container-box">
  					<div className="col-xs-12">
  						{this.props.children}
  					</div>
  				</div>
  			</div>
  		);
	}
};

export default Layout;