import fetch from 'unfetch';

const checkStatus = response => {
    if (response.ok) {
        return response;
    }
    const error = new Error(response.statusText);
    error.response = response;
    return Promise.reject(error);
}

export const getAllAliments = () =>
    fetch('/api/v1/aliment/all')
        .then(checkStatus);


export const getStats = (email) =>
    fetch('/api/v1/userStats/'+ email)
        .then(checkStatus);


export const getUserAliments = (email) =>
    fetch('/api/v1/userAliment/all/' + email)
        .then(checkStatus);

export const addAliment = aliment =>
    fetch('api/v1/aliment', {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(aliment)

    }).then(checkStatus);


export const deleteAllUserAliments = (email) =>
    fetch('/api/v1/userAliment/all/'+ email, {
        method: 'DELETE'

    }).then(checkStatus);


export const consumeAliment = (email, alimentName, quantity) =>
    fetch('/api/v1/userAliment', {
        headers: {
                    'Content-Type': 'application/json'
                },
        method: 'POST',
        body: JSON.stringify({email, alimentName, quantity})

    }).then(checkStatus);



export const deleteUserAliment = (email, alimentName) =>
    fetch('/api/v1/userAliment?email=' + email + '&alimentName=' + alimentName, {
        method: 'DELETE'
    }).then(checkStatus);

export const deleteAliment = (alimentName) =>
    fetch('/api/v1/aliment/' + alimentName, {
        method: 'DELETE'
    }).then(checkStatus);


export const signUp = (account) =>
    fetch('/api/v1/user', {
        headers: {
                    'Content-Type': 'application/json'
             },
        method: 'POST',
        body: JSON.stringify(account)
    }).then(checkStatus);


export const signInUser = (account) =>
    fetch('/api/v1/user/login?email=' + account.email + '&password=' + account.password)
        .then(checkStatus);








