import axios from 'axios';


/* *** actions *** */

export const fetchHistoryChartData = () => {
	return {
    	type: '@@home/FETCH_HISTORY',
    	payload: axios.get('/api/home/history')
	}
};

export const fetchSummaryChartData = () => {
	return {
    	type: '@@home/FETCH_SUMMARY',
    	payload: axios.get('/api/home/summary')
	}
};


/* *** reducer *** */
export const reducer = (state = {history: [], summary: {}}, action) => {
  	switch (action.type) {
	case '@@home/FETCH_HISTORY_PENDING':
		return {
			...state,
			status: 'pending'
		}
	case '@@home/FETCH_HISTORY_REJECTED':
		return {
			...state,
			status: 'failed',
			history: [],
			error: 'TODO'
		}
	case '@@home/FETCH_HISTORY_FULFILLED': 
		return {
			...state,
			status: 'success',
			history: action.payload.data
		};

	case '@@home/FETCH_SUMMARY_PENDING':
		return {
			...state,
			status: 'pending'
		}
	case '@@home/FETCH_SUMMARY_REJECTED':
		return {
			...state,
			status: 'failed',
			summary: {},
			error: 'TODO'
		}
	case '@@home/FETCH_SUMMARY_FULFILLED':
		return {
			...state,
			status: 'success',
			summary: action.payload.data
		};

	default:
		return state
  }
}
