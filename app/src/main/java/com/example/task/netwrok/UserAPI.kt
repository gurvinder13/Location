
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface UserAPI {

    @FormUrlEncoded
    @POST("get_monthly_appointments")
    fun getBookinglist(
        @Field("user_employeid") employeId: String?,
        @Field("status") page_no: String?,
        @Field("appointment_type") appointment_type: String?,
        @Field("month") appointment_date: String?
    ): Call<UserResponse>

}