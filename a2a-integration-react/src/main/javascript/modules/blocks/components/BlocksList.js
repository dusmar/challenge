import React, {Component} from 'react'
import {connect} from 'react-redux'
import {Route} from 'react-router';
import {defineMessages, injectIntl} from 'react-intl';
import ReactList from 'react-list'

import { Spinner } from '~/lib/status/status'


const messages = defineMessages({
	"title": {
		"id": "blocks.title",
		"defaultMessage": "Blocks"
	}
});


require('./blocks.scss');

//TODO refactor
class BlocksList extends Component {

	constructor(props) {
		super(props);
		this.renderItem = this.renderItem.bind(this);
		this.loadList = this.loadList.bind(this);
	}

	componentWillMount() {
		this.loadList();
	}

	renderItem(idx) {
		const { items = [] } = this.props.msg;
		const item = items[idx];
		return (<div key={item.id}><b>{item.id}</b> {item.status}</div>)
	}

	async loadList(page) {
	}

	render() {
		const { intl, fetchMessages } = this.props;
		const title = intl.formatMessage(messages.title);

		return (
			<div>
				<h1>{title}</h1>
				<div className="margin-20 pos-relative block-canvas">
					<div className="block-container">
						<div className="block-in-queue">
							<div className="block-queue">ART.IN</div>
						</div>
						<div className="block-body">
						</div>
						<div className="block-out-queues">
							<div className="">
													<div className="block-container">
						<div className="block-in-queue">
							<div className="block-queue">ART.E1</div>
						</div>
						<div className="block-body">
						</div>
						<div className="block-out-queues">
							<div className="block-queue">ART.E1.E1</div>
							<div className="block-queue">ART.ERROR.FORK.E1</div>
						</div>
					</div>
							</div>
							<div className="block-queue">ART.ERROR1</div>
						</div>
					</div>
				</div>
			</div>
			)
	}
}

//inject i18n
BlocksList = injectIntl(BlocksList);

export default connect(state => ({
	list: state.messages.list
}), { })(BlocksList);


