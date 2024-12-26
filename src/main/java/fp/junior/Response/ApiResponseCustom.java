package fp.junior.Response;

import lombok.Data;

@Data
public class ApiResponseCustom<T> {
    private String message;
    private T data;
    private boolean success;

    public ApiResponseCustom(String message, T data, boolean success) {
        this.message = message;
        this.data = data;
        this.success = success;
    }

   }
