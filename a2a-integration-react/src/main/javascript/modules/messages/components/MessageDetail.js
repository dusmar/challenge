import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Route, browserHistory } from 'react-router';

import { defineMessages, injectIntl } from 'react-intl';

import { Button, Spinner } from '~/lib/status/status'
import Field from '~/lib/detail/detail'


const messages = defineMessages({
	"title": {
		"id": "message.title",
		"defaultMessage": "Message Detail"
	},

	"ok": {
		id: "button.ok",
		"defaultMessage": "Ok"
	},

	/* fields */
	"id": {
		id: "message.field.id",
		"defaultMessage": "Id"
	},
	"parentId": {
		id: "message.field.parentId",
		"defaultMessage": "Parent Id"
	},
	"queueId": {
		id: "message.field.queueId",
		"defaultMessage": "Queue Id"
	},
	"locked": {
		id: "message.field.locked",
		"defaultMessage": "Locked"
	}
});


class MessageDetail extends Component {

	constructor(props) {
		super(props);
	}

	handleBack(e) {
		e.preventDefault();
		browserHistory.goBack();
	}


	/*
	{
	"id":6697644,
	"parentId":6697641,
	"status":"failed",
	"attemptNo":3,
	"lastAttempt":"2016-09-26T07:35:10.000Z",
	"created":"2016-09-26T07:32:00.000Z",
	"createdBy":"CVNotifyShippingEventForkController",
	"creatorType":"fork","creatorInstance":"one",
	"updated":"2016-09-26T07:35:10.000Z",
	"updatedBy":"CVNotifyShippingEventOrderStatusAlertSenderController",
	"updatorType":"sender",
	"updatorInstance":"one",
	"queueId":8377,
	"locked":false,
	"type":"UNKNOWN",
	"isSeq":false,
	"duration":32130
	}
	*/
	render() {
		const { intl, detail } = this.props;
		const { status, data } = detail;
		const title = intl.formatMessage(messages.title);

		return (
			<div>
				<h1>{title}</h1>
				<div className="row-margin-10">
					<div className="col-xs-12 padding-10">
						<button onClick={ this.handleBack }>{ intl.formatMessage(messages.ok) }</button>
					</div>
				</div>

                <div className="row-margin-10">
				    <div className="row row-height-md">
					    <div className="col-md-6 padding-10 col-height-md">
                            <div>
					            <div className="box padding-10">
					            	<Field value={data.id} label={intl.formatMessage(messages.id)}>{data.id}</Field>
					            	<Field value={data.parentId} label="Parent Id">{data.parentId}</Field>
					            	<Field value={data.queueId} label="Queue Id">{data.queueId}</Field>
					            	<Field value={data.locked} label="Locked">{data.locked}</Field>
					            	<Spinner status={status}/>
                                </div>
						    </div>
                        </div>
    					<div className="col-md-6 padding-10 col-height-md">
                            <div>
        						<div className="box padding-10">
					            	<Field value={data.status} label="status">{data.status}</Field>
					            	<Field value={data.lastAttempt} label="lastAttempt">{data.lastAttempt}</Field>
					            	<Field value={data.attempt} label="attempt">{data.attempt}</Field>
					            	<Field value={data.duration} label="duration">{data.duration}</Field>
					            	<Spinner status={status}/>
                                </div>
                            </div>
    					</div>
                    </div>
                </div>

                <div className="row-margin-10">
                	<div className="col-xs-12 padding-10">
                		<div className="box padding-10">
                			<h2>Content</h2>
                			<Field value={data.header} label="status">{data.header}</Field>
                			<Field value={data.data} label="status">{data.data}</Field>
			            	<Spinner status={status}/>
                		</div>
                	</div>
                </div>

                <div className="row-margin-10">
                	<div className="col-md-6 padding-10">
                		<h2>Created</h2>
            			<Field value={data.created} label="status">{data.created}</Field>
            			<Field value={data.createdBy} label="status">{data.createdBy}</Field>
            			<Field value={data.creatorType} label="status">{data.creatorType}</Field>
            			<Field value={data.creatorInstance} label="status">{data.creatorInstance}</Field>
		            	<Spinner status={status}/>
                	</div>
                	<div className="col-md-6 padding-10">
                		<h2>Updated</h2>
            			<Field value={data.updated} label="status">{data.updated}</Field>
            			<Field value={data.createdBy} label="status">{data.createdBy}</Field>
            			<Field value={data.creatorType} label="status">{data.creatorType}</Field>
            			<Field value={data.creatorInstance} label="status">{data.creatorInstance}</Field>
		            	<Spinner status={status}/>
                	</div>
                </div>


			</div>
			)
	}
}

MessageDetail = injectIntl(MessageDetail);

export default connect(state => ({
	detail: state.messages.detail
}), {})(MessageDetail);

