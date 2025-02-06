package network;

import model.CategoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {
    @GET("categories.php")
    Call<CategoryResponse> getCategories();
}
