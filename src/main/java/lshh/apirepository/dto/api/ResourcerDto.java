package lshh.apirepository.dto.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true, fluent = true)
@Data
public class ResourcerDto {
    int id;

    String name;
    String path;
    String description;

    String key;

    String driver;
    String driverDetail;
}
