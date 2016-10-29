import React, { Component } from 'react'
import ReactList from 'react-list'


import { Spinner, EmptyInfo } from '~/lib/status/status'
import { PagingNavigation } from '~/lib/paging/paging'

require('~/styles/tables.scss')
require('./list.scss')

//TODO refactor
class List extends Component {

	constructor(props) {
		super(props);

		this.renderTitle = this.renderTitle.bind(this);
		this.renderFilter = this.renderFilter.bind(this);

		this.renderHeader = this.renderHeader.bind(this);
		this.renderList = this.renderList.bind(this);
		this.renderItem = this.renderItem.bind(this);

		this.renderPaging = this.renderPaging.bind(this);

		this.loadList = this.loadList.bind(this);
		this.handleClick = this.handleClick.bind(this);
	}


	componentWillMount() {
		const { status } = this.props.list;
		if (!status) {
			this.loadList()
		}
	}

	handleClick(e, item) {
		e.preventDefault();
		if (item) {
			browserHistory.push('/messages/' + item.id);
		}
	}

	renderTitle() {
	}

	renderFilter() {
	}

	renderHeader() {
	}

	renderItem(idx) {
	}

	renderList() {
		const { list = {} } = this.props;
		return <ReactList itemRenderer={this.renderItem} length={list.count} type='uniform' data={list.data}/>
	}

	renderPaging() {
		const { list = {} } = this.props;
		return <PagingNavigation paging={list.paging} onPageChanged={this.loadList}/>
	}

	loadList(page) {
	}

	render() {
		const { intl, list } = this.props;
		const { filter, paging } = this.props.list;

		return (
			<div>
				{ this.renderTitle() }
				{ this.renderFilter() }
				
				<div className="margin-20">
					{ this.renderHeader() }
					{ this.renderList() }
					<EmptyInfo empty={!list.count}></EmptyInfo>
					<Spinner status={list.status}/>
				</div>
				{ this.renderPaging() }
			</div>
			)
	}
}

export default List;