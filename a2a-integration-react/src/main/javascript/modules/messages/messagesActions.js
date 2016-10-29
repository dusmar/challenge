import axios from 'axios';
import { injectPaging } from '../../lib/paging/paging';

export const fetchMessages = (filter, paging) => {
	const headers = injectPaging(paging);

	//set query
	const params = {};
	if (filter && filter.query) {
		params.q = filter.query;
	}

	const payload = axios.get('/api/messages', {headers, params});
	return {
    	type: '@@messages/FETCH_MESSAGES',
    	payload,
    	filter
	}
};

export const fetchMessage = (id) => {
	const payload = axios.get('/api/messages/' + id);
	return {
    	type: '@@messages/FETCH_MESSAGE',
    	payload,
    	id
	}

}