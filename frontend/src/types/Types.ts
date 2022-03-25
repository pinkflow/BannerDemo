export type Banner = {
    id?: number
    name: string
    price?: number
    text: string
    categoriesList: Category[]
    status: string
}

export type Category = {
    id: number
    name: string
    request_id: string
}

export type Entity = Banner | Category

export type BannerData = Omit<Banner, 'categoriesList'> & { categoriesIdList: number[] }