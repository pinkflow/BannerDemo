import axios from "axios";
import AuthWrapper from "../authwrapper/AuthWrapper";


class LoginService {
    async login(username: string, password: string) {
        const auth = window.btoa(username + ":" + password)
        AuthWrapper.setAuth(auth)
        return this.loginByAuth(auth)
    }

    async loginByAuth(auth: string) {
        return axios.post("http://localhost:8080/login", {
            headers: {
                "Authorization": 'Basic ' + auth
            }
        }).then(resp => {
            return resp.data;
        });
    }
}

export default new LoginService();