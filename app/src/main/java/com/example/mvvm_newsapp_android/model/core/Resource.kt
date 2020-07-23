
import androidx.annotation.IntDef

class Resource<T> private constructor(@State var status: Int, var data: T? = null, var error: Throwable = NullPointerException("nothing")) {

    companion object {

        const val LOADING = 0
        const val SUCCESS = 1
        const val ERROR = 2

        @IntDef(
            LOADING,
            SUCCESS,
            ERROR
        )
        annotation class State

        /** Creates a new loading resource object  */
        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                status = LOADING,
                data = data
            )
        }

        /**
         * Creates a new successful resource object.
         * @param data the data to be set
         */
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(
                status = SUCCESS,
                data = data
            )
        }

        /**
         * Creates a new error resource object.
         * @param error the error
         */
        fun <T> error(error: Throwable, data: T? = null): Resource<T> {
            return Resource(
                status = ERROR,
                data = data,
                error = error
            )
        }
    }
}
