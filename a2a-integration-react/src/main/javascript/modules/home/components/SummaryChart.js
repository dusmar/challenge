import React, {Component} from 'react'
import { connect } from 'react-redux'
import { defineMessages, injectIntl } from 'react-intl'
import { Pie } from 'react-chartjs-2'

import { fetchSummaryChartData } from '../homeRedux.js';


const messages = defineMessages({
	"chartTitle": {
		"id": "home.summaryChart.title",
		"defaultMessage": "Message summary - Last 7 days"
	},

	"processed": {
		"id": "home.summaryChart.processed",
		"defaultMessage": "Processed"
	},
	"failed": {
		"id": "home.summaryChart.failed",
		"defaultMessage": "Failed"
	},
	"rejected": {
		"id": "home.summaryChart.rejected",
		"defaultMessage": "Rejected"
	},
	"other": {
		"id": "home.summaryChart.other",
		"defaultMessage": "Other"
	}
});

class SummaryChart extends Component {

	constructor(props) {
		super(props);
		this.prepareChart = this.prepareChart.bind(this);
	}

	async componentWillMount() {
		const { fetchSummaryChartData } = this.props;
		try {
			await fetchSummaryChartData()
		} catch (e) {
			console.error(e); //TODO
		}
	}

	prepareChart() {
		const { intl, data = {}, colors } = this.props;

        return {
            data: {
                labels: [intl.formatMessage(messages.processed), 
                	intl.formatMessage(messages.failed), 
                	intl.formatMessage(messages.rejected), 
                	intl.formatMessage(messages.other)],
                datasets: [{
                    data: [data.processed, 
                    	data.failed,
                    	data.rejected,
                    	data.other
                    	],
                    backgroundColor: colors
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
                title: {
                    display: true,
                    text: intl.formatMessage(messages.chartTitle)
                }
            }
        };		
	}

	render() {
		const { data, options } = this.prepareChart();
		return <Pie {...this.props} data={data} options={options}/>;
	}

}

SummaryChart = injectIntl(SummaryChart);

export default connect(state => ({
  data: state.home.summary
}), { fetchSummaryChartData })(SummaryChart);

