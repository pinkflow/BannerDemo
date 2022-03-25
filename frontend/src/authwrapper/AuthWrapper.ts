import axios from "axios";

const API_BASE_URL = "http://localhost:8080"

export default class AuthWrapper {

    private static auth: string = localStorage.getItem('auth') || '';

    static setAuth(auth: string) {
        AuthWrapper.auth = auth
        localStorage.setItem('auth', auth)
    }

    static get(url: string, params: any = {}) {
        console.log(AuthWrapper.auth)
        return axios.get(`${API_BASE_URL}${url}`, {
            params: params,
            headers: {
                "Authorization": 'Basic ' + AuthWrapper.auth
            }
        })
    }

    static delete(url: string) {
        return axios.delete(url, {
            headers: {
                "Authorization": 'Basic ' + window.btoa(localStorage.getItem("username") + ":" + localStorage.getItem("password"))
            }
        })
    }

    static put(url: string, data: any) {
        return axios.put(`${API_BASE_URL}/api/banner`, data, {
            headers: {
                "Authorization": 'Basic ' + window.btoa(localStorage.getItem("username") + ":" + localStorage.getItem("password"))
            }
        })
    }

}

