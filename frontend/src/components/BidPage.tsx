import {Card, CardContent, CardHeader, Typography} from '@mui/material';
import * as React from 'react'
import {useEffect, useState} from 'react';
import {useLocation} from "react-router-dom";
import {AxiosResponse} from "axios";
import {Banner} from "../types/Types";
import BannerService from "../service/BannerService";
import queryString from 'query-string';

export default function BidPage() {

    const [banner, setBanner] = useState<Banner>()
    const [error, setError] = useState<boolean>()
    const [message, setMessage] = useState<string>('')
    const params = queryString.parse(useLocation().search);

    const updateBanner = () => {
        BannerService.getBidBanner(params).then(
            (res: AxiosResponse<Banner>) => {
                const banner: Banner = res.data as Banner
                setBanner(banner)
                if (res.status === 204) {
                    setMessage("No content")
                    setError(true)
                }
            }
        );
    }

    useEffect(() => {
        updateBanner()
    }, [])

    return (
        <div className="bid-banner">
            <Card>
                <CardHeader title={banner?.name} subheader={banner?.price ? banner?.price + " units" : ''}/>
                <CardContent>
                    <Typography>
                        {banner?.text}
                    </Typography>
                </CardContent>
            </Card>
        </div>
    )
}