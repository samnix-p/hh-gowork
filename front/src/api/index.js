import axios from 'axios';
import ApiConstants from 'src/api/ApiConstants';

import store from 'src/store';

const getFormRequestOptions = (location, parameters) => {
    const urlEncodedParameters = new URLSearchParams();
    for (const [key, value] of Object.entries(parameters)) {
        urlEncodedParameters.append(key, value);
    }

    return [
        `${ApiConstants.API_BASE_URL}/${location}`,
        urlEncodedParameters,
        { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } },
    ];
};

const Api = {
    async register(email, password) {
        const requestOptions = getFormRequestOptions('register', { email, password });
        const response = await axios.post(...requestOptions);

        return response;
    },
    async login(email, password) {
        const requestOptions = getFormRequestOptions('login', { email, password });
        const response = await axios.post(...requestOptions);

        return response;
    },
    async logout() {
        const response = await axios.put(`${ApiConstants.API_BASE_URL}/logout`);

        return response;
    },
    async content() {
        store.menuSetIsLoading(true);

        const response = await axios.get(`${ApiConstants.API_BASE_URL}/content`);

        store.menuSetIsLoading(false);
        store.menuSetIsLoaded();

        return response;
    },
};

export default Api;
