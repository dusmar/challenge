import React, {Component} from 'react'
import { connect } from 'react-redux'
import {FormattedMessage, defineMessages, injectIntl} from 'react-intl'
import { Line } from 'react-chartjs-2'

import { fetchHistoryChartData } from '../homeRedux.js';
import { Spinner } from '~/lib/status/status'


const messages = defineMessages({
	"chartTitle": {
		"id": "home.historyChart.title",
		"defaultMessage": "Message statistics - Last 7 days"
	},

	"countAxis": {
		"id": "home.historyChart.countAxis",
		"defaultMessage": "Messages"
	},
	"dateAxis": {
		"id": "home.historyChart.dateAxis",
		"defaultMessage": "Date"
	},


	"processed": {
		"id": "home.historyChart.processed",
		"defaultMessage": "Processed"
	},
	"failed": {
		"id": "home.historyChart.failed",
		"defaultMessage": "Failed"
	},
	"rejected": {
		"id": "home.historyChart.rejected",
		"defaultMessage": "Rejected"
	},
	"other": {
		"id": "home.historyChart.other",
		"defaultMessage": "Other"
	}
});

class HistoryChart extends Component {

	constructor(props) {
		super(props);
		this.prepareChart = this.prepareChart.bind(this);
	}

	async componentWillMount() {
		const { fetchHistoryChartData } = this.props;
		try {
			await fetchHistoryChartData()
		} catch (e) {
			console.error(e); //TODO
		}
	}

	prepareChart() {
		const { intl, data, colors } = this.props;
		const 
			labels = [],
			processed = [],
			failed = [],
			rejected = [],
			other = [];

		if (data) {
			for (let s of data) {
				labels.push(intl.formatDate(new Date(s.statDate)));
				processed.push(s.processed);
				failed.push(s.failed);
				rejected.push(s.rejected);
				other.push(s.other);
			}
		}

        return {
            data: {
                labels: labels,
                datasets: [{
                    label: intl.formatMessage(messages.processed),
                    data: processed,
                    fill: false,
                    lineTension: 0,
		            borderColor: colors[0],
		            backgroundColor: colors[0]
                }, {
                    label: intl.formatMessage(messages.failed),
                    data: failed,
                    fill: false,
                    lineTension: 0,
		            borderColor: colors[1],
		            backgroundColor: colors[1]
                }, {
                    label: intl.formatMessage(messages.rejected),
                    data: rejected,
                    lineTension: 0,
                    fill: false,
		            borderColor: colors[2],
		            backgroundColor: colors[2]
                }, {
                    label: intl.formatMessage(messages.other),
                    data: other,
                    lineTension: 0,
                    fill: false,
		            borderColor: colors[3],
		            backgroundColor: colors[3]
                }]
            },
            options: {
                responsive: true,
                legend: {
                    position: 'bottom',
                },
                hover: {
                    mode: 'index'
                },
                scales: {
                    xAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: intl.formatMessage(messages.dateAxis)
                        }
                    }],
                    yAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: intl.formatMessage(messages.countAxis)
                        }
                    }]
                },
                title: {
                    display: true,
                    text: intl.formatMessage(messages.chartTitle)
                }
            }
        };		
	}

	render() {
		const { data, options } = this.prepareChart();
		return <Line {...this.props} data={data} options={options}/>;
	}

}

HistoryChart = injectIntl(HistoryChart);

export default connect(state => ({
  data: state.home.history
}), { fetchHistoryChartData })(HistoryChart);

