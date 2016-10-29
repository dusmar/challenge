/**
 * Set the value on the provided object. Creates 
 * all children on the path if nescessary
 */
export function setValue(obj, expression, value) {
	if (!expression) {
		//nothing to set
		return;
	}

	if (expression.length == 0) {
		return value;
	}

	if (!obj) {
		obj = {};
	}

	let o = obj;
	const path = expression.split('.');
	
	for (let i = 0; i < path.length - 1; i++) {
		const field = path[i];
		if (!field) {
			continue;
		}
		if (!o[field]) {
			o[field] = {};
		} 
		o = o[field];
	}

	const field = path[path.length - 1];
	o[field] = value;
	return obj;
}


/**
 * Reads the value from provided object
 */
export function getValue(obj, expression) {
	if (!obj || !expression || expression.length == 0) {
		return obj;
	}

	const path = expression.split('.');
	for (let i = 0; i < path.length; i++) {
		obj = obj[path[i]];
		if (!obj) {
			return null;
		}
	}

	return obj;
}


//TODO other helpers