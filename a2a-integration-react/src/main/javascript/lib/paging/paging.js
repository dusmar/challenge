import React, {Component} from 'react';

const DEFAULT_PAGE_SIZE = 10;

export const injectPaging = function(paging = {page: 0, pageSize: DEFAULT_PAGE_SIZE}) {
	return {
		'X-Paging': true,
		'X-Paging-Page': paging.page || 0,
		'X-Paging-PageSize': paging.pageSize || DEFAULT_PAGE_SIZE
	};
};

export const readPaging = function(headers = {}, paging) {
	if (!headers["x-paging"]) {
		return paging;
	}

	const ps = headers["x-paging-page"] * 1;
	const pcs = headers["x-paging-pagescount"] * 1;

	return {
		...paging,
		page: isNaN(ps) ? 0 : ps,
		pagesCount: isNaN(pcs) ? 1 : pcs
	};
}

require('./paging.scss');
export class PagingNavigation extends Component {

	constructor(props) {
		super(props);
		this.changePage = this.changePage.bind(this);
	}

	changePage(e, page) {
		e.preventDefault();
		const {onPageChanged, paging, status} = this.props;
		
		if (page < 0 || page > paging.pagesCount || status == 'pending') {
			//do nothing
			return;
		}
		if (typeof onPageChanged == "function") {
			onPageChanged(page);
		}
	}

	render() {
		const { paging, onPageChanged, status } = this.props;
		if (!(paging || paging.pagesCount || paging.page)) {
			//do not display paging
			return null;
		}

		const page = (paging.page || 0) * 1;
		const count = (paging.pagesCount || 1) * 1;

		//numbers
		const links = [];
		const min = Math.max(page - 1, 1);
		const max = Math.min(page + 2, count - 1);
		for (let i = min; i < max; i++) {
			links.push({page: i, num: i + 1});
		}

		return (
			<div className="pagingContainer" {...this.props}>
				<div className="paging">
					<button href="#" disabled={page < 1 || status == 'pending'} onClick={(e) => {this.changePage(e, page - 1)}}>&lt;</button>
					<a href="#" className={page == 0 ? "active" : null} onClick={(e) => {this.changePage(e, 0)}}>1</a>
					{count > 1 && page > 2 && <span>&hellip;</span> }
					{links.map((item) => {
					    return <a href="#" className={page ==  item.page ? "active" : null} key={item.page} onClick={(e) => {this.changePage(e, item.page)}}>{item.num}</a>
					})}
					{count > 1 && page < count - 3 && <span>&hellip;</span> }
					{count > 1 && <a href="#" className={page == count - 1 ? "active" : null} onClick={(e) => {this.changePage(e, count - 1)}}>{count}</a>}
					<button href="#" disabled={page >= count - 1 || status == 'pending'} onClick={(e) => {this.changePage(e, page + 1)}}>&gt;</button>
				</div>
			</div>
		)	
	}
}
