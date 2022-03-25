import AuthWrapper from "../authwrapper/AuthWrapper";
import {Category} from "../types/Types";

class CategoryService{

    async getCategory(id: number) {
        return AuthWrapper.get(`/api/category/${id}`)
    }

    async updateCategory(category: Category){
        return AuthWrapper.put(`/api/category`, category)
    }

    async deleteCategory(category: Category){
        return AuthWrapper.delete(`/api/category/${category.id}`)
    }

    async createCategory(category: Category){
        return AuthWrapper.put(`/api/category/${category.id}`, category)
    }

    async getCategories(namePart: string | null = null) {
        return AuthWrapper.get(`/api/category`,
            {namePart: namePart}
        )
    }

}

export default new CategoryService();