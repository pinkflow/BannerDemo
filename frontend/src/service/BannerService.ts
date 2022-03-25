import axios, {AxiosRequestConfig} from "axios";
import AuthWrapper from "../authwrapper/AuthWrapper";
import {BannerData} from "../types/Types";

const API_BASE_URL = "http://localhost:8080"

class BannerService {

    async getBidBanner(searchParams: AxiosRequestConfig) {
        return axios.get(`${API_BASE_URL}/bid`, {params: searchParams})
    }

    async updateBanner(bannerData: BannerData) {
        return AuthWrapper.put(`/api/banner/${bannerData.id}`, bannerData)
    }

    async deleteBanner(bannerData: BannerData) {
        return AuthWrapper.delete(`/api/banner/${bannerData.id}`)
    }

    async createBanner(bannerData: BannerData) {
        return AuthWrapper.put(`/api/banner`, bannerData)
    }


    async getBanners(namePart: string | null = null) {
        return AuthWrapper.get(`/api/banner`,
            {namePart: namePart}
        )
    }


}

export default new BannerService();