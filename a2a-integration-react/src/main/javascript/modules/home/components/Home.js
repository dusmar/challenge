import React, {Component} from 'react'
import {FormattedMessage, defineMessages, injectIntl} from 'react-intl'
import { Line, Pie } from 'react-chartjs-2'

import HistoryChart from './HistoryChart.js'
import SummaryChart from './SummaryChart.js'

const CHART_COLORS = [
    "rgba(255,204,0,1)", "rgba(204,0,0,1)", "rgba(220,220,220,1)", "rgba(110,110,110,1)"
];

const messages = defineMessages({
	"mainTitle": {
		"id": "home.title",
		"defaultMessage": "Connectivity"
	}
});

class Home extends Component {

    constructor(props) {
        super(props);
    }


	render() {
	    const { intl } = this.props;
	    const title = intl.formatMessage(messages.mainTitle);
		return (
            <div className="">
				<h1>{title}</h1>
                <div className="row-margin-10">
				    <div className="row row-height-md">
					    <div className="col-md-8 padding-10 col-height-md">
                            <div>
					            <div className="box padding-10">
						      	    <HistoryChart colors={CHART_COLORS} />
                                </div>
						    </div>
                        </div>
    					<div className="col-md-4 padding-10 col-height-md">
                            <div>
        						<div className="box padding-10">
                                    <SummaryChart colors={CHART_COLORS} />
                                </div>
                            </div>
    					</div>
                    </div>
                </div>
                <div className="row row-margin-10">
					<div className="col-md-4 padding-10">
						<div className="box padding-10"></div>
					</div>
					<div className="col-md-4 padding-10">
						<div className="box padding-10"></div>
					</div>
					<div className="col-md-4 padding-10">
						<div className="box padding-10"></div>
					</div>
				</div>
			</div>
		)
	}

}

Home = injectIntl(Home);

export default Home;
