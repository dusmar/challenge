/** 
 * 
 */
 import { readPaging } from '../../lib/paging/paging';

const reducer = (state = { list:{ count: 0, data:[], paging: {}, filter: {} }, detail:{} }, action) => {

  	switch (action.type) {
	case '@@messages/FETCH_MESSAGES_PENDING':
		return {
			...state,
			list: {
				...state.list,
				status: 'pending'
			}
		}
	case '@@messages/FETCH_MESSAGES_REJECTED':
		return {
			...state,
			list: {
				...state.list,
				status: 'failed',
				data: [],
				count: 0,
				paging: {},
				error: ''
			}
		}
	case '@@messages/FETCH_MESSAGES_FULFILLED': {
		const { data = [], headers } = action.payload;
		const paging = state.paging;
		const p = readPaging(headers, paging);
		const st = {
			...state,
			list: {
				...state.list,
				status: 'success',
				data,
				count: data.length,
				paging: p
			}
		};
		return st;
	}

	case '@@messages/FETCH_MESSAGE_PENDING':		
		return {
			...state,
			detail: {
				...state.detail,
				status: 'pending',
				data: {}	
			}
		}

	case '@@messages/FETCH_MESSAGE_FULFILLED':		
		return {
			...state,
			detail: {
				status: 'success',
				data: action.payload.data
			}
		}

	case '@@messages/FETCH_MESSAGE_REJECTED':		
		return {
			...state,
			detail: {
				status: 'error',
				data: {},
				error: ''
			}
		}

	default:
		return state
  }
}

export default reducer;