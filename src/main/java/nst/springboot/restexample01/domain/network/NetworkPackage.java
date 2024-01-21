package nst.springboot.restexample01.domain.network;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetworkPackage<T> {
    private T data;
    private LocalDateTime dateTime;
}
