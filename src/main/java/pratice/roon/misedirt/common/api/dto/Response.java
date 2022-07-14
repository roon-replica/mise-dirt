package pratice.roon.misedirt.common.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Response<T> {
    T data;
}
