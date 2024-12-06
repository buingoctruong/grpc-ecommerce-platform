package github.io.truongbn.entiry;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderResponse {
    private String itemId;
    private Status status;
}
